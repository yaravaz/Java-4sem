package by.poit.lab7;

public class SysAdmin {
    public String FIO;
    public double salary;

    public SysAdmin(){}

    public SysAdmin(String fio, double salary){
        FIO = fio;
        this.salary = salary;
    }

    private final String position = "sysAdmin";

    public void check(){
        System.out.println("The system administrator checked the system performance");
    }

    public boolean fix() {
        System.out.println("The system administrator fixed the equipment");
        return true;
    }

    public double upSalary(double money){
        System.out.println("The salary of the system administrator has been increased");
        if(money > 0){salary += money;}
        else{
            System.out.println("Function to reduce elsewhere");
        }
        return salary;
    }

    public void downSalary(int pros){
        int newSalary = (int)salary / pros;
        if(newSalary <= 100){
            System.out.println("fired");
        }
        else{
            System.out.println("The system administrator's salary was lowered");
        }
        salary = newSalary;
    }
}
