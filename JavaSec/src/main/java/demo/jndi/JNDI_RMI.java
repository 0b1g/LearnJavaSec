package demo.jndi;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

public class JNDI_RMI {
    public static void main(String[] args) throws Exception {

        //设置JNDI环境变量
        Hashtable<String, String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
        env.put(Context.PROVIDER_URL, "rmi://localhost:1099");


        //初始化上下文
        Context initialContext = new InitialContext(env);

        //调用远程类
        IHello ihello = (IHello) initialContext.lookup("hello");
        System.out.println(ihello.sayHello("Feng"));

    }
}
