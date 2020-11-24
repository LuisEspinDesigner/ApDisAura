/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Interface.ServerInterface;
import java.io.*;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author Dell i5
 */
public class Client {

    static int RMIPort = 1100;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("serverlocalhost", RMIPort);
            ServerInterface h = (ServerInterface) registry.lookup("server1");
            System.out.println(" Lookup for server1 completed ");
            System.out.println(" ***Have a good trip, " + " agent 007.");
            Vector hostList = new Vector();
            hostList.addElement("server1");
            hostList.addElement("server2");
            hostList.addElement("server3");
            Agent a = new Agent("007", hostList, RMIPort);
            h.receive(a);
            System.out.println("***Nice job, agent 007");
        } catch (NotBoundException | RemoteException e) {
            System.out.println("Exception in main: " + e);
        }
    } // main
}
