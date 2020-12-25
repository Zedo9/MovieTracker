package Tasks;

import Model.DBUtils;
import Model.User;
import javafx.concurrent.Task;

public class FetchUserByUsernameTask extends Task<User> {

    public String getUsername() {
        return username;
    }

    private final String username;
    public FetchUserByUsernameTask(String username){
        this.username = username;
    }

    @Override
    protected User call() {
        return DBUtils.getUserByUsername(username);
    }
}
