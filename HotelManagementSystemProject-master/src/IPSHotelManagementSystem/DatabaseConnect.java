package IPSHotelManagementSystem;

import java.sql.*;

public class DatabaseConnect {
    Connection con;
    Statement stat;

    public DatabaseConnect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql:///hms","root","root");
            stat = con.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
