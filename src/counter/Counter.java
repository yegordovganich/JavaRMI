package counter;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by Yegor on 02.04.2017.
 */
public interface Counter extends Remote {
    final String COUNTER_NAME = "Counter";

    int reset() throws RemoteException;
    int increment() throws RemoteException;
    int get() throws RemoteException;
}
