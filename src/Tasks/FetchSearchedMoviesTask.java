package Tasks;

import Model.APIData;
import Model.Movie;
import javafx.concurrent.Task;

import java.util.List;

public class FetchSearchedMoviesTask extends Task<List<Movie>> {
    private String query;
    public FetchSearchedMoviesTask(String query){
        this.query=query;
    }

    @Override
    protected List<Movie> call(){
        return APIData.getSearchMovie(query);
    }
}
