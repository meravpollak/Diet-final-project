import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Cities {
    private Connection connection ;

    public Cities(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
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
    public ArrayList<String> getCities () throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from cities");
        ){
            ArrayList<String> cities = new ArrayList<>();
            while (rs.next()) {
                String city = rs.getString("name");
                cities.add(city);
            }
            return cities ;
        }
    }

    public void addNewCity(Integer id, String name) throws SQLException {
        Statement stmnt = connection.createStatement();
        stmnt.executeUpdate("INSERT INTO cities (id,name) " +
                "VALUES('" +id+ "','" + name +"')");
    }
}
