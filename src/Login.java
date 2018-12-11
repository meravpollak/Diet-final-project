import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.geom.Rectangle2D;
import java.sql.SQLException;
import java.lang.ClassNotFoundException;

import java.io.IOException;
import java.util.Optional;

public class Login {

    @FXML
    public TextField UserName;
    public PasswordField Password;
    public BorderPane border;
    public PatientDataAccessor PatientdataAccessor;
    public UserDataAccessor UserdataAccessor;
    private Stage stage;
    private Parent parent;
    private Scene scene;
    public Text mainLabel;

    public Login()
    {

    }
    public void lanchLogin(Stage stageFrommain, Scene sceneFromMain) throws Exception
    {
        scene =  sceneFromMain;
        scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
        //
        //shadow

        DropShadow shadow = new DropShadow();
        shadow.setBlurType(BlurType.GAUSSIAN);
        shadow.setColor(Color.GHOSTWHITE);
        shadow.setHeight(5);
        shadow.setWidth(5);
        shadow.setRadius(5);
        shadow.setOffsetX(3.0);
        shadow.setOffsetY(2.0);
        shadow.setSpread(12);
        mainLabel.setEffect(shadow);

//        BackgroundSize bsize = new BackgroundSize(BackgroundSize.AUTO,BackgroundSize.AUTO, false,false, true, false);
//        Image image = new Image("pic.png");
//        border.setBackground(new Background( new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.CENTER,bsize)));

        /*-fx-background-image: url("pic.png");*/
        /*-fx-background-repeat: stretch;*/
        /*-fx-background-size: cover;*/
        /*-fx-background-size: 1600 900;*/


        //
        this.stage = stageFrommain;
        stage.setTitle("חרמון מן");
        stage.setScene(scene);
        stage.setResizable(true);
        setResizeEvent(scene);
        stage.hide();
        stage.show();

        stage.setFullScreen(true);

    }

    public void connect(ActionEvent event) throws Exception {
        String userName = UserName.getText();
        String password = Password.getText();
        UserdataAccessor = new UserDataAccessor( "com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initModality(Modality.APPLICATION_MODAL);
        alert.initOwner(stage);

            if (UserdataAccessor.checkIfUserExists(userName)==1 && UserdataAccessor.checkIfpasswordCorrent(userName,password)==1)
            {
                alert.setTitle("התחברות");
                alert.setHeaderText("ההתחברות התבצעה בהצלחה!      ");
                //alert.showAndWait();
                stop();
                FXMLLoader fxmlLoader = new FXMLLoader();
                Parent s = fxmlLoader.load(getClass().getResource("View.fxml").openStream());
                s.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
                border.getChildren().setAll(s);
                View view = fxmlLoader.getController();
                view.lanchView(stage, scene, userName, border);
            }
            else {
                alert.setTitle("שגיאה");
                alert.setHeaderText("שם המשתמש או הסיסמה לא נכונים");
                alert.showAndWait();
                stop();
            }
    }

    public void setResizeEvent(Scene scene) {

        border.prefHeightProperty().bind(scene.heightProperty());
        border.prefWidthProperty().bind(scene.heightProperty());
        scene.widthProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {

            }
        });

        scene.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) {

            }
        });
    }

    public void stop() throws Exception {
        if (UserdataAccessor != null) {
            UserdataAccessor.shutdown();
        }
    }
}

