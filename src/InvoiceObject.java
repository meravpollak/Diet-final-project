import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import java.time.format.DateTimeFormatter;

import java.time.LocalDate;
import java.util.Date;

public class InvoiceObject {

    //constructor
    public InvoiceObject(Integer id, Integer code, LocalDate date, Integer sum, String howPay, String moreDetails, String marks) {
        setId(id);
        setSum(sum);
        setcode(code);
        setdate(date);
        setHowPay(howPay);
        setMoreDetails(moreDetails);
        setMarks(marks);
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

    //howPay
    private final StringProperty howPay = new SimpleStringProperty(this, "howPay");
    public StringProperty howPay() {
        return howPay ;
    }
    public final String getHowPay() {
        return howPay().get();
    }
    public final void setHowPay(String howPay) {
        howPay().set(howPay);
    }

    //moreDetails
    private final StringProperty moreDetails = new SimpleStringProperty(this, "moreDetails");
    public StringProperty moreDetailsProperty() {
        return moreDetails ;
    }
    public final String getMoreDetails() {
        return moreDetailsProperty().get();
    }
    public final void setMoreDetails(String moreDetails) {
        moreDetailsProperty().set(moreDetails);
    }

    //marks
    private final StringProperty marks = new SimpleStringProperty(this, "marks");
    public StringProperty marksProperty() {
        return marks ;
    }
    public final String getMarks() {
        return marksProperty().get();
    }
    public final void setMarks(String marks) {
        marksProperty().set(marks);
    }

    }
