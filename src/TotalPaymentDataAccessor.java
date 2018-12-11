import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TotalPaymentDataAccessor { private Connection connection ;

    public TotalPaymentDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
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
    public ArrayList<PaymentObject> getTotalpayment(LocalDate startDate, LocalDate endDate) throws SQLException {
        Date start = java.sql.Date.valueOf(startDate);
        Date end = java.sql.Date.valueOf(endDate);
        ArrayList<PaymentObject> paymentObjects = new ArrayList<>();
       // final String queryCheck = "SELECT * from totalpayment WHERE date BETWEEN "+start+" AND "+end;
        final String queryCheck = "SELECT * from totalpayment WHERE date >= "+start;
        final PreparedStatement ps = connection.prepareStatement(queryCheck);
        final ResultSet rs = ps.executeQuery();
        while (rs.next()) {
                Integer id_in = rs.getInt("id");
                LocalDate date = rs.getDate("date").toLocalDate();
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                Integer pay = rs.getInt("pay");
                Integer credit = rs.getInt("credit");
                Integer cash = rs.getInt("cash");
                Integer chek = rs.getInt("chek");

                PaymentObject paymentObject = new PaymentObject(id_in,date,firstName,lastName,pay,credit,cash,chek);
                paymentObjects.add(paymentObject);
            }
            return paymentObjects ;
    }

    public void updatePayment(Integer id, Date date, String firstName, String lastName, Integer pay, Integer credit, Integer cash, Integer chek) throws SQLException {
        Statement stmnt = connection.createStatement();
        stmnt.executeUpdate("INSERT INTO totalpayment (id, date, firstName, lastName, pay, credit, cash, chek) " +
                "VALUES('" +id+ "','" + date + "','" + firstName + "','" + lastName + "','" + pay + "','" + credit + "','" + cash + "','" + chek +"')");
    }

}
