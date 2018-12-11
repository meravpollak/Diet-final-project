import javafx.beans.property.*;

import java.sql.Time;
import java.time.LocalDate;

public class PeopleUnderMemObject {

    public PeopleUnderMemObject(Integer id, String firstName, String lastName, Integer tel, LocalDate birthday, String sex) {
    setId(id);
    setFirstName(firstName);
    setLastName(lastName);
    setTel(tel);
    setBirthday(birthday);
    setSex(sex);
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
    private final ObjectProperty<LocalDate> birthday = new SimpleObjectProperty(this, "birthday");
    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday ;
    }
    public final LocalDate getBirthday() {
        return birthdayProperty().get();
    }
    public final void setBirthday(LocalDate birthday) {
        birthdayProperty().set(birthday);
    }

    //sex
    private final StringProperty sex = new SimpleStringProperty(this, "sex");
    public StringProperty sexProperty() {
        return sex ;
    }
    public final String getSex() {
        return sexProperty().get();
    }
    public final void setSex(String sex) {
        sexProperty().set(sex);
    }

}