package Controllers;

import Model.DBUtils;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleSignupButtonClick(ActionEvent e) {
        errorText.setText("");
        DBUtils db = new DBUtils();
        if (usernameField.getText().length() > 0){
            if (passwordField.getText().length()>0){
                if (passwordConfirmField.getText().equals(passwordField.getText())){
                    try {
                        if (db.getUsers().stream().noneMatch(element -> element.getUsername().equals(usernameField.getText()))){
                            db.insertUser(new User(1,usernameField.getText(),passwordField.getText()));
                        }
                        else {
                            errorText.setText("Username Taken!");
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
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
