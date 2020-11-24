/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Interface.ServerInterface;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Dell i5
 */
public class Server extends UnicastRemoteObject implements ServerInterface  {

    static int RMIPort = 1100;

    public Server() throws RemoteException {
        super();
    }
    @Override
    public void receive(Agent h) throws RemoteException {
        sleep(3);
        System.out.println("*****Agent" + h.name + " arrived.");
        h.execute();
    }

    static void sleep(double time) {
        try {
            Thread.sleep((long) (time * 1000.0));
        } catch (InterruptedException e) {
            System.out.println("sleep exception");
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         InputStreamReader is = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(is);
        String s;
        String myName = "server" + args[0];
        try {
            System.setSecurityManager(new RMISecurityManager());
            Server h = new Server();
            Registry registry = LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            registry.rebind(myName, h);
            System.out.println("********************************");
            System.out.println(" Agent " + myName + " ready.");
            System.out.println("********************************");
        } catch (RemoteException re) {
            System.out.println("Exception inAgentServer.main:" + re);
        }
    }
    
}
