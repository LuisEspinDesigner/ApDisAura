/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.rmi.Remote;
import server.Agent;

/**
 *
 * @author Dell i5
 */
public interface ServerInterface extends Remote{
    public void receive(Agent h)
throws java.rmi.RemoteException;

}
