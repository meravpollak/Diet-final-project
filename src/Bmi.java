import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class Bmi {private Stage stage;
    private Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public GridPane PatientDetails;
    public Patient current_patient;
    @FXML
    public CategoryAxis xAxis;
    public NumberAxis yAxis;
    @FXML

    public DietCenterData dietCenterData;
    public TextField textfield_pickGraph;
    public Integer Current_week;
    public Integer Current_week_basic;
    public ArrayList<DietCycleObject> listOfCycles;
    public BarChart barChart;
    public PatientDataAccessor patientDataAccessor;

    public void lanch(Stage stageF, Scene sceneF, GridPane contentF,GridPane rightMenuF, Patient patient, ArrayList<DietCycleObject> listCycles) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu= rightMenuF;
        Current_week=0;
        Current_week_basic=0;
        listOfCycles= listCycles;
        current_patient = patient;
        showChart();
    }


    public void showChart() throws SQLException, ClassNotFoundException {
        patientDataAccessor =  new PatientDataAccessor("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        ArrayList < HashMap<Double, Integer>> array = patientDataAccessor.getDietForBMI();
        final  String BMI1 = "BMI1";
        final  String BMI2 = "BMI2";
        final  String BMI3 = "BMI3";
        final  String BMI4 = "BMI4";
        final  String BMI5 = "BMI5";
        final  String BMI6 = "BMI6";

        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        //barChart = new BarChart<String,Number>(xAxis,yAxis);
        xAxis.setLabel("BMI");
        yAxis.setLabel("Value");

        XYChart.Series series1 = new XYChart.Series();
        XYChart.Series series2 = new XYChart.Series();
        XYChart.Series series3 = new XYChart.Series();

        series1.setName(" דיאטה מומלצת א'");
        series2.setName(" דיאטה מומלצת ב'");
        series3.setName("דיאטה מומלצת ג'");

        String bmi = "BMI";
        int count=1;
        for( HashMap<Double, Integer>  cycle: array) {
            String bmi_category="";
           HashMap<Double, Integer> passedMap = cycle;
            List<Integer> mapValues = new ArrayList<Integer>(passedMap.values());
            List<Double> mapKeys = new ArrayList<Double>(passedMap.keySet());
            Collections.sort(mapValues);
            Collections.reverse(mapValues);
           // Collections.sort(mapKeys);
            if (mapKeys.size()<=3)
            {
                mapKeys.add(0.0);
                mapKeys.add(0.0);
                mapKeys.add(0.0);
            }
            if (count==1)
            {
                bmi_category="תת משקל";
            }
            else if (count==2)
            {
                bmi_category="משקל תקין";
            }
            else if (count==3)
            {
                bmi_category="עודף משקל";
            }
            else if (count==4)
            {
                bmi_category="השמנה";
            }
            else if (count==5)
            {
                bmi_category="השמנת יתר";
            }
            else if (count==6)
            {
                bmi_category="השמנה חולנית";
            }
            series1.getData().add(new XYChart.Data(bmi_category, mapKeys.get(0)));
            series2.getData().add(new XYChart.Data(bmi_category, mapKeys.get(1)));
            series3.getData().add(new XYChart.Data(bmi_category, mapKeys.get(2)));
            count++;
        }


        barChart.getData().addAll(series1, series2, series3);
    }


    public void watchOnPatientDetails (ActionEvent event) throws IOException
    {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("PatientDetails.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        System.out.print(" ");
        PatientDetails patientDetails = fxmlLoader.getController();
        patientDetails.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietCard (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        System.out.print(" ");
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
        System.out.print(" ");
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
        System.out.print("");
        Invoice invoice = fxmlLoader.getController();
        invoice.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void AddAppointment (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("MeetingCard.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        System.out.print(" ");
        MeetingCard meetingCard = fxmlLoader.getController();
        meetingCard.lanch(stage, scene, content,rightMenu,current_patient);
        System.out.print("");
    }

}