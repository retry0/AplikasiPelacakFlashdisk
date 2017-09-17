package PelacakFlashdisk;



public interface FlashInterface {
            String url = "jdbc:oracle:thin:@localhost:1521:XE" ;
            String username = "GEGE" ;
            String password = "mhs" ;
                       
            Frame mainFrame = new Frame();
            GUIPendaftaran daftar = new GUIPendaftaran();
            GUILogin login = new GUILogin();
            GUIBeranda beranda = new GUIBeranda();
            GUIGantiPassword gantiPassword = new GUIGantiPassword();
            GUIHasil hasil = new GUIHasil();
            Koneksi conn = new Koneksi();
            
            
                                  
}
