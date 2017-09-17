/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Background;



import Flash.Flashdisk;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Background{
    Flashdisk flashclient;
    static String ruangan;
    private String productID;
    static private String f;
    static private String g;
    static private String h;
    static Thread t;
    
    public Background() throws RemoteException{ 
       try{
            flashclient = (Flashdisk) Naming.lookup("rmi://localhost/flashserver");
        }catch (NotBoundException ex) { 
            System.out.println(ex);
            System.exit(1);
        }catch (MalformedURLException ex){
            System.out.println(ex);
            System.exit(1);
        }catch (RemoteException ex){
            System.out.println(ex);
            System.exit(1);
        }
        
       statusFlashdisk();
       cek();
    }
    
    public static String getSerialNumber(String drive) {
        String result = "";
        try {
        File file = File.createTempFile("getSN_byNuriw",".vbs");
        file.deleteOnExit();
        FileWriter fw = new java.io.FileWriter(file);
        String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n"
        +"Set colDrives = objFSO.Drives\n"
        +"Set objDrive = colDrives.item(\"" + drive + "\")\n"
        +"Wscript.Echo objDrive.SerialNumber";
        fw.write(vbs);
        fw.close();
        Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
        BufferedReader input =new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;
        while ((line = input.readLine()) != null) {
        result += line;
        }
        input.close();
        }
        catch(Exception e){
        e.printStackTrace();
        }
        return result.trim();
 }
    
    public String IPaddress() throws RemoteException, UnknownHostException{
        InetAddress host;
        host = InetAddress.getLocalHost();
        return host.getHostAddress();   
    }
    
   
    File[] drive = File.listRoots();
    
    public void statusFlashdisk() {
    t = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(File.listRoots().length>drive.length){
                    drive = File.listRoots();
                    f = getSerialNumber("f:");
                    g = getSerialNumber("g:");
                    h = getSerialNumber("h:");
                }
                else if(File.listRoots().length<drive.length){
                    drive = File.listRoots();
                    f = getSerialNumber("f:");
                    g = getSerialNumber("g:");
                    h = getSerialNumber("h:");
                }
                else if(File.listRoots().length==drive.length){
                    drive = File.listRoots();
                    f = getSerialNumber("f:");
                    g = getSerialNumber("g:");
                    h = getSerialNumber("h:");
                }
            }         
        }      
    });
    t.start();
}
    
    public void cek() {
        t = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    if(flashclient.getProductID()!=null){
                        if(flashclient.getProductID().equals(f) || flashclient.equals(g) || flashclient.equals(h)){
                            flashclient.IP(IPaddress());
                        } 
                    }} catch (RemoteException ex) {
                    Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnknownHostException ex) {
                    Logger.getLogger(Background.class.getName()).log(Level.SEVERE, null, ex);
                }
            }         
        }      
    });
    t.start();
    }
    
    
    
    public static void main(String [] args) throws RemoteException, UnknownHostException{
        
        Background background = new Background();
        
}

}