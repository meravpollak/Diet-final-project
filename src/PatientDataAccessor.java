import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import java.sql.*;
import javax.swing.JOptionPane;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List ;
import java.util.ArrayList ;

public class PatientDataAccessor {


    private Connection connection;

    public PatientDataAccessor(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
    }

    //close connection
    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    //get all ID
    public ArrayList<Integer> getAllPatientsID() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from patient");
        ) {
            ArrayList<Integer> Ids_list = new ArrayList<>();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Ids_list.add(id);
            }
            return Ids_list;
        }
    }

    //get all ID
    public ArrayList<String> getPatientsID() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from patient");
        ) {
            ArrayList<String> Ids_list = new ArrayList<>();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Ids_list.add(Integer.toString(id));
            }
            return Ids_list;
        }
    }

    //get all ID
    public ArrayList<String> getAllNames() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from patient");
        ) {
            ArrayList<String> names_list = new ArrayList<>();
            while (rs.next()) {
                String firstName = rs.getString("firstName");
                names_list.add(firstName);

            }

            return names_list;
        }
    }

    //get all the patients
    public ArrayList<Patient> getPatientList() throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from patient");
        ) {
            ArrayList<Patient> patientList = new ArrayList<>();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String address = rs.getString("address");
                String city = rs.getString("city");
                Integer tel = rs.getInt("tel");
                String email = rs.getString("email");
                Date birthDay = rs.getDate("birthDay");
                String sex = rs.getString("sex");
                String familyStatus = rs.getString("familyStatus");
                Integer age = rs.getInt("age");
                String job = rs.getString("job");
                Date dateOpenCard = rs.getDate("dateOpenCard");
                Double currentWeight = rs.getDouble("currentWeight");
                Double pastHighWeight = rs.getDouble("pastHighWeight");
                Double height = rs.getDouble("hieght");
                Double weight = rs.getDouble("weight");
                String doctor = rs.getString("doctor");
                Double startWeightCycle = rs.getDouble("startWeightCycle");
                String marks = rs.getString("marks");

                LocalDate birthday_localdate = birthDay.toLocalDate();
                LocalDate dateOpenCard_localdate = dateOpenCard.toLocalDate();

                Patient patient = new Patient(id, firstName, lastName, address, city, tel, email, birthday_localdate, sex, familyStatus, age, job, dateOpenCard_localdate, currentWeight, pastHighWeight, height, weight, doctor, startWeightCycle, marks);
                patientList.add(patient);
            }
            return patientList;
        }
    }

    //add patient
    public void addPatient(Integer id, String firstName, String lastName, String address, String city, Integer tel, String email, LocalDate birthDay, String sex, String familyStatus, Integer age, String job, LocalDate dateOpenCard, Double currentWeight, Double pastHighWeight, Double height, Double weight, String doctor, Double startWeightCycle, String marks)
            throws SQLException {
        Statement stmnt = connection.createStatement();
        Date date_birthDay = java.sql.Date.valueOf(birthDay); //convert to date in DB
        Date date_dateOpenedCard = java.sql.Date.valueOf(dateOpenCard); //convert to date in DB
        stmnt.executeUpdate("INSERT INTO patient (id, firstName, lastName, address, city, tel, email, birthDay, sex, familyStatus, age, job, dateOpenCard, currentWeight, pastHighWeight, hieght, weight, doctor, startWeightCycle, marks) " +
                "VALUES('" + id + "','" + firstName + "','" + lastName + "','" + address + "','" + city + "','" + tel + "','" + email + "','" + date_birthDay + "','" + sex + "','" + familyStatus + "','" + age + "','" + job + "','" + date_dateOpenedCard + "','" + currentWeight + "','" + pastHighWeight + "','" + height + "','" + weight + "','" + doctor + "','" + startWeightCycle + "','" + marks + "')");
    }

    public void updatePatientDetails(Integer id, String firstName, String lastName, String address, String city, Integer tel, String email, LocalDate birthDay, String sex, String familyStatus, Integer age, String job, LocalDate dateOpenCard, Double currentWeight, Double pastHighWeight, Double height, Double weight, String doctor, Integer startWeightCycle, String marks)
            throws SQLException {
        //PreparedStatement stmnt = connection.prepareStatement("UPDATE  patient SET firstName = ? AND lastName = ? AND lastName = ? AND address = ? AND city = ? AND tel = ? AND email = ? AND birthday = ? AND sex = ? AND familyStatus = ? AND age = ? AND job = ? AND dateOpenCard = ? AND currentWeight = ? AND pastHighWeight = ? AND height = ? AND weight = ? AND doctor = ? AND startWeightCycle = ? AND marks = ? WHERE id = ? ");
        PreparedStatement stmnt = connection.prepareStatement("UPDATE  patient SET firstName = ? WHERE id = ? ");
        stmnt.setString(1, firstName);
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET lastName = ? WHERE id = ? ");
        stmnt.setString(1, lastName);
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET address = ? WHERE id = ? ");
        stmnt.setString(1, address);
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET city = ? WHERE id = ? ");
        stmnt.setString(1, city);
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET tel = ? WHERE id = ? ");
        stmnt.setString(1, Integer.toString(tel));
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET email = ? WHERE id = ? ");
        stmnt.setString(1, email);
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET birthDay = ? WHERE id = ? ");
        stmnt.setDate(1, java.sql.Date.valueOf(birthDay));
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET sex = ? WHERE id = ? ");
        stmnt.setString(1, sex);
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET familyStatus = ? WHERE id = ? ");
        stmnt.setString(1, familyStatus);
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET age = ? WHERE id = ? ");
        stmnt.setString(1, Integer.toString(age));
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET job = ? WHERE id = ? ");
        stmnt.setString(1, job);
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET dateOpenCard = ? WHERE id = ? ");
        stmnt.setDate(1, java.sql.Date.valueOf(dateOpenCard));
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET currentWeight = ? WHERE id = ? ");
        stmnt.setString(1, Double.toString(currentWeight));
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET pastHighWeight = ? WHERE id = ? ");
        stmnt.setString(1, Double.toString(pastHighWeight));
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET hieght = ? WHERE id = ? ");
        stmnt.setString(1, Double.toString(height));
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET weight = ? WHERE id = ? ");
        stmnt.setString(1, Double.toString(weight));
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET doctor = ? WHERE id = ? ");
        stmnt.setString(1, doctor);
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET startWeightCycle = ? WHERE id = ? ");
        stmnt.setString(1, Double.toString(startWeightCycle));
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
        stmnt = connection.prepareStatement("UPDATE  patient SET marks = ? WHERE id = ? ");
        stmnt.setString(1, marks);
        stmnt.setString(2, Integer.toString(id));
        stmnt.execute();
    }

    //removePatient
    public void removePatient(Integer id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM patient WHERE id = ?");
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    public int checkIfIdExists(Integer id) throws SQLException {
        final String queryCheck = "SELECT * from patient WHERE id = ?";
        final PreparedStatement ps = connection.prepareStatement(queryCheck);
        ps.setInt(1, id);
        final ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            //System.out.println(resultSet.getString(1));
            return 1;
        }
        return 0;
    }

    public Patient getPatient(Integer id_patient) throws SQLException {
        final String queryCheck = "SELECT * from patient WHERE id = ?";
        final PreparedStatement ps = connection.prepareStatement(queryCheck);
        ps.setInt(1, id_patient);
        final ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Integer id = rs.getInt("id");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String address = rs.getString("address");
            String city = rs.getString("city");
            Integer tel = rs.getInt("tel");
            String email = rs.getString("email");
            LocalDate birthDay = rs.getDate("birthDay").toLocalDate();
            String sex = rs.getString("sex");
            String familyStatus = rs.getString("familyStatus");
            Integer age = rs.getInt("age");
            String job = rs.getString("job");
            LocalDate dateOpenCard = rs.getDate("dateOpenCard").toLocalDate();
            Double currentWeight = rs.getDouble("currentWeight");
            Double pastHighWeight = rs.getDouble("pastHighWeight");
            Double height = rs.getDouble("hieght");
            Double weight = rs.getDouble("weight");
            String doctor = rs.getString("doctor");
            Double startWeightCycle = rs.getDouble("startWeightCycle");
            String marks = rs.getString("marks");
            Patient patient = new Patient(id, firstName, lastName, address, city, tel, email, birthDay, sex, familyStatus, age, job, dateOpenCard, currentWeight, pastHighWeight, height, weight, doctor, startWeightCycle, marks);
            return patient;
        }
        return null;
    }

    public ArrayList<Patient> getBitrthday() throws SQLException {
        ArrayList<Patient> patientList = new ArrayList<>();
        final String queryCheck = "SELECT * from patient WHERE MONTH(Birthday) = MONTH (CURRENT_DATE) AND DAY(Birthday) = DAY(CURRENT_DATE )";
        final PreparedStatement ps = connection.prepareStatement(queryCheck);
        final ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String address = rs.getString("address");
            String city = rs.getString("city");
            Integer tel = rs.getInt("tel");
            String email = rs.getString("email");
            LocalDate birthDay = rs.getDate("birthDay").toLocalDate();
            String sex = rs.getString("sex");
            String familyStatus = rs.getString("familyStatus");
            Integer age = rs.getInt("age");
            String job = rs.getString("job");
            LocalDate dateOpenCard = rs.getDate("dateOpenCard").toLocalDate();
            Double currentWeight = rs.getDouble("currentWeight");
            Double pastHighWeight = rs.getDouble("pastHighWeight");
            Double height = rs.getDouble("hieght");
            Double weight = rs.getDouble("weight");
            String doctor = rs.getString("doctor");
            Double startWeightCycle = rs.getDouble("startWeightCycle");
            String marks = rs.getString("marks");
            Patient patient = new Patient(id, firstName, lastName, address, city, tel, email, birthDay, sex, familyStatus, age, job, dateOpenCard, currentWeight, pastHighWeight, height, weight, doctor, startWeightCycle, marks);
            patientList.add(patient);
        }
        return patientList;
    }

    public void updateHighLoss(double weight, String id, double height, double bmi, double diet) throws SQLException {
        final String queryCheck = "SELECT * from patientLossWeight WHERE id = " + id;
        final PreparedStatement ps = connection.prepareStatement(queryCheck);
        final ResultSet rs = ps.executeQuery();
        // the id exist
        if (rs.next()) {
            Double diff = rs.getDouble("diff");
            // check for update
            if (weight<diff)
            {
                PreparedStatement stmnt = connection.prepareStatement("UPDATE  patientLossWeight SET weight = ? WHERE id = ? ");
                stmnt.setString(1, Double.toString(weight));
                stmnt.setString(2, id);
                stmnt.execute();
                PreparedStatement stmnt1 = connection.prepareStatement("UPDATE  patientLossWeight SET diet = ? WHERE id = ? ");
                stmnt1.setString(1, Double.toString(diet));
                stmnt1.setString(2, id);
                stmnt1.execute();
            }
            // the id isnt exist
        } else {
            Statement stmnt = connection.createStatement();
            stmnt.executeUpdate("INSERT INTO patientLossWeight (id, weight, height, bmi, diet) " +
                    "VALUES('" + id + "','" + weight + "','" + height + "','" + bmi + "','" + diet + "')");
        }
    }

    public ArrayList < HashMap<Double, Integer>> getDietForBMI() throws SQLException {
        ArrayList< HashMap<Double, Integer>> arraylist = new ArrayList<>();
        HashMap<Double, Integer> BMi1 = new HashMap<Double, Integer>(); // (Diet , numOfPeople)
        HashMap<Double, Integer> BMi2 = new HashMap<Double, Integer>();
        HashMap<Double, Integer> BMi3 = new HashMap<Double, Integer>();
        HashMap<Double, Integer> BMi4 = new HashMap<Double, Integer>();
        HashMap<Double, Integer> BMi5 = new HashMap<Double, Integer>();
        HashMap<Double, Integer> BMi6 = new HashMap<Double, Integer>();

        final String queryCheck = "SELECT * from patientLossWeight ";
        final PreparedStatement ps = connection.prepareStatement(queryCheck);
        final ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Double diet = rs.getDouble("diet");
            Double bmi = rs.getDouble("bmi");
            Double weight = rs.getDouble("weight");
            if (bmi < 18.5) {
                Integer count = BMi1.get(diet);
                if (count == null) {
                    count = 1;
                } else count++;
                BMi1.put(diet, count);
                System.out.println("תת משקל");
            } else if (bmi >= 18.5 && bmi <= 24.9) {
                Integer count = BMi2.get(diet);
                if (count == null) {
                    count = 1;
                } else count++;
                BMi2.put(diet, count);
                System.out.println("משקל תקין");
            } else if (bmi >= 25 && bmi <= 29.9) {
                Integer count = BMi3.get(diet);
                if (count == null) {
                    count = 1;
                } else count++;
                BMi3.put(diet, count);
                System.out.println("עודף משקל");
            } else if (bmi >= 30 && bmi <= 34.9) {
                Integer count = BMi4.get(diet);
                if (count == null) {
                    count = 1;
                } else count++;
                BMi4.put(diet, count);
                System.out.println("השמנה");
            } else if (bmi >= 35 && bmi <= 39.9) {
                Integer count = BMi5.get(diet);
                if (count == null) {
                    count = 1;
                } else count++;
                BMi5.put(diet, count);
                System.out.println("השמנת יתר");
            } else if (bmi > 40) {
                Integer count = BMi6.get(diet);
                if (count == null) {
                    count = 1;
                } else count++;
                BMi6.put(diet, count);
                System.out.println("השמנה חולנית");
            }
        }
        arraylist.add(BMi1);
        arraylist.add(BMi2);
        arraylist.add(BMi3);
        arraylist.add(BMi4);
        arraylist.add(BMi5);
        arraylist.add(BMi6);

        return arraylist;
    }
}