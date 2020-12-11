package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class MovieCardController  implements Initializable {

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

    public void handleFilmTitleButtonClicked(ActionEvent event) {
    }

    public void handleAddToWatchListButtonClick(ActionEvent event) {
    }

    public void handleAddToFavouritesButtonClick(ActionEvent event) {
    }
}
