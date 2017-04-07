package counter;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by Yegor on 02.04.2017.
 */
public class CounterClass extends UnicastRemoteObject implements Counter {
    private int counter;

    protected CounterClass() throws RemoteException {}

    @Override
    public synchronized int reset() throws RemoteException {
        this.counter = 0;
        return counter;
    }

    @Override
    public synchronized int increment() throws RemoteException {
        return ++this.counter;
    }

    @Override
    public synchronized int get() {
        return counter;
    }
}
