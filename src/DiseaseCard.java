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

public class DiseaseCard {

    public Disease disease;
    public TextField disease_add;
    public Stage stage;
    public Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public javafx.scene.control.TableView table;
    @FXML
    public TableColumn<Column_Obj, String> disease_col;

    public void lanch(Stage stageF, Scene sceneF, GridPane contentF, GridPane rightMenuF) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu = rightMenuF;
        showDisease();
    }

    public void showDisease() throws SQLException, ClassNotFoundException {
        disease = new Disease("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        ArrayList<String> Disease_list = disease.getDisease();
        disease_col.setCellValueFactory(new PropertyValueFactory<Column_Obj,String>("name"));
        ObservableList<Column_Obj> data = FXCollections.observableArrayList();

        for( String city: Disease_list) {
            Column_Obj column_info = new Column_Obj(city);
            data.add(column_info);
            table.setItems(data);
        }
        disease.shutdown();
    }

    public void addNewDisease(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        disease = new Disease("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        int i = disease.getDisease().size();
        disease.addNewDisease(i+1,disease_add.getText());
        showDisease();
    }
}
