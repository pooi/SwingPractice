package tk.twpooi.taewoo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Prob01 {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob01");
		f.setSize(500, 500);
		
		Prob01Panel panel = new Prob01Panel();
		f.add(panel);
		
		f.setVisible(true);

	}

}

class Prob01Panel extends JPanel implements MouseListener, MouseMotionListener{
	
	private JLabel numLabel;
	private int count = 0;
	
	private int mouseSelected;
	private ArrayList<HashMap<String, Integer>> list;
	
	private HashMap<String, Integer> deleteRec;
	
	Prob01Panel(){
		
		this.list = new ArrayList<>();
		this.deleteRec = new HashMap<>();
		
		
		this.setLayout(new BorderLayout());
		
		numLabel = new JLabel("Num Of Points : " + list.size());
		numLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(numLabel, BorderLayout.NORTH);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setFocusable(true);
		
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		
		super.paintComponent(g);
		
		g.setColor(Color.black);
		for(HashMap<String, Integer> h : list){
			
			g.fillRect(h.get("x"), h.get("y"), 3, 3);
			
		}
		
		if(deleteRec.size() > 0){
			g.setColor(Color.red);
			
			int x1 = deleteRec.get("x1");
			int y1 = deleteRec.get("y1");
			int x2 = deleteRec.get("x2");
			int y2 = deleteRec.get("y2");
			
			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			
			g.drawRect(x, y, Math.abs(x2-x1), Math.abs(y2-y1));
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(mouseSelected == MouseEvent.BUTTON1){
			
			HashMap<String, Integer> temp = new HashMap<>();
			temp.put("x", e.getX());
			temp.put("y", e.getY());
			list.add(temp);
			numLabel.setText("Num Of Points : " + list.size());
			repaint();
			
		}
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		this.mouseSelected = e.getButton();
		
		if(mouseSelected == MouseEvent.BUTTON3){
			
			deleteRec.clear();
			deleteRec.put("x1", e.getX());
			deleteRec.put("x2", e.getX());
			deleteRec.put("y1", e.getY());
			deleteRec.put("y2", e.getY());
			
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(deleteRec.size()>0){
			int x1 = deleteRec.get("x1");
			int y1 = deleteRec.get("y1");
			int x2 = deleteRec.get("x2");
			int y2 = deleteRec.get("y2");

			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			int w = Math.abs(x1-x2);
			int h = Math.abs(y1-y2);

			for(int i=0; i<list.size(); i++){
				HashMap<String, Integer> hash = list.get(i);
				
				int hashX = hash.get("x");
				int hashY = hash.get("y");

				if( (x <= hashX && hashX <= x + w) && (y <= hashY && hashY <= y+h)){
					list.remove(i);
					i-=1;
				}
				
			}

			deleteRec.clear();
			numLabel.setText("Num Of Points : " + list.size());
			repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(mouseSelected == MouseEvent.BUTTON1){
			HashMap<String, Integer> temp = new HashMap<>();
			temp.put("x", e.getX());
			temp.put("y", e.getY());
			list.add(temp);
			numLabel.setText("Num Of Points : " + list.size());
			repaint();
		}else if(mouseSelected == MouseEvent.BUTTON3){
			
			deleteRec.put("x2", e.getX());
			deleteRec.put("y2", e.getY());
			repaint();
			
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}