package tk.twpooi.taewoo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Prob09 {
	
	public static void main(String[] args) {
		
		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob09");
		f.setSize(800, 500);
		
		Prob09Panel panel = new Prob09Panel();
		f.add(panel);
		
		f.setVisible(true);

	}

}

class Prob09Panel extends JPanel implements MouseMotionListener{
	
	private int x;
	private int y;
	private int r;
	
	Prob09Panel(){
		
		this.setLayout(new BorderLayout());
		
		
		new Thread(){
			public void run(){
				
				int num = 1;
				
				while(true){
					
					r += num;
					
					if(r <= 0 || r >= 50){
						num *= -1;
					}
					
					repaint();
					
					try{
						Thread.sleep(10);
					}catch(Exception e){}
					
				}
				
			}
		}.start();
		
		this.addMouseMotionListener(this);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.setColor(Color.black);
		g.setColor(new Color(r*4, r*4, r*4));
		g.fillOval(x-r, y-r, r*2, r*2);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
		x = e.getX();
		y = e.getY();
		repaint();
		
	}
	
}