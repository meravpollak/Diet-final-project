import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class RemoveMeeting {
    private Stage stage;
    private Scene scene;
    public GridPane rightMenu;
    public GridPane content;
    public Patient current_patient;


    public void lanch(Stage stageF, Scene sceneF, GridPane contentF, GridPane rightMenuF, Patient patient) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu = rightMenuF;
        current_patient=patient;

    }

    public void watchcalendar (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("     ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("CalendarWindow.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        CalendarWindow calendarWindow = fxmlLoader.getController();
        calendarWindow.lanch(stage, scene, content,rightMenu);
        System.out.println("     ");
    }
    public void Addmeeting (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("AddMeeting.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        AddMeeting addMeeting = fxmlLoader.getController();
        addMeeting.lanch(stage, scene, content,rightMenu);
        System.out.println("     ");
    }
    public void MarkDays (ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        System.out.println("    ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("MarkDaysCal.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        MarkDaysCal markDaysCal = fxmlLoader.getController();
        markDaysCal.lanch(stage, scene, content,rightMenu , current_patient);
        System.out.println("     ");
    }

    public void RemoveMeeting(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        System.out.println("    ");
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent s = fxmlLoader.load(getClass().getResource("RemoveMeeting.fxml").openStream());
        content.getChildren().clear();
        content.getChildren().addAll(s);
        RemoveMeeting removeMeeting = fxmlLoader.getController();
        removeMeeting.lanch(stage, scene, content,rightMenu, current_patient);

    }
}
