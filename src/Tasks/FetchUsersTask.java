package Tasks;

import Model.User;
import javafx.concurrent.Task;
import Model.DBUtils;
import java.util.List;

public class FetchUsersTask extends Task<List<User>> {
    @Override
    protected List<User> call() throws Exception {
        return DBUtils.getUsers();
    }
}
