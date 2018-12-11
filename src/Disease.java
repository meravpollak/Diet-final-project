import java.sql.*;
import java.util.ArrayList;

public class Disease {
    private Connection connection ;

    public Disease(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
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
    public ArrayList<String> getDisease () throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from disease");
        ){
            ArrayList<String> diseases = new ArrayList<>();
            while (rs.next()) {
                String disease = rs.getString("name");
                diseases.add(disease);
            }
            return diseases ;
        }
    }

    public void addNewDisease(Integer id, String name) throws SQLException {
        Statement stmnt = connection.createStatement();
        stmnt.executeUpdate("INSERT INTO disease (id,name) " +
                "VALUES('" +id+ "','" + name +"')");

    }
}

