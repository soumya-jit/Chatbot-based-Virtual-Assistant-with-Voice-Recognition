
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author soumajit
 */
class meetings
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
            con=DriverManager.getConnection("jdbc:sqlite:"+ /*this.getClass().getResource("/").getPath()*/path +"\\chatbot.db");
            ps=con.prepareStatement("SELECT * FROM `meetings` WHERE `id`||`subject`||`location`||`category`||`agenda`||`participants`||`date`||`time` LIKE ?");
            ps.setString(1, "%"+valuetoserach+"%");
            rs=ps.executeQuery();
            DefaultTableModel model=(DefaultTableModel)table.getModel();
            Object row[];
            while(rs.next())
            {
                row=new Object[9];
                row[0]=rs.getString(1);
                row[1]=rs.getString(2);
                row[2]=rs.getString(3);
                row[3]=rs.getString(4);
                row[4]=rs.getString(5);
                row[5]=rs.getString(6);
                row[6]=rs.getString(7);
                row[7]=rs.getString(8);
                //row[8]=rs.getString(9);
                model.addRow(row);
            }
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(null, "Can't find database path", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        catch(ClassNotFoundException cnfe)
        {
            JOptionPane.showMessageDialog(null, "Can't find database driver", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException sqle)
        {
            JOptionPane.showMessageDialog(null, "Problem to communicate with database", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            try
            {
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
public class ManageMeetings extends javax.swing.JFrame {
    meetings m=new meetings();
    public ManageMeetings() {
        initComponents();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        m.showtable(jTable1, "");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        subject = new javax.swing.JTextField();
        location = new javax.swing.JTextField();
        category = new javax.swing.JTextField();
        agenda = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        hour = new javax.swing.JComboBox<>();
        minute = new javax.swing.JComboBox<>();
        ampm = new javax.swing.JComboBox<>();
        SaveButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel8 = new javax.swing.JLabel();
        participants = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        SearchField = new javax.swing.JTextField();
        DeleteButton = new javax.swing.JButton();
        EditButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Manage Meetings");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Set Your Meetings Here :");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Subject :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Location :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Category :");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Agenda :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Date :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Time :");

        hour.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));

        minute.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48", "49", "50", "51", "52", "53", "54", "55", "56", "57", "58", "59" }));

        ampm.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));

        SaveButton.setText("Save");
        SaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SaveButtonActionPerformed(evt);
            }
        });

        CancelButton.setText("Cancel");

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Participants :");

        jLabel9.setText("*Optional");

        jLabel10.setText("*Optional");

        jLabel11.setText("*Optional");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Subject", "Location", "Category", "Agenda", "Participants", "Date", "Time"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel12.setText("Search :");

        SearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchFieldKeyReleased(evt);
            }
        });

        DeleteButton.setText("Delete");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        EditButton.setText("Edit");
        EditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(22, 22, 22))
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(hour, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(minute, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(ampm, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(subject)
                                            .addComponent(location)
                                            .addComponent(category)
                                            .addComponent(agenda)
                                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel11))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(4, 4, 4)
                                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(participants, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(139, 139, 139)
                                .addComponent(jLabel1))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(111, 111, 111)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(188, 188, 188)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel2))
                                .addGap(18, 18, 18)
                                .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(20, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(EditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(198, 198, 198))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(subject, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(location, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(category, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(agenda, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(participants, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hour, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                            .addComponent(minute, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ampm, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SaveButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EditButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
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

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        Connection con=null;
        PreparedStatement ps=null;
        try{
        String path=new File(".").getCanonicalPath();
        int rowindex=jTable1.getSelectedRow();
        int rowcount=jTable1.getSelectedRowCount();
            //System.out.println("row index: "+rowindex);
            //System.out.println("row count: "+rowcount);
        if(rowindex==-1 || rowcount==0)
        {
            JOptionPane.showMessageDialog(null, "No meetings selected!\nPlease select a meeting first");
        }
        else
        {
            int input = JOptionPane.showConfirmDialog(this,
                "Do you really want to delete?", "Please Confirm!",JOptionPane.YES_NO_OPTION);
            if(input==0)
            {
                if(rowcount>1)
                {
                    DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
                    //int rownum=jTable1.getSelectedRow();
                    int selectedrows[]=jTable1.getSelectedRows();
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
                        con=DriverManager.getConnection("jdbc:sqlite:"+ /*this.getClass().getResource("/").getPath()*/path +"\\chatbot.db");
                        ps=con.prepareStatement("DELETE FROM `meetings` WHERE `id` = ?");
                        ps.setInt(1, Id);
                        if(ps.executeUpdate()>0)
                        {
                            
                        }
                        rowcount-=1;
                        a+=1;
                    }
                    m.showtable(jTable1, "");
                    jTable1.setModel(new DefaultTableModel(null, new Object[]{"id","subject","location","category","agenda","participants","date","time"}));
                    m.showtable(jTable1, SearchField.getText());
                    hour.setSelectedIndex(0);
                    minute.setSelectedIndex(0);
                    ampm.setSelectedIndex(0);
                    jDateChooser1.setCalendar(null);
                    //name.setText("");
                    this.subject.setText("");
                    this.location.setText("");
                    this.category.setText("");
                    this.agenda.setText("");
                    this.participants.setText("");
                    JOptionPane.showMessageDialog(null, "Meetings Deleted Successfully");
                }
                else if(rowcount==1)
                {
                    
                    DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
                    Integer Id = Integer.parseInt(model.getValueAt(rowindex, 0).toString());
                    Class.forName("org.sqlite.JDBC");
                    con=DriverManager.getConnection("jdbc:sqlite:"+ /*this.getClass().getResource("/").getPath()*/path +"\\chatbot.db");
                    ps=con.prepareStatement("DELETE FROM `meetings` WHERE `id` = ?");
                    ps.setInt(1, Id);
                    if(ps.executeUpdate()>0)
                    {
                        m.showtable(jTable1, "");
                        jTable1.setModel(new DefaultTableModel(null, new Object[]{"id","subject","location","category","agenda","participants","date","time"}));
                        m.showtable(jTable1, SearchField.getText());
                        hour.setSelectedIndex(0);
                        minute.setSelectedIndex(0);
                        ampm.setSelectedIndex(0);
                        jDateChooser1.setCalendar(null);
                        //name.setText("");
                        this.subject.setText("");
                        this.location.setText("");
                        this.category.setText("");
                        this.agenda.setText("");
                        this.participants.setText("");
                        JOptionPane.showMessageDialog(null, "Meeting Deleted Successfully");
                    }
                }
            }
        }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        finally
        {
            try{
                con.close();
                ps.close();
                }
            catch(Exception ex){}
        }
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int rowindex=jTable1.getSelectedRow();
        DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
        this.subject.setText(model.getValueAt(rowindex, 1).toString());
        this.location.setText(model.getValueAt(rowindex, 2).toString());
        this.category.setText(model.getValueAt(rowindex, 3).toString());
        this.agenda.setText(model.getValueAt(rowindex, 4).toString());
        this.participants.setText(model.getValueAt(rowindex, 5).toString());
        try
        {
            java.util.Date d=new SimpleDateFormat("dd/MM/yyyy").parse(model.getValueAt(rowindex, 6).toString());
            jDateChooser1.setDate(d);
            
            String s=model.getValueAt(rowindex, 7).toString();
            String s2[]=s.split(":");
            String s3[]=s2[1].split(" ");
            String h=s2[0];
            
            hour.setSelectedItem(s2[0]);
            minute.setSelectedItem(s3[0]);
            ampm.setSelectedItem(s3[1]);
        }
        catch(ParseException ex)
        {
            
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void SaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SaveButtonActionPerformed
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
                String path=new File(".").getCanonicalPath();
                boolean same_meeting_found=false;
                String subject=this.subject.getText();
                String location=this.location.getText();
                String category=this.category.getText();
                String agenda=this.agenda.getText();
                String participants=this.participants.getText();
                
                int hour=(this.hour.getSelectedIndex())+1;
                int minute=(this.minute.getSelectedIndex());
                String ampm=this.ampm.getSelectedItem().toString();
                int am_pm=0;
                Calendar userdate=jDateChooser1.getCalendar();
                userdate.set(Calendar.HOUR, hour);userdate.set(Calendar.MINUTE, minute);
                if(ampm.equals("AM"))
                {
                    am_pm=0;
                }
                else if(ampm.equals("PM"))
                {
                    am_pm=1;
                }
                userdate.set(Calendar.AM_PM, am_pm);
                //check same meeting exists or not
                Class.forName("org.sqlite.JDBC");
                con=DriverManager.getConnection("jdbc:sqlite:"+ /*this.getClass().getResource("/").getPath()*/path +"\\chatbot.db");
                ps=con.prepareStatement("SELECT * FROM `meetings`");
                rs=ps.executeQuery();
                while(rs.next())
                {
                    String sub=rs.getString("subject");
                    String loc=rs.getString("location");
                    String cat=rs.getString("category");
                    String agen=rs.getString("agenda");
                    String part=rs.getString("participants");
                    
                    String d=rs.getString("date");
                    String d2[]=d.split("/");
                    Integer date=Integer.parseInt(d2[0]), month=Integer.parseInt(d2[1]), year=Integer.parseInt(d2[2]);
                    
                    String t=rs.getString("time");
                    String t2[]=t.split(":");
                    String t3[]=t2[1].split(" ");
                    Integer h=Integer.parseInt(t2[0]);
                    Integer m=Integer.parseInt(t3[0]);
                    String meridian=t3[1];
                    if(sub.equals(subject) && loc.equals(location) && cat.equals(category) && agen.equals(agenda) && part.equals(participants) && hour==h && minute==m && meridian.equals(ampm) && userdate.get(Calendar.DATE)==date && (userdate.get(Calendar.MONTH)+1)==month && userdate.get(Calendar.YEAR)==year)
                    {
                        same_meeting_found=true;
                        break;
                    }
                }
                //end of same meeting checking
                Calendar systemdate=Calendar.getInstance();
                if(!same_meeting_found)
                {
                    if(userdate.compareTo(systemdate)>0)
                    {
                        //Class.forName("org.sqlite.JDBC");
                        //con=DriverManager.getConnection("jdbc:sqlite:"+ /*this.getClass().getResource("/").getPath()*/path +"\\chatbot.db");
                        String sql ="INSERT INTO meetings (id, subject, location, category, agenda, participants, date, time, flag) values (?,?,?,?,?,?,?,?,?)";
                        ps = con.prepareStatement(sql);
                        ps.setString(1, null);
                        ps.setString(2, subject);
                        ps.setString(3, location);
                        ps.setString(4, category);
                        ps.setString(5, agenda);
                        ps.setString(6, participants);
                        ps.setString(7, userdate.get(Calendar.DATE)+"/"+(userdate.get(Calendar.MONTH)+1)+"/"+userdate.get(Calendar.YEAR));
                        ps.setString(8, hour+":"+minute+" "+ampm);
                        ps.setString(9, "notdone");
                        int check=ps.executeUpdate();
                        if(check==1)
                        {
                            this.subject.setText("");
                            this.location.setText("");
                            this.category.setText("");
                            this.agenda.setText("");
                            this.participants.setText("");
                            jDateChooser1.setDate(null);
                            this.hour.setSelectedIndex(0);
                            this.minute.setSelectedIndex(0);
                            this.ampm.setSelectedIndex(0);
                            JOptionPane.showMessageDialog(this, "Meeting set successfully!");
                            m.showtable(jTable1, "");
                            jTable1.setModel(new DefaultTableModel(null, new Object[]{"id","subject","location","category","agenda","participants","date","time"}));
                            m.showtable(jTable1, SearchField.getText());
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Please choose a valid date and time");
                    }
                }
                else if(same_meeting_found)
                {
                    JOptionPane.showMessageDialog(this, "Same Meeting Already saved", "Oops!", JOptionPane.WARNING_MESSAGE);
                }
            }
        catch(ClassNotFoundException cnfe)
        {
            JOptionPane.showMessageDialog(this, "Driver is Missing or Problem to fetching Driver!", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
            JOptionPane.showMessageDialog(this, sqle+"\nCan't find database or Problem to communicate with Database!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe)
        {
            JOptionPane.showMessageDialog(this, "Database path not found!", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        catch(NullPointerException npe)
        {
            JOptionPane.showMessageDialog(this, "Please provide Meeting Details first!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        finally
        {
            try{
                con.close();
                ps.close();
                rs.close();
                }
            catch(Exception ex)
            {
                System.out.println("prblm here");
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_SaveButtonActionPerformed

    private void EditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditButtonActionPerformed
        Connection con=null;
        ResultSet rs=null;
        PreparedStatement ps=null;
        try
        {
            String path=new File(".").getCanonicalPath();
            DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
            Integer id=Integer.parseInt(model.getValueAt(jTable1.getSelectedRow(), 0).toString());
            boolean same_meeting_found=false;
            String subject=this.subject.getText();
            String location=this.location.getText();
            String category=this.category.getText();
            String agenda=this.agenda.getText();
            String participants=this.participants.getText();
                
            int hour=(this.hour.getSelectedIndex())+1;
            int minute=(this.minute.getSelectedIndex());
            int ampm=(this.ampm.getSelectedIndex());
            String am_pm=null;
                
            Calendar userdate=jDateChooser1.getCalendar();
            userdate.set(Calendar.HOUR, hour);userdate.set(Calendar.MINUTE, minute);userdate.set(Calendar.AM_PM, ampm);
            if(userdate.get(Calendar.AM_PM)==Calendar.AM)
            {
                am_pm="AM";
            }
            else if(userdate.get(Calendar.AM_PM)==Calendar.PM)
            {
                am_pm="PM";
            }
                
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:"+ /*this.getClass().getResource("/").getPath()*/path +"\\chatbot.db");
            ps=con.prepareStatement("SELECT * FROM `meetings`");
            rs=ps.executeQuery();
            while(rs.next())
            {
                String sub=rs.getString("subject");
                String loc=rs.getString("location");
                String cat=rs.getString("category");
                String agen=rs.getString("agenda");
                String part=rs.getString("participants");
                
                String d=rs.getString("date");
                String d2[]=d.split("/");
                Integer date=Integer.parseInt(d2[0]), month=Integer.parseInt(d2[1]), year=Integer.parseInt(d2[2]);
                
                String t=rs.getString("time");
                String t2[]=t.split(":");
                String t3[]=t2[1].split(" ");
                Integer h=Integer.parseInt(t2[0]);
                Integer m=Integer.parseInt(t3[0]);
                String meridian=t3[1];
                
                if(sub.equals(subject) && loc.equals(location) && cat.equals(category) && agen.equals(agenda) && part.equals(participants) && hour==h && minute==m && meridian.equals(am_pm) && userdate.get(Calendar.DATE)==date && (userdate.get(Calendar.MONTH)+1)==month && userdate.get(Calendar.YEAR)==year)
                {
                    same_meeting_found=true;
                    break;
                }
            }
                
            Calendar systemdate=Calendar.getInstance();
            if(!same_meeting_found)
            {
                if(userdate.compareTo(systemdate)>0)
                {
                    Class.forName("org.sqlite.JDBC");
                    con=DriverManager.getConnection("jdbc:sqlite:"+ /*this.getClass().getResource("/").getPath()*/path+"\\chatbot.db");
                    ps=con.prepareStatement("UPDATE `meetings` SET `subject`=?,`location`=?,`category`=?,`agenda`=?,`participants`=?,`date`=?,`time`=? WHERE `id`=?");
                    ps.setString(1, subject);
                    ps.setString(2, location);
                    ps.setString(3, category);
                    ps.setString(4, agenda);
                    ps.setString(5, participants);
                    ps.setString(6, userdate.get(Calendar.DATE)+"/"+(userdate.get(Calendar.MONTH)+1)+"/"+userdate.get(Calendar.YEAR));
                    ps.setString(7, hour+":"+minute+" "+am_pm);
                    ps.setInt(8, id);
                    if(ps.executeUpdate()>0)
                    {
                        m.showtable(jTable1, "");
                        jTable1.setModel(new DefaultTableModel(null, new Object[]{"id","subject","location","category","agenda","participants","date","time"}));
                        m.showtable(jTable1, SearchField.getText());
                        this.subject.setText("");
                        this.location.setText("");
                        this.category.setText("");
                        this.agenda.setText("");
                        this.participants.setText("");
                        jDateChooser1.setDate(null);
                        this.hour.setSelectedIndex(0);
                        this.minute.setSelectedIndex(0);
                        this.ampm.setSelectedIndex(0);
                        JOptionPane.showMessageDialog(this, "Meeting Edited Successfully");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(this, "Please choose a valid date and time");
                }
            }
            else if(same_meeting_found)
            {
                JOptionPane.showMessageDialog(this, "Same meeting already exists", "Oops!", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch(ArrayIndexOutOfBoundsException aiofb)
        {
            JOptionPane.showMessageDialog(this, "Please select a meeting first", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        catch(ClassNotFoundException cnfe)
        {
            JOptionPane.showMessageDialog(this, "Driver is Missing or Problem to fetching Driver!", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        catch(SQLException sqle)
        {
            System.out.println(sqle);
            JOptionPane.showMessageDialog(this, sqle+"\nCan't find database or Problem to communicate with Database!", "Error!", JOptionPane.ERROR_MESSAGE);
        }
        catch(IOException ioe)
        {
            JOptionPane.showMessageDialog(this, "Database path not found!", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
        catch(NullPointerException npe)
        {
            JOptionPane.showMessageDialog(this, "Please provide Meeting Details first!", "Error!", JOptionPane.ERROR_MESSAGE);
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
    }//GEN-LAST:event_EditButtonActionPerformed

    private void SearchFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchFieldKeyReleased
        jTable1.setModel(new DefaultTableModel(null, new Object[]{"id","subject","location","category","agenda","participants","date","time"}));
        m.showtable(jTable1, SearchField.getText());
    }//GEN-LAST:event_SearchFieldKeyReleased

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
            java.util.logging.Logger.getLogger(ManageMeetings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageMeetings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageMeetings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageMeetings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageMeetings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton EditButton;
    private javax.swing.JButton SaveButton;
    private javax.swing.JTextField SearchField;
    private javax.swing.JTextField agenda;
    private javax.swing.JComboBox<String> ampm;
    private javax.swing.JTextField category;
    private javax.swing.JComboBox<String> hour;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField location;
    private javax.swing.JComboBox<String> minute;
    private javax.swing.JTextField participants;
    private javax.swing.JTextField subject;
    // End of variables declaration//GEN-END:variables
}
