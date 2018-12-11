import javafx.beans.property.*;

import java.time.LocalDate;

public class NeedToPayObject {
    //constructor
    public NeedToPayObject(Integer id, Integer code, LocalDate date, Integer sum, Integer tel, String first, String last) {
        setId(id);
        setSum(sum);
        setcode(code);
        setdate(date);
        setFirstName(first);
        setLastName(last);
        setTel(tel);
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

    //code
    private final IntegerProperty code = new SimpleIntegerProperty(this, "code");
    public IntegerProperty codeProperty() {
        return code ;
    }
    public final Integer getCode() {
        return codeProperty().get();
    }
    public final void setcode(Integer code) {
        codeProperty().set(code);
    }

    //date
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<LocalDate>(this, "date");
    public ObjectProperty<LocalDate> dateProperty() {
        return date ;
    }
    public final LocalDate getdate() {
        return dateProperty().get();
    }
    public final void setdate(LocalDate date) {
        dateProperty().set(date);
    }

    //sum
    private final IntegerProperty sum = new SimpleIntegerProperty(this, "sum");
    public IntegerProperty sumProperty() {
        return sum ;
    }
    public final Integer getSum() {
        return sumProperty().get();
    }
    public final void setSum(Integer sum) {
        sumProperty().set(sum);
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
}
