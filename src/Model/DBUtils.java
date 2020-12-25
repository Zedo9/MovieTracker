package Model;

import java.sql.*;
import java.util.ArrayList;

public class DBUtils {
    private static Connection conn;
    private static PreparedStatement ps;
    private static ResultSet resp;

    private static final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://mysql-chedlyz.alwaysdata.net/chedlyz_showtracker";
    private static final String USERNAME = "chedlyz";
    private static final String PASSWORD ="tFb8rEAUcdy89ek";

    public static void connect(){
        try {
            Class.forName(DRIVER_CLASS).newInstance();
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> getUsers() throws SQLException {
        ArrayList<User> result = new ArrayList<>();
        connect();
        try {
            ps = conn.prepareStatement("SELECT * FROM user");
            resp = ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        while (resp.next()){
            int id = resp.getInt("id");
            String user = resp.getString("username");
            String pass = resp.getString("password");
            result.add(new User(id,user,pass));
        }
        close();
        return result;
    }

    public static User getUserByUsername(String username){
        User result;
        result = null;
        String query = "SELECT * FROM user WHERE username=?;";
        connect();
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1,username);
            System.out.println(ps);
            resp = ps.executeQuery();
            if (resp.next()){
                result = new User(resp.getInt("id"),resp.getString("username"),resp.getString("password"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close();
        return result;
    }

    public static ArrayList<Double> getUserFavourites(int userID){
        ArrayList<Double> result = new ArrayList<>();
        connect();
        String query = "SELECT * FROM favourites WHERE user_id=?;";
        connect();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,userID);
            System.out.println(ps);
            resp = ps.executeQuery();
            while (resp.next()){
                result.add(resp.getDouble("movie_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close();
        return result;
    }

    public static ArrayList<Double> getUserWatchList(int userID){
        ArrayList<Double> result = new ArrayList<>();
        connect();
        String query = "SELECT * FROM watchlist WHERE user_id=?;";
        connect();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,userID);
            System.out.println(ps);
            resp = ps.executeQuery();
            while (resp.next()){
                result.add(resp.getDouble("movie_id"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close();
        return result;
    }

    public static void insertUser(User user){
        String query = "INSERT INTO user(username,password) VALUES (?,?);";
        connect();
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close();
    }

    public static void deleteFromWatchList(double movie_id,int user_id){
        String query = "DELETE FROM watchlist WHERE user_id=? and movie_id=?;";
        connect();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,user_id);
            ps.setInt(2,(int)movie_id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close();
    }

    public static void addToWatchList(double movie_id,int user_id){
        String query = "INSERT INTO watchlist(user_id,movie_id) VALUES (?,?);";
        connect();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,user_id);
            ps.setInt(2,(int)movie_id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close();
    }

    public static void deleteFromFavourites(double movie_id,int user_id){
        String query = "DELETE FROM favourites WHERE user_id=? and movie_id=?;";
        connect();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,user_id);
            ps.setInt(2,(int)movie_id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close();
    }

    public static void addToFavourites(double movie_id,int user_id){
        String query = "INSERT INTO favourites(user_id,movie_id) VALUES (?,?);";
        connect();
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1,user_id);
            ps.setInt(2,(int)movie_id);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close();
    }

    public static void close(){
        try {
            resp.close();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
