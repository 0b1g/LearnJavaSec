package demo.fastjsondemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Fastjson_Learning {
    public static void main(String[] args) {
        //创建一个Java Bean对象
        Person person = new Person();
        person.setName("Faster");
        person.setAge(18);

        System.out.println("--------------序列化-------------");

        //将其序列化为JSON
        //String JSON_Serialize = JSON.toJSONString(person, SerializerFeature.WriteClassName);
        String JSON_Serialize = JSON.toJSONString(person);
        System.out.println(JSON_Serialize);

        System.out.println("-------------反序列化-------------");

        //使用parse方法，将JSON反序列化为一个JSONObject
        Object o1 =  JSON.parse(JSON_Serialize);
        System.out.println(o1.getClass().getName());
        System.out.println(o1);

        System.out.println("-------------反序列化-------------");

        //使用parseObject方法，将JSON反序列化为一个JSONObject
        Object o2 = JSON.parseObject(JSON_Serialize);
        System.out.println(o2.getClass().getName());
        System.out.println(o2);

        System.out.println("-------------反序列化-------------");

        //使用parseObject方法，并指定类，将JSON反序列化为一个指定的类对象
        Object o3 = JSON.parseObject(JSON_Serialize,Person.class);
        System.out.println(o3.getClass().getName());
        System.out.println(o3);
    }

}
