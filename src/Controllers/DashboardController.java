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
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private Label welcomeText;

    @FXML
    private Button trendingButton;

    @FXML
    private HBox mainContent;

    private LinkedList<Boolean> bits;

    private User user;

    public void initData(User user) throws IOException {
        this.user = user;
        welcomeText.setText("Hi "+user.getUsername());
        handleTrendingButtonClick(null);
    }
    public FXMLLoader prepareLoader(String fxmlLink) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../Views/"+fxmlLink));
        Parent root = null;
        return loader;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleTrendingButtonClick(ActionEvent e) throws IOException {
        FXMLLoader loader =  prepareLoader("TrendingMoviesView.fxml");
        Parent root = loader.load();
        TrendingMoviesController controller = loader.getController();
        controller.initData(user);
        System.out.println("Dashboard " + user);
        mainContent.getChildren().clear();
        mainContent.getChildren().add(root);
    }

    public void handleUpcomingButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = prepareLoader("UpcomingMoviesView.fxml");
        Parent root = loader.load();
        UpcomingMoviesController controller = loader.getController();
        controller.initData(user);
        mainContent.getChildren().clear();
        mainContent.getChildren().add(root);
    }

    public void handleSearchButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = prepareLoader("SearchMoviesView.fxml");
        Parent root = loader.load();
        SearchMoviesController controller = loader.getController();
        controller.initData(user);
        mainContent.getChildren().clear();
        mainContent.getChildren().add(root);
    }

    public void handleWatchListButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = prepareLoader("WatchListMoviesView.fxml");
        Parent root = loader.load();
        WatchListMoviesController controller = loader.getController();
        controller.initData(user);
        mainContent.getChildren().clear();
        mainContent.getChildren().add(root);
    }

    public void handleFavouritesButtonClick(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = prepareLoader("FavouriteMoviesView.fxml");
        Parent root = loader.load();
        FavouriteMoviesController controller = loader.getController();
        controller.initData(user);
        mainContent.getChildren().clear();
        mainContent.getChildren().add(root);
    }

    public void handleLogoutButtonClick(ActionEvent e) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../Views/LoginView.fxml"));
        Scene s = new Scene(root,1280,720);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }
}
