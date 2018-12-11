import java.sql.*;
import java.util.ArrayList;

public class Therapy {

    private Connection connection ;

    public Therapy(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
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
    public ArrayList<String> getTherapy () throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from therapy");
        ){
            ArrayList<String> therapies = new ArrayList<>();
            while (rs.next()) {
                String therapy = rs.getString("name");
                therapies.add(therapy);
            }
            return therapies ;
        }
    }

    public void addNewTherapy(Integer id, String name) throws SQLException {
        Statement stmnt = connection.createStatement();
        stmnt.executeUpdate("INSERT INTO therapy (id,name) " +
                "VALUES('" +id+ "','" + name +"')");

    }
}
