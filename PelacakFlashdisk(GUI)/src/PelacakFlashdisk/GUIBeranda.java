/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PelacakFlashdisk;



import Flash.Flashdisk;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author acer
 */
public class GUIBeranda extends javax.swing.JPanel implements FlashInterface {
    Flashdisk flashclient;
    private String flashdrive;
    private Boolean type;
    private DefaultTableModel tabel;
    private Object pilihan;
    private Object productID;
    Thread t;
    
   
    /**
     * Creates new form GUIBeranda
     */
    public GUIBeranda() {
        initComponents();
        try{
            flashclient = (Flashdisk) Naming.lookup("rmi://localhost/flashserver");
            flashclient.IP("Tidak ditemukan");
            flashclient.productID("null");
            
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
        
        statusFlashdisk();
    
    }
    
    public JLabel getLabel(){
        return label_ID;
    }
    
    public void table(){
        tabel = new DefaultTableModel(){
            @Override
            public Class getColumnClass(int c) {
                Class tipe = super.getColumnClass(c);
                if(c==2){
                    tipe = Boolean.class;
                }
                
                return tipe; 
            }
            
            public void setValueAt(Object aValue,int row,int col){
                if(col==2){
                    for(int i = 0;i<=tabel.getRowCount()-1;i++){
                        super.setValueAt(false, i, 2);
                    }
                }
                super.setValueAt(aValue, row, col);
            }
        };
        
        tabel.addColumn("Flash Name");
        tabel.addColumn("Product ID");
        tabel.addColumn("Pilihan");
        try{
        Statement stmt = conn.koneksi().createStatement();
        String sql = "select * from flashdisk where id='"+login.getID().getText()+"'";
        ResultSet rs = stmt.executeQuery(sql);
        
        int jumlahbaris = tabel.getRowCount();
         
        for(int i = 0;i<=jumlahbaris-1;i++){
            tabel.setValueAt(rs.getString("Flashdisk_Name"), i, 0);
            tabel.setValueAt(rs.getString("Product_ID"), i, 1);
            tabel.setValueAt(type, i, 2);
        }
        
        while(rs.next()){
            tabel.addRow(new Object[]{
            rs.getString("Flashdisk_Name"),
            rs.getString("Product_ID"),
            false}); 
        }
        jTable1.setModel(tabel);
        
        }
        catch(Exception e){
            System.out.println("Error"+ e);
        }
    }
    
    File[] drive = File.listRoots();

    public void statusFlashdisk() {
    Thread t = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
                FileSystemView fsv = FileSystemView.getFileSystemView();
                String flashname = null;
                if (File.listRoots().length > drive.length) {
                    drive = File.listRoots();
                    flashdrive = drive[drive.length-1].toString();
                    flashname = fsv.getSystemDisplayName(drive[drive.length-1]);
                    jComboBox1.addItem(flashname);
                    
                } else if (File.listRoots().length < drive.length) {
                    drive = File.listRoots();                  
                    jComboBox1.removeItem(flashname);
                }

            }
        }
    });
    t.start();
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
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        popup = new javax.swing.JPopupMenu();
        ganti = new javax.swing.JMenuItem();
        keluar = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btn_hapus1 = new javax.swing.JButton();
        btn_product1 = new javax.swing.JButton();
        btn_cari1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        label_ID = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        popup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                popupMouseClicked(evt);
            }
        });

        ganti.setText("Ganti Password");
        ganti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gantiActionPerformed(evt);
            }
        });
        popup.add(ganti);

        keluar.setText("Keluar");
        keluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keluarActionPerformed(evt);
            }
        });
        popup.add(keluar);

        setBackground(new java.awt.Color(153, 255, 255));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nama Flashdisk", "ID Flashdisk", "Pilihan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setRowHeight(20);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
        }

        btn_hapus1.setBackground(new java.awt.Color(255, 255, 255));
        btn_hapus1.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 14)); // NOI18N
        btn_hapus1.setForeground(new java.awt.Color(255, 255, 255));
        btn_hapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PelacakFlashdisk/DELETE.png"))); // NOI18N
        btn_hapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapus1ActionPerformed(evt);
            }
        });

        btn_product1.setBackground(new java.awt.Color(255, 102, 102));
        btn_product1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        btn_product1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PelacakFlashdisk/DAPATKAN ID.png"))); // NOI18N
        btn_product1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_product1ActionPerformed(evt);
            }
        });

        btn_cari1.setBackground(new java.awt.Color(255, 255, 255));
        btn_cari1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        btn_cari1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PelacakFlashdisk/cari.png"))); // NOI18N
        btn_cari1.setText("Cari");
        btn_cari1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cari1ActionPerformed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 11)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Pilih Flashdisk" }));

        label_ID.setFont(new java.awt.Font("BankGothic Lt BT", 1, 18)); // NOI18N
        label_ID.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PelacakFlashdisk/orang.png"))); // NOI18N
        label_ID.setText("ID");
        label_ID.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_IDMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/PelacakFlashdisk/BERANDA.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Algerian", 0, 18)); // NOI18N
        jLabel4.setText("APLIKASI PELACAK FLASHDISK");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_ID, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_hapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_cari1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_product1, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(83, 83, 83))
            .addGroup(layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addComponent(jLabel4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(label_ID))
                .addGap(27, 27, 27)
                .addComponent(jLabel4)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_product1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_hapus1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cari1))
                .addContainerGap(54, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_hapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapus1ActionPerformed

        
        try{
            String ID = label_ID.getText();
            for(int i=0; i<=tabel.getRowCount()-1; i++){
            pilihan = (Boolean) tabel.getValueAt(i, 2);
            
                if((Boolean) pilihan==true){
            productID= tabel.getValueAt(i, 1);}
            }    
            Statement statement=conn.koneksi().createStatement();
            String sql="delete from flashdisk where product_id='"+productID+"'";
            if(productID!=null){
            ResultSet rs=statement.executeQuery(sql);
            if(rs.next())
            {
                JOptionPane.showMessageDialog(null, "Data telah dihapus");    
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Data tidak terhapus");
            }}
            else{
                System.out.println("Pilih Flashdisk yang ingin dihapus!");
            }
             
            table();
           
        }

        catch(Exception ex)
        {
            System.out.println("Erorr"+ex);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btn_hapus1ActionPerformed

    private void btn_product1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_product1ActionPerformed
    String product_id = GUIBeranda.getSerialNumber(flashdrive);
    String ID = label_ID.getText();
    String flash_name = jComboBox1.getSelectedItem().toString();
        try{
        Statement stmt = conn.koneksi().createStatement();
        String sql = "Insert into flashdisk values ('"+flash_name+"','"+product_id+"','"+ID+"')";
        ResultSet rs = stmt.executeQuery(sql);
        if(rs.next()){
            JOptionPane.showMessageDialog(null, "Flashdisk berhasil terdaftar");
            table();
        }
        else {
            JOptionPane.showMessageDialog(null, "Flashdisk gagal terdaftar");
        }
        
        }
        catch(Exception e){
            System.out.println("Error"+ e);
        }
    }//GEN-LAST:event_btn_product1ActionPerformed

    
    private void btn_cari1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cari1ActionPerformed
        for(int i=0; i<=tabel.getRowCount()-1; i++){
            Boolean pilih = (Boolean) tabel.getValueAt(i, 2);
            
            if((Boolean) pilih==true){
                productID= tabel.getValueAt(i, 1);
            
                try {
                    if(productID!=null){
                    flashclient.productID(productID);
                    }
                    else{
                     JOptionPane.showMessageDialog(null, "Pilih flashdisk yang ingin ditemukan!");
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(GUIBeranda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }}    
        
        t = new Thread(new Runnable() {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    if(flashclient.getIP()!=null){
                        hasil.getTxtIP().setText(flashclient.getIP());
                        if(flashclient.getIP().equals("192.168.76.3")){
                            hasil.getTxtRuang().setText("706");
                        }
                        else{
                            hasil.getTxtRuang().setText("Tidak terdaftar");
                        }
                        
                        
                        
                    }
                    else {
                       
                    }
                } catch (RemoteException ex) {
                    Logger.getLogger(GUIBeranda.class.getName()).log(Level.SEVERE, null, ex);
                }
            }         
        }      
    });
    t.start();
    mainFrame.show(hasil, true);
    beranda.setVisible(false);
        
    }//GEN-LAST:event_btn_cari1ActionPerformed

    private void label_IDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_IDMouseClicked

 
       popup.show(label_ID, evt.getX(), evt.getY());
 
            // TODO add your handling code here:
    }//GEN-LAST:event_label_IDMouseClicked

    private void gantiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gantiActionPerformed
    beranda.setVisible(false);
    mainFrame.show(gantiPassword, true);
    }//GEN-LAST:event_gantiActionPerformed

    private void popupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_popupMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_popupMouseClicked

    private void keluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarActionPerformed
    
    beranda.setVisible(false);
    login.setVisible(true);// TODO add your handling code here:
    }//GEN-LAST:event_keluarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_cari1;
    private javax.swing.JButton btn_hapus1;
    private javax.swing.JButton btn_product1;
    private javax.swing.JMenuItem ganti;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem keluar;
    private javax.swing.JLabel label_ID;
    private javax.swing.JPopupMenu popup;
    // End of variables declaration//GEN-END:variables

}
