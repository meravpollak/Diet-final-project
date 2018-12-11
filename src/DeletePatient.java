import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeletePatient {
    @FXML
    public PatientDataAccessor PatientdataAccessor;
    @FXML
    public ComboBox<String> UserID;
    @FXML
    public ComboBox<String> NameSearch;
    @FXML
    public TableView table;
    @FXML
    public TableColumn<CardDetails,String> id_col;
    @FXML
    public TableColumn<CardDetails,String> firstName_col;
    @FXML
    public TableColumn<CardDetails,String> lastName_col;
    @FXML
    public TableColumn<CardDetails,String> tel_col;
    @FXML
    public TableColumn<CardDetails,String> email_col;
    @FXML
    public TableColumn<CardDetails,String> address_col;
    @FXML
    public TableColumn<CardDetails,String> city_col;

    private Stage stage;
    private Scene scene;
    public GridPane content;
    public GridPane rightMenu;

    public void lanch(Stage stageF, Scene sceneF, GridPane contentF, GridPane rightMenuF)
    {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu = rightMenuF;

        //complete Combo Box
        new AutoComboBox<String>(getComboBoxID());
        new AutoComboBox<String>(getComboBoxName());

    }


    public ComboBox getComboBoxID()
    {
        try {
            PatientdataAccessor = new PatientDataAccessor("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ArrayList<String> Ids_list = PatientdataAccessor.getPatientsID();
            UserID.getItems().addAll(Ids_list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PatientdataAccessor.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return UserID;
    }

    public ComboBox getComboBoxName()
    {
        try {
            PatientdataAccessor = new PatientDataAccessor("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            ArrayList<String> Names_list = PatientdataAccessor.getAllNames();
            NameSearch.getItems().addAll(Names_list);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PatientdataAccessor.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return NameSearch;
    }

    public void submit (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        if (table.getSelectionModel().getSelectedItem() != null) {
            CardDetails selectedDetails = (CardDetails) table.getSelectionModel().getSelectedItem();
            PatientdataAccessor = new PatientDataAccessor("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
            Patient patient_selected = PatientdataAccessor.getPatient(Integer.parseInt(selectedDetails.getId()));
            //remove the patient from db
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.OK, ButtonType.CANCEL);
           // alert.setContentText("Are you sure you want to delete this patient?");
            alert.setTitle("מחיקת מטופל");
            alert.setHeaderText("האם אתה בטוח שברצונך למחוק את המטופל? ");

            alert.initModality(Modality.APPLICATION_MODAL);
            alert.initOwner(stage);
            alert.showAndWait();
            if (alert.getResult()==ButtonType.OK)
            {
                PatientdataAccessor.removePatient(patient_selected.getId());
                PatientdataAccessor.shutdown();
                table.getItems().clear();
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
                alert1.setTitle("מחיקת מטופל");
                alert1.setHeaderText("המטופל נמחק בהצלחה!   ");
                alert1.initModality(Modality.APPLICATION_MODAL);
                alert1.initOwner(stage);
                alert1.showAndWait();
            }
            else {
                alert.close();
            }

        }


    }

    public void search_on_id (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String userId =  UserID.getSelectionModel().getSelectedItem().toString();
        if (!userId.equals(""))
        {
            PatientdataAccessor = new PatientDataAccessor("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");

            if ( PatientdataAccessor.checkIfIdExists(Integer.parseInt(userId.trim()))==1)
            {
                Patient p = PatientdataAccessor.getPatient(Integer.parseInt(userId.trim()));
                CardDetails cardDetails = new CardDetails(p.getId().toString(), p.getFirstName(), p.getLastName(), p.getTel().toString(), p.getEmail(),p.getAddress(), p.getCity());
                ObservableList<CardDetails> data = FXCollections.observableArrayList(cardDetails);
                bulidColumns();
                table.setItems(data);
                System.out.print("");
            }
            else {
                table.getItems().clear();
            }

            PatientdataAccessor.shutdown();
        }

    }

    public void search_on_name (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        String name_patient =  NameSearch.getSelectionModel().getSelectedItem().toString();
        if (!name_patient.equals(""))
        {
            PatientdataAccessor = new PatientDataAccessor("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
            ArrayList<Patient> listOfPatients = PatientdataAccessor.getPatientList();
            bulidColumns();
            ObservableList<CardDetails> data = FXCollections.observableArrayList();
            for( Patient patient: listOfPatients) {
                System.out.println(patient.getFirstName());
                if (patient.getFirstName().equals(name_patient))
                {
                    CardDetails cardDetails = new CardDetails(patient.getId().toString(), patient.getFirstName(), patient.getLastName(), patient.getTel().toString(), patient.getEmail(),patient.getAddress(), patient.getCity());
                    data.add(cardDetails);
                    table.setItems(data);
                    System.out.print("");
                }
            }
            PatientdataAccessor.shutdown();
            System.out.print("");
        }

    }

    public void bulidColumns()
    {
        id_col.setCellValueFactory(new PropertyValueFactory<CardDetails,String>("id"));
        firstName_col.setCellValueFactory(new PropertyValueFactory<CardDetails,String> ("firstName"));
        lastName_col.setCellValueFactory(new PropertyValueFactory<CardDetails,String> ("lastName"));
        tel_col.setCellValueFactory(new PropertyValueFactory<CardDetails,String> ("tel"));
        email_col.setCellValueFactory(new PropertyValueFactory<CardDetails,String> ("email"));
        address_col.setCellValueFactory(new PropertyValueFactory<CardDetails,String> ("address"));
        city_col.setCellValueFactory(new PropertyValueFactory<CardDetails,String> ("city"));
    }

}