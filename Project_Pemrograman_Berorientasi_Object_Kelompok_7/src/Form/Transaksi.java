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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author candr
 */
public class Transaksi extends javax.swing.JFrame {

    /**
     * Creates new form Penjualan_Barang
     */
    private PreparedStatement ps = null;
    private ResultSet rs  = null;
    private ImageIcon ic;
    private SimpleDateFormat sdf;
    private DefaultTableModel dtm;
    private String ubahtanggal, tanggal;
    private int stokbarang, jmljual, sisastok;
    private String sql, sqlA, sqlB, sqlupdate;
    private int pilihan, total, bayar, kembali;
    private int stokbrg, jmljual1, sisastok1;
    private String cari;
    private int baris;
    
    
    // Construktor
    public Transaksi() 
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
        this.setTitle("Transaksi");
        this.setLocationRelativeTo(null);
        ic = new ImageIcon((getClass().getResource("Img/2-modified (1).png")));
        this.setIconImage(ic.getImage());
        TampilkanDataTransaksi();
    }
    
    // Method Kosongkan Data
    private void KosongkanData()
    {
        tfidtrans.setText("");
        tfkodetransaksi.setText("");
        dctanggaltransaksi.setDate(null);
        tfkodepelanggan.setText("");
        tfnamapelangan.setText("");
        tfalamatpelanggan.setText("");
        tfnohp.setText("");
        tfkodebarang.setText("");
        tfnamabarang.setText("");
        tfsatuanbarang.setText("");
        tfjenisbarang.setText("");
        tfhargajual.setText("");
        tfstokbarang.setText("");
        tfbanyakqty.setText("");
        tftotal.setText("");
        tfbayar.setText("");
        tfkembali.setText("");
        tfcaritransaksi.setText("");
        
        btnsave.setEnabled(true);
    }
    
    // Method Tampilkan Data Transaksi
    private void TampilkanDataTransaksi()
    {
        try
        {
            sql = null;
            sql = "SELECT * FROM rtrans";
            ps = null;
            
            Object [] kolom = {"Id Trans", "Kode Trans", "Tanggal Trans", "Kode Pel", "Nama Pel", "Alamat", "No HP" ,"Kode Brg", "Nama Brg", "Satuan", "Jenis Barang", "Harga Jual", "Stok", "Qty", "Total", "Bayar"};
            dtm = new DefaultTableModel(null, kolom);
            tbl_trans.setModel(dtm);
            jScrollPane1.setEnabled(true);
            jScrollPane1.setBorder(null);
            jScrollPane1.setViewportView(tbl_trans);
            
            rs  = null;
            ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                String id = rs.getString(1);
                String kodetrans = rs.getString(2);
                String tgl = rs.getString(3);
                
                String kodepel = rs.getString(4);
                String namapel = rs.getString(5);
                String alamat = rs.getString(9);
                String telp = rs.getString(10);
                
                String kodebarang = rs.getString(12);
                String namabarang = rs.getString(13);
                String satuan = rs.getString(14);
                String jenisbarang = rs.getString(15);
                
                String hargajual = rs.getString(18);
                String stok = rs.getString(16);
                String qty = rs.getString(20);
                String total = rs.getString(21);
                String bayar = rs.getString(22);
                
                String baris [] = {id, kodetrans, tgl, kodepel, namapel, alamat, telp, kodebarang, namabarang, satuan, jenisbarang, hargajual, stok, qty, total, bayar};
                dtm.addRow(baris);
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Data gagal ditampilkan ! " + e.getMessage(), "Perhatian", JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    // Method Tampilkan Data Pelanggan
    private void TampilkanDataPelanggan()
    {
        try
        {
            sql = null;
            sql = "SELECT * FROM tbl_pelanggan WHERE kode_pelanggan = '" + tfkodepelanggan.getText() + "'";
            ps = null;
            
            rs  = null;
            ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
            rs = ps.executeQuery();
            
            
            if(rs.next())
            {
                tfkodepelanggan.setText(rs.getString(1));
                tfnamapelangan.setText(rs.getString(2));
                tfalamatpelanggan.setText(rs.getString(6));
                tfnohp.setText(rs.getString(7));
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Maaf data pelanggan tidak ditemukan", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfkodepelanggan.requestFocus();
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage(), "Perhatian", JOptionPane.ERROR_MESSAGE);
        
        }  
    }
    
    // Method Tampilkan Data Barang
    private void TampilkanDataBarang()
    {
        try
        {
            sql = null;
            sql = "SELECT * FROM tbl_barang WHERE kode_barang = '" + tfkodebarang.getText()+ "'";
            ps = null;
            
            
            rs  = null;
            ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
            rs = ps.executeQuery();
            if(rs.next())
            {
               tfkodebarang.setText(rs.getString(1));
               tfnamabarang.setText(rs.getString(2));
               tfsatuanbarang.setText(rs.getString(3));
               tfjenisbarang.setText(rs.getString(4));
               tfhargajual.setText(rs.getString(7)); 
               tfstokbarang.setText(rs.getString(5));
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Maaf data pelanggan tidak ditemukan", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfkodepelanggan.requestFocus();
            }
        }
        catch (Exception e)
        {
            JOptionPane.showMessageDialog(this, "Error " + e.getMessage(), "Perhatian", JOptionPane.ERROR_MESSAGE);
        }            
    }
    
    // Method Cari Data Transaksi
    private void CariDataTransaksi()
    {
        try
        {
            if(tfcaritransaksi.getText().equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Kolom pencarian transaksi masih kosong !", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfcaritransaksi.requestFocus();
            }
            else
            {
                cari = tfcaritransaksi.getText();
                sql = null;
                sql = "select * from rtrans WHERE id_transaksi like '%" + cari + "%' OR kode_transaksi like '%" + cari + "%'";

                Object [] kolom = {"Id Trans", "No Trans","Tanggal Trans", "Kode Pel", "Nama Pel", "Alamat", "No HP" ,"Kode Brg", "Nama Brg", "Satuan", "Jenis Brg", "Harga Jual", "Stok", "Qty", "Total Harga", "Bayar"};
                dtm = new DefaultTableModel(null, kolom);
                tbl_trans.setModel(dtm);
                jScrollPane1.setEnabled(true);
                jScrollPane1.setBorder(null);
                jScrollPane1.setViewportView(tbl_trans);


                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                rs = ps.executeQuery();

                while(rs.next())
                {
                    String id = rs.getString(1);
                    String kodetrans = rs.getString(2);
                    String tgl = rs.getString(3);

                    String kodepel = rs.getString(4);
                    String namapel = rs.getString(5);
                    String alamat = rs.getString(9);
                    String telp = rs.getString(10);

                    String kodebarang = rs.getString(12);
                    String namabarang = rs.getString(13);
                    String satuan = rs.getString(14);
                    String jenisbarang = rs.getString(15);

                    String hargajual = rs.getString(18);
                    String stok = rs.getString(16);
                    String qty = rs.getString(20);
                    String total = rs.getString(21);
                    String bayar = rs.getString(22);

                    String baris [] = {id, kodetrans, tgl, kodepel, namapel, alamat, telp, kodebarang, namabarang, satuan, jenisbarang, hargajual, stok, qty, total, bayar};
                    dtm.addRow(baris);
                }
            }
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, "Error, " + e.getMessage(), "Perhatian", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Method Save
    private void Save()
    {
        try
        {
            if(tfkodetransaksi.getText().equals("") || dctanggaltransaksi.getDate() == null || tfkodepelanggan.getText().equals("") || tfkodebarang.getText().equals("") || tfbanyakqty.getText().equals("") || tfbayar.getText().equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Lengkapi data terlebih dahulu !", "Perhatian", JOptionPane.WARNING_MESSAGE);
            }
            else 
            {
                sqlA = null;
                sqlA = "SELECT * FROM tbl_transaksi WHERE kode_transaksi = '"+ tfkodetransaksi.getText()+"' AND kode_barang = '" + tfkodebarang.getText() +"'";
                
                rs = null;
                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sqlA, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                rs = ps.executeQuery();
                
                if(rs.first() == true)
                {
                    JOptionPane.showMessageDialog(rootPane, "Data Transaksi Telah Ada");
                }
                else if(rs.first() == false)
                {
                    ps = null;
                    sqlB = null;
                    sqlB = "Insert into tbl_transaksi(kode_transaksi, tgl_trans, kode_pelanggan, kode_barang, qty, total, uangbayar) values('"+ tfkodetransaksi.getText()+ "','" + tanggal + "', '" + tfkodepelanggan.getText() + "', '" + tfkodebarang.getText() + "', '" + tfbanyakqty.getText() +"', '" + tftotal.getText() +"', '" + tfbayar.getText() + "')";
                    
                    ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sqlB);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil disimpan", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    
                    // Mengurangi Stok Database
                    stokbarang = 0;
                    jmljual = 0;
                    sisastok = 0;
                    
                    stokbarang = Integer.parseInt(tfstokbarang.getText());
                    jmljual = Integer.parseInt(tfbanyakqty.getText());
                    ps = null;
                    sisastok = (stokbarang-jmljual);
                    
                    sqlupdate = "Update tbl_barang set stok_barang='"+sisastok+"' where kode_barang = '" + tfkodebarang.getText()+"'";
                    ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sqlupdate);
                    ps.executeUpdate();
                    
                    tfkodebarang.setText("");
                    tfnamabarang.setText("");
                    tfsatuanbarang.setText("");
                    tfjenisbarang.setText("");
                    tfhargajual.setText("");
                    tfstokbarang.setText("");
                    tfbanyakqty.setText("");
                    tftotal.setText("");
                    tfbayar.setText("");
                    tfkembali.setText("");
                    
                    TampilkanDataTransaksi();   
                }
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        } 
        
    }
    
    // Method Edit
    private void Edit()
    {
        try
        {
            if(tfkodetransaksi.getText().equals("") || dctanggaltransaksi.getDate() == null || tfkodepelanggan.getText().equals("") || tfkodebarang.getText().equals("") || tfbanyakqty.getText().equals("") || tfbayar.getText().equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Pilih data yang akan dihapus terlebih dahulu !", "Perhatian", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                sql = null;
                sql = "Update tbl_transaksi set kode_transaksi = '"+ tfkodetransaksi.getText()+"', tgl_trans = '"+ tanggal +"', kode_pelanggan = '"+ tfkodepelanggan.getText()+"',kode_barang = '"+ tfkodebarang.getText()+"', qty = '"+ tfbanyakqty.getText()+"', total = '"+ tftotal.getText()+"', uangbayar = '"+ tfbayar.getText()+"' WHERE id_transaksi = '"+ tfidtrans.getText()+"'";
                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data Berhasil diedit");
                
                stokbrg = Integer.parseInt(tfstokbarang.getText());
                jmljual1 = Integer.parseInt(tfbanyakqty.getText());
                    
                sisastok1 = (stokbrg - jmljual1);
                sql = null;
                sql = "Update tbl_barang set stok_barang='"+sisastok1+"' where kode_barang = '" + tfkodebarang.getText()+"'";
                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                ps.executeUpdate();
                
                TampilkanDataTransaksi();
                TampilkanDataBarang();
                KosongkanData();
            
            
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    // Method Delete
    private void Delete()
    {
        try
        {
            if(tfkodetransaksi.getText().equals("") || dctanggaltransaksi.getDate() == null || tfkodepelanggan.getText().equals("") || tfkodebarang.getText().equals("") || tfbanyakqty.getText().equals("") || tfbayar.getText().equals(""))
            {
                JOptionPane.showMessageDialog(rootPane, "Lengkapi data terlebih dahulu !", "Perhatian", JOptionPane.WARNING_MESSAGE);
            }
            else
            {
                pilihan = JOptionPane.showConfirmDialog(rootPane, "Apakah anda yakin ingin menghapus data ?", "Pemberitahuan ", JOptionPane.YES_NO_OPTION);
                if(pilihan == JOptionPane.YES_OPTION)
                {
                    ps = null;
                    sql = "Update tbl_barang set stok_barang='"+tfstokbarang.getText()+"' where kode_barang = '" + tfkodebarang.getText()+"'";
                    ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                    ps.executeUpdate();
                    TampilkanDataBarang();

                    sql = null;
                    sql = "Delete From tbl_transaksi WHERE id_transaksi = '"+tfidtrans.getText()+"'";
                    ps = null;
                    ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Data Berhasil dihapus");
                    TampilkanDataTransaksi();
                    KosongkanData();
                } 
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(rootPane, "Error " + e.getMessage());
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
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tfkodetransaksi = new javax.swing.JTextField();
        dctanggaltransaksi = new com.toedter.calendar.JDateChooser();
        tfidtrans = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tfkodepelanggan = new javax.swing.JTextField();
        tfnamapelangan = new javax.swing.JTextField();
        tfalamatpelanggan = new javax.swing.JTextField();
        tfnohp = new javax.swing.JTextField();
        btncaripelanggan = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        tfkodebarang = new javax.swing.JTextField();
        tfnamabarang = new javax.swing.JTextField();
        tfsatuanbarang = new javax.swing.JTextField();
        tfjenisbarang = new javax.swing.JTextField();
        tfhargajual = new javax.swing.JTextField();
        tfstokbarang = new javax.swing.JTextField();
        tfbanyakqty = new javax.swing.JTextField();
        tftotal = new javax.swing.JTextField();
        btncaribarang = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        tfbayar = new javax.swing.JTextField();
        tfkembali = new javax.swing.JTextField();
        btnrefresh = new javax.swing.JButton();
        btnsave = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnback = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        tfcaritransaksi = new javax.swing.JTextField();
        btntelusuri = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_trans = new javax.swing.JTable()  {     public boolean isCellEditable(int rowIndex, int colIndex)     {       return false;      }  };
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 247, 202));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Tranasaksi");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel7.setText("Kode Transaksi");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel8.setText("Tanggal Transaksi");

        dctanggaltransaksi.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dctanggaltransaksiPropertyChange(evt);
            }
        });

        tfidtrans.setEnabled(false);

        jLabel25.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel25.setText("Id Transaksi");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfkodetransaksi)
                    .addComponent(dctanggaltransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfidtrans, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(tfidtrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfkodetransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dctanggaltransaksi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Pelanggan");

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel9.setText("Kode Pelanggan");

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel10.setText("Nama Pelanggan");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel11.setText("Alamat Pelanggan");

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel12.setText("No Handphone");

        tfnamapelangan.setEnabled(false);

        tfalamatpelanggan.setEnabled(false);

        tfnohp.setEnabled(false);

        btncaripelanggan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btncaripelanggan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/Telusuri.png"))); // NOI18N
        btncaripelanggan.setText("Cari Pelanggan");
        btncaripelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaripelangganActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfnamapelangan)
                    .addComponent(tfalamatpelanggan)
                    .addComponent(tfnohp, javax.swing.GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)
                    .addComponent(tfkodepelanggan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncaripelanggan)
                .addGap(21, 21, 21))
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfkodepelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncaripelanggan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(tfnamapelangan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tfalamatpelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(tfnohp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Barang");

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel13.setText("Kode Barang");

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel14.setText("Nama Barang");

        jLabel15.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel15.setText("Satuan Barang");

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel17.setText("Jenis Barang");

        jLabel18.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel18.setText("Harga Jual");

        jLabel19.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel19.setText("Stok Barang");

        jLabel20.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel20.setText("Qty");

        jLabel21.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel21.setText("Total");

        tfnamabarang.setEnabled(false);
        tfnamabarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfnamabarangActionPerformed(evt);
            }
        });

        tfsatuanbarang.setEnabled(false);

        tfjenisbarang.setEnabled(false);

        tfhargajual.setEnabled(false);

        tfstokbarang.setEnabled(false);

        tfbanyakqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfbanyakqtyKeyReleased(evt);
            }
        });

        tftotal.setEnabled(false);

        btncaribarang.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btncaribarang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/Telusuri.png"))); // NOI18N
        btncaribarang.setText("Cari Barang");
        btncaribarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncaribarangActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel16.setText("Uang Bayar");

        jLabel24.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel24.setText("Uang Kembali");

        tfbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tfbayarKeyReleased(evt);
            }
        });

        tfkembali.setEnabled(false);

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

        btnback.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/back.png"))); // NOI18N
        btnback.setText("Back");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfkembali)
                            .addComponent(tfbayar)
                            .addComponent(tfnamabarang)
                            .addComponent(tfsatuanbarang)
                            .addComponent(tfjenisbarang)
                            .addComponent(tfhargajual)
                            .addComponent(tfstokbarang)
                            .addComponent(tfbanyakqty)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(tfkodebarang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btncaribarang, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(tftotal)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnrefresh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btndelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnsave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(9, 9, 9)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tfkodebarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncaribarang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tfnamabarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(tfsatuanbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(tfjenisbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(tfhargajual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(tfstokbarang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(tfbanyakqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(tftotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(tfbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfkembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24))
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnrefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnsave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnback, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(btndelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jLabel22.setBackground(new java.awt.Color(255, 255, 255));
        jLabel22.setFont(new java.awt.Font("Trebuchet MS", 1, 11)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Pencarian");

        jLabel23.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel23.setText("Cari ");

        btntelusuri.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btntelusuri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/Telusuri.png"))); // NOI18N
        btntelusuri.setText("Telusuri");
        btntelusuri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntelusuriActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tfcaritransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btntelusuri)
                .addGap(23, 23, 23))
            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(tfcaritransaksi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btntelusuri))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(255, 247, 202));

        tbl_trans.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_trans.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_transMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_trans);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel1.setText("Transaksi");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/Form Transaksi.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 506, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(30, 30, 30))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
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

    // Button Cari Data Barang
    private void btncaribarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaribarangActionPerformed
        TampilkanDataBarang();
    }//GEN-LAST:event_btncaribarangActionPerformed

    // Button Cari Data Pelanggan
    private void btncaripelangganActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncaripelangganActionPerformed
      TampilkanDataPelanggan();
    }//GEN-LAST:event_btncaripelangganActionPerformed

    // Button Telusuri Data Transaksi
    private void btntelusuriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntelusuriActionPerformed
        CariDataTransaksi();
    }//GEN-LAST:event_btntelusuriActionPerformed

    // Key Released STOK
    private void tfbanyakqtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfbanyakqtyKeyReleased
       int stok, hargajual, qty, total;
       stok = Integer.parseInt(tfstokbarang.getText());
       hargajual = Integer.parseInt(tfhargajual.getText());
       qty = Integer.parseInt(tfbanyakqty.getText());
       
       if(stok < qty)
        {
            JOptionPane.showMessageDialog(null, "Maaf, Stok barang Tidak Cukup");
            tfbanyakqty.setText("");
            tftotal.setText("");
            tfbanyakqty.requestFocus();
        }
        else
        {
            total = hargajual * qty;
            tftotal.setText(String.valueOf(total));
        } 
    }//GEN-LAST:event_tfbanyakqtyKeyReleased

    // Date Chooser
    private void dctanggaltransaksiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dctanggaltransaksiPropertyChange
       try
        {
            if(dctanggaltransaksi.getDate() != null)
            {
                ubahtanggal = "yyyy-MM-dd";
                sdf = new SimpleDateFormat(ubahtanggal);
                tanggal = String.valueOf(sdf.format(dctanggaltransaksi.getDate()));
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Tanggal tidak bisa diubah !" + e.getMessage());
        }
    }//GEN-LAST:event_dctanggaltransaksiPropertyChange

    // Key Released Bayar
    private void tfbayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfbayarKeyReleased
        total = Integer.parseInt(tftotal.getText());
        bayar = Integer.parseInt(tfbayar.getText());
        
        kembali = (bayar - total);
        tfkembali.setText(String.valueOf(kembali));
    }//GEN-LAST:event_tfbayarKeyReleased

    // Tabel Transaksi
    private void tbl_transMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_transMouseClicked
        baris = tbl_trans.getSelectedRow();

        tfidtrans.setText(tbl_trans.getModel().getValueAt(baris, 0).toString());
        tfkodetransaksi.setText(tbl_trans.getModel().getValueAt(baris, 1).toString());
        
        String tgl = (String) tbl_trans.getModel().getValueAt(baris, 2);
        
        try
        {
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date d = sdf.parse(tgl);
            dctanggaltransaksi.setDate(d);
        }
        catch(ParseException ex)
        {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        
        tfkodepelanggan.setText(tbl_trans.getModel().getValueAt(baris, 3).toString());
        tfnamapelangan.setText(tbl_trans.getModel().getValueAt(baris, 4).toString());
        tfalamatpelanggan.setText(tbl_trans.getModel().getValueAt(baris, 5).toString());
        tfnohp.setText(tbl_trans.getModel().getValueAt(baris, 6).toString());
        tfkodebarang.setText(tbl_trans.getModel().getValueAt(baris, 7).toString());
        tfnamabarang.setText(tbl_trans.getModel().getValueAt(baris, 8).toString());
        tfsatuanbarang.setText(tbl_trans.getModel().getValueAt(baris, 9).toString());
        tfjenisbarang.setText(tbl_trans.getModel().getValueAt(baris, 10).toString());
        tfhargajual.setText(tbl_trans.getModel().getValueAt(baris, 11).toString());
        
        tfbanyakqty.setText(tbl_trans.getModel().getValueAt(baris, 13).toString());
        tftotal.setText(tbl_trans.getModel().getValueAt(baris, 14).toString());
        tfbayar.setText(tbl_trans.getModel().getValueAt(baris, 15).toString());
        
        btnsave.setEnabled(false);
        
        String stokbrg;
        int stokbrg2, jmljual, returnstok;
        stokbrg = (String) tbl_trans.getModel().getValueAt(baris, 12);
        jmljual = Integer.parseInt(tfbanyakqty.getText());
        stokbrg2 = Integer.parseInt(stokbrg);
        
        returnstok = (jmljual + stokbrg2);
        tfstokbarang.setText(String.valueOf(returnstok));
       
    }//GEN-LAST:event_tbl_transMouseClicked

    // Tidak Terpakai
    private void tfnamabarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfnamabarangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfnamabarangActionPerformed

    // Button Save
    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        Save();
    }//GEN-LAST:event_btnsaveActionPerformed

    // Button Edit
    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        Edit();
    }//GEN-LAST:event_btneditActionPerformed

    // Button Refresh
    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        KosongkanData();
        TampilkanDataTransaksi();
    }//GEN-LAST:event_btnrefreshActionPerformed

    // Button Delete
    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        Delete();
    }//GEN-LAST:event_btndeleteActionPerformed

    // Button Back
    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
       dispose();
    }//GEN-LAST:event_btnbackActionPerformed

    
    
    
    
    
    
    
    
    
    
    
    
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
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Transaksi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Transaksi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnback;
    private javax.swing.JButton btncaribarang;
    private javax.swing.JButton btncaripelanggan;
    public javax.swing.JButton btndelete;
    public javax.swing.JButton btnedit;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btntelusuri;
    private com.toedter.calendar.JDateChooser dctanggaltransaksi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_trans;
    private javax.swing.JTextField tfalamatpelanggan;
    private javax.swing.JTextField tfbanyakqty;
    private javax.swing.JTextField tfbayar;
    private javax.swing.JTextField tfcaritransaksi;
    public javax.swing.JTextField tfhargajual;
    private javax.swing.JTextField tfidtrans;
    public javax.swing.JTextField tfjenisbarang;
    private javax.swing.JTextField tfkembali;
    public javax.swing.JTextField tfkodebarang;
    private javax.swing.JTextField tfkodepelanggan;
    private javax.swing.JTextField tfkodetransaksi;
    public javax.swing.JTextField tfnamabarang;
    private javax.swing.JTextField tfnamapelangan;
    private javax.swing.JTextField tfnohp;
    public javax.swing.JTextField tfsatuanbarang;
    public javax.swing.JTextField tfstokbarang;
    private javax.swing.JTextField tftotal;
    // End of variables declaration//GEN-END:variables
}
