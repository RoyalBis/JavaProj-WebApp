package com.travelexperts.model;

// We both use different Nullable imports. Will have to swap back and forth unfortunately

//import org.jetbrains.annotations.Nullable;
//import com.sun.istack.internal.Nullable; 


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Created by 723403 on 3/9/2016.
 */
public class Database
{
    //@Nullable
    public static Connection connect() throws ClassNotFoundException, SQLException
    {
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/travelexperts", "root", "");
        conn.setAutoCommit(false);
        //Statement stmt = conn.createStatement();
        return conn;
    }
}
