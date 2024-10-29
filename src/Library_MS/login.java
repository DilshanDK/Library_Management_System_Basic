package Library_MS;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class login extends javax.swing.JFrame {

    manage manage = new manage();
    Connection conn;
    PreparedStatement pst;
    ResultSet rs;

    public login() {
        initComponents();
        conn = (Connection) DBconnect.connection();

    }

    public void log() {

        String uName = txtUName.getText();
        @SuppressWarnings("deprecation")
        String pass = txtPassword.getText();
        try {
            String sqlLogin = "SELECT* FROM `login` Where uName ='" + uName + "' and Password = '" + pass + "'";
            pst = conn.prepareStatement(sqlLogin);
            rs = pst.executeQuery();

            if (rs.next()) {
                manage.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Invalid Username or Password");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panPic = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panData = new javax.swing.JPanel();
        lblLogin = new javax.swing.JLabel();
        lblUName = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        txtUName = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();
        rSMaterialButtonRectangle1 = new rojerusan.RSMaterialButtonRectangle();
        btnLogin = new rojerusan.RSMaterialButtonRectangle();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Library Management System");
        setBackground(new java.awt.Color(0, 0, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setMaximumSize(new java.awt.Dimension(895, 488));
        setMinimumSize(new java.awt.Dimension(895, 488));
        setName("form()"); // NOI18N
        setSize(new java.awt.Dimension(895, 488));

        panPic.setBackground(new java.awt.Color(0, 153, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Library_MS/Untitled-1.jpg"))); // NOI18N

        javax.swing.GroupLayout panPicLayout = new javax.swing.GroupLayout(panPic);
        panPic.setLayout(panPicLayout);
        panPicLayout.setHorizontalGroup(
            panPicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPicLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        panPicLayout.setVerticalGroup(
            panPicLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panPicLayout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        panData.setBackground(new java.awt.Color(22, 160, 133));
        panData.setForeground(new java.awt.Color(0, 0, 0));
        panData.setToolTipText("");

        lblLogin.setFont(new java.awt.Font("Poppins ExtraBold", 1, 36)); // NOI18N
        lblLogin.setForeground(new java.awt.Color(0, 0, 0));
        lblLogin.setText("Login Here");

        lblUName.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lblUName.setForeground(new java.awt.Color(0, 0, 0));
        lblUName.setText("User Name");

        lblPassword.setFont(new java.awt.Font("Poppins Medium", 0, 14)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(0, 0, 0));
        lblPassword.setText("Password");

        txtUName.setBackground(new java.awt.Color(44, 62, 80));
        txtUName.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        txtUName.setForeground(new java.awt.Color(255, 255, 255));

        txtPassword.setBackground(new java.awt.Color(44, 62, 80));
        txtPassword.setFont(new java.awt.Font("Poppins Medium", 0, 12)); // NOI18N
        txtPassword.setForeground(new java.awt.Color(255, 255, 255));
        txtPassword.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPasswordKeyPressed(evt);
            }
        });

        rSMaterialButtonRectangle1.setBackground(new java.awt.Color(26, 188, 156));
        rSMaterialButtonRectangle1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 5));
        rSMaterialButtonRectangle1.setForeground(new java.awt.Color(0, 0, 0));
        rSMaterialButtonRectangle1.setText("Cancal");
        rSMaterialButtonRectangle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonRectangle1ActionPerformed(evt);
            }
        });

        btnLogin.setBackground(new java.awt.Color(26, 188, 156));
        btnLogin.setBorder(new javax.swing.border.MatteBorder(null));
        btnLogin.setForeground(new java.awt.Color(0, 0, 0));
        btnLogin.setText("Login");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panDataLayout = new javax.swing.GroupLayout(panData);
        panData.setLayout(panDataLayout);
        panDataLayout.setHorizontalGroup(
            panDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDataLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addGroup(panDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lblUName, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                    .addComponent(lblPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(panDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblLogin)
                    .addGroup(panDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtUName, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
                        .addComponent(txtPassword)))
                .addContainerGap(154, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panDataLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119))
        );
        panDataLayout.setVerticalGroup(
            panDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panDataLayout.createSequentialGroup()
                .addGap(85, 85, 85)
                .addComponent(lblLogin)
                .addGap(59, 59, 59)
                .addGroup(panDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUName)
                    .addComponent(txtUName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPassword)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panDataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rSMaterialButtonRectangle1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panPic, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(panData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panPic, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtPasswordKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPasswordKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            log();
        }
    }//GEN-LAST:event_txtPasswordKeyPressed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        log();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void rSMaterialButtonRectangle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonRectangle1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_rSMaterialButtonRectangle1ActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new login().setVisible(true);

        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojerusan.RSMaterialButtonRectangle btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblLogin;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUName;
    private javax.swing.JPanel panData;
    private javax.swing.JPanel panPic;
    private rojerusan.RSMaterialButtonRectangle rSMaterialButtonRectangle1;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUName;
    // End of variables declaration//GEN-END:variables
}
