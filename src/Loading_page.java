
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.*;

/**
 *
 * @author soumajit
 */
public class Loading_page extends javax.swing.JFrame {
    
    static boolean flag=false;
    public Loading_page() {
        initComponents();
        flag=false;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jarvis");

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/VA.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(Loading_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Loading_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Loading_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Loading_page.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        try{
            String path=new File(".").getCanonicalPath();
            final Loading_page ob=new Loading_page();
            ob.setLocationRelativeTo(null);
            ob.setVisible(true);
            JLabel jLabel2=new JLabel("Loading...");

            Font font = new Font("Georgia", Font.PLAIN, 20);
            jLabel2.setFont(font);
            jLabel2.setSize(100, 60);
            jLabel2.setLocation(260, 400);
            jLabel1.add(jLabel2);
            Alarm_and_Reminder_checker checker=new Alarm_and_Reminder_checker();
            checker.setVisible(false);

            Thread th=new Thread() {     //thread to increase progress bar
            public void run(){
            for(int i=1;i<=10;i++){
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Loading_page.class.getName()).log(Level.SEVERE, null, ex);
                        }
                if(i==1){ob.jProgressBar1.setValue(10); jLabel2.setText("Loading.");}
                if(i==2){ob.jProgressBar1.setValue(20); jLabel2.setText("Loading..");}
                if(i==3){ob.jProgressBar1.setValue(30); jLabel2.setText("Loading...");}
                if(i==4){ob.jProgressBar1.setValue(40); jLabel2.setText("Loading.");}
                if(i==5){ob.jProgressBar1.setValue(50); jLabel2.setText("Loading..");}
                if(i==6){ob.jProgressBar1.setValue(60); jLabel2.setText("Loading...");}
                if(i==7){ob.jProgressBar1.setValue(70); jLabel2.setText("Loading.");}
                if(i==8){ob.jProgressBar1.setValue(80); jLabel2.setText("Loading..");}
                if(i==9){ob.jProgressBar1.setValue(90); jLabel2.setText("Loading...");}
                if(i==10){ob.jProgressBar1.setValue(100);jLabel2.setText("Loading...");}
        }}
        };

            Thread th2=new Thread(){     //thread to check if the software runs first time on this system
                public void run()
                {
                    Connection con=null;
                    PreparedStatement ps=null;
                    ResultSet rs=null;
                    try
                    {
                        String path=new File(".").getCanonicalPath();
                        Class.forName("org.sqlite.JDBC");
                        con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
                        ps=con.prepareStatement("SELECT * FROM `userinfo`");
                        rs=ps.executeQuery();
                        while(rs.next())
                        {
                            flag=true;
                            //JOptionPane.showMessageDialog(null, rs.getString("password"), rs.getString("username"), JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    catch(IOException ioe)
                    {
                        JOptionPane.showMessageDialog(null, ioe+"Can't find path of dependency files", "Oops!", JOptionPane.ERROR_MESSAGE);
                    }
                    catch(ClassNotFoundException cnfe)
                    {
                        JOptionPane.showMessageDialog(null, "can't find driver", "Oops!", JOptionPane.ERROR_MESSAGE);
                    }
                    catch(SQLException ex)
                    {
                        JOptionPane.showMessageDialog(null, "Problem to connect with Database\nPlease try again");
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
            };

            th.start();th2.start();
            th.join();th2.join();
            if(!th2.isAlive() && !th.isAlive())
            {
                if(flag)        //if the software runs first time on this system then it opens the form that takes user's info
                {
                    ob.dispose();
                    LoginForm ob2=new LoginForm();
                    ob2.setResizable(false);
                    ob2.setLocationRelativeTo(null);
                    ob2.setVisible(true);
                }
                else          //if the software runs more than one time on this system then it opens the main menu of the s/w
                {
                    User_details_form ob2=new User_details_form();
                    ob2.setVisible(true);
                    ob2.setLocationRelativeTo(null);
                    ob2.setResizable(false);
                    ob.dispose();
                }
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex);
            System.exit(0);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
