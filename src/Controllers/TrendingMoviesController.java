package Controllers;

import Model.APIData;
import Model.Movie;
import Tasks.FetchTrendingMoviesTask;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TrendingMoviesController implements Initializable {
    @FXML
    private FlowPane content;

    @FXML
    private VBox container;

    @FXML
    private HBox titleContainer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ImageView loadingView = new ImageView(new Image("Main/resources/assets/loading7.gif"));
        loadingView.setFitWidth(45);
        loadingView.setFitHeight(45);
        titleContainer.getChildren().add(loadingView);
        Task<List<Movie>> fetchTask = new FetchTrendingMoviesTask();
        fetchTask.setOnFailed(e -> fetchTask.getException().printStackTrace());
        fetchTask.setOnSucceeded((ev) -> {
            fetchTask.getValue().forEach((movie) -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/MovieCardView.fxml"));
                try {
                    Parent MovieCard = loader.load();
                    MovieCardController movieCardController = loader.getController();
                    movieCardController.setMovieModel(movie);
                    content.getChildren().add(MovieCard);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            titleContainer.getChildren().remove(1);
        });
        try {
            Thread t = new Thread(fetchTask);
            t.setDaemon(true);
            t.start();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}
