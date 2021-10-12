package com.fclass;

import java.sql.*;

public class Main {

    public static void main(String[] args)throws Exception {

       String url = "jdbc:mysql://localhost:3306/student";
       String uname = "root";
       String pass = "password";
       String query = "select Name from software where id = 1";


        // step 2b: Register mysql driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // step 3: establish the connection
        Connection con = DriverManager.getConnection(url, uname, pass);

        //step 4: Creating the statement
        Statement st = con.createStatement();

        //step 5: execute the query
        ResultSet rs = st.executeQuery(query);

        //step 6: process the results
        rs.next(); // to get the pointer to the first value of the output table
        String name = rs.getString("Name");

        //step 7: close the connection
        st.close();
        con.close();

        System.out.println(name);





    }
}
