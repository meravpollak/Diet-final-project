import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class TotalPayment {

    public DatePicker fromDate;
    public DatePicker UntilDate;
    @FXML
    public TableView table;
    @FXML
    public TableColumn<PaymentObject,String> id_col;
    @FXML
    public TableColumn<PaymentObject,String> firstName_col;
    @FXML
    public TableColumn<PaymentObject,String> lastName_col;
    @FXML
    public TableColumn<PaymentObject,String> date_col;
    @FXML
    public TableColumn<PaymentObject,String> cash_col;
    @FXML
    public TableColumn<PaymentObject,String> cheks_col ;
    @FXML
    public TableColumn<PaymentObject,String> pay_col;
    @FXML
    public TableColumn<PaymentObject,String> credit_col ;

    public TotalPaymentDataAccessor totalPaymentDataAccessor;

    public void showTotalPayment (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        LocalDate startDate = fromDate.getValue();
        LocalDate EndDate = UntilDate.getValue();
        totalPaymentDataAccessor = new TotalPaymentDataAccessor("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/diet", "root", "Dana1993!");
        ArrayList<PaymentObject> paymentObjects = totalPaymentDataAccessor.getTotalpayment(startDate,EndDate);
        bulidColumns();
        ObservableList<PaymentObject> data = FXCollections.observableArrayList();
        for( PaymentObject object: paymentObjects) {
            PaymentObject paymentObject = new PaymentObject(object.getId(),object.getDate(), object.getFirstName(), object.getLastName(), object.getPay(),object.getcredit(), object.getcash(), object.getchek());
            data.add(paymentObject);
            table.setItems(data);
        }
        totalPaymentDataAccessor.shutdown();
    }


    public void bulidColumns()
    {
        id_col.setCellValueFactory(new PropertyValueFactory<PaymentObject,String>("id"));
        firstName_col.setCellValueFactory(new PropertyValueFactory<PaymentObject,String>("firstName"));
        lastName_col.setCellValueFactory(new PropertyValueFactory<PaymentObject,String> ("lastName"));
        date_col.setCellValueFactory(new PropertyValueFactory<PaymentObject,String> ("date"));
        pay_col.setCellValueFactory(new PropertyValueFactory<PaymentObject,String> ("pay"));
        credit_col.setCellValueFactory(new PropertyValueFactory<PaymentObject,String> ("credit"));
        cash_col.setCellValueFactory(new PropertyValueFactory<PaymentObject,String> ("cash"));
        cheks_col.setCellValueFactory(new PropertyValueFactory<PaymentObject,String> ("chek"));
    }

    public void printTotalPayment (ActionEvent event) throws IOException
    {

    }
}
