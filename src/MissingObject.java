import javafx.beans.property.*;

import java.sql.Time;
import java.time.LocalDate;

public class MissingObject {
    //constructor
    public MissingObject(Integer id, String firstName, String lastName, Integer tel, LocalDate date, Time time, Integer week) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setTel(tel);
        setDate(date);
        setTime(time);
        setweek(week);
    }

    //id
    private final IntegerProperty id = new SimpleIntegerProperty(this, "id");
    public IntegerProperty idProperty() {
        return id ;
    }
    public final Integer getId() {
        return idProperty().get();
    }
    public final void setId(Integer id) {
        idProperty().set(id);
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
    private final ObjectProperty<Time> time = new SimpleObjectProperty(this, "time");
    public ObjectProperty<Time> timeProperty() {
        return time ;
    }
    public final Time getTime() {
        return timeProperty().get();
    }
    public final void setTime(Time time) {
        timeProperty().set(time);
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

    //week
    private final IntegerProperty week = new SimpleIntegerProperty(this, "week");
    public IntegerProperty weekProperty() {
        return week ;
    }
    public final Integer getweek() {
        return weekProperty().get();
    }
    public final void setweek(Integer week) {
        weekProperty().set(week);
    }


}