package by.poit.lab7;

import java.util.ArrayList;

public class CEO {

    public ArrayList<SysAdmin> sysAdmins;

    public String FIO;
    public double salary;

    public CEO(){}

    public CEO(String fio, double salary){
        FIO = fio;
        this.salary = salary;
        sysAdmins = new ArrayList<>();
    }

    public void addSysAdmin(SysAdmin sysAdmin){
        sysAdmins.add(sysAdmin);
        System.out.println("A new sysadmin has been hired");
    }
}
