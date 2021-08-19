
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.PatternSyntaxException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author soumajit
 */
class note
{
    public void showtable(JTable table, String valuetoserach)
    {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try
        {
            String path=new File(".").getCanonicalPath();
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
            ps=con.prepareStatement("SELECT * FROM `notes` WHERE `id`||`note` LIKE ?");
            ps.setString(1, "%"+valuetoserach+"%");
            rs=ps.executeQuery();
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            Object row[];
            while(rs.next())
            {
                row=new Object[1];
                row[0]=rs.getString(2);
                model.addRow(row);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        finally
        {
            try{
                con.close();
                rs.close();
                ps.close();
            }
            catch(Exception e)
            {
                
            }
        }
    }
}

public class EditNotes extends javax.swing.JFrame {
    note n=new note();
    public EditNotes() {
        initComponents();
        n.showtable(jTable1, "");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        edit = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit Notes");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "notes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
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
        jScrollPane2.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Search notes :");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Note :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Edit & Delete Notes :");

        jButton1.setText("Close");

        edit.setText("Edit");
        edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1)
                        .addGap(34, 34, 34))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(edit, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(27, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(64, 64, 64)))
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField1))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(edit, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        String text=jTextField1.getText();
        jTable1.setModel(new DefaultTableModel(null, new Object[]{"notes"}));
        n.showtable(jTable1, text);
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int rowindex=jTable1.getSelectedRow();
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        jTextArea1.setText(model.getValueAt(rowindex, 0).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void editActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editActionPerformed
        boolean edited=false;
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            long id=0;
            DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
            String edittext=jTextArea1.getText();
            int rownum=jTable1.getSelectedRow();
            String originaltext=model.getValueAt(rownum, 0).toString();
            if(edittext.isEmpty())
            {
                JOptionPane.showMessageDialog(this, "Can't edit with empty note", "Oops!",JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                String path=new File(".").getCanonicalPath();
                Class.forName("org.sqlite.JDBC");
                con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
                ps=con.prepareStatement("SELECT `id` FROM `notes` WHERE `note`=?");
                ps.setString(1, originaltext);
                rs=ps.executeQuery();
                id=rs.getLong("id");
                ps=con.prepareStatement("UPDATE `notes` SET `note`=? WHERE `id`=?");
                ps.setString(1, edittext);
                ps.setLong(2, id);
                if(ps.executeUpdate()>0)
                    {
                        edited=true;
                        JOptionPane.showMessageDialog(this, "Note Edited successfully","Success!",JOptionPane.INFORMATION_MESSAGE);
                    }
                if(edited)
                {
                    n.showtable(jTable1, "");
                    jTable1.setModel(new DefaultTableModel(null, new Object[]{"notes"}));
                    n.showtable(jTable1, jTextField1.getText());
                }
                
            }
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            JOptionPane.showMessageDialog(this, "Please select a note first", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Problem to communicate with database", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch(ClassNotFoundException cnfe)
        {
            JOptionPane.showMessageDialog(this, "Can't find database driver", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe)
        {
            JOptionPane.showMessageDialog(this, "Can't find database path", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            try{
            con.close();
            ps.close();
            rs.close();
            }catch(Exception e)
            {
                
            }
        }
    }//GEN-LAST:event_editActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try
        {
            DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
            int rownum=jTable1.getSelectedRow();
            String originalnote=model.getValueAt(rownum, 0).toString();
            int choice=JOptionPane.showConfirmDialog(this, "Are you sure you want to delete?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if(choice==0)
            {
            long id=0;
            //String edittext=jTextArea1.getText();
            String path=new File(".").getCanonicalPath();
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
            ps=con.prepareStatement("SELECT `id` FROM `notes` WHERE `note`=?");
            ps.setString(1, originalnote);
            rs=ps.executeQuery();
            id=rs.getLong("id");
            
            ps=con.prepareStatement("DELETE FROM `notes` WHERE `id` = ?");
            ps.setLong(1, id);
            if(ps.executeUpdate()>0)
            {
                JOptionPane.showMessageDialog(null, "note Deleted Successfully");
                n.showtable(jTable1, "");
                jTable1.setModel(new DefaultTableModel(null, new Object[]{"notes"}));
                n.showtable(jTable1, jTextField1.getText());
                jTextArea1.setText("");
            }
            }
        }
        catch(IOException ioe)
        {
            JOptionPane.showMessageDialog(this, ioe);
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            JOptionPane.showMessageDialog(this, "Please select a note first", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch(ClassNotFoundException cnf)
        {
            JOptionPane.showMessageDialog(this, "Can't find driver", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(this, "Problem to connect with Database!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            try
            {
            con.close();
            ps.close();
            rs.close();
            }
            catch(Exception e)
            {
                
            }
        }
        //end of delete
    }//GEN-LAST:event_deleteActionPerformed

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
            java.util.logging.Logger.getLogger(EditNotes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditNotes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditNotes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditNotes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditNotes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delete;
    private javax.swing.JButton edit;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
