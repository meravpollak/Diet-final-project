import javafx.beans.property.*;

import java.time.format.DateTimeFormatter;

import java.time.LocalDate;

public class CardDetails {


    //constructor
    public CardDetails(String id, String firstName, String lastName,  String tel, String email, String address, String city) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setCity(city);
        setTel(tel);
        setEmail(email);
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
    private final StringProperty tel = new SimpleStringProperty(this, "tel");
    public StringProperty telProperty() {
        return tel ;
    }
    public final String getTel() {
        return telProperty().get();
    }
    public final void setTel(String tel) {
        telProperty().set(tel);
    }


    //email
    private final StringProperty email = new SimpleStringProperty(this, "email");
    public StringProperty emailProperty() {
        return email ;
    }
    public final String getEmail() {
        return emailProperty().get();
    }
    public final void setEmail(String email) {
        emailProperty().set(email);
    }

    //address
    private final StringProperty address = new SimpleStringProperty(this, "address");
    public StringProperty addressProperty() {
        return address ;
    }
    public final String getAddress() {
        return addressProperty().get();
    }
    public final void setAddress(String address) {
        addressProperty().set(address);
    }

    //city
    private final StringProperty city = new SimpleStringProperty(this, "city");
    public StringProperty cityProperty() {
        return city ;
    }
    public final String getCity() {
        return cityProperty().get();
    }
    public final void setCity(String city) {
        cityProperty().set(city);
    }



}
