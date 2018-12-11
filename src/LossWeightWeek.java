import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class LossWeightWeek {

    private Stage stage;
    private Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public GridPane PatientDetails;
    public Patient current_patient;
    @FXML
    public DietCenterData dietCenterData;
    public Integer Current_week;
    public Integer Current_week_basic;
    public ArrayList<DietCycleObject> listOfCycles;
    public PieChart pieChart;
    public TextField week_textField;
    public Label labelOfNumOfPatient;
    public Label TotalLossWeight;
    public Patient Current_patient;

    public void lanch(Stage stageF, Scene sceneF, GridPane contentF,GridPane rightMenuF, Patient patient , ArrayList<DietCycleObject> listCycles) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu= rightMenuF;
        Current_week=0;
        Current_week_basic=0;
        listOfCycles= listCycles;
        Current_patient=patient;
       // showChart();
    }


    public void showChart() throws SQLException, ClassNotFoundException {
        ObservableList<PieChart.Data> pieCharData = FXCollections.observableArrayList();
        dietCenterData = new DietCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        HashMap<Double,Double> map_total_mem = new HashMap<Double,Double>();
        double total_sum = dietCenterData.getSumofDiffPerWeek(week_textField.getText())*(-1);
        if (week_textField.getText()!="")
        {
            map_total_mem = dietCenterData.getWeightsPerWeek(week_textField.getText());

            for (Double diet_num : map_total_mem.keySet())
            {
                DecimalFormat df = new DecimalFormat("#.00");
                double value = map_total_mem.get(diet_num)/total_sum;
                //String diet_s = " דיאטה "+Double.toString(diet_num) + " : "+Double.toString(Double.parseDouble(df.format(map_total_mem.get(diet_num))));
                String diet_s = " דיאטה "+Double.toString(diet_num) + " : "+Double.toString(Double.parseDouble(df.format(value)))+"%";

                pieCharData.add(new PieChart.Data(diet_s,value));
            }

            TotalLossWeight.setText("סך הכל ירידה בשבוע בקילוגרמים : "+ dietCenterData.getSumofDiffPerWeek(week_textField.getText())*(-1));
            TotalLossWeight.setStyle("-fx-font-weight: bold");
            labelOfNumOfPatient.setText("מס הלקוחות : "+ dietCenterData.getNumOfPatientPerWeek(week_textField.getText()));
            labelOfNumOfPatient.setStyle("-fx-font-weight: bold");
                        
            pieChart.setTitle("ירידה במשקל בשבוע ");
            pieChart.setData(pieCharData);
        }
    }

    public void watchOnPatientDetails (ActionEvent event) throws IOException
    {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("PatientDetails.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        PatientDetails patientDetails = fxmlLoader.getController();
        System.out.print("  ");
        patientDetails.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietCard (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print(" ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("DietCard.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        System.out.print(" ");
        DietCard dietCard = fxmlLoader.getController();
        dietCard.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietStatistics (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        System.out.print(" ");
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
        System.out.print("     ");
        content.getChildren().addAll(s);
        Invoice invoice = fxmlLoader.getController();
        invoice.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void AddAppointment (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("MeetingCard.fxml").openStream());
        content.getChildren().clear();
        System.out.print("      ");
        content.getChildren().addAll(s);
        MeetingCard meetingCard = fxmlLoader.getController();
        meetingCard.lanch(stage, scene, content,rightMenu,current_patient);
        System.out.print("");
    }



}
