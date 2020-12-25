package Tasks;

import Model.APIData;
import Model.Movie;
import Model.User;
import javafx.concurrent.Task;
import java.util.List;

public class FetchTrendingMoviesTask extends Task<List<Movie>> {
    private User user;
    @Override
    protected List<Movie> call(){
        APIData.setUser(user);
        return APIData.getTrendingMovies();
    }
    public FetchTrendingMoviesTask(User user){
        this.user = user;
    }
}
