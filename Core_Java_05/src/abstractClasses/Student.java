package abstractClasses;

public class Student extends Person {
    private String major;

    public Student(String name, String major){
        super(name); //将 name 传给超类的构造器
        this.major = major;
    }

    public String getDescription(){
        return "a student majoring in " + major;
    }
}
