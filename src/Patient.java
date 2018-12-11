import javafx.beans.property.*;

import java.time.format.DateTimeFormatter;

import java.time.LocalDate;

public class Patient {

    //constructor
    public Patient() {}

    //constructor
    public Patient(Integer id, String firstName, String lastName, String address, String city , Integer tel, String email , LocalDate birthDay, String sex, String familyStatus, Integer age, String job, LocalDate dateOpenCard, Double currentWeight, Double pastHighWeight, Double height, Double weight, String doctor, Double startWeightCycle, String marks) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
        setAddress(address);
        setCity(city);
        setTel(tel);
        setEmail(email);
        setBirthDay(birthDay);
        setSex(sex);
        setFamilyStatusProperty(familyStatus);
        setAge(age);
        setJob(job);
        setDateOpenCard(dateOpenCard);
        setCurrentWeight(currentWeight);
        setPastHighWeightProperty(pastHighWeight);
        setHeightProperty(height);
        setWeightProperty(weight);
        setDoctor(doctor);
        setStartWeightCycleProperty(startWeightCycle);
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

    //BirthDay
    private final ObjectProperty<LocalDate> birthDay = new SimpleObjectProperty<LocalDate>(this, "birthDay");
    public ObjectProperty<LocalDate> birthDayProperty() {
        return birthDay ;
    }
    public final LocalDate getBirthDay() {
        return birthDayProperty().get();
    }
    public final void setBirthDay(LocalDate birthDay) {
        birthDayProperty().set(birthDay);
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

    //FamilyStatus
    private final StringProperty familyStatus = new SimpleStringProperty(this, "familyStatus");
    public StringProperty familyStatusProperty() {
        return familyStatus ;
    }
    public final String getFamilyStatus() {
        return familyStatusProperty().get();
    }
    public final void setFamilyStatusProperty(String familyStatus) {
        familyStatusProperty().set(familyStatus);
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

    //Job
    private final StringProperty job = new SimpleStringProperty(this, "job");
    public StringProperty jobProperty() {
        return job ;
    }
    public final String getJob() {
        return jobProperty().get();
    }
    public final void setJob(String job) {
        jobProperty().set(job);
    }

    //dateOpenCard
    private final ObjectProperty<LocalDate> dateOpenCard = new SimpleObjectProperty(this, "dateOpenCard");
    public ObjectProperty<LocalDate> dateOpenCardProperty() {
        return dateOpenCard ;
    }
    public final LocalDate getDateOpenCard() {
        return dateOpenCardProperty().get();
    }
    public final void setDateOpenCard(LocalDate dateOpenCard) {
        dateOpenCardProperty().set(dateOpenCard);
    }

    //currentWeight
    private final DoubleProperty currentWeight = new SimpleDoubleProperty(this, "currentWeight");
    public DoubleProperty currentWeightProperty() {
        return currentWeight ;
    }
    public final Double getCurrentWeight() {
        return currentWeightProperty().get();
    }
    public final void setCurrentWeight(Double currentWeight) {
        currentWeightProperty().set(currentWeight);
    }

    //pastHighWeight
    private final DoubleProperty pastHighWeight = new SimpleDoubleProperty(this, "pastHighWeight");
    public DoubleProperty pastHighWeightProperty() {
        return pastHighWeight ;
    }
    public final Double getPastHighWeightProperty() {
        return pastHighWeightProperty().get();
    }
    public final void setPastHighWeightProperty(Double pastHighWeight) {
        pastHighWeightProperty().set(pastHighWeight);
    }

    //height
    private final DoubleProperty height = new SimpleDoubleProperty(this, "height");
    public DoubleProperty heightProperty() {
        return height ;
    }
    public final Double getHeightProperty() {
        return heightProperty().get();
    }
    public final void setHeightProperty(Double height) {
        heightProperty().set(height);
    }

    //weight
    private final DoubleProperty weight = new SimpleDoubleProperty(this, "weight");
    public DoubleProperty weightProperty() {
        return weight ;
    }
    public final Double getWeightProperty() {
        return weightProperty().get();
    }
    public final void setWeightProperty(Double weight) {
        weightProperty().set(weight);
    }

    //doctor
    private final StringProperty doctor = new SimpleStringProperty(this, "doctor");
    public StringProperty doctorProperty() {
        return doctor ;
    }
    public final String getDoctor() {
        return doctorProperty().get();
    }
    public final void setDoctor(String doctor) {
        doctorProperty().set(doctor);
    }

    //startWeightCycle
    private final DoubleProperty startWeightCycle = new SimpleDoubleProperty(this, "startWeightCycle");
    public DoubleProperty startWeightCycleProperty() {
        return startWeightCycle ;
    }
    public final Double getStartWeightCycleProperty() {
        return startWeightCycleProperty().get();
    }
    public final void setStartWeightCycleProperty(Double startWeightCycle) {
        startWeightCycleProperty().set(startWeightCycle);
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
