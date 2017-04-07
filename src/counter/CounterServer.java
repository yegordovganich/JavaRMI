package counter;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by Yegor on 02.04.2017.
 */
public class CounterServer {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        CounterClass counter = new CounterClass();
        Registry localReg = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
        localReg.bind(Counter.COUNTER_NAME, counter);
        System.out.println("CounterServer is running.");
    }
}
