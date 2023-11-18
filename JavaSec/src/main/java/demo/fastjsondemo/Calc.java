package demo.fastjsondemo;

import java.io.IOException;

public class Calc {
    public String calc;

    public Calc() {
        System.out.println("调用了构造函数");
    }

    public String getCalc() {
        System.out.println("调用了getter");
        return calc;
    }

    public void setCalc(String calc) throws IOException, IOException {
        this.calc = calc;
        Runtime.getRuntime().exec("calc");
        System.out.println("调用了setter");
    }
}
