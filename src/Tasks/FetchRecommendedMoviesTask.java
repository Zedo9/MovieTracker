package Tasks;

import Model.APIData;
import Model.Movie;
import Model.User;
import javafx.concurrent.Task;

import java.util.List;

public class FetchRecommendedMoviesTask extends Task<List<Movie>> {
    private int id;
    private User user;
    public FetchRecommendedMoviesTask(int id, User user){
        this.id=id;
        this.user = user;
    }

    @Override
    protected List<Movie> call(){
        APIData.setUser(user);
        return APIData.getRecommendations(id);
    }
}
