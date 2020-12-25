package Controllers;

import Model.User;
import Tasks.FetchUserByUsernameTask;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class LoginController implements  Initializable{

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signupButton;

    @FXML
    private Button loginButton;

    @FXML
    private ImageView tmdbLogo;

    private User userModel;

    private Executor exec ;

    @FXML
    private Text errorText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t ;
        });
    }


    public void handleLoginButtonClick(ActionEvent e) {
        errorText.setText("");
        String usernameInput = usernameField.getText();
        String passwordInput = passwordField.getText();
        if (usernameInput.length() > 0){
            if (passwordInput.length() > 0){
                FetchUserByUsernameTask fetchTask = new FetchUserByUsernameTask(usernameInput);
                fetchTask.setOnSucceeded((ev1)->{
                    userModel = fetchTask.getValue();
                    if (userModel != null ){
                        if (userModel.getPassword().equals(passwordInput)){
                            try {
                                FXMLLoader loader = new FXMLLoader();
                                loader.setLocation(getClass().getResource("../Views/DashboardView.fxml"));
                                Parent root = null;
                                root = loader.load();
                                Scene s = new Scene(root,1280,720);
                                DashboardController controller = loader.getController();
                                System.out.println("LoginController " + userModel);
                                controller.initData(userModel);
                                Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
                                window.setScene(s);
                                window.show();
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                        }
                        else {
                            errorText.setText("Please verify your password");
                        }
                    }
                    else {
                        errorText.setText("User does not exist !");
                    }
                });
                exec.execute(fetchTask);
            }
            else {
                errorText.setText("Please enter your password");
            }
        }
        else {
            errorText.setText("Please enter your username");
        }
    }

    public void handleSignupButtonClick(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/SignupView.fxml"));
        Scene s = new Scene(root,1280,720);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }
}
