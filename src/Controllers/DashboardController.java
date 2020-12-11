package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    @FXML
    private Text welcomeText;

    @FXML
    private Button trendingButton;

    @FXML
    private HBox mainContent;

    public void navigateTo(String fxmlLink) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/"+fxmlLink));
        mainContent.getChildren().clear();
        mainContent.getChildren().add(root);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleTrendingButtonClick(ActionEvent e) throws IOException {
        navigateTo("TrendingMoviesView.fxml");
    }

    public void handleUpcomingButtonClick(ActionEvent actionEvent) {
    }

    public void handleSearchButtonClick(ActionEvent actionEvent) {
    }

    public void handleWatchListButtonClick(ActionEvent actionEvent) {
    }

    public void handleFavouritesButtonClick(ActionEvent actionEvent) {
    }

    public void handleLogoutButtonClick(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/LoginView.fxml"));
        Scene s = new Scene(root,1280,720);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window .setScene(s);
        window.show();
    }
}
