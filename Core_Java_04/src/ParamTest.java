/*
class Employee{
    private String name;
    private double salary;

    public Employee(String n, double s){
        name = n;
        salary = s;
    }

    public String getName(){
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void raiseSalary(double byPercent){
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}

public class ParamTest {
    public static void main(String[] args) {
        //Test1:方法不能修改基本类型参数
        System.out.println("Testing tripleValue:");
        double percent = 10;
        System.out.println("Before: percent = " + percent);
        tripleValue(percent);
        System.out.println("After: percent = " + percent);

        //Test2:方法可以改变对象参数的状态
        System.out.println("\nTesting tripleSalary: ");
        Employee harry = new Employee("Harry", 50000);
        System.out.println("Before: salary = " + harry.getSalary());
        tripleSalary(harry);
        System.out.println("After: salary = " + harry.getSalary());

        //Test3:方法不能将新对象附加到对象参数
        System.out.println("\nTesting swap: ");
        Employee a = new Employee("Alice", 70000);
        Employee b = new Employee("Bob", 60000);
        System.out.println("Before: a = " + a.getName());
        System.out.println("Before: b = " + b.getName());
        swap(a, b);
        System.out.println("After: a = " + a.getName());
        System.out.println("After: b = " + b.getName());
    }

    public static void tripleValue(double x){//无效：方法不能改变基本类型参数的值
        x = x * 3;
        System.out.println("End of method: x = " + x);
    }

    public static void tripleSalary(Employee x){//有效：方法可以改变引用类型参数的状态
        x.raiseSalary(200);
        System.out.println("End of method: salary = " + x.getSalary());
    }

    public static void swap(Employee x, Employee y){//无效：方法不能附加新的对象到原有对象上
        Employee temp = x;
        x= y;
        y = temp;
        System.out.println("End of method: x = " + x.getName());
        System.out.println("End of method: y = " + y.getName());
    }
}
*/