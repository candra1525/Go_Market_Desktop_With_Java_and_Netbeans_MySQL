/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import Koneksi.Koneksikedatabase;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author candr
 */
public class Check_Pelanggan extends javax.swing.JFrame {

    /**
     * Creates new form Cari_Pelanggan
     */
    private ImageIcon ic;
    private String sql;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private DefaultTableModel dtm;
    private String kodepelanggan, namapelanggan, tempatlahir, tgllahir, jk, alamat, nohp, email;
    private String cari;
    
    // Construktor
    public Check_Pelanggan() 
    {
        initComponents();
        // Tutup Frame
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        //menambahkan perintah penutupan
        this.addWindowListener(new WindowAdapter() 
        {
            public void windowClosing(WindowEvent evt) 
            {
                dispose();
            }
        });
        this.setTitle("Check Pelanggan");
        this.setLocationRelativeTo(null);
        TampilkanData();
        ic = new ImageIcon((getClass().getResource("Img/2-modified (1).png")));
        this.setIconImage(ic.getImage());
    }
    
    // Method Tampilkan Data
    private void TampilkanData()
    {
        try
        {
            sql = null;
            sql = "SELECT * FROM tbl_pelanggan";
            
            ps = null;
            Object [] kolom = {"Kode Pelanggan", "Nama Pelanggan", "Tempat Lahir", "Tanggal Lahir", "Jenis Kelamin", "Alamat", "No HP", "Email"};
            dtm = new DefaultTableModel(null, kolom);
            tabel_pelanggan.setModel(dtm);
            jScrollPane1.setEnabled(true);
            jScrollPane1.setBorder(null);
            jScrollPane1.setViewportView(tabel_pelanggan);
            
            
            rs = null;
            ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                kodepelanggan = rs.getString(1);
                namapelanggan = rs.getString(2);
                tempatlahir = rs.getString(3);
                tgllahir = rs.getString(4);
                jk = rs.getString(5);
                alamat = rs.getString(6);
                nohp = rs.getString(7);
                email = rs.getString(8);
                
                String baris [] = {kodepelanggan, namapelanggan, tempatlahir, tgllahir, jk, alamat, nohp, email};
                dtm.addRow(baris);
            }
   
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, "Data tidak dapat ditampilkan ! " + e.getMessage());
        
        }  
    }
    
    // Method Cari Data
    private void CariData()
    {
        if(cbkodepelanggan.isSelected() == false && cbnamapelanggan.isSelected() == false)
        {
            JOptionPane.showMessageDialog(null, "Silahkan centang filter data yang telah disediakan", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
            cbkodepelanggan.requestFocus();
            TampilkanData();
        }
        else
        {
            if(tfcaripelanggan.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Kolom masih kosong !", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfcaripelanggan.requestFocus();
            }
            else
            {
                try
                {
                    cari = tfcaripelanggan.getText();
                    sql = null;
                    if(cbkodepelanggan.isSelected() == true)
                    {
                        sql = "SELECT * FROM tbl_pelanggan WHERE kode_pelanggan like '%" + cari + "%'";
                    }
                    else if(cbnamapelanggan.isSelected() == true)
                    {
                        sql = "SELECT * FROM tbl_pelanggan WHERE nama_pelanggan like '%" + cari + "%'";
                    }

                    ps = null;

                    Object [] kolom = {"Kode Pelanggan", "Nama Pelanggan", "Tempat Lahir", "Tanggal Lahir", "Jenis Kelamin", "Alamat", "No HP", "Email"};
                    dtm = new DefaultTableModel(null, kolom);
                    tabel_pelanggan.setModel(dtm);
                    jScrollPane1.setEnabled(true);
                    jScrollPane1.setBorder(null);
                    jScrollPane1.setViewportView(tabel_pelanggan);


                    rs = null;
                    ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                    rs = ps.executeQuery();
                    while(rs.next())
                    {
                        kodepelanggan = rs.getString(1);
                        namapelanggan = rs.getString(2);
                        tempatlahir = rs.getString(3);
                        tgllahir = rs.getString(4);
                        jk = rs.getString(5);
                        alamat = rs.getString(6);
                        nohp = rs.getString(7);
                        email = rs.getString(8);

                        String baris [] = {kodepelanggan, namapelanggan, tempatlahir, tgllahir, jk, alamat, nohp, email};
                        dtm.addRow(baris);
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak ditemukan !");
                }
            }
        }
    }

    // Method Kosongkan Data
    private void KosongkanData()
    {
       tfcaripelanggan.setText("");
       cbkodepelanggan.setSelected(false);
       cbnamapelanggan.setSelected(false);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        tfcaripelanggan = new javax.swing.JTextField();
        btnrefresh = new javax.swing.JButton();
        btncaripelanggan = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        cbkodepelanggan = new javax.swing.JCheckBox();
        cbnamapelanggan = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pelanggan = new javax.swing.JTable()  {     public boolean isCellEditable(int rowIndex, int colIndex)     {       return false;      }  };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 247, 202));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel1.setText("Cari Pelanggan");

        tfcaripelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfcaripelangganKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfcaripelangganKeyReleased(evt);
            }
        });

        btnrefresh.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btnrefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/refresh.png"))); // NOI18N
        btnrefresh.setText("Refresh");
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });

        btncaripelanggan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btncaripelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/Telusuri.png"))); // NOI18N
        btncaripelanggan.setText("Cari Pelanggan");
        btncaripelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaripelangganActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel12.setText("Filter");

        cbkodepelanggan.setBackground(new java.awt.Color(255, 247, 202));
        cbkodepelanggan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cbkodepelanggan.setText("Kode Pelanggan");
        cbkodepelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbkodepelangganActionPerformed(evt);
            }
        });

        cbnamapelanggan.setBackground(new java.awt.Color(255, 247, 202));
        cbnamapelanggan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cbnamapelanggan.setText("Nama Pelanggan");
        cbnamapelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnamapelangganActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 247, 202));

        tabel_pelanggan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tabel_pelanggan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabel_pelanggan);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 549, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfcaripelanggan)
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbkodepelanggan)
                                .addGap(18, 18, 18)
                                .addComponent(cbnamapelanggan)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnrefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btncaripelanggan))
                        .addGap(42, 42, 42))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfcaripelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncaripelanggan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnrefresh)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbkodepelanggan)
                            .addComponent(cbnamapelanggan))))
                .addGap(13, 13, 13)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Tidak Digunakan
    private void tfcaripelangganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfcaripelangganKeyPressed

    }//GEN-LAST:event_tfcaripelangganKeyPressed

    // Tidak Digunakan
    private void tfcaripelangganKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfcaripelangganKeyReleased

    }//GEN-LAST:event_tfcaripelangganKeyReleased

    // Button Refresh
    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
       KosongkanData();
       TampilkanData();
    }//GEN-LAST:event_btnrefreshActionPerformed

    // Button Cari Pelanggan
    private void btncaripelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaripelangganActionPerformed
        CariData();
    }//GEN-LAST:event_btncaripelangganActionPerformed

    // Combo Box Kode Pelanggan
    private void cbkodepelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbkodepelangganActionPerformed
        if(cbkodepelanggan.isSelected() == true)
        {
            cbnamapelanggan.setSelected(false);
        }
        else
        {
            cbkodepelanggan.setSelected(false);
        }
    }//GEN-LAST:event_cbkodepelangganActionPerformed

    // Combo Box Nama Pelanggan
    private void cbnamapelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnamapelangganActionPerformed
        if(cbnamapelanggan.isSelected() == true)
        {
            cbkodepelanggan.setSelected(false);
        }
        else
        {
            cbnamapelanggan.setSelected(false);
        }
    }//GEN-LAST:event_cbnamapelangganActionPerformed

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
            java.util.logging.Logger.getLogger(Check_Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Check_Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Check_Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Check_Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Check_Pelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncaripelanggan;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JCheckBox cbkodepelanggan;
    private javax.swing.JCheckBox cbnamapelanggan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel_pelanggan;
    private javax.swing.JTextField tfcaripelanggan;
    // End of variables declaration//GEN-END:variables
}
