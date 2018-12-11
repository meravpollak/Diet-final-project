import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class NeedToPayCenter {

    private Connection connection ;

    public NeedToPayCenter(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    //close connection
    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public ArrayList<NeedToPayObject> getAllInvoiceNeedToPay() throws SQLException
        {
            Statement stmnt = connection.createStatement();
            ResultSet rs = stmnt.executeQuery("select * from needtopayinvoice");
            ArrayList<NeedToPayObject> invoiceList = new ArrayList<>();
            while (rs.next())
            {
                Integer id_in = rs.getInt("id");
                Integer code = rs.getInt("code");
                LocalDate date_in = rs.getDate("date").toLocalDate();
                Integer sum = rs.getInt("sum");
                Integer tel = rs.getInt("tel");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                NeedToPayObject missingObject = new NeedToPayObject(id_in,code,date_in,sum,tel,firstName, lastName);
                invoiceList.add(missingObject);
            }
            return invoiceList;
        }

        public void addNewNeedToPay (Integer id, Integer code, Date date, Integer pay, Integer tel, String firstname, String lastname) throws SQLException {
            Statement stmnt = connection.createStatement();
            stmnt.executeUpdate("INSERT INTO needtopayinvoice (id, code, date, sum, tel , firstName, lastName) " +
                    "VALUES('" + id + "','" + code + "','" + date + "','" + pay + "','" + tel + "','" + firstname+ "','" + lastname +"')");
        }

}
