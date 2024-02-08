/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.styely.gui;

import com.styley.Model.MySQL;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anjana
 */
public class Product_registation extends javax.swing.JPanel {

    /**
     * Creates new form Styley_Product_registation
     */
    public Product_registation() {
        initComponents();
        loadCategory();
        loadBrand();
        loadMaterial();
        loadProduct();
        addproduct();
    }

    Grn_manegment gr;

    public Product_registation(Grn_manegment gr) {
        initComponents();
        loadCategory();
        loadBrand();
        loadMaterial();
        loadProduct();
        addproduct();
        this.gr = gr;
    }

    public void addproduct() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `product` WHERE `product_status`='2'");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {

                v.add(rs.getString("id") + " :" + rs.getString("name"));
            }
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox4.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        jTextField1.setText("");
        jComboBox1.setSelectedItem("Select");
        jComboBox2.setSelectedItem("Select");
        jComboBox3.setSelectedItem("Select");
        jTable1.clearSelection();
        jButton7.setForeground(Color.BLACK);
        jButton7.setBackground(null);
        jButton2.setForeground(Color.BLACK);
        jButton2.setBackground(null);
    }

    public void loadProduct() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `product` INNER JOIN `catecory` ON `product`.`Catecory_id` = `catecory`.`id` INNER JOIN `brand` ON `product`.`Brand_id` = `brand`.`id` INNER JOIN `material` ON `material`.`id` = `product`.`material_id` WHERE `product`.`product_status` = '1' ORDER BY `product`.`id`ASC");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("product.id"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("catecory.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("material.name"));

                dtm.addRow(v);
            }
            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchProduct(String text) {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `product` INNER JOIN `catecory` ON `product`.`Catecory_id` = `catecory`.`id` INNER JOIN `brand` ON `product`.`Brand_id` = `brand`.`id` INNER JOIN `material` ON `material`.`id` = `product`.`material_id` WHERE `product`.`product_status` = '1' AND product.id LIKE '" + text + "%' OR product.name LIKE '" + text + "%' OR catecory.name LIKE '" + text + "%' OR brand.name LIKE '" + text + "%' OR material.name LIKE '" + text + "%'");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("product.id"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("catecory.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("material.name"));

                dtm.addRow(v);
            }
            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadCategory() {

        try {
            ResultSet rs = MySQL.search("SELECT * FROM `catecory`");
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

    public void loadBrand() {

        try {
            ResultSet rs = MySQL.search("SELECT * FROM `brand`");
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

    public void loadMaterial() {

        try {
            ResultSet rs = MySQL.search("SELECT * FROM `material`");
            Vector v = new Vector();
            v.add("Select");
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
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe Script", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Manege Products");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(283, 11, 522, 36));

        jLabel2.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel2.setText("Product Name:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 121, 81, 28));

        jTextField1.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 121, 212, 28));

        jLabel3.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel3.setText("Category:");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 81, 28));

        jComboBox1.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 160, 167, -1));

        jLabel4.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel4.setText("Brand:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 194, 81, 28));

        jComboBox2.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jPanel1.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 194, 167, -1));

        jLabel5.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel5.setText("Mterial:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 228, 81, 28));

        jComboBox3.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jPanel1.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 228, 167, -1));

        jButton1.setBackground(new java.awt.Color(102, 153, 255));
        jButton1.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton1.setText("Register");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 267, 297, 35));

        jButton3.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton3.setText("+");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 160, -1, 28));

        jButton4.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton4.setText("+");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 194, -1, 28));

        jButton5.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton5.setText("+");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 228, -1, 28));

        jButton6.setBackground(new java.awt.Color(0, 102, 204));
        jButton6.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton6.setText("Update");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 308, 297, 33));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "P_id", "Name", "Category", "Brand", "Material"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 570, 350));

        jLabel7.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Search");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 80, 30));

        jTextField2.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });
        jPanel2.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 470, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(325, 80, 591, 411));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/styley/img/dt.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 259, 80));

        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        jLabel8.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel8.setText("Block Product:");

        jComboBox4.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton2.setText("Unblock Product");
        jButton2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 352, 297, -1));

        jButton7.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton7.setText("BLock Product Here");
        jButton7.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 454, 297, 37));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 915, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        Category_registation cr = new Category_registation(this, true);
        cr.setVisible(true);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        Brand_registation br = new Brand_registation(this, true);
        br.setVisible(true);

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        Material_Registation mr = new Material_Registation(this, true);
        mr.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String name = jTextField1.getText();
        String c = jComboBox1.getSelectedItem().toString();
        String b = jComboBox2.getSelectedItem().toString();
        String m = jComboBox3.getSelectedItem().toString();

        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter product name", "warning", JOptionPane.WARNING_MESSAGE);
        } else if (c.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select a Category", "warning", JOptionPane.WARNING_MESSAGE);
        } else if (b.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select a Brand", "warning", JOptionPane.WARNING_MESSAGE);
        } else if (m.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please Select a Material", "warning", JOptionPane.WARNING_MESSAGE);
        } else {

            try {
                ResultSet cat = MySQL.search("SELECT * FROM `catecory` WHERE `name`='" + c + "'");
                cat.next();
                String category_id = cat.getString("id");

                ResultSet brand = MySQL.search("SELECT * FROM `brand` WHERE `name`='" + b + "'");
                brand.next();
                String brand_id = brand.getString("id");

                ResultSet mat = MySQL.search("SELECT * FROM `material` WHERE `name`='" + m + "'");
                mat.next();
                String material_id = mat.getString("id");

                ResultSet rs = MySQL.search("SELECT * FROM `product` WHERE `name`='" + name + "' AND `Catecory_id`='" + category_id + "' AND `Brand_id`='" + brand_id + "' AND `material_id`='" + material_id + "'");
                if (rs.next()) {
                    JOptionPane.showMessageDialog(this, "This product Already exsist.", "warning", JOptionPane.WARNING_MESSAGE);
                } else {

                    MySQL.iud("INSERT INTO `product`(`name`,`Catecory_id`,`Brand_id`,`material_id`) VALUES('" + name + "','" + category_id + "','" + brand_id + "','" + material_id + "')");
                    JOptionPane.showMessageDialog(this, "Product insert sucessfully", "Sucess", JOptionPane.INFORMATION_MESSAGE);
                    loadProduct();
                    reset();

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        String text = jTextField2.getText();
        searchProduct(text);
        if (text.isEmpty()) {
            loadProduct();
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int selectrow = jTable1.getSelectedRow();

        String pname = jTable1.getValueAt(selectrow, 1).toString();
        String cname = jTable1.getValueAt(selectrow, 2).toString();
        String bname = jTable1.getValueAt(selectrow, 3).toString();
        String mname = jTable1.getValueAt(selectrow, 4).toString();

        jTextField1.setText(pname);
        jComboBox1.setSelectedItem(cname);
        jComboBox2.setSelectedItem(bname);
        jComboBox3.setSelectedItem(mname);

        if (selectrow != -1) {
            jButton7.setForeground(Color.WHITE);
            jButton7.setBackground(Color.red);
        }

    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int selectrow = jTable1.getSelectedRow();
        if (selectrow == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a Row", "warning", JOptionPane.WARNING_MESSAGE);
        } else {
            String p_id = jTable1.getValueAt(selectrow, 0).toString();
            String name = jTextField1.getText();
            String c = jComboBox1.getSelectedItem().toString();
            String b = jComboBox2.getSelectedItem().toString();
            String m = jComboBox3.getSelectedItem().toString();

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter Product Name", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (c.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select a Catecory", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (b.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select a Brand", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (m.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please Select a Material", "warning", JOptionPane.WARNING_MESSAGE);
            } else {

                try {
                    ResultSet rs1 = MySQL.search("SELECT * FROM `catecory` WHERE `name`='" + c + "'");
                    rs1.next();
                    String c_id = rs1.getString("id");

                    ResultSet rs2 = MySQL.search("SELECT * FROM `brand` WHERE `name`='" + b + "'");
                    rs2.next();
                    String b_id = rs2.getString("id");

                    ResultSet rs3 = MySQL.search("SELECT * FROM `material` WHERE `name`='" + m + "'");
                    rs3.next();
                    String m_id = rs3.getString("id");

                    ResultSet rs4 = MySQL.search("SELECT * FROM `product` WHERE `name`='" + name + "' AND `Catecory_id`='" + c_id + "' AND `Brand_id`='" + b_id + "' AND `material_id`='" + m_id + "'");
                    if (rs4.next()) {
                        JOptionPane.showMessageDialog(this, "This product hasnot been updated in any  way", "warning", JOptionPane.WARNING_MESSAGE);

                    } else {
                        MySQL.iud("UPDATE `product` SET `name`='" + name + "',`Catecory_id`='" + c_id + "',`Brand_id`='" + b_id + "',`material_id`='" + m_id + "' WHERE `id`='" + p_id + "'");
                        JOptionPane.showMessageDialog(this, "Update Sucessfully", "Sucess", JOptionPane.INFORMATION_MESSAGE);
                        loadProduct();
                        reset();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int selectRow = jTable1.getSelectedRow();
        if (selectRow == -1) {
            JOptionPane.showMessageDialog(this, "Please Select a row", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            String p_id = jTable1.getValueAt(selectRow, 0).toString();
            String block = "2";
            try {
                ResultSet rs = MySQL.search("SELECT * FROM `product` WHERE `id`='" + p_id + "'");
                if (rs.next()) {
                    String p_st = rs.getString("product_status");
                    if (p_st.equals(block)) {
                        JOptionPane.showMessageDialog(this, "This Product is Already Blocked Do you want to Unblock Click Unblock Product Button", "Warning", JOptionPane.WARNING_MESSAGE);
                        jButton2.grabFocus();
                    } else {
                        MySQL.iud("UPDATE `product` SET `product_status`='" + block + "' WHERE `id`='" + p_id + "'");
                        JOptionPane.showMessageDialog(this, "Product Blocked Sucess", "Information", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                loadProduct();
                reset();
                addproduct();
                jTextField1.grabFocus();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String prod = jComboBox4.getSelectedItem().toString();
        String[] part = prod.split(" :");
        String p_id = part[0];
        String active = "1";
        if (prod.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Choose a Product on here", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            MySQL.iud("UPDATE `product` SET `product_status` ='" + active + "' WHERE `id`='" + p_id + "'");
            JOptionPane.showMessageDialog(this, "Product Unblock Sucess", "Information", JOptionPane.INFORMATION_MESSAGE);

            loadProduct();
            reset();
            addproduct();
        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:

        if (jComboBox4.getSelectedIndex() != 0) {
            jButton2.setForeground(Color.WHITE);
            jButton2.setBackground(Color.GREEN);
        } else {
            jButton2.setForeground(Color.BLACK);
            jButton2.setBackground(null);
        }


    }//GEN-LAST:event_jComboBox4ItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public javax.swing.JButton jButton3;
    public javax.swing.JButton jButton4;
    public javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables
}
