import com.sun.javafx.collections.MappingChange;
import javafx.application.Platform;
import javafx.beans.binding.ObjectExpression;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


import javax.security.auth.callback.Callback;
import javax.swing.text.AbstractDocument;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.util.*;

public class ChoosePatient {

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

    public void lanchChoosePatient(Stage stageF, Scene sceneF, GridPane contentF, GridPane rightMenuF)
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
            PatientdataAccessor.shutdown();

            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent s = fxmlLoader.load(getClass().getResource("PatientDetails.fxml").openStream());
            content.getChildren().clear();
            content.getChildren().addAll(s);
            PatientDetails patientDetails = fxmlLoader.getController();
            patientDetails.lanch(stage, scene, content,rightMenu,patient_selected);
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
                }
            }
            PatientdataAccessor.shutdown();
        }

    }

    public void bulidColumns()
    {
        id_col.setCellValueFactory(new PropertyValueFactory<CardDetails,String> ("id"));
        firstName_col.setCellValueFactory(new PropertyValueFactory<CardDetails,String> ("firstName"));
        lastName_col.setCellValueFactory(new PropertyValueFactory<CardDetails,String> ("lastName"));
        tel_col.setCellValueFactory(new PropertyValueFactory<CardDetails,String> ("tel"));
        email_col.setCellValueFactory(new PropertyValueFactory<CardDetails,String> ("email"));
        address_col.setCellValueFactory(new PropertyValueFactory<CardDetails,String> ("address"));
        city_col.setCellValueFactory(new PropertyValueFactory<CardDetails,String> ("city"));
        System.out.print("");
    }

}
