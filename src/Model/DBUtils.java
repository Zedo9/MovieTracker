package Model;

import java.sql.*;

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

    public void execute(String query){
        connect();
        try {
            ps = conn.prepareStatement(query);
            resp = ps.executeQuery();
            while (resp.next()){
                int id = resp.getInt("id");
                String user = resp.getString("username");
                String pass = resp.getString("password");
                System.out.println(id + " " + user + " " + pass);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        close();
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
