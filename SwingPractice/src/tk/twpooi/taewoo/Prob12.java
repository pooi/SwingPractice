package tk.twpooi.taewoo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Prob12 {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob12");
		f.setSize(800, 800);
		
		Prob12Panel panel = new Prob12Panel();
		f.add(panel);
		
		f.setVisible(true);

	}

}



class Prob12Panel extends JPanel implements KeyListener {
	
	Prob12Panel p = this;
	
	ArrayList<MyCircle12> list;
	int count = 0;
	
	boolean isRun = false;
	
	class MyCircle12 implements Runnable{
		
		private double x, y;
		private double a;
		private int r = 10;
		private int delay;
		private Color color;
		
		MyCircle12(double x, double y, int delay, Color color){
			
			this.x = x;
			this.y = y;
			this.a = 0.0;
			this.delay = delay;
			this.color = color;
			
		}
		
		public void setInit(double x, double y, int delay){
			this.x = x;
			this.y = y;
			this.a = 0.0;
			this.delay = delay;
		}
		
		@Override
		public void run(){
			
			delay -=1;
			if(delay > 0){
				return;
			}
			
			a += 1.0;
			y += a;

			if(y > p.getSize().getHeight() - r*2){
				y = p.getSize().getHeight() - r*2;
				a = -a*0.9;
			}

		}
		
		public void drawCircle(Graphics g){
			g.setColor(color);
			g.fillOval((int)x-r, (int)y, r*2, r*2);
			g.setColor(Color.black);
			g.drawOval((int)x-r, (int)y, r*2, r*2);
		}
	}
	
	Prob12Panel(){
		
		this.list = new ArrayList<>();
		
		this.setLayout(new BorderLayout());

		this.setFocusable(true);
		this.addKeyListener(this);
		
		new Thread(){
			public void run(){
				
				while(true){

					try{
						Thread.sleep(30);
					}catch(Exception e){
						System.out.println(e.getMessage());
					}
					
					if(isRun){
						
						for(MyCircle12 c : list){
							c.run();
						}

						repaint();

					}

				}
				
			}
		}.start();
		
	}
	
	private void addCircle(){
		
		isRun = false;
		//list.clear();
		for(int i=0; i<list.size(); i++){
			
			double x = (this.getSize().getWidth())*(i+1)/(count+1);
			double y = 20;
			
			list.get(i).setInit(x, y, i*5);
		}
		
		Random r = new Random();
		
			
			double x = (this.getSize().getWidth())*(count)/(count+1);
			double y = 20;
			
			list.add(new MyCircle12(x, y, (count-1)*5, new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255))));
			
		
		repaint();
		
		isRun = true;
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(MyCircle12 c : list){
			c.drawCircle(g);
		}
		
	}
	
	@Override
	public void keyTyped(KeyEvent k) {
		
		if(k.getKeyChar() == ' '){
			
			count += 1;
			addCircle();
			
		}
		
	}

	@Override
	public void keyPressed(KeyEvent k) {}

	@Override
	public void keyReleased(KeyEvent k) {}

	
}