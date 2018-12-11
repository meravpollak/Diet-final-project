import javafx.scene.control.CheckBox;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class DietCenterData {

    private Connection connection ;
    public int NumOfPatients;
    public double SumOfDiff;
    public PatientDataAccessor patientDataAccessor;

    public DietCenterData(String driverClassName, String dbURL, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName(driverClassName);
        connection = DriverManager.getConnection(dbURL, user, password);
        NumOfPatients=0;
        SumOfDiff=0.0;
    }
    //close connection
    public void shutdown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public void addNewCycle(Integer id, Integer treat, Integer week, LocalDate localDate, Double weight, Double diff, Double diet, Integer days, CheckBox arrived, String marks) throws SQLException {
        Date date = java.sql.Date.valueOf(localDate); //convert to date in DBthrows SQLException
        Statement stmnt = connection.createStatement();
        Integer ar=0;
        if (arrived.isSelected())
        {
            ar=1;
        }
        stmnt.executeUpdate("INSERT INTO dietcycles (id, treat, week, date, weight, diff, diet, days , arrived , marks) " +
                "VALUES('" +id+ "','" +treat+ "','" + week + "','" + date + "','" + weight + "','" + diff + "','" + diet + "','" + days + "','" + ar + "','" + marks + "')");
    }

    public void UpdateMissingReport(Integer id, String firstName, String lastName, Integer tel, LocalDate localDate,String time, Integer week) throws SQLException {
        Date date = java.sql.Date.valueOf(localDate);
        Statement stmnt = connection.createStatement();
        stmnt.executeUpdate("INSERT INTO missingpatient (id, firstName, lastName, tel, date, time, week) " +
                "VALUES('" +id+ "','" +firstName+ "','" + lastName + "','" + tel + "','" + date + "','" + time + "','" + week + "')");
    }

    public void removeLastCycle(Integer id, DietCycleObject dietCycleObject) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM dietcycles WHERE id = ? AND treat = ? AND week = ?");
        statement.setInt(1, id);
        statement.setInt(2, dietCycleObject.gettreat());
        statement.setInt(3, dietCycleObject.getWeek());
        statement.executeUpdate();
    }

    //get all the patients
    public ArrayList<DietCycleObject> getCycelsOfPatient(String id) throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from dietcycles WHERE id = "+id);
        ){
            ArrayList<DietCycleObject> cyclelist = new ArrayList<>();
            while (rs.next()) {

                Integer id_in = rs.getInt("id");
                Integer treat_in = rs.getInt("treat");
                Integer week_in = rs.getInt("week");
                Date date_in = rs.getDate("date");
                Double weight_in = rs.getDouble("weight");
                Double diff_in = rs.getDouble("diff");
                Double diet_in = rs.getDouble("diet");
                Integer days_in = rs.getInt("days");
                Integer arrived_in = rs.getInt("arrived");
                String marks_invoice = rs.getString("marks");
                DietCycleObject dietCycleObject = new DietCycleObject(id_in,treat_in,week_in,date_in.toLocalDate(),weight_in,diff_in,diet_in,days_in,arrived_in,marks_invoice );
                cyclelist.add(dietCycleObject);
            }
            return cyclelist ;
        }
    }

    public ArrayList<DietCycleObject> getThreatCycelsOfPatient(String id, String treat) throws SQLException {
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from dietcycles WHERE id = "+id+" AND treat = "+treat);
        ){
            ArrayList<DietCycleObject> cyclelist = new ArrayList<>();
            while (rs.next()) {

                Integer id_in = rs.getInt("id");
                Integer treat_in = rs.getInt("treat");
                Integer week_in = rs.getInt("week");
                Date date_in = rs.getDate("date");
                Double weight_in = rs.getDouble("weight");
                Double diff_in = rs.getDouble("diff");
                Double diet_in = rs.getDouble("diet");
                Integer days_in = rs.getInt("days");
                Integer arrived_in = rs.getInt("arrived");
                String marks_invoice = rs.getString("marks");
                System.out.print("");
                DietCycleObject dietCycleObject = new DietCycleObject(id_in,treat_in,week_in,date_in.toLocalDate(),weight_in,diff_in,diet_in,days_in,arrived_in,marks_invoice );
                cyclelist.add(dietCycleObject);
            }
            return cyclelist ;
        }
    }

    public void insertIntoLastMeet(String id, int week, LocalDate date) throws SQLException {
        Date date_d = java.sql.Date.valueOf(date);
        final String queryCheck = "SELECT * from lastMeet WHERE id = " + id;
        final PreparedStatement ps = connection.prepareStatement(queryCheck);
        final ResultSet rs = ps.executeQuery();
        // the id exist
        if (rs.next()) {
            Date date_table = rs.getDate("date");
                PreparedStatement stmnt = connection.prepareStatement("UPDATE  lastMeet SET week = ? WHERE id = ? ");
                stmnt.setInt(1,week);
                stmnt.setString(2, id);
                stmnt.execute();
                PreparedStatement stmnt1 = connection.prepareStatement("UPDATE  lastMeet SET date = ? WHERE id = ? ");
                stmnt1.setDate(1, date_d);
                stmnt1.setString(2, id);
                stmnt1.execute();

            // the id isnt exist
        } else {
            Statement stmnt = connection.createStatement();
            stmnt.executeUpdate("INSERT INTO lastMeet (id, week, date) " +
                    "VALUES('" + id + "','" + week + "','" + date + "')");
        }
    }

    public ArrayList <Integer> getLastMeet(LocalDate date) throws SQLException {
        ArrayList <Integer> arrayList = new ArrayList();
        arrayList.add(0,0);
        arrayList.add(1,0);
        arrayList.add(2,0);
        arrayList.add(3,0);
        arrayList.add(4,0);
        arrayList.add(5,0);
        arrayList.add(6,0);
        arrayList.add(7,0);
        arrayList.add(8,0);
        arrayList.add(9,0);
        arrayList.add(10,0);
        arrayList.add(11,0);
        Date date_d = java.sql.Date.valueOf(date);
        final String queryCheck = "SELECT * from lastMeet";
        final PreparedStatement ps = connection.prepareStatement(queryCheck);
        final ResultSet rs = ps.executeQuery();
        // the id exist
        while (rs.next()) {
            Date date_table = rs.getDate("date");
            Integer week_in = rs.getInt("week");
            long diff = date_d.getTime() - date_table.getTime();
            int diffDays = (int) (diff / (24 * 1000 * 60 * 60));
            if (diffDays > 30) {
                int count = arrayList.get(week_in)+1;
                arrayList.add(week_in,count);
            }
        }
        return arrayList;
    }

    public HashMap<Double,Double> getWeightsPerWeek (String week) throws SQLException {
      //  ArrayList<Double> diets_perWeek = new ArrayList<>();
        HashMap<Double,Integer> map_count = new HashMap<Double,Integer>();
        HashMap<Double,Double> map_diffs = new HashMap<Double,Double>();
        HashMap<Double,Double> map_total_mem = new HashMap<Double,Double>();
        HashMap<Double,HashSet<Integer>> map_NumOfPatients = new HashMap<Double,HashSet<Integer>>();
        int count_patients=0;
        double sum_diff=0.0;
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from dietcycles WHERE week = "+week);
        ) {
            while (rs.next()) {
                Double diff_in = rs.getDouble("diff");
                Double diet_in = rs.getDouble("diet");
//                Integer id_in = rs.getInt("id");
//                HashSet<Integer> new_hashSet = new HashSet<Integer>();
//                new_hashSet = map_NumOfPatients.get(diet_in);
//                if (!new_hashSet.contains(id_in))
//                {
//                    new_hashSet.add(id_in);
//                }
//                map_NumOfPatients.put(diet_in, new_hashSet );
                Integer count = map_count.get(diet_in);
                if (count == null) {
                    count = 1;
                } else count++;
                Double diff = map_diffs.get(diff_in);
                if (diff == null) {
                    diff = 0.0;
                } else diff++;
                map_count.put(diet_in, count);
                map_diffs.put(diet_in, diff + diff_in);

                count_patients++;
                sum_diff = sum_diff + diff_in;
            }
        }

        double mem=0.0;
        for (Double diet_num : map_count.keySet())
        {
            mem = ((map_diffs.get(diet_num))/map_count.get(diet_num));
            mem=mem*(-1);
            map_total_mem.put(diet_num, mem);
            mem=0.0;
        }

        NumOfPatients=count_patients;
        SumOfDiff=sum_diff;
        return map_total_mem;
    }

    public int getNumOfPatientPerWeek (String week) throws SQLException {
        HashMap<Double,Double> a = getWeightsPerWeek(week);
        return NumOfPatients;
    }

    public double getSumofDiffPerWeek (String week) throws SQLException {
        HashMap<Double,Double> a = getWeightsPerWeek(week);
        return SumOfDiff;
    }

    public HashMap<Integer,Double> getPeopleLeft () throws SQLException, ClassNotFoundException {

//        PatientDataAccessor patientDataAccessor = new PatientDataAccessor("com.mysql.jdbc.Driver", "jdbc:mysql://127.0.0.1:3306/diet", "root", "Dana1993!");
//        int numOfPatients = patientDataAccessor.getPatientsID().size();
//
        HashMap<Integer,Double> map = new HashMap<Integer,Double>();
//        try (
//                Statement stmnt = connection.createStatement();
//                ResultSet rs = stmnt.executeQuery("select * from dietcycles WHERE week = "+week);
//        ) {
//            while (rs.next()) {
//                Double diff_in = rs.getDouble("diff");
//                Double diet_in = rs.getDouble("diet");
//            }
//        }
        return map;
    }

    public ArrayList <Patient> getPatientsUnderMem(String week) throws SQLException, ClassNotFoundException {
        double mem = getSumofDiffPerWeek(week)/getNumOfPatientPerWeek(week);
        ArrayList<Integer> ids = new ArrayList<>();
        ArrayList<Patient> patients = new ArrayList<>();
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from dietcycles WHERE diff < "+mem);
        ) {
            while (rs.next()) {
                Integer id_in = rs.getInt("id");
                if (!ids.contains(id_in))
                {
                    ids.add(id_in);
                }
            }
        }

        for ( Integer id: ids)
        {
            patientDataAccessor = new PatientDataAccessor("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/diet","root","Dana1993!");
            Patient p = patientDataAccessor.getPatient(id);
            patients.add(patientDataAccessor.getPatient(id));
        }

        return patients;

    }

    public Map<String,Double> getAllDetailsForOrderDiet()  throws SQLException
    {
        Map<String,Integer> map_count = new HashMap<String, Integer>();
        Map<String,Double> map_value = new HashMap<String, Double>();
        Map<String,Double> map_order = new HashMap<String, Double>();
        int count=0;
        double value=0.0;
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from dietcycles");
        ) {
            while (rs.next()) {
                Integer id = rs.getInt("id");
                Double diff_in1 = rs.getDouble("diff");
                Double diet_in1 = rs.getDouble("diet");

                if (rs.next())
                {
                    if (rs.getInt("id") == id)
                    {
                        Double diff_in2 = rs.getDouble("diff");
                        Double diet_in2 = rs.getDouble("diet");
                        //update the count
                        if (map_count.get(diet_in1+" -> "+diff_in2) == null) {
                            count = 1;
                            value = diff_in1 + diff_in2;
                        }
                        else {
                            count=map_count.get(diet_in1+" -> "+diff_in2)+1;
                            value = map_value.get(diet_in1+" -> "+diff_in2)+diff_in1 + diff_in2;
                        }
                        map_count.put(diet_in1+" -> "+diet_in2,count);
                        map_value.put(diet_in1+" -> "+diet_in2,value);
                    }
                }


            }

        }

        for (String diet : map_count.keySet())
        {
            double mem =  map_value.get(diet)/map_count.get(diet);
            mem=mem*(-1);
            if (mem>0)
            {
                map_order.put(diet, mem);
            }
        }

        return map_order;
    }

        public void insertDiet(double diet_id, String diet_details) throws SQLException {
        Statement stmnt = connection.createStatement();
        stmnt.executeUpdate("INSERT INTO diets (id, diet) " +
                "VALUES('" +diet_id+ "','" +diet_details+ "')");

        }

    public String getDiet (double diet_id) throws SQLException {
        String diet="";
        try (
                Statement stmnt = connection.createStatement();
                ResultSet rs = stmnt.executeQuery("select * from diets WHERE id ="+diet_id);
        ) {
            while (rs.next()) {
                diet = rs.getString("diet");
            }
        }

        return diet;
    }
}
