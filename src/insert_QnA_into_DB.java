/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.io.*;
/**
 *
 * @author soumajit
 */
public class insert_QnA_into_DB
{
    public static void main(String[] args) throws Exception
    {
    String question;
    String answer,ack_text;
    PreparedStatement ps = null;
    Connection con = null;
    ResultSet rs = null;

    try
    {
        BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\soumajit\\Desktop\\chatbot QnA\\chatbot_questions (part-4).txt"));
        
        Class.forName("org.sqlite.JDBC");
        con=DriverManager.getConnection("jdbc:sqlite:C:\\Users\\soumajit\\Documents\\NetBeansProjects\\Chatbot2\\src\\chatbot.db");

        String line = null;//long id=2;
        
        while ((line = br.readLine()) != null)
        {
            if (line != null && !line.equals("") && line.matches(".*[,].*"))
            {
            String tmp[] = line.split(",");
            question = tmp[0];
            answer = tmp[1];
            //ack_text= tmp[2];
            System.out.println(question+ "\t" + answer);

            //String sql ="INSERT INTO qna (id, question, answer) values (?,?,?)";
            String sql="INSERT INTO qna (id, question, answer) values (?,?,?)";

            ps = con.prepareStatement(sql);
            ps.setString(1, null);
            ps.setString(2, question);
            ps.setString(3, answer);
            //ps.setString(4, ack_text);
            int check=ps.executeUpdate();
            if(check==1)
            {
                System.out.println("insertion successfull");
                //++id;
            }
            }
        }

        br.close();
        con.close();
        ps.close();
    }
    catch (IOException e)
    {
        e.printStackTrace();
    }
    }
}
