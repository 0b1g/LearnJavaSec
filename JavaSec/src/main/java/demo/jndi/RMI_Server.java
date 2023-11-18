package demo.jndi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class RMI_Server {

    public class RMIHello extends UnicastRemoteObject implements IHello {
        protected RMIHello() throws RemoteException{
            super();
        }

//        protected RMIHello() throws RemoteException{
//            UnicastRemoteObject.exportObject(this,0);
//        }

        @Override
        public String sayHello(String name) throws RemoteException {
            System.out.println("Hello World!");
            return name;
        }
    }

    private void register() throws Exception{
        RMIHello rmiHello=new RMIHello();
        LocateRegistry.createRegistry(1099);
        Naming.bind("rmi://127.0.0.1:1099/hello",rmiHello);
        System.out.println("Registry运行中......");
    }

    public static void main(String[] args) throws Exception {
        new RMI_Server().register();
    }

}
