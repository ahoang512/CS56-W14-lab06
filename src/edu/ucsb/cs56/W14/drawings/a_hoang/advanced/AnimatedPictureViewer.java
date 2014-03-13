package edu.ucsb.cs56.w14.drawings.a_hoang.advanced;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;


public class AnimatedPictureViewer{
    
    private DrawPanel panel = new DrawPanel();

    private Skateboard sb = new Skateboard(100,100,100,20);

    Thread animation;

    private int x = 450;
    private int y = 400;

    private int speed = 5;
    
    public static void main(String[] args){
	new AnimatedPictureViewer().start();
    }
    
    public void start(){
	JFrame frame = new JFrame();
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	frame.getContentPane().add(panel);
	frame.setSize(640,480);
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);

	frame.getContentPane().addMouseListener(new MouseAdapter(){
	  public void mouseEntered(MouseEvent e){
       	    animation = new Animation();		 
	    animation.start();
	  }

	  public void mouseExited(MouseEvent e){
       	    animation.interrupt();		
	    while(animation.isAlive()){}
		animation= null;
	    
		panel.repaint();
	    
	  }
	  });
    }//start()

   class DrawPanel extends JPanel {
       public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;

         // Clear the panel first
          g2.setColor(Color.white);
          g2.fillRect(0,0,this.getWidth(), this.getHeight());

          // Draw the Ipod
          g2.setColor(Color.RED);
          Skateboard test = new Skateboard(x, y, 150,40);
          g2.draw(test);
       }
   }

    class Animation extends Thread {
      public void run() {
        try {
          while (true) {
            // Bounce off the walls

            if (x >= 480) { speed = -5; }
            if (x <= 0) { speed = 5; }
            
            x += speed;                
            panel.repaint();
            Thread.sleep(50);
          }
        } catch(Exception ex) {
          if (ex instanceof InterruptedException) {
            // Do nothing - expected on mouseExited
          } else {
            ex.printStackTrace();
            System.exit(1);
          }
        }
      }
    }
}
