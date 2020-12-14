package Tasks;

import Model.DBUtils;
import Model.User;
import javafx.concurrent.Task;

public class InsertUserTask extends Task<Void> {
    private final User user;
    public InsertUserTask(User user){
        this.user = user;
    }

    @Override
    protected Void call() {
        DBUtils.insertUser(user);
        return null;
    }
}
