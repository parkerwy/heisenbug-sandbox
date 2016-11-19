package lab.heisenbug.sandbox.java.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by IntelliJ IDEA.
 * User: parker
 * Date: Nov 15, 2010
 * Time: 10:18:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface IControl extends Remote {

    String getStatus() throws RemoteException;

    void turnOn() throws RemoteException;

    void turnOff() throws RemoteException;
}
