package demo.fastjsondemo;

public class Person {
    public String name;
    public int age;

    public String getName() {
        System.out.println("调用了getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println("调用了setName()");
        this.name = name;
    }

    public int getAge() {
        System.out.println("调用了getAge()");
        return age;
    }

    public void setAge(int age) {
        System.out.println("调用了setAge()");
        this.age = age;
    }
}
