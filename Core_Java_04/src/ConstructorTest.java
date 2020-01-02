import java.util.Random;

class Employee{
    private static int nextId;//静态域:对其的初始化在后面的静态代码块中

    private int id;
    private String name = "";
    private double salary;//实例域初始化

    //静态初始化块
    static{
        Random generator = new Random();
        //设置每个员工的nextId为1-9999的一个随机数
        nextId = generator.nextInt(10000);
    }

    //对象初始化块
    {
        id = nextId;
        nextId ++;
    }

    //三个重载构造器
    public Employee(String name, double salary){
        this.name = name;
        this.salary = salary;
    }
    public Employee(double salary){
        this("Employee #" + nextId, salary);
    }
    public Employee(){ //在声明中赋值：看属性；初始化代码块中赋值；都没有的默认初始化
    }

    //方法
    public String getName(){
        return name;
    }
    public double getSalary() {
        return salary;
    }
    public int getId(){
        return id;
    }
}
public class ConstructorTest {
    public static void main(String[] args){
        //用三个Employee对象填满staff数组
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Harry", 40000);
        staff[1] = new Employee(60000);
        staff[2] = new Employee();

        for(Employee e: staff){
            System.out.println
                    ("name: " + e.getName() + ", id: " + e.getId() + ", salary: " + e.getSalary());
            /**假如随机值为0
             * name: Harry, id: 1, salary: 40000.0(注意数据类型，double小数点）
             * name: Employee #2, id: 2, salary: 60000.0
             * name:  , id: 3, salary: 0.0
              */
        }
    }
}
