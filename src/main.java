

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.TableView ;
import javafx.scene.control.TableColumn ;
import javafx.scene.control.cell.PropertyValueFactory ;

import java.awt.*;
import java.sql.SQLException;
import java.util.Optional;

public class main extends Application {

    public PatientDataAccessor PatientdataAccessor;
    public UserDataAccessor UserdataAccessor;
    private Parent parent;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {

        final int initWidth =1600;      //initial width
        final int initHeight = 900;    //initial height
        final Pane root = new Pane();   //necessary evil

        FXMLLoader fxmlLoader = new FXMLLoader();
        // Parent root = fxmlLoader.load(getClass().getResource("Login.fxml").openStream());
        Pane controller =  fxmlLoader.load(getClass().getResource("Login.fxml").openStream());//initial view
        controller.setPrefWidth(initWidth);     //if not initialized
        controller.setPrefHeight(initHeight);   //if not initialized
        root.getChildren().add(controller);     //necessary evil

        Scale scale = new Scale(1, 1, 0, 0);
        scale.xProperty().bind(root.widthProperty().divide(initWidth));     //must match with the one in the controller
        scale.yProperty().bind(root.heightProperty().divide(initHeight));   //must match with the one in the controller
        root.getTransforms().add(scale);


        // Parent root = fxmlLoader.load(getClass().getResource("Login.fxml").openStream());
        // root = fxmlLoader.load(getClass().getResource("Login.fxml").openStream());
        Scene scene = new Scene(root,1600,900);

        scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
        Login login = fxmlLoader.getController();
        login.lanchLogin(primaryStage, scene);

        //add listener for the use of scene.setRoot()
        scene.rootProperty().addListener(new ChangeListener<Parent>(){
            @Override public void changed(ObservableValue<? extends Parent> arg0, Parent oldValue, Parent newValue){
                scene.rootProperty().removeListener(this);
                scene.setRoot(root);
                ((Region)newValue).setPrefWidth(initWidth);     //make sure is a Region!
                ((Region)newValue).setPrefHeight(initHeight);   //make sure is a Region!
                root.getChildren().clear();
                root.getChildren().add(newValue);
                scene.rootProperty().addListener(this);
            }
        });

        //previous
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        Parent root = fxmlLoader.load(getClass().getResource("Login.fxml").openStream());
//
//        Scene scene = new Scene(root, 800, 700);
//        scene.getStylesheets().add(getClass().getResource("ViewStyle.css").toExternalForm());
//        primaryStage.setScene(scene);
//
//        Login login = fxmlLoader.getController();
//        login.setResizeEvent(scene);
//
//        SetStageCloseEvent(primaryStage);
//        primaryStage.show();
//        primaryStage.setFullScreen(true);

//        javafx.geometry.Rectangle2D Visualbounds = Screen.getPrimary().getVisualBounds();
//        double heigh = Visualbounds.getHeight();
//        double width = Visualbounds.getWidth();
//        System.out.println("h"+" "+heigh);
//        System.out.println("w"+" "+width);



    }
    private void SetStageCloseEvent(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent windowEvent) {
                //להוריד בסוף מהערה!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setHeaderText("לבצע יציאה?");
//                alert.setTitle("יציאה");
//                Optional<ButtonType> result = alert.showAndWait();
//                if (result.get() == ButtonType.OK){
//                    // ... user chose OK
//                    // Close program
//                } else {
//                    // ... user chose CANCEL or closed the dialog
//                    windowEvent.consume();
//                }
            }
        });
    }


    @Override
    public void stop() throws Exception {
        if (PatientdataAccessor != null) {
            PatientdataAccessor.shutdown();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

