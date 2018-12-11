import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MeetingCenterData { private Connection connection ;

    public MeetingCenterData(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    //close connection
    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    //add patient
    public void addNewMeeting(String id, String firstName, String lastName, Integer tel, LocalTime time, LocalDate date, Integer arrived, Integer newPatient)
            throws SQLException {
        Statement stmnt = connection.createStatement();
        Date date_ToInsert = java.sql.Date.valueOf(date); //convert to date in DB
        Time time_ToInsert = java.sql.Time.valueOf(time);
        stmnt.executeUpdate("INSERT INTO meeting (date, id, firstName, lastName, time, arrived, new, tel) " +
                "VALUES('" +date_ToInsert+ "','" + id + "','" + firstName + "','" + lastName + "','" + time_ToInsert + "','" + arrived + "','" + newPatient + "','" + tel+"')");
    }

    public ArrayList<Meeting_Patient_Object> getAllMeetings() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from meeting");
        ){
            ArrayList<Meeting_Patient_Object> meetings = new ArrayList<>();
            while (rs.next()) {

                String id_in = rs.getString("id");
                LocalDate date_in = rs.getDate("date").toLocalDate();
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Integer arrived_in = rs.getInt("arrived");
                Integer tel = rs.getInt("tel");
                Integer new_in = rs.getInt("new");
                String time = rs.getString("time");
                Meeting_Patient_Object meeting_obj = new Meeting_Patient_Object(id_in,date_in,time,firstName,lastName,tel,arrived_in,new_in);
                meetings.add(meeting_obj);
            }
            return meetings ;
        }
    }

    public ArrayList<Meeting_Patient_Object> getMeetingOfSelectedDate(Date date1) throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from meeting");
        ){
            ArrayList<Meeting_Patient_Object> meetings = new ArrayList<>();
            while (rs.next()) {
                String id_in = rs.getString("id");
                LocalDate date_in = rs.getDate("date").toLocalDate();
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Integer arrived_in = rs.getInt("arrived");
                Integer tel = rs.getInt("tel");
                Integer new_in = rs.getInt("new");
                String time = rs.getString("time");
                Meeting_Patient_Object meeting_obj = new Meeting_Patient_Object(id_in,date_in,time,firstName,lastName,tel,arrived_in,new_in);
                meetings.add(meeting_obj);
                System.out.println("");
            }

            ArrayList<Meeting_Patient_Object> meetingsInDate = new ArrayList<>();
            for ( Meeting_Patient_Object meet : meetings)
            {
                Date m = java.sql.Date.valueOf(meet.getDate());
                if (java.sql.Date.valueOf(meet.getDate()).equals(date1))
                {
                    meetingsInDate.add(meet);
                }
            }
            return meetingsInDate ;
        }
    }

    public void removeMeeting(Integer id) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM meeting WHERE id = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

}
