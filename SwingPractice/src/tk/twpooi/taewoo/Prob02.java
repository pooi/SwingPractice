package tk.twpooi.taewoo;

import java.awt.BorderLayout;
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

public class Prob02 {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob02");
		f.setSize(500, 500);
		
		Prob02Panel panel = new Prob02Panel();
		f.add(panel);
		
		f.setVisible(true);

	}

}

class Prob02Panel extends JPanel implements MouseListener, MouseMotionListener{
	
	private JLabel numLabel;
	
	private ArrayList<ArrayList<HashMap<String, Double>>> list;
	private ArrayList<HashMap<String, Double>> tempList;
	
	Prob02Panel(){
		
		this.list = new ArrayList<>();
		
		this.setLayout(new BorderLayout());
		
		numLabel = new JLabel("Num Of Curves : " + list.size());
		numLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(numLabel, BorderLayout.NORTH);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(ArrayList<HashMap<String, Double>> a : list){
			for(int i=1; i<a.size(); i++){
				
				HashMap<String, Double> po1 = a.get(i-1);
				HashMap<String, Double> po2 = a.get(i);
				
				int x1 = (int)(po1.get("x") * getWidth());
				int y1 = (int)(po1.get("y") * getHeight());
				int x2 = (int)(po2.get("x") * getWidth());
				int y2 = (int)(po2.get("y") * getHeight());
				
				g.drawLine(x1, y1, x2, y2);
				
			}
		}
		
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		tempList = new ArrayList<>();
		HashMap<String, Double> temp = new HashMap<>();
		temp.put("x", (double)e.getX()/getWidth());
		temp.put("y", (double)e.getY()/getHeight());
		tempList.add(temp);
		list.add(tempList);
		repaint();
		numLabel.setText("Num Of Curves : " + list.size());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		HashMap<String, Double> temp = new HashMap<>();
		temp.put("x", (double)e.getX()/getWidth());
		temp.put("y", (double)e.getY()/getHeight());
		tempList.add(temp);
		list.set(list.size()-1, tempList);
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}