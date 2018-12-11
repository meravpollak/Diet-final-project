import javafx.beans.property.*;

import java.sql.Time;
import java.time.LocalDate;

public class RecivableObject {

    public RecivableObject(Integer id, String firstName, String lastName, Integer tel, LocalDate date, Integer sum) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setTel(tel);
        setDate(date);
        setSum(sum);
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

    //tel
    private final IntegerProperty sum = new  SimpleIntegerProperty(this, "sum");
    public IntegerProperty sumProperty() {
        return sum ;
    }
    public final Integer getSum() {
        return sumProperty().get();
    }
    public final void setSum(Integer s) {
        sumProperty().set(s);
    }


}
