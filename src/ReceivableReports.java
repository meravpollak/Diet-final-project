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

public class ReceivableReports {
    public DatePicker fromDate;
    public DatePicker UntilDate;
    @FXML
    public TableView table;
    @FXML
    public TableColumn<RecivableObject,String> id_col;
    @FXML
    public TableColumn<RecivableObject,String> firstName_col;
    @FXML
    public TableColumn<RecivableObject,String> lastName_col;
    @FXML
    public TableColumn<RecivableObject,String> tel_col;
    @FXML
    public TableColumn<RecivableObject,String> date_col ;
    @FXML
    public TableColumn<RecivableObject,String> pay_col;

    public RecivableReportsCenter recivableReportsCenter;

    public void showReceivableReports (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        LocalDate startDate = fromDate.getValue();
        LocalDate EndDate = UntilDate.getValue();
        recivableReportsCenter = new RecivableReportsCenter("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/diet", "root", "Dana1993!");
        ArrayList<RecivableObject> missingObjects = recivableReportsCenter.getRecivableReports(startDate,EndDate );
        bulidColumns();
        ObservableList<RecivableObject> data = FXCollections.observableArrayList();

        for( RecivableObject object: missingObjects) {
            RecivableObject missingObject = new RecivableObject(object.getId(), object.getFirstName(), object.getLastName(), object.getTel(), object.getDate(), object.getSum());
            data.add(missingObject);
            table.setItems(data);
        }
        recivableReportsCenter.shutdown();
    }


    public void bulidColumns()
    {
        id_col.setCellValueFactory(new PropertyValueFactory<RecivableObject,String>("id"));
        firstName_col.setCellValueFactory(new PropertyValueFactory<RecivableObject,String>("firstName"));
        lastName_col.setCellValueFactory(new PropertyValueFactory<RecivableObject,String> ("lastName"));
        tel_col.setCellValueFactory(new PropertyValueFactory<RecivableObject,String> ("tel"));
        date_col.setCellValueFactory(new PropertyValueFactory<RecivableObject,String> ("date"));
        pay_col.setCellValueFactory(new PropertyValueFactory<RecivableObject,String> ("sum"));
    }

    public void printReceivableReports (ActionEvent event) throws IOException
    {

    }
}
