package Controllers;

import Model.DBUtils;
import Model.Movie;
import Model.User;
import Tasks.FetchRecommendedMoviesTask;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MovieController implements Initializable {

    private Movie movieModel;
    private User userModel;
    @FXML
    private Text movieTitle;
    @FXML
    private Text movieRating;
    @FXML
    private Text movieOverview;
    @FXML
    private Text movieGenres;
    @FXML
    private ImageView moviePoster;
    @FXML
    private Text movieDuration;
    @FXML
    private Button addToWatchListButton;
    @FXML
    private Button addToFavouritesButton;
    @FXML
    private FlowPane content;

    private Executor exec ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initData(Movie movie, User user){
        exec = Executors.newCachedThreadPool(runnable -> {
            Thread t = new Thread(runnable);
            t.setDaemon(true);
            return t ;
        });
        movieModel = movie;
        userModel  = user;
        movieDuration.setText(((Integer)movieModel.getLength()).toString() + " minutes");
        StringBuilder genres = new StringBuilder();
        movieModel.getGenres().stream().limit(4).forEach(e -> genres.append(e).append(" | "));
        genres.setLength(genres.length()-2);
        movieGenres.setText(genres.toString());
        moviePoster.setImage(new Image("https://image.tmdb.org/t/p/w300" + movieModel.getPosterLink(),false));
        movieOverview.setText(movieModel.getOverview());
        movieRating.setText(((Float)movieModel.getRating()).toString());
        movieTitle.setText(movieModel.getTitle());
        generateAddButtons();
        Task<List<Movie>> fetchTask = new FetchRecommendedMoviesTask(((int)movieModel.getId()),user);
        fetchTask.setOnFailed(e -> fetchTask.getException().printStackTrace());
        fetchTask.setOnSucceeded((ev) -> {
            fetchTask.getValue().forEach((film) -> {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/MovieCardView.fxml"));
                try {
                    Parent MovieCard = loader.load();
                    MovieCardController movieCardController = loader.getController();
                    movieCardController.setMovieModel(film);
                    movieCardController.setUser(user);
                    movieCardController.generateAddButtons();
                    content.getChildren().add(MovieCard);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
        exec.execute(fetchTask);
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

    public void handleAddToWatchListButtonClick(ActionEvent e) {
        if (movieModel.isWatchList){
            DBUtils.deleteFromWatchList(movieModel.getId(),userModel.getId());
            movieModel.setWatchList(false);
            setAddToWatchList();
        }
        else {
            DBUtils.addToWatchList(movieModel.getId(), userModel.getId());
            movieModel.setWatchList(true);
            setRemoveFromWatchList();
        }
    }

    public void handleAddToFavouritesButtonClick(ActionEvent e) {
        if (movieModel.isFavourite){
            DBUtils.deleteFromFavourites(movieModel.getId(),userModel.getId());
            movieModel.setFavourite(false);
            setAddToFavourites();
        }
        else {
            DBUtils.addToFavourites(movieModel.getId(), userModel.getId());
            movieModel.setFavourite(true);
            setRemoveFromFavourites();
        }
    }
}
