import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class User {


    public User()
    {

    }

    public User(String userName, String pass)
    {
        setUserName(userName);
        setPassword(pass);
    }

    //userName
    private final StringProperty userNAme = new SimpleStringProperty(this, "userName");
    public StringProperty userNameProperty() {
        return userNAme ;
    }
    public final String getUserName() {
        return userNameProperty().get();
    }
    public final void setUserName(String userNAme) {
        userNameProperty().set(userNAme);
    }

    //password
    private final StringProperty password = new SimpleStringProperty(this, "pass");
    public StringProperty userPassword() {
        return password ;
    }
    public final String getPassword() {
        return userPassword().get();
    }
    public final void setPassword(String password) {
        userPassword().set(password);
    }
}
