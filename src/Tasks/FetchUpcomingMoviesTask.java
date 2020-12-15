package Tasks;

import Model.APIData;
import Model.Movie;
import javafx.concurrent.Task;

import java.util.List;

public class FetchUpcomingMoviesTask extends Task<List<Movie>> {
    @Override
    protected List<Movie> call(){
        return APIData.getUpcomingMovies();
    }
}
