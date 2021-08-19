package BBGame;


import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author soumajit
 */
public class main
{
    public static void main(String[] args)
    {
        JFrame obj=new JFrame();
        gameplay gameplay=new gameplay();
        obj.setBounds(10,10,710,600);
        obj.setTitle("Brick and Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
}
