package org.de.jdbc.statement.executeUpdate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/de-jdbc", "root", "1234");
            Statement stmt = con.createStatement();
//            int result = stmt.executeUpdate("UPDATE product set `price` = `price` + 10000 where id = 2");
//            System.out.println(result);
//            int multiResult = stmt.executeUpdate("UPDATE product set `price` = `price` - 1000 where `name` like 'shoes%'");
//            System.out.println(multiResult);
            int noResult = stmt.executeUpdate("UPDATE product set `price` = `price` - 1000 where `name` like 'ss%'");
            System.out.println(noResult);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
