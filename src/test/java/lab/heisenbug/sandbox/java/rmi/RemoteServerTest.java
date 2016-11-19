package lab.heisenbug.sandbox.java.rmi;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Nov 15, 2010
 * Time: 10:32:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class RemoteServerTest {

    private static final Logger logger = LoggerFactory.getLogger(RemoteServerTest.class);

    @Test
    public void register() throws RemoteException, InterruptedException {

        RemoteDevice device = new RemoteDevice();
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("device", device);
        Thread.sleep(1000 * 60 * 10);
    }

    @Test
    public void invoke() throws RemoteException, NotBoundException {

        Registry registry = LocateRegistry.getRegistry();
        IRemoteDevice device = (IRemoteDevice)registry.lookup("device");
        IControl control = device.getControl();
        Class[] interfazes = control.getClass().getInterfaces();
        for (Class interfaze : interfazes){
            logger.info("The stub implemented {}", interfaze.getName());
        }
        logger.info("The stub extended {}", control.getClass().getName());
        logger.info("control status is {}", control.getStatus());
        control.turnOn();
        logger.info("control status is {}", control.getStatus());
        control.turnOff();
    }

}
