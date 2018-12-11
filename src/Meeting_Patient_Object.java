import javafx.beans.property.*;

import java.sql.Time;
import java.time.LocalDate;

public class Meeting_Patient_Object {

    public Meeting_Patient_Object(String id, LocalDate date, String time, String firstName, String lastName, Integer tel, Integer arrived, Integer newPatient)
{
    setId(id);
    setDate(date);
    setFirstName(firstName);
    setLastName(lastName);
    setTel(tel);
    setTime(time);
    setArrived(arrived);
    setNewP(newPatient);
}

    //id
    private final StringProperty id = new SimpleStringProperty(this, "id");
    public StringProperty idProperty() {
        return id ;
    }
    public final String getId() {
        return idProperty().get();
    }
    public final void setId(String id) {
        idProperty().set(id);
    }

    //date
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty(this, "date");
    public ObjectProperty<LocalDate> dateOpenCardProperty() {
        return date ;
    }
    public final LocalDate getDate() {
        return dateOpenCardProperty().get();
    }
    public final void setDate(LocalDate date) {
        dateOpenCardProperty().set(date);
    }

    //first name
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName");
    public StringProperty firstNameProperty() {
        return firstName ;
    }
    public final String getFirstName() {
        return firstNameProperty().get();
    }
    public final void setFirstName(String firstName) {
        firstNameProperty().set(firstName);
    }

    //last name
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName");
    public StringProperty lastNameProperty() {
        return lastName ;
    }
    public final String getLastName() {
        return lastNameProperty().get();
    }
    public final void setLastName(String lastName) {
        lastNameProperty().set(lastName);
    }

    //tel
    private final IntegerProperty tel = new SimpleIntegerProperty(this, "tel");
    public IntegerProperty telProperty() {
        return tel ;
    }
    public final Integer getTel() {
        return telProperty().get();
    }
    public final void setTel(Integer tel) {
        telProperty().set(tel);
    }

    //time
    private final StringProperty time = new SimpleStringProperty(this, "time");
    public StringProperty timeProperty() {
        return time ;
    }
    public final String getTime() {
        return timeProperty().get();
    }
    public final void setTime(String time) {
        timeProperty().set(time);
    }

    //arrived
    private final IntegerProperty arrived = new SimpleIntegerProperty(this, "arrived");
    public IntegerProperty arrivedProperty() {
        return arrived ;
    }
    public final Integer getArrived() {
        return arrivedProperty().get();
    }
    public final void setArrived(Integer arr) {
        arrivedProperty().set(arr);
    }
//
//
//    private final BooleanProperty arrived = new SimpleBooleanProperty(this, "arrived");
//    public BooleanProperty arrivedProperty() {
//        return arrived ;
//    }
//    public final Boolean getArrived() {
//        return arrivedProperty().get();
//    }
//    public final void setArrived(Boolean arr) {
//        arrivedProperty().set(arr);
//    }


    private final IntegerProperty newP = new SimpleIntegerProperty(this, "new");
    public IntegerProperty newProperty() {
        return newP ;
    }
    public final Integer getNewP() {
        return newProperty().get();
    }
    public final void setNewP(Integer newP1) {
        newProperty().set(newP1);
    }
}