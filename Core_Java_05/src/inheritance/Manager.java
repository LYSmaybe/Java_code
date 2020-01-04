package inheritance;

public class Manager extends Employee{
    private double bonus;

    //构造器初始化
    public Manager(String name, double salary, int year, int month, int day){
        super(name, salary, year, month, day);
        bonus = 0;
    }

    public double getSalary(){
        double baseSalary = super.getSalary();
        return baseSalary + bonus;
    }

    public void setBonus(double b) {
        this.bonus = b;
    }
}
