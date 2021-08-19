
import java.io.File;
import java.io.IOException;
import javax.swing.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author soumajit
 */
class alarm
{
    public void showtable(JTable table, String valuetosearch)
    {
        try
        {
            String path=new File(".").getCanonicalPath();
            Class.forName("org.sqlite.JDBC");
            Connection con=DriverManager.getConnection("jdbc:sqlite:"+path +"\\chatbot.db");
            PreparedStatement ps;
            ps=con.prepareStatement("SELECT * FROM `alarms` WHERE `id`||`date`||`month`||`year`||`hour`||`minute`||`subject`||`am_pm` LIKE ?");
            ps.setString(1, "%"+valuetosearch+"%");
            ResultSet rs=ps.executeQuery();
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            Object row[];
            while(rs.next())
            {
                row=new Object[8];
                row[0]=rs.getString(1);
                row[1]=rs.getString(7);
                row[2]=rs.getString(5);
                row[3]=rs.getString(6);
                row[4]=rs.getString(9);
                row[5]=rs.getString(2);
                row[6]=rs.getString(3);
                row[7]=rs.getString(4);
                model.addRow(row);
            }
            con.close();rs.close();ps.close();
        }
        catch(Exception ex)
        {
            System.out.println("prblm is here"+ex);
        }
    }
}
public class EditAlarm extends javax.swing.JFrame {

    alarm a=new alarm();
    public EditAlarm() {
        initComponents();
        a.showtable(jTable1, "");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        editbutton = new javax.swing.JButton();
        close_button = new javax.swing.JButton();
        delete_button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        search_field = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        subject = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        hour = new javax.swing.JComboBox<>();
        minute = new javax.swing.JComboBox<>();
        ampm = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Edit Alarm");

        editbutton.setText("Edit");
        editbutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editbuttonActionPerformed(evt);
            }
        });

        close_button.setText("Close");
        close_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                close_buttonActionPerformed(evt);
            }
        });

        delete_button.setText("Delete");
        delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_buttonActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "subject", "hour", "minute", "am/pm", "date", "month", "year"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, false, false, true
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

        search_field.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                search_fieldKeyReleased(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Enter Details to search :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Edit and Delete Alarm :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("hour :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("minute :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("AM/PM :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Date :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("subject :");

        hour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        minute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        ampm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(search_field, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(14, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(13, 13, 13))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(minute, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ampm, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(hour, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(close_button, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(editbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(174, 174, 174))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(search_field, javax.swing.GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(12, 12, 12)
                        .addComponent(delete_button, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(hour))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(minute)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                            .addComponent(ampm))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(subject, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(close_button, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(editbutton, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(87, 87, 87))))
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

    private void close_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_close_buttonActionPerformed
        this.dispose();
    }//GEN-LAST:event_close_buttonActionPerformed

    private void search_fieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_search_fieldKeyReleased
        jTable1.setModel(new DefaultTableModel(null, new Object[]{"id","subject","hour","minute","am/pm","date","month","year"}));
        a.showtable(jTable1, search_field.getText());
    }//GEN-LAST:event_search_fieldKeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int rowindex=jTable1.getSelectedRow();
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        hour.setSelectedIndex((Integer.parseInt(model.getValueAt(rowindex, 2).toString()))-1);
        minute.setSelectedIndex((Integer.parseInt(model.getValueAt(rowindex, 3).toString())));
        ampm.setSelectedItem(model.getValueAt(rowindex, 4).toString());
        try
        {
            java.util.Date d=new SimpleDateFormat("dd/MM/yyyy").parse(model.getValueAt(rowindex, 5).toString()+"/"+(Integer.parseInt(model.getValueAt(rowindex, 6).toString()))+"/"+model.getValueAt(rowindex, 7).toString());
            jDateChooser1.setDate(d);
        }
        catch(ParseException ex)
        {
            Logger.getLogger(EditAlarm.class.getName()).log(Level.SEVERE, null, ex);
        }
        subject.setText(model.getValueAt(rowindex, 1).toString());
    }//GEN-LAST:event_jTable1MouseClicked

    private void editbuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editbuttonActionPerformed
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            String path=new File(".").getCanonicalPath();
            boolean same_alarm_found=false;
        Integer hou = (this.hour.getSelectedIndex()+1);
        Integer min=(this.minute.getSelectedIndex());
        String ampm=this.ampm.getSelectedItem().toString();
        Calendar userdate=jDateChooser1.getCalendar();
        userdate.set(Calendar.HOUR, hou);userdate.set(Calendar.MINUTE, min);
        if(ampm.equals("AM"))
        {
            userdate.set(Calendar.AM_PM, Calendar.AM);
        }
        else if(ampm.equals("PM"))
        {
            userdate.set(Calendar.AM_PM, Calendar.PM);
        }
        String sub=subject.getText();
        
        Class.forName("org.sqlite.JDBC");
                con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
                ps=con.prepareStatement("SELECT * FROM `alarms`");
                rs=ps.executeQuery();
                while(rs.next())
                {
                    Integer date=Integer.parseInt(rs.getString("date"));
                    Integer month=Integer.parseInt(rs.getString("month"));
                    Integer year=Integer.parseInt(rs.getString("year"));
                    Integer hour=Integer.parseInt(rs.getString("hour"));
                    Integer minute=Integer.parseInt(rs.getString("minute"));
                    String subject=rs.getString("subject");
                    String meridian=rs.getString("am_pm");
                    
                    if(date==userdate.get(Calendar.DATE) && month==(userdate.get(Calendar.MONTH)+1) && year==userdate.get(Calendar.YEAR) && hou==hour && min==minute && meridian.equals(ampm) && sub.equals(subject))
                    {
                        same_alarm_found=true;
                        break;
                    }
                }
        con.close();
        ps.close();
        rs.close();
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        int rowindex=jTable1.getSelectedRow();
        Integer Id=Integer.parseInt(model.getValueAt(rowindex, 0).toString());
        Calendar systemdate=Calendar.getInstance();
            if(hou.toString().equals("") || min.toString().equals("") || ampm.equals("") || sub.equals("") )
            {
                JOptionPane.showMessageDialog(null, "One or more fields are empty or Choose a valid date!");
            }
            else if(userdate.compareTo(systemdate)<0)//(userdate.before(systemdate))//
            {
                JOptionPane.showMessageDialog(null, "Choose a valid date and Time!");
            }
            else
            {
                    Class.forName("org.sqlite.JDBC");
                    con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
                    ps=con.prepareStatement("UPDATE `alarms` SET `date`=?,`month`=?,`year`=?,`hour`=?,`minute`=?,`subject`=?,`am_pm`=? WHERE `id`=?");
                    ps.setInt(1, userdate.get(Calendar.DATE));
                    ps.setInt(2, (userdate.get(Calendar.MONTH)+1));
                    ps.setInt(3, userdate.get(Calendar.YEAR));
                    ps.setInt(4, hou);
                    ps.setInt(5, min);
                    ps.setString(6, sub);
                    ps.setString(7, ampm);
                    ps.setInt(8, Id);
                    
                    if(ps.executeUpdate()>0)
                    {
                        a.showtable(jTable1, "");
                        jTable1.setModel(new DefaultTableModel(null, new Object[]{"id","subject","hour","minute","am/pm","date","month","year"}));
                        a.showtable(jTable1, search_field.getText());
                        hour.setSelectedIndex(0);
                        minute.setSelectedIndex(0);
                        jDateChooser1.setDate(null);
                        subject.setText(null);
                        JOptionPane.showMessageDialog(this, "Alarm Edited Successfully");
                    }
                    con.close();ps.close();
            }
        }catch(ArrayIndexOutOfBoundsException e)
        {
            JOptionPane.showMessageDialog(this, "please select an alarm first");
        }
        catch(NullPointerException npe)
        {
            JOptionPane.showMessageDialog(this, "please select an alarm first or provide Valid details");
        }
        catch(IOException ioe)
        {
            JOptionPane.showMessageDialog(this, "Can't find path of dependency files", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        catch(ClassNotFoundException cnfe)
        {
            JOptionPane.showMessageDialog(this, "can't find driver", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(this, "Problem to connect with Database\nPlease try again");
        }
        finally
        {
            try{
            con.close();
            ps.close();
            rs.close();}catch(Exception e){}
        }
    }//GEN-LAST:event_editbuttonActionPerformed

    private void delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_buttonActionPerformed
        int rowindex=jTable1.getSelectedRow();
        int rowcount=jTable1.getSelectedRowCount();
        if(rowindex==-1 || rowcount==0)
        {
            JOptionPane.showMessageDialog(null, "No alarms selected!\nPlease select an alarm first");
        }
        else
        {
            int input = JOptionPane.showConfirmDialog(this, 
            "Do you really want to delete?", "Please Confirm!",JOptionPane.YES_NO_OPTION);
            if(input==0)
            {
            Connection con=null;
            PreparedStatement ps=null;
            DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
            Integer Id = Integer.parseInt(model.getValueAt(rowindex, 0).toString());
            try
            {
                String path=new File(".").getCanonicalPath();
                Class.forName("org.sqlite.JDBC");
                con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
                ps=con.prepareStatement("DELETE FROM `alarms` WHERE `id` = ?");
                ps.setInt(1, Id);
                if(ps.executeUpdate()>0)
                {
                    a.showtable(jTable1, "");
                    jTable1.setModel(new DefaultTableModel(null, new Object[]{"id","subject","hour","minute","am/pm","date","month","year"}));
                    a.showtable(jTable1, search_field.getText());
                    hour.setSelectedIndex(0);
                    minute.setSelectedIndex(0);
                    jDateChooser1.setCalendar(null);
                    subject.setText(null);
                    JOptionPane.showMessageDialog(null, "Alarm Deleted Successfully");
                }
            }
            catch(IOException ioe)
            {
                JOptionPane.showMessageDialog(this, ioe+"Can't find path of dependency files", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            catch(ClassNotFoundException cnfe)
            {
                JOptionPane.showMessageDialog(this, "can't find driver", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            catch(SQLException ex)
            {
                JOptionPane.showMessageDialog(this, "Problem to connect with Database\nPlease try again");
            }
            finally
            {
                try
                {
                    con.close();ps.close();
                }
                catch(Exception e)
                {
                    
                }
            }
            }
        }
    }//GEN-LAST:event_delete_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(EditAlarm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditAlarm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditAlarm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditAlarm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EditAlarm().setVisible(true);
            }
        });*/
        EditAlarm ea=new EditAlarm();
        ea.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ampm;
    private javax.swing.JButton close_button;
    private javax.swing.JButton delete_button;
    private javax.swing.JButton editbutton;
    private javax.swing.JComboBox<String> hour;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> minute;
    private javax.swing.JTextField search_field;
    private javax.swing.JTextField subject;
    // End of variables declaration//GEN-END:variables
}
