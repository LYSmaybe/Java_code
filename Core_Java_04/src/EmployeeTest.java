/*
import java.time.LocalDate;

//涨工资后打印出工资条
class Employee{
    //类的属性
    private String name;
    private double salary;
    private LocalDate hireDay;

    //类的构造器
    public Employee(String n, double s, int year, int month, int day){
        name = n;
        salary = s;
        hireDay = LocalDate.of(year, month, day);
    }

    //类的方法
    public String getName(){
        return name;
    }
    public double getSalary() {
        return salary;
    }
    public LocalDate getHireDay() {
        return hireDay;
    }

    public void raiseSalary(double byPercent){
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}
public class EmployeeTest {
    public static void main(String[] args){
        //new一个staff数组，用3个Employee对象填充数组
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("A", 75000, 1987, 12, 15);
        staff[1] = new Employee("B", 50000, 1989, 10, 1);
        staff[2] = new Employee("C", 40000, 1990, 3, 5);

        //涨工资
        for(Employee e:staff){
            e.raiseSalary(5);
        }

        //打印工资条
        for(Employee e:staff){
            System.out.println("name = " + e.getName() + ", salary = " + e.getSalary() + ", hireDay = " + e.getHireDay());
        }
    }
}
*/