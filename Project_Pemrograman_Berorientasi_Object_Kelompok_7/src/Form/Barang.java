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
import javax.swing.JFrame;

/**
 *
 * @author candr
 */
public class Barang extends javax.swing.JFrame 
{

    private ImageIcon ic;
    private ResultSet rs;
    private String sql;
    private PreparedStatement ps = null; 
    private DefaultTableModel dtm;
    private String cari;
    private String kodebarang, namabarang, satuan, jenisbarang, stok, hargabeli, hargajual, namasupp;
    private int pilihan;
    
    
    /**
     * Creates new form Barang
     */
    
    // Construktor
    public Barang() 
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
        this.setTitle("Master Barang");
        this.setLocationRelativeTo(null);
        ic = new ImageIcon((getClass().getResource("Img/2-modified (1).png")));
        this.setIconImage(ic.getImage());
        TampilkanData();
    }

    // Method Kosongkan Data
    private void KosongkanData()
    {
        tfkodebarang.setText("");
        tfnamabarang.setText("");
        cbsatuan.setSelectedItem("=== Pilih ===");
        cbjenis.setSelectedItem("=== Pilih ===");
        tfstok.setText("");
        tfhargabeli.setText("");
        tfhargajual.setText("");
        tfnamasupp.setText("");
        tfcaribarang.setText("");
        
        cbkodebarang.setSelected(false);
        cbnamabarang.setSelected(false);
        
        btnsave.setEnabled(true);
        tfkodebarang.setEnabled(true);
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
    
    // Method Save
    private void Save()
    {
        kodebarang = null;
        namabarang = null;
        satuan = null;
        jenisbarang = null;
        stok = null;
        hargabeli = null;
        hargajual = null;
        namasupp = null;
         
        kodebarang = tfkodebarang.getText();
        namabarang = tfnamabarang.getText();
        satuan = (String) cbsatuan.getSelectedItem();
        jenisbarang = (String) cbjenis.getSelectedItem();
        stok = tfstok.getText();
        hargabeli = tfhargabeli.getText();
        hargajual = tfhargajual.getText();
        namasupp = tfnamasupp.getText();
        
        ps = null;
        sql = null;
        sql = "Insert into tbl_barang values(?,?,?,?,?,?,?,?)";
        try
        {
            
            if(kodebarang.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Kode Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfkodebarang.requestFocus();
            }
            else if(namabarang.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Nama Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfnamabarang.requestFocus();
            }
            else if(satuan.equals("=== Pilih ==="))
            {
                JOptionPane.showMessageDialog(this, "Satuan Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                cbsatuan.requestFocus();
            }
            else if(jenisbarang.equals("=== Pilih ==="))
            {
                JOptionPane.showMessageDialog(this, "Jenis Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                cbjenis.requestFocus();
            }
            else if(stok.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Stok Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfstok.requestFocus();
            }
            else if(hargabeli.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Harga Beli Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfhargabeli.requestFocus();
            }
            else if(hargajual.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Harga Jual Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfhargajual.requestFocus();
            }
            else if(namasupp.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Nama Supplier Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfnamasupp.requestFocus();
            }
            else
            {
                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                ps.setString(1, kodebarang);
                ps.setString(2, namabarang);
                ps.setString(3, satuan);
                ps.setString(4, jenisbarang);
                ps.setString(5, stok);
                ps.setString(6, hargabeli);
                ps.setString(7, hargajual);
                ps.setString(8, namasupp);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan 👍🏻", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                TampilkanData();
                KosongkanData();
                tfkodebarang.requestFocus();
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Kode Barang yang dimaskukkan telah ada !", "Peringatan", JOptionPane.WARNING_MESSAGE);
            JOptionPane.showMessageDialog(this, "Harap masukkan Kode Barang yang berbeda", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
            tfkodebarang.setText("");
            tfkodebarang.requestFocus();
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

                    ResultSet rs  = null;
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
                    KosongkanData();
                    tfcaribarang.requestFocus();
                }
            }
        }
    }
    
    // Method Edit Data
    private void EditData()
    {
        kodebarang = null;
        namabarang = null;
        satuan = null;
        jenisbarang = null;
        stok = null;
        hargabeli = null;
        hargajual = null;
        namasupp = null;
        
        kodebarang = tfkodebarang.getText();
        namabarang = tfnamabarang.getText();
        satuan = (String) cbsatuan.getSelectedItem();
        jenisbarang = (String) cbjenis.getSelectedItem();
        stok = tfstok.getText();
        hargabeli = tfhargabeli.getText();
        hargajual = tfhargajual.getText();
        namasupp = tfnamasupp.getText();
        
        try
        {
            if(kodebarang.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Kode Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfkodebarang.requestFocus();
            }
            else if(namabarang.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Nama Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfnamabarang.requestFocus();
            }
            else if(satuan.equals("=== Pilih ==="))
            {
                JOptionPane.showMessageDialog(this, "Satuan Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                cbsatuan.requestFocus();
            }
            else if(jenisbarang.equals("=== Pilih ==="))
            {
                JOptionPane.showMessageDialog(this, "Jenis Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                cbjenis.requestFocus();
            }
            else if(stok.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Stok Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfstok.requestFocus();
            }
            else if(hargabeli.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Harga Beli Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfhargabeli.requestFocus();
            }
            else if(hargajual.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Harga Jual Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfhargajual.requestFocus();
            }
            else if(namasupp.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Nama Supplier Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfnamasupp.requestFocus();
            }
            else
            {
                sql = null;
                sql = "UPDATE tbl_barang SET nama_barang='"+ namabarang +"', satuan='"+ satuan +"', jenis_barang='" + jenisbarang +"', stok='" + stok+"', harga_beli='" + hargabeli +"', harga_jual = '"+ hargajual +"', nama_supp='"+ namasupp +"' WHERE kode_barang='"+ kodebarang +"'";
                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(rootPane, "Data berhasil di Edit 👍🏻", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
                TampilkanData();
                KosongkanData();
            }
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(null, "Data Gagal di edit" + e.getMessage(), "Pemberitahuan", JOptionPane.ERROR_MESSAGE);
             tfkodebarang.requestFocus();
        }
    }
    
    // Method Delete Data
    private void DeleteData()
    {
        kodebarang = null;
        namabarang = null;
        satuan = null;
        jenisbarang = null;
        stok = null;
        hargabeli = null;
        hargajual = null;
        namasupp = null;
        
        kodebarang = tfkodebarang.getText();
        namabarang = tfnamabarang.getText();
        satuan = (String) cbsatuan.getSelectedItem();
        jenisbarang = (String) cbjenis.getSelectedItem();
        stok = tfstok.getText();
        hargabeli = tfhargabeli.getText();
        hargajual = tfhargajual.getText();
        namasupp = tfnamasupp.getText();
        
        try
        {
            if(kodebarang.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Kode Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfkodebarang.requestFocus();
            }
            else if(namabarang.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Nama Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfnamabarang.requestFocus();
            }
            else if(satuan.equals("=== Pilih ==="))
            {
                JOptionPane.showMessageDialog(this, "Satuan Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                cbsatuan.requestFocus();
            }
            else if(jenisbarang.equals("=== Pilih ==="))
            {
                JOptionPane.showMessageDialog(this, "Jenis Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                cbjenis.requestFocus();
            }
            else if(stok.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Stok Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfstok.requestFocus();
            }
            else if(hargabeli.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Harga Beli Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfhargabeli.requestFocus();
            }
            else if(hargajual.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Harga Jual Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfhargajual.requestFocus();
            }
            else if(namasupp.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Nama Supplier Barang belum diisi !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfnamasupp.requestFocus();
            }
            else
            {
                pilihan = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ?", "Perhatian", JOptionPane.YES_NO_OPTION);
                if(pilihan == JOptionPane.YES_OPTION)
                {
                    sql = "DELETE FROM tbl_barang WHERE kode_barang = '" + kodebarang + "'";
                    ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Data berhasil dihapus 👍🏻", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
                    TampilkanData();
                    KosongkanData();
                }  
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, "Data gagal dihapus !" + e.getMessage(), "Pemberitahuan ", JOptionPane.ERROR_MESSAGE);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfkodebarang = new javax.swing.JTextField();
        tfnamabarang = new javax.swing.JTextField();
        cbsatuan = new javax.swing.JComboBox<>();
        tfstok = new javax.swing.JTextField();
        tfhargabeli = new javax.swing.JTextField();
        tfhargajual = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tfnamasupp = new javax.swing.JTextField();
        btnrefresh = new javax.swing.JButton();
        btnsave = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cbjenis = new javax.swing.JComboBox<>();
        btnback = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_barang = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; 
            }
        };
        jLabel11 = new javax.swing.JLabel();
        tfcaribarang = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        cbkodebarang = new javax.swing.JCheckBox();
        cbnamabarang = new javax.swing.JCheckBox();
        btntelusuri = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 247, 202));

        jPanel2.setBackground(new java.awt.Color(255, 247, 202));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("Kode Barang");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("Nama Barang");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel4.setText("Satuan Barang");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel5.setText("Stok Barang");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel6.setText("Harga Beli");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel7.setText("Harga Jual");

        tfkodebarang.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        tfnamabarang.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        cbsatuan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cbsatuan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=== Pilih ===", "Buah", "Bungkus", "Butir", "Dus", "Gram", "Gulung", "Kaleng", "Karung", "Kilogram", "Liter", "Lusin", "Pasang", "Unit", "Lainnya", " ", " ", " " }));

        tfstok.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        tfhargabeli.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        tfhargajual.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel8.setText("Nama Supplier");

        tfnamasupp.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tfnamasupp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfnamasuppActionPerformed(evt);
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

        btnsave.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btnsave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/save.png"))); // NOI18N
        btnsave.setText("Save");
        btnsave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsaveActionPerformed(evt);
            }
        });

        btnedit.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btnedit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/edit.png"))); // NOI18N
        btnedit.setText("Edit");
        btnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditActionPerformed(evt);
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

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel9.setText("Jenis Barang");

        cbjenis.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cbjenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=== Pilih ===", "Aksesoris", "Makanan", "Minuman", "Pakaian", "Peralatan", "Sembako", "Lainnya", " " }));

        btnback.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/back.png"))); // NOI18N
        btnback.setText("Back");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8)
                    .addComponent(btnrefresh))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfhargajual, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfhargabeli, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfstok, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbjenis, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbsatuan, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfkodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(btnsave)
                            .addGap(24, 24, 24)
                            .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(btndelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(tfnamasupp, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnback))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfkodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbsatuan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbjenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfstok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(tfhargabeli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfhargajual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfnamasupp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnrefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btndelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 247, 202));

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
        tabel_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_barangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_barang);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel11.setText("Cari Barang");

        tfcaribarang.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tfcaribarang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfcaribarangKeyPressed(evt);
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

        btntelusuri.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btntelusuri.setText("Telusuri");
        btntelusuri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntelusuriActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/form barang.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 0, 24)); // NOI18N
        jLabel1.setText("Barang");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel10)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel1))
                    .addComponent(jLabel10))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel12)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbkodebarang)
                                .addGap(18, 18, 18)
                                .addComponent(cbnamabarang))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tfcaribarang, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btntelusuri)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(31, 31, 31))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(tfcaribarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntelusuri))
                        .addGap(14, 14, 14)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbkodebarang)
                            .addComponent(cbnamabarang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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
   
    // Button Edit
    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
       EditData();
    }//GEN-LAST:event_btneditActionPerformed

    // Button Refresh
    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
     KosongkanData();
     TampilkanData();
    }//GEN-LAST:event_btnrefreshActionPerformed

    // Button Save
    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
      Save();
    }//GEN-LAST:event_btnsaveActionPerformed

    // Button Delete
    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
       DeleteData();
    }//GEN-LAST:event_btndeleteActionPerformed

    // Button Back
    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnbackActionPerformed

    // Tabel Barang
    private void tabel_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_barangMouseClicked
       int row = tabel_barang.getSelectedRow();
       tfkodebarang.setText(tabel_barang.getModel().getValueAt(row, 0).toString());
       tfnamabarang.setText(tabel_barang.getModel().getValueAt(row, 1).toString());
       cbsatuan.setSelectedItem(tabel_barang.getModel().getValueAt(row, 2).toString());
       cbjenis.setSelectedItem(tabel_barang.getModel().getValueAt(row, 3).toString());
       tfstok.setText(tabel_barang.getModel().getValueAt(row, 4).toString());
       tfhargabeli.setText(tabel_barang.getModel().getValueAt(row, 5).toString());
       tfhargajual.setText(tabel_barang.getModel().getValueAt(row, 6).toString());
       tfnamasupp.setText(tabel_barang.getModel().getValueAt(row, 7).toString());
       
       btnsave.setEnabled(false);
       tfkodebarang.setEnabled(false);
    }//GEN-LAST:event_tabel_barangMouseClicked

    // Button Telusuri
    private void btntelusuriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntelusuriActionPerformed
        CariData();
    }//GEN-LAST:event_btntelusuriActionPerformed

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
    private void tfnamasuppActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfnamasuppActionPerformed
      
    }//GEN-LAST:event_tfnamasuppActionPerformed

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
            java.util.logging.Logger.getLogger(Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Barang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Barang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    public javax.swing.JButton btndelete;
    public javax.swing.JButton btnedit;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btntelusuri;
    private javax.swing.JComboBox<String> cbjenis;
    private javax.swing.JCheckBox cbkodebarang;
    private javax.swing.JCheckBox cbnamabarang;
    private javax.swing.JComboBox<String> cbsatuan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tabel_barang;
    private javax.swing.JTextField tfcaribarang;
    private javax.swing.JTextField tfhargabeli;
    private javax.swing.JTextField tfhargajual;
    private javax.swing.JTextField tfkodebarang;
    private javax.swing.JTextField tfnamabarang;
    private javax.swing.JTextField tfnamasupp;
    private javax.swing.JTextField tfstok;
    // End of variables declaration//GEN-END:variables
}
