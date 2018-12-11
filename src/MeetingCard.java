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
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MeetingCard {

    private Stage stage;
    private Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public GridPane PatientDetails;
    public Patient current_patient;
    public ListView hour;
    public ListView minutes;
    @FXML
    public ComboBox<String> UserID1;
    public PatientDataAccessor PatientdataAccessor;
    public MeetingCenterData meetingCenterData;
    public DatePicker datePicker;
    @FXML
    public TableColumn<Meeting_Patient_Object,String> hour_col;
    public TableColumn<Meeting_Patient_Object,String> firstName_col;
    public TableColumn<Meeting_Patient_Object,String> lastName_col;
    public TableColumn<Meeting_Patient_Object,String> tel_col;

    public TableView table;
    public LocalDate selectedDate;


    public void lanch(Stage stageF, Scene sceneF, GridPane contentF,GridPane rightMenuF, Patient patient) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu= rightMenuF;
        current_patient = patient;
        ObservableList<String> items_hour = FXCollections.observableArrayList ("7","8" ,"9","10", "11", "12", "13", "14","15","16","17","18","19","20");
        hour.setItems(items_hour);
        ObservableList<String> items_minutes = FXCollections.observableArrayList ("00","05" ,"10","15","20", "25", "30", "35","40","45","50","55");
        minutes.setItems(items_minutes);
        datePicker.setValue(LocalDate.now());
        selectedDate=datePicker.getValue();

        showMeetings();

    }

    public void addNewMeeting(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        System.out.println(" ");
       // String userId =  UserID1.getSelectionModel().getSelectedItem().toString();
       // String time = (String)hour.getSelectionModel().getSelectedItems().get(0)+":"+(String)minutes.getSelectionModel().getSelectedItems().get(0);
        PatientdataAccessor = new PatientDataAccessor("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        int newPatient=0;
            Patient p = PatientdataAccessor.getPatient(current_patient.getId());
            if (p.getDateOpenCard().getMonth()== LocalDate.now().getMonth())
            {
                newPatient = 1;
            }
            LocalTime localtime = LocalTime.of(Integer.parseInt((String)hour.getSelectionModel().getSelectedItems().get(0)),Integer.parseInt((String)minutes.getSelectionModel().getSelectedItems().get(0)));

            meetingCenterData = new MeetingCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
            meetingCenterData.addNewMeeting( String.valueOf(p.getId()),p.getFirstName(),p.getLastName(),p.getTel(),localtime,datePicker.getValue(),0,newPatient);
            table.getItems().clear();
            showMeetings();
            System.out.println(" ");
        }

    public void showMeetings() throws SQLException, ClassNotFoundException {
        System.out.println("  ");
        meetingCenterData = new MeetingCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        ArrayList<Meeting_Patient_Object> meeting_list = meetingCenterData.getMeetingOfSelectedDate(java.sql.Date.valueOf(selectedDate));
        bulidColumns();
        ObservableList<Meeting_Patient_Object> data = FXCollections.observableArrayList();
        for( Meeting_Patient_Object cycle: meeting_list) {
            Meeting_Patient_Object meet1 = new Meeting_Patient_Object(cycle.getId(),cycle.getDate(),cycle.getTime(),cycle.getFirstName(),cycle.getLastName(),cycle.getTel(),cycle.getArrived(),cycle.getNewP());
            data.add(meet1);
            hour_col.setSortType(TableColumn.SortType.ASCENDING);
            table.setItems(data);
            table.getSortOrder().add(hour_col);
            System.out.println("");
        }

    }

    public void bulidColumns()
    {
        hour_col.setCellValueFactory(new PropertyValueFactory<Meeting_Patient_Object,String>("time"));
        firstName_col.setCellValueFactory(new PropertyValueFactory<Meeting_Patient_Object,String> ("firstName"));
        lastName_col.setCellValueFactory(new PropertyValueFactory<Meeting_Patient_Object,String> ("lastName"));
        tel_col.setCellValueFactory(new PropertyValueFactory<Meeting_Patient_Object,String> ("tel"));
    }

    public void watchOnPatientDetails (ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("PatientDetails.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        PatientDetails patientDetails = fxmlLoader.getController();
        System.out.println("");
        patientDetails.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietCard (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("DietCard.fxml").openStream());
        content.getChildren().clear();
        System.out.println("");
        content.getChildren().addAll(s);
        DietCard dietCard = fxmlLoader.getController();
        dietCard.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietStatistics (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("StatisticDiet.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        StatisicDiet statisicDiet = fxmlLoader.getController();
        System.out.println("");
        statisicDiet.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void Invoices (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("Invoice.fxml").openStream());
        content.getChildren().clear();
        System.out.println("");
        content.getChildren().addAll(s);
        Invoice invoice = fxmlLoader.getController();
        invoice.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void AddAppointment (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("MeetingCard.fxml").openStream());
        content.getChildren().clear();
        System.out.println("");
        content.getChildren().addAll(s);
        MeetingCard meetingCard = fxmlLoader.getController();
        meetingCard.lanch(stage, scene, content,rightMenu,current_patient);
    }

    
}
