package demo.jndi;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

public class JDNI_DNS {

    public static void main(String[] args) {
        Hashtable<String,String> env = new Hashtable<String, String>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
        env.put(Context.PROVIDER_URL, "dns://192.168.43.1");

        try {
            DirContext ctx = new InitialDirContext(env);
            Attributes res = ctx.getAttributes("goodapple.top", new String[] {"A"});
            System.out.println(res);
        } catch (NamingException e) {
            e.printStackTrace();
        }

    }
}
