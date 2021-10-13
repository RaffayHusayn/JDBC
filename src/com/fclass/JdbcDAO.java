package com.fclass;
import java.sql.*;

import java.sql.DriverManager;

public class JdbcDAO {

    public static void main(String[] args){
        StudentDAO dao = new StudentDAO();
        Student s1 = dao.getStudent(2);
        System.out.println(s1.sname);

    }
}

class StudentDAO{
    Student getStudent(int roll) {
        Student s = new Student();
        String url = "jdbc:mysql://localhost:3306/student";
        String name = "root";
        String password = "password";
        String selectQuery = "Select Name from software where id = "+ roll;
        try {
            Connection con = DriverManager.getConnection(url, name, password);
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