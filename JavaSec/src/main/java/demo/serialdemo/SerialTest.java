package demo.serialdemo;

import com.sun.corba.se.impl.ior.OldJIDLObjectKeyTemplate;

import java.io.*;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SerialTest{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        SerialTest serialTest = new SerialTest();
//        serialTest.serial();
        serialTest.unserial();

    }

    public void serial() throws IOException {
        Student student = new Student();
        FileOutputStream fos = new FileOutputStream("./data/serial.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(student);
    }
    public void unserial() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("./data/serial.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
//        ois.readObject();

        Student obj = (Student) ois.readObject();
        System.out.println(obj.name);
    }

}

class Student implements Serializable{
    private static final long serialVersionUID = 6873561273755018260L;
    public String name = "jack";
    public int id = 123;
    public String score = "90";
//    transient public int price = 11; //transient修饰的变量不能序列化

    public Student() {
        System.out.println("无参构造方法");
    }
    public void study(){
        System.out.println("学生在学习");
    }
    public void sleep(){
        System.out.println("学生在睡觉");
    }
    //重写readobject方法
    private void readObject(ObjectInputStream ois) throws Exception{
        ois.defaultReadObject(); //先调用默认得readobject方法
        //执行命令1
//        Runtime.getRuntime().exec("calc.exe");
//        Runtime r = Runtime.getRuntime();

        //使用反射调用
//        Class clazz = Class.forName("java.lang.Runtime");
//        Method m1 = clazz.getDeclaredMethod("getRuntime");
//        Object obj = m1.invoke(clazz,null);
//        Method m2 = clazz.getDeclaredMethod("exec",String.class);
//        m2.invoke(obj,"calc.exe");


        //执行命令2
//        ProcessBuilder pb = new ProcessBuilder("calc.exe");
//        pb.start();
        Class clazz = Class.forName("java.lang.ProcessBuilder");
        Constructor c = clazz.getConstructor(String[].class);
        String[][] cmd = {{"calc.exe"}}; //newInstance和ProcessBuilder都是数组，所以要用二维数组
        Object obj = c.newInstance(cmd);
        Method m = clazz.getMethod("start");
        m.invoke(obj,null);
    }
}
