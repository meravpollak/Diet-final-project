import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MissingReportAccessor {
    private Connection connection ;

    public MissingReportAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
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
    public ArrayList<MissingObject> getMissingPatients(LocalDate startDate, LocalDate endDate) throws SQLException {
        Date start = java.sql.Date.valueOf(startDate);
        Date end = java.sql.Date.valueOf(endDate);
        ArrayList<MissingObject> missingObjects = new ArrayList<>();
        //final String queryCheck = "SELECT * from missingpatient WHERE date BETWEEN "+start+" AND "+end;
        final String queryCheck = "SELECT * from missingpatient WHERE date >= "+start;
        final PreparedStatement ps = connection.prepareStatement(queryCheck);
        final ResultSet rs = ps.executeQuery();
        while (rs.next()) {

                Integer id_in = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Integer tel = rs.getInt("tel");
                LocalDate date = rs.getDate("date").toLocalDate();
                Time time = rs.getTime("time");
                Integer week = rs.getInt("week");

                MissingObject missingObject = new MissingObject(id_in,firstName,lastName,tel,date,time,week);
                missingObjects.add(missingObject);
            }
            return missingObjects ;
        }

}
