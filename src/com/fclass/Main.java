package com.fclass;


import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/student";
        String uname = "root";
        String pass = "password";
        String selectQuery = "select * from software";
//       String insertQuery = "Insert into `student`.`software` values(5, \"naveed\")";
        String insertPrepared = "Insert into software values(?, ?)";
        // step 2b: Register mysql driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // step 3: establish the connection
        Connection con = DriverManager.getConnection(url, uname, pass);

        //step 4: Creating the statement
        Statement st = con.createStatement();
        PreparedStatement st1 = con.prepareStatement(insertPrepared);

        //step 5: execute the query
//        int rowsAffected = st.executeUpdate(insertQuery);
//        System.out.println("Total rows affected : " + rowsAffected);
        st1.setInt(1,7 );
        st1.setString(2, "Another Qari");

        int rowAffectedPrepared = st1.executeUpdate();
        System.out.println("rows affected : " + rowAffectedPrepared);


        ResultSet rs = st.executeQuery(selectQuery);


        //step 6: process the results
        while (rs.next()) {
            String userData = rs.getInt("ID") + " : " + rs.getString("Name");
            System.out.println(userData);
        }


        //step 7: close the connection
        st.close();
        con.close();


    }
}
