package Controllers;

import Model.Movie;
import Model.User;
import Tasks.FetchSearchedMoviesTask;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SearchMoviesController implements Initializable {
    @FXML
    private FlowPane content;

    @FXML
    private VBox container;

    @FXML
    private HBox titleContainer;

    @FXML
    private TextField searchField;

    private ImageView loadingView;


    private User user;

    public void initData(User user){
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImageView loadingView = new ImageView(new Image("Main/resources/assets/loading7.gif"));
        loadingView.setFitWidth(45);
        loadingView.setFitHeight(45);
        titleContainer.getChildren().add(loadingView);
        titleContainer.getChildren().remove(1);
    }

    public void handleSearchButtonClick(javafx.event.ActionEvent event) {
        String query = searchField.getText();
        Task<List<Movie>> fetchTask = new FetchSearchedMoviesTask(query,user);
        fetchTask.setOnFailed(e -> fetchTask.getException().printStackTrace());
        fetchTask.setOnSucceeded((ev) -> {
            content.getChildren().clear();
            fetchTask.getValue().forEach((movie) -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/MovieCardView.fxml"));
                try {
                    Parent MovieCard = loader.load();
                    MovieCardController movieCardController = loader.getController();
                    movieCardController.setMovieModel(movie);
                    movieCardController.setUser(user);
                    content.getChildren().add(MovieCard);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        try {
            Thread t = new Thread(fetchTask);
            t.setDaemon(true);
            Platform.runLater(()->{

            });
            t.start();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
