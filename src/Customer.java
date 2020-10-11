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

    public Customer(){}

    public Customer(String name, String personID, LocalDate membershipPaid){
        this.name=name;
        this.personID = personID;
        this.membershipPaid = membershipPaid;
        if(membershipPaid.plusYears(1).isAfter(LocalDate.now())){
            isActiveMember=true;
        } else isActiveMember=false;
    }

    public Customer(String name, String personID, LocalDate membershipPaid, String list) {
        this.name=name;
        this.personID=personID;
        this.membershipPaid=membershipPaid;
        if(membershipPaid.plusYears(1).isAfter(LocalDate.now())){
            isActiveMember=true;
        } else {
            isActiveMember = false;
        }
        if(!list.isEmpty()) {
            String[] listSplit = list.split(",");
            for (String s : listSplit) {
                listOfTrainingSessions.add(s.trim());
            }
        } else {
            listOfTrainingSessions.clear();
        }
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
            sb.append(getName()+ "\n");
            for (String s : listOfTrainingSessions) {
                sb.append(s + "\n");
            }
            JOptionPane.showMessageDialog(null, sb.toString());
            return sb.toString();
        }
    }

    public String listOfTrainingSessions(){
        if(listOfTrainingSessions.isEmpty()){
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            for (String s : listOfTrainingSessions) {
                sb.append(s+",");
            }
            return sb.toString();
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPersonID(String personID) {
        this.personID = personID;
    }

    public void setActiveMember(boolean activeMember) {
        isActiveMember = activeMember;
    }
}
