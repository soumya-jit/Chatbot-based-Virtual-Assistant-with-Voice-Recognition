
import javax.swing.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
/**
 *
 * @author soumajit
 */
public class set_notes extends javax.swing.JFrame {

    public set_notes() {
        initComponents();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jTextArea1.setLineWrap(true);
        jTextArea1.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        new_menu = new javax.swing.JMenuItem();
        save_menu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Notes");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setText("Save");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Back");
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 611, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(138, 138, 138))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 347, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(25, 25, 25))
        );

        jMenu1.setText("File");

        new_menu.setText("New");
        new_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_menuActionPerformed(evt);
            }
        });
        jMenu1.add(new_menu);

        save_menu.setText("Save");
        save_menu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                save_menuActionPerformed(evt);
            }
        });
        jMenu1.add(save_menu);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void new_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_menuActionPerformed
        boolean note_saved=false;Connection con=null;PreparedStatement ps=null;
        String text=jTextArea1.getText();
        if(!jTextArea1.getText().isEmpty())
        {
            int choice=JOptionPane.showConfirmDialog(this, "You have unsaved notes.\nDo you want to save it?", "Want to save?", JOptionPane.YES_NO_CANCEL_OPTION);
            if(choice==JOptionPane.YES_OPTION)
            {
                try{
                    String path=new File(".").getCanonicalPath();
                    Class.forName("org.sqlite.JDBC");
                    con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
                    ps = con.prepareStatement("INSERT INTO `notes`(`id`, `note`) VALUES (?, ?)");
                    ps.setString(1, null);
                    ps.setString(2, text);
                    int check=ps.executeUpdate();
                    if(check==1)
                    {
                        note_saved=true;
                        JOptionPane.showMessageDialog(null, "note saved successfully");
                    }
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(this, ex);
                }
                finally
                {
                    try{
                    con.close();
                    ps.close();
                    }
                    catch(Exception ex)
                    {
                    }
                }
            }
            else if(choice==JOptionPane.NO_OPTION)
            {
                jTextArea1.setText("");
            }
        }
        else if(jTextArea1.getText().isEmpty() || jTextArea1.getText().equals(""))
        {
            jTextArea1.setText("");
        }
    }//GEN-LAST:event_new_menuActionPerformed

    private void save_menuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_save_menuActionPerformed
        String text=jTextArea1.getText();//boolean same_note_found=false;
        boolean note_saved=false;Connection con=null;PreparedStatement ps=null;
	try
        {
            if(text.equals(null) || text.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Can't store empty note", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                String path=new File(".").getCanonicalPath();
                Class.forName("org.sqlite.JDBC");
                con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
                ps = con.prepareStatement("INSERT INTO `notes`(`id`, `note`) VALUES (?, ?)");
                ps.setString(1, null);
                ps.setString(2, text);
                int check=ps.executeUpdate();
                if(check==1)
                {
                    note_saved=true;
                    JOptionPane.showMessageDialog(null, "note saved successfully");
                }
            }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, ex);
        }
        finally
        {
            try{
                con.close();
                ps.close();
                }
                catch(Exception ex)
                {
                }
        }
    }//GEN-LAST:event_save_menuActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String text=jTextArea1.getText();//boolean same_note_found=false;
        boolean note_saved=false;Connection con=null;PreparedStatement ps=null;
	try
        {
            if(text.equals(null) || text.equals(""))
            {
                JOptionPane.showMessageDialog(this, "Can't store empty note", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                String path=new File(".").getCanonicalPath();
                Class.forName("org.sqlite.JDBC");
                con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
                ps = con.prepareStatement("INSERT INTO `notes`(`id`, `note`) VALUES (?, ?)");
                ps.setString(1, null);
                ps.setString(2, text);
                int check=ps.executeUpdate();
                if(check==1)
                {
                    note_saved=true;
                    JOptionPane.showMessageDialog(null, "note saved successfully");
                }
            }
        }catch(Exception ex)
        {
            JOptionPane.showMessageDialog(this, ex);
            jTextArea1.requestFocus();
        }
        finally
        {
            try
            {
                con.close();
                ps.close();
            }
            catch(Exception ex)
            {
            }
        }
        jTextArea1.requestFocus();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ResultSet rs=null;
        Connection con=null;
        PreparedStatement ps=null;
        try{
        boolean already_saved=false;
        String text=jTextArea1.getText();
        if(!jTextArea1.getText().isEmpty())
        {
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:"+ new File(".").getCanonicalPath() +"\\chatbot.db");
            ps=con.prepareStatement("SELECT * FROM `notes`");
            rs=ps.executeQuery();
            while(rs.next())
            {
                if(rs.getString("note").equals(text))
                {
                    already_saved=true;
                }
            }
            if(!already_saved)
            {
            int choice=JOptionPane.showConfirmDialog(this, "You have unsaved notes.\nDo you want to save it?", "Want to save?", JOptionPane.YES_NO_CANCEL_OPTION);
            if(choice==JOptionPane.YES_OPTION)
            {
                try{
                    String path=new File(".").getCanonicalPath();
                    Class.forName("org.sqlite.JDBC");
                    con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
                    ps = con.prepareStatement("INSERT INTO `notes`(`id`, `note`) VALUES (?, ?)");
                    ps.setString(1, null);
                    ps.setString(2, text);
                    int check=ps.executeUpdate();
                    if(check==1)
                    {
                        JOptionPane.showMessageDialog(null, "note saved successfully");
                        this.dispose();
                    }
                }catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(this, ex);
                }
                finally{
                    try{
                    con.close();
                    ps.close();
                    }
                    catch(Exception ex)
                    {
                    }
                }
            }
            else if(choice==JOptionPane.NO_OPTION)
            {
                this.dispose();
            }
        }
            else
            {
                this.dispose();
            }
        }
        else if(jTextArea1.getText().isEmpty() || jTextArea1.getText().equals(""))
        {
            this.dispose();
        }
    }
    catch(NullPointerException npe)
    {
        
    }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(set_notes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(set_notes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(set_notes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(set_notes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new set_notes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JMenuItem new_menu;
    private javax.swing.JMenuItem save_menu;
    // End of variables declaration//GEN-END:variables
}
