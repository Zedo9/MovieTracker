package Controllers;

import Model.User;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void handleLoginButtonClick(ActionEvent e) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../Views/DashboardView.fxml"));
        Parent root = loader.load();

        Scene s = new Scene(root,1280,720);

        DashboardController controller = loader.getController();
        User user = new User(1,"TEST","pass");
        controller.initData(user);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }

    public void handleSignupButtonClick(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/SignupView.fxml"));
        Scene s = new Scene(root,1280,720);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }
}
