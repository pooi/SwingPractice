package tk.twpooi.taewoo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Prob04_02 {

	public static void main(String[] args) {

		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob04_02");
		f.setSize(500, 500);

		Prob04_02Panel panel = new Prob04_02Panel();
		f.add(panel);

		f.setVisible(true);

	}

}

class Prob04_02Panel extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener{
	
	private JLabel numLabel;
	
	private ArrayList<HashMap<String, Object>> list;
	private ArrayList<Integer> innerIndexList;
	
	boolean isDrawing;
	int x1, x2, y1, y2;
	
	Prob04_02Panel(){
		
		this.list = new ArrayList<>();
		this.innerIndexList = new ArrayList<>();
		
		this.setLayout(new BorderLayout());
		
		numLabel = new JLabel("Num Of Circles : " + list.size());
		numLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(numLabel, BorderLayout.NORTH);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		
	}
	
	private void addCircle(int x, int y, int r){
		
		HashMap<String, Object> temp = new HashMap<>();
		
		
		temp.put("x", (double)x/getSize().getWidth());
		temp.put("y", (double)y/getSize().getHeight());
		temp.put("color", Color.red);
		temp.put("r", (double)r/getSize().getWidth());
		
		list.add(temp);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
		for(HashMap<String, Object> hash : list){
			
			int x = (int) ((double)hash.get("x")*getSize().getWidth());
			int y = (int) ((double)hash.get("y")*getSize().getHeight());
			Color color = (Color)hash.get("color");

			int r = (int) ((double)hash.get("r")*getSize().getWidth());
			
			g.setColor(color);
			g.fillOval(x-r, y-r, r*2, r*2);
			g.setColor(Color.black);
			g.drawOval(x-r, y-r, r*2, r*2);
			
		}
		
		if(isDrawing){

			int r = (int) Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
			
			g.setColor(Color.red);
			g.fillOval(x1-r, y1-r, r*2, r*2);
			g.setColor(Color.black);
			g.drawOval(x1-r, y1-r, r*2, r*2);
			
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(innerIndexList.size() == 0){ // 원이 선택되어진 상황이 아니라면
			
			x2 = e.getX();
			y2 = e.getY();
			
			repaint();
			
		}else{
			double ex = (double)e.getX()/getSize().getWidth();
			double ey = (double)e.getY()/getSize().getHeight();
			
			for(Integer index : innerIndexList){
				
				HashMap<String, Object> temp = list.get(index);

				double x = (double)temp.get("x");
				double y = (double)temp.get("y");
				
				double x3 = (double)temp.get("x3");
				double y3 = (double)temp.get("y3");
				
				double rx = ex - x3;
				double ry = ey - y3;
				
				x += rx;
				x3 += rx;
				
				y += ry;
				y3 += ry;
				
				temp.put("x", x);
				temp.put("y", y);
				temp.put("x3", x3);
				temp.put("y3", y3);
				
				temp.put("color", Color.yellow);
				
				list.set(index, temp);
				
			}
			
			repaint();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		innerIndexList.clear();
		for(int i=0; i<list.size(); i++){ // 마우스를 클릭한 위치에 원이 이미 그려져 있는지 확인
			HashMap<String, Object> temp = list.get(i);
			
			int x = (int) ((double)temp.get("x")*getSize().getWidth());
			int y = (int) ((double)temp.get("y")*getSize().getHeight());

			int r = (int) ((double)temp.get("r")*getSize().getWidth());
			
			if((int)Math.sqrt(Math.pow((double)x-e.getX(), 2) + Math.pow((double)y-e.getY(), 2)) <= r){
				innerIndexList.add(i);
			}
			
		}
		
		if(innerIndexList.size() == 0){ // 이미 그려진 원이 없다면 새로운 원을 추가
			
			isDrawing = true;
			x1 = x2 = e.getX();
			y1 = y2 = e.getY();
			
			repaint();
			
		}else{ // 이미 그려진 원이 있다면
			
			int ex = e.getX();
			int ey = e.getY();
			
			for(Integer index : innerIndexList){
				
				HashMap<String, Object> temp = list.get(index);
				
				temp.put("x3", (double)ex/getSize().getWidth());
				temp.put("y3", (double)ey/getSize().getHeight());
				
				temp.put("color", Color.yellow);
				
				list.set(index, temp);
				
			}
			
			repaint();
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(isDrawing){

			int r = (int) Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2));
			
			addCircle(x1, y1, r);
			
			isDrawing = false;
			
		}else{

			for(int index : innerIndexList){
				list.get(index).put("color", Color.red);
			}
			innerIndexList.clear();
			
		}
		
		numLabel.setText("Num Of Circles : " + list.size());
		
		repaint();
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		
		for(int i : innerIndexList){

			HashMap<String, Object> temp = list.get(i);

			int r = (int) ((double)temp.get("r")*getSize().getWidth());
			r += (e.getWheelRotation() * -2);
			
			if(r<5){
				r = 5;
			}
			
			temp.put("r", (double)r/getSize().getWidth());

			list.set(i, temp);

		}

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
	
}