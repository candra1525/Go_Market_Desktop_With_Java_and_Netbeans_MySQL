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
public class Check_Barang extends javax.swing.JFrame {

    /**
     * Creates new form Cari_Barang
     */
    private ImageIcon ic;
    private String sql;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private DefaultTableModel dtm;
    private String kodebarang, namabarang, satuan, jenisbarang, stok, hargabeli, hargajual, namasupp, cari;
    
    // Construktor
    public Check_Barang() 
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
        this.setTitle("Check Barang");
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
            sql = "Select * from tbl_barang";
            ps = null;
            
            Object [] kolom = {"Kode Barang", "Nama Barang", "Satuan", "Jenis Barang", "Stok Barang", "Harga Beli", "Harga Jual", "Nama Supplier"};
            dtm  = new DefaultTableModel(null, kolom);
            tabel_barang.setModel(dtm);
            jScrollPane1.setEnabled(true);
            jScrollPane1.setBorder(null);
            jScrollPane1.setViewportView(tabel_barang);
            
            rs = null;
            ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                kodebarang = rs.getString(1);
                namabarang = rs.getString(2);
                satuan = rs.getString(3);
                jenisbarang = rs.getString(4);
                stok = rs.getString(5);
                hargabeli = rs.getString(6);
                hargajual = rs.getString(7); 
                namasupp = rs.getString(8);
                
                String [] baris  = {kodebarang, namabarang, satuan, jenisbarang, stok, hargabeli, hargajual, namasupp};
                dtm.addRow(baris);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data " + e.getMessage() , "Pemberitahuan", JOptionPane.ERROR_MESSAGE);
        }
    }
       
    // Method Cari Data
    private void CariData()
    {
        if(cbkodebarang.isSelected() == false && cbnamabarang.isSelected() == false)
        {
            JOptionPane.showMessageDialog(null, "Silahkan centang filter data yang telah disediakan", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
            cbkodebarang.requestFocus();
            TampilkanData();
        }
        else
        {
            if(tfcaribarang.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Silahkan isi data yang akan dicari 👌🏻", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfcaribarang.requestFocus();
            }
            else
            {
                try
                {
                    cari = tfcaribarang.getText();
                    sql = null;
                    if(cbkodebarang.isSelected() == true)
                    {
                        sql = "SELECT * FROM tbl_barang WHERE kode_barang like '%" + cari + "%'";
                    }
                    else if(cbnamabarang.isSelected() == true)
                    {
                        sql = "SELECT * FROM tbl_barang WHERE nama_barang like '%" + cari + "%'";
                    }
                     
                    ps = null;
                    Object [] kolom = {"Kode Barang", "Nama Barang", "Satuan", "Jenis Barang", "Stok Barang", "Harga Beli", "Harga Jual", "Nama Supplier"};
                    dtm = new DefaultTableModel(null, kolom);
                    tabel_barang.setModel(dtm);
                    jScrollPane1.setEnabled(true);
                    jScrollPane1.setBorder(null);
                    jScrollPane1.setViewportView(tabel_barang);

                    rs  = null;
                    ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                    rs = ps.executeQuery();
                    while(rs.next())
                    {
                        kodebarang = rs.getString(1);
                        namabarang = rs.getString(2);
                        satuan = rs.getString(3);
                        jenisbarang = rs.getString(4);
                        stok = rs.getString(5);
                        hargabeli = rs.getString(6);
                        hargajual = rs.getString(7); 
                        namasupp = rs.getString(8);

                        String [] baris  = {kodebarang, namabarang, satuan, jenisbarang, stok, hargabeli, hargajual, namasupp};
                        dtm.addRow(baris);
                    }
                }
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak ditemukan !");
                    tfcaribarang.setText("");
                    tfcaribarang.requestFocus();
                }
            }
        }
    }
    
    private void KosongkanData()
    {
       tfcaribarang.setText("");
       cbkodebarang.setSelected(false);
       cbnamabarang.setSelected(false);
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
        tfcaribarang = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbkodebarang = new javax.swing.JCheckBox();
        cbnamabarang = new javax.swing.JCheckBox();
        btncaribarang = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_barang = new javax.swing.JTable()  
        {     
            public boolean isCellEditable(int rowIndex, int colIndex)     
            {       return false;      
            }  
        };

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 247, 202));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel1.setText("Cari Barang");

        tfcaribarang.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tfcaribarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfcaribarangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfcaribarangKeyReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel12.setText("Filter");

        cbkodebarang.setBackground(new java.awt.Color(255, 247, 202));
        cbkodebarang.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cbkodebarang.setText("Kode Barang");
        cbkodebarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbkodebarangActionPerformed(evt);
            }
        });

        cbnamabarang.setBackground(new java.awt.Color(255, 247, 202));
        cbnamabarang.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cbnamabarang.setText("Nama Barang");
        cbnamabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnamabarangActionPerformed(evt);
            }
        });

        btncaribarang.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btncaribarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/Telusuri.png"))); // NOI18N
        btncaribarang.setText("Cari Barang");
        btncaribarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaribarangActionPerformed(evt);
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

        jPanel2.setBackground(new java.awt.Color(255, 247, 202));

        tabel_barang.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tabel_barang.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabel_barang);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
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
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(28, 28, 28))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(39, 39, 39)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(tfcaribarang))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btncaribarang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnrefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(50, 50, 50))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbkodebarang)
                                .addGap(18, 18, 18)
                                .addComponent(cbnamabarang)
                                .addGap(120, 120, 120))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfcaribarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncaribarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(btnrefresh))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbkodebarang)
                    .addComponent(cbnamabarang))
                .addGap(4, 4, 4)
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
    private void tfcaribarangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfcaribarangKeyPressed
        
    }//GEN-LAST:event_tfcaribarangKeyPressed

    // Check Box Kode Barang
    private void cbkodebarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbkodebarangActionPerformed
        if(cbkodebarang.isSelected() == true)
        {
            cbnamabarang.setSelected(false);
        }
        else
        {
            cbkodebarang.setSelected(false);
        }
    }//GEN-LAST:event_cbkodebarangActionPerformed

     // Check Box Nama Barang
    private void cbnamabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnamabarangActionPerformed
        if(cbnamabarang.isSelected() == true)
        {
            cbkodebarang.setSelected(false);
        }
        else
        {
            cbnamabarang.setSelected(false);
        }
    }//GEN-LAST:event_cbnamabarangActionPerformed

     // Tidak Digunakan
    private void tfcaribarangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfcaribarangKeyReleased

    }//GEN-LAST:event_tfcaribarangKeyReleased

    // Button Cari Barang
    private void btncaribarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaribarangActionPerformed
        CariData();
    }//GEN-LAST:event_btncaribarangActionPerformed

    // Button Refresh
    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
       KosongkanData();
       TampilkanData();
    }//GEN-LAST:event_btnrefreshActionPerformed

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
            java.util.logging.Logger.getLogger(Check_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Check_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Check_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Check_Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Check_Barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btncaribarang;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JCheckBox cbkodebarang;
    private javax.swing.JCheckBox cbnamabarang;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel_barang;
    private javax.swing.JTextField tfcaribarang;
    // End of variables declaration//GEN-END:variables
}
