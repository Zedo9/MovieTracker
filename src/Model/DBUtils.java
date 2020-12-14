package Model;

import java.sql.*;
import java.util.ArrayList;

public class DBUtils {
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet resp;

    private final String DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    private final String URL = "jdbc:mysql://mysql-chedlyz.alwaysdata.net/chedlyz_showtracker";
    private final String USERNAME = "chedlyz";
    private final String PASSWORD ="tFb8rEAUcdy89ek";

    public void connect(){
        try {
            Class.forName(DRIVER_CLASS).newInstance();
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> getUsers() throws SQLException {
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

    public void insertUser(User user){
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

    public void execute(String query){
        connect();
        try {
            ps = conn.prepareStatement(query);
            resp = ps.executeQuery();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void close(){
        try {
            resp.close();
            ps.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
