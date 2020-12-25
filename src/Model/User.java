package Model;

import java.util.ArrayList;

public class User {
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    private int id;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    private String username;

    public String getPassword() {
        return password;
    }


    private String password;
    private ArrayList<Double> favourites;
    private ArrayList<Double> watchList;

    public ArrayList<Double> getWatchList() {
        return watchList;
    }

    public ArrayList<Double> getFavourites() {
        return favourites;
    }


    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        fetchFavourites();
        fetchWatchList();
    }

    public void addToFavourites(int id){

    }

    public void fetchFavourites(){
        favourites = DBUtils.getUserFavourites(id);
    }

    public void fetchWatchList(){
        watchList = DBUtils.getUserWatchList(id);
    }
}
