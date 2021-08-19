
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.paralleldots.paralleldots.App;
import com.sun.glass.events.KeyEvent;
import java.sql.*;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import javax.swing.*;
import opennlp.tools.tokenize.SimpleTokenizer;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import javax.speech.AudioException;
import javax.speech.EngineException;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.*;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author soumajit
 */

public class Chatbot_UI extends javax.swing.JFrame {
    
    static boolean ans_found=false,final_ans_found=false;
    Long qus_id=new Long(0);int pass=0;
    String user_fname=null;
    //String user_lname=null;
    //String dob=null;
    //String email=null;
    
    Document doc;
    SimpleAttributeSet left;   //to append text left of JEditorPane
    SimpleAttributeSet right;  //to append text right of JEditorPane
    
    HTMLDocument htmldoc = null;
    HTMLEditorKit editorKit = null;
    
    static String process_name[]=null;
    static Process proc[]=null;static int a=0;
    static long count=0;
    static String msg="";
    static boolean got_answer=false;
    
    public static Map<String, Object> jsonToMap(String str)   //convert the json objeect to Map 
    {
        Map<String, Object> map=new Gson().fromJson(str, new TypeToken<HashMap<String, Object>>(){}.getType());
        return map;
    }
    
    public static Double probability(String s1, String s2)
    {
        Double d=0.00;
        try{
            App pd = new App("HoCuSYlXHAWDxjcoF7kEuWJbApwU08nCurdyJLn959o");

            String similarity = pd.similarity(s1, s2);
            Map<String, Object> respMap=jsonToMap(similarity.toString());
            d=Double.parseDouble(respMap.get("similarity_score").toString());
        }
        catch(Exception e)
        {
            try{
                App pd = new App("ZaHeSjyUl8FFZxwBJv4GLOlrQro4lzjTz39X2oHH6Pc");

                String similarity = pd.similarity(s1, s2);
                Map<String, Object> respMap=jsonToMap(similarity.toString());
                d=Double.parseDouble(respMap.get("similarity_score").toString());
            }
            catch(Exception e2)
            {
                try{
                    App pd = new App("Q9bY4awzbCwjcNTaiij1J0p9sHZQ15DjjDfd4VajnE4");

                    String similarity = pd.similarity(s1, s2);
                    Map<String, Object> respMap=jsonToMap(similarity.toString());
                    d=Double.parseDouble(respMap.get("similarity_score").toString());
                }
                catch(Exception e3)
                {
                    try{
                        App pd = new App("HoCuSYlXHAWDxjcoF7kEuWJbApwU08nCurdyJLn959o");

                        String similarity = pd.similarity(s1, s2);
                        Map<String, Object> respMap=jsonToMap(similarity.toString());
                        d=Double.parseDouble(respMap.get("similarity_score").toString());
                    }
                    catch(Exception e4)
                    {
                        return d;
                    }
                    //7raV0ZmibSkT4ymBwR8YXK03VZg4BRpx7asf8e7LNF0
                }
            }
        }
        return d;
    }
    
    public Chatbot_UI() {
        initComponents();
        
        jTextField1.requestFocus();
        jEditorPane1.setContentType("text/html"); //to set html and text both type of content in the JEditorPane
        
        htmldoc = (HTMLDocument) jEditorPane1.getDocument();
        editorKit = (HTMLEditorKit) jEditorPane1.getEditorKit();
        
        doc = jEditorPane1.getDocument();
        left = new SimpleAttributeSet();
        StyleConstants.setAlignment(left, StyleConstants.ALIGN_LEFT);//set text alignment left so the text can append left side of the jEditorPane
        StyleConstants.setForeground(left, Color.BLACK);//set the color of the text

        right = new SimpleAttributeSet();
        StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);//set text alignment right so the text can append right side of the jEditorPane
        StyleConstants.setForeground(right, Color.BLUE);//set the color of the text
        
        jEditorPane1.addHyperlinkListener(new HyperlinkListener() { //to listen clicks (or cursor movement) on links
                        @Override
                        public void hyperlinkUpdate(HyperlinkEvent e) {
                            Desktop desktop=Desktop.getDesktop();
                            if (HyperlinkEvent.EventType.ACTIVATED.equals(e.getEventType()))
                            {
                                try {
                                    
                                    desktop.browse(e.getURL().toURI());  //this will convert the URL to URI and open the URI via default browser
                                    //Desktop.getDesktop().browse(new URI(e.getURL().toString()));
                                    
                                } catch (Exception ex) {
                                    System.out.println(ex);
                                    String os = System.getProperty("os.name").toLowerCase();
                                    Runtime rt = Runtime.getRuntime();

                                    try{

                                        if (os.indexOf( "win" ) >= 0) {

                                            // this doesn't support showing urls in the form of "page.html#nameLink" 
                                            rt.exec( "rundll32 url.dll,FileProtocolHandler " + e.getURL().toString());

                                        } else if (os.indexOf( "mac" ) >= 0) {

                                            rt.exec( "open " + e.getURL().toString());

                                        } else if (os.indexOf( "nix") >=0 || os.indexOf( "nux") >=0) {

                                            // Do a best guess on unix until we get a platform independent way
                                            // Build a list of browsers to try, in this order.
                                            String[] browsers = {"epiphany", "firefox", "mozilla", "konqueror", "netscape","opera","links","lynx"};

                                            // Build a command string which looks like "browser1 "url" || browser2 "url" ||..."
                                            StringBuffer cmd = new StringBuffer();
                                            for (int i=0; i<browsers.length; i++)
                                                cmd.append( (i==0  ? "" : " || " ) + browsers[i] +" \"" + e.getURL().toString() + "\" ");

                                            rt.exec(new String[] { "sh", "-c", cmd.toString() });

                                       } else {
                                            return;
                                       }
                                   }catch (Exception Ex){
                                        System.out.println(Ex);
                                   }
                                }
                            }
                        }
                    });
        
        try{
            ans_found=false;final_ans_found=false;pass=0;
            jEditorPane1.setEditable(false);
            jPanel1.setBackground(Color.LIGHT_GRAY);
            String path=new File(".").getCanonicalPath();
            }
            catch(FileNotFoundException ex)
            {
                JOptionPane.showMessageDialog(null, "user_info file not found\nPlease try again");
                this.dispose();
            }
            catch(IOException ioe)
            {
                JOptionPane.showMessageDialog(this, "Problem to open Chatbot.\nPlease try again!", "Oops!", JOptionPane.ERROR_MESSAGE);
                this.dispose();
            }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        voiceoutput = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Jarvis");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/final_send_arrow-3.png"))); // NOI18N
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton1MousePressed(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/chatbot-icon (1)-edited.jpg"))); // NOI18N
        jLabel1.setText("JARVIS - your chat buddy");

        voiceoutput.setText("Voice Output");

        jScrollPane1.setViewportView(jEditorPane1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                        .addComponent(voiceoutput)
                        .addGap(21, 21, 21))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .addComponent(voiceoutput))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jMenu2.setText("File");

        jMenuItem1.setText("new chat");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuItem2.setText("back to home");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);

        jMenuItem3.setText("exit");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        PreparedStatement ps=null;
        Connection con=null;
        ResultSet rs=null;
        String weblink="";
        Runtime runtime = Runtime.getRuntime();
        String table="";
        if(voiceoutput.isSelected())
        {
            try{
                String path=new File(".").getCanonicalPath();
                System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
                Central.registerEngineCentral("com.sun.speech.freetts.jsapi.FreeTTSEngineCentral");
                Synthesizer synthesizer = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
                synthesizer.allocate();
                synthesizer.resume();

                Class.forName("org.sqlite.JDBC");
                con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
                if(msg.matches(""))
                {
                    editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Sorry I don't understand.\n"+"</span>"+"\n", 0, 0, null);
                    final_ans_found=true;
                    jTextField1.setText("");
                    synthesizer.speakPlainText("sorry I don't understand.", null);
                    synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                }
                else if(!final_ans_found)
                {
                    ps=con.prepareStatement("SELECT `answer` FROM `qna` WHERE `question`=?");
                    ps.setString(1, msg);
                    rs=ps.executeQuery();
                    if(rs.next())
                    {
                        ans_found=true;
                        jTextField1.setText("");
                        table="qna";
                    }
                    else
                    {
                        String os = System.getProperty("os.name").toLowerCase();
                        Runtime rt = Runtime.getRuntime();
                        if (os.indexOf( "win" ) >= 0)
                        {
                            if(msg.equalsIgnoreCase("open chrome"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("cmd /c start chrome");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Google chrome opened successfully."+"</span>"+"\n", 0, 0, null);
                                synthesizer.speakPlainText("google chrome opened successfully.", null);
                                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                            }
                            else if(msg.equalsIgnoreCase("close chrome"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("Taskkill /IM chrome.exe /F");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Google chrome closed successfully."+"</span>"+"\n", 0, 0, null);
                                synthesizer.speakPlainText("google chrome closed successfully.", null);
                                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                            }
                            else if(msg.equalsIgnoreCase("open notepad"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("cmd /c start notepad");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Notepad opened successfully."+"</span>"+"\n", 0, 0, null);
                                synthesizer.speakPlainText("notepad opened successfully.", null);
                                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                            }
                            else if(msg.equalsIgnoreCase("close notepad"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("Taskkill /IM notepad.exe /F");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Notepad closed successfully."+"</span>"+"\n", 0, 0, null);
                                synthesizer.speakPlainText("notepad closed successfully.", null);
                                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                            }
                            else if(msg.equalsIgnoreCase("open paint"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("cmd /c start mspaint");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Paint opened successfully."+"</span>"+"\n", 0, 0, null);
                                synthesizer.speakPlainText("paint opened successfully.", null);
                                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                            }
                            else if(msg.equalsIgnoreCase("close paint"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("Taskkill /IM mspaint.exe /F");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Paint closed successfully."+"</span>"+"\n", 0, 0, null);
                                synthesizer.speakPlainText("paint closed successfully.", null);
                                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                            }
                            else if(msg.equalsIgnoreCase("open taskmanager") || msg.equalsIgnoreCase("open task manager"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("cmd /c start taskmgr.exe");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Task manager opened successfully."+"</span>"+"\n", 0, 0, null);
                                synthesizer.speakPlainText("task manager opened successfully.", null);
                                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                            }
                            else if(msg.equalsIgnoreCase("close taskmanager") || msg.equalsIgnoreCase("close task manager"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("Taskkill /IM taskmgr.exe /F");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Task manager closed successfully."+"</span>"+"\n", 0, 0, null);
                                synthesizer.speakPlainText("task manager closed successfully.", null);
                                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                            }
                            else if(msg.equalsIgnoreCase("open word") || msg.equalsIgnoreCase("open msword"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("cmd /c start winword");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"M S word opened successfully."+"</span>"+"\n", 0, 0, null);
                                synthesizer.speakPlainText("Microsoft word opened successfully.", null);
                                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                            }
                            else if(msg.equalsIgnoreCase("close word") || msg.equalsIgnoreCase("close msword"))
                            {
                                got_answer=true;
                                jTextField1.setText("");
                                rt.exec("Taskkill /IM winword.exe /F");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"M S word closed successfully."+"</span>"+"\n", 0, 0, null);
                                synthesizer.speakPlainText("Microsoft word closed successfully.", null);
                                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                            }
                        }
                    }
                    if(ans_found && table.equals("qna"))
                    {
                        editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+rs.getString("answer")+"</span>"+"\n", 0, 0, null);
                        synthesizer.speakPlainText(rs.getString("answer"), null);
                        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                    }
                    else if(!ans_found)
                    {
                        /*Process process = java.lang.Runtime.getRuntime().exec("ping www.geeksforgeeks.org"); 
                        int x = process.waitFor(); 
                        if (x == 0)
                        { 
                                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                                SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
                                String user_tokens_array[]=simpleTokenizer.tokenize(msg);
                                
                                ps=con.prepareStatement("SELECT * FROM `qna`");
                                rs=ps.executeQuery();
                                int question_position[]=new int[100];
                                double probability[]=new double[100];
                                int a=0;

                                while(rs.next())
                                {
                                    String qus=rs.getString("question");
                                    int id=rs.getInt("id");

                                    long count=0,user_tokens_len=0;
                                    //Tokenizing the DB question
                                    String sys_tokens_array[] = simpleTokenizer.tokenize(qus);
                                    user_tokens_len=user_tokens_array.length;

                                    for(int i=0;i<sys_tokens_array.length;i++)
                                    {
                                        for(int j=0;j<user_tokens_array.length;j++)
                                        {
                                            if(user_tokens_array[j].equalsIgnoreCase(sys_tokens_array[i]))
                                            {
                                                count+=1;
                                            }
                                        }
                                    }
                                    if(count>(user_tokens_len/2))
                                    {
                                        double prob=probability(msg, qus);
                                        if(prob>0.70)
                                        {
                                            probability[a]=prob;
                                            question_position[a]=id;
                                            a+=1;
                                        }
                                    }
                                }
                                ps.close();
                                rs.close();
                                double max_prob=probability[0];
                                int max_prob_id=question_position[0];
                                for(int i=1;i<a;i++)
                                {
                                    if(max_prob<probability[i])
                                    {
                                        max_prob=probability[i];
                                        max_prob_id=question_position[i];
                                    }
                                }
                                ps=con.prepareStatement("SELECT `answer` FROM `qna` WHERE `id`=?");
                                ps.setLong(1, max_prob_id);
                                rs=ps.executeQuery();
                                while(rs.next())
                                {
                                    ans_found=true;
                                    jTextField1.setText("");
                                    editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+rs.getString("answer")+"</span>"+"\n", 0, 0, null);
                                    synthesizer.speakPlainText(rs.getString("answer"), null);
                                    synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                                }
                        }
                        else
                        {*/
                            ps=con.prepareStatement("SELECT * FROM `qna`");
                            rs=ps.executeQuery();
                            long match_count[]=new long[100]; 
                            long question_position[]=new long[100];
                            long remaining_token_count[]=new long[100];
                            int a=0;

                            while(rs.next())
                            {
                                String qus=rs.getString("question");
                                Long id=rs.getLong("id");

                                long count=0,user_tokens_len=0;
                                SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
                                //Tokenizing the given sentence
                                String sys_tokens_array[] = simpleTokenizer.tokenize(qus);
                                String user_tokens_array[]=simpleTokenizer.tokenize(msg);
                                user_tokens_len=user_tokens_array.length;
                                long remaining_tokens=0;

                                for(int i=0;i<sys_tokens_array.length;i++)
                                {
                                    for(int j=0;j<user_tokens_array.length;j++)
                                    {
                                        if(user_tokens_array[j].equalsIgnoreCase(sys_tokens_array[i]))
                                        {
                                            count+=1;
                                        }
                                    }
                                }
                                remaining_tokens=sys_tokens_array.length-count;

                                if(count>(user_tokens_len/2))
                                {
                                    match_count[a]=count;
                                    question_position[a]=id;
                                    remaining_token_count[a]=remaining_tokens;
                                    a+=1;
                                }
                            }
                            long id=0,value=match_count[0];int index=0;
                            long temp=remaining_token_count[0];
                            for(int i=1;i<(match_count.length);i++)
                            {
                                if(value<match_count[i])
                                {
                                    value=match_count[i];
                                    temp=remaining_token_count[i];
                                    index=i;
                                }
                                else if(value==match_count[i])
                                {
                                    if(temp>remaining_token_count[i])
                                    {
                                        index=i;
                                    }
                                }
                            }
                            id=question_position[index];
                            ps=con.prepareStatement("SELECT `answer` FROM `qna` WHERE `id`=?");
                            ps.setLong(1, id);
                            rs=ps.executeQuery();
                            while(rs.next())
                            {
                                jTextField1.setText("");
                                ans_found=true;
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+rs.getString("answer")+"</span>"+"\n", 0, 0, null);
                                synthesizer.speakPlainText(rs.getString("answer"), null);
                                synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                            }
                        //}
                    }
                    if(!ans_found)
                    {
                        int pass=0;
                        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
                        String m[] = simpleTokenizer.tokenize(msg);
                        String query="";
                        for(String s : m)
                        {
                            if(pass==0)
                            {
                                query=query+s;
                                pass+=1;
                            }
                            else if(pass>0)
                            {
                                query=query+"+"+s;
                            }
                        }
                        String searchURL = "https://www.google.com/search?q="+query.toString();
                        editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"I don't know how to answer for it.\nPlease click the link and search it on google."+"</span>"+"\n", 0, 0, null);
                        editorKit.insertHTML(htmldoc, doc.getLength(), "<a href='"+searchURL+"'>click here</a>", 0, 0, null);
                        jTextField1.setText("");
                        synthesizer.speakPlainText("I don't know how to answer for it. Please click the link and search it on google.", null);
                        synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);
                    }
                }
            }
            catch(UnknownHostException uhe)
            {
                JOptionPane.showMessageDialog(this, "Internet connection required!", "Oops!", JOptionPane.ERROR_MESSAGE);
                try{doc.insertString(doc.getLength(), "Internet connection required\n", left );}catch(Exception e){}
                ans_found=false;final_ans_found=false;
                System.gc();
            }
            catch(AudioException ae)
            {
                JOptionPane.showMessageDialog(this, ae+"\nProblem to allocate Speech engine", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            catch(ClassNotFoundException cnfe)
            {
                JOptionPane.showMessageDialog(this, cnfe+"\nDriver class not found", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            catch(EngineException ee)
            {
                JOptionPane.showMessageDialog(this, ee+"\nProblem to communicate with speech engine", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            catch(BadLocationException ex)
            {
                JOptionPane.showMessageDialog(this, "Some technical problems occur!\nPlease try again!", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            catch(IOException ioe)
            {
                JOptionPane.showMessageDialog(this, ioe+"", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            catch(InterruptedException ie)
            {
                JOptionPane.showMessageDialog(this, ie+"\nProblem with voice output", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            catch(SQLException sqle)
            {
                JOptionPane.showMessageDialog(this, sqle+"\nproblem to connect with database", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            finally
            {
                jTextField1.setText(null);
                jTextField1.requestFocus();
                ans_found=false;
                final_ans_found=false;
                try{
                con.close();
                rs.close();
                ps.close();
                }catch(Exception ex)
                {
                    
                }
            }
        }
        else       //for text output only(no voice output)
        {
            try{
                String path=new File(".").getCanonicalPath();
                Class.forName("org.sqlite.JDBC");
                con=DriverManager.getConnection("jdbc:sqlite:"+ path +"\\chatbot.db");
                if(msg.matches(""))
                {
                    editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Sorry I don't understand.\n"+"</span>"+"\n", 0, 0, null);
                    final_ans_found=true;
                    jTextField1.setText("");
                }
                else if(!final_ans_found)
                {
                    ps=con.prepareStatement("SELECT `answer` FROM `qna` WHERE `question`=?");
                    ps.setString(1, msg);
                    rs=ps.executeQuery();
                    if(rs.next())
                    {
                        ans_found=true;
                        jTextField1.setText("");
                        table="qna";
                    }
                    else
                    {
                        String os = System.getProperty("os.name").toLowerCase();
                        Runtime rt = Runtime.getRuntime();
                        if (os.indexOf( "win" ) >= 0)
                        {
                            if(msg.equalsIgnoreCase("open chrome"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("cmd /c start chrome");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Google chrome opened successfully."+"</span>"+"\n", 0, 0, null);
                            }
                            else if(msg.equalsIgnoreCase("close chrome"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("Taskkill /IM chrome.exe /F");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Google chrome closed successfully."+"</span>"+"\n", 0, 0, null);
                            }
                            else if(msg.equalsIgnoreCase("open notepad"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("cmd /c start notepad");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Notepad opened successfully."+"</span>"+"\n", 0, 0, null);
                            }
                            else if(msg.equalsIgnoreCase("close notepad"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("Taskkill /IM notepad.exe /F");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Notepad closed successfully."+"</span>"+"\n", 0, 0, null);
                            }
                            else if(msg.equalsIgnoreCase("open paint"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("cmd /c start mspaint");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Paint opened successfully."+"</span>"+"\n", 0, 0, null);
                            }
                            else if(msg.equalsIgnoreCase("close paint"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("Taskkill /IM mspaint.exe /F");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Paint closed successfully."+"</span>"+"\n", 0, 0, null);
                            }
                            else if(msg.equalsIgnoreCase("open taskmanager") || msg.equalsIgnoreCase("open task manager"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("cmd /c start taskmgr.exe");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Task manager opened successfully."+"</span>"+"\n", 0, 0, null);
                            }
                            else if(msg.equalsIgnoreCase("close taskmanager") || msg.equalsIgnoreCase("close task manager"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("taskkill /IM taskmgr.exe /F");         //doesn't working
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"Task manager closed successfully."+"</span>"+"\n", 0, 0, null);
                            }
                            else if(msg.equalsIgnoreCase("open word") || msg.equalsIgnoreCase("open msword"))
                            {
                                ans_found=true;
                                jTextField1.setText("");
                                rt.exec("cmd /c start winword");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"M S word opened successfully."+"</span>"+"\n", 0, 0, null);
                            }
                            else if(msg.equalsIgnoreCase("close word") || msg.equalsIgnoreCase("close msword"))
                            {
                                got_answer=true;
                                jTextField1.setText("");
                                rt.exec("Taskkill /IM winword.exe /F");
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"M S word closed successfully."+"</span>"+"\n", 0, 0, null);
                            }
                        }
                    }
                    if(ans_found && table.equals("qna"))
                    {
                        editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+rs.getString("answer")+"</span>"+"\n", 0, 0, null);
                    }
                    else if(!ans_found)
                    {
                        /*Process process = java.lang.Runtime.getRuntime().exec("ping www.geeksforgeeks.org"); 
                        int x = process.waitFor(); 
                        if (x == 0)
                        { 
                                Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
                                SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
                                String user_tokens_array[]=simpleTokenizer.tokenize(msg);
                                //finding matching tokens count, id and unmatched tokens count
                                ps=con.prepareStatement("SELECT * FROM `qna`");
                                rs=ps.executeQuery();
                                int question_position[]=new int[100];
                                double probability[]=new double[100];
                                int a=0;

                                while(rs.next())
                                {
                                    String qus=rs.getString("question");
                                    int id=rs.getInt("id");

                                    long count=0,user_tokens_len=0;
                                    //Tokenizing the DB question
                                    String sys_tokens_array[] = simpleTokenizer.tokenize(qus);
                                    user_tokens_len=user_tokens_array.length;

                                    for(int i=0;i<sys_tokens_array.length;i++)
                                    {
                                        for(int j=0;j<user_tokens_array.length;j++)
                                        {
                                            if(user_tokens_array[j].equalsIgnoreCase(sys_tokens_array[i]))
                                            {
                                                count+=1;
                                            }
                                        }
                                    }
                                    if(count>(user_tokens_len/2))
                                    {
                                        double prob=probability(msg, qus);
                                        if(prob>0.70)
                                        {
                                            probability[a]=prob;
                                            question_position[a]=id;
                                            a+=1;
                                        }
                                    }
                                }
                                ps.close();
                                rs.close();
                                double max_prob=probability[0];
                                int max_prob_id=question_position[0];
                                for(int i=1;i<a;i++)
                                {
                                    if(max_prob<probability[i])
                                    {
                                        max_prob=probability[i];
                                        max_prob_id=question_position[i];
                                    }
                                }
                                ps=con.prepareStatement("SELECT `answer` FROM `qna` WHERE `id`=?");
                                ps.setLong(1, max_prob_id);
                                rs=ps.executeQuery();
                                while(rs.next())
                                {
                                    ans_found=true;
                                    jTextField1.setText("");
                                    editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+rs.getString("answer")+"</span>"+"\n", 0, 0, null);
                                }
                        }
                        else
                        { */
                            ps=con.prepareStatement("SELECT * FROM `qna`");
                            rs=ps.executeQuery();
                            long match_count[]=new long[100]; 
                            long question_position[]=new long[100];
                            long remaining_token_count[]=new long[100];
                            int a=0;

                            while(rs.next())
                            {
                                String qus=rs.getString("question");
                                Long id=rs.getLong("id");

                                long count=0,user_tokens_len=0;
                                SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
                                //Tokenizing the given sentence
                                String sys_tokens_array[] = simpleTokenizer.tokenize(qus);
                                String user_tokens_array[]=simpleTokenizer.tokenize(msg);
                                user_tokens_len=user_tokens_array.length;
                                long remaining_tokens=0;

                                for(int i=0;i<sys_tokens_array.length;i++)
                                {
                                    for(int j=0;j<user_tokens_array.length;j++)
                                    {
                                        if(user_tokens_array[j].equalsIgnoreCase(sys_tokens_array[i]))
                                        {
                                            count+=1;
                                        }
                                    }
                                }
                                remaining_tokens=sys_tokens_array.length-count;

                                if(count>(user_tokens_len/2))
                                {
                                    match_count[a]=count;
                                    question_position[a]=id;
                                    remaining_token_count[a]=remaining_tokens;
                                    a+=1;
                                }
                            }
                            long id=0,value=match_count[0];int index=0;
                            long temp=remaining_token_count[0];
                            for(int i=1;i<(match_count.length);i++)
                            {
                                if(value<match_count[i])
                                {
                                    value=match_count[i];
                                    temp=remaining_token_count[i];
                                    index=i;
                                }
                                else if(value==match_count[i])
                                {
                                    if(temp>remaining_token_count[i])
                                    {
                                        index=i;
                                    }
                                }
                            }
                            id=question_position[index];
                            ps=con.prepareStatement("SELECT `answer` FROM `qna` WHERE `id`=?");
                            ps.setLong(1, id);
                            rs=ps.executeQuery();
                            while(rs.next())
                            {
                                jTextField1.setText("");
                                ans_found=true;
                                editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+rs.getString("answer")+"</span>"+"\n", 0, 0, null);
                            }
                        //}
                    }
                    if(!ans_found)
                    {
                        int pass=0;
                        SimpleTokenizer simpleTokenizer = SimpleTokenizer.INSTANCE;
                        String m[] = simpleTokenizer.tokenize(msg);
                        String query="";
                        for(String s : m)
                        {
                            if(pass==0)
                            {
                                query=query+s;
                                pass+=1;
                            }
                            else if(pass>0)
                            {
                                query=query+"+"+s;
                            }
                        }
                        String searchURL = "https://www.google.com/search?q="+query.toString();//+"&num="+1;
                        editorKit.insertHTML(htmldoc, doc.getLength(), "<span style=\"background-color: #D3D3D3\">"+"I don't know how to answer for it.\nPlease click the link and search it on google........................."+"</span>"+"\n", 0, 0, null);
                        editorKit.insertHTML(htmldoc, doc.getLength(), "<a href='"+searchURL+"'>click here</a>", 0, 0, null);
                        jTextField1.setText("");
                    }
                }
            }
            catch(UnknownHostException uhe)
            {
                JOptionPane.showMessageDialog(this, "Internet connection required!", "Oops!", JOptionPane.ERROR_MESSAGE);
                try{doc.insertString(doc.getLength(), "Internet connection required\n", left );}catch(Exception e){}
                ans_found=false;final_ans_found=false;
                System.gc();
            }
            catch(ClassNotFoundException cnfe)
            {
                JOptionPane.showMessageDialog(this, cnfe+"\nDriver class not found", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            catch(BadLocationException ex)
            {
                JOptionPane.showMessageDialog(this, "Some technical problems occur!\nPlease try again!", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            catch(IOException ioe)
            {
                JOptionPane.showMessageDialog(this, ioe+"", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            catch(SQLException sqle)
            {
                JOptionPane.showMessageDialog(this, sqle+"\nproblem to connect with database", "Oops!", JOptionPane.ERROR_MESSAGE);
            }
            finally
            {
                jTextField1.setText("");
                jTextField1.requestFocus();
                ans_found=false;
                final_ans_found=false;
                try{
                con.close();
                rs.close();
                ps.close();
                }
                catch(Exception ex)
                {
                    
                }
            }
        }
        System.gc();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        jTextField1.setText("");
        jEditorPane1.setText("");
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        this.dispose();
        System.exit(0);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        
    }//GEN-LAST:event_jButton1MouseClicked

    private void jButton1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MousePressed
        try{
                doc = jEditorPane1.getDocument();
                String txt=jTextField1.getText();
                doc.insertString(doc.getLength(), ""+txt+"\n", right );
                msg=txt;
                jTextField1.setText("Chatbot is Typing...");
                }
                catch(BadLocationException ex)
                {
                    System.out.println(ex);
                }
    }//GEN-LAST:event_jButton1MousePressed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        
    }//GEN-LAST:event_jTextField1KeyPressed

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
            java.util.logging.Logger.getLogger(Chatbot_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Chatbot_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Chatbot_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Chatbot_UI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        
        Chatbot_UI ui=new Chatbot_UI();
        ui.setVisible(true);
        ui.setResizable(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private static javax.swing.JButton jButton1;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    public static javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JRadioButton voiceoutput;
    // End of variables declaration//GEN-END:variables
}
