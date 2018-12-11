import com.sun.xml.internal.ws.spi.db.DatabindingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class LeftPeople {

    private Stage stage;
    private Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public GridPane PatientDetails;
    public Patient current_patient;
    @FXML
    public CategoryAxis xAxis;
    @FXML
    public NumberAxis yAxis;
    public Integer Current_week;
    public Integer Current_week_basic;
    public ArrayList<DietCycleObject> listOfCycles;
    public BarChart barChart;
    public DietCenterData dietCenterData;

    public void lanch(Stage stageF, Scene sceneF, GridPane contentF,GridPane rightMenuF, Patient patient , ArrayList<DietCycleObject> listCycles) throws SQLException, ClassNotFoundException {
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
        dietCenterData = new DietCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        ArrayList<Integer> arrayList = dietCenterData.getLastMeet(LocalDate.now());
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        //   final BarChart<String,Number> bc = new BarChart<String,Number>(xAxis,yAxis);
        String week = "שבוע";
        int count=1;
        xAxis.setLabel("שבוע");
        yAxis.setLabel("מס אנשים שעזבו");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("מס האנשים שפרשו מהתהליך");
        arrayList.remove(0);
        arrayList.remove(11);
        for(Integer w: arrayList) {
            if (count <12 )
            {
                series1.getData().add(new XYChart.Data(week+" "+count, w));
                count++;
            }
        }

        barChart.getData().addAll(series1);
    }

    public void watchOnPatientDetails (ActionEvent event) throws IOException
    {
        System.out.print("  ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("PatientDetails.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        System.out.print("  ");
        PatientDetails patientDetails = fxmlLoader.getController();
        patientDetails.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietCard (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print(" ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("DietCard.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        System.out.print("  ");
        DietCard dietCard = fxmlLoader.getController();
        dietCard.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietStatistics (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print(" ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("StatisticDiet.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        StatisicDiet statisicDiet = fxmlLoader.getController();
        System.out.print("  ");
        statisicDiet.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void Invoices (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print(" ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("Invoice.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        Invoice invoice = fxmlLoader.getController();
        System.out.print("  ");
        invoice.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void AddAppointment (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print(" ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("MeetingCard.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        MeetingCard meetingCard = fxmlLoader.getController();
        meetingCard.lanch(stage, scene, content,rightMenu,current_patient);
        System.out.print("  ");
        System.out.print("");
    }



}