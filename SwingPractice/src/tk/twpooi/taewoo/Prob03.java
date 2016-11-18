package tk.twpooi.taewoo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingConstants;

public class Prob03 {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob03");
		f.setSize(500, 500);
		
		Prob03Panel panel = new Prob03Panel();
		f.add(panel);
		
		f.setJMenuBar(panel.getMenuBar());
		
		f.setVisible(true);

	}

}

class Prob03Panel extends JPanel implements MouseListener, MouseMotionListener{
	
	private JLabel numLabel;
	
	private ArrayList<HashMap<String, Integer>> list;
	private int colorIndex;
	private Color[] colors = {
			Color.RED,
			Color.GREEN,
			Color.BLUE
	};
	
	private JMenuBar menuBar;
	
	Prob03Panel(){
		
		this.list = new ArrayList<>();
		
		this.setLayout(new BorderLayout());
		
		numLabel = new JLabel("Num Of Rects : " + list.size());
		numLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(numLabel, BorderLayout.NORTH);
		
		menuBar = new JMenuBar();
		JMenu menu1 = new JMenu("color");
		ButtonGroup group = new ButtonGroup();
		
		JRadioButtonMenuItem itemRed = new JRadioButtonMenuItem("Red");
		itemRed.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				colorIndex = 0;
				
			}
			
		});
		itemRed.setSelected(true);
		menu1.add(itemRed);
		group.add(itemRed);
		
		JRadioButtonMenuItem itemGreen = new JRadioButtonMenuItem("Green");
		itemGreen.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				colorIndex = 1;
				
			}
			
		});
		menu1.add(itemGreen);
		group.add(itemGreen);

		JRadioButtonMenuItem itemBlue = new JRadioButtonMenuItem("Blue");
		itemBlue.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				colorIndex = 2;
				
			}
			
		});
		menu1.add(itemBlue);
		group.add(itemBlue);
		
		menuBar.add(menu1);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}
	
	public JMenuBar getMenuBar(){
		return menuBar;
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(HashMap<String, Integer> hash : list){
			
			int x1 = hash.get("x1");
			int y1 = hash.get("y1");
			int x2 = hash.get("x2");
			int y2 = hash.get("y2");
			int colorIndexTemp = hash.get("color");
			
			int x = Math.min(x1, x2);
			int y = Math.min(y1, y2);
			int w = Math.abs(x1-x2);
			int h = Math.abs(y1-y2);
			Color color = colors[colorIndexTemp];
			
			g.setColor(color);
			g.fillRect(x, y, w, h);
			g.setColor(Color.black);
			g.drawRect(x, y, w, h);
			
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		HashMap<String, Integer> temp = list.get(list.size()-1);
		temp.put("x2", e.getX());
		temp.put("y2", e.getY());
		list.set(list.size()-1, temp);
		repaint();
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
		HashMap<String, Integer> temp = new HashMap<>();
		temp.put("x1", e.getX());
		temp.put("y1", e.getY());
		temp.put("x2", e.getX());
		temp.put("y2", e.getY());
		temp.put("color", colorIndex);
		list.add(temp);
		numLabel.setText("Num Of Rects : " + list.size());
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}