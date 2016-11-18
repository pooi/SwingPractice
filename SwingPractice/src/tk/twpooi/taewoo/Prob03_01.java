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

public class Prob03_01 {

	public static void main(String[] args) {

		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob03_01");
		f.setSize(800, 800);

		Prob03_01Panel panel = new Prob03_01Panel();
		f.add(panel);

		f.setJMenuBar(panel.getMenuBar());

		f.setVisible(true);

	}

}

class Prob03_01Panel extends JPanel implements MouseListener, MouseMotionListener{

	private JLabel numLabel;

	private final static int RECT = 0;
	private final static int CIRCLE = 1;


	private ArrayList<HashMap<String, Object>> list;
	private int colorIndex;
	private Color[] colors = {
			Color.RED,
			Color.GREEN,
			Color.BLUE
	};

	private boolean isFill = true;

	private int shapeIndex;
	private int[] shapes = {
			RECT,
			CIRCLE
	};

	private JMenuBar menuBar;

	Prob03_01Panel(){

		this.list = new ArrayList<>();

		this.setLayout(new BorderLayout());

		numLabel = new JLabel("Num Of Object : " + list.size());
		numLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(numLabel, BorderLayout.NORTH);

		menuBar = new JMenuBar();
		addMenuBar();

		this.addMouseListener(this);
		this.addMouseMotionListener(this);

	}

	private void addMenuBar(){

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

		JMenu fillMenu = new JMenu("Fill");
		ButtonGroup fillGroup = new ButtonGroup();

		JRadioButtonMenuItem itemFill = new JRadioButtonMenuItem("Fill");
		itemFill.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				isFill = true;

			}

		});
		itemFill.setSelected(true);
		fillMenu.add(itemFill);
		fillGroup.add(itemFill);

		JRadioButtonMenuItem itemDraw = new JRadioButtonMenuItem("Draw");
		itemDraw.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				isFill = false;

			}

		});
		fillMenu.add(itemDraw);
		fillGroup.add(itemDraw);

		menuBar.add(fillMenu);


		JMenu shapeMenu = new JMenu("Shape");
		ButtonGroup shapeGroup = new ButtonGroup();

		JRadioButtonMenuItem shapeRect = new JRadioButtonMenuItem("Rect");
		shapeRect.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				shapeIndex = RECT;

			}

		});
		shapeRect.setSelected(true);
		shapeMenu.add(shapeRect);
		shapeGroup.add(shapeRect);

		JRadioButtonMenuItem shapeCircle = new JRadioButtonMenuItem("Circle");
		shapeCircle.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {

				shapeIndex = CIRCLE;

			}

		});
		shapeMenu.add(shapeCircle);
		shapeGroup.add(shapeCircle);

		menuBar.add(shapeMenu);

	}

	public JMenuBar getMenuBar(){
		return menuBar;
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);

		for(HashMap<String, Object> hash : list){

			boolean isFillTemp = (Boolean)hash.get("isFill");
			int shapeTemp = (int)hash.get("shape");

			int x1 = (int)hash.get("x1");
			int y1 = (int)hash.get("y1");
			int x2 = (int)hash.get("x2");
			int y2 = (int)hash.get("y2");
			int colorIndexTemp = (int)hash.get("color");
			Color color = colors[colorIndexTemp];

			if(shapeTemp == RECT){

				int x = Math.min(x1, x2);
				int y = Math.min(y1, y2);
				int w = Math.abs(x1-x2);
				int h = Math.abs(y1-y2);

				if(isFillTemp){
					g.setColor(color);
					g.fillRect(x, y, w, h);
					g.setColor(Color.black);
					g.drawRect(x, y, w, h);
				}else{
					g.setColor(color);
					g.drawRect(x, y, w, h);
				}



			}else if(shapeTemp == CIRCLE){

				int r = (int) Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));

				if(isFillTemp){
					g.setColor(color);
					g.fillOval(x1-r, y1-r, r*2, r*2);
					g.setColor(Color.black);
					g.drawOval(x1-r, y1-r, r*2, r*2);
				}else{
					g.setColor(color);
					g.drawOval(x1-r, y1-r, r*2, r*2);
				}

			}




		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		HashMap<String, Object> temp = list.get(list.size()-1);
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
		HashMap<String, Object> temp = new HashMap<>();
		temp.put("x1", e.getX());
		temp.put("y1", e.getY());
		temp.put("x2", e.getX());
		temp.put("y2", e.getY());
		temp.put("shape", shapeIndex);
		temp.put("isFill", isFill);
		temp.put("color", colorIndex);
		list.add(temp);
		numLabel.setText("Num Of Object : " + list.size());
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}