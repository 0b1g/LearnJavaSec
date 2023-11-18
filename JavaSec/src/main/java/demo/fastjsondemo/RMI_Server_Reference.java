package demo.fastjsondemo;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.Reference;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RMI_Server_Reference {
    void register() throws Exception{
        LocateRegistry.createRegistry(1099);
        Reference reference = new Reference("RMIHello","RMIHello","http://127.0.0.1:8888/");
        ReferenceWrapper refObjWrapper = new ReferenceWrapper(reference);
        Naming.bind("rmi://127.0.0.1:1099/hello",refObjWrapper);
        System.out.println("Registry运行中......");
    }

    public static void main(String[] args) throws Exception {
        new RMI_Server_Reference().register();
    }
}
