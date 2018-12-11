import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PeopleUnderMem {


    private Stage stage;
    private Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public GridPane PatientDetails;
    public Patient current_patient;

    @FXML
    public TableView table;
    @FXML
    public TableColumn<PeopleUnderMemObject,String> id_col;
    @FXML
    public TableColumn<PeopleUnderMemObject,String> firstName_col;
    @FXML
    public TableColumn<PeopleUnderMemObject,String> lastName_col;
    @FXML
    public TableColumn<PeopleUnderMemObject,String> tel_col;
    @FXML
    public TableColumn<PeopleUnderMemObject,String> birthday_col ;
    @FXML
    public TableColumn<PeopleUnderMemObject,String> sex_col;

    public TextField week_textfield;
    public DietCenterData dietCenterData;

    public void lanch(Stage stageF, Scene sceneF, GridPane contentF, GridPane rightMenuF, Patient patient , ArrayList<DietCycleObject> listCycles) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu= rightMenuF;
        current_patient=patient;
    }

    public void showPeopleUnderMem (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        table.getItems().clear();
        dietCenterData = new DietCenterData("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/diet", "root", "Dana1993!");
        ArrayList<Patient> peopleUnderMem = dietCenterData.getPatientsUnderMem(week_textfield.getText());
        bulidColumns();
        ObservableList<PeopleUnderMemObject> data = FXCollections.observableArrayList();

        for( Patient object: peopleUnderMem) {
            PeopleUnderMemObject peopleUnderMemObject = new PeopleUnderMemObject(object.getId(), object.getFirstName(), object.getLastName(), object.getTel(), object.getBirthDay(), object.getSex());
            data.add(peopleUnderMemObject);
            table.setItems(data);
        }
        dietCenterData.shutdown();
    }


    public void bulidColumns()
    {
        id_col.setCellValueFactory(new PropertyValueFactory<PeopleUnderMemObject,String>("id"));
        firstName_col.setCellValueFactory(new PropertyValueFactory<PeopleUnderMemObject,String>("firstName"));
        lastName_col.setCellValueFactory(new PropertyValueFactory<PeopleUnderMemObject,String> ("lastName"));
        tel_col.setCellValueFactory(new PropertyValueFactory<PeopleUnderMemObject,String> ("tel"));
        birthday_col.setCellValueFactory(new PropertyValueFactory<PeopleUnderMemObject,String> ("birthday"));
        sex_col.setCellValueFactory(new PropertyValueFactory<PeopleUnderMemObject,String> ("sex"));
    }

    public void watchOnPatientDetails (ActionEvent event) throws IOException
    {
        System.out.print("     ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("PatientDetails.fxml").openStream());
        content.getChildren().clear();
        System.out.print("    ");
        content.getChildren().addAll(s);
        PatientDetails patientDetails = fxmlLoader.getController();
        patientDetails.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietCard (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("     ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("DietCard.fxml").openStream());
        content.getChildren().clear();
        System.out.print("    ");
        content.getChildren().addAll(s);
        DietCard dietCard = fxmlLoader.getController();
        dietCard.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietStatistics (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("     ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("StatisticDiet.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        System.out.print("    ");
        StatisicDiet statisicDiet = fxmlLoader.getController();
        statisicDiet.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void Invoices (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("    ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("Invoice.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        Invoice invoice = fxmlLoader.getController();
        System.out.print("    ");
        invoice.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void AddAppointment (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("  ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("MeetingCard.fxml").openStream());
        content.getChildren().clear();
        System.out.print("    ");
        content.getChildren().addAll(s);
        MeetingCard meetingCard = fxmlLoader.getController();
        meetingCard.lanch(stage, scene, content,rightMenu,current_patient);
        System.out.print(" ");
    }

}
