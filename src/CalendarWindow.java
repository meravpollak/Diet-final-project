import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;

import javax.swing.text.TableView;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class CalendarWindow {

    private Stage stage;
    private Scene scene;
    public GridPane rightMenu;
    public GridPane content;
    public DatePicker datePicker;
    public GridPane calendar;
    public LocalDate selectedDate;
    public MeetingCenterData meetingCenterData;
    public javafx.scene.control.TableView table;
    public TableColumn<Meeting_Patient_Object,String> tel_col;
    public TableColumn<Meeting_Patient_Object,String> hour_col;
    public TableColumn<Meeting_Patient_Object,String> firstName_col;
    public TableColumn<Meeting_Patient_Object,String> lastName_col;
    public TableColumn<Meeting_Patient_Object,String> arrived_col;
    public TableColumn<Meeting_Patient_Object,String> new_col;
    public TableColumn<Meeting_Patient_Object, Boolean> checkbox_arrived_col;
    public Text Totalinvented;
    public Text TotalArrived;
    public Text dateOfToday;
    public DatePickerSkin datePickerSkin;
    public LocalDate markedAsHoliday;
    public ArrayList datesMarkedAsHolidays;
    public int numOfAttending;


    public void lanch(Stage stageF, Scene sceneF, GridPane contentF, GridPane rightMenuF) throws SQLException, ClassNotFoundException {
        datesMarkedAsHolidays = new ArrayList();
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu = rightMenuF;
        numOfAttending=0;
        TotalArrived.setText(String.valueOf(numOfAttending));
        showCallander();
        showMeetingsOftoday();

        //showMeetings();
    }

    public void showMeetingsOftoday() throws SQLException, ClassNotFoundException
    {
        numOfAttending=0;
        TotalArrived.setText(String.valueOf(numOfAttending));
        dateOfToday.setText(datePicker.getValue().toString());
        meetingCenterData = new MeetingCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        Date date_sql = java.sql.Date.valueOf(datePicker.getValue());
        ArrayList<Meeting_Patient_Object> meeting_list = meetingCenterData.getMeetingOfSelectedDate(date_sql);
        Totalinvented.setText(String.valueOf(meeting_list.size()));
        bulidColumns();
        ObservableList<Meeting_Patient_Object> data = FXCollections.observableArrayList();
        for( Meeting_Patient_Object cycle: meeting_list) {
            Meeting_Patient_Object meet1 = new Meeting_Patient_Object(cycle.getId(),cycle.getDate(),cycle.getTime(),cycle.getFirstName(),cycle.getLastName(),cycle.getTel(),cycle.getArrived(),cycle.getNewP());
            data.add(meet1);
            hour_col.setSortType(TableColumn.SortType.ASCENDING);
            table.setItems(data);
            table.getSortOrder().add(hour_col);

            if (cycle.getNewP()==1)
            {
                new_col.setCellFactory(
                        new Callback<TableColumn<Meeting_Patient_Object, String>, TableCell<Meeting_Patient_Object, String>>() {
                            @Override
                            public TableCell<Meeting_Patient_Object, String> call(TableColumn<Meeting_Patient_Object, String> p) {
                                return new CalendarWindow.ImageCell();
                            }
                        });
            }

            else if (cycle.getNewP()==0)
            {
                new_col.setCellFactory(
                        new Callback<TableColumn<Meeting_Patient_Object, String>, TableCell<Meeting_Patient_Object, String>>() {
                            @Override
                            public TableCell<Meeting_Patient_Object, String> call(TableColumn<Meeting_Patient_Object, String> p) {
                                return new CalendarWindow.ImageCellX();
                            }
                        });
            }

            new_col.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<Meeting_Patient_Object, String>,
                            ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<Meeting_Patient_Object, String> p) {
                            return new SimpleStringProperty("");
                        }
                    });


        }

        System.out.println("");
        meetingCenterData.shutdown();
    }
    public void showMeetings() throws SQLException, ClassNotFoundException {
        numOfAttending=0;
        TotalArrived.setText(String.valueOf(numOfAttending-1));
        meetingCenterData = new MeetingCenterData("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        Date date_sql = java.sql.Date.valueOf(selectedDate);
        ArrayList<Meeting_Patient_Object> meeting_list = meetingCenterData.getMeetingOfSelectedDate(date_sql);
        Totalinvented.setText(String.valueOf(meeting_list.size()));
        bulidColumns();
        ObservableList<Meeting_Patient_Object> data = FXCollections.observableArrayList();
        for( Meeting_Patient_Object cycle: meeting_list) {
            Meeting_Patient_Object meet1 = new Meeting_Patient_Object(cycle.getId(),cycle.getDate(),cycle.getTime(),cycle.getFirstName(),cycle.getLastName(),cycle.getTel(),cycle.getArrived(),cycle.getNewP());
            data.add(meet1);
            hour_col.setSortType(TableColumn.SortType.ASCENDING);
            table.setItems(data);
            table.getSortOrder().add(hour_col);

            if (cycle.getNewP()==1)
                {System.out.println("");
                new_col.setCellFactory(
                        new Callback<TableColumn<Meeting_Patient_Object, String>, TableCell<Meeting_Patient_Object, String>>() {
                            @Override
                            public TableCell<Meeting_Patient_Object, String> call(TableColumn<Meeting_Patient_Object, String> p) {
                                return new CalendarWindow.ImageCell();
                            }
                        });
            }

            else if (cycle.getArrived()==0)
            {
                new_col.setCellFactory(
                        new Callback<TableColumn<Meeting_Patient_Object, String>, TableCell<Meeting_Patient_Object, String>>() {
                            @Override
                            public TableCell<Meeting_Patient_Object, String> call(TableColumn<Meeting_Patient_Object, String> p) {
                                return new CalendarWindow.ImageCellX();
                            }
                        });
            }

            new_col.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<Meeting_Patient_Object, String>,
                            ObservableValue<String>>() {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<Meeting_Patient_Object, String> p) {
                            return new SimpleStringProperty("");
                        }
                    });

        }


        System.out.println("");
        meetingCenterData.shutdown();
    }

    private class ImageCell extends TableCell<Meeting_Patient_Object, String> {
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

    private class ImageCellX extends TableCell<Meeting_Patient_Object, String> {
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

    public void bulidColumns()
    {

        hour_col.setCellValueFactory(new PropertyValueFactory<Meeting_Patient_Object,String>("time"));
        firstName_col.setCellValueFactory(new PropertyValueFactory<Meeting_Patient_Object,String> ("firstName"));
        lastName_col.setCellValueFactory(new PropertyValueFactory<Meeting_Patient_Object,String> ("lastName"));
        tel_col.setCellValueFactory(new PropertyValueFactory<Meeting_Patient_Object,String> ("tel"));
       // arrived_col.setCellValueFactory(new PropertyValueFactory<Meeting_Patient_Object,String> ("arrived"));
        new_col.setCellValueFactory(new PropertyValueFactory<Meeting_Patient_Object,String> ("new"));

        checkbox_arrived_col.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Meeting_Patient_Object, Boolean>,
                        ObservableValue<Boolean>>() {
                    @Override
                    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Meeting_Patient_Object, Boolean> p) {
                        return new SimpleBooleanProperty(p.getValue() != null);
                    }
                });

        checkbox_arrived_col.setCellFactory(
                new Callback<TableColumn<Meeting_Patient_Object, Boolean>, TableCell<Meeting_Patient_Object, Boolean>>() {
                    @Override
                    public TableCell<Meeting_Patient_Object, Boolean> call(TableColumn<Meeting_Patient_Object, Boolean> p) {
                        return new BooleanCell();
                    }
                });

    }


    private class BooleanCell extends TableCell<Meeting_Patient_Object, Boolean> {
        private CheckBox checkBox;
        public BooleanCell() {
            checkBox = new CheckBox();
            //checkBox.setDisable(true);
            checkBox.setSelected(false);
            checkBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(isEditing())
                        commitEdit(newValue == null ? false : newValue);
                    if (checkBox.isSelected()) {
                        numOfAttending++;
                        TotalArrived.setText(String.valueOf(numOfAttending-1));
                    }
                    else {
                        numOfAttending--;
                        TotalArrived.setText(String.valueOf(numOfAttending-1));
                    }
                }
            });
            this.setGraphic(checkBox);
            this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
            this.setEditable(true);
        }
        @Override
        public void startEdit() {
            super.startEdit();
            if (isEmpty()) {
                return;
            }
            checkBox.setDisable(false);
            checkBox.requestFocus();
        }
        @Override
        public void cancelEdit() {
            super.cancelEdit();
            checkBox.setDisable(true);
        }
        public void commitEdit(Boolean value) {
            super.commitEdit(value);
            checkBox.setDisable(true);
        }
        @Override
        public void updateItem(Boolean item, boolean empty) {
            super.updateItem(item, empty);
            if (!isEmpty()) {
                checkBox.setSelected(item);

            }
        }

    }



    public void showCallander() {
        datePicker = new DatePicker(LocalDate.now());
        datePicker.setPrefWidth(500);
        datePicker.setPrefHeight(500);
                final Callback<DatePicker, DateCell> dayCellFactory =
                new Callback<DatePicker, DateCell>() {
                    @Override
                    public DateCell call(final DatePicker datePicker) {
                        return new DateCell() {
                            @Override
                            public void updateItem(LocalDate item, boolean empty) {
                                super.updateItem(item, empty);
                                if (datesMarkedAsHolidays.contains(item))
                                {
                                    setStyle("-fx-background-color: #ffc0cb;");
                                }

                                if (item.getDayOfWeek() == DayOfWeek.SUNDAY //
                                || item.getDayOfWeek() == DayOfWeek.TUESDAY //
                                || item.getDayOfWeek() == DayOfWeek.THURSDAY
                                ||  item.getDayOfWeek() == DayOfWeek.FRIDAY
                                || item.getDayOfWeek() == DayOfWeek.SATURDAY) {
                            //setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");

                        }

                            }
                        };
                    }
                };
        datePicker.setDayCellFactory(dayCellFactory);
        datePickerSkin = new DatePickerSkin(datePicker);

        Node popupContent = datePickerSkin.getPopupContent();
        //add the calander
        calendar.add(popupContent, 1, 1, 3, 3);
        //get the day
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            selectedDate = newValue;
            dateOfToday.setText(selectedDate.toString());
            //System.out.println("New Value: " + newValue);
            try {
                table.getItems().clear();
                showMeetings();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

    public void Mark(ActionEvent actionEvent) {
        System.out.println("the date is"+markedAsHoliday);
        markedAsHoliday =selectedDate;
        datesMarkedAsHolidays.add(markedAsHoliday);
        showCallander();
    }

    public void watchcalendar (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("   ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("CalendarWindow.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        CalendarWindow calendarWindow = fxmlLoader.getController();
        calendarWindow.lanch(stage, scene, content,rightMenu);
    }
    public void Addmeeting (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("   ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("AddMeeting.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        AddMeeting addMeeting = fxmlLoader.getController();
        addMeeting.lanch(stage, scene, content,rightMenu);
    }

    public void deleteMeeting(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (table.getSelectionModel().getSelectedItem() != null) {
            Meeting_Patient_Object selectedDetails = (Meeting_Patient_Object) table.getSelectionModel().getSelectedItem();
            meetingCenterData.removeMeeting(Integer.parseInt(selectedDetails.getId()));
        }
        table.getItems().clear();
        showMeetings();
    }
}
