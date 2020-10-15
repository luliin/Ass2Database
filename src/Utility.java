import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Julia Wigenstedt 
 * Date: 2020-10-08
 * Time: 19:30
 * Project: Ass2Database
 * Copyright: MIT
 */
public class Utility {
    Database database;
    //  List<Customer> customers;
    List<Customer> customersDatabase;

    public Utility() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        database = new Database();
        customersDatabase = new ArrayList<>();
        customersDatabase = database.getCustomers();
        if(customersDatabase.isEmpty()) {
            createListFromFile();
            database.createDatabase(customersDatabase);
        }
    }

    public void createListFromFile() {

        if (customersDatabase.isEmpty()) {
            try (Scanner in = new Scanner(new FileReader("customers.txt")).useDelimiter(",|\\n")) {
                while (in.hasNextLine()) {
                    String personId = in.next().trim();
                    String name = in.next().trim();
                    String dateString = in.next().trim();
                    LocalDate date = LocalDate.parse(dateString);
                    customersDatabase.add(new Customer(name, personId, date));
                }
                System.out.println("Fil skapad");

            } catch (FileNotFoundException e) {
                System.out.println("Filen kunde inte hittas.");
                e.printStackTrace();
            }
        }
    }

    public void print(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

    public String getString(String question, String errorMessage, String title) {
        String answer = null;
        while (answer == null || answer.isEmpty()) {
            answer = JOptionPane.showInputDialog(null, question, title, JOptionPane.QUESTION_MESSAGE);
            if (answer == null || answer.isEmpty())
                print(errorMessage);
        }
        return answer;
    }

    public Customer getCustomer(String input) {
        for (Customer customer : customersDatabase) {
            if (customer.getName().equalsIgnoreCase(input) || customer.getPersonID().equalsIgnoreCase(input)) {
                return customer;
            }
        }
        return null;
    }

    public void program() throws SQLException {
        while (true) {

            String[] options = {"Receptionist", "Tränare"};
            int user = JOptionPane.showOptionDialog(null,
                    "Vem använder programmet?",
                    "Välj användare",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options, options[0]);
            if (user == 0) {
                receptionist();
            } else if (user == 1) {
                trainer();
            } else {
                System.exit(0);
            }
        }
    }

    public void receptionist() throws SQLException {
        while (true) {
            String input = getString("Ange kundens namn eller personnummer",
                    "Du måste ange antingen namn eller personnummer.",
                    "Ange namn eller personnummer").trim();
            if(printMembershipInfo(getCustomer(input))){
                writeToFile();
            }
            String[] options = {"Ja", "Nej"};
            int selection = JOptionPane.showOptionDialog(null,
                    "Vill du söka igen?",
                    "Sök igen?", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (selection != 0) {
                break;
            }
        }
    }

    public void trainer() {
        while (true) {
            String input = getString("Ange kundens namn eller personnummer",
                    "Du måste ange antingen namn eller personnummer.",
                    "Ange namn eller personnummer").trim();
            printMembershipInfo(getCustomer(input));
            String[] options = {"Ja", "Nej"};
            int selection = JOptionPane.showOptionDialog(null,
                    "Vill du söka igen?",
                    "Sök igen?",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
            if (selection != 0) {
                break;
            }
        }
    }

    public boolean printMembershipInfo(Customer customer) {
        if (customer != null) {
            customer.isActiveMember();
            if (customer.isActiveMember()) {
                customer.printListOfTrainingSessions();
                return true;
            } else {
                print(customer.getName() + " har inte längre ett aktivt medlemskap. " +
                        "Sista giltighetsdag var " + customer.membershipPaid.plusYears(1));
                return true;
            }
        } else {
            print("Obehörig person.");
            return false;
        }
    }

    public void writeToFile() {
        try (PrintWriter out = new PrintWriter(new FileWriter("listOfTrainingSessions.txt"))) {
            out.println("Lista över träningssessioner per kund:\n");
            for (Customer customer : customersDatabase) {
                for (String s : customer.getListOfTrainingSessions()) {
                    out.printf("%s, %s\n%s\n", customer.getName(), customer.getPersonID(), s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IllegalAccessException, InstantiationException {
        Utility utility = new Utility();
        utility.program();
    }
}