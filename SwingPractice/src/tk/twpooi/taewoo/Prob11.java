package tk.twpooi.taewoo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Prob11 {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob11");
		f.pack();
		f.setSize(800, 500);
		
		Prob11Panel panel = new Prob11Panel(f);
		panel.setBounds(0, 0, 800, 500);
		f.add(panel);
		
		f.setVisible(true);

	}

}

class Prob11Panel extends JPanel implements KeyListener{
	
	private JFrame parent;
	
	int x, y;
	int d = 100;
	
	int direction = 0;
	
	Prob11Panel(JFrame parent){
		
		this.parent = parent;
		
		this.setLayout(new BorderLayout());
		
		this.setFocusable(true);
	    requestFocusInWindow();
		
		this.addKeyListener(this);
		
		new Thread(){
			public void run(){
				
				int gap = 15;
				
				while(true){
					
					if(direction != 0){
						
						switch(direction){
						case KeyEvent.VK_UP:
							y-=gap;
							if(y < 0){
								y+=gap;
								direction = KeyEvent.VK_DOWN;
							}
							break;
						case KeyEvent.VK_DOWN:
							y+=gap;
							if(y+d > parent.getHeight()-25-1){
								y-=gap;
								direction = KeyEvent.VK_UP;
							}
							break;
						case KeyEvent.VK_LEFT:
							x-=gap;
							if(x < 0){
								x+=gap;
								direction = KeyEvent.VK_RIGHT;
							}
							break;
						case KeyEvent.VK_RIGHT:
							x+=gap;
							if(x+d > parent.getWidth()-1){
								x-=gap;
								direction = KeyEvent.VK_LEFT;
							}
							break;
						}
						
						repaint();
					}
					try{
						Thread.sleep(20);
					}catch(Exception e){}
				}
				
			}
		}.start();
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.fillRect(x, y, d, d);
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
		switch(arg0.getKeyCode()){
		case KeyEvent.VK_UP:
		case KeyEvent.VK_DOWN:
		case KeyEvent.VK_LEFT:
		case KeyEvent.VK_RIGHT:
			direction =  arg0.getKeyCode();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
	
}