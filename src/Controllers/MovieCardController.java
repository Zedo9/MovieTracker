package Controllers;

import Model.DBUtils;
import Model.Movie;
import Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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

    private User user;

    public void setUser(User user){
        this.user = user;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void handleFilmTitleButtonClicked(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../Views/MovieView.fxml"));
        System.out.println(filmTitle.getText());
        Parent root = loader.load();
        MovieController controller = loader.getController();
        controller.initData(movieModel,user);
        Scene scene = (Scene)((Node)e.getSource()).getScene();
        Parent windowRoot = scene.getRoot();
        List<Node> children = windowRoot.getChildrenUnmodifiable();
        HBox mainContent = (HBox) children.get(1);
        mainContent.getChildren().clear();
        mainContent.getChildren().add(root);
        System.out.println(children);
    }

    public void handleAddToWatchListButtonClick(ActionEvent e) {
        if (movieModel.isWatchList){
            DBUtils.deleteFromWatchList(movieModel.getId(),user.getId());
            movieModel.setWatchList(false);
            setAddToWatchList();
        }
        else {
            DBUtils.addToWatchList(movieModel.getId(), user.getId());
            movieModel.setWatchList(true);
            setRemoveFromWatchList();
        }
    }

    public void handleAddToFavouritesButtonClick(ActionEvent e) {
        if (movieModel.isFavourite){
            DBUtils.deleteFromFavourites(movieModel.getId(),user.getId());
            movieModel.setFavourite(false);
            setAddToFavourites();
        }
        else {
            DBUtils.addToFavourites(movieModel.getId(), user.getId());
            movieModel.setFavourite(true);
            setRemoveFromFavourites();
        }
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
        generateAddButtons();
    }

    public Movie getMovieModel() {
        return movieModel;
    }

    public void setRemoveFromWatchList(){
        addToWatchListButton.setStyle("-fx-background-color:#FF0000;");
        addToWatchListButton.setText("Remove from WatchList");
    }

    public void setAddToWatchList(){
        addToWatchListButton.setStyle("-fx-background-color:#90cea1;");
        addToWatchListButton.setText("Add to WatchList");
    }

    public void setAddToFavourites(){
        addToFavouritesButton.setStyle("-fx-background-color: #90cea1;");
        addToFavouritesButton.setText("Add to Favourites");
    }

    public void setRemoveFromFavourites(){
        addToFavouritesButton.setStyle("-fx-background-color:#FF0000;");
        addToFavouritesButton.setText("Remove from favourites");
    }

    public void generateAddButtons(){
        if (movieModel.isFavourite){
            setRemoveFromFavourites();
        }
        else {
            setAddToFavourites();
        }
        if (movieModel.isWatchList){
            setRemoveFromWatchList();
        }
        else {
            setAddToWatchList();
        }
    }
}
