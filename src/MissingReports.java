
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.text.Position;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public class MissingReports {
   public DatePicker fromDate;
    public DatePicker UntilDate;

    @FXML
    public TableView table;
    @FXML
    public TableColumn<MissingObject,String> id_col;
    @FXML
    public TableColumn<MissingObject,String> firstName_col;
    @FXML
    public TableColumn<MissingObject,String> lastName_col;
    @FXML
    public TableColumn<MissingObject,String> tel_col;
    @FXML
    public TableColumn<MissingObject,String> date_col ;
    @FXML
    public TableColumn<MissingObject,String> hourDate_col;
    @FXML
    public TableColumn<MissingObject,String> weekCycle_col;

    public MissingReportAccessor missingReportAccessor;

public void showmissingReports (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
    LocalDate startDate = fromDate.getValue();
    LocalDate EndDate = UntilDate.getValue();
    missingReportAccessor = new MissingReportAccessor("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/diet", "root", "Dana1993!");
    ArrayList <MissingObject> missingObjects = missingReportAccessor.getMissingPatients(startDate,EndDate);
    bulidColumns();
    ObservableList<MissingObject> data = FXCollections.observableArrayList();

    for( MissingObject object: missingObjects) {
        MissingObject missingObject = new MissingObject(object.getId(), object.getFirstName(), object.getLastName(), object.getTel(), object.getDate(), object.getTime(), object.getweek());
        data.add(missingObject);
        table.setItems(data);
    }
    missingReportAccessor.shutdown();
}

    public void bulidColumns()
    {
        id_col.setCellValueFactory(new PropertyValueFactory<MissingObject,String>("id"));
        firstName_col.setCellValueFactory(new PropertyValueFactory<MissingObject,String>("firstName"));
        lastName_col.setCellValueFactory(new PropertyValueFactory<MissingObject,String> ("lastName"));
        tel_col.setCellValueFactory(new PropertyValueFactory<MissingObject,String> ("tel"));
        date_col.setCellValueFactory(new PropertyValueFactory<MissingObject,String> ("date"));
        hourDate_col.setCellValueFactory(new PropertyValueFactory<MissingObject,String> ("time"));
        weekCycle_col.setCellValueFactory(new PropertyValueFactory<MissingObject,String> ("week"));
    }

public void printmissingReports (ActionEvent event) throws IOException
{

}

    //endregion

}
