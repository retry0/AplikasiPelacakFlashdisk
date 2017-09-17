/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PelacakFlashdisk;


import Flash.Flashdisk;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


/**
 *
 * @author acer
 */
public class Flashserver extends UnicastRemoteObject implements Flashdisk {
    Object productID;
    String ip;
    String ruang;
    
    
    
    public Flashserver() throws RemoteException{

    }
    
    
    public static void main(String[] args) throws RemoteException{
        Registry registry = LocateRegistry.createRegistry(1099);
        
        Flashserver flashserver = new Flashserver();
        registry.rebind("flashserver", flashserver);
 
        System.out.println("Server Telah Berjalan");
    }

    @Override
    public void productID(Object product_ID) throws RemoteException {
        productID=product_ID;   
    }
    
    public Object getProductID() throws RemoteException{
        return productID;
    }

    @Override
    public void IP(String IP) throws RemoteException {
        ip=IP;
    }
    
    public String getIP() throws RemoteException {
        return ip;
    }
  
  
    
}
