import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class SuggestionDiet {

    private Stage stage;
    private Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public GridPane PatientDetails;
    public Patient current_patient;
    @FXML
    public NumberAxis x;
    public LineChart lineChart;
    @FXML
    public NumberAxis y;
    public DietCenterData dietCenterData;
    public TextField textfield_pickGraph;
    public Integer Current_week;
    public Integer Current_week_basic;
    public ArrayList<DietCycleObject> listOfCycles;
    public PieChart pieChart;

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
        ObservableList<PieChart.Data> pieCharData = FXCollections.observableArrayList();
        HashMap<Double,Integer> map_count = new HashMap<Double,Integer>();
        HashMap<Double,Double> map_diffs = new HashMap<Double,Double>();

        for (DietCycleObject cycle: listOfCycles) {
        Integer count =map_count.get(cycle.getDiet());
        if (count == null)
        {
            count = 1;
        }
        else count++;
            Double diff = map_diffs.get(cycle.getDiet());
        if (diff == null)
        {
            diff = 0.0;
        }
        else diff++;
        map_count.put(cycle.getDiet(), count);
        map_diffs.put(cycle.getDiet(), diff+cycle.getDiff());
        }

        ArrayList<Double> diets = new ArrayList<>();
        double sum=0;
        double d=0;
        int count = 0;
        for( DietCycleObject cycle: listOfCycles)
        {
            if (count<=8)
            {
                if (!diets.contains(cycle.getDiet()))
                {
                    diets.add(cycle.getDiet());

                    if ((map_diffs.get(cycle.getDiet())<0))
                    {
                        d =map_diffs.get(cycle.getDiet())*(-1);
                    }
                    sum = d/(map_count.get(cycle.getDiet()));
                    double diet = cycle.getDiet();
                    sum = Double.parseDouble(new DecimalFormat("##.##").format(sum));
                    String diet_s = " דיאטה "+Double.toString(diet) + " : "+Double.toString(sum);
                    pieCharData.add(new PieChart.Data(diet_s,sum));
                }
                sum=0;
                d=0;
                count++;
            }

        }

        final Label caption = new Label("");
        caption.setTextFill(Color.DARKGRAY);

        for (final PieChart.Data data : pieChart.getData())
        {
            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, event ->
                    {
                double total =0;
//                for (PieChart.Data d1 : pieChart.getData())
//                {
//                    total +=d1.getPieValue();
//                }
                caption.setTranslateX(event.getSceneX());
                caption.setTranslateY(event.getSceneY());
                caption.setText(Double.toString(data.getPieValue()));

            }
            );
        }


        pieChart.setTitle("ירידה במשקל כפונקציה של הדיאטה");
        pieChart.setData(pieCharData);
    }

    public void watchOnPatientDetails (ActionEvent event) throws IOException
    {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("PatientDetails.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        PatientDetails patientDetails = fxmlLoader.getController();
        System.out.print("");
        patientDetails.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietCard (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("DietCard.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        System.out.print("");
        DietCard dietCard = fxmlLoader.getController();
        dietCard.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietStatistics (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        System.out.print("");
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
        System.out.print("    ");
        content.getChildren().addAll(s);
        Invoice invoice = fxmlLoader.getController();
        invoice.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void AddAppointment (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.print("");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("MeetingCard.fxml").openStream());
        content.getChildren().clear();
        System.out.print("     ");
        content.getChildren().addAll(s);
        MeetingCard meetingCard = fxmlLoader.getController();
        meetingCard.lanch(stage, scene, content,rightMenu,current_patient);
        System.out.print("");
    }



}