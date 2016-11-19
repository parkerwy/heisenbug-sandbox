package lab.heisenbug.sandbox.java.rmi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Nov 15, 2010
 * Time: 10:25:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Control extends UnicastRemoteObject implements IControl {

    private static final Logger logger = LoggerFactory.getLogger(Control.class);

    private static final String ON = "on";

    private static final String OFF = "off";

    private String status = OFF;

    public Control() throws RemoteException {
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void turnOn() {
        logger.info("request received to turn it on.");
        status = ON;
    }

    @Override
    public void turnOff() {
        logger.info("request received to turn it off.");
        status = OFF;
    }
}
