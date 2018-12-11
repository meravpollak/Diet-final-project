import java.sql.*;
import java.util.ArrayList;

public class DietMenuSuggest {

private Connection connection ;

    public DietMenuSuggest(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
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
    public ArrayList<String> getDiets () throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from diets");
        ){
            ArrayList<String> diets = new ArrayList<>();
            while (rs.next()) {
                String diet = rs.getString("name");
                diets.add(diet);
            }
            return diets ;
        }
    }

    public void addNewDiet(Integer id, String name) throws SQLException {
        Statement stmnt = connection.createStatement();
        stmnt.executeUpdate("INSERT INTO diets (id,name) " +
                "VALUES('" +id+ "','" + name +"')");

    }
}

