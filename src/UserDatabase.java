import java.io.File;
import java.io.IOException;




public class UserDatabase {

    private static final UserDatabase single_instance = new UserDatabase();


    private UserDatabase() {}

    public static UserDatabase getInstance() {
        return single_instance;
    }



}
