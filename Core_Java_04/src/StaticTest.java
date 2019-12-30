/*
class Employee{
    //一个静态域
    private  static int nextId = 1;

    //三个实例域
    private String name;
    private double salary;
    private int id;

    //构造器
    public Employee(String n, double s){
        name = n;
        salary = s;
        id = 0;
    }

    //方法（没有显式参数，也不能忘记括号）
    public String getName(){
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }

    public void setId(){
        id = nextId;
        nextId++;
    }

    //静态方法
    public static int getNextId(){
        return nextId;//返回静态域数据
    }

    public static void main(String[] args){ //用于单元测试
        Employee e = new Employee("Harry", 50000);
        System.out.println(e.getName() + " " + e.getSalary());
    }
}

public class StaticTest {
    public static void main(String[] args){
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Tom", 40000);
        staff[1] = new Employee("Dick", 60000);
        staff[2] = new Employee("Harry", 65000);

        for(Employee e : staff){
            e.setId();
            System.out.println
                    ("name = " + e.getName() + ", id = " + e.getId() + ", salary = " + e.getSalary());
        }

        int n = Employee.getNextId();//调用静态方法
        System.out.println("Next available id = " + n);
    }
}
*/