package demo.fastjsondemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Fastjson_Test {
    public static void main(String[] args) {
//        String JSON_Serialize = "{\"@type\":\"demo.fastjsondemo.Person\",\"age\":18,\"name\":\"Faster\"}";
//        System.out.println(JSON.parse(JSON_Serialize));

        String JSON_Calc = "{\"@type\":\"demo.fastjsondemo.Calc\",\"calc\":\"Faster\"}";
        System.out.println(JSON.parseObject(JSON_Calc));;

    }
}
