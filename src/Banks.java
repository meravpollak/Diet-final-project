import java.sql.*;
import java.util.ArrayList;

public class Banks {
    private Connection connection ;

    public Banks(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
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
    public ArrayList<String> getBanks () throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from banks");
        ){
            ArrayList<String> banks = new ArrayList<>();
            while (rs.next()) {
                String bank = rs.getString("name");
                banks.add(bank);
            }
            return banks ;
        }
    }

    public void addNewBank(Integer id, String name) throws SQLException {
        Statement stmnt = connection.createStatement();
        stmnt.executeUpdate("INSERT INTO banks (id,name) " +
                "VALUES('" +id+ "','" + name +"')");
    }
}
