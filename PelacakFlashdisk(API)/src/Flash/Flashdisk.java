/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Flash;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Flashdisk extends Remote {
    public void productID(Object product_ID) throws RemoteException;
    public Object getProductID() throws RemoteException;
    public void IP(String IP) throws RemoteException; 
    public String getIP() throws RemoteException;
      
    
    
}
