package counter;

import java.rmi.RemoteException;

/**
 * Created by Yegor on 02.04.2017.
 */
public interface SupplierUnchecked<T> {
    T get() throws RemoteException;
}
