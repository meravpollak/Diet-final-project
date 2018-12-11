import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class Foods {
    private Connection connection ;

    public Foods(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
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
    public ArrayList<String> getFoods () throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from foods");
        ){
            ArrayList<String> foods = new ArrayList<>();
            while (rs.next()) {
                String food = rs.getString("name");
                foods.add(food);
            }
            return foods ;
        }
    }

    public void addNewFood(Integer id, String name) throws SQLException {
        Statement stmnt = connection.createStatement();
        stmnt.executeUpdate("INSERT INTO foods (id,name) " +
                "VALUES('" +id+ "','" + name +"')");

    }
}
