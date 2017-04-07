package execute.client;

import execute.compute.Compute;

import java.math.BigDecimal;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import static execute.compute.Compute.COMPUTE_NAME;

/**
 * Created by y.dovganich on 05.04.2017.
 */
public class ComputePi {
    public static void main(String[] args) {
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }

        try {
            Registry registry = LocateRegistry.getRegistry();
            Compute engine = (Compute) registry.lookup(COMPUTE_NAME);

            Pi task = new Pi(5);
            BigDecimal pi = engine.executeTask(task);
            System.out.println(pi);
        } catch (Exception e) {
            System.out.println("ComputePI exception:");
            e.printStackTrace();
        }
    }
}
