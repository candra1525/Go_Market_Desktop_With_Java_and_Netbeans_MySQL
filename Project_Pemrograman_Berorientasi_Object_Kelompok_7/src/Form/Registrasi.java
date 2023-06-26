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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author candr
 */
public class Registrasi extends javax.swing.JFrame {
    
    private String sql;
    private PreparedStatement ps = null;
    private ImageIcon ic;
    private DefaultTableModel dtm;
    private SimpleDateFormat sdf;
    private ResultSet rs = null;
    private String username, nama, tmptlahir, tgllahir, jk, alamat, nohp, email, level, pass, konfirmasipass, tampilan, tanggal, cari, str_tanggal ;
    private int pilihan, row;
    
    
    /**
     * Creates new form Regis
     */
    
    // Construktor
    public Registrasi() 
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
        this.setTitle("Register");
        this.setLocationRelativeTo(null);
        ic = new ImageIcon((getClass().getResource("Img/2-modified (1).png")));
        this.setIconImage(ic.getImage());
    }
    
    // Method Kosongkan Data
    private void KosongkanData()
    {
        tfusername.setText("");
        tfnama.setText("");
        tftempatlahir.setText("");
        dctanggallahir.setDate(null);
        btn_grup_jk.clearSelection();
        tfalamat.setText("");
        tfnohp.setText("");
        tfemail.setText("");
        cblevel.setSelectedItem("=== Pilih ===");
        tfpass.setText("");
        tfkonfirmasipass.setText("");
        
        tfcari.setText("");
        cbusername.setSelected(false);
        cbnama.setSelected(false);
        cbemail.setSelected(false);
        
        btnsave.setEnabled(true);
        tfusername.setEnabled(true);
    }
    
    // Method Tampilkan Data
    private void TampilkanData()
    {
        try
        {
            sql = null;
            sql = "SELECT * FROM tbl_data";
            ps = null;
             
            Object [] kolom = {"Username", "Nama Lengkap", "Tempat Lahir", "Tanggal Lahir", "Jenis Kelamin", "Alamat", "No HP", "Email", "Level", "Password"};
            dtm = new DefaultTableModel(null, kolom);
            tabeldata.setModel(dtm);
            jScrollPane1.setEnabled(true);
            jScrollPane1.setBorder(null);
            jScrollPane1.setViewportView(tabeldata);
            
            
            rs = null;
            ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next())
            {
                username = rs.getString(1);
                nama = rs.getString(2);
                tmptlahir = rs.getString(3);
                tgllahir = rs.getString(4);
                jk = rs.getString(5);
                alamat = rs.getString(6);
                nohp = rs.getString(7);
                email = rs.getString(8);
                level = rs.getString(9);
                pass = rs.getString(10);
                
                
                String baris [] = {username, nama, tmptlahir, tgllahir, jk, alamat, nohp, email, level, pass};
                dtm.addRow(baris);
            }
   
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Maaf data tidak bisa ditampilkan", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // Method Save Data
    private void Save()
    { 
        username = null;
        nama = null;
        tmptlahir = null;
        alamat = null;
        nohp = null;
        email = null;
        level = null;
        pass = null;
        konfirmasipass = null;
        
        
        username = tfusername.getText();
        nama= tfnama.getText();
        tmptlahir = tftempatlahir.getText();
        alamat = tfalamat.getText();
        nohp = tfnohp.getText();
        email = tfemail.getText();
        level = (String) cblevel.getSelectedItem();
        pass = tfpass.getText();
        konfirmasipass = tfkonfirmasipass.getText();
        
        jk = null;
        if(rblaki.isSelected() == true)
        {
            jk = "L";
        }
        else if(rbpr.isSelected() == true)
        {
            jk = "P";
        }
        
        ps = null;
        sql = null;
        sql = "Insert into tbl_data values(?,?,?,?,?,?,?,?,?,?)";
        try
        {
            if(username.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Username belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfusername.requestFocus();
            }
            else if(nama.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Nama Lengkap belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfnama.requestFocus();
            }
            else if(tmptlahir.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Tempat Lahir belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tftempatlahir.requestFocus();
            }
            else if(dctanggallahir.getDate() == null)
            {
               JOptionPane.showMessageDialog(rootPane, "Tanggal Lahir belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
               dctanggallahir.requestFocus();
            }
            else if(rblaki.isSelected() == false && rbpr.isSelected() == false)
            {
                 JOptionPane.showMessageDialog(rootPane, "Jenis Kelamin belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 rblaki.requestFocus();
            }
            else if(alamat.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Alamat belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfalamat.requestFocus();
            }
            else if(nohp.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "No Handphone belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfnohp.requestFocus();
            }
            else if(email.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Email belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfemail.requestFocus();
            }
            else if(level.equals("=== Pilih ==="))
            {
                 JOptionPane.showMessageDialog(rootPane, "Level belum dipilih", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 cblevel.requestFocus();
            }
            else if(pass.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Password belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfpass.requestFocus();
            }
            else if(konfirmasipass.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Konfirmasi Password belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfkonfirmasipass.requestFocus();
            }
            else
            {
                if(pass.equals(konfirmasipass))
                {
                    ps = Koneksi.Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                    tampilan = "yyyy-MM-dd";
                    sdf = new SimpleDateFormat(tampilan);
                    tanggal = String.valueOf(sdf.format(dctanggallahir.getDate()));
                    ps.setString(1, username);
                    ps.setString(2, nama);
                    ps.setString(3, tmptlahir);
                    ps.setString(4, tanggal);
                    ps.setString(5, jk);
                    ps.setString(6, alamat);
                    ps.setString(7, nohp);
                    ps.setString(8, email);
                    ps.setString(9, level);
                    ps.setString(10, pass);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Data Anda Berhasil Disimpan", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
                    TampilkanData();
                    KosongkanData();
                    tfusername.requestFocus();
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane, "Password dan Konfirmasi Tidak Sama !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                    tfkonfirmasipass.setText("");
                    tfkonfirmasipass.requestFocus();
                }
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(this, "Username yang dimaskukkan telah ada !", "Peringatan", JOptionPane.WARNING_MESSAGE);
            JOptionPane.showMessageDialog(this, "Harap masukkan username yang berbeda", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
            tfusername.setText("");
            tfusername.requestFocus();
        }
    }
    
    // Method Cari Data
    private void CariData()
    {
        if(cbusername.isSelected() == false && cbnama.isSelected() == false && cbemail.isSelected() == false)
        {
            JOptionPane.showMessageDialog(null, "Silahkan centang filter data yang telah disediakan", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
            cbusername.requestFocus();
        }
        else
        {
            if(tfcari.getText().equals(""))
            {
                JOptionPane.showMessageDialog(this, "Silahkan isi data yang akan dicari 👌🏻", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                tfcari.requestFocus();
                TampilkanData();
            }
            else
            {
                try
                {
                    cari = tfcari.getText();
                    sql = null;
                    if(cbusername.isSelected() == true)
                    {
                        sql = "SELECT * FROM tbl_data WHERE username like '%" + cari + "%'";
                    }
                    else if(cbnama.isSelected() == true)
                    {
                        sql = "SELECT * FROM tbl_data WHERE nama like '%" + cari + "%'";
                    }
                    else if(cbemail.isSelected() == true)
                    {
                        sql = "SELECT * FROM tbl_data WHERE email like '%" + cari + "%'";
                    }

                    ps = null;
                    Object [] kolom = {"Username", "Nama Lengkap", "Tempat Lahir", "Tanggal Lahir", "Jenis Kelamin", "Alamat", "No HP", "Email", "Level", "Password"};
                    dtm = new DefaultTableModel(null, kolom);
                    tabeldata.setModel(dtm);
                    jScrollPane1.setEnabled(true);
                    jScrollPane1.setBorder(null);
                    jScrollPane1.setViewportView(tabeldata);

                    rs = null;
                    ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                    rs = ps.executeQuery();
                    while(rs.next())
                    {
                        username = rs.getString(1);
                        nama = rs.getString(2);
                        tmptlahir = rs.getString(3);
                        tgllahir = rs.getString(4);
                        jk = rs.getString(5);
                        alamat = rs.getString(6);
                        nohp = rs.getString(7);
                        email = rs.getString(8);
                        level = rs.getString(9);
                        pass = rs.getString(10);

                        String baris [] = {username, nama, tmptlahir, tgllahir, jk, alamat, nohp, email, level, pass};
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
    
    // Method Edit Data
    private void EditData()
    {    
        username = null;
        nama = null;
        tmptlahir = null;
        alamat = null;
        nohp = null;
        email = null;
        level = null;
        pass = null;
        konfirmasipass = null;
        
        username = tfusername.getText();
        nama= tfnama.getText();
        tmptlahir = tftempatlahir.getText();
        alamat = tfalamat.getText();
        nohp = tfnohp.getText();
        email = tfemail.getText();
        level = (String) cblevel.getSelectedItem();
        pass = tfpass.getText();
        konfirmasipass = tfkonfirmasipass.getText();
        
        jk = null;
        if(rblaki.isSelected() == true)
        {
            jk = "L";
        }
        else if(rbpr.isSelected() == true)
        {
            jk = "P";
        }
        
        try
        {
            if(username.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Username belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfusername.requestFocus();
            }
            else if(nama.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Nama Lengkap belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfnama.requestFocus();
            }
            else if(tmptlahir.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Tempat Lahir belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tftempatlahir.requestFocus();
            }
            else if(dctanggallahir.getDate() == null)
            {
               JOptionPane.showMessageDialog(rootPane, "Tanggal Lahir belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
               dctanggallahir.requestFocus();
            }
            else if(rblaki.isSelected() == false && rbpr.isSelected() == false)
            {
                 JOptionPane.showMessageDialog(rootPane, "Jenis Kelamin belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 rblaki.requestFocus();
            }
            else if(alamat.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Alamat belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfalamat.requestFocus();
            }
            else if(nohp.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "No Handphone belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfnohp.requestFocus();
            }
            else if(email.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Email belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfemail.requestFocus();
            }
            else if(level.equals("=== Pilih ==="))
            {
                 JOptionPane.showMessageDialog(rootPane, "Level belum dipilih", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 cblevel.requestFocus();
            }
            else if(pass.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Password belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfpass.requestFocus();
            }
            else if(konfirmasipass.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Konfirmasi Password belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfkonfirmasipass.requestFocus();
            }
            else
            {
                if(pass.equals(konfirmasipass))
                {
                    tampilan = "yyyy-MM-dd";
                    sdf = new SimpleDateFormat(tampilan);
                    tanggal = String.valueOf(sdf.format(dctanggallahir.getDate()));
                    sql = null;
                    sql = "UPDATE tbl_data SET nama='"+ nama +"', tampat_lahir='"+ tmptlahir +"', tanggal_lahir='" + tanggal +"', jenis_kelamin='" + jk +"', alamat = '"+ alamat +"', no_hp='"+ nohp +"', email='" + email +"', level='" + level +"', pword = '"+ pass +"' WHERE username='"+ username +"'";
                    ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(rootPane, "Data Berhasil di Update 👍🏻", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
                    TampilkanData();
                    KosongkanData();
                    tfusername.requestFocus();
                }
                else
                {
                    JOptionPane.showMessageDialog(rootPane, "Password dan Konfirmasi Tidak Sama !", "Pemberitahuan", JOptionPane.WARNING_MESSAGE);
                    tfkonfirmasipass.setText("");
                    tfkonfirmasipass.requestFocus();
                }
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
        username = null;
        nama = null;
        tmptlahir = null;
        alamat = null;
        nohp = null;
        email = null;
        level = null;
        pass = null;
        konfirmasipass = null;
        
        username = tfusername.getText();
        nama= tfnama.getText();
        tmptlahir = tftempatlahir.getText();
        alamat = tfalamat.getText();
        nohp = tfnohp.getText();
        email = tfemail.getText();
        level = (String) cblevel.getSelectedItem();
        pass = tfpass.getText();
        konfirmasipass = tfkonfirmasipass.getText();
        
        jk = null;
        if(rblaki.isSelected() == true)
        {
            jk = "L";
        }
        else if(rbpr.isSelected() == true)
        {
            jk = "P";
        }
        
        try
        {
           if(username.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Username belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfusername.requestFocus();
            }
            else if(nama.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Nama Lengkap belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfnama.requestFocus();
            }
            else if(tmptlahir.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Tempat Lahir belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tftempatlahir.requestFocus();
            }
            else if(dctanggallahir.getDate() == null)
            {
               JOptionPane.showMessageDialog(rootPane, "Tanggal Lahir belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
               dctanggallahir.requestFocus();
            }
            else if(rblaki.isSelected() == false && rbpr.isSelected() == false)
            {
                 JOptionPane.showMessageDialog(rootPane, "Jenis Kelamin belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 rblaki.requestFocus();
            }
            else if(alamat.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Alamat belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfalamat.requestFocus();
            }
            else if(nohp.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "No Handphone belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfnohp.requestFocus();
            }
            else if(email.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Email belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfemail.requestFocus();
            }
            else if(level.equals("=== Pilih ==="))
            {
                 JOptionPane.showMessageDialog(rootPane, "Level belum dipilih", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 cblevel.requestFocus();
            }
            else if(pass.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Password belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfpass.requestFocus();
            }
            else if(konfirmasipass.equals(""))
            {
                 JOptionPane.showMessageDialog(rootPane, "Konfirmasi Password belum diisi", "Perhatian", JOptionPane.WARNING_MESSAGE);
                 tfkonfirmasipass.requestFocus();
            }
            else
            {
                pilihan = JOptionPane.showConfirmDialog(null, "Apakah anda yakin ingin menghapus data ?", "Perhatian", JOptionPane.YES_NO_OPTION);
                if(pilihan == JOptionPane.YES_OPTION)
                {
                    sql = null;
                    sql = "DELETE FROM tbl_data WHERE username = '" + username + "'";
                    ps = Koneksikedatabase.ambilKoneksiDatabase().prepareStatement(sql);
                    ps.executeUpdate();
                    JOptionPane.showMessageDialog(this, "Data berhasil di Hapus 👍🏻", "Pemberitahuan", JOptionPane.INFORMATION_MESSAGE);
                    TampilkanData();
                    KosongkanData();
                    tfusername.requestFocus();
                }
            }
        }
        catch(Exception e)
        {
             JOptionPane.showMessageDialog(this, "Data Gagal di Hapus ! " + e.getMessage(), "Pemberitahuan ", JOptionPane.INFORMATION_MESSAGE);
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        tfusername = new javax.swing.JTextField();
        tfnama = new javax.swing.JTextField();
        tftempatlahir = new javax.swing.JTextField();
        tfalamat = new javax.swing.JTextField();
        dctanggallahir = new com.toedter.calendar.JDateChooser();
        rblaki = new javax.swing.JRadioButton();
        rbpr = new javax.swing.JRadioButton();
        tfnohp = new javax.swing.JTextField();
        tfemail = new javax.swing.JTextField();
        cblevel = new javax.swing.JComboBox<>();
        tfpass = new javax.swing.JPasswordField();
        tfkonfirmasipass = new javax.swing.JPasswordField();
        cbpass = new javax.swing.JCheckBox();
        btnrefresh = new javax.swing.JButton();
        btnsave = new javax.swing.JButton();
        btnedit = new javax.swing.JButton();
        btndelete = new javax.swing.JButton();
        btnback = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabeldata = new javax.swing.JTable() {    public boolean isCellEditable(int rowIndex, int colIndex)    {      return false;     } };
        jLabel13 = new javax.swing.JLabel();
        tfcari = new javax.swing.JTextField();
        btntelusuri = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        cbusername = new javax.swing.JCheckBox();
        cbnama = new javax.swing.JCheckBox();
        cbemail = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 247, 202));
        jPanel1.setMinimumSize(new java.awt.Dimension(0, 0));

        jPanel2.setBackground(new java.awt.Color(255, 247, 202));

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel2.setText("Username");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel3.setText("Nama Lengkap");

        jLabel4.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel4.setText("Tempat Lahir");

        jLabel5.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel5.setText("Tanggal Lahir");

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel6.setText("Jenis Kelamin");

        jLabel7.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel7.setText("Alamat");

        jLabel8.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel8.setText("No Handphone");

        jLabel9.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel9.setText("Email");

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel10.setText("Level");

        jLabel11.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel11.setText("Password");

        jLabel12.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel12.setText("Konfirmasi Password");

        tfusername.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        tfnama.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        tftempatlahir.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tftempatlahir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tftempatlahirActionPerformed(evt);
            }
        });

        tfalamat.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        rblaki.setBackground(new java.awt.Color(255, 247, 202));
        btn_grup_jk.add(rblaki);
        rblaki.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        rblaki.setText("Laki-laki");

        rbpr.setBackground(new java.awt.Color(255, 247, 202));
        btn_grup_jk.add(rbpr);
        rbpr.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        rbpr.setText("Perempuan");

        tfnohp.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        tfemail.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        cblevel.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cblevel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "=== Pilih ===", "Developer", "Admin", "Kasir" }));

        tfpass.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        tfkonfirmasipass.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        cbpass.setBackground(new java.awt.Color(255, 247, 202));
        cbpass.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cbpass.setText("Tampilkan Password");
        cbpass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbpassActionPerformed(evt);
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(btnrefresh)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbpass)
                    .addComponent(tfusername)
                    .addComponent(tfnama)
                    .addComponent(tftempatlahir)
                    .addComponent(tfalamat)
                    .addComponent(dctanggallahir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(rblaki)
                        .addGap(18, 18, 18)
                        .addComponent(rbpr))
                    .addComponent(tfnohp)
                    .addComponent(tfemail)
                    .addComponent(cblevel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfpass)
                    .addComponent(tfkonfirmasipass)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnsave)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(btnedit)
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnback, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btndelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfusername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tfnama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tftempatlahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dctanggallahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rblaki)
                        .addComponent(rbpr))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfalamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tfnohp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(tfemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cblevel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(tfpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfkonfirmasipass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 4, Short.MAX_VALUE)
                .addComponent(cbpass)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnrefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnsave, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnedit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btndelete, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(btnback, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 247, 202));

        tabeldata.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        tabeldata.setModel(new javax.swing.table.DefaultTableModel(
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
        tabeldata.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeldataMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabeldata);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 533, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel13.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel13.setText("Cari");

        tfcari.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N

        btntelusuri.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        btntelusuri.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/Telusuri.png"))); // NOI18N
        btntelusuri.setText("Telusuri");
        btntelusuri.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btntelusuriActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        jLabel14.setText("Filter");

        cbusername.setBackground(new java.awt.Color(255, 247, 202));
        cbusername.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cbusername.setText("Username");
        cbusername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbusernameActionPerformed(evt);
            }
        });

        cbnama.setBackground(new java.awt.Color(255, 247, 202));
        cbnama.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cbnama.setText("Nama Lengkap");
        cbnama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbnamaActionPerformed(evt);
            }
        });

        cbemail.setBackground(new java.awt.Color(255, 247, 202));
        cbemail.setFont(new java.awt.Font("Trebuchet MS", 0, 11)); // NOI18N
        cbemail.setText("Email");
        cbemail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbemailActionPerformed(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/Register.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB", 0, 24)); // NOI18N
        jLabel1.setText("Registrasi");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel17)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tfcari, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btntelusuri))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cbusername)
                                .addGap(18, 18, 18)
                                .addComponent(cbnama)
                                .addGap(18, 18, 18)
                                .addComponent(cbemail))))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(tfcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btntelusuri))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbusername)
                            .addComponent(cbnama)
                            .addComponent(cbemail))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
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

    // Check Box Email
    private void cbemailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbemailActionPerformed
        if(cbemail.isSelected()==true)
        {
            cbnama.setSelected(false);
            cbusername.setSelected(false);
        }
        else
        {
            cbemail.setSelected(false);
        }
    }//GEN-LAST:event_cbemailActionPerformed

    // Check Box Nama Lengkap
    private void cbnamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbnamaActionPerformed
        if(cbnama.isSelected()==true)
        {
            cbemail.setSelected(false);
            cbusername.setSelected(false);
        }
        else
        {
            cbnama.setSelected(false);
        }
    }//GEN-LAST:event_cbnamaActionPerformed

    // Check Box Username
    private void cbusernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbusernameActionPerformed
        if(cbusername.isSelected()==true)
        {
            cbemail.setSelected(false);
            cbnama.setSelected(false);
        }
        else
        {
            cbusername.setSelected(false);
        }
    }//GEN-LAST:event_cbusernameActionPerformed

    // Button Telusuri
    private void btntelusuriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btntelusuriActionPerformed
        CariData();
    }//GEN-LAST:event_btntelusuriActionPerformed

    // Tabel Data Registrasi
    private void tabeldataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeldataMouseClicked
        row = tabeldata.getSelectedRow();
        tfusername.setText(tabeldata.getModel().getValueAt(row, 0).toString());
        tfnama.setText(tabeldata.getModel().getValueAt(row, 1).toString());
        tftempatlahir.setText(tabeldata.getModel().getValueAt(row, 2).toString());

        str_tanggal = String.valueOf(tabeldata.getValueAt(row, 3));
        Date tanggal = null;
        try
        {
            tanggal = new SimpleDateFormat("yyyy-MM-dd").parse(str_tanggal);
        }
        catch (ParseException ex)
        {
            Logger.getLogger(Registrasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        dctanggallahir.setDate(tanggal);

        if(tabeldata.getValueAt(row, 4).toString().equals("L"))
        {
            rblaki.setSelected(true);
        }
        else if(tabeldata.getValueAt(row, 4).toString().equals("P"))
        {
            rbpr.setSelected(true);
        }

        tfalamat.setText(tabeldata.getModel().getValueAt(row, 5).toString());
        tfnohp.setText(tabeldata.getModel().getValueAt(row, 6).toString());
        tfemail.setText(tabeldata.getModel().getValueAt(row, 7).toString());
        cblevel.setSelectedItem(tabeldata.getModel().getValueAt(row, 8).toString());
        tfpass.setText(tabeldata.getModel().getValueAt(row, 9).toString());
        tfkonfirmasipass.setText(tabeldata.getModel().getValueAt(row, 9).toString());

        btnsave.setEnabled(false);
        tfusername.setEnabled(false);
    }//GEN-LAST:event_tabeldataMouseClicked

    // Button Back
    private void btnbackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbackActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnbackActionPerformed

    // Button Delete
    private void btndeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndeleteActionPerformed
        DeleteData();
    }//GEN-LAST:event_btndeleteActionPerformed

    // Button Edit
    private void btneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditActionPerformed
        EditData();
    }//GEN-LAST:event_btneditActionPerformed

    // Button Save
    private void btnsaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsaveActionPerformed
        Save();
    }//GEN-LAST:event_btnsaveActionPerformed

    // Button Refresh
    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        KosongkanData();
        TampilkanData();
    }//GEN-LAST:event_btnrefreshActionPerformed

    // Check Box Tampilkan Password dan Hide Password
    private void cbpassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbpassActionPerformed
        if(cbpass.isSelected() == true)
        {
            cbpass.setText("Sembunyikan Password");
            tfpass.setEchoChar((char)0);
            tfkonfirmasipass.setEchoChar((char)0);
        }
        else
        {
            cbpass.setText("Tampilkan Password");
            tfpass.setEchoChar('*');
            tfkonfirmasipass.setEchoChar('*');
        }
    }//GEN-LAST:event_cbpassActionPerformed

    // Tidak Digunakan
    private void tftempatlahirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tftempatlahirActionPerformed

    }//GEN-LAST:event_tftempatlahirActionPerformed

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
            java.util.logging.Logger.getLogger(Registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btn_grup_jk;
    private javax.swing.JButton btnback;
    private javax.swing.JButton btndelete;
    private javax.swing.JButton btnedit;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsave;
    private javax.swing.JButton btntelusuri;
    private javax.swing.JCheckBox cbemail;
    private javax.swing.JComboBox<String> cblevel;
    private javax.swing.JCheckBox cbnama;
    private javax.swing.JCheckBox cbpass;
    private javax.swing.JCheckBox cbusername;
    private com.toedter.calendar.JDateChooser dctanggallahir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rblaki;
    private javax.swing.JRadioButton rbpr;
    private javax.swing.JTable tabeldata;
    private javax.swing.JTextField tfalamat;
    private javax.swing.JTextField tfcari;
    private javax.swing.JTextField tfemail;
    private javax.swing.JPasswordField tfkonfirmasipass;
    private javax.swing.JTextField tfnama;
    private javax.swing.JTextField tfnohp;
    private javax.swing.JPasswordField tfpass;
    private javax.swing.JTextField tftempatlahir;
    private javax.swing.JTextField tfusername;
    // End of variables declaration//GEN-END:variables
}
