/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.styely.gui;

import com.styley.Model.MySQL;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anjana
 */
public class userRegistation extends javax.swing.JPanel {

    /**
     * Creates new form userRegistation
     */
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public userRegistation() {
        initComponents();
        loadGender();
        loadCompanyUtype();
        loadUserStatus();
        loadData();
    }

    public void reset() {
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField5.setText("");
        jTextField6.setText("");
        jDateChooser1.setDate(null);
        jDateChooser1.setEnabled(true);
        jPasswordField1.setText("");
        jComboBox1.setSelectedItem("Select");
        jComboBox1.setEnabled(true);
        jComboBox2.setSelectedItem("Select");
        jComboBox2.setEnabled(true);
        jComboBox3.setSelectedItem("active");
        jLabel15.setText("Section");
        jLabel16.setText("Category");

    }

    public void loadData() {
        try {
            ResultSet rs = MySQL.search("SELECT `company_user`.`id`,`company_user`.`name`,`company_user`.`username`,`company_user`.`NIC`,`company_user`.`Number`,`company_user`.`DOB`,`company_user`.`Adress`,`company_user_type`.`name`,`user_status_type`.`name`,`sub_category`.`name`,`company_user`.`Register_Date`,`company_user`.`password` FROM `company_user` INNER JOIN `gender` ON `company_user`.`gender_id` = `gender`.`id` INNER JOIN `company_user_type` ON `company_user_type`.`id` = `company_user`.`company_user_type_id` INNER JOIN `user_status_type` ON `user_status_type`.`id` = `company_user`.`user_status_type_id` INNER JOIN `sub_category` ON `sub_category`.`id` = `company_user`.`section` WHERE `company_user`.`id`!='1' ORDER BY `company_user`.`Register_Date` DESC");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("company_user.id"));
                v.add(rs.getString("company_user.name"));
                v.add(rs.getString("company_user.username"));
                v.add(rs.getString("company_user.NIC"));
                v.add(rs.getString("company_user.Number"));
                v.add(rs.getString("company_user.DOB"));
                v.add(rs.getString("company_user.Adress"));
                v.add(rs.getString("company_user_type.name"));
                v.add(rs.getString("user_status_type.name"));
                v.add(rs.getString("sub_category.name"));
                v.add(rs.getString("company_user.Register_Date"));
                v.add(rs.getString("company_user.password"));

                dtm.addRow(v);
            }
            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchData(String text) {
        try {
            ResultSet rs = MySQL.search("SELECT `company_user`.`id`,`company_user`.`name`,`company_user`.`username`,`company_user`.`NIC`,`company_user`.`Number`,`company_user`.`DOB`,`company_user`.`Adress`,`company_user_type`.`name`,`user_status_type`.`name`,`sub_category`.`name`,`company_user`.`Register_Date`,`company_user`.`password` FROM `company_user` INNER JOIN `gender` ON `company_user`.`gender_id` = `gender`.`id` INNER JOIN `company_user_type` ON `company_user_type`.`id` = `company_user`.`company_user_type_id` INNER JOIN `user_status_type` ON `user_status_type`.`id` = `company_user`.`user_status_type_id` INNER JOIN `sub_category` ON `sub_category`.`id` = `company_user`.`section` WHERE `company_user`.`id`!='1' AND `company_user`.`name` LIKE '" + text + "%' OR `company_user`.`id` LIKE '" + text + "%' OR `company_user`.`NIC` LIKE '" + text + "%' OR `sub_category`.`name` LIKE '" + text + "%' OR `user_status_type`.`name` LIKE '" + text + "%'  ORDER BY `company_user`.`Register_Date` DESC");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("company_user.id"));
                v.add(rs.getString("company_user.name"));
                v.add(rs.getString("company_user.username"));
                v.add(rs.getString("company_user.NIC"));
                v.add(rs.getString("company_user.Number"));
                v.add(rs.getString("company_user.DOB"));
                v.add(rs.getString("company_user.Adress"));
                v.add(rs.getString("company_user_type.name"));
                v.add(rs.getString("user_status_type.name"));
                v.add(rs.getString("sub_category.name"));
                v.add(rs.getString("company_user.Register_Date"));
                v.add(rs.getString("company_user.password"));

                dtm.addRow(v);
            }
            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadGender() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `gender`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {

                v.add(rs.getString("name"));

            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox1.setModel(dcm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadCompanyUtype() {
        ResultSet rs;
        try {
            ResultSet rs1 = MySQL.search("SELECT `company_user_type`.`id` FROM `company_user` INNER JOIN `company_user_type` ON `company_user`.`company_user_type_id` = `company_user_type`.`id` WHERE `company_user`.`id` = '" + Styley_signin.userId + "'");
            rs1.next();
            String type_id = rs1.getString("company_user_type.id");

            if (type_id.equals("2")) {
                rs = MySQL.search("SELECT * FROM `company_user_type` WHERE `id`!='1' AND `id`!='2'");
            } else {
                rs = MySQL.search("SELECT * FROM `company_user_type` WHERE `id`!='1'");
            }

            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {

                v.add(rs.getString("name"));

            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox2.setModel(dcm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadUserStatus() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `user_status_type`");
            Vector v = new Vector();

            while (rs.next()) {

                v.add(rs.getString("name"));

            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox3.setModel(dcm);
        } catch (Exception e) {
            e.printStackTrace();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton2 = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setPreferredSize(new java.awt.Dimension(517, 489));

        jLabel1.setFont(new java.awt.Font("Segoe Script", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("User Manegment");

        jLabel13.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Search");

        jTextField7.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        jTextField7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField7KeyReleased(evt);
            }
        });

        jPanel2.setLayout(new java.awt.CardLayout());

        jButton1.setBackground(new java.awt.Color(0, 102, 0));
        jButton1.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Register");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(153, 0, 0));
        jButton3.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable1.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "user_ID", "name", "Username", "NIC", "Number", "DOB", "Adress", "Position", "User_type", "Section", "Register Date", "Password"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton4.setBackground(new java.awt.Color(255, 102, 0));
        jButton4.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Update");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel2.setText("First name:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 80, 27));

        jTextField1.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 152, 27));

        jTextField2.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 150, 27));

        jLabel3.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel3.setText("Last name:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 49, 80, 27));

        jLabel4.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel4.setText("NIC:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 64, 27));

        jTextField3.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 10, 180, 27));

        jLabel5.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel5.setText("Number:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 50, 64, 27));

        jTextField4.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 50, 180, 27));

        jLabel6.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel6.setText("DOB:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 64, 27));

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jPanel3.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 150, 27));

        jLabel7.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel7.setText("Address l1:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 10, 70, 27));

        jLabel8.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel8.setText("Address l2:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 50, 80, 27));

        jTextField5.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jPanel3.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 170, 27));

        jTextField6.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jPanel3.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 50, 170, 27));

        jLabel9.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel9.setText("Gender:");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, 75, 27));

        jComboBox1.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 90, 180, 27));

        jLabel10.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel10.setText("Company user type:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 90, 130, 27));

        jComboBox2.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jPanel3.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 90, 140, 27));

        jLabel11.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel11.setText("User Type");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 70, 27));

        jComboBox3.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jPanel3.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 150, 27));

        jLabel12.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel12.setText("Section:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 130, 81, 27));

        jLabel14.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel14.setText("password:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 130, 64, 27));

        jPasswordField1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jPanel3.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 130, 180, 30));

        jButton2.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton2.setText("Select Section");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 130, 180, 30));

        jLabel15.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("Section");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 170, 90, 30));

        jLabel16.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Category");
        jPanel3.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 170, 80, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 51, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 879, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 765, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 860, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(14, 14, 14)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel1)
                .addGap(4, 4, 4)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 950, 660));
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String fn = jTextField1.getText();
        String ln = jTextField2.getText();
        String fullname = fn + " " + ln;
        String nic = jTextField3.getText();
        String number = jTextField4.getText();

        String DOB = null;

        if (jDateChooser1.getDate() != null) {
            DOB = sdf.format(jDateChooser1.getDate());
        }
        String add1 = jTextField5.getText();
        String add2 = jTextField6.getText();
        String addline = add1 + "," + add2;
        String gender = jComboBox1.getSelectedItem().toString();
        String ComUserType = jComboBox2.getSelectedItem().toString();
        String uType = jComboBox3.getSelectedItem().toString();
        String section = jLabel15.getText();
        String pw = jPasswordField1.getText();
        String username = fn + "123";
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String rdate = sdf1.format(new Date());

        if (fn.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter firsr name", "warrning", JOptionPane.WARNING_MESSAGE);
        } else if (ln.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter last name", "warrning", JOptionPane.WARNING_MESSAGE);
        } else if (nic.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter NIC number", "warrning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("([0-9]{9}[a-z]{1}|[0-9]{12})").matcher(nic).matches()) {
            JOptionPane.showMessageDialog(this, "this NIC is incorect", "warrning", JOptionPane.WARNING_MESSAGE);
        } else if (number.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your mobile number", "warrning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("07[01245678][0-9]{7}").matcher(number).matches()) {
            JOptionPane.showMessageDialog(this, "this number is incorect", "warrning", JOptionPane.WARNING_MESSAGE);
        } else if (DOB == null) {
            JOptionPane.showMessageDialog(this, "Please add DOB", "warrning", JOptionPane.WARNING_MESSAGE);
        } else if (add1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add Address line 1", "warrning", JOptionPane.WARNING_MESSAGE);
        } else if (add2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add Address line 2", "warrning", JOptionPane.WARNING_MESSAGE);
        } else if (gender.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Gender", "warrning", JOptionPane.WARNING_MESSAGE);

        } else if (ComUserType.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select Company user type", "warrning", JOptionPane.WARNING_MESSAGE);

        } else if (pw.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Enter valid password", "warrning", JOptionPane.WARNING_MESSAGE);
        } else if (section.equals("Section")) {
            JOptionPane.showMessageDialog(this, "Please Select Section", "warrning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet rs = MySQL.search("SELECT * FROM `gender` WHERE `gender`.`name` = '" + gender + "'");
                rs.next();
                String g_id = rs.getString("id");

                ResultSet rs1 = MySQL.search("SELECT * FROM `company_user_type` WHERE `company_user_type`.`name` = '" + ComUserType + "'");
                rs1.next();
                String cut_id = rs1.getString("id");

                ResultSet rs2 = MySQL.search("SELECT * FROM `user_status_type` WHERE `user_status_type`.`name` = '" + uType + "'");
                rs2.next();
                String ut_id = rs2.getString("id");

                ResultSet rs3 = MySQL.search("SELECT * FROM `sub_category` WHERE `sub_category`.`name` = '" + section + "'");
                rs3.next();
                String section_id = rs3.getString("id");

                ResultSet rs4 = MySQL.search("SELECT * FROM `company_user` WHERE `NIC`='" + nic + "'");
                if (rs4.next()) {
                    JOptionPane.showMessageDialog(this, "This user already added on this Shop", "warrning", JOptionPane.WARNING_MESSAGE);
                } else {
                    MySQL.iud("INSERT INTO `company_user`(`name`,`username`,`password`,`DOB`,`Adress`,`NIC`,`Number`,`Register_Date`,`gender_id`,`company_user_type_id`,`section`) VALUES('" + fullname + "','" + username + "','" + pw + "','" + DOB + "','" + addline + "','" + nic + "','" + number + "','" + rdate + "','" + g_id + "','" + cut_id + "','" + section_id + "') ");
                    JOptionPane.showMessageDialog(this, "Registation Sucess ful", "Sucess", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    reset();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            //insert start

            //insert end
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped


    }//GEN-LAST:event_jTextField3KeyTyped

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        // TODO add your handling code here:
        String mobile = jTextField4.getText();
        String text = mobile + evt.getKeyChar();

        if (text.length() == 1) {

            if (!text.equals("0")) {
                evt.consume();
            }
        } else if (text.length() == 2) {

            if (!text.equals("07")) {
                evt.consume();
            }
        } else if (text.length() == 3) {

            if (!Pattern.compile("07[01245678]").matcher(text).matches()) {
                evt.consume();
            }
        } else if (text.length() <= 10) {

            if (!Pattern.compile("07[01245678][0-9]+").matcher(text).matches()) {
                evt.consume();
            }
        } else {

            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int selectrow = jTable1.getSelectedRow();

        if (selectrow != -1) {

            try {
                String uid = jTable1.getValueAt(selectrow, 0).toString();
                String uname = jTable1.getValueAt(selectrow, 1).toString();
                String nic = jTable1.getValueAt(selectrow, 3).toString();
                String number = jTable1.getValueAt(selectrow, 4).toString();
                String dob = jTable1.getValueAt(selectrow, 5).toString();
                String address = jTable1.getValueAt(selectrow, 6).toString();
                String posion = jTable1.getValueAt(selectrow, 7).toString();
                String user_type = jTable1.getValueAt(selectrow, 8).toString();
                String section = jTable1.getValueAt(selectrow, 9).toString();
                String pw = jTable1.getValueAt(selectrow, 11).toString();

                String[] parts = uname.split(" ");
                String part1 = parts[0];
                String part2 = parts[1];

                String[] add = address.split(",");
                String line1 = add[0];
                String line2 = add[1];

                ResultSet rs = MySQL.search("SELECT * FROM `company_user` WHERE `id`='" + uid + "'");
                rs.next();
                String gen = rs.getString("gender_id");
                ResultSet rs1 = MySQL.search("SELECT * FROM `gender` WHERE `id`='" + gen + "'");
                rs1.next();
                String gender = rs1.getString("name");

                jTextField1.setText(part1);
                jTextField2.setText(part2);

                Date d1 = sdf.parse(dob);
                jDateChooser1.setDate(d1);

                jTextField3.setText(nic);
                jTextField4.setText(number);
                jDateChooser1.setEnabled(false);
                jTextField5.setText(line1);
                jTextField6.setText(line2);
                jComboBox1.setSelectedItem(gender);
                jComboBox1.setEnabled(false);
                jComboBox2.setSelectedItem(posion);
                jComboBox3.setSelectedItem(user_type);
//            jComboBox3.setEnabled(false);
                jLabel15.setText(section);
                jPasswordField1.setText(pw);

                if (Styley_signin.userId == 2) {

                    jComboBox2.setEnabled(false);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        int selectRow = jTable1.getSelectedRow();
        if (selectRow == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Row", "warrning", JOptionPane.WARNING_MESSAGE);
        } else {

            String uid = jTable1.getValueAt(selectRow, 0).toString();
            String fn = jTextField1.getText();
            String ln = jTextField2.getText();
            String nic = jTextField3.getText();
            String number = jTextField4.getText();
            String add1 = jTextField5.getText();
            String add2 = jTextField6.getText();
            String posion = jComboBox2.getSelectedItem().toString();
            String ut = jComboBox3.getSelectedItem().toString();
            String section = jLabel15.getText();
            String pw = jPasswordField1.getText();

            String fullname = fn + " " + ln;
            String address = add1 + "," + add2;

            if (fn.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter firsr name", "warrning", JOptionPane.WARNING_MESSAGE);
            } else if (ln.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter last name", "warrning", JOptionPane.WARNING_MESSAGE);
            } else if (nic.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter NIC number", "warrning", JOptionPane.WARNING_MESSAGE);
            } else if (!Pattern.compile("([0-9]{9}[a-z]{1}|[0-9]{12})").matcher(nic).matches()) {
                JOptionPane.showMessageDialog(this, "this NIC is incorect", "warrning", JOptionPane.WARNING_MESSAGE);
            } else if (number.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your mobile number", "warrning", JOptionPane.WARNING_MESSAGE);
            } else if (!Pattern.compile("07[01245678][0-9]{7}").matcher(number).matches()) {
                JOptionPane.showMessageDialog(this, "this number is incorect", "warrning", JOptionPane.WARNING_MESSAGE);
            } else if (add1.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please add Address line 1", "warrning", JOptionPane.WARNING_MESSAGE);
            } else if (add2.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please add Address line 2", "warrning", JOptionPane.WARNING_MESSAGE);
            } else if (posion.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select Company user type", "warrning", JOptionPane.WARNING_MESSAGE);

            } else if (pw.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter valid password", "warrning", JOptionPane.WARNING_MESSAGE);
            } else if (section.equals("Section")) {
                JOptionPane.showMessageDialog(this, "Please Select Section", "warrning", JOptionPane.WARNING_MESSAGE);
            } else {

                try {
                    ResultSet rs1 = MySQL.search("SELECT * FROM `company_user` WHERE `id`='" + uid + "'");
                    rs1.next();
//                    String com_u_type = rs1.getString("company_user_type_id");

                    ResultSet rs2 = MySQL.search("SELECT * FROM `user_status_type` WHERE `name`='" + ut + "'");
                    rs2.next();
                    String utype = rs2.getString("id");

                    ResultSet rs3 = MySQL.search("SELECT * FROM `sub_category` WHERE `name`='" + section + "'");
                    rs3.next();
                    String section_id = rs3.getString("id");

                    ResultSet rs4 = MySQL.search("SELECT * FROM `company_user_type` WHERE `name`='" + posion + "'");
                    rs4.next();
                    String posion_id = rs4.getString("id");

                    //Update
                    MySQL.iud("UPDATE `company_user` SET `name`='" + fullname + "',`password`='" + pw + "',`Adress`='" + address + "',`NIC`='" + nic + "',`Number`='" + number + "',`company_user_type_id`='" + posion_id + "',`user_status_type_id`='" + utype + "',`section`='" + section_id + "' WHERE `id`='" + uid + "'");
                    JOptionPane.showMessageDialog(this, "Update Sucessfull", "Sucess", JOptionPane.INFORMATION_MESSAGE);
                    loadData();
                    jTable1.clearSelection();
                    reset();

                    //Update
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }


    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField7KeyReleased
        // TODO add your handling code here:
        String text = jTextField7.getText();
        searchData(text);


    }//GEN-LAST:event_jTextField7KeyReleased

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        int selectRow = jTable1.getSelectedRow();
        if (selectRow == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Row", "warrning", JOptionPane.WARNING_MESSAGE);
        } else {
            String user_id = jTable1.getValueAt(selectRow, 0).toString();

            int option = JOptionPane.showConfirmDialog(this, "Are you do you want Detele This user?", "Warning", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
//Detele
                MySQL.iud("DELETE FROM `company_user` WHERE `id`='" + user_id + "'");
                JOptionPane.showMessageDialog(this, "Delete Sucessfully", "Sucess", JOptionPane.INFORMATION_MESSAGE);
                loadData();
                reset();
            }
        }


    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        new_section ns = new new_section(this, true);
        ns.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    public javax.swing.JLabel jLabel16;
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
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
