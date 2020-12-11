package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void handleLoginButtonClick(ActionEvent actionEvent) {
        System.out.println(usernameField.getText());
    }

    public void handleSignupButtonClick(ActionEvent actionEvent) {
    }
}
