package Controllers;

import Model.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieCardController  implements Initializable {
    private Movie movieModel;
    @FXML
    private ImageView filmPoster;

    @FXML
    private Button filmTitle;

    @FXML
    private Text genresText;

    @FXML
    private Button addToWatchListButton;

    @FXML
    private Button addToFavouritesButton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void handleFilmTitleButtonClicked(ActionEvent e) {
        System.out.println(filmTitle.getText());
    }

    public void handleAddToWatchListButtonClick(ActionEvent e) {
    }

    public void handleAddToFavouritesButtonClick(ActionEvent e) {
    }

    public void setMovieModel(Movie movieModel){
        this.movieModel = movieModel;
        // setting title
        filmTitle.setText(movieModel.getTitle());
        //setting genres
        StringBuilder genres = new StringBuilder();
        movieModel.getGenres().stream().limit(4).forEach(e -> genres.append(e).append(" | "));
        genres.setLength(genres.length()-2);
        genresText.setText(genres.toString());
        filmPoster.setImage(new Image ("https://image.tmdb.org/t/p/w300" + movieModel.getPosterLink(),false));
    }

    public Movie getMovieModel() {
        return movieModel;
    }
}
