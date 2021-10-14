package com.fclass;
import java.sql.*;

import java.sql.DriverManager;
import java.util.function.DoubleToIntFunction;

public class JdbcDAO {

    public static void main(String[] args){
        StudentDAO dao = new StudentDAO();
        dao.establishConnection();
        Student s1 = dao.getStudent(4);
        Student s2 = new Student(10,"Raffay");

        int rows = dao.updateStudent(s2);
        System.out.println(rows);
        System.out.println(s1.getSname());

    }
}

class StudentDAO{
    Connection con;
    void establishConnection(){

        Student s = new Student();
        String url = "jdbc:mysql://localhost:3306/student";
        String name = "root";
        String password = "password";

        try {
             con = DriverManager.getConnection(url, name, password);
        } catch (Exception e) {
            System.out.println("problem");
        }

    }
    Student getStudent(int roll) {
        Student s = new Student();
        String selectQuery = "Select Name from software where id = "+ roll;
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(selectQuery);
            rs.next();
            String studentName = rs.getString("Name");
            s.rollno = roll;
            s.sname = studentName;
            return s;

        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }
    int insertStudent(Student s){
        String name = s.getSname();
        int roll = s.getRollno();
        String insertQuery = "Insert into software values (?, ?)";
        try {

            PreparedStatement st = con.prepareStatement(insertQuery);
            st.setInt(1, roll);
            st.setString(2, name);
            int rowAffected = st.executeUpdate();
            return rowAffected;
        } catch (Exception e) {
            System.out.println(e);
        }

        return 0;

    }
    int updateStudent(Student s){
        String name = s.getSname();
        int roll = s.getRollno();
        String updateQuery = "Update software set Name = ? where ID = ?";
        try{
            PreparedStatement st = con.prepareStatement(updateQuery);
            st.setString(1, name);
            st.setInt(2, roll);
            int rowAffected = st.executeUpdate();
            return rowAffected;

        }catch(Exception e){
            System.out.println(e);
        }

        return 0;
    }
}
class Student{
    String sname;
    int rollno;
    public Student(){

    }
    public Student(int roll, String name){
       this.sname = name;
       this.rollno = roll;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public int getRollno() {
        return rollno;
    }

    public void setRollno(int rollno) {
        this.rollno = rollno;
    }
}