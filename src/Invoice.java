
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

import javax.swing.text.TableView;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Invoice {

    private Stage stage;
    private Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public GridPane PatientDetails;
    Patient current_patient;
    InvoiceDataCenter invoiceDataCenter;
    TotalPaymentDataAccessor totalPaymentDataAccessor;
    NeedToPayCenter needToPayCenter;
    @FXML
    public TableColumn<InvoiceObject,String> date_col;
    @FXML
    public TableColumn<InvoiceObject,String> code_col;
    @FXML
    public TableColumn<InvoiceObject,String> pay_col;
    @FXML
    public TableColumn<InvoiceObject,String> howPay_col;
    @FXML
    public TableColumn<InvoiceObject,String> moreDetails_col;
    @FXML
    public TableColumn<InvoiceObject,String> marks_col;
    @FXML
    public javafx.scene.control.TableView table;

    public TextField code_add;
    public DatePicker date_add;
    public TextField pay_add;
    public ListView howPay_add;
    public TextField moreDetails_add;
    public TextField marks_add;
    public DatePicker dateToPay_add;
    public TextField payNedded_add;

    public void lanch(Stage stageF, Scene sceneF, GridPane contentF,GridPane rightMenuF, Patient patient) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu= rightMenuF;
        current_patient = patient;
        //howPay_add = new ListView<String>();
        ObservableList<String> items =FXCollections.observableArrayList ("אשראי", "מזומן", "צ'ק");
        howPay_add.setItems(items);
        showInvoicesfromDB();
    }

    public void showInvoicesfromDB() throws SQLException, ClassNotFoundException {
        invoiceDataCenter = new InvoiceDataCenter("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        ArrayList<InvoiceObject> listOfInvoices = invoiceDataCenter.getInvoicesOfPatient(Integer.toString(current_patient.getId()));
        bulidColumns();
        ObservableList<InvoiceObject> data = FXCollections.observableArrayList();

        for( InvoiceObject invoice: listOfInvoices) {
                InvoiceObject invoiceObject = new InvoiceObject(invoice.getId(), invoice.getCode(), invoice.getdate(), invoice.getSum(), invoice.getHowPay(), invoice.getMoreDetails(), invoice.getMarks());
                data.add(invoiceObject);
                table.setItems(data);
        }
        invoiceDataCenter.shutdown();
    }

    public void printgraph (ActionEvent event) throws IOException
    {

    }

    public void bulidColumns()
    {
        date_col.setCellValueFactory(new PropertyValueFactory<InvoiceObject,String>("date"));
        code_col.setCellValueFactory(new PropertyValueFactory<InvoiceObject,String>("code"));
        pay_col.setCellValueFactory(new PropertyValueFactory<InvoiceObject,String> ("sum"));
        howPay_col.setCellValueFactory(new PropertyValueFactory<InvoiceObject,String> ("howPay"));
        moreDetails_col.setCellValueFactory(new PropertyValueFactory<InvoiceObject,String> ("moreDetails"));
        marks_col.setCellValueFactory(new PropertyValueFactory<InvoiceObject,String> ("marks"));
    }

    public void addNeedToPay(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        needToPayCenter = new NeedToPayCenter("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        LocalDate localDate = dateToPay_add.getValue(); // from datepicker
        Date date = java.sql.Date.valueOf(localDate); //convert to date in DB
        int i = needToPayCenter.getAllInvoiceNeedToPay().size();
        needToPayCenter.addNewNeedToPay(current_patient.getId(), i+1 ,date , Integer.parseInt(payNedded_add.getText()), current_patient.getTel(), current_patient.getFirstName(), current_patient.getLastName());
        payNedded_add.clear();
    }

    public void addNewInvoice(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        invoiceDataCenter = new InvoiceDataCenter("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        LocalDate localDate = date_add.getValue(); // from datepicker
        Date date = java.sql.Date.valueOf(localDate); //convert to date in DB
        int payOption = howPay_add.getSelectionModel().getSelectedIndex();
        String pay="";
        switch (payOption)
        {
            case 0: pay="אשראי";
                break;
            case 1: pay="מזומן";
                break;
            case 2: pay="צק";
                break;
        }
        int i = invoiceDataCenter.getAllInvoices().size();
        invoiceDataCenter.addNewInvoice(current_patient.getId(), i+1 ,date , Integer.parseInt(pay_add.getText()), pay, moreDetails_add.getText(), marks_add.getText() );
        updateOnTotalPayment (payOption,pay);
        pay_add.clear();
        moreDetails_add.clear();
        marks_add.clear();
        table.getItems().clear();
        showInvoicesfromDB();
    }

    public void updateOnTotalPayment (Integer payOption, String pay) throws SQLException, ClassNotFoundException {
        int credit=0;
        int chek=0;
        int cash=0;
        switch (payOption)
        {
            case 0: credit=1;
                break;
            case 1: cash=1;
                break;
            case 2: chek=1;
                break;
        }
        totalPaymentDataAccessor = new TotalPaymentDataAccessor("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        totalPaymentDataAccessor.updatePayment(current_patient.getId(), java.sql.Date.valueOf(date_add.getValue()),current_patient.getFirstName(), current_patient.getLastName(), Integer.parseInt(pay_add.getText()), credit, cash, chek);
        }
    public void watchOnPatientDetails (ActionEvent event) throws IOException
    {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("PatientDetails.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        PatientDetails patientDetails = fxmlLoader.getController();
        patientDetails.lanch(stage, scene, content,rightMenu,current_patient);
        System.out.print("");
    }
    public void DietCard (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("DietCard.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        DietCard dietCard = fxmlLoader.getController();
        dietCard.lanch(stage, scene, content,rightMenu,current_patient);
        System.out.print("");
    }
    public void DietStatistics (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("StatisticDiet.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        StatisicDiet statisicDiet = fxmlLoader.getController();
        statisicDiet.lanch(stage, scene, content,rightMenu,current_patient);
        System.out.print("");
    }
    public void Invoices (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("Invoice.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        Invoice invoice = fxmlLoader.getController();
        invoice.lanch(stage, scene, content,rightMenu,current_patient);
        System.out.print("");
    }
    public void AddAppointment (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("MeetingCard.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        MeetingCard meetingCard = fxmlLoader.getController();
        meetingCard.lanch(stage, scene, content,rightMenu,current_patient);
        System.out.print("");
    }



}
