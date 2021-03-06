package org.softlang.company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Total {

    /**
     * Total all salaries of a given company.
     */
    public static double total(Connection connection, String name){
        double total = 0;
        try {
            //
            // We could be using the SUM aggregator of SQL.
            // However, we use this example to illustrate iteration over result sets.
            //
            String query =
                    "SELECT salary FROM employee "
                            + "WHERE cid = (SELECT id FROM company WHERE name = ?);";
            PreparedStatement stm = connection.prepareStatement(query);
            stm.setString(1, name);
            ResultSet salaries = stm.executeQuery();
            while (salaries.next())
                total += salaries.getDouble("salary");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return total;
    }
}