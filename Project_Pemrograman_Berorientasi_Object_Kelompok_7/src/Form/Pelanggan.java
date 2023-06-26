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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author candr
 */
public class Pelanggan extends javax.swing.JFrame {

    /**
     * Creates new form Pelanggan
     */
    private ImageIcon ic;
    private PreparedStatement ps = null;
    private DefaultTableModel dtm;
    private ResultSet rs = null;
    private SimpleDateFormat sdf;
    private String sql;
    private int pilihan, row;
    private String kodepelanggan, namapelanggan, tempatlahir, tgllahir, jk, alamat, nohp, email, jeniskelamin, tampilan, tanggal;
    private String cari;
    private String str_tanggal;
    
    // Construktor
    public Pelanggan() 
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
        TampilkanData();
        this.setTitle("Master Pelanggan");
        this.setLocationRelativeTo(null);
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
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Maaf data tidak bisa ditampilkan", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // Method Kosongkan Data
    private void KosongkanData()
    {
        tfkodepelanggan.setText("");
        tfnamapelanggan.setText("");
        tftempatlahir.setText("");
        dctangallahir.setDate(null);
        btn_grup_jk.clearSelection();
        tfalamat.setText("");
        tfnohandphone.setText("");
        tfemail.setText("");
        
        btnsave.setEnabled(true);
        tfkodepelanggan.setEnabled(true);
    }

    // Method Save
    private void Save()
    {
        kodepelanggan = tfkodepelanggan.getText();
        namapelanggan = tfnamapelanggan.getText();
        tempatlahir = tftempatlahir.getText();
        
        jeniskelamin = null;
        if(rblaki.isSelected() == true)
        {
            jeniskelamin = "L";
        }
        else if(rbpr.isSelected() == true)
        {
            jeniskelamin = "P";
        }
        
        alamat = tfalamat.getText();
        nohp = tfnohandphone.getText();
        email = tfemail.getText();

        ps = null;
        sql = null;
        sql = "Insert into tbl_pelanggan values(?,?,?,?,?,?,?,?)";
        
        try
        {
            if(kodepelanggan.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Kode Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfkodepelanggan.requestFocus();
            }
            else if(namapelanggan.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Nama Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfnamapelanggan.requestFocus();
            }
            else if(tempatlahir.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Tempat Lahir Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tftempatlahir.requestFocus();
            }
            else if(dctangallahir.getDate() == null)
            {
                JOptionPane.showMessageDialog(this, "Tanggal Lahir Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                dctangallahir.requestFocus();
            }
            else if(rblaki.isSelected() == false && rbpr.isSelected() == false)
            {
                JOptionPane.showMessageDialog(this, "Jenis Kelamin Pelanggan belum dipilih", "Perhatian", JOptionPane.WARNING_MESSAGE);
                rblaki.requestFocus();
            }
            else if(alamat.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Alamat Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfalamat.requestFocus();
            }
            else if(nohp.equals(""))
            {
                JOptionPane.showMessageDialog(this, "No Handphone Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfnohandphone.requestFocus();
            }
            else if(email.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Email Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfemail.requestFocus();
            }
            else
            {
                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                tampilan = "yyyy-MM-dd";
                sdf = new SimpleDateFormat(tampilan);
                tanggal = String.valueOf(sdf.format(dctangallahir.getDate()));
                ps.setString(1, kodepelanggan);
                ps.setString(2, namapelanggan);
                ps.setString(3, tempatlahir);
                ps.setString(4, tanggal);
                ps.setString(5, jeniskelamin);
                ps.setString(6, alamat);
                ps.setString(7, nohp);
                ps.setString(8, email);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil disimpan 👍🏻", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                TampilkanData();
                KosongkanData();
                tfkodepelanggan.requestFocus();
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Kode Pelanggan yang dimaskukkan telah ada !", "Peringatan", JOptionPane.WARNING_MESSAGE);
            JOptionPane.showMessageDialog(this, "Harap masukkan Kode Pelanggan yang berbeda", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
            tfkodepelanggan.setText("");
            tfkodepelanggan.requestFocus();
        }
    }
    
    // Method Edir Data
    private void EditData()
    {
        kodepelanggan = tfkodepelanggan.getText();
        namapelanggan = tfnamapelanggan.getText();
        tempatlahir = tftempatlahir.getText();
        jeniskelamin = null;
        
        if(rblaki.isSelected() == true)
        {
            jeniskelamin = "L";
        }
        else if(rbpr.isSelected() == true)
        {
            jeniskelamin = "P";
        }
        
        alamat = tfalamat.getText();
        nohp = tfnohandphone.getText();
        email = tfemail.getText();
        
        try
        {
            if(kodepelanggan.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Kode Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfkodepelanggan.requestFocus();
            }
            else if(namapelanggan.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Nama Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfnamapelanggan.requestFocus();
            }
            else if(tempatlahir.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Tempat Lahir Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tftempatlahir.requestFocus();
            }
            else if(dctangallahir.getDate() == null)
            {
                JOptionPane.showMessageDialog(this, "Tanggal Lahir Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                dctangallahir.requestFocus();
            }
            else if(rblaki.isSelected() == false && rbpr.isSelected() == false)
            {
                JOptionPane.showMessageDialog(this, "Jenis Kelamin Pelanggan belum dipilih", "Perhatian", JOptionPane.WARNING_MESSAGE);
                rblaki.requestFocus();
            }
            else if(alamat.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Alamat Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfalamat.requestFocus();
            }
            else if(nohp.equals(""))
            {
                JOptionPane.showMessageDialog(this, "No Handphone Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfnohandphone.requestFocus();
            }
            else if(email.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Email Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfemail.requestFocus();
            }
            else
            {
                tampilan = "yyyy-MM-dd";
                sdf = new SimpleDateFormat(tampilan);
                tanggal = String.valueOf(sdf.format(dctangallahir.getDate()));
                sql = null;
                sql = "UPDATE tbl_pelanggan SET nama_pelanggan = '" + namapelanggan + "', tempat_lahir = '"+ tempatlahir +"', tanggal_lahir = '"+ tanggal +"', jenis_kelamin = '" + jeniskelamin + "', alamat = '" + alamat + "', no_hp = '"+ nohp +"', email = '"+ email +"' WHERE kode_pelanggan = '" + kodepelanggan + "'";
                ps = null;
                ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil di Update 👍🏻", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                TampilkanData();
                KosongkanData();
                tfkodepelanggan.requestFocus();
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Data Gagal di Edit" + e.getMessage(), "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // Method Delete Data
    private void DeleteData()
    {
        kodepelanggan = tfkodepelanggan.getText();
        namapelanggan = tfnamapelanggan.getText();
        tempatlahir = tftempatlahir.getText();
        jeniskelamin = null;
        
        if(rblaki.isSelected() == true)
        {
            jeniskelamin = "L";
        }
        else if(rbpr.isSelected() == true)
        {
            jeniskelamin = "P";
        }
        
        alamat = tfalamat.getText();
        nohp = tfnohandphone.getText();
        email = tfemail.getText();
        
        try
        {
            if(kodepelanggan.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Kode Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfkodepelanggan.requestFocus();
            }
            else if(namapelanggan.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Nama Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfnamapelanggan.requestFocus();
            }
            else if(tempatlahir.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Tempat Lahir Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tftempatlahir.requestFocus();
            }
            else if(dctangallahir.getDate() == null)
            {
                JOptionPane.showMessageDialog(this, "Tanggal Lahir Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                dctangallahir.requestFocus();
            }
            else if(rblaki.isSelected() == false && rbpr.isSelected() == false)
            {
                JOptionPane.showMessageDialog(this, "Jenis Kelamin Pelanggan belum dipilih", "Perhatian", JOptionPane.WARNING_MESSAGE);
                rblaki.requestFocus();
            }
            else if(alamat.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Alamat Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfalamat.requestFocus();
            }
            else if(nohp.equals(""))
            {
                JOptionPane.showMessageDialog(this, "No Handphone Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfnohandphone.requestFocus();
            }
            else if(email.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Email Pelanggan belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                tfemail.requestFocus();
            }
            else
            {
                pilihan = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ?", "Perhatian", JOptionPane.YES_NO_OPTION);
                if(pilihan == JOptionPane.YES_OPTION)
                {
                    sql = null;
                    sql = "DELETE FROM tbl_pelanggan WHERE kode_pelanggan = '" + kodepelanggan + "'";
                    ps = null;
                    ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Data berhasil di Hapus 👍🏻 ", "Informasi", JOptionPane.INFORMATION_MESSAGE);
                    TampilkanData();
                    KosongkanData();
                    tfkodepelanggan.requestFocus();
                } 
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Data Gagal di Hapus ! " + e.getMessage(), "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // Method Cari Data
    private void CariData()
    {
        if(cbkodepelanggan.isSelected() == false && cbnamapelanggan.isSelected() == false)
        {
            JOptionPane.showMessageDialog(null, "Silahkan centang filter data yang telah disediakan", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
            cbkodepelanggan.requestFocus();
        }
        else
        {
            if(tfcaripelanggan.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Silahkan isi data yang akan dicari 👌🏻", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfcaripelanggan.requestFocus();
                TampilkanData();
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
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_grup_jk = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tfkodepelanggan = new javax.swing.JTextField();
        tfnamapelanggan = new javax.swing.JTextField();
        tftempatlahir = new javax.swing.JTextField();
        btnrefresh = new javax.swing.JButton();
        btnsave = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        btnback = new javax.swing.JButton();
        dctangallahir = new com.toedter.calendar.JDateChooser();
        rblaki = new javax.swing.JRadioButton();
        rbpr = new javax.swing.JRadioButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        tfalamat = new javax.swing.JTextField();
        tfnohandphone = new javax.swing.JTextField();
        tfemail = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_pelanggan = new javax.swing.JTable()
        {
            public boolean isCellEditable(int rowIndex, int colIndex)
            {
                return false; 
            }
        };
        cbnamapelanggan = new javax.swing.JCheckBox();
        cbkodepelanggan = new javax.swing.JCheckBox();
        jLabel12 = new javax.swing.JLabel();
        tfcaripelanggan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        btntelusuri = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 247, 202));
        jPanel1.setPreferredSize(new java.awt.Dimension(1607, 793));

        jPanel2.setBackground(new java.awt.Color(255, 247, 202));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("Kode Pelanggan");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("Nama Pelanggan");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel4.setText("Tempat, Tanggal Lahir");

        tfkodepelanggan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        tfnamapelanggan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        tftempatlahir.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

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
        jLabel9.setText("Jenis Kelamin");

        btnback.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btnback.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/back.png"))); // NOI18N
        btnback.setText("Back");
        btnback.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbackActionPerformed(evt);
            }
        });

        rblaki.setBackground(new java.awt.Color(255, 247, 202));
        btn_grup_jk.add(rblaki);
        rblaki.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        rblaki.setText("Laki - Laki");
        rblaki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rblakiActionPerformed(evt);
            }
        });

        rbpr.setBackground(new java.awt.Color(255, 247, 202));
        btn_grup_jk.add(rbpr);
        rbpr.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        rbpr.setText("Perempuan");
        rbpr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbprActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel8.setText("Alamat");

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel13.setText("No Handphone");

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel14.setText("Email");

        tfalamat.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tfalamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfalamatActionPerformed(evt);
            }
        });

        tfnohandphone.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        tfemail.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(btnrefresh))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rblaki)
                        .addGap(44, 44, 44)
                        .addComponent(rbpr))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfemail, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfnohandphone, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfalamat, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tftempatlahir, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(dctangallahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(tfnamapelanggan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfkodepelanggan, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btnsave)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                                .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btndelete)))))
                .addGap(20, 20, 20))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfkodepelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfnamapelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dctangallahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(tftempatlahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(rblaki)
                    .addComponent(rbpr))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfalamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(tfnohandphone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(tfemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnrefresh, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                    .addComponent(btndelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnedit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnsave, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 247, 202));

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
        tabel_pelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_pelangganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_pelanggan);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        cbnamapelanggan.setBackground(new java.awt.Color(255, 247, 202));
        cbnamapelanggan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cbnamapelanggan.setText("Nama Pelanggan");
        cbnamapelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnamapelangganActionPerformed(evt);
            }
        });

        cbkodepelanggan.setBackground(new java.awt.Color(255, 247, 202));
        cbkodepelanggan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cbkodepelanggan.setText("Kode Pelanggan");
        cbkodepelanggan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbkodepelangganActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel12.setText("Filter");

        tfcaripelanggan.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tfcaripelanggan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfcaripelangganKeyPressed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel11.setText("Cari Pelanggan");

        btntelusuri.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btntelusuri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/Telusuri.png"))); // NOI18N
        btntelusuri.setText("Telusuri");
        btntelusuri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntelusuriActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel1.setText("Pelanggan");

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/Form Pelanggan.png"))); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel5)
                .addGap(26, 26, 26)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(30, 30, 30))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(92, 92, 92)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cbkodepelanggan)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbnamapelanggan))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfcaripelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btntelusuri)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfcaripelanggan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntelusuri))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbkodepelanggan)
                            .addComponent(cbnamapelanggan))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1144, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Button Refresh
    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        KosongkanData();
        TampilkanData();
    }//GEN-LAST:event_btnrefreshActionPerformed

    // Button Save
    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        Save();
    }//GEN-LAST:event_btnsaveActionPerformed

    // Button Edit
    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        EditData();
    }//GEN-LAST:event_btneditActionPerformed

    // Button Delete
    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        DeleteData();
    }//GEN-LAST:event_btndeleteActionPerformed

    // Button Back
    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnbackActionPerformed

    // Tidak Digunakan
    private void rbprActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbprActionPerformed

    }//GEN-LAST:event_rbprActionPerformed

    // Tidak Digunakan
    private void rblakiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rblakiActionPerformed

    }//GEN-LAST:event_rblakiActionPerformed

    // Tabel Pelanggan
    private void tabel_pelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_pelangganMouseClicked
        row = tabel_pelanggan.getSelectedRow();
        tfkodepelanggan.setText(tabel_pelanggan.getModel().getValueAt(row, 0).toString());
        tfnamapelanggan.setText(tabel_pelanggan.getModel().getValueAt(row, 1).toString());
        tftempatlahir.setText(tabel_pelanggan.getModel().getValueAt(row, 2).toString());
        
        str_tanggal = String.valueOf(tabel_pelanggan.getValueAt(row, 3));
        Date tanggal = null;
        try
        {
            tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(str_tanggal);
        }
        catch (ParseException ex)
        {
            Logger.getLogger(Registrasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        dctangallahir.setDate(tanggal);
        
        if(tabel_pelanggan.getValueAt(row, 4).toString().equals("L"))
        {
            rblaki.setSelected(true);
        }
        else if(tabel_pelanggan.getValueAt(row, 4).toString().equals("P"))
        {
            rbpr.setSelected(true);
        }
        
        tfalamat.setText(tabel_pelanggan.getModel().getValueAt(row, 5).toString());
        tfnohandphone.setText(tabel_pelanggan.getModel().getValueAt(row, 6).toString());
        tfemail.setText(tabel_pelanggan.getModel().getValueAt(row, 7).toString());

        btnsave.setEnabled(false);
        tfkodepelanggan.setEnabled(false);
    }//GEN-LAST:event_tabel_pelangganMouseClicked

    // Check Box Nama Pelanggan
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

    // Check Box Kode Pelanggan
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

    // Tidak Digunakan
    private void tfcaripelangganKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfcaripelangganKeyPressed

    }//GEN-LAST:event_tfcaripelangganKeyPressed

    // Button Telusuri Pelanggan
    private void btntelusuriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntelusuriActionPerformed
        CariData();
    }//GEN-LAST:event_btntelusuriActionPerformed

    // Tidak Digunakan
    private void tfalamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfalamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfalamatActionPerformed

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
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btn_grup_jk;
    private javax.swing.JButton btnback;
    public javax.swing.JButton btndelete;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btntelusuri;
    private javax.swing.JCheckBox cbkodepelanggan;
    private javax.swing.JCheckBox cbnamapelanggan;
    private com.toedter.calendar.JDateChooser dctangallahir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rblaki;
    private javax.swing.JRadioButton rbpr;
    private javax.swing.JTable tabel_pelanggan;
    private javax.swing.JTextField tfalamat;
    private javax.swing.JTextField tfcaripelanggan;
    private javax.swing.JTextField tfemail;
    private javax.swing.JTextField tfkodepelanggan;
    private javax.swing.JTextField tfnamapelanggan;
    private javax.swing.JTextField tfnohandphone;
    private javax.swing.JTextField tftempatlahir;
    // End of variables declaration//GEN-END:variables
}
