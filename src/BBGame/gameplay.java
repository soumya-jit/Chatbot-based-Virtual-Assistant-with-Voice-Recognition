package BBGame;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author soumajit
 */
public class gameplay extends JPanel implements KeyListener,ActionListener
{

    private boolean play=false; private boolean flag=false;
    private int score=0;
    private int totalbricks=21;     //
    private Timer timer;
    private int delay=6;
    private int playerx=310;
    private int ballposx=330;
    private int ballposy=530;
    private int ballxdir=-1;
    private int ballydir=-2;
    
    private mapgenerator map;
    
    public gameplay()
    {
        map=new mapgenerator(3,7);   //
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        timer=new Timer(delay,this); //speed of ball is delay var
        timer.start();
        
    }
    
    public void paint(Graphics g)
    {
        //background
        g.setColor(Color.black);
        g.fillRect(1,1,692,592);
        
        //drawing map
        map.draw((Graphics2D)g);
        /*g.setColor(Color.red);
        g.drawString("Press Enter to Play", 323, 245);*/
        
        //borders
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592);
        g.fillRect(0, 0, 692, 3);
        g.fillRect(691, 0, 3, 592);
        
        //scores
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString("Score : "+score, 490, 30);
        
        //the paddle
        g.setColor(Color.green);
        g.fillRect(playerx,550,100,8);
        
        //the ball
        g.setColor(Color.yellow);
        g.fillOval(ballposx,ballposy,20,20);
        
        if(totalbricks <=0)
        {
            play = false;
            flag=true;
            ballxdir=0;
            ballydir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won! , Score : "+score, 220, 300);
            
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Play on Level 2", 240, 350);
        }
        
        if(ballposy > 570)
        {
            play = false;
            
            ballxdir=0;
            ballydir=0;
            g.setColor(Color.red);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Score : "+score, 190, 300);
            
            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }
        
        g.dispose();
    }

    @Override
    public void keyPressed(KeyEvent e) 
    {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(play ==false)
            {
                play=false;
            }
        }
        
        if(e.getKeyCode()==KeyEvent.VK_RIGHT)
        {
            if(playerx >= 600)
            {
                playerx=600;
            }
            else
            {
                moveRight();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_LEFT)
        {
            if(playerx < 10)
            {
                playerx = 10;
            }
            else
            {
                moveLeft();
            }
        }
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if(!play && flag==true)
            {
                play=true;
                ballposx=330;
                ballposy=530;
                ballxdir=-1;
                ballydir=-2;
                playerx=310;
                score=0;
                totalbricks=28;
                delay=4;
                map=new mapgenerator(4,7);
                repaint();
            }
            
            if(!play)
            {
                play=true;
                ballposx=330;
                ballposy=530;
                ballxdir=-1;
                ballydir=-2;
                playerx=310;
                score=0;
                totalbricks=21;
                delay=6;
                map=new mapgenerator(3,7);
                repaint();
            }
            
        }
    }
    
    public void moveRight()
    {
        play=true;
        playerx+=20;
    }
    public void moveLeft()
    {
        play=true;
        playerx-=20;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        
        if(play)
        {
            if(new Rectangle(ballposx,ballposy,20,20).intersects(new Rectangle(playerx,550,100,8)))
            {
                ballydir =-ballydir;
            }
            
            A: for(int i=0;i<map.map.length;i++)
            {
                for(int j=0;j<map.map[0].length;j++)
                {
                    if(map.map[i][j] > 0)
                    {
                        int brickx = j*map.brickwidth+80;
                        int bricky = i*map.brickheight+50;
                        int brickwidth = map.brickwidth;
                        int brickheight = map.brickheight;
                        
                        Rectangle rect = new Rectangle(brickx, bricky, brickwidth, brickheight);
                        Rectangle ballrect = new Rectangle(ballposx, ballposy, 20, 20);
                        Rectangle brickrect = rect;
                        
                        if(ballrect.intersects(brickrect))
                        {
                            map.setbrickvalue(0, i, j);
                            totalbricks-=1;
                            score+=5;
                            
                            if(ballposx+19 <= brickrect.x ||ballposx+1 >= brickrect.x+brickrect.width)
                            {
                                ballxdir=-ballxdir;
                            }
                            else
                            {
                                ballydir=-ballydir;
                            }
                            break A;
                        }
                    }
                }
            }
            
            ballposx +=ballxdir;
            ballposy +=ballydir;
            if(ballposx < 0)             //for left border
            {
                ballxdir = -ballxdir;
            }
            if(ballposy < 0)             //for top border
            {
                ballydir = -ballydir;
            }
            if(ballposx > 670)           //for right border
            {
                ballxdir = -ballxdir;
            }
        }
        
        repaint();
    }
    
}
