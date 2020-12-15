package Model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class APIData {
    private final static APIUtils api = new APIUtils();
    private final static String API_KEY = "?api_key=82aa513c60c3c3a2179c97c32968a4d4";
    private final static String API_LINK = "https://api.themoviedb.org/3";

    public static ArrayList<Movie> getSearchMovie(String query){
        String pureQuery = query.replace(" ","%20");
        StringBuilder url = new StringBuilder(API_LINK);
        url.append("/search/movie");
        url.append(API_KEY);
        url.append("&query=");
        url.append(pureQuery);
        System.out.println(url.toString());
        api.connect(url.toString());
        String resp = api.getResponse().toString();
        api.closeConnection();
        ArrayList<Movie> result = new ArrayList<>();
        JSONObject respJSON = new JSONObject(resp);
        JSONArray movieObjects = respJSON.getJSONArray("results");
        movieObjects.forEach(e -> {
            JSONObject movie = (JSONObject) e;
            result.add(getMovieById((movie.getInt("id"))));
        });
        System.out.println(result);
        return result;
    }

    public static ArrayList<Movie> getTrendingMovies(){
        StringBuilder url = new StringBuilder(API_LINK);
        url.append("/movie/popular");
        url.append(API_KEY);
        System.out.println(url.toString());
        api.connect(url.toString());
        String resp = api.getResponse().toString();
        api.closeConnection();
        ArrayList<Movie> result = new ArrayList<>();
        JSONObject respJSON = new JSONObject(resp);
        JSONArray movieObjects = respJSON.getJSONArray("results");
        movieObjects.forEach(e -> {
            JSONObject movie = (JSONObject) e;
            result.add(getMovieById((movie.getInt("id"))));
        });
        System.out.println(result);
        return result;
    }

    public static ArrayList<Movie> getUpcomingMovies(){
        StringBuilder url = new StringBuilder(API_LINK);
        url.append("/movie/upcoming");
        url.append(API_KEY);
        System.out.println(url.toString());
        api.connect(url.toString());
        String resp = api.getResponse().toString();
        api.closeConnection();
        ArrayList<Movie> result = new ArrayList<>();
        JSONObject respJSON = new JSONObject(resp);
        JSONArray movieObjects = respJSON.getJSONArray("results");
        movieObjects.forEach(e -> {
            JSONObject movie = (JSONObject) e;
            result.add(getMovieById((movie.getInt("id"))));
        });
        System.out.println(result);
        return result;
    }

    public static Movie getMovieById(int id){
        StringBuilder url = new StringBuilder(API_LINK);
        url.append("/movie/");
        url.append(id);
        url.append(API_KEY);
        System.out.println(url.toString());
        api.connect(url.toString());
        String resp = api.getResponse().toString();
        api.closeConnection();
        JSONObject resJson = new JSONObject(resp);
        int moveId = resJson.getInt("id");
        Object poster = resJson.get("poster_path");
        String moviePoster ="";
        if (poster != JSONObject.NULL){
            moviePoster = resJson.getString("poster_path");
        }
        String movieOverview = resJson.getString("overview");
        String movieReleaseDate = resJson.getString("release_date");
        Object runtime = resJson.get("runtime");
        int movieLength =0;
        if (runtime != JSONObject.NULL){
            movieLength = resJson.getInt("runtime");
        }
        String movieTitle = resJson.getString("title");
        float movieRating = resJson.getFloat("vote_average");
        int movieRevenue = resJson.getInt("revenue");
        Movie result = new Movie(moveId,movieRating,movieLength,moviePoster,movieTitle,movieReleaseDate,movieOverview,false,false);
        result.setGenres(getGenres(new JSONArray(resJson.getJSONArray("genres"))));
        return result;
    }

    public static ArrayList<Actor> getActors(int movieId){
        ArrayList<Actor> result = new ArrayList<>();
        return result;
    }

    public static ArrayList<String> getGenres(JSONArray genresArray){
        ArrayList<String> result = new ArrayList<>();
        genresArray.forEach(e-> {
            JSONObject genres = (JSONObject) e;
            result.add(genres.getString("name"));
        });
        if (result.size() == 0){
            result.add("Unknown");
        }
        return result;
    }
}