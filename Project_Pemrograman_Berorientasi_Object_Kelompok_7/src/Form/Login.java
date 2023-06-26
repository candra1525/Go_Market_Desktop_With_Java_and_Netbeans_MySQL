package Form;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Koneksi.Koneksikedatabase;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;

public class Login extends javax.swing.JFrame 
{
    private ImageIcon ic;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql, sqlA;
    private int pilihan, keluar;
    
  
    // Construktor
    public Login() 
    {
        initComponents();
        // Tombol Closing X
         setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
         //menambahkan perintah penutupan
        this.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent evt) 
            {
                pilihan = JOptionPane.showConfirmDialog(null, "Apakah anda akan keluar dari program ?", "Tutup Aplikasi", JOptionPane.YES_NO_OPTION);
                if (pilihan == JOptionPane.YES_OPTION) 
                {
                    System.exit(0);
                }
            }
        });
        this.setTitle("Login");
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        ic = new ImageIcon((getClass().getResource("Img/2-modified (1).png")));
        this.setIconImage(ic.getImage());
        
    }
    
    // Method Login
    private void Login()
    {        
        sql = "select * from tbl_data where username = ? and pword = ?";
       
        if(tfusername.getText().equals("") && tfpass.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Username dan Password belum di isi !!!", "Perhatian !", JOptionPane.WARNING_MESSAGE);
            tfusername.requestFocus();
        }
        else if(tfusername.getText().equals(""))
        {   
            JOptionPane.showMessageDialog(this, "Username belum di isi !!!", "Perhatian !!!", JOptionPane.WARNING_MESSAGE);
            tfusername.requestFocus();
        }
        else if(tfpass.getText().equals(""))
        {
            JOptionPane.showMessageDialog(this, "Password belum di isi !!!", "Perhatian !!!", JOptionPane.WARNING_MESSAGE);
            tfpass.requestFocus();
        }
        else
        {
            try
            {
                ps = null;
                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                ps.setString(1, tfusername.getText());
        
                ps.setString(2, String.valueOf(tfpass.getPassword()));
                rs  = ps.executeQuery();

                if(rs.next())
                {
                    if(tfusername.getText().equals(rs.getString("username")) && tfpass.getText().equals(rs.getString("pword")))
                    {
                        sqlA = "Select * From tbl_data where username='"+tfusername.getText()+"'";
                        
                        String nama = null;
                        String level = null;
                        String email = null;
                        ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sqlA);
                        rs = ps.executeQuery();
                        
                        while(rs.next())
                        { 
                            nama = rs.getString(2);
                            email = rs.getString(8);
                            level = rs.getString(9);
                        }
                            
                        if(level.equals("Developer"))
                        {
                            JOptionPane.showMessageDialog(this, "Selamat Anda Berhasil Login", "Information", JOptionPane.INFORMATION_MESSAGE);
                            Main_Menu mm = new Main_Menu();
                            mm.tfuser.setText(nama);
                            mm.tflevel.setText(level);
                            mm.tfemail.setText(email);
                            mm.setVisible(true);
                            KosongkanData();
                            this.dispose();
                        }
                        else if(level.equals("Admin"))
                        {
                            JOptionPane.showMessageDialog(this, "Selamat Anda Berhasil Login", "Information", JOptionPane.INFORMATION_MESSAGE);
                            Main_Menu mm = new Main_Menu();
                            mm.MenuTambahUser.setVisible(false);
                            mm.MenuUlasan.setVisible(false);
                            mm.Laporan.setVisible(false);
                            mm.tfuser.setText(nama);
                            mm.tflevel.setText(level);
                            mm.tfemail.setText(email);
                            mm.setVisible(true);
                            KosongkanData();
                            this.dispose();   
                        }
                        else if(level.equals("Kasir"))
                        {
                            JOptionPane.showMessageDialog(this, "Selamat Anda Berhasil Login", "Information", JOptionPane.INFORMATION_MESSAGE);
                            Main_Menu mm = new Main_Menu();
                            mm.MenuTambahUser.setVisible(false);
                            mm.MenuUlasan.setVisible(false);
                            mm.Laporan.setVisible(false);
                            mm.tfuser.setText(nama);
                            mm.tflevel.setText(level);
                            mm.tfemail.setText(email);
                            mm.setVisible(true);
                            KosongkanData();
                            this.dispose();
                        }
                              
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Login Gagal", "Pemberitahuan", JOptionPane.ERROR_MESSAGE);
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }    
        }
        
    }
    
    // Method Kosongkan Data
    public void KosongkanData()
    {
        tfusername.setText("");
        tfpass.setText("");
    }
    
    // Method Exit Program
    private void ExitProgram()
    {
        keluar = JOptionPane.showConfirmDialog(null, "Apakah anda akan keluar dari program ?", "Tutup Aplikasi", JOptionPane.YES_NO_OPTION);
        if(keluar == JOptionPane.YES_OPTION)
        {
            System.exit(0);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        tfusername = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tfpass = new javax.swing.JPasswordField();
        btnlogin = new javax.swing.JButton();
        btnexit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Berlin Sans FB", 0, 36)); // NOI18N
        jLabel3.setText("LOGIN");

        jLabel6.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/Username.png"))); // NOI18N
        jLabel6.setText("Username");

        tfusername.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Berlin Sans FB", 0, 18)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/password.png"))); // NOI18N
        jLabel5.setText("Password");

        tfpass.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        btnlogin.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnlogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/Login.png"))); // NOI18N
        btnlogin.setText("Login");
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });
        btnlogin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                btnloginKeyPressed(evt);
            }
        });

        btnexit.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        btnexit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/Exit.png"))); // NOI18N
        btnexit.setText("Exit");
        btnexit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnexitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tfusername)
                            .addComponent(tfpass, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btnlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel3))
                                .addGap(0, 154, Short.MAX_VALUE)))
                        .addContainerGap(55, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(tfusername, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(tfpass, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(btnlogin, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(btnexit, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        jPanel2.setBackground(new java.awt.Color(255, 247, 202));

        jLabel1.setBackground(new java.awt.Color(222, 229, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/300x300.png"))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("KELOMPOK 7");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(91, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(2, 2, 2)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Button Login 
    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
        Login();
    }//GEN-LAST:event_btnloginActionPerformed

    // Button Exit Program
    private void btnexitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnexitActionPerformed
       ExitProgram();
    }//GEN-LAST:event_btnexitActionPerformed

    // Button Login KeyPress
    private void btnloginKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_btnloginKeyPressed
        Login();
    }//GEN-LAST:event_btnloginKeyPressed

   
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Tentang_Dev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tentang_Dev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tentang_Dev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tentang_Dev.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnexit;
    private javax.swing.JButton btnlogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPasswordField tfpass;
    private javax.swing.JTextField tfusername;
    // End of variables declaration//GEN-END:variables
}
