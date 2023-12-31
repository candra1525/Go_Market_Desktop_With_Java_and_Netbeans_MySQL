/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Form;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;


/**
 *
 * @author candr
 */
public class LoadingScreen extends javax.swing.JFrame {

    /**
     * Creates new form LoadingScreen
     */
    private ImageIcon ic;
    
    // Construktor
    public LoadingScreen() 
    {
        initComponents();
        setTitle("Loading Screen");
        ic = new ImageIcon((getClass().getResource("Img/2-modified (1).png")));
        this.setIconImage(ic.getImage());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backgroundpanel = new javax.swing.JPanel();
        namaKelompok = new javax.swing.JLabel();
        LoadingStatus = new javax.swing.JLabel();
        LoadingBar = new javax.swing.JProgressBar();
        LoadingValue = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        backgroundpanel.setBackground(new java.awt.Color(255, 247, 202));

        namaKelompok.setFont(new java.awt.Font("Trebuchet MS", 1, 30)); // NOI18N
        namaKelompok.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        namaKelompok.setText("Kelompok 7");

        LoadingStatus.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        LoadingStatus.setText("Loading...");

        LoadingBar.setForeground(new java.awt.Color(51, 102, 255));

        LoadingValue.setFont(new java.awt.Font("Trebuchet MS", 0, 16)); // NOI18N
        LoadingValue.setText("0 %");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Form/Img/400x400.png"))); // NOI18N

        javax.swing.GroupLayout backgroundpanelLayout = new javax.swing.GroupLayout(backgroundpanel);
        backgroundpanel.setLayout(backgroundpanelLayout);
        backgroundpanelLayout.setHorizontalGroup(
            backgroundpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundpanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(LoadingStatus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LoadingValue)
                .addContainerGap())
            .addComponent(LoadingBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
            .addComponent(namaKelompok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        backgroundpanelLayout.setVerticalGroup(
            backgroundpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(backgroundpanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 370, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(namaKelompok)
                .addGap(59, 59, 59)
                .addGroup(backgroundpanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(LoadingStatus)
                    .addComponent(LoadingValue))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LoadingBar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backgroundpanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(backgroundpanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(LoadingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoadingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoadingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoadingScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

       LoadingScreen ls = new LoadingScreen();
       ls.setVisible(true);
       
       
        try 
        {
            for(int i = 0; i <= 100; i++)
            {
                Thread.sleep(60);
                ls.LoadingValue.setText(i + " %");
                if(i == 10)
                {
                    ls.LoadingStatus.setText("Turning On Modules...");
                }
                if(i == 20)
                {
                    ls.LoadingStatus.setText("Loading Modules...");
                }
                if(i == 50)
                {
                    ls.LoadingStatus.setText("Connecting to Database...");
                }
                if(i == 70)
                {
                    ls.LoadingStatus.setText("Connection Successful...");
                }
                if(i == 80)
                {
                    ls.LoadingStatus.setText("Launching Application...");
                }
                ls.LoadingBar.setValue(i);
            }
        } 
        catch (Exception e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
      
        Login lg = new Login();
        lg.setVisible(true);
        ls.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar LoadingBar;
    private javax.swing.JLabel LoadingStatus;
    private javax.swing.JLabel LoadingValue;
    private javax.swing.JPanel backgroundpanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel namaKelompok;
    // End of variables declaration//GEN-END:variables
}
