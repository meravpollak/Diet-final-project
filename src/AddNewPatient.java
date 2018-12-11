import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;

public class AddNewPatient {
    public Disease disease;
    public Patient current_patient;
    public TextField UserID;
    public TextField firstName;
    public TextField lastName;
    public TextField address;
    public ListView city;
    public TextField tel;
    public TextField email;
    public DatePicker birthDay;
    public ListView sex;
    public ListView familyStatus;
    public TextField age;
    public TextField job;
    public DatePicker dateOpenedCard;
    public TextField currentWeight;
    public TextField pastHighestWeight;
    public TextField height;
    public TextField weight;
    public TextField doctor;
    public ListView diseases;
    public TextField startweightCycle;
    public TextField marks;
    public PatientDataAccessor PatientdataAccessor;
    private Stage stage;
    private Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public String sex_string;
    public String familyStatus_string;
    public Cities cities;
    public  ArrayList<String> arrayList_Cities;
    public ArrayList<String> arrayList_Diseases;

    public void lanch(Stage stageF,Scene sceneF, GridPane contentF,GridPane rightMenuF) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu = rightMenuF;
        diseases.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        ObservableList<String> items_sex = FXCollections.observableArrayList ("זכר", "נקבה");
        sex.setItems(items_sex);
        ObservableList<String> items_stsus = FXCollections.observableArrayList ("רווק/ה","נשוי/ה" ,"גרוש/ה","אלמנ/ה");
        familyStatus.setItems(items_stsus);
        cities = new Cities("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        arrayList_Cities = cities.getCities();
        ObservableList<String> items_cities = FXCollections.observableArrayList(arrayList_Cities);
        city.setItems(items_cities);
        disease = new Disease("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        arrayList_Diseases = disease.getDisease();
        ObservableList<String> items_diseases = FXCollections.observableArrayList(arrayList_Diseases);
        diseases.setItems(items_diseases);

    }

    public void submit (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        if (checkValid())
        {
            //change the patient info
            current_patient = new Patient();
            current_patient.setId(Integer.parseInt(UserID.getText()));
            current_patient.setFirstName(firstName.getText());
            current_patient.setLastName(lastName.getText());
            current_patient.setAddress(address.getText());
            current_patient.setCity((String)city.getSelectionModel().getSelectedItems().get(0));
            current_patient.setTel(Integer.parseInt(tel.getText()));
            current_patient.setEmail(email.getText());

            LocalDate localDate_birthDay = birthDay.getValue(); // from datepicker

            //update dateOpenedCard Birthday
            current_patient.setBirthDay(localDate_birthDay);

            //set sex
            int sexOption = sex.getSelectionModel().getSelectedIndex();
            sex_string="";
            switch (sexOption)
            {
                case 0: sex_string="זכר";
                    break;
                case 1: sex_string="נקבה";
                    break;
            }
            current_patient.setSex(sex_string);

            //set status
            int StatusOption = familyStatus.getSelectionModel().getSelectedIndex();
            familyStatus_string="";
            switch (StatusOption)
            {
                case 0: familyStatus_string="רווק/ה";
                    break;
                case 1: familyStatus_string="נשוי/ה";
                    break;
                case 2: familyStatus_string="גרוש/ה";
                    break;
                case 3: familyStatus_string="אלמנ/ה";
                    break;
            }

            current_patient.setFamilyStatusProperty(familyStatus_string);
            current_patient.setAge(Integer.parseInt(age.getText()));
            current_patient.setJob(job.getText());

            //update dateOpenedCard
            LocalDate localDate_dateOpenedCard = dateOpenedCard.getValue(); // from datepicker
            current_patient.setDateOpenCard(localDate_dateOpenedCard);

            current_patient.setCurrentWeight(Double.parseDouble(currentWeight.getText()));
            current_patient.setPastHighWeightProperty(Double.parseDouble(pastHighestWeight.getText()));
            current_patient.setHeightProperty(Double.parseDouble(height.getText()));
            current_patient.setWeightProperty(Double.parseDouble(weight.getText()));
            current_patient.setDoctor(doctor.getText());
            current_patient.setStartWeightCycleProperty(Double.parseDouble(startweightCycle.getText()));
            current_patient.setMarks(marks.getText());

            //update on db
            PatientdataAccessor = new PatientDataAccessor("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/diet", "root", "Dana1993!");
            PatientdataAccessor.addPatient(current_patient.getId(), current_patient.getFirstName(), current_patient.getLastName(), current_patient.getAddress(), current_patient.getCity(), current_patient.getTel(), current_patient.getEmail(), current_patient.getBirthDay(), sex_string, familyStatus_string, current_patient.getAge(), current_patient.getJob(), current_patient.getDateOpenCard(), current_patient.getCurrentWeight(), current_patient.getPastHighWeightProperty(), current_patient.getHeightProperty(), current_patient.getCurrentWeight(), current_patient.getDoctor(), current_patient.getStartWeightCycleProperty(), current_patient.getMarks());
            PatientdataAccessor.shutdown();

            Alert alert1 = new Alert(Alert.AlertType.INFORMATION, "", ButtonType.OK);
            alert1.setTitle("הוספת מטופל");
            alert1.setHeaderText("המטופל הוסף בהצלחה!   ");
            alert1.initModality(Modality.APPLICATION_MODAL);
            alert1.initOwner(stage);
            alert1.showAndWait();

            //load the cardDetails
            FXMLLoader fxmlLoader = new FXMLLoader();
            Parent s = fxmlLoader.load(getClass().getResource("PatientDetails.fxml").openStream());
            content.getChildren().clear();
            System.out.print("");
            content.getChildren().addAll(s);
            PatientDetails patientDetails = fxmlLoader.getController();
            patientDetails.lanch(stage, scene, content, rightMenu, current_patient);
        }
    }


    public boolean checkValid()
    {
        boolean good=true;
           if (birthDay.getValue()==null || dateOpenedCard.getValue()==null || sex.getSelectionModel().isEmpty() || familyStatus.getSelectionModel().getSelectedItems().isEmpty())
               good=false;
        try{
            System.out.print("  ");
            Integer.parseInt(UserID.getText());
        }catch(Exception e ){
            UserID.setText("bad input!");
            UserID.setStyle("-fx-text-fill: #DC143C");
            good=false;
        }

        try{
            System.out.print("");
            Integer.parseInt(tel.getText());
        }catch(Exception e ){
            tel.setText("bad input!");
            tel.setStyle("-fx-text-fill: #DC143C");

            good=false;
        }

        try{
            Integer.parseInt(age.getText());
            System.out.print("");
        }catch(Exception e ){
            age.setText("bad input!");
            age.setStyle("-fx-text-fill: #DC143C");
            good=false;
        }

        try{
            System.out.print(" ");
            Double.parseDouble(height.getText());
        }catch(Exception e ){
            height.setText("bad input!");
            height.setStyle("-fx-text-fill: #DC143C");
            good=false;
        }

        try{

            Double.parseDouble(weight.getText());
        }catch(Exception e ){
            weight.setText("bad input!");
            weight.setStyle("-fx-text-fill: #DC143C");
            good=false;
            System.out.print("     ");
        }

        try{
            Double.parseDouble(currentWeight.getText());
        }catch(Exception e ){
            currentWeight.setText("bad input!");
            currentWeight.setStyle("-fx-text-fill: #DC143C");
            good=false;
            System.out.print("      ");
        }

        try{
            Double.parseDouble(pastHighestWeight.getText());
        }catch(Exception e ){
            pastHighestWeight.setText("bad input!");
            pastHighestWeight.setStyle("-fx-text-fill: #DC143C");
            good=false;
            System.out.print("   ");
        }

        try{
            Double.parseDouble(startweightCycle.getText());
        }catch(Exception e ){
            startweightCycle.setText("bad input!");
            startweightCycle.setStyle("-fx-text-fill: #DC143C");
            good=false;
            System.out.print("-");
        }

        return good;
    }


    public void cancel (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        //load the same page again with the updates
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("AddNewPatient.fxml").openStream());
        content.getChildren().clear();
        System.out.print("");
        content.getChildren().addAll(s);
        AddNewPatient addNewPatient = fxmlLoader.getController();
        addNewPatient.lanch(stage, scene, content,rightMenu);
    }
}
