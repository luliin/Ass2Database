import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Julia Wigenstedt
 * Date: 2020-10-11
 * Time: 17:39
 * Project: DatabaseTest
 * Copyright: MIT
 */
public class Database {
    Connection connection;
    Statement statement;
    ResultSet resultSet;
    private String url = "jdbc:mysql://localhost:3306/customerdb?autoReconnect=true&useSSL=false";
    private String user = "root", pass = "Wigenst1!";
    private String db_table = "customer";


    public Database() throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(url, user, pass);
        statement = connection.createStatement();
    }
    public void createDatabase(List<Customer> customerList) throws SQLException {
        int key = 1;
        for (var c : customerList) {
            String counter = key++ +"";
            String name = c.getName();
            String pId = c.getPersonID();
            String date = c.getMembershipPaid().toString();
            String list = c.listOfTrainingSessions();
            if(list==null){
                list="";
            }


            statement.executeUpdate(
                    "INSERT IGNORE into customer" +
                            "( customerID, name, personId, membershipPaid, list)" +
                            "values ('"+counter+"','"+name+"','"+pId+"','"+date+"', '"+list+"')");
        }
    }

    public void addListToDatabase(int id, String string) throws SQLException {
        connection = DriverManager.getConnection(url, user, pass);
        String query = "update customer set list = ? where customerID = ?";
        PreparedStatement preparedStmt = connection.prepareStatement(query);
        preparedStmt.setString(1, string);
        preparedStmt.setInt(2, id);
        preparedStmt.executeUpdate();
        connection.close();
    }

    public List<Customer> getCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        resultSet = statement.executeQuery(
                "select * from " + db_table);
        while (resultSet.next()){
            customers.add(new Customer(resultSet.getString(2),
                    resultSet.getString(3),
                    LocalDate.parse(resultSet.getObject(4).toString()),
                    resultSet.getString(5)));
        } return customers;
    }
}
