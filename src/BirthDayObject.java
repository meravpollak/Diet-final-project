import javafx.beans.property.*;

import java.time.LocalDate;

public class BirthDayObject {
    public BirthDayObject(Integer id, String firstName, String lastName, Integer tel ,Integer age  ) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setTel(tel);
        setAge(age);
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

    //firstName
    private final StringProperty firstName = new SimpleStringProperty(this, "firstName");
    public StringProperty firstNameProperty() {
        return firstName ;
    }
    public final String getFirstName() {
        return firstNameProperty().get();
    }
    public final void setFirstName(String name) {
        firstNameProperty().set(name);
    }

    //lastName
    private final StringProperty lastName = new SimpleStringProperty(this, "lastName");
    public StringProperty lastNameProperty() {
        return lastName ;
    }
    public final String getLastName() {
        return lastNameProperty().get();
    }
    public final void setLastName(String name) {
        lastNameProperty().set(name);
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

    //Age
    private final IntegerProperty age = new SimpleIntegerProperty(this, "age");
    public IntegerProperty ageProperty() {
        return age ;
    }
    public final Integer getAge() {
        return ageProperty().get();
    }
    public final void setAge(Integer age) {
        ageProperty().set(age);
    }

}
