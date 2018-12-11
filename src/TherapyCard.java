import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class TherapyCard {
    public Therapy therapy;
    public TextField therapy_add;
    public Stage stage;
    public Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public javafx.scene.control.TableView table;
    @FXML
    public TableColumn<Column_Obj, String> therapy_col;

    public void lanch(Stage stageF, Scene sceneF, GridPane contentF, GridPane rightMenuF) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu = rightMenuF;
        showTherapy();
    }

    public void showTherapy() throws SQLException, ClassNotFoundException {
        therapy = new Therapy("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        ArrayList<String> Therapy_list = therapy.getTherapy();
        therapy_col.setCellValueFactory(new PropertyValueFactory<Column_Obj,String>("name"));
        ObservableList<Column_Obj> data = FXCollections.observableArrayList();

        for( String therapy: Therapy_list) {
            Column_Obj column_info = new Column_Obj(therapy);
            data.add(column_info);
            table.setItems(data);
        }
        therapy.shutdown();
    }

    public void addNewTherapy(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        therapy = new Therapy("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        int i = therapy.getTherapy().size();
        therapy.addNewTherapy(i+1,therapy_add.getText());
        showTherapy();
    }
}
