package demo.cc.cc1;

import org.apache.commons.collections.OrderedMap;
import org.apache.commons.collections.map.LinkedMap;

import java.util.HashMap;
import java.util.Map;

public class TestCC1 {
    public static void main(String[] args) {
        //常规的HashMap是无序的
//        Map<String,String> map = new HashMap<>();
//        map.put("one","111");
//        map.put("two","222");
//        map.put("three","333");
//        map.put("four","444");
//
//        for (String key: map.keySet()){
//            System.out.println(key+" "+  map.get(key));
//        }
        //调用commons.collections中类 有序
        OrderedMap map = new LinkedMap();
        map.put("one","111");
        map.put("two","222");
        map.put("three","333");
        map.put("four","444");
        for (Object key: map.keySet()){
            System.out.println(key + " " + map.get(key));
        }
        System.out.println(map.getClass());
    }

}
