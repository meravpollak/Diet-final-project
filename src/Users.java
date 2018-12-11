import java.sql.*;
import java.util.ArrayList;

public class Users { private Connection connection ;

    public Users(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    //close connection
    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    //get all the patients
    public ArrayList<String> getUsers () throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from users");
        ){
            ArrayList<String> users = new ArrayList<>();
            while (rs.next()) {
                String user = rs.getString("name");
                users.add(user);
            }
            return users ;
        }
    }

    public void addNewUser(Integer id, String name) throws SQLException {
        Statement stmnt = connection.createStatement();
        stmnt.executeUpdate("INSERT INTO users (id,name) " +
                "VALUES('" +id+ "','" + name +"')");
    }
}
