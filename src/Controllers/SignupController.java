package Controllers;

import Model.DBUtils;
import Model.User;
import Tasks.FetchUsersTask;
import Tasks.InsertUserTask;
import javafx.concurrent.Task;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SignupController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField passwordConfirmField;
    @FXML
    private Button backToLoginButton;
    @FXML
    private Button signupButton;
    @FXML
    private Text errorText;
    @FXML
    private VBox container;
    private Executor exec ;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t ;
        });
    }

    public void handleSignupButtonClick(ActionEvent e) {
        errorText.setFill(Color.RED);
        errorText.setText("");
        if (usernameField.getText().length() > 0){
            if (passwordField.getText().length()>0){
                if (passwordConfirmField.getText().equals(passwordField.getText())){
                    FetchUsersTask fetchUsersTask = new FetchUsersTask();
                    fetchUsersTask.setOnSucceeded((ev1) -> {
                        List<User> result = fetchUsersTask.getValue();
                        if (result.stream().noneMatch(element -> element.getUsername().equals(usernameField.getText()))){
                            InsertUserTask insertUserTask = new InsertUserTask(new User(1,usernameField.getText(),passwordField.getText()));
                            insertUserTask.setOnSucceeded((ev2)->{
                                errorText.setFill(Color.GREEN);
                                errorText.setText("User created successfully !");
                            });
                            exec.execute(insertUserTask);
                        }
                        else {
                            errorText.setText("Username Taken!");
                        }
                    });
                    fetchUsersTask.run();
                }
                else {
                    errorText.setText("Passwords don't match!");
                }
            }
            else {
                errorText.setText("Please specify a password!");
            }
        }
        else {
            errorText.setText("Please specify a username!");
        }
    }

    public void handleBackToLoginButtonClick(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/LoginView.fxml"));
        Scene s = new Scene(root,1280,720);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window .setScene(s);
        window.show();
    }
}
