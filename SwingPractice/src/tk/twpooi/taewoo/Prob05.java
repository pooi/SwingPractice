package tk.twpooi.taewoo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Prob05 {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob05");
		f.setSize(800, 500);
		
		Prob05Panel panel = new Prob05Panel();
		f.add(panel);
		
		f.setVisible(true);

	}

}

class Prob05Panel extends JPanel implements MouseListener, MouseMotionListener{
	
	private JList circleList;
	
	private ArrayList<HashMap<String, Object>> list;
	private Vector v;
	
	Prob05Panel(){
		
		this.list = new ArrayList<>();
		this.v = new Vector<>();
		
		this.setLayout(new BorderLayout());
		
		JPanel listPanel = new JPanel();
		listPanel.setPreferredSize(new Dimension(200, 100));
		listPanel.setOpaque(true);
		listPanel.setBackground(Color.darkGray);
		listPanel.setLayout(new BorderLayout());
		
		circleList = new JList(new Vector());
		circleList.addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectCircle(circleList.getSelectedIndex());
			}
			
		});
		listPanel.add(circleList, BorderLayout.CENTER);
		
		JButton deleteBtn = new JButton("Delete");
		deleteBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(circleList.getSelectedIndex() >= 0){
					removeCircle(circleList.getSelectedIndex());
					setList();
				}
				
			}
			
		});
		listPanel.add(deleteBtn, BorderLayout.SOUTH);
		
		this.add(listPanel, BorderLayout.WEST);
		
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}
	
	public void setList(){
		
		v = new Vector<>();
		for(int i=0; i<list.size(); i++){
			v.addElement(i + ":center(" + list.get(i).get("x") + "," + list.get(i).get("y") + ")");
		}
		circleList.setListData(v);
		
		
	}
	
	public void removeCircle(int index){
		
		if(0 <= index && index < list.size()){
			list.remove(index);
			repaint();
		}
		
	}
	
	public void selectCircle(int index){
		
		for(int i=0; i<list.size(); i++){
			if(i == index){
				list.get(i).put("color", Color.RED);
			}else{
				list.get(i).put("color", Color.BLACK);
			}
		}
		repaint();
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(HashMap<String, Object> hash : list){
			
			int x1 = (int)hash.get("x");
			int y1 = (int)hash.get("y");
			Color color = (Color)hash.get("color");

			//int r = (int)Math.sqrt(Math.pow((double)x1-x2, 2) + Math.pow((double)y1-y2, 2));
			
			int x = x1-50;
			int y = y1-50;
			
			g.setColor(color);
			g.drawOval(x, y, 100, 100);
			
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("x", e.getX());
		temp.put("y", e.getY());
		temp.put("color", Color.black);
		list.add(temp);
		setList();
		repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}