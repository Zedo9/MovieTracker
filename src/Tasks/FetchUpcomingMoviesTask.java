package Tasks;

import Model.APIData;
import Model.Movie;
import Model.User;
import javafx.concurrent.Task;

import java.util.List;

public class FetchUpcomingMoviesTask extends Task<List<Movie>> {

    private User user;

    public FetchUpcomingMoviesTask(User user){
        this.user = user;
    }

    @Override
    protected List<Movie> call(){
        APIData.setUser(user);
        return APIData.getUpcomingMovies();
    }
}
