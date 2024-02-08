/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.styely.gui;

import com.styley.Model.MySQL;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Anjana
 */
public class select_invoice extends javax.swing.JDialog {

    DecimalFormat df = new DecimalFormat("0.00");

    public select_invoice(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadTable();
    }

    Manege_return_product mr;

    public select_invoice(Manege_return_product parent, boolean modal) {

        initComponents();
        loadTable();
        this.mr = parent;
    }

    public void loadTable() {
        try {
            ResultSet rs = MySQL.search("SELECT `invoice`.`id`,`stock`.`id`,`customer`.`id`,`customer`.`name`,`company_user`.`id`,`company_user`.`name`,`product`.`id`,`product`.`name`,`invoice_item`.`qty`,`stock`.`Selling_price`,`invoice`.`date_time` FROM `invoice` INNER JOIN `invoice_item` ON `invoice`.`id` = `invoice_item`.`invoice_id` INNER JOIN `stock` ON `stock`.`id` = `invoice_item`.`Stock_id` INNER JOIN `customer` ON `customer`.`id` = `invoice`.`customer_id` INNER JOIN `company_user` ON `company_user`.`id` =`invoice`.`company_user_id` INNER JOIN `product` ON `product`.`id` = `stock`.`product_id`");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("invoice.id"));
                v.add(rs.getString("stock.id"));
                v.add(rs.getString("customer.id"));
                v.add(rs.getString("customer.name"));
                v.add(rs.getString("company_user.id"));
                v.add(rs.getString("company_user.name"));
                v.add(rs.getString("product.id"));
                v.add(rs.getString("product.name"));
                v.add(rs.getString("invoice_item.qty"));
                v.add(df.format(Double.parseDouble(rs.getString("stock.Selling_price"))));
                String idate = rs.getString("invoice.date_time");
                String[] d = idate.split(" ");
                String d1 = d[0];
                v.add(d1);

                dtm.addRow(v);
            }
            jTable1.setModel(dtm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.CardLayout());

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jPanel2.setLayout(new java.awt.CardLayout());

        jTable1.setFont(new java.awt.Font("Candara", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice", "Stock", "C id", "Name", "U id", "Name", "Product id", "P name", "Qty", "Price", "Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
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

        jPanel2.add(jScrollPane1, "card2");

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Candara", 1, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/styley/img/reset_icon.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 547, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, "card2");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String text = jTextField1.getText();
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add invice code", "warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                ResultSet rs = MySQL.search("SELECT `invoice`.`id`,`stock`.`id`,`customer`.`id`,`customer`.`name`,`company_user`.`id`,`company_user`.`name`,`product`.`id`,`product`.`name`,`invoice_item`.`qty`,`stock`.`Selling_price`,`invoice`.`date_time` FROM `invoice` INNER JOIN `invoice_item` ON `invoice`.`id` = `invoice_item`.`invoice_id` INNER JOIN `stock` ON `stock`.`id` = `invoice_item`.`Stock_id` INNER JOIN `customer` ON `customer`.`id` = `invoice`.`customer_id` INNER JOIN `company_user` ON `company_user`.`id` =`invoice`.`company_user_id` INNER JOIN `product` ON `product`.`id` = `stock`.`product_id` WHERE `invoice`.`unique_id`='" + text + "'");
                DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                dtm.setRowCount(0);
                while (rs.next()) {
                    Vector v = new Vector();
                    v.add(rs.getString("invoice.id"));
                    v.add(rs.getString("stock.id"));
                    v.add(rs.getString("customer.id"));
                    v.add(rs.getString("customer.name"));
                    v.add(rs.getString("company_user.id"));
                    v.add(rs.getString("company_user.name"));
                    v.add(rs.getString("product.id"));
                    v.add(rs.getString("product.name"));
                    v.add(rs.getString("invoice_item.qty"));
                    v.add(df.format(Double.parseDouble(rs.getString("stock.Selling_price"))));
                    String idate = rs.getString("invoice.date_time");
                    String[] d = idate.split(" ");
                    String d1 = d[0];
                    v.add(d1);

                    dtm.addRow(v);
                }
                jTable1.setModel(dtm);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        loadTable();
        jTextField1.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        int selectRow = jTable1.getSelectedRow();
        String invoice_id = jTable1.getValueAt(selectRow, 0).toString();
        String Stock_id = jTable1.getValueAt(selectRow, 1).toString();
        String C_id = jTable1.getValueAt(selectRow, 2).toString();
        String C_name = jTable1.getValueAt(selectRow, 3).toString();
        String U_id = jTable1.getValueAt(selectRow, 4).toString();
        String U_name = jTable1.getValueAt(selectRow, 5).toString();
        String P_id = jTable1.getValueAt(selectRow, 6).toString();
        String P_name = jTable1.getValueAt(selectRow, 7).toString();
        String qty = "1";
        String price = jTable1.getValueAt(selectRow, 9).toString();
//        String Date = jTable1.getValueAt(selectRow, 10).toString();

      
        this.mr.jLabel4.setText(invoice_id);
        this.mr.jLabel5.setText(Stock_id);
        this.mr.jLabel7.setText(C_id);
        this.mr.jLabel9.setText(C_name);
        this.mr.jLabel22.setText(U_id);
        this.mr.jLabel24.setText(U_name);
        this.mr.jLabel13.setText(P_id);
        this.mr.jLabel15.setText(P_name);
        this.mr.jTextField1.setText(qty);
        this.mr.jLabel19.setText(price);
        
        this.dispose();
    }//GEN-LAST:event_jTable1MouseClicked

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
            java.util.logging.Logger.getLogger(select_invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(select_invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(select_invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(select_invoice.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                select_invoice dialog = new select_invoice(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
