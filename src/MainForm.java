
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import BBGame.gameplay;
import java.awt.Color;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 *
 * @author soumajit
 */
public class MainForm extends javax.swing.JFrame {
    public static Map<String, Object> jsonToMap(String str)
    {
        Map<String, Object> map=new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>(){}.getType());
        return map;
    }
    public MainForm() {
        initComponents();
        try
        {
            jarvis.setText("Jarvis");
            jarvis.setForeground(Color.WHITE);
            jLabel2.setText("Your virtual assistant");
            jLabel2.setForeground(Color.WHITE);
            
            Thread th=new Thread(){
                public void run()
                {
                    try{
                        while(true)
                        {
                        //for weather update
                        String API_KEY="bd52b2d10aa50c3079ae62fbc880d05b";
                        String LOCATION="Kolkata,IN";
                        String urlString="http://api.openweathermap.org/data/2.5/weather?q="+LOCATION+"&APPID="+API_KEY;//+"&units=imperial";

                        StringBuilder result=new StringBuilder();
                        URL url=new URL(urlString);
                        URLConnection conn=url.openConnection();
                        BufferedReader rd=new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String line;
                        while((line=rd.readLine())!=null)
                        {
                            result.append(line);
                        }
                        rd.close();
                        //System.out.println(result);
                        Map<String, Object> respMap=jsonToMap(result.toString());
                        Map<String, Object> mainMap=jsonToMap(respMap.get("main").toString());
                        Map<String, Object> windMap=jsonToMap(respMap.get("wind").toString());

                        //to set temparature
                        Double temparature=Double.parseDouble(mainMap.get("temp").toString());
                        Double t=temparature-273.15;
                        temperature.setText(/*"Temparature : "*/t.toString()+" °C");
                        temperature.setForeground(Color.WHITE);
                        //end of temparature

                        //to set humidity
                        Double h=Double.parseDouble(mainMap.get("humidity").toString());
                        humidity.setText("Humidity : "+h+" %");humidity.setForeground(Color.WHITE);
                        windspeed.setVisible(false);
                        //end of humidity
                        Thread.currentThread().sleep(120000);
                        }
                    }
                    catch(UnknownHostException uhe)
                    {
                        //JOptionPane.showMessageDialog(null, "Internet connection required!", "Oops!", JOptionPane.ERROR_MESSAGE);
                    }
                    catch(Exception e)
                    {
                        
                    }
                }
            };
            th.start();
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        temperature = new javax.swing.JLabel();
        humidity = new javax.swing.JLabel();
        windspeed = new javax.swing.JLabel();
        jarvis = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jarvis");
        setPreferredSize(new java.awt.Dimension(450, 600));
        setSize(new java.awt.Dimension(450, 600));

        jPanel1.setLayout(null);

        temperature.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jPanel1.add(temperature);
        temperature.setBounds(290, 20, 160, 30);

        humidity.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(humidity);
        humidity.setBounds(300, 60, 150, 30);

        windspeed.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jPanel1.add(windspeed);
        windspeed.setBounds(30, 200, 140, 30);

        jarvis.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jPanel1.add(jarvis);
        jarvis.setBounds(20, 20, 130, 40);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jPanel1.add(jLabel2);
        jLabel2.setBounds(20, 60, 160, 30);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1.jpg"))); // NOI18N
        jLabel1.setText("Jarvis");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, 0, 450, 250);

        jLabel5.setFont(new java.awt.Font("Sylfaen", 1, 18)); // NOI18N
        jLabel5.setText("Hi! May I help you?");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(140, 320, 170, 60);

        jButton1.setFont(new java.awt.Font("Sylfaen", 1, 14)); // NOI18N
        jButton1.setText("Let's Chat");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(140, 380, 170, 50);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Meetings");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2);
        jButton2.setBounds(180, 470, 90, 30);

        jMenu1.setText("File");

        jMenu3.setText("New");

        jMenuItem9.setText("Chat");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem9);

        jMenu1.add(jMenu3);

        jMenuItem1.setText("Exit");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");

        jMenuItem2.setText("Edit user data");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem12.setText("Forget password");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem12);

        jMenuBar1.add(jMenu2);

        jMenu4.setText("Tools");

        jMenuItem11.setText("Manage Database");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem11);

        jMenu5.setText("Alarm");

        jMenuItem3.setText("Set Alarm");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem3);

        jMenuItem4.setText("Manage Alarms");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem4);

        jMenu4.add(jMenu5);

        jMenu6.setText("Reminder");

        jMenuItem5.setText("Set Reminder");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem5);

        jMenuItem6.setText("Edit Reminder");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItem6);

        jMenu4.add(jMenu6);

        jMenu7.setText("Notes");

        jMenuItem7.setText("Set Note");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem7);

        jMenuItem8.setText("Manage Notes");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem8);

        jMenu4.add(jMenu7);

        jMenuBar1.add(jMenu4);

        jMenu8.setText("Game");

        jMenuItem10.setText("Brick&Ball");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu8.add(jMenuItem10);

        jMenuBar1.add(jMenu8);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 448, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 599, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        Chatbot_UI ob=new Chatbot_UI();
        ob.setResizable(false);
        ob.setLocationRelativeTo(null);
        ob.setVisible(true);
        ob.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //this.dispose();
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        String pass=JOptionPane.showInputDialog(this, "Please Enter Your Password :");
        Connection con=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try{
            String password="";
            String path=new File(".").getCanonicalPath();
            Class.forName("org.sqlite.JDBC");
            con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
            ps=con.prepareStatement("SELECT * FROM `userinfo`");
            rs=ps.executeQuery();
            while(rs.next())
            {
                password=rs.getString("password");
                break;
            }
            if(pass.equals(password))
            {
                EditUserDataForm ob=new EditUserDataForm();
                ob.setResizable(false);
                ob.setLocationRelativeTo(null);
                ob.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Wrong Password! Please try again", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        set_alarm ob=new set_alarm();
        ob.setResizable(false);
        ob.setLocationRelativeTo(null);
        ob.setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        EditAlarm ob=new EditAlarm();
        ob.setResizable(false);
        ob.setLocationRelativeTo(null);
        ob.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        set_reminder ob=new set_reminder();
        ob.setResizable(false);
        ob.setLocationRelativeTo(null);
        ob.setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        EditReminder ob=new EditReminder();
        ob.setResizable(false);
        ob.setLocationRelativeTo(null);
        ob.setVisible(true);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        set_notes ob=new set_notes();
        ob.setResizable(false);
        ob.setLocationRelativeTo(null);
        ob.setVisible(true);
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        EditNotes ob=new EditNotes();
        ob.setResizable(false);
        ob.setLocationRelativeTo(null);
        ob.setVisible(true);
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        javax.swing.JFrame obj=new javax.swing.JFrame();
        gameplay gameplay=new gameplay();
        obj.setBounds(10,10,710,600);
        obj.setTitle("Brick and Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
        obj.add(gameplay);
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Chatbot_UI ob=new Chatbot_UI();
        ob.setResizable(false);
        ob.setLocationRelativeTo(null);
        ob.setVisible(true);
        ob.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        ManageMeetings ob=new ManageMeetings();
        ob.setResizable(false);
        ob.setLocationRelativeTo(null);
        ob.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        String pass=JOptionPane.showInputDialog(this, "Please Enter Your Password :");
        try{
            String path=new File(".").getCanonicalPath();
            BufferedReader readfile=new BufferedReader(new FileReader(path +"\\user_info.txt"));
            String check=readfile.readLine();
            String s[]=check.split(",");
            String password = s[5];
            if(pass.equals(password))
            {
                DatabaseTableManager ob=new DatabaseTableManager();
                ob.setResizable(false);
                ob.setLocationRelativeTo(null);
                ob.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Wrong Password! Please try again", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(this, e+"\nProblem to access some system files!", "Oops!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        ForgetPasswordForm ob=new ForgetPasswordForm();
        ob.setResizable(false);
        ob.setLocationRelativeTo(null);
        ob.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        /*java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });*/
        MainForm ob=new MainForm();
        ob.setVisible(true);
        ob.setResizable(false);
        ob.setLocationRelativeTo(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel humidity;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jarvis;
    private javax.swing.JLabel temperature;
    private javax.swing.JLabel windspeed;
    // End of variables declaration//GEN-END:variables
}
