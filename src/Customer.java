import javax.swing.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Julia Wigenstedt
 * Date: 2020-10-08
 * Time: 19:16
 * Project: Ass2Database
 * Copyright: MIT
 */
public class Customer implements Serializable {
    protected String name;
    protected String personID;
    protected LocalDate membershipPaid;
    List<String> listOfTrainingSessions = new ArrayList<>();
    protected boolean isActiveMember;

    public Customer(String name, String personID, LocalDate membershipPaid) {
        this.name=name;
        this.personID=personID;
        this.membershipPaid=membershipPaid;
        if(membershipPaid.plusYears(1).isAfter(LocalDate.now())){
            isActiveMember=true;
        } else isActiveMember=false;
    }

    public boolean isActiveMember(LocalDate membershipPaid){
        isActiveMember = membershipPaid.plusYears(1).isAfter(LocalDate.now());
        return isActiveMember;
    }

    public String getName() {
        return name;
    }

    public String getPersonID() {
        return personID;
    }

    public LocalDate getMembershipPaid() {
        return membershipPaid;
    }

    public List<String> getListOfTrainingSessions() {
        return listOfTrainingSessions;
    }

    public boolean isActiveMember() {
        return isActiveMember;
    }

    public String printListOfTrainingSessions(){
        if(listOfTrainingSessions.isEmpty()){
            String message = name + " har inga loggade träningspass.";
            JOptionPane.showMessageDialog(null, message);
            return message;
        } else {
            StringBuilder sb = new StringBuilder();
            for (String s : listOfTrainingSessions) {
                sb.append(s);
            }
            JOptionPane.showMessageDialog(null, sb.toString());
            return sb.toString();
        }
    }
}
