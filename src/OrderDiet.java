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

public class OrderDiet {

    private Stage stage;
    private Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public GridPane PatientDetails;
    public Patient current_patient;

    public DietCenterData dietCenterData;
    public Integer Current_week;
    public Integer Current_week_basic;
    public ArrayList<DietCycleObject> listOfCycles;
    public BarChart barChart;

    public CategoryAxis xAxis;
    public NumberAxis yAxis;

    public void lanch(Stage stageF, Scene sceneF, GridPane contentF,GridPane rightMenuF, Patient patient , ArrayList<DietCycleObject> listCycles) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu= rightMenuF;
        Current_week=0;
        Current_week_basic=0;
        listOfCycles= listCycles;
        current_patient=patient;
        showChart();
    }


    public void showChart() throws SQLException, ClassNotFoundException {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        //barChart = new BarChart<String,Number>(xAxis,yAxis);
        xAxis.setLabel("סדר דיאטות");
        yAxis.setLabel("ממוצע ירידה במשקל");
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("ממוצע ירידה במשקל עבור סדר הדיאטות");
        dietCenterData = new DietCenterData("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/diet", "root", "Dana1993!");
        Map<String,Double> map_OrderDiet = new HashMap<String,Double>();
        map_OrderDiet = dietCenterData.getAllDetailsForOrderDiet(); // get the options of orderDiet and the diff
        List<Double> mapValues = new ArrayList<Double>(map_OrderDiet.values());
        List<String> mapKeys = new ArrayList<String>(map_OrderDiet.keySet());
        Collections.sort(mapValues);
        Collections.reverse(mapValues);
       // Collections.reverse(mapKeys);
        for (int i=0; i<8; i++)
        {
            series1.getData().add(new XYChart.Data(mapKeys.get(i), mapValues.get(i)));
        }
        barChart.getData().addAll(series1);
    }

    public void watchOnPatientDetails (ActionEvent event) throws IOException
    {
        System.out.print(" ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        System.out.print(" ");
        Parent s = fxmlLoader.load(getClass().getResource("PatientDetails.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        PatientDetails patientDetails = fxmlLoader.getController();
        patientDetails.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietCard (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print(" ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        System.out.print(" ");
        Parent s = fxmlLoader.load(getClass().getResource("DietCard.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        DietCard dietCard = fxmlLoader.getController();
        dietCard.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietStatistics (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print(" ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        System.out.print(" ");
        Parent s = fxmlLoader.load(getClass().getResource("StatisticDiet.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        StatisicDiet statisicDiet = fxmlLoader.getController();
        statisicDiet.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void Invoices (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print(" ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        System.out.print(" ");
        Parent s = fxmlLoader.load(getClass().getResource("Invoice.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        Invoice invoice = fxmlLoader.getController();
        invoice.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void AddAppointment (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print(" ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        System.out.print(" ");
        Parent s = fxmlLoader.load(getClass().getResource("MeetingCard.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        MeetingCard meetingCard = fxmlLoader.getController();
        meetingCard.lanch(stage, scene, content,rightMenu,current_patient);
        System.out.print("");
    }



}