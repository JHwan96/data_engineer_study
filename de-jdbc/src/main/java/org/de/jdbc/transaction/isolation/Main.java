package org.de.jdbc.transaction.isolation;

import org.de.jdbc.mapper.ResultSetMapper;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/de-jdbc", "root", "1234");
        con.setAutoCommit(false);

        Statement stmt = con.createStatement();
        stmt.executeUpdate("Update product set `id` = 109 where `id` = 9;");

        ResultSet rs = stmt.executeQuery("Select `id`, `name`, `updated_at`, `contents`, `price` from product where id = 109");
        while(rs.next()){
            ResultSetMapper.printRs(rs);
        }
        // no commit
        con.close();

        Connection con2 = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/de-jdbc", "root", "1234");
        Statement stmt2 = con2.createStatement();
        ResultSet rs2 = stmt2.executeQuery("Select `id`, `name`, `updated_at`, `contents`, `price` from product where id = 9");
        while(rs2.next()){
            // id 1
            ResultSetMapper.printRs(rs2);
        }
        con2.close();
    }
}
