import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Calendar;
import java.util.Date;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class DietCard {

    private Stage stage;
    private Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public GridPane PatientDetails;
    public Patient current_patient;
    /**
     *
     */
    @FXML
    public TableColumn<DietCycleObject, String> treat_col;
    @FXML
    public TableColumn<DietCycleObject, String> week_col;
    @FXML
    public TableColumn<DietCycleObject, String> date_col;
    @FXML
    public TableColumn<DietCycleObject, String> weight_col;
    @FXML
    public TableColumn<DietCycleObject, String> weight_diff_col;
    @FXML
    public TableColumn<DietCycleObject, String> diet_col;
    public TableColumn<DietCycleObject, String> days_col;
    public TableColumn<DietCycleObject, String> arrived_col;
    public TableColumn<DietCycleObject, String> marks_col;
    public TableColumn<DietCycleObject, Boolean> dietDetails_col;

    @FXML
    public TextField firstName;
    public TextField wantwieght;
    public TextField bodyMass;
    public TextField howmuchBodymass;
    public TextField calories;

    //table
    DietCenterData dietCenterData;
    public TextField treat_add;
    public TextField week_add;
    public DatePicker date_add;
    public TextField weight_add;
    //public TextField weight_diff_add;
    public TextField diet_add;
    public TextField days_add;
    public CheckBox arrived_add;
    public TextField marks_add;
    public TableView table;
    public ObservableList<DietCycleObject> data;
    public DietCycleObject lastCycle;
    PatientDataAccessor patientDataAccessor;

    public void lanch(Stage stageF, Scene sceneF, GridPane contentF,GridPane rightMenuF, Patient patient_selected) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu= rightMenuF;
        current_patient=patient_selected;
        firstName.setText(current_patient.getFirstName());
        wantwieght.setText(Double.toString(current_patient.getWeightProperty()));
        double BMI = calculteBMI();
        BMI = Double.parseDouble(new DecimalFormat("##.##").format(BMI));
        bodyMass.setText(Double.toString(BMI));
        checkBodyMass(BMI);
        calculateCalories();
        initializeCycle();
        showCycelsfromDB();

    }

    public void initializeCycle() throws SQLException, ClassNotFoundException {
        dietCenterData = new DietCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        ArrayList<DietCycleObject> listOfCycles = dietCenterData.getCycelsOfPatient(Integer.toString(current_patient.getId()));
        if (listOfCycles.size()==0)
        {
            dietCenterData.addNewCycle(current_patient.getId(),1,0,LocalDate.now(),current_patient.getPastHighWeightProperty(),0.0,0.0,0,arrived_add,marks_add.getText());
            lastCycle= new DietCycleObject(current_patient.getId(),1,0,LocalDate.now(),current_patient.getPastHighWeightProperty(),0.0,0.0,0,1,marks_add.getText());
        }
        dietCenterData.shutdown();
    }

    public void calculateCalories() {
        double c = 0;
        String sex = current_patient.getSex();
        if (sex.equals("זכר"))
        {
           // c = (current_patient.getCurrentWeight()*10) + (current_patient.getHeightProperty()*6.25) - (current_patient.getAge()*5) + 5;
            c = 665+ (current_patient.getCurrentWeight()*13.75) + (5.003* current_patient.getHeightProperty()) - (current_patient.getAge()*6.775);

        }
        else
        {
           // c = (current_patient.getCurrentWeight()*10) + (current_patient.getHeightProperty()*6.25) -(current_patient.getAge()*5) -1;
            c = 665+ (current_patient.getCurrentWeight()*9.563) + (1.85* current_patient.getHeightProperty()) - (current_patient.getAge()*4.676);

        }
        c = Double.parseDouble(new DecimalFormat("##.##").format(c));
        calories.setText(Double.toString(c));
    }


    public double calculteBMI()
    {
        double h =current_patient.getHeightProperty();
        double w =current_patient.getCurrentWeight();
        double h2=h*h;
        return (1.0*w)/h2*10.0 /10.0;
    }

    public void checkBodyMass(double BMI){
            if (BMI<18.5)
            {
                howmuchBodymass.setText("תת משקל");
            }
            else if (BMI>=18.5 && BMI<=24.9)
            {
                howmuchBodymass.setText("משקל תקין");
            }
            else if (BMI>=25 && BMI<=29.9)
            {
                howmuchBodymass.setText("עודף משקל");
            }
            else if (BMI>=30 && BMI<=34.9)
            {
                howmuchBodymass.setText("השמנה");
            }
            else if (BMI>=35 && BMI<=39.9)
            {
                howmuchBodymass.setText("השמנת יתר");
            }
            else if(BMI>40)
            {
                howmuchBodymass.setText("השמנה חולנית");
            }

    }

    public void showCycelsfromDB() throws SQLException, ClassNotFoundException {
        dietCenterData = new DietCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        ArrayList<DietCycleObject> listOfCycles = dietCenterData.getCycelsOfPatient(Integer.toString(current_patient.getId()));
        bulidColumns();
        data = FXCollections.observableArrayList();

        for( DietCycleObject cycle: listOfCycles) {
            DietCycleObject dietCycleObject = new DietCycleObject(cycle.getId(), cycle.gettreat(), cycle.getWeek(), cycle.getDate(), cycle.getWeight(), cycle.getDiff(), cycle.getDiet(), cycle.getDays(), cycle.getarrived() , cycle.getmarks());
            lastCycle = new DietCycleObject(cycle.getId(), cycle.gettreat(), cycle.getWeek(), cycle.getDate(), cycle.getWeight(), cycle.getDiff(), cycle.getDiet(), cycle.getDays(), cycle.getarrived() , cycle.getmarks());

            data.add(dietCycleObject);
            treat_col.setSortType(TableColumn.SortType.ASCENDING);
            table.setItems(data);
            dietDetails_col.setCellFactory(
                    new Callback<TableColumn<DietCycleObject, Boolean>, TableCell<DietCycleObject, Boolean>>() {
                        @Override
                        public TableCell<DietCycleObject, Boolean> call(TableColumn<DietCycleObject, Boolean> p) {
                            return new ButtonCell(dietCycleObject.getDiet());
                        }
                    });

            table.getSortOrder().add(treat_col);

            if (cycle.getarrived()==1)
            {
                arrived_col.setCellFactory(
                        new Callback<TableColumn<DietCycleObject, String>, TableCell<DietCycleObject, String>>() {
                            @Override
                            public TableCell<DietCycleObject, String> call(TableColumn<DietCycleObject, String> p) {
                                return new ImageCell();
                            }
                        });
            }

            else if (cycle.getarrived()==0)
            {
                arrived_col.setCellFactory(
                        new Callback<TableColumn<DietCycleObject, String>, TableCell<DietCycleObject, String>>() {
                            @Override
                            public TableCell<DietCycleObject, String> call(TableColumn<DietCycleObject, String> p) {
                                return new ImageCellX();
                            }
                        });
            }
            arrived_col.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<DietCycleObject, String>,
                            ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<DietCycleObject, String> p) {
                            return new SimpleStringProperty("");
                        }
                    });
        }
        dietCenterData.shutdown();
    }

    public void bulidColumns()
    {

        treat_col.setCellValueFactory(new PropertyValueFactory<DietCycleObject,String>("treat"));
        week_col.setCellValueFactory(new PropertyValueFactory<DietCycleObject,String>("week"));
        date_col.setCellValueFactory(new PropertyValueFactory<DietCycleObject,String> ("date"));
        weight_col.setCellValueFactory(new PropertyValueFactory<DietCycleObject,String> ("weight"));
        weight_diff_col.setCellValueFactory(new PropertyValueFactory<DietCycleObject,String> ("diff"));
        diet_col.setCellValueFactory(new PropertyValueFactory<DietCycleObject,String> ("diet"));
        days_col.setCellValueFactory(new PropertyValueFactory<DietCycleObject,String> ("days"));
        //arrived_col.setCellValueFactory(new PropertyValueFactory<DietCycleObject,String> ("arrived"));
        marks_col.setCellValueFactory(new PropertyValueFactory<DietCycleObject,String> ("marks"));

        dietDetails_col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<DietCycleObject, Boolean>,
                        ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<DietCycleObject, Boolean> p) {
                        return new SimpleBooleanProperty(p.getValue() != null);
                    }
                });

    }

    public void addPrivateDiet(ActionEvent actionEvent) {
        Dialog<String> dialog = new Dialog<>();
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        gridPane.setPrefWidth(500);
        gridPane.setPrefHeight(400);

        TextField textField = new TextField();
        gridPane.add(textField, 0, 0);
        dialog.getDialogPane().setContent(gridPane);
        dialog.showAndWait();
    }

    public void addNewDiet(ActionEvent actionEvent) {

      //  StackPane secondLayout = new StackPane();

        GridPane grid = new GridPane();
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        ColumnConstraints columnConstraints2 = new ColumnConstraints();
        RowConstraints rowConstraints1 = new RowConstraints();
        RowConstraints rowConstraints2 = new RowConstraints();
        RowConstraints rowConstraints3 = new RowConstraints();
        RowConstraints rowConstraints4 = new RowConstraints();
        RowConstraints rowConstraints5 = new RowConstraints();
        RowConstraints rowConstraints6 = new RowConstraints();
        grid.getRowConstraints().add(0,rowConstraints1);
        grid.getRowConstraints().add(1,rowConstraints2);
        grid.getRowConstraints().add(2,rowConstraints3);
        grid.getRowConstraints().add(3,rowConstraints4);
        grid.getRowConstraints().add(4,rowConstraints5);
        grid.getRowConstraints().add(4,rowConstraints6);
        grid.getRowConstraints().add(4,rowConstraints6);
        grid.getColumnConstraints().add(0,columnConstraints1);
        grid.getColumnConstraints().add(1,columnConstraints2);
        grid.setPrefHeight(700);
        grid.setPrefWidth(700);
        grid.setVgap(30);
        grid.setHgap(30);
        grid.setAlignment(Pos.CENTER);
        TextField diet_details = new TextField();
        diet_details.setPrefWidth(400);
        diet_details.setPrefHeight(400);
        Label l = new Label("הכנס את תפריט הדיאטה:           ");
        l.setStyle("-fx-text-fill: #818181;\n" +
                "    -fx-font-family: 'Comix No2 CLM' ;\n" +
                "    -fx-font-weight: bold; -fx-font-size: 25px");
        Label numDiet = new Label("הכנס מספר דיאטה: ");
        numDiet.setStyle("-fx-text-fill: #818181;\n" +
                "    -fx-font-family: 'Comix No2 CLM' ;\n" +
                "    -fx-font-weight: bold; -fx-font-size: 18px");
        TextField id = new TextField();
        Button button= new Button("הוסף דיאטה אישית");
        button.setStyle("-fx-text-fill: #818181;\n" +
                "    -fx-font-family: 'Comix No2 CLM' ;\n" +
                "    -fx-font-weight: bold; -fx-font-size: 22px");

        grid.add(l,0,0,2,1);
        grid.add(numDiet,1,1,1,1);
        grid.add(id,0,1,1,1);
        grid.add(diet_details,0,2,2,4);
        grid.add(button,0,6,2,1);


      //  secondLayout.getChildren().add(grid);
        Scene secondScene = new Scene(grid,700,700);

                //new Window
        Stage newWindow = new Stage();
        newWindow.setTitle("דיאטה אישית");
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.initOwner(stage);
        newWindow.setX(stage.getX()+500);
        newWindow.setY(stage.getY()+100);
        newWindow.show();

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    dietCenterData = new DietCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
                    dietCenterData.insertDiet(Double.parseDouble(id.getText()), diet_details.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("הדיאטה נוספה בהצלחה!");
                    alert.setTitle("הוספת דיאטה");
                    alert.initModality(Modality.APPLICATION_MODAL);
                    alert.initOwner(stage);
                    newWindow.close();
                    alert.showAndWait();
                    diet_add.setText(id.getText());
                    marks_add.setText("דיאטה אישית");

                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void showDiet (String details) {

        GridPane grid = new GridPane();
        System.out.println();
        ColumnConstraints columnConstraints1 = new ColumnConstraints();
        ColumnConstraints columnConstraints2 = new ColumnConstraints();
        RowConstraints rowConstraints1 = new RowConstraints();
        RowConstraints rowConstraints2 = new RowConstraints();
        RowConstraints rowConstraints3 = new RowConstraints();
        RowConstraints rowConstraints4 = new RowConstraints();
        RowConstraints rowConstraints5 = new RowConstraints();
        RowConstraints rowConstraints6 = new RowConstraints();
        grid.getRowConstraints().add(0,rowConstraints1);
        grid.getRowConstraints().add(1,rowConstraints2);
        grid.getRowConstraints().add(2,rowConstraints3);
        grid.getRowConstraints().add(3,rowConstraints4);
        grid.getRowConstraints().add(4,rowConstraints5);
        grid.getRowConstraints().add(4,rowConstraints6);
        grid.getRowConstraints().add(4,rowConstraints6);
        grid.getColumnConstraints().add(0,columnConstraints1);
        grid.getColumnConstraints().add(1,columnConstraints2);
        grid.setPrefHeight(600);
        grid.setPrefWidth(600);
        grid.setVgap(30);
        grid.setHgap(30);
        grid.setAlignment(Pos.CENTER);
        TextArea diet_details = new TextArea();
        diet_details.setPrefWidth(500);
        diet_details.setPrefHeight(500);
        diet_details.setText(details);
        diet_details.setStyle("-fx-text-alignment: right; ");

        grid.add(diet_details,0,2,5,8);
        Label l = new Label("תפריט הדיאטה:                    ");
        l.setStyle("-fx-text-fill: #818181;\n" +
                "    -fx-font-family: 'Comix No2 CLM' ;\n" +
                "    -fx-font-weight: bold; -fx-font-size: 32px");
        grid.add(l,0,0,2,1);

        //  secondLayout.getChildren().add(grid);
        Scene secondScene = new Scene(grid,700,700);

        //new Window
        Stage newWindow = new Stage();
        newWindow.setTitle("צפייה בדיאטה שניתנה");
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.APPLICATION_MODAL);
        newWindow.initOwner(stage);
        newWindow.setX(stage.getX()+500);
        newWindow.setY(stage.getY()+100);
        newWindow.show();



    }

    private class ImageCell extends TableCell<DietCycleObject, String> {
        ImageView ImageCell = new ImageView(new Image("v.png"));

        ImageCell(){
            ImageCell.setFitHeight(30);
            ImageCell.setFitWidth(30);
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(String t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(ImageCell);
            }
        }
    }

    private class ImageCellX extends TableCell<DietCycleObject, String> {
        ImageView ImageCellX = new ImageView(new Image("X.png"));

        ImageCellX(){
            ImageCellX.setFitHeight(30);
            ImageCellX.setFitWidth(30);
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(String t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(ImageCellX);
            }
        }
    }

    private class ButtonCell extends TableCell<DietCycleObject, Boolean> {
        final Button cellButton = new Button("צפיה");

        ButtonCell(Double diet){



            cellButton.setOnAction(new EventHandler<ActionEvent>(){

                @Override
                public void handle(ActionEvent t) {
                    // do something when button clicked
                    //...
                    try {
                        dietCenterData = new DietCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
                        String dietShow = dietCenterData.getDiet(diet);
                        System.out.println(dietShow);
                        System.out.println(dietShow.replace(".", System.lineSeparator()));
                        showDiet(dietShow.replace(".", System.lineSeparator()));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }



                }
            });
        }

        //Display button if the row is not empty
        @Override
        protected void updateItem(Boolean t, boolean empty) {
            super.updateItem(t, empty);
            if(!empty){
                setGraphic(cellButton);
            }
        }
    }

    public void removeLastCycle(ActionEvent event) throws IOException, SQLException, ClassNotFoundException
    {
        data.remove(lastCycle);
        dietCenterData = new DietCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        dietCenterData.removeLastCycle(current_patient.getId(), lastCycle);
        table.getItems().clear();
        showCycelsfromDB();

    }
    public void addNewCycle(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        if (checkValid())
        {

            dietCenterData = new DietCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
            LocalDate localDate = date_add.getValue(); // from datepicker
            double diff;
            if (diet_add.getText().equals("0"))
            {
              diff=0.0;
              dietCenterData.addNewCycle(current_patient.getId(), Integer.parseInt(treat_add.getText()), Integer.parseInt(week_add.getText()), localDate, 0.0, 0.0, 0.0, 0,arrived_add, "");

            }
            else {
                diff = Double.parseDouble(weight_add.getText())-lastCycle.getWeight();
                diff = Double.parseDouble(new DecimalFormat("##.##").format((diff)));
                dietCenterData.addNewCycle(current_patient.getId(), Integer.parseInt(treat_add.getText()), Integer.parseInt(week_add.getText()), localDate, Double.parseDouble(weight_add.getText()), diff, Double.parseDouble(diet_add.getText()), Integer.parseInt(days_add.getText()),arrived_add, marks_add.getText());
                Double diet = lastCycle.getDiet();
                patientDataAccessor = new PatientDataAccessor("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
                patientDataAccessor.updateHighLoss(diff, Integer.toString(current_patient.getId()),current_patient.getHeightProperty(), Double.parseDouble(bodyMass.getText()), diet);
                dietCenterData.insertIntoLastMeet(Integer.toString(current_patient.getId()), Integer.parseInt(week_add.getText()), localDate);

            }

            //get time
            Calendar cal = Calendar.getInstance();
            Date date=cal.getTime();
            DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            String formattedDate=dateFormat.format(date);

            dietCenterData.UpdateMissingReport(current_patient.getId(),current_patient.getFirstName(),current_patient.getLastName(),current_patient.getTel(), date_add.getValue(),formattedDate , Integer.parseInt(week_add.getText()));
            treat_add.clear();
            week_add.clear();
            weight_add.clear();
            //weight_diff_add.clear();;
            diet_add.clear();
            days_add.clear();
            marks_add.clear();
            table.getItems().clear();
            dietCenterData.shutdown();
            showCycelsfromDB();
        }

    }

    public boolean checkValid()
    {
        boolean good=true;
        try{

            if (!treat_add.getText().equals(""))
            {
                Integer.parseInt(treat_add.getText());
            }
        }catch(Exception e ){
            treat_add.setText("bad input!");
            treat_add.setStyle("-fx-text-fill: #DC143C");
            System.out.print(" ");
            good=false;
        }

        try{
            if (!week_add.getText().equals(""))
            {
                Integer.parseInt(week_add.getText());
            }
        }catch(Exception e ){
            week_add.setText("bad input!");
            week_add.setStyle("-fx-text-fill: #DC143C");
            good=false;
            System.out.print("-");
        }

        try{

            if (!weight_add.getText().equals(""))
            {
                System.out.print("  ");
                Double.parseDouble(weight_add.getText());
            }
        }catch(Exception e ){
            weight_add.setText("bad input!");
            weight_add.setStyle("-fx-text-fill: #DC143C");
            good=false;
        }


        try{
            if (!days_add.getText().equals(""))
            {
                Integer.parseInt(days_add.getText());
            }

        }catch(Exception e ){
            days_add.setText("bad input!");
            days_add.setStyle("-fx-text-fill: #DC143C");
            good=false;
            System.out.print("  ");
        }

            if (diet_add.getText().equals(""))
            {
                diet_add.setText("0");
            }

        return good;
    }


    public void showasGraph(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("DietCardGraph.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        DietCardGraph dietCardGraph = fxmlLoader.getController();
        dietCardGraph.lanch(stage, scene, content,rightMenu, current_patient);
    }

    public void printDiet (ActionEvent event) throws IOException
    {

    }

    public void printgraph (ActionEvent event) throws IOException
    {

    }

    public void watchOnPatientDetails (ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("PatientDetails.fxml").openStream());
        content.getChildren().clear();
        System.out.println();
        content.getChildren().addAll(s);
        PatientDetails patientDetails = fxmlLoader.getController();
        patientDetails.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietCard (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("DietCard.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        System.out.println();
        DietCard dietCard = fxmlLoader.getController();
        dietCard.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void DietStatistics (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("StatisticDiet.fxml").openStream());
        content.getChildren().clear();
        System.out.println();
        content.getChildren().addAll(s);
        StatisicDiet statisicDiet = fxmlLoader.getController();
        statisicDiet.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void Invoices (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("Invoice.fxml").openStream());
        content.getChildren().clear();
        System.out.println();
        content.getChildren().addAll(s);
        Invoice invoice = fxmlLoader.getController();
        invoice.lanch(stage, scene, content,rightMenu,current_patient);
    }
    public void AddAppointment (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        System.out.println();
        Parent s = fxmlLoader.load(getClass().getResource("MeetingCard.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        MeetingCard meetingCard = fxmlLoader.getController();
        meetingCard.lanch(stage, scene, content,rightMenu, current_patient);
    }

}
