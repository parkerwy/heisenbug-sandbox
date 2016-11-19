package lab.heisenbug.sandbox.java.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Nov 15, 2010
 * Time: 10:24:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class RemoteDevice extends UnicastRemoteObject implements IRemoteDevice {

    private static final Logger logger = LoggerFactory.getLogger(RemoteDevice.class);

    private Control control = new Control();

    public RemoteDevice() throws RemoteException {
    }

    @Override
    public IControl getControl() throws RemoteException {
        logger.info("request for control received.");
        return control;
    }
}
