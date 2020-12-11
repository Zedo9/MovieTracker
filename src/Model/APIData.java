package Model;

public class APIData {
    private final static APIUtils api = new APIUtils();
    private final static String API_KEY = "?api_key=82aa513c60c3c3a2179c97c32968a4d4";
    private final static String API_LINK = "https://api.themoviedb.org/3";

    public static String getMovieById(int id){
        StringBuffer url = new StringBuffer(API_LINK);
        url.append("/movie/");
        url.append(id);
        url.append(API_KEY);
        System.out.println(url.toString());
        api.connect(url.toString());
        String res = api.getResponse().toString();
        api.closeConnection();
        return res;
    }

}