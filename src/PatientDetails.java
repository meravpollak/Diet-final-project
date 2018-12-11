import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class PatientDetails {

    private Stage stage;
    private Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public GridPane PatientDetails;
    public Patient current_patient;
    public TextField UserID;
    public TextField firstName;
    public TextField lastName;
    public TextField address;
    public TextField city;
    public TextField tel;
    public TextField email;
    public TextField birthDay;
    public TextField sex;
    public TextField familyStatus;
    public TextField age;
    public TextField job;
    public TextField dateOpenedCard;
    public TextField currentWeight;
    public TextField pastHighestWeight;
    public TextField height;
    public TextField weight;
    public TextField doctor;
    public TextField startweightCycle;
    public TextField marks;
    public PatientDataAccessor PatientdataAccessor;


    public void lanch(Stage stageF, Scene sceneF, GridPane contentF,GridPane rightMenuF, Patient patient_selected)
    {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu= rightMenuF;
        current_patient=patient_selected;
        UserID.setText(Integer.toString(current_patient.getId()));
        firstName.setText(current_patient.getFirstName());
        lastName.setText(current_patient.getLastName());
        address.setText(current_patient.getAddress());
        city.setText(current_patient.getCity());
        tel.setText(Integer.toString(current_patient.getTel()));
        email.setText(current_patient.getEmail());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        birthDay.setText(current_patient.getBirthDay().format(formatter));
        sex.setText(current_patient.getSex());
        familyStatus.setText(current_patient.getFamilyStatus());
        age.setText(Integer.toString(current_patient.getAge()));
        job.setText(current_patient.getJob());
        dateOpenedCard.setText(current_patient.getDateOpenCard().format(formatter));
        currentWeight.setText(Double.toString(current_patient.getCurrentWeight()));
        pastHighestWeight.setText(Double.toString(current_patient.getPastHighWeightProperty()));
        height.setText(Double.toString(current_patient.getHeightProperty()));
        weight.setText(Double.toString(current_patient.getWeightProperty()));
        doctor.setText(current_patient.getDoctor());
        startweightCycle.setText(Double.toString(current_patient.getStartWeightCycleProperty()));
        marks.setText(current_patient.getMarks());
    }

    public void submit (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (checkValid()) {


            //change the patient info
            current_patient.setId(Integer.parseInt(UserID.getText()));
            current_patient.setFirstName(firstName.getText());
            current_patient.setLastName(lastName.getText());
            current_patient.setAddress(address.getText());
            current_patient.setCity(city.getText());
            current_patient.setTel(Integer.parseInt(tel.getText()));
            current_patient.setEmail(email.getText());
            //current_patient.setBirthDay(birthDay.getText());
            current_patient.setSex(sex.getText());
            current_patient.setFamilyStatusProperty(familyStatus.getText());
            current_patient.setAge(Integer.parseInt(age.getText()));
            current_patient.setJob(job.getText());
            //current_patient.setDateOpenCard(dateOpenedCard.getText());
            current_patient.setCurrentWeight(Double.parseDouble(currentWeight.getText()));
            current_patient.setPastHighWeightProperty(Double.parseDouble(pastHighestWeight.getText()));
            current_patient.setHeightProperty(Double.parseDouble(height.getText()));
            current_patient.setWeightProperty(Double.parseDouble(weight.getText()));
            current_patient.setDoctor(doctor.getText());
            current_patient.setStartWeightCycleProperty(Double.parseDouble(startweightCycle.getText()));
            current_patient.setMarks(marks.getText());

            //set on the page the info
            UserID.setText(Integer.toString(current_patient.getId()));
            firstName.setText(current_patient.getFirstName());
            lastName.setText(current_patient.getLastName());
            address.setText(current_patient.getAddress());
            city.setText(current_patient.getCity());
            tel.setText(Integer.toString(current_patient.getTel()));
            email.setText(current_patient.getEmail());
            //birthDay.setText(current_patient.getBirthDay());
            sex.setText(current_patient.getSex());
            familyStatus.setText(current_patient.getFamilyStatus());
            age.setText(Integer.toString(current_patient.getAge()));
            job.setText(current_patient.getJob());
            //dateOpenedCard.setText(current_patient.getDateOpenCard());
            currentWeight.setText(Double.toString(current_patient.getCurrentWeight()));
            pastHighestWeight.setText(Double.toString(current_patient.getPastHighWeightProperty()));
            height.setText(Double.toString(current_patient.getHeightProperty()));
            weight.setText(Double.toString(current_patient.getWeightProperty()));
            doctor.setText(current_patient.getDoctor());
            startweightCycle.setText(Double.toString(current_patient.getStartWeightCycleProperty()));
            marks.setText(current_patient.getMarks());


            //update on db
            PatientdataAccessor = new PatientDataAccessor("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/diet", "root", "Dana1993!");
            PatientdataAccessor.updatePatientDetails(Integer.parseInt(UserID.getText()), firstName.getText(), lastName.getText(), address.getText(), city.getText(), Integer.parseInt(tel.getText()), email.getText(), current_patient.getBirthDay(), sex.getText(), familyStatus.getText(),Integer.parseInt(age.getText()), job.getText(), current_patient.getDateOpenCard(), Double.parseDouble(currentWeight.getText()), Double.parseDouble(pastHighestWeight.getText()), Double.parseDouble(height.getText()), Double.parseDouble(currentWeight.getText()), doctor.getText(), Integer.parseInt(startweightCycle.getText()), current_patient.getMarks());
            PatientdataAccessor.shutdown();

            //load the same page again with the updates
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
        System.out.print("");
        try{
            Integer.parseInt(UserID.getText());
        }catch(Exception e ){
            UserID.setText("bad input!");
            UserID.setStyle("-fx-text-fill: #DC143C");
            good=false;
        }

        try{
            Integer.parseInt(tel.getText());
        }catch(Exception e ){
            tel.setText("bad input!");
            tel.setStyle("-fx-text-fill: #DC143C");
            System.out.print("");
            good=false;
        }

        try{
            Integer.parseInt(age.getText());
        }catch(Exception e ){
            age.setText("bad input!");
            age.setStyle("-fx-text-fill: #DC143C");
            good=false;
        }

        try{
            System.out.print("");
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
            System.out.print("");
        }

        try{
            Double.parseDouble(currentWeight.getText());
        }catch(Exception e ){
            currentWeight.setText("bad input!");
            currentWeight.setStyle("-fx-text-fill: #DC143C");
            good=false;
            System.out.print(" ");
        }

        try{
            Double.parseDouble(pastHighestWeight.getText());
        }catch(Exception e ){
            pastHighestWeight.setText("bad input!");
            pastHighestWeight.setStyle("-fx-text-fill: #DC143C");
            good=false;
            System.out.print("  ");
        }

        try{
            Double.parseDouble(startweightCycle.getText());
        }catch(Exception e ){
            startweightCycle.setText("bad input!");
            startweightCycle.setStyle("-fx-text-fill: #DC143C");
            good=false;
        }

        return good;
    }

    public void cancel (ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("PatientDetails.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        System.out.print("");
        PatientDetails patientDetails = fxmlLoader.getController();
        patientDetails.lanch(stage, scene, content,rightMenu, current_patient);
    }

    public void watchOnPatientDetails (ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("PatientDetails.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        PatientDetails patientDetails = fxmlLoader.getController();
        patientDetails.lanch(stage, scene, content,rightMenu, current_patient);
    }
    public void DietCard (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("DietCard.fxml").openStream());
        content.getChildren().clear();
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
        statisicDiet.lanch(stage, scene, content,rightMenu, current_patient);
    }
    public void Invoices (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("Invoice.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        Invoice invoice = fxmlLoader.getController();
        invoice.lanch(stage, scene, content,rightMenu, current_patient);
    }
    public void AddAppointment (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("MeetingCard.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        MeetingCard meetingCard = fxmlLoader.getController();
        meetingCard.lanch(stage, scene, content,rightMenu, current_patient);
    }


}
