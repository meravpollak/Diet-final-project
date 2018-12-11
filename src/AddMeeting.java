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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Created by MERAV on 14/05/2018.
 */
public class AddMeeting {

    private Stage stage;
    private Scene scene;
    public GridPane rightMenu;
    public GridPane content;
    public ListView hour;
    public ListView minutes;
    @FXML
    public ComboBox<String> UserID1;
    public PatientDataAccessor PatientdataAccessor;
    public MeetingCenterData meetingCenterData;
    public DatePicker datePicker;
    public Patient current_patient;
    @FXML
    public TableColumn<Meeting_Patient_Object,String> hour_col;
    public TableColumn<Meeting_Patient_Object,String> firstName_col;
    public TableColumn<Meeting_Patient_Object,String> lastName_col;
    public TableColumn<Meeting_Patient_Object,String> tel_col;
    public LocalDate selectedDate;

    public TableView table;


    public void lanch(Stage stageF, Scene sceneF, GridPane contentF, GridPane rightMenuF) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu = rightMenuF;
        ObservableList<String> items_hour = FXCollections.observableArrayList ("7","8" ,"9","10", "11", "12", "13", "14","15","16","17","18","19","20");
        hour.setItems(items_hour);
        ObservableList<String> items_minutes = FXCollections.observableArrayList ("00","05" ,"10","15","20", "25", "30", "35","40","45","50","55");
        minutes.setItems(items_minutes);
        //complete Combo Box
        new AutoComboBox<String>(getComboBoxID());
        datePicker.setValue(LocalDate.now());
        showMeetingOftheDay();
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedDate = newValue;
            try {
                table.getItems().clear();
                showMeetingOftheDay();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

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
            UserID1.getItems().addAll(Ids_list);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            PatientdataAccessor.shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return UserID1;
    }

    public void bulidColumns()
    {
        hour_col.setCellValueFactory(new PropertyValueFactory<Meeting_Patient_Object,String>("time"));
        firstName_col.setCellValueFactory(new PropertyValueFactory<Meeting_Patient_Object,String> ("firstName"));
        lastName_col.setCellValueFactory(new PropertyValueFactory<Meeting_Patient_Object,String> ("lastName"));
        tel_col.setCellValueFactory(new PropertyValueFactory<Meeting_Patient_Object,String> ("tel"));
    }

    public void watchcalendar (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("     ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("CalendarWindow.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        CalendarWindow calendarWindow = fxmlLoader.getController();
        calendarWindow.lanch(stage, scene, content,rightMenu);
    }
    public void Addmeeting (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("     ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("AddMeeting.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        AddMeeting addMeeting = fxmlLoader.getController();
        addMeeting.lanch(stage, scene, content,rightMenu);
    }

    public void RemoveMeeting(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        System.out.println("   ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("RemoveMeeting.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        RemoveMeeting removeMeeting = fxmlLoader.getController();
        removeMeeting.lanch(stage, scene, content,rightMenu, current_patient);

    }

    public void addNewMeeting(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String userId =  UserID1.getSelectionModel().getSelectedItem().toString();
       // String time = (String)hour.getSelectionModel().getSelectedItems().get(0)+":"+(String)minutes.getSelectionModel().getSelectedItems().get(0);
        PatientdataAccessor = new PatientDataAccessor("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        int newPatient=0;

        if ( PatientdataAccessor.checkIfIdExists(Integer.parseInt(userId.trim()))==1) {
            Patient p = PatientdataAccessor.getPatient(Integer.parseInt(userId.trim()));
            if (p.getDateOpenCard().getMonth()== LocalDate.now().getMonth())
            {
                newPatient = 1;
            }
            LocalTime localtime = LocalTime.of(Integer.parseInt((String)hour.getSelectionModel().getSelectedItems().get(0)),Integer.parseInt((String)minutes.getSelectionModel().getSelectedItems().get(0)));

            meetingCenterData = new MeetingCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
            meetingCenterData.addNewMeeting( String.valueOf(p.getId()),p.getFirstName(),p.getLastName(),p.getTel(),localtime,datePicker.getValue(),0,newPatient);

            table.getItems().clear();
            showMeetingOftheDay();
        }
    }

    public void showMeetingOftheDay() throws SQLException, ClassNotFoundException {
        table.getItems().clear();
        meetingCenterData = new MeetingCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        ArrayList<Meeting_Patient_Object> meeting_list = meetingCenterData.getMeetingOfSelectedDate(java.sql.Date.valueOf(datePicker.getValue()));
        bulidColumns();
        ObservableList<Meeting_Patient_Object> data = FXCollections.observableArrayList();
        for( Meeting_Patient_Object cycle: meeting_list) {
           Meeting_Patient_Object meet1 = new Meeting_Patient_Object(cycle.getId(),cycle.getDate(),cycle.getTime(),cycle.getFirstName(),cycle.getLastName(),cycle.getTel(),cycle.getArrived(),cycle.getNewP());
           data.add(meet1);
            hour_col.setSortType(TableColumn.SortType.ASCENDING);
            table.setItems(data);
            table.getSortOrder().add(hour_col);
        }
    }
}
