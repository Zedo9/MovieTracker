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
        execute("SELECT * FROM user");
        while (resp.next()){
            int id = resp.getInt("id");
            String user = resp.getString("username");
            String pass = resp.getString("password");
            result.add(new User(id,user,pass));
        }
        close();
        return result;
    }

    public static void insertUser(User user){
        System.out.println(user);
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

    public static void execute(String query){
        connect();
        try {
            ps = conn.prepareStatement(query);
            resp = ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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
