package PelacakFlashdisk;

import Flash.Flashdisk;
import java.awt.Component;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import javax.swing.JOptionPane;




public class Frame extends javax.swing.JFrame implements FlashInterface {
    static Flashdisk flashclient;
    static String ruangan;
    static String alamat;
    
    public Frame() {
        initComponents();
        try{
            flashclient = (Flashdisk) Naming.lookup("rmi://localhost:1099/flashserver");
        }catch (NotBoundException ex) { 
            JOptionPane.showMessageDialog(null, "Terjadi Error dengan Pesan: "+ ex.getMessage());
            System.exit(1);
        }catch (MalformedURLException ex){
            JOptionPane.showMessageDialog(null, "Terjadi Error dengan Pesan: "+ ex.getMessage());
            System.exit(1);
        }catch (RemoteException ex){
            JOptionPane.showMessageDialog(null, "Terjadi Error dengan Pesan: "+ ex.getMessage());
            System.exit(1);
        }
        
        
    }
    
    public void show(Component comp, Boolean bol){
        mainFrame.getContentPane().add(comp);
        mainFrame.setLocation(350,150);
        mainFrame.setSize(600, 450);
        mainFrame.setVisible(true);
        comp.setVisible(bol);
        mainFrame.setDefaultCloseOperation(Frame.EXIT_ON_CLOSE);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) throws RemoteException {
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {     
                mainFrame.show(login, true);
               
                 
                
            }           
        });
        //cek();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


}
