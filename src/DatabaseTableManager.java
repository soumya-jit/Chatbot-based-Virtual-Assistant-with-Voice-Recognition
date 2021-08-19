
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author soumajit
 */

class ShowData
{
    public void showtableforqna(JTable table, String valuetosearch)
    {
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try
        {
            String path=new File(".").getCanonicalPath();
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
            ps=con.prepareStatement("SELECT * FROM `qna` WHERE `id`||`question`||`answer` LIKE ?");
            ps.setString(1, "%"+valuetosearch+"%");
            rs=ps.executeQuery();
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            Object row[];
            while(rs.next())
            {
                row=new Object[3];
                row[0]=rs.getString(1);
                row[1]=rs.getString(2);
                row[2]=rs.getString(3);
                model.addRow(row);
            }
        }
        catch(Exception ex)
        {
            System.out.println("prblm is here"+ex);
        }
        finally
        {
            try
            {
                con.close();
                rs.close();
                ps.close();
            }
            catch(Exception e){}
        }
    }
}


public class DatabaseTableManager extends javax.swing.JFrame {
    ShowData sd=new ShowData();
    public DatabaseTableManager() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        sd.showtableforqna(jTable2, "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        QnASearchField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        deleteQnA = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        addQnA = new javax.swing.JButton();
        editQnA = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manage QnA");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel7.setText("Search :");

        QnASearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                QnASearchFieldKeyReleased(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "question", "answer"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        deleteQnA.setText("Delete");
        deleteQnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteQnAActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Add Questions :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Question :");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Answer :");

        addQnA.setText("Add");
        addQnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addQnAActionPerformed(evt);
            }
        });

        editQnA.setText("Edit");
        editQnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editQnAActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 241, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QnASearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(93, 93, 93))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField6, javax.swing.GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
                            .addComponent(jTextField7)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(editQnA, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                        .addComponent(addQnA, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteQnA, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(200, 200, 200))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(QnASearchField, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addQnA, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editQnA, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(130, 130, 130))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                .addGap(68, 68, 68)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteQnA, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(22, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 865, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("QnA", jPanel3);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void editQnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editQnAActionPerformed
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement ps=null;
        boolean same_qus_found=false;
        try
        {
            DefaultTableModel model=(DefaultTableModel)jTable2.getModel();
            Integer id=Integer.parseInt(model.getValueAt(jTable2.getSelectedRow(), 0).toString());
            String user_question=jTextField6.getText();
            String user_answer=jTextField7.getText();
            String path=new File(".").getCanonicalPath();
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:"+path +"\\chatbot.db");
            ps=con.prepareStatement("SELECT * FROM `qna`");
            rs=ps.executeQuery();
            while(rs.next())
            {
                String db_question=rs.getString("question");
                String db_answer=rs.getString("answer");
                if(user_question.equals(db_question) && user_answer.equals(db_answer))
                {
                    same_qus_found=true;
                    break;
                }
            }
            if(!same_qus_found)
            {
                ps=con.prepareStatement("UPDATE `qna` SET `question`=?,`answer`=? WHERE `id`=?");
                ps.setString(1, user_question);
                ps.setString(2, user_answer);
                ps.setInt(3, id);
                if(ps.executeUpdate()>0)
                {
                    JOptionPane.showMessageDialog(this, "Question edited successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
                }
                sd.showtableforqna(jTable2, "");
                jTable2.setModel(new DefaultTableModel(null, new Object[]{"id", "question", "answer"}));
                sd.showtableforqna(jTable2, QnASearchField.getText());
            }
            else if(same_qus_found)
            {
                JOptionPane.showMessageDialog(this, "Same question already exists!", "Oops!", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(this, "Database path not found!", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(this, "Problem to communicate with database!", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        catch(ClassNotFoundException cnfe)
        {
            JOptionPane.showMessageDialog(this, "Database driver not found!", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        catch(ArrayIndexOutOfBoundsException aioube)
        {
            JOptionPane.showMessageDialog(this, "Please select a row from the table!", "Error!", JOptionPane.ERROR_MESSAGE);
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
    }//GEN-LAST:event_editQnAActionPerformed

    private void addQnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addQnAActionPerformed
        if(jTextField6.getText().equals("") || jTextField6.getText().isEmpty() || jTextField6.getText()==null ||jTextField7.getText().equals("") || jTextField7.getText().isEmpty() || jTextField7.getText()==null)
        {
            JOptionPane.showMessageDialog(this, "One or more fields are empty!", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        else
        {
            Connection con=null;
            ResultSet rs=null;
            PreparedStatement ps=null;
            boolean same_qus_found=false;
            try
            {
                String user_question=jTextField6.getText();
                String user_answer=jTextField7.getText();
                String path=new File(".").getCanonicalPath();
                Class.forName("org.sqlite.JDBC");
                con=DriverManager.getConnection("jdbc:sqlite:"+path +"\\chatbot.db");
                ps=con.prepareStatement("SELECT * FROM `qna`");
                rs=ps.executeQuery();
                while(rs.next())
                {
                    String db_question=rs.getString("question");
                    if(user_question.equals(db_question) && user_answer.equals(rs.getString("answer")))
                    {
                        same_qus_found=true;
                        break;
                    }
                }
                if(!same_qus_found)
                {
                    ps = con.prepareStatement("INSERT INTO `qna` (id, question, answer) values (?,?,?)");
                    ps.setString(1, null);
                    ps.setString(2, user_question);
                    ps.setString(3, user_answer);
                    int check=ps.executeUpdate();
                    if(check==1)
                    {
                        JOptionPane.showMessageDialog(this, "Question added successfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
                    }
                    sd.showtableforqna(jTable2, "");
                    jTable2.setModel(new DefaultTableModel(null, new Object[]{"id", "question", "answer"}));
                    sd.showtableforqna(jTable2, QnASearchField.getText());
                }
                else if(same_qus_found)
                {
                    JOptionPane.showMessageDialog(this, "Same question already exists!", "Oops!", JOptionPane.WARNING_MESSAGE);
                }
            }
            catch(IOException e)
            {
                JOptionPane.showMessageDialog(this, "Database path not found!", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            catch(SQLException sqle)
            {
                JOptionPane.showMessageDialog(this, "Problem to communicate with database!", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            catch(ClassNotFoundException cnfe)
            {
                JOptionPane.showMessageDialog(this, "Database driver not found!", "Oops!", JOptionPane.ERROR_MESSAGE);
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
        }
    }//GEN-LAST:event_addQnAActionPerformed

    private void deleteQnAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteQnAActionPerformed
        Connection con=null;
        PreparedStatement ps=null;
        try{
            String path=new File(".").getCanonicalPath();
            int rowindex=jTable2.getSelectedRow();
            int rowcount=jTable2.getSelectedRowCount();
            if(rowindex==-1 || rowcount==0)
            {
                JOptionPane.showMessageDialog(this, "No rows are selected!\nPlease select atleast one row first", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            else
            {
                int input = JOptionPane.showConfirmDialog(this,
                    "Do you really want to delete?", "Please Confirm!",JOptionPane.YES_NO_OPTION);
                if(input==0)
                {
                    DefaultTableModel model=(DefaultTableModel)jTable2.getModel();
                    if(rowcount>1)
                    {
                        int selectedrows[]=jTable2.getSelectedRows();
                        int ids[]=new int[selectedrows.length];
                        for(int i=0;i<selectedrows.length;i++)
                        {
                            ids[i]=Integer.parseInt(model.getValueAt(selectedrows[i], 0).toString());
                        }
                        int a=0;
                        while(rowcount>0)
                        {

                            Integer Id = ids[a];
                            Class.forName("org.sqlite.JDBC");
                            con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
                            ps=con.prepareStatement("DELETE FROM `qna` WHERE `id` = ?");
                            ps.setInt(1, Id);
                            if(ps.executeUpdate()>0)
                            {

                            }
                            rowcount-=1;
                            a+=1;
                        }
                        sd.showtableforqna(jTable2, "");
                        jTable2.setModel(new DefaultTableModel(null, new Object[]{"id", "question", "answer"}));
                        sd.showtableforqna(jTable2, QnASearchField.getText());
                        this.jTextField6.setText("");
                        this.jTextField7.setText("");
                        JOptionPane.showMessageDialog(this, "Questions Deleted Successfully");
                    }
                    else if(rowcount==1)
                    {

                        Integer Id = Integer.parseInt(model.getValueAt(rowindex, 0).toString());
                        Class.forName("org.sqlite.JDBC");
                        con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
                        ps=con.prepareStatement("DELETE FROM `qna` WHERE `id` = ?");
                        ps.setInt(1, Id);
                        if(ps.executeUpdate()>0)
                        {
                            sd.showtableforqna(jTable2, "");
                            jTable2.setModel(new DefaultTableModel(null, new Object[]{"id", "question", "answer"}));
                            sd.showtableforqna(jTable2, QnASearchField.getText());
                            this.jTextField6.setText("");
                            this.jTextField7.setText("");
                            JOptionPane.showMessageDialog(this, "Question Deleted Successfully");
                        }
                    }
                }
            }
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(this, "Problem to communicate with database", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch(ClassNotFoundException cnfe)
        {
            JOptionPane.showMessageDialog(this, "Can't find driver", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe)
        {
            JOptionPane.showMessageDialog(this, "Problem to find database path", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch(NullPointerException npe)
        {
            JOptionPane.showMessageDialog(this, "null pointer exception", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            try{
                con.close();
                ps.close();
            }
            catch(Exception ex){}
        }
    }//GEN-LAST:event_deleteQnAActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int rowindex=jTable2.getSelectedRow();
        DefaultTableModel model=(DefaultTableModel)jTable2.getModel();
        this.jTextField6.setText(model.getValueAt(rowindex, 1).toString());
        this.jTextField7.setText(model.getValueAt(rowindex, 2).toString());
    }//GEN-LAST:event_jTable2MouseClicked

    private void QnASearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_QnASearchFieldKeyReleased
        jTable2.setModel(new DefaultTableModel(null, new Object[]{"id", "question", "answer"}));
        sd.showtableforqna(jTable2, QnASearchField.getText());
    }//GEN-LAST:event_QnASearchFieldKeyReleased

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
            java.util.logging.Logger.getLogger(DatabaseTableManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatabaseTableManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatabaseTableManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatabaseTableManager.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DatabaseTableManager().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField QnASearchField;
    private javax.swing.JButton addQnA;
    private javax.swing.JButton deleteQnA;
    private javax.swing.JButton editQnA;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    // End of variables declaration//GEN-END:variables
}
