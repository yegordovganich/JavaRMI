package execute.engine;

import execute.compute.Compute;
import execute.compute.Task;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by y.dovganich on 05.04.2017.
 */
public class ComputeEngine implements Compute {

    public ComputeEngine() {
        super();
    }

    @Override
    public <T> T executeTask(Task<T> t) throws RemoteException {
        return t.execute();
    }

    /*
        Before running this main:
        1. Copy Compute.class and Task.class to /src/execute.compute
        2. Start rmiregistry from /src/
     */
    public static void main(String[] args) {
//        if (System.getSecurityManager() == null) {
//            System.setSecurityManager(new SecurityManager());
//        }

        try {
            Compute engine = new ComputeEngine();
            execute.compute.Compute stub = (Compute) UnicastRemoteObject.exportObject(engine, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(COMPUTE_NAME, stub);
            System.out.println("ComputeEngine bound.");
        } catch (RemoteException e) {
            System.out.println("ComputeEngine exception: ");
            e.printStackTrace();
        }
    }
}
