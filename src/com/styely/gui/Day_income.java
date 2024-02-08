/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.styely.gui;

import com.styley.Model.MySQL;
import java.io.InputStream;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.regex.Pattern;
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
public class Day_income extends javax.swing.JPanel {

    DecimalFormat df = new DecimalFormat("0.00");
    DecimalFormat df1 = new DecimalFormat("0");
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String setdate = sdf.format(new Date());
    String[] getMoth = setdate.split("-");
    String y = getMoth[0];
    String m = getMoth[1];

    String dm = y + "-" + m;

    public Day_income() {
        initComponents();
        daily();
        ern();
        jLabel2.setText(setdate);
        arrears();
        titalincom();
    }

    public void titalincom() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `total_income` ORDER BY `total_income`.`id` DESC LIMIT 1");
            rs.next();
            double tvaue = Double.parseDouble(rs.getString("total"));

            jLabel7.setText(df.format(tvaue));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void arrears() {
        try {
            ResultSet rs = MySQL.search("SELECT COUNT(`grn_payment`.`id`) AS available FROM `grn_payment` WHERE `grn_payment`.`balance` LIKE'-%'");
            rs.next();
            String count = rs.getString("available");
            jLabel10.setText(count);
        } catch (Exception e) {
        }
    }

    public void randomSearch() {

        String mindate = null;
        String maxdtae = null;

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        if (jDateChooser1.getDate() != null) {
            mindate = sdf1.format(jDateChooser1.getDate());
        } else {
            JOptionPane.showMessageDialog(this, "Please enter minimume date", maxdtae, JOptionPane.WARNING_MESSAGE);
        }
        if (jDateChooser2.getDate() != null) {
            maxdtae = sdf1.format(jDateChooser2.getDate());
        } else {
            JOptionPane.showMessageDialog(this, "Please enter minimume date", maxdtae, JOptionPane.WARNING_MESSAGE);
        }
//        System.out.println(mindate);
//        System.out.println(maxdtae);
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `income` INNER JOIN `invoice` ON `invoice`.`id` = `income`.`invoice_id` INNER JOIN `invoice_item` ON `invoice`.`id` = `invoice_item`.`invoice_id` INNER JOIN `stock` ON `invoice_item`.`Stock_id` = `stock`.`id` INNER JOIN `product` ON `stock`.`product_id` = `product`.`id` INNER JOIN `payment_type` ON `income`.`payment_type` = `payment_type`.`id` WHERE `income`.`income_day` >= '" + mindate + "' AND `income`.`income_day` <= '" + maxdtae + "'");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("income.id"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("invoice_item.qty"));
                v.add(rs.getString("payment_type.name"));
                v.add(rs.getString("income.income_day"));

                dtm.addRow(v);
            }
            jTable1.setModel(dtm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reset() {
        jDateChooser1.setDate(null);
        jDateChooser2.setDate(null);
        jLabel12.setText("Daily incom");
        daily();
        ern();
    }

    public void ern() {

        try {
            ResultSet rs = MySQL.search("SELECT * FROM `income`");
            double total = 0;
            while (rs.next()) {
                String t = rs.getString("income_value");

                total = total + Double.parseDouble(t);

            }
            jLabel4.setText(df.format(total));
        } catch (Exception e) {
        }

    }

    public void daily() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `income` INNER JOIN `invoice` ON `invoice`.`id` = `income`.`invoice_id` INNER JOIN `invoice_item` ON `invoice`.`id` = `invoice_item`.`invoice_id` INNER JOIN `stock` ON `invoice_item`.`Stock_id` = `stock`.`id` INNER JOIN `product` ON `stock`.`product_id` = `product`.`id` INNER JOIN `payment_type` ON `income`.`payment_type` = `payment_type`.`id`");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("income.id"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("invoice_item.qty"));
                v.add(rs.getString("payment_type.name"));
                v.add(rs.getString("income.income_day"));

                dtm.addRow(v);
            }
            jTable1.setModel(dtm);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void today() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `income` INNER JOIN `invoice` ON `invoice`.`id` = `income`.`invoice_id` INNER JOIN `invoice_item` ON `invoice`.`id` = `invoice_item`.`invoice_id` INNER JOIN `stock` ON `invoice_item`.`Stock_id` = `stock`.`id` INNER JOIN `product` ON `stock`.`product_id` = `product`.`id` INNER JOIN `payment_type` ON `income`.`payment_type` = `payment_type`.`id` WHERE `income`.`income_day` = '" + setdate + "'");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("income.id"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("invoice_item.qty"));
                v.add(rs.getString("payment_type.name"));
                v.add(rs.getString("income.income_day"));

                dtm.addRow(v);
            }
            jTable1.setModel(dtm);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void month() {
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `income` INNER JOIN `invoice` ON `invoice`.`id` = `income`.`invoice_id` INNER JOIN `invoice_item` ON `invoice`.`id` = `invoice_item`.`invoice_id` INNER JOIN `stock` ON `invoice_item`.`Stock_id` = `stock`.`id` INNER JOIN `product` ON `stock`.`product_id` = `product`.`id` INNER JOIN `payment_type` ON `income`.`payment_type` = `payment_type`.`id` WHERE `income`.`income_day` LIKE '" + dm + "%'"); //2022-7
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("income.id"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("invoice_item.qty"));
                v.add(rs.getString("payment_type.name"));
                v.add(rs.getString("income.income_day"));

                dtm.addRow(v);
            }
            jTable1.setModel(dtm);
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
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton4 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton7 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setLayout(new java.awt.CardLayout());

        jLabel1.setFont(new java.awt.Font("Segoe Script", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Income Reports");

        jLabel2.setFont(new java.awt.Font("Candara", 0, 18)); // NOI18N
        jLabel2.setText("date");

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jButton1.setText("Daily income");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 270, 35));

        jButton2.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jButton2.setText("Get Random income");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 270, 270, 35));

        jButton3.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jButton3.setText("Monthly income");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 220, 270, 35));

        jPanel3.setLayout(new java.awt.CardLayout());

        jTable1.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Product", "Quentity", "method", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel3.add(jScrollPane1, "card2");

        jPanel2.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 4, 900, 200));

        jButton4.setBackground(new java.awt.Color(204, 153, 0));
        jButton4.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Get Report");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 410, 289, 40));

        jLabel3.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Total Erning:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, 138, 30));

        jLabel4.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("0.00");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 220, 141, 30));

        jButton5.setBackground(new java.awt.Color(51, 153, 0));
        jButton5.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Reset to Default");
        jButton5.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 0, true));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 360, 290, 40));
        jPanel2.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 110, 30));
        jPanel2.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 110, 30));

        jLabel5.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("To");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 30, 30));

        jLabel6.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Avilable income:");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 270, 138, 30));

        jLabel7.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("0.00");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 270, 141, 30));

        jLabel8.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Arrears balance payment for Grn");
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 250, 30));

        jLabel9.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Avilable:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 350, 90, 30));

        jLabel10.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("0");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 70, 30));

        jButton6.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jButton6.setText("Check");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 350, 140, 30));

        jLabel11.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Add on Invest:");
        jPanel2.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 110, 30));

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField1KeyTyped(evt);
            }
        });
        jPanel2.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 200, 30));

        jButton7.setBackground(new java.awt.Color(0, 51, 102));
        jButton7.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 255, 255));
        jButton7.setText("Submit");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 410, 100, 30));

        jLabel12.setFont(new java.awt.Font("Candara", 1, 14)); // NOI18N
        jLabel12.setText("Daily incom");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(165, 165, 165)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 465, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jPanel1, "card2");
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jLabel12.setText("Daily incom");
        today();

        try {
            ResultSet rs = MySQL.search("SELECT * FROM `income` WHERE `income`.`income_day` = '" + setdate + "'");
            double total = 0;
            while (rs.next()) {
                String t = rs.getString("income_value");

                total = total + Double.parseDouble(t);

            }
            jLabel4.setText(df.format(total));
        } catch (Exception e) {
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        jLabel12.setText("Ramdom incom");
        randomSearch();

        String mindate = null;
        String maxdtae = null;

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        if (jDateChooser1.getDate() != null) {
            mindate = sdf1.format(jDateChooser1.getDate());
        }
        if (jDateChooser2.getDate() != null) {
            maxdtae = sdf1.format(jDateChooser2.getDate());
        }

        try {
            ResultSet rs = MySQL.search("SELECT * FROM `income` WHERE `income`.`income_day` >='" + mindate + "' AND `income`.`income_day` <= '" + maxdtae + "'");
            double total = 0;
            while (rs.next()) {
                String t = rs.getString("income_value");

                total = total + Double.parseDouble(t);

            }
            jLabel4.setText(df.format(total));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jLabel12.setText("Monthly incom");
        month();
        try {
            ResultSet rs = MySQL.search("SELECT * FROM `income` WHERE `income`.`income_day` LIKE '" + dm + "%'");
            double total = 0;
            while (rs.next()) {
                String t = rs.getString("income_value");

                total = total + Double.parseDouble(t);

            }
            jLabel4.setText(df.format(total));
        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        avilablegrn_paymnet av = new avilablegrn_paymnet(this, true);
        av.setVisible(true);
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTextField1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyTyped
        // TODO add your handling code here:
        String price = jTextField1.getText();
        String text = price + evt.getKeyChar();

        if (!Pattern.compile("(0|0[.]|0[.][0-9]*)|[1-9]|[1-9][0-9]*|[1-9][0-9]*[.]|[1-9][0-9]*[.][0-9]*").matcher(text).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField1KeyTyped

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String invest = jTextField1.getText();
        if (invest.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please Add invest paymet", "warning", JOptionPane.WARNING_MESSAGE);
        } else if (!Pattern.compile("([1-9][0-9]*)|(([1-9][0-9]*[.]([0]*[1-9][0-9]*)))|([0][.]([0]*[1-9][0-9]*))").matcher(invest).matches()) {
            JOptionPane.showMessageDialog(this, "Invalid invest payment", "warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet rs = MySQL.search("SELECT * FROM `total_income` ORDER BY `total_income`.`id` DESC LIMIT 1");
                rs.next();
                String oldt = rs.getString("Total");
                double newt = Double.parseDouble(oldt) + Double.parseDouble(invest);
                MySQL.iud("INSERT INTO `total_income`(`Total`,`Tdate`) VALUES('" + newt + "','" + setdate + "')");
                titalincom();
                jTextField1.setText("");
                JOptionPane.showMessageDialog(this, "Yiur invetmet is Sucess", "Sucess", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
            }
        }

    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:

        DecimalFormat df = new DecimalFormat("0.00");

        try {

            ResultSet rs = MySQL.search("SELECT SUM(`income`.`income_value`) AS `tot` FROM `income`");
            rs.next();
            String tot = rs.getString("tot");
            double totalinvest = Double.parseDouble(jLabel7.getText()) - Double.parseDouble(tot);
            
            InputStream stream = getClass().getResourceAsStream("/report/DF_incom.jasper");

//            JasperReport jr = JasperCompileManager.compileReport(stream);
            HashMap parameters = new HashMap();
            parameters.put("Parameter1", jLabel12.getText());
            parameters.put("Parameter2", jLabel4.getText());
            parameters.put("Parameter3", df.format(totalinvest));
            parameters.put("Parameter4", jLabel7.getText());

            TableModel tm = jTable1.getModel();
            JRTableModelDataSource dataSource = new JRTableModelDataSource(tm);
            JasperPrint jp = JasperFillManager.fillReport(stream, parameters, dataSource);
            JasperViewer.viewReport(jp, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
