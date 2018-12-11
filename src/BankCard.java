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

public class BankCard {
    public Banks banks;
    public TextField bank_add;
    public Stage stage;
    public Scene scene;
    public GridPane content;
    public GridPane rightMenu;
    public javafx.scene.control.TableView table;
    @FXML
    public TableColumn<Column_Obj, String> bank_col;

    public void lanch(Stage stageF, Scene sceneF, GridPane contentF, GridPane rightMenuF) throws SQLException, ClassNotFoundException {
        stage=stageF;
        scene=sceneF;
        content=contentF;
        rightMenu = rightMenuF;
        showBanks();
    }

    public void showBanks() throws SQLException, ClassNotFoundException {
        banks = new Banks("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        ArrayList<String> Cities_list = banks.getBanks();
        bank_col.setCellValueFactory(new PropertyValueFactory<Column_Obj,String>("name"));
        ObservableList<Column_Obj> data = FXCollections.observableArrayList();

        for( String city: Cities_list) {
            Column_Obj column_info = new Column_Obj(city);
            data.add(column_info);
            table.setItems(data);
        }
        banks.shutdown();
    }

    public void addNewBank(ActionEvent event) throws IOException, SQLException, ClassNotFoundException {
        banks = new Banks("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
        int i = banks.getBanks().size();
        banks.addNewBank(i+1,bank_add.getText());
        showBanks();
    }
}
