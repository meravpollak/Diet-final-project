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

public class DietMenu {

    public DietMenuSuggest dietMenuSuggest;
    public TextField diet_add;
    public Stage stage;
    public Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public javafx.scene.control.TableView table;
    @FXML
    public TableColumn<Column_Obj, String> diet_col;

    public void lanch(Stage stageF, Scene sceneF, GridPane contentF, GridPane rightMenuF) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu = rightMenuF;
        showdiet();
    }

    public void showdiet() throws SQLException, ClassNotFoundException {
        dietMenuSuggest = new DietMenuSuggest("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        ArrayList<String> Foods_list = dietMenuSuggest.getDiets();
        diet_col.setCellValueFactory(new PropertyValueFactory<Column_Obj,String>("name"));
        ObservableList<Column_Obj> data = FXCollections.observableArrayList();

        for( String city: Foods_list) {
            Column_Obj column_info = new Column_Obj(city);
            data.add(column_info);
            table.setItems(data);
        }
        dietMenuSuggest.shutdown();
    }

    public void addNewDiet(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        dietMenuSuggest = new DietMenuSuggest("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        int i = dietMenuSuggest.getDiets().size();
        dietMenuSuggest.addNewDiet(i+1,diet_add.getText());
        showdiet();
    }
}