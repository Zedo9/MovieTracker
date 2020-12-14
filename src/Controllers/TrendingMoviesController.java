package Controllers;

import Model.APIData;
import Model.Movie;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.FlowPane;
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


    public TrendingMoviesController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        ProgressBar progressBar = new ProgressBar();
//        content.getChildren().add(progressBar);
        Task<List<Movie>> fetchTask = new Task<List<Movie>>() {
            @Override
            public List<Movie> call() {
                return APIData.getTrendingMovies();
            }
        };
        fetchTask.setOnFailed(e -> {
            fetchTask.getException().printStackTrace();
        });

        fetchTask.setOnSucceeded((ev) -> {
            //progressBar.setVisible(false);
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
        });
        try {
            //progressBar.progressProperty().bind(fetchTask.progressProperty());
            Thread t = new Thread(fetchTask);
            t.setDaemon(true); // Imp! missing in your code
            t.start();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

    }
}
