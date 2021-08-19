import java.awt.Toolkit;
import java.io.File;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
/**
 *
 * @author soumajit
 */

public class Alarm_and_Reminder_checker extends JFrame
{
    Alarm_and_Reminder_checker()
    {
        
        
        Thread alarmcheck=new Thread(){    //thread to check alarms
            public void run()
            {
                //synchronized(this)
                {
                while(true)
                {
                Connection con=null;PreparedStatement ps=null;ResultSet rs=null;
                try{
                    Class.forName("org.sqlite.JDBC");
                    String path=new File(".").getCanonicalPath();
                    con=DriverManager.getConnection("jdbc:sqlite:"+ /*this.getClass().getResource("/").getPath()*/path +"\\chatbot.db");
                    ps=con.prepareStatement("SELECT * FROM `alarms`");
                    rs=ps.executeQuery();
                    while(rs.next())
                    {
                        long id=rs.getLong("id");
                        int date=rs.getInt("date");
                        int month=rs.getInt("month");
                        int year=rs.getInt("year");
                        int hour=rs.getInt("hour");
                        int minute=rs.getInt("minute");
                        String am_pm=rs.getString("am_pm");
                        int second=00;
                        String sub=rs.getString("subject");
                        String flag=rs.getString("flag");
                        
                        Calendar currentdate=Calendar.getInstance();
                        Calendar userdate=Calendar.getInstance();
                        userdate.set(Calendar.HOUR, hour);userdate.set(Calendar.MINUTE, minute);userdate.set(Calendar.DATE, date);userdate.set(Calendar.MONTH, month);userdate.set(Calendar.YEAR, year);
                        if(am_pm.equals("AM"))
                            userdate.set(Calendar.AM_PM, Calendar.AM);
                        else if(am_pm.equals("PM"))
                            userdate.set(Calendar.AM_PM, Calendar.PM);
                        
                        if(date==currentdate.get(Calendar.DATE) && month==(currentdate.get(Calendar.MONTH)+1) && year==currentdate.get(Calendar.YEAR) && hour==currentdate.get(Calendar.HOUR) && minute==currentdate.get(Calendar.MINUTE) /*&& second==d.getSeconds()*/ && userdate.get(Calendar.AM_PM)==currentdate.get(Calendar.AM_PM) && flag.equals("notdone"))
                        {
                            for(int i=0;i<5;i++)
                                {
                                    try{
                                        Thread.sleep(1000);
                                        Toolkit.getDefaultToolkit().beep();
                                        }
                                    catch(Exception e){}
                                }
                            JOptionPane.showMessageDialog(null, "You have an Alarm for : \n"+sub);
                            ps=con.prepareStatement("UPDATE `alarms` SET `flag`=? WHERE `id`=?");
                            ps.setString(1, "done");
                            ps.setLong(2, id);
                            ps.executeUpdate();
                        }
                        else if(flag.equals("done"))
                        {
                            ps=con.prepareStatement("DELETE FROM `alarms` WHERE `id` = ?");
                            ps.setLong(1, id);
                            if(ps.executeUpdate()>0)
                            {
                                //JOptionPane.showMessageDialog(null, "alarm deleted successfully");
                            }
                        }
                    }
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex);
                    System.exit(0);
                }
                finally
                {
                    try{con.close();ps.close();rs.close();}catch(Exception ex){}
                }
                }
                }
            }
        };
        Thread remindercheck=new Thread(){       //thread to check reminder
            public void run()
            {
                //synchronized(this)
                {
                while(true)
                {
                Connection con=null;PreparedStatement ps=null;ResultSet rs=null;
                try{
                    Class.forName("org.sqlite.JDBC");
                    String path=new File(".").getCanonicalPath();
                    con=DriverManager.getConnection("jdbc:sqlite:"+ /*this.getClass().getResource("/").getPath()*/path +"\\chatbot.db");
                    ps=con.prepareStatement("SELECT * FROM `reminders`");
                    rs=ps.executeQuery();
                    while(rs.next())
                    {
                        long id=rs.getLong("id");
                        int date=rs.getInt("date");
                        int month=rs.getInt("month");
                        int year=rs.getInt("year");
                        int hour=rs.getInt("hour");
                        int minute=rs.getInt("minute");
                        String am_pm=rs.getString("am_pm");
                        String sub=rs.getString("subject");
                        String name=rs.getString("name");
                        String flag=rs.getString("flag");
                        
                        Calendar currentdate=Calendar.getInstance();
                        Calendar userdate=Calendar.getInstance();
                        userdate.set(Calendar.HOUR, hour);userdate.set(Calendar.MINUTE, minute);userdate.set(Calendar.DATE, date);userdate.set(Calendar.MONTH, month);userdate.set(Calendar.YEAR, year);
                        if(am_pm.equals("AM"))
                            userdate.set(Calendar.AM_PM, Calendar.AM);
                        else if(am_pm.equals("PM"))
                            userdate.set(Calendar.AM_PM, Calendar.PM);
                        if(date==currentdate.get(Calendar.DATE) && month==(currentdate.get(Calendar.MONTH)+1) && year==currentdate.get(Calendar.YEAR) && hour==currentdate.get(Calendar.HOUR) && minute==currentdate.get(Calendar.MINUTE) && userdate.get(Calendar.AM_PM)==currentdate.get(Calendar.AM_PM) && flag.equals("notdone"))
                        {
                            JOptionPane.showMessageDialog(null, "You have a Reminder : \n"+name+" :\n"+sub, "Reminder!", JOptionPane.PLAIN_MESSAGE);
                            ps=con.prepareStatement("UPDATE `reminders` SET `flag`=? WHERE `id`=?");
                            ps.setString(1, "done");
                            ps.setLong(2, id);
                            ps.executeUpdate();
                        }
                        else if(flag.equals("done"))
                        {
                            
                            ps=con.prepareStatement("DELETE FROM `reminders` WHERE `id` = ?");
                            ps.setLong(1, id);
                            if(ps.executeUpdate()>0)
                            {
                                //JOptionPane.showMessageDialog(null, "alarm deleted successfully");
                            }
                        }
                    }
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null, ex);
                    System.exit(0);
                }
                finally
                {
                    try{con.close();ps.close();rs.close();}catch(Exception ex){}
                }
                }
                }
            }
        };
        
        Thread meetingcheck=new Thread(){       //thread to check meetings
            public void run()
            {
                while(true)
                {
                Connection con=null;PreparedStatement ps=null;ResultSet rs=null;
                try{
                    Class.forName("org.sqlite.JDBC");
                    String path=new File(".").getCanonicalPath();
                    con=DriverManager.getConnection("jdbc:sqlite:"+ /*this.getClass().getResource("/").getPath()*/path +"\\chatbot.db");
                    ps=con.prepareStatement("SELECT * FROM `meetings`");
                    rs=ps.executeQuery();
                    while(rs.next())
                    {
                        Integer id=rs.getInt("id");
                        String subject=rs.getString("subject");
                        String location=rs.getString("location");
                        String category=rs.getString("category");
                        String agenda=rs.getString("agenda");
                        String participant=rs.getString("participants");
                        String date=rs.getString("date");
                        String time=rs.getString("time");
                        String flag=rs.getString("flag");
                        
                        String s[]=date.split("/");
                        Integer dat=Integer.parseInt(s[0]);
                        Integer month=Integer.parseInt(s[1]);
                        Integer year=Integer.parseInt(s[2]);
                        
                        String s2[]=time.split(":");                          //s2[0]=hour, s3[0]=minute, s3[1]=am/pm
                        String s3[]=s2[1].split(" ");
                        Integer hour=Integer.parseInt(s2[0]);
                        Integer minute=Integer.parseInt(s3[0]);
                        Integer am_pm=0;
                        if(s3[1].equals("AM"))
                        {
                            am_pm=0;
                        }
                        else if(s3[1].equals("PM"))
                        {
                            am_pm=1;
                        }
                        
                        Calendar currentdate=Calendar.getInstance();
                        //currentdate.add(Calendar.HOUR, -1);
                        //System.out.println(currentdate.getTime());
                        if(dat==currentdate.get(Calendar.DATE) && month==(currentdate.get(Calendar.MONTH)+1) && year==currentdate.get(Calendar.YEAR) && hour==currentdate.get(Calendar.HOUR) && minute==currentdate.get(Calendar.MINUTE) /*&& second==d.getSeconds()*/ && am_pm==currentdate.get(Calendar.AM_PM) && flag.equals("notdone"))
                        {
                            JOptionPane.showMessageDialog(null, "You have a Meeting Reminder : \nSubject: "+subject+"    Location: "+location+"\nCategory: "+category+"    Agenda"+agenda+"\nParticipants: "+participant+"    Date: "+date+"\nTime: "+time  ,"Meeting Reminder!", JOptionPane.PLAIN_MESSAGE);
                            ps=con.prepareStatement("UPDATE `meetings` SET `flag`=? WHERE `id`=?");
                            ps.setString(1, "done");
                            ps.setLong(2, id);
                            ps.executeUpdate();
                        }
                        else if(flag.equals("done"))
                        {
                            
                            ps=con.prepareStatement("DELETE FROM `meetings` WHERE `id` = ?");
                            ps.setLong(1, id);
                            ps.executeUpdate();
                        }
                    }
                }
                catch(Exception e)
                {
                    System.out.println(e);
                    JOptionPane.showMessageDialog(null, e, "Error!", JOptionPane.ERROR_MESSAGE);
                }
                finally
                {
                    try{
                    con.close();
                    ps.close();
                    rs.close();
                    }
                    catch(Exception e)
                    {
                        
                    }
                }
                }
            }
        };
        alarmcheck.start();
        remindercheck.start();
        meetingcheck.start();
    }
    public static void main(String[] args)
    {
        Alarm_and_Reminder_checker ob=new Alarm_and_Reminder_checker();
        ob.setVisible(false);
        
        
    }
}
