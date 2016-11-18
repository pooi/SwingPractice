package tk.twpooi.taewoo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Prob09_01 {

public static void main(String[] args) {
		
		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob09");
		f.setSize(800, 500);
		
		Prob09_01Panel panel = new Prob09_01Panel();
		f.add(panel);
		
		f.setVisible(true);

	}

}

class Prob09_01Panel extends JPanel implements MouseListener{
	
	private ArrayList<HashMap<String, Integer>> list; 
	
	
	Prob09_01Panel(){
		
		this.list = new ArrayList<>();
		
		this.setLayout(new BorderLayout());
		
		
		new Thread(){
			public void run(){
				
				
				while(true){
					
					for(int i=0; i<list.size(); i++){
						
						HashMap<String, Integer> h = list.get(i);
						
						int num = h.get("num");
						int r = h.get("r");
						
						r += num;
						
						if(r <= 0 || r >= 50){
							num *= -1;
						}
						
						h.put("num", num);
						h.put("r", r);
						list.set(i, h);
						
					}
					
					
					repaint();
					
					try{
						Thread.sleep(10);
					}catch(Exception e){}
					
				}
				
			}
		}.start();
		
		this.addMouseListener(this);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(HashMap<String, Integer> h : list){
			
			int x = h.get("x");
			int y = h.get("y");
			int r = h.get("r");
			
			g.setColor(new Color(255, r*4, r*4));
			g.fillOval(x-r, y-r, r*2, r*2);
			
		}
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> temp = new HashMap<>();
		temp.put("x", e.getX());
		temp.put("y", e.getY());
		temp.put("num", 1);
		temp.put("r", 0);
		list.add(temp);
		
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
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}