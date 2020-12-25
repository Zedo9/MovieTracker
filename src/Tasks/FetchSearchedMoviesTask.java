package Tasks;

import Model.APIData;
import Model.Movie;
import Model.User;
import javafx.concurrent.Task;

import java.util.List;

public class FetchSearchedMoviesTask extends Task<List<Movie>> {
    private String query;
    private User user;
    public FetchSearchedMoviesTask(String query,User user){
        this.query=query;
        this.user = user;
    }

    @Override
    protected List<Movie> call(){
        APIData.setUser(user);
        return APIData.getSearchMovie(query);
    }
}
