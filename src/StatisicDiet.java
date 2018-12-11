import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StatisicDiet {

    private Stage stage;
    private Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public GridPane PatientDetails;
    public Patient current_patient;
    @FXML
    public LineChart lineChart;
    @FXML
    public DietCenterData dietCenterData;
    public TextField textfield_pickGraph;
    public Integer Current_week;
    public Integer Current_week_basic;
    public ArrayList<DietCycleObject> listOfCycles;

    public void lanch(Stage stageF, Scene sceneF, GridPane contentF,GridPane rightMenuF, Patient patient) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu= rightMenuF;
        current_patient = patient;
        Current_week=0;
        Current_week_basic=0;
    }

    public void showBMI(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("Bmi.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        Bmi bmi_f = fxmlLoader.getController();
        bmi_f.lanch(stage, scene, content,rightMenu, current_patient, listOfCycles);
        System.out.println("                                    ");
    }

    public void showLeftPeople(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("leftPeople.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        LeftPeople leftPeople = fxmlLoader.getController();
        leftPeople.lanch(stage, scene, content,rightMenu,current_patient, listOfCycles);
        System.out.println("                                    ");

    }

     public void selectBestDiet() throws SQLException, ClassNotFoundException {
         dietCenterData = new DietCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
         listOfCycles = dietCenterData.getCycelsOfPatient(Integer.toString(current_patient.getId()));
         Collections.sort(listOfCycles, new Comparator<DietCycleObject>() {
             @Override
             public int compare(DietCycleObject o1, DietCycleObject o2) {
                 return o1.getDiff().compareTo(o2.getDiff());
             }
         });
         ArrayList<DietCycleObject> listOfCyclesRemoved = new ArrayList<>();
         for( DietCycleObject cycle: listOfCycles)
         {
             if (cycle.getDiet()==0.0)
                 listOfCyclesRemoved.add(cycle);
         }

         listOfCycles.removeAll(listOfCyclesRemoved);
         System.out.println("                                      ");
     }
    public void showBestDiet(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {

        selectBestDiet();
        //load the page
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("SuggestionDiet.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        SuggestionDiet suggestionDiet = fxmlLoader.getController();
        suggestionDiet.lanch(stage, scene, content,rightMenu,current_patient , listOfCycles);
        System.out.println("                                    ");
    }

    public void showBestWeekDiet(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        //load the page
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("LossWeightWeek.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        LossWeightWeek lossWeightWeek = fxmlLoader.getController();
        lossWeightWeek.lanch(stage, scene, content,rightMenu,current_patient, listOfCycles);
        System.out.println("                       ");
    }


    public void showBestOrder(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("OrderDiet.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        OrderDiet orderDiet = fxmlLoader.getController();
        orderDiet.lanch(stage, scene, content,rightMenu,current_patient, listOfCycles);
        System.out.println("                       ");

    }

    public void ShowPeopleUnderMem(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("PeopleUnderMem.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        PeopleUnderMem peopleUnderMem = fxmlLoader.getController();
        peopleUnderMem.lanch(stage, scene, content,rightMenu,current_patient, listOfCycles);
        System.out.println("                       ");
    }


    public void watchOnPatientDetails (ActionEvent event) throws IOException
    {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("PatientDetails.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        PatientDetails patientDetails = fxmlLoader.getController();
        patientDetails.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietCard (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("DietCard.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        DietCard dietCard = fxmlLoader.getController();
        dietCard.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietStatistics (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("StatisticDiet.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        StatisicDiet statisicDiet = fxmlLoader.getController();
        statisicDiet.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void Invoices (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("Invoice.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        Invoice invoice = fxmlLoader.getController();
        invoice.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void AddAppointment (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("MeetingCard.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        MeetingCard meetingCard = fxmlLoader.getController();
        meetingCard.lanch(stage, scene, content,rightMenu,current_patient);
        System.out.print("");
    }



}
