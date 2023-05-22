package org.de.jdbc.transaction.commit.success;

import org.de.jdbc.mapper.ResultSetMapper;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/de-jdbc", "root", "1234");
        con.setAutoCommit(false);

        Statement stmt = con.createStatement();
        stmt.executeUpdate("UPDATE product SET `price` = `price`+10000 where id = 1");
        stmt.executeUpdate("UPDATE product SET `price` = `price`+10000 where id = 2");
        stmt.executeUpdate("UPDATE product SET `price` = `price`+10000 where id = 3");
        stmt.executeUpdate("Delete from review where product_id = 1");
        stmt.executeUpdate("Delete from product where id = 1");
        con.commit();
        con.close();

        Connection con2 = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/de-jdbc", "root", "1234");
        Statement stmt2 = con2.createStatement();
        ResultSet rs2 = stmt2.executeQuery("Select `id`, `name`, " +
                "`updated_at`, `contents`, `price` from product where id = 1");
        if(rs2.next()){
            // id 1
            ResultSetMapper.printRs(rs2);
        }
        else{
            System.out.println("No result");
        }
        con2.close();
    }
}
