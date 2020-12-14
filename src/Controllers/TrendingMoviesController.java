package Controllers;

import Model.APIData;
import Model.Movie;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TrendingMoviesController implements Initializable {
    @FXML
    private FlowPane content;

    @FXML
    private VBox container;

    private ArrayList<Movie> trending;

    public TrendingMoviesController(){
        trending = APIData.getTrendingMovies();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        trending.forEach(movie -> {
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
    }
}
