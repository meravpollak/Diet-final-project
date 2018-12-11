import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataAccessor {
    private Connection connection ;

    public UserDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    //close connection
    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }



    public int checkIfUserExists(String user) throws SQLException
    {
        final String queryCheck = "SELECT * from user WHERE userName = ?";
        final PreparedStatement ps = connection.prepareStatement(queryCheck);
        ps.setString(1, user);
        final ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()) {
            //System.out.println(resultSet.getString(1));
            return 1;
        }
       return 0;
    }

    public int checkIfpasswordCorrent(String user, String password) throws SQLException
    {
        String queryString = "SELECT * FROM user where userName=? and pass=?";
        final PreparedStatement ps = connection.prepareStatement(queryString);
        ps.setString(1,user);
        ps.setString(2,password);
        final ResultSet resultSet = ps.executeQuery();
        if(resultSet.next()) {
             return 1;
        }
        return 0;
    }

    //get UserList
    public List<User> getUserList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from user");
        ){
            List<User> userList = new ArrayList<>();
            while (rs.next()) {
                String userName = rs.getString("userName");
                String password = rs.getString("password");
                User user = new User(userName,password);
                userList.add(user);
            }
            return userList ;
        }
    }

    //add patient
    public void addUser(String userName, String password)
            throws SQLException {
        Statement stmnt = connection.createStatement();
        stmnt.executeUpdate("INSERT INTO user (userName,  password) " +
                "VALUES('" +userName+ "','" + password +"')");
    }

    //removePatient
    public void removePatient(String userName) throws SQLException
    {
        int action = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this user?");
        if(action == 0) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM user WHERE i = ?");
            statement.setString(1, userName);
            statement.executeUpdate();
        }
    }
}
