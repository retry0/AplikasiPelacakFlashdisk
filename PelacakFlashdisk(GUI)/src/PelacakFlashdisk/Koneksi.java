/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package PelacakFlashdisk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author acer
 */
public final class Koneksi implements FlashInterface {
    
    Connection con;
    
    public Connection koneksi(){
        if(con==null){
          try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            con = DriverManager.getConnection(url,username,password);
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
            System.out.println(ex);
        }  
        }
        
        return con;
            
    }
    
}
