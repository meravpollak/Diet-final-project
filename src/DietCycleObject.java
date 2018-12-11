import javafx.beans.property.*;
import javafx.scene.control.CheckBox;

import java.time.LocalDate;

public class DietCycleObject {
    //constructor
    public DietCycleObject(Integer id, Integer treat, Integer week, LocalDate date, Double weight, Double diff, Double diet, Integer days, Integer arrived , String marks) {
        setId(id);
        setTreat(treat);
        setWeek(week);
        setDate(date);
        setWeight(weight);
        setDiff(diff);
        setDiet(diet);
        setDays(days);
        setarrived(arrived);
        setmarks(marks);
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

    //treat
    private final IntegerProperty treat = new SimpleIntegerProperty(this, "treat");
    public IntegerProperty treatProperty() {
        return treat ;
    }
    public final Integer gettreat() {
        return treatProperty().get();
    }
    public final void setTreat(Integer treat) {
        treatProperty().set(treat);
    }

    //week
    private final IntegerProperty week = new SimpleIntegerProperty(this, "week");
    public IntegerProperty WeekProperty() {
        return week ;
    }
    public final Integer getWeek() {
        return WeekProperty().get();
    }
    public final void setWeek(Integer week) {
        WeekProperty().set(week);
    }

    //date
    private final ObjectProperty<LocalDate> date = new SimpleObjectProperty<LocalDate>(this, "date");
    public ObjectProperty<LocalDate> DateProperty() {
        return date ;
    }
    public final LocalDate getDate() {
        return DateProperty().get();
    }
    public final void setDate(LocalDate date) {
        DateProperty().set(date);
    }

    //weight
    private final DoubleProperty weight = new SimpleDoubleProperty(this, "weight");
    public DoubleProperty weightProperty() {
        return weight ;
    }
    public final Double getWeight() {
        return weightProperty().get();
    }
    public final void setWeight(Double weight) {
        weightProperty().set(weight);
    }

    //diff
    private final DoubleProperty diff = new SimpleDoubleProperty(this, "diff");
    public DoubleProperty diffProperty() {
        return diff ;
    }
    public final Double getDiff() {
        return diffProperty().get();
    }
    public final void setDiff(Double diff) {
        diffProperty().set(diff);
    }

    //diet
    private final DoubleProperty diet = new SimpleDoubleProperty(this, "diet");
    public DoubleProperty dietProperty() {
        return diet ;
    }
    public final Double getDiet() {
        return dietProperty().get();
    }
    public final void setDiet(Double diet) {
        dietProperty().set(diet);
    }

    //days
    private final IntegerProperty days = new SimpleIntegerProperty(this, "days");
    public IntegerProperty daysProperty() {
        return days ;
    }
    public final Integer getDays() {
        return daysProperty().get();
    }
    public final void setDays(Integer days) {
        daysProperty().set(days);
    }

    //arrived
    private final IntegerProperty arrived = new SimpleIntegerProperty(this, "arrived");
    public IntegerProperty arrivedProperty() {
        return arrived ;
    }
    public final Integer getarrived() {
        return arrivedProperty().get();
    }
    public final void setarrived(Integer arrived) {
        arrivedProperty().set(arrived);
    }

    //marks
    private final StringProperty marks = new SimpleStringProperty(this, "marks");
    public StringProperty marksProperty() {
        return marks ;
    }
    public final String getmarks() {
        return marksProperty().get();
    }
    public final void setmarks(String marks) {
        marksProperty().set(marks);
    }

}
