/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import Interface.AgentInterface;
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
public class Agent implements AgentInterface {

    int hostIndex;
    String name;
    Vector hostList;
    int RMIPort = 1099;

    public Agent(String myName, Vector theHostList, int theRMIPort) {
        name = myName;
        hostList = theHostList;
        hostIndex = 0;
        RMIPort = theRMIPort;
    }

    public void execute() {
        String thisHost, nextHost;
        sleep(2);
        System.out.println("007 here!");
        thisHost = (String) hostList.elementAt(hostIndex);
        hostIndex++;
        if (hostIndex < hostList.size()) {
            nextHost = (String) hostList.elementAt(hostIndex);
            sleep(5);
            try {
                Registry registry = LocateRegistry.getRegistry("localhost", RMIPort);
                ServerInterface h = (ServerInterface) registry.lookup(nextHost);
                System.out.println("Lookup for " + nextHost + " at " + thisHost + " completed ");
                sleep(5); // delay for visibility
                h.receive(this);
            } catch (Exception e) {
                System.out.println("Exception in Agent execute: " + e);
            }
        } else {
            sleep(5);
            System.out.println("Agent 007 has come home");
            sleep(5);
        }
    } // execute

    static void sleep(double time) {
        try {
            Thread.sleep((long) (time * 1000.0));
        } catch (InterruptedException e) {
            System.out.println("sleep exception");
        }
    }

}
