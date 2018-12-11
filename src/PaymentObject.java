import javafx.beans.property.*;

import java.sql.Time;
import java.time.LocalDate;

public class PaymentObject {

    public PaymentObject(Integer id, LocalDate date, String firstName, String lastName, Integer pay, Integer credit, Integer cash, Integer chek)
    {
        setId(id);
        setDate(date);
        setFirstName(firstName);
        setLastName(lastName);
        setPay(pay);
        setcredit(credit);
        setcash(cash);
        setchek(chek);
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

    //pay
    private final IntegerProperty pay = new SimpleIntegerProperty(this, "pay");
    public IntegerProperty payProperty() {
        return pay ;
    }
    public final Integer getPay() {
        return payProperty().get();
    }
    public final void setPay(Integer pay) {
        payProperty().set(pay);
    }

    //credit
    private final IntegerProperty credit = new SimpleIntegerProperty(this, "credit");
    public IntegerProperty creditProperty() {
        return credit ;
    }
    public final Integer getcredit() {
        return creditProperty().get();
    }
    public final void setcredit(Integer credit) {
        creditProperty().set(credit);
    }

    //cash
    private final IntegerProperty cash = new SimpleIntegerProperty(this, "cash");
    public IntegerProperty cashProperty() {
        return cash ;
    }
    public final Integer getcash() {
        return cashProperty().get();
    }
    public final void setcash(Integer cash) {
        cashProperty().set(cash);
    }

    //chek
    private final IntegerProperty chek = new SimpleIntegerProperty(this, "chek");
    public IntegerProperty chekProperty() {
        return chek ;
    }
    public final Integer getchek() {
        return chekProperty().get();
    }
    public final void setchek(Integer chek) {
        chekProperty().set(chek);
    }


}