package execute.compute;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by y.dovganich on 05.04.2017.
 */
public interface Compute extends Remote {
    String COMPUTE_NAME = "Compute";

    <T> T executeTask(Task<T> t) throws RemoteException;
}
