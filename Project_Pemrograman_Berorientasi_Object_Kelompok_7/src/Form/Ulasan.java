/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import javax.swing.ImageIcon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import Koneksi.Koneksikedatabase;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author candr
 */

public class Ulasan extends javax.swing.JFrame 
{

    /**
     * Creates new form Ulasan
     */
    private ImageIcon ic;
    private String sql;
    private PreparedStatement ps = null;
    private DefaultTableModel dtm;
    private ResultSet rs;
    private String id, nilai, nama, masukkan, ket;
    private String pilih;
    
    // Construktor
    public Ulasan() 
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
        this.setLocationRelativeTo(null);
        this.setTitle("Ulasan");
        ic = new ImageIcon((getClass().getResource("Img/2-modified (1).png")));
        this.setIconImage(ic.getImage());
        TampilkanData();
        
    }
    
    // Method Kosongkan Data
    private void KosongkanData()
    {
        tfid.setText("");
        tfnilai.setText("");
        tfnama.setText("");
        tfmasukkan.setText("");
        tfketerangan.setText("");
        btn_nilai.clearSelection();
    }
    
    // Method Tampilkan Data
    private void TampilkanData()
    {
        try
        {
            sql = null;
            sql = "Select * From tbl_ulasan";
            ps = null;
            
            Object [] kolom = {"ID", "Penilaian", "Nama Lengkap", "Masukkan", "Keterangan"};
            dtm  = new DefaultTableModel(null, kolom);
            tabel_ulasan.setModel(dtm);
            jScrollPane2.setEnabled(true);
            jScrollPane2.setBorder(null);
            jScrollPane2.setViewportView(tabel_ulasan);
            
            
            rs = null;
            ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                id = rs.getString(1);
                nilai = rs.getString(2);
                nama = rs.getString(3);
                masukkan = rs.getString(4);
                ket = rs.getString(5);
               
                String [] baris  = {id, nilai, nama, masukkan, ket};
                dtm.addRow(baris);
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data " + e.getMessage() , "Pemberitahuan", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method SaveEdit Data
    private void SaveEdit()
    {
        id = null;
        nilai = null;
        nama = null;
        masukkan = null;
        ket = null;
        
        id = tfid.getText();
        nilai = tfnilai.getText();
        nama = tfnama.getText();
        masukkan = tfmasukkan.getText();
        ket = tfketerangan.getText();
        
        try
        {
            String sql = "UPDATE tbl_ulasan SET nilai = '" + nilai + "', nama = '" + nama + "', masukkan = '"+ masukkan +"', ket = '"+ ket +"' WHERE id_ulasan = '"+ id +"'"; 
            PreparedStatement ps = null;
            ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(rootPane, "Data berhasil di Edit 👍🏻", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
            TampilkanData();
            KosongkanData();
            tfnama.requestFocus();
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, "Data Gagal di edit" + e.getMessage(), "Pemberitahuan", JOptionPane.ERROR_MESSAGE);
             tfnama.requestFocus();
        }
    }
    
    // Method Delete Data
    private void DeleteData()
    {
        id = null;
        nilai = null;
        nama = null;
        masukkan = null;
        ket = null;
        id = tfid.getText();
        nilai = tfnilai.getText();
        nama = tfnama.getText();
        masukkan = tfmasukkan.getText();
        ket = tfketerangan.getText();
 
        try
        {
            int pilihan = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ?", "Perhatian", JOptionPane.YES_NO_OPTION);
            if(pilihan == JOptionPane.YES_OPTION)
            {
                sql = null;
                sql = "DELETE FROM tbl_ulasan WHERE id_ulasan = '" + id + "'";    
                PreparedStatement ps = null;
                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus 👍🏻", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
                TampilkanData();
                KosongkanData();
                tfnama.requestFocus();
            }
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(rootPane, "Data gagal dihapus !" + e.getMessage(), "Pemberitahuan ", JOptionPane.ERROR_MESSAGE);
        }    
    }
    
    // Method Pilih Data
    private void PilihData()
    {
        if(rb5.isSelected() == true)
        {
            try {
                pilih = "5";
                sql = null;
                sql = "SELECT * FROM tbl_ulasan WHERE nilai like '%" + pilih + "%'";
                ps = null;
                
                Object [] kolom = {"ID", "Penilaian", "Nama Lengkap", "Masukkan", "Keterangan"};
                dtm  = new DefaultTableModel(null, kolom);
                tabel_ulasan.setModel(dtm);
                jScrollPane2.setEnabled(true);
                jScrollPane2.setBorder(null);
                jScrollPane2.setViewportView(tabel_ulasan);
                
                
                rs = null;
                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next())
                {
                    id = rs.getString(1);
                    nilai = rs.getString(2);
                    nama = rs.getString(3);
                    masukkan = rs.getString(4);
                    ket = rs.getString(5);
                    
                    String [] baris  = {id, nilai, nama, masukkan, ket};
                    dtm.addRow(baris);
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(Ulasan.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak ditemukan !");
                rb5.requestFocus();
            }
        }
        else if(rb4.isSelected() == true)
        {
            try 
            {
                pilih = "4";
                sql = null;
                sql = "SELECT * FROM tbl_ulasan WHERE nilai like '%" + pilih + "%'";
                ps = null;
                
                Object [] kolom = {"ID", "Penilaian", "Nama Lengkap", "Masukkan", "Keterangan"};
                dtm  = new DefaultTableModel(null, kolom);
                tabel_ulasan.setModel(dtm);
                jScrollPane2.setEnabled(true);
                jScrollPane2.setBorder(null);
                jScrollPane2.setViewportView(tabel_ulasan);
                
                
                rs = null;
                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next())
                {
                     id = rs.getString(1);
                     nilai = rs.getString(2);
                     nama = rs.getString(3);
                     masukkan = rs.getString(4);
                     ket = rs.getString(5);
                    
                    String [] baris  = {id, nilai, nama, masukkan, ket};
                    dtm.addRow(baris);
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(Ulasan.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak ditemukan !");
                rb5.requestFocus();
            }
        }
        else if(rb3.isSelected() == true)
        {
            try 
            {
                pilih = "3";
                sql = null;
                sql = "SELECT * FROM tbl_ulasan WHERE nilai like '%" + pilih + "%'";
                ps = null;
                
                Object [] kolom = {"ID", "Penilaian", "Nama Lengkap", "Masukkan", "Keterangan"};
                dtm  = new DefaultTableModel(null, kolom);
                tabel_ulasan.setModel(dtm);
                jScrollPane2.setEnabled(true);
                jScrollPane2.setBorder(null);
                jScrollPane2.setViewportView(tabel_ulasan);
                
                
                rs = null;
                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next())
                {
                     id = rs.getString(1);
                     nilai = rs.getString(2);
                     nama = rs.getString(3);
                     masukkan = rs.getString(4);
                     ket = rs.getString(5);
                    
                    String [] baris  = {id, nilai, nama, masukkan, ket};
                    dtm.addRow(baris);
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(Ulasan.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak ditemukan !");
                rb5.requestFocus();
            }
        }
        else if(rb2.isSelected() == true)
        {
            try 
            {
                pilih = "2";
                sql = null;
                sql = "SELECT * FROM tbl_ulasan WHERE nilai like '%" + pilih + "%'";
                ps = null;
                
                Object [] kolom = {"ID", "Penilaian", "Nama Lengkap", "Masukkan", "Keterangan"};
                dtm  = new DefaultTableModel(null, kolom);
                tabel_ulasan.setModel(dtm);
                jScrollPane2.setEnabled(true);
                jScrollPane2.setBorder(null);
                jScrollPane2.setViewportView(tabel_ulasan);
                
                
                rs = null;
                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next())
                {
                     id = rs.getString(1);
                     nilai = rs.getString(2);
                     nama = rs.getString(3);
                     masukkan = rs.getString(4);
                     ket = rs.getString(5);
                    
                    String [] baris  = {id, nilai, nama, masukkan, ket};
                    dtm.addRow(baris);
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(Ulasan.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak ditemukan !");
                rb5.requestFocus();
            }
        }
        else if(rb1.isSelected() == true)
        {
            try 
            {
                pilih = "1";
                sql = null;
                sql = "SELECT * FROM tbl_ulasan WHERE nilai like '%" + pilih + "%'";
                ps = null;
                
                Object [] kolom = {"ID", "Penilaian", "Nama Lengkap", "Masukkan", "Keterangan"};
                dtm  = new DefaultTableModel(null, kolom);
                tabel_ulasan.setModel(dtm);
                jScrollPane2.setEnabled(true);
                jScrollPane2.setBorder(null);
                jScrollPane2.setViewportView(tabel_ulasan);
                
                
                rs = null;
                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                rs = ps.executeQuery();
                while(rs.next())
                {
                     id = rs.getString(1);
                     nilai = rs.getString(2);
                     nama = rs.getString(3);
                     masukkan = rs.getString(4);
                     ket = rs.getString(5);
                    
                    String [] baris  = {id, nilai, nama, masukkan, ket};
                    dtm.addRow(baris);
                }
            } 
            catch (SQLException ex) 
            {
                Logger.getLogger(Ulasan.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(rootPane, "Data yang anda cari tidak ditemukan !");
                rb5.requestFocus();
            }
        } 
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_nilai = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tfnama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tfmasukkan = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        tfnilai = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tfketerangan = new javax.swing.JTextField();
        btnsave = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        tfid = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabel_ulasan = new javax.swing.JTable()  {     public boolean isCellEditable(int rowIndex, int colIndex)     {       return false;      }  };
        jLabel4 = new javax.swing.JLabel();
        rb5 = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        rb4 = new javax.swing.JRadioButton();
        jLabel11 = new javax.swing.JLabel();
        rb3 = new javax.swing.JRadioButton();
        jLabel12 = new javax.swing.JLabel();
        rb2 = new javax.swing.JRadioButton();
        jLabel13 = new javax.swing.JLabel();
        rb1 = new javax.swing.JRadioButton();
        jLabel14 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 247, 202));

        jPanel2.setBackground(new java.awt.Color(255, 247, 202));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("Nama");

        tfnama.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("Pendapat, Saran, dan Kritik");

        tfmasukkan.setColumns(20);
        tfmasukkan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tfmasukkan.setRows(5);
        jScrollPane1.setViewportView(tfmasukkan);

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel5.setText("Penilaian : ");

        tfnilai.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tfnilai.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel7.setText("Keterangan");

        tfketerangan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        btnsave.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btnsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/save.png"))); // NOI18N
        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btndelete.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btndelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/delete.png"))); // NOI18N
        btndelete.setText("Delete");
        btndelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndeleteActionPerformed(evt);
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

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel8.setText("ID");

        tfid.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tfid.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
                    .addComponent(tfnama)
                    .addComponent(tfnilai)
                    .addComponent(tfketerangan)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnrefresh)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btndelete))
                    .addComponent(tfid))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tfid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tfnilai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tfnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tfketerangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 247, 202));

        tabel_ulasan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tabel_ulasan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabel_ulasan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_ulasanMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabel_ulasan);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel4.setText("Cari Ulasan Berdasarkan : ");

        rb5.setBackground(new java.awt.Color(255, 247, 202));
        btn_nilai.add(rb5);
        rb5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb5ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/star.png"))); // NOI18N
        jLabel10.setText("5");

        rb4.setBackground(new java.awt.Color(255, 247, 202));
        btn_nilai.add(rb4);
        rb4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb4ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/star.png"))); // NOI18N
        jLabel11.setText("4");

        rb3.setBackground(new java.awt.Color(255, 247, 202));
        btn_nilai.add(rb3);
        rb3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb3ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/star.png"))); // NOI18N
        jLabel12.setText("3");

        rb2.setBackground(new java.awt.Color(255, 247, 202));
        btn_nilai.add(rb2);
        rb2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb2ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/star.png"))); // NOI18N
        jLabel13.setText("2");

        rb1.setBackground(new java.awt.Color(255, 247, 202));
        btn_nilai.add(rb1);
        rb1.setIconTextGap(10);
        rb1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rb1ActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/star.png"))); // NOI18N
        jLabel14.setText("1");

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel1.setText("Ulasan");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/Ulasan.png"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(rb5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addGap(30, 30, 30)
                                .addComponent(rb4)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel11)
                                .addGap(30, 30, 30)
                                .addComponent(rb3)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel12)
                                .addGap(30, 30, 30)
                                .addComponent(rb2)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel13)
                                .addGap(30, 30, 30)
                                .addComponent(rb1)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel14))
                            .addComponent(jLabel4))
                        .addGap(0, 63, Short.MAX_VALUE)))
                .addGap(90, 90, 90))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rb3)
                            .addComponent(rb5)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rb2)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rb4)
                            .addComponent(rb1)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(43, 43, 43))
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

    // Button Delete 
    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        DeleteData();
    }//GEN-LAST:event_btndeleteActionPerformed

    // Tabel Ulasan
    private void tabel_ulasanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_ulasanMouseClicked
        int row = tabel_ulasan.getSelectedRow();
        tfid.setText(tabel_ulasan.getModel().getValueAt(row, 0).toString());
        tfnilai.setText(tabel_ulasan.getModel().getValueAt(row, 1).toString());
        tfnama.setText(tabel_ulasan.getModel().getValueAt(row, 2).toString());
        tfmasukkan.setText(tabel_ulasan.getModel().getValueAt(row, 3).toString());
        tfketerangan.setText(tabel_ulasan.getModel().getValueAt(row, 4).toString());
    }//GEN-LAST:event_tabel_ulasanMouseClicked

    // Nilai 2
    private void rb2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb2ActionPerformed
       PilihData();
    }//GEN-LAST:event_rb2ActionPerformed

    // Nilai 1
    private void rb1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb1ActionPerformed
       PilihData();
    }//GEN-LAST:event_rb1ActionPerformed

    // Button Refresh
    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
       KosongkanData();
       TampilkanData();
    }//GEN-LAST:event_btnrefreshActionPerformed

    // Button SaveEdit
    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
       SaveEdit();
    }//GEN-LAST:event_btnsaveActionPerformed

    // Nilai 5
    private void rb5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb5ActionPerformed
       PilihData();
    }//GEN-LAST:event_rb5ActionPerformed

    // Nilai 4
    private void rb4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb4ActionPerformed
       PilihData();
    }//GEN-LAST:event_rb4ActionPerformed

    // Nilai 3
    private void rb3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rb3ActionPerformed
       PilihData();
    }//GEN-LAST:event_rb3ActionPerformed

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
            java.util.logging.Logger.getLogger(Ulasan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ulasan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ulasan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ulasan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ulasan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btn_nilai;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rb1;
    private javax.swing.JRadioButton rb2;
    private javax.swing.JRadioButton rb3;
    private javax.swing.JRadioButton rb4;
    private javax.swing.JRadioButton rb5;
    private javax.swing.JTable tabel_ulasan;
    private javax.swing.JTextField tfid;
    private javax.swing.JTextField tfketerangan;
    private javax.swing.JTextArea tfmasukkan;
    private javax.swing.JTextField tfnama;
    private javax.swing.JTextField tfnilai;
    // End of variables declaration//GEN-END:variables
}
