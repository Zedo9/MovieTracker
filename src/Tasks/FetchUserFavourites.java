package Tasks;

import Model.APIData;
import Model.DBUtils;
import Model.Movie;
import javafx.concurrent.Task;

import java.util.LinkedList;
import java.util.List;

public class FetchUserFavourites extends Task<List<Movie>> {

    private final int userID;
    public FetchUserFavourites(int userID){
        this.userID = userID;
    }

    @Override
    protected List<Movie> call() {
        List<Movie> result = new LinkedList<>();
        List<Double> list = DBUtils.getUserFavourites(userID);
        list.forEach(e-> result.add(APIData.getMovieById((int)((double)e))));
        return result;
    }
}
