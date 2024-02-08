/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.styely.gui;

import com.styley.Model.MySQL;
import java.awt.Color;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Anjana
 */
public class Manege_stock_panel extends javax.swing.JPanel {

    /**
     * Creates new form Manege_stock_panel
     */
    public Manege_stock_panel() {
        initComponents();
        loadCategory();
        loadBrand();
        loadMaterial();
        loadStock();
    }

    public void reset() {
        jComboBox1.setSelectedItem("Select");
        jComboBox2.setSelectedItem("Select");
        jComboBox3.setSelectedItem("Select");
        jTextField1.setText("");
        jDateChooser1.setDate(null);
        jTextField2.setText("");
        jTextField3.setText("");
        jComboBox4.setSelectedItem("Name ASC");
        jLabel11.setText("0.00");
        jTextField4.setText("");
        loadStock();

    }

    public void searchStock() {
        try {
            String category = jComboBox1.getSelectedItem().toString();
            String brand = jComboBox2.getSelectedItem().toString();
            String material = jComboBox3.getSelectedItem().toString();
            String p_name = jTextField1.getText();
            String addDate = null;

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            if (jDateChooser1.getDate() != null) {
                addDate = sdf.format(jDateChooser1.getDate());
            }

            String min = jTextField2.getText();
            String max = jTextField3.getText();

            int sort = jComboBox4.getSelectedIndex();

            Vector queryVector = new Vector();

            if (category.equals("Select")) {

            } else {
                queryVector.add("AND `catecory`.`name`='" + category + "'");
            }

            if (brand.equals("Select")) {

            } else {
                queryVector.add("AND `brand`.`name`='" + brand + "'");
            }

            if (material.equals("Select")) {

            } else {
                queryVector.add("AND `material`.`name`='" + brand + "'");
            }

            if (p_name.isEmpty()) {

            } else {
                queryVector.add("AND `product`.`name` LIKE '" + p_name + "%'");
            }

            if (addDate == null) {

            } else {
                queryVector.add("AND `stock`.`AddDate`='" + addDate + "'");
            }

            if (addDate == null) {

            } else {
                queryVector.add("AND `stock`.`AddDate`='" + addDate + "'");
            }

            if (!min.isEmpty()) {
                if (max.isEmpty()) {
                    //min t max f
                    queryVector.add("AND `stock`.`Selling_price` >= '" + min + "'");
                } else {
                    //min t max t
                    queryVector.add("AND `stock`.`Selling_price` >= '" + min + "' AND `stock`.`Selling_price` <= '" + max + "'");
                }
            }

            if (!max.isEmpty()) {
                if (min.isEmpty()) {
                    //min f max t
                    queryVector.add("AND `stock`.`Selling_price` <= '" + max + "'");
                }
            }

            String wherequery = "WHERE stock.id !='0' ";

            for (int i = 0; i < queryVector.size(); i++) {
                wherequery += " ";
                wherequery += queryVector.get(i);
                wherequery += " ";

//                if (i != queryVector.size() - 1) {
//                    wherequery += "AND";
//                }
            }

            String sortQuery;
            switch (sort) {
                case 0:
                    sortQuery = " `product`.`name` ASC";
                    break;
                case 1:
                    sortQuery = " `product`.`name` DESC";
                    break;
                case 2:
                    sortQuery = " `stock`.`Selling_price` ASC";
                    break;
                case 3:
                    sortQuery = " `stock`.`Selling_price` DESC";
                    break;
                case 4:
                    sortQuery = " `stock`.`qty` ASC";
                    break;
                case 5:
                    sortQuery = " `stock`.`qty` DESC";
                    break;
                case 6:
                    sortQuery = " `stock`.`AddDate` ASC";
                    break;
                default:
                    sortQuery = " `stock`.`AddDate` DESC";
                    break;
            }
            String lastQuery = "SELECT DISTINCT `stock`.`id`,`product`.`id`,`catecory`.`name`,`brand`.`name`,`material`.`name`,`product`.`name`,`size`.`name`,`stock`.`qty`,`grn_item`.`buying_price`,`stock`.`Selling_price`,`stock`.`AddDate` FROM `stock` INNER JOIN `product` ON `product`.`id` = `stock`.`product_id` INNER JOIN `catecory` ON `catecory`.`id` = `product`.`Catecory_id` INNER JOIN `brand` ON `brand`.`id` = `product`.`Brand_id` INNER JOIN `material` ON `material`.`id` = `product`.`material_id` INNER JOIN `grn_item` ON `grn_item`.`Stock_id` = `stock`.`id` INNER JOIN `size` ON `stock`.`Size_id` = `size`.`id` " + wherequery + " ORDER BY " + sortQuery + "";
            ResultSet rs = MySQL.search(lastQuery);
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("stock.id"));
                v.add(rs.getString("product.id"));
                v.add(rs.getString("catecory.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("material.name"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("size.name"));
                v.add(rs.getString("stock.qty"));
                v.add(rs.getString("grn_item.buying_price"));
                v.add(rs.getString("stock.Selling_price"));
                v.add(rs.getString("stock.AddDate"));

                dtm.addRow(v);

            }
            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadStock() {
        try {
            ResultSet rs = MySQL.search("SELECT DISTINCT `stock`.`id`,`product`.`id`,`catecory`.`name`,`brand`.`name`,`material`.`name`,`product`.`name`,`size`.`name`,`stock`.`qty`,`grn_item`.`buying_price`,`stock`.`Selling_price`,`stock`.`AddDate` FROM `stock` INNER JOIN `product` ON `product`.`id` = `stock`.`product_id` INNER JOIN `catecory` ON `catecory`.`id` = `product`.`Catecory_id` INNER JOIN `brand` ON `brand`.`id` = `product`.`Brand_id` INNER JOIN `material` ON `material`.`id` = `product`.`material_id` INNER JOIN `grn_item` ON `grn_item`.`Stock_id` = `stock`.`id` INNER JOIN `size` ON `stock`.`Size_id` = `size`.`id`");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {

                Vector v = new Vector();
                v.add(rs.getString("stock.id"));
                v.add(rs.getString("product.id"));
                v.add(rs.getString("catecory.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("material.name"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("size.name"));
                v.add(rs.getString("stock.qty"));
                v.add(rs.getString("grn_item.buying_price"));
                v.add(rs.getString("stock.Selling_price"));
                v.add(rs.getString("stock.AddDate"));

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe Print", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Manege Stock");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 921, -1));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel2.setText("Category:");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, 111, 23));

        jComboBox1.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox1PropertyChange(evt);
            }
        });
        jPanel3.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 11, 189, 23));

        jLabel3.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel3.setText("Brand:");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 111, 23));

        jComboBox2.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        jComboBox2.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox2PropertyChange(evt);
            }
        });
        jPanel3.add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 40, 189, 23));

        jLabel4.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel4.setText("Material:");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 69, 111, 23));

        jComboBox3.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        jComboBox3.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox3PropertyChange(evt);
            }
        });
        jPanel3.add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(131, 69, 189, 23));

        jLabel5.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel5.setText("Product name:");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 14, 95, -1));

        jTextField1.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 11, 186, -1));

        jLabel6.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel6.setText("Date:");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 44, 95, -1));

        jDateChooser1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateChooser1PropertyChange(evt);
            }
        });
        jPanel3.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(435, 41, 186, -1));

        jLabel7.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel7.setText("price:");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 69, 104, 23));

        jLabel8.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel8.setText("Sort:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 103, 59, 25));

        jComboBox4.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Name ASC", "Name DESC", "Price min", "Price max", "Quentity min", "Quentity max", "Date ASC", "Date DESC", " " }));
        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });
        jComboBox4.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox4PropertyChange(evt);
            }
        });
        jPanel3.add(jComboBox4, new org.netbeans.lib.awtextra.AbsoluteConstraints(79, 104, 127, 23));

        jTextField2.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(438, 70, 67, -1));

        jLabel9.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("to");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(509, 69, 43, 23));

        jTextField3.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(556, 70, 65, -1));

        jLabel10.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel10.setText("Buying Price:");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(639, 11, 87, 20));

        jLabel11.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel11.setText("0.00");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(744, 11, 90, 20));

        jLabel12.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel12.setText("New sell price:");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(639, 41, 98, 22));

        jTextField4.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });
        jPanel3.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(741, 40, 170, -1));

        jButton1.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton1.setText("Update price");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 70, 170, -1));

        jPanel4.setLayout(new java.awt.CardLayout());

        jTable1.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock_id", "Product_id", "Category", "Brand", "Material", "Name", "Size", "Quentity", "Buy", "Sell", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, true, false
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

        jPanel4.add(jScrollPane1, "card2");

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 920, 210));

        jButton2.setBackground(new java.awt.Color(153, 0, 51));
        jButton2.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Reset");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(833, 10, 80, -1));

        jButton3.setBackground(new java.awt.Color(102, 102, 102));
        jButton3.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Get Report");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(743, 370, 180, 50));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 76, 931, 433));

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int selectRow = jTable1.getSelectedRow();
        if (selectRow != -1) {
            String buy = jTable1.getValueAt(selectRow, 8).toString();
            jLabel11.setText(buy);

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int selectRow = jTable1.getSelectedRow();
        if (selectRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a stock Stock", "warning", JOptionPane.WARNING_MESSAGE);
        } else {
            String buy = jLabel11.getText();
            String new_price = jTextField4.getText();

            if (new_price.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please Enter New Selling Price", "warning", JOptionPane.WARNING_MESSAGE);
            } else if (!Pattern.compile("(0)|([1-9][0-9]*)|(([1-9][0-9]*[.]([0]*[1-9][0-9]*)))|([0][.]([0]*[1-9][0-9]*))").matcher(new_price).matches()) {
                JOptionPane.showMessageDialog(this, "Invalid New Selling Price", "warning", JOptionPane.WARNING_MESSAGE);
            } else {
                String stock_id = jTable1.getValueAt(selectRow, 0).toString();
                if (Double.parseDouble(new_price) <= Double.parseDouble(buy)) {
                    int option = JOptionPane.showConfirmDialog(this, "This Stock Buying Price increase than new selling price. Do you want to Update", "warning", JOptionPane.INFORMATION_MESSAGE, JOptionPane.YES_NO_OPTION);

                    if (option == JOptionPane.YES_OPTION) {
                        MySQL.iud("UPDATE `stock` SET `Selling_price`='" + new_price + "' WHERE `id`='" + stock_id + "'");
                    }
                } else {
                    MySQL.iud("UPDATE `stock` SET `Selling_price`='" + new_price + "' WHERE `id`='" + stock_id + "'");
                }
                reset();

            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        // TODO add your handling code here:
        String price = jTextField4.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        // TODO add your handling code here:
        searchStock();
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        // TODO add your handling code here:
        searchStock();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jComboBox4PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox4PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox4PropertyChange

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        // TODO add your handling code here:
        searchStock();
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jDateChooser1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateChooser1PropertyChange
        // TODO add your handling code here:
        searchStock();
    }//GEN-LAST:event_jDateChooser1PropertyChange

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        searchStock();
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jComboBox3PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox3PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3PropertyChange

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        // TODO add your handling code here:
        searchStock();
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox2PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox2PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2PropertyChange

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        // TODO add your handling code here:
        searchStock();
    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PropertyChange

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        searchStock();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {

            ResultSet rs = MySQL.search("SELECT `stock`.`id`,`product`.`name`,`catecory`.`name`,`brand`.`name`,`material`.`name`,`size`.`name`,`stock`.`qty`,`grn_item`.`buying_price`,`stock`.`Selling_price`,`stock`.`AddDate` FROM `stock` INNER JOIN `product` ON `product`.`id` = `stock`.`product_id` INNER JOIN `catecory` ON `catecory`.`id` = `product`.`Catecory_id` INNER JOIN `brand` ON `brand`.`id` = `product`.`Brand_id` INNER JOIN `material` ON `material`.`id` = `product`.`material_id` INNER JOIN `size` ON `size`.`id` = `stock`.`Size_id` INNER JOIN `grn_item` ON `stock`.`id` = `grn_item`.`Stock_id` ORDER BY `stock`.`id` DESC LIMIT 1");
            rs.next();
            String sid = rs.getString("stock.id");
            String pname = rs.getString("product.name");
            String cat = rs.getString("catecory.name");
            String brand = rs.getString("brand.name");
            String mat = rs.getString("material.name");
            String size = rs.getString("size.name");
            String qty = rs.getString("stock.qty");
            String buy = rs.getString("grn_item.buying_price");
            String sell = rs.getString("stock.Selling_price");
            String add = rs.getString("stock.AddDate");

//            DefaultTableModel dtm = (DefaultTableModel)jTable1.getModel();
            InputStream stream = getClass().getResourceAsStream("/report/DF_stocks.jasper");            
//            JasperReport jp = JasperCompileManager.compileReport(stream);

            HashMap parameters = new HashMap();
            parameters.put("Parameter1", sid);
            parameters.put("Parameter2", pname);
            parameters.put("Parameter3", cat);
            parameters.put("Parameter4", brand);
            parameters.put("Parameter5", mat);
            parameters.put("Parameter6", qty);
            parameters.put("Parameter7", buy);
            parameters.put("Parameter8", sell);
            parameters.put("Parameter9", size);
            parameters.put("Parameter10", add);

            TableModel tm = jTable1.getModel();
            JRTableModelDataSource dataSource = new JRTableModelDataSource(tm);

            JasperPrint jr = JasperFillManager.fillReport(stream, parameters, dataSource);
            JasperViewer.viewReport(jr, false);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
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
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
