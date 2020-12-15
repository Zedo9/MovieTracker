package Tasks;

import Model.APIData;
import Model.Movie;
import javafx.concurrent.Task;
import java.util.List;

public class FetchTrendingMoviesTask extends Task<List<Movie>> {
    @Override
    protected List<Movie> call(){
        return APIData.getTrendingMovies();
    }
}
