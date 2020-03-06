package MyBinarySearchTree;

public class Person implements Comparable<Person> {
    private int age;

    public Person(int age){ //构造器
        this.age = age;
    }

    //方法
    public int getAge() {
        return age;
    }

    public int compareTo(Person e){
        return age - e.age;
    }
}
