import org.junit.jupiter.api.Test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Created by Julia Wigenstedt
 * Date: 2020-10-11
 * Time: 17:25
 * Project: Ass2Database
 * Copyright: MIT
 */
public class DatabaseTest {

    Database database = new Database();

    public DatabaseTest() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    @Test
    public final void addListToDatabaseTest() throws SQLException {
        int id = 1;
        String date = "2019-07-01";
        database.addListToDatabase(id, date);
        id = 2;
        date ="2020-03-23";
        database.addListToDatabase(id, date);
        Statement statement = database.statement;
        String query = "SELECT * FROM customers ;";
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()) {
            System.out.println(rs.getString(5));
            assertNotEquals(date, rs.getString(5));
            rs.next();
            System.out.println(rs.getString(5));
            assertEquals(date, rs.getString(5));
        }
    }

}
