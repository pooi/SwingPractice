package tk.twpooi.taewoo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Prob10_01 {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		f.setSize(1000, 800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Prob10_01Panel panel = new Prob10_01Panel();
		f.add(panel);
		
		f.setVisible(true);

	}

}


class Prob10_01Panel extends JPanel implements MouseListener, MouseMotionListener{
	
	BufferedImage img;
	
	ArrayList<HashMap<String, Integer>> list;
	int x1, x2, y1, y2;
	boolean isDrawing;
	
	ArrayList<Integer> innerIndexList;
	
	Prob10_01Panel(){
		
		this.list = new ArrayList<>();
		this.innerIndexList = new ArrayList<>();
		
		try{
			img = ImageIO.read(new File("lena.jpg"));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		for(int i=0; i<list.size(); i++){
			
			HashMap<String, Integer> h = list.get(i);
			
			int x1 = h.get("x1");
			int x2 = h.get("x2");
			int y1 = h.get("y1");
			int y2 = h.get("y2");
			
			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			
			int dx = Math.abs(x1-x2);
			int dy = Math.abs(y1-y2);
			
			g2d.drawImage(img, x1, y1, x2-x1, y2-y1, null);
			if(this.innerIndexList.contains(i)){
				g2d.setColor(Color.yellow);
				g2d.setStroke(new BasicStroke(2));
				g2d.drawRect(x, y, dx, dy);
			}
			
		}
		
		if(isDrawing){
			
			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			
			int dx = Math.abs(x1-x2);
			int dy = Math.abs(y1-y2);
			
			g2d.drawImage(img, x1, y1, x2-x1, y2-y1, null);
			g2d.setColor(Color.red);
			g2d.setStroke(new BasicStroke(2));
			g2d.drawRect(x, y, dx, dy);
			
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
		if(innerIndexList.size() == 0){
			
			x2 = e.getX();
			y2 = e.getY();
			
		}else{
			
			for(int index : innerIndexList){
				
				HashMap<String, Integer> h = list.get(index);
				
				int x1 = h.get("x1");
				int x2 = h.get("x2");
				int y1 = h.get("y1");
				int y2 = h.get("y2");
				int x3 = h.get("x3");
				int y3 = h.get("y3");
				
				int dx = e.getX() - x3;
				int dy = e.getY() - y3;
				
				x1 += dx;
				x2 += dx;
				x3 += dx;
				y1 += dy;
				y2 += dy;
				y3 += dy;
				
				h.put("x1", x1);
				h.put("x2", x2);
				h.put("x3", x3);
				h.put("y1", y1);
				h.put("y2", y2);
				h.put("y3", y3);
				
				list.set(index, h);
				
			}
			
		}
		
		repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
		this.innerIndexList.clear();
		
		int ex = e.getX();
		int ey = e.getY();
		
		for(int i=0; i<list.size(); i++){
			
			HashMap<String, Integer> h = list.get(i);
			
			int x1 = Math.min(h.get("x1"), h.get("x2"));//h.get("x1");
			int x2 = Math.max(h.get("x1"), h.get("x2"));;
			int y1 = Math.min(h.get("y1"), h.get("y2"));
			int y2 = Math.max(h.get("y1"), h.get("y2"));
			
			if(x1 <= ex && ex <= x2 && y1 <= ey && ey <= y2){
				innerIndexList.add(i);
			}
			
		}
		
		if(innerIndexList.size() == 0){
			
			isDrawing = true;
			x1 = x2 = e.getX();
			y1 = y2 = e.getY();
			
		}else{
			
			for(int index : innerIndexList){
				
				list.get(index).put("x3", ex);
				list.get(index).put("y3", ey);
				
			}
			
		}
		
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		if(this.innerIndexList.size() == 0){

			HashMap<String, Integer> temp = new HashMap<>();

			temp.put("x1", x1);
			temp.put("y1", y1);
			temp.put("x2", x2);
			temp.put("y2", y2);

			list.add(temp);

			isDrawing = false;

		}
		
		this.innerIndexList.clear();
		
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}