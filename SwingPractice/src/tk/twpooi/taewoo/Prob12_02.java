package tk.twpooi.taewoo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Prob12_02 {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob12_02");
		f.setSize(800, 800);
		
		Prob12_02Panel panel = new Prob12_02Panel();
		f.add(panel);
		
		f.setVisible(true);

	}

}



class Prob12_02Panel extends JPanel implements MouseListener, MouseMotionListener {
	
	Prob12_02Panel p = this;
	
	ArrayList<MyCircle12_02> list;
	int count = 0;
	
	boolean isRun = false;
	
	boolean isDraw;
	int x1, x2, y1, y2;
	Color newColor;
	
	class MyCircle12_02 implements Runnable{
		
		private double x, y;
		private double a;
		private double g;
		private int r;
		private int delay;
		private Color color;
		
		MyCircle12_02(double x, double y, int r, Color color){
			
			this.x = x;
			this.y = y;
			this.a = 0.0;
			this.g= Math.max(1, r/20);
			this.r = r;
			this.color = color;
			
		}
		
		public void setInit(double x, double y, int r){
			this.x = x;
			this.y = y;
			this.a = 0.0;
			this.r = r;
		}
		
		@Override
		public void run(){
			
			delay -=1;
			if(delay > 0){
				return;
			}
			
			a += g;
			y += a;

			if(y > p.getSize().getHeight() - r){
				y = p.getSize().getHeight() - r;
				a = -a*0.9;
			}

		}
		
		public void drawCircle(Graphics g){
			g.setColor(color);
			g.fillOval((int)x-r, (int)y-r, r*2, r*2);
			g.setColor(Color.black);
			g.drawOval((int)x-r, (int)y-r, r*2, r*2);
		}
	}
	
	Prob12_02Panel(){
		
		this.list = new ArrayList<>();
		
		this.setLayout(new BorderLayout());

		this.setFocusable(true);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		new Thread(){
			public void run(){
				
				while(true){

					try{
						Thread.sleep(30);
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
					
					if(isRun){
						
						for(MyCircle12_02 c : list){
							c.run();
						}

						repaint();

					}

				}
				
			}
		}.start();
		
	}
	
	private void addCircle(int x, int y, int r, Color co){
		
		isRun = false;

		list.add(new MyCircle12_02(x, y, r, co));
		
		repaint();
		
		isRun = true;
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(MyCircle12_02 c : list){
			c.drawCircle(g);
		}
		

		if(isDraw){

			int r = (int) Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
			g.setColor(newColor);
			g.fillOval(x1-r, y1-r, r*2, r*2);
			g.setColor(Color.black);
			g.drawOval(x1-r, y1-r, r*2, r*2);
			
		}
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		Random rand = new Random();
		
		isDraw = true;
		x1 = x2 = e.getX();
		y1 = y2 = e.getY();
		newColor = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		int r = (int) Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
		addCircle(x1, y1, r, newColor);
		isDraw = false;
		repaint();
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		x2 = e.getX();
		y2 = e.getY();
		repaint();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}