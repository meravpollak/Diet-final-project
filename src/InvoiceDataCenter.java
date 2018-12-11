import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class InvoiceDataCenter {

    private Connection connection ;

    public InvoiceDataCenter(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
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
    public ArrayList<InvoiceObject> getInvoicesOfPatient(String id) throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from invoice WHERE id = "+id);
        ){
            ArrayList<InvoiceObject> invoiceList = new ArrayList<>();
            while (rs.next()) {

                Integer id_in = rs.getInt("id");
                Integer code = rs.getInt("code");
                LocalDate date_in = rs.getDate("date").toLocalDate();
                Integer sum = rs.getInt("sum");
                String howPay = rs.getString("howPay");
                String moreDetails = rs.getString("moreDetails");
                String marks_invoice = rs.getString("marks");
                InvoiceObject invoice = new InvoiceObject(id_in,code,date_in,sum,howPay,moreDetails,marks_invoice);
                invoiceList.add(invoice);
            }
            return invoiceList ;
        }
    }

    public void addNewInvoice(Integer id, Integer code, Date date, Integer pay, String howPay, String moreDetails, String marks) throws SQLException {
        Statement stmnt = connection.createStatement();
        stmnt.executeUpdate("INSERT INTO invoice (id, code, date, sum, howPay, moreDetails, marks) " +
                "VALUES('" +id+ "','" + code + "','" + date + "','" + pay + "','" + howPay + "','" + moreDetails + "','" + marks +"')");
    }

    //get all the patients
    public ArrayList<InvoiceObject> getAllInvoices() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from invoice");
        ){
            ArrayList<InvoiceObject> invoiceList = new ArrayList<>();
            while (rs.next()) {

                Integer id_in = rs.getInt("id");
                Integer code = rs.getInt("code");
                LocalDate date_in = rs.getDate("date").toLocalDate();
                Integer sum = rs.getInt("sum");
                String howPay = rs.getString("howPay");
                String moreDetails = rs.getString("moreDetails");
                String marks_invoice = rs.getString("marks");
                InvoiceObject invoice = new InvoiceObject(id_in,code,date_in,sum,howPay,moreDetails,marks_invoice);
                invoiceList.add(invoice);
                System.out.print("");
            }
            return invoiceList ;
        }
    }

}
