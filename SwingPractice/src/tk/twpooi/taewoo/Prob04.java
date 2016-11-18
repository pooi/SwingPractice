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

public class Prob04 {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob04");
		f.setSize(500, 500);
		
		Prob04Panel panel = new Prob04Panel();
		f.add(panel);
		
		f.setVisible(true);

	}

}

class Prob04Panel extends JPanel implements MouseListener, MouseMotionListener{
	
	private JLabel numLabel;
	
	private ArrayList<HashMap<String, Object>> list;
	private ArrayList<Integer> innerIndexList;
	
	Prob04Panel(){
		
		this.list = new ArrayList<>();
		this.innerIndexList = new ArrayList<>();
		
		this.setLayout(new BorderLayout());
		
		numLabel = new JLabel("Num Of Circles : " + list.size());
		numLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(numLabel, BorderLayout.NORTH);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		for(HashMap<String, Object> hash : list){
			
			int x1 = (int)hash.get("x1");
			int y1 = (int)hash.get("y1");
			int x2 = (int)hash.get("x2");
			int y2 = (int)hash.get("y2");
			Color color = (Color)hash.get("color");

			int r = (int)Math.sqrt(Math.pow((double)x1-x2, 2) + Math.pow((double)y1-y2, 2));
			
			int x = x1-r;
			int y = y1-r;
			
			g.setColor(color);
			g.fillOval(x, y, r*2, r*2);
			g.setColor(Color.black);
			g.drawOval(x, y, r*2, r*2);
			
		}
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		if(innerIndexList.size() == 0){ // 원이 선택되어진 상황이 아니라면
			HashMap<String, Object> temp = list.get(list.size()-1);
			temp.put("x2", e.getX());
			temp.put("y2", e.getY());
			list.set(list.size()-1, temp);
			repaint();
		}else{
			int ex = e.getX();
			int ey = e.getY();
			
			for(Integer index : innerIndexList){
				
				HashMap<String, Object> temp = list.get(index);
				
				int x1 = (int)temp.get("x1");
				int y1 = (int)temp.get("y1");
				int x2 = (int)temp.get("x2");
				int y2 = (int)temp.get("y2");
				
				int x3 = (int)temp.get("x3");
				int y3 = (int)temp.get("y3");
				
				int rx = ex - x3;
				int ry = ey - y3;
				
				x1 += rx;
				x2 += rx;
				x3 += rx;
				y1 += ry;
				y2 += ry;
				y3 += ry;
				
				temp.put("x1", x1);
				temp.put("y1", y1);
				temp.put("x2", x2);
				temp.put("y2", y2);
				temp.put("x3", x3);
				temp.put("y3", y3);
				
				temp.put("color", Color.yellow);
				
				list.set(index, temp);
				
			}
			
			repaint();
		}
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
		
		innerIndexList.clear();
		for(int i=0; i<list.size(); i++){ // 마우스를 클릭한 위치에 원이 이미 그려져 있는지 확인
			HashMap<String, Object> temp = list.get(i);
			
			int x1 = (int)temp.get("x1");
			int y1 = (int)temp.get("y1");
			int x2 = (int)temp.get("x2");
			int y2 = (int)temp.get("y2");
			
			int r = (int)Math.sqrt(Math.pow((double)x1-x2, 2) + Math.pow((double)y1-y2, 2));
			
			if((int)Math.sqrt(Math.pow((double)x1-e.getX(), 2) + Math.pow((double)y1-e.getY(), 2)) <= r){
				innerIndexList.add(i);
			}
			
		}
		
		if(innerIndexList.size() == 0){ // 이미 그려진 원이 없다면 새로운 원을 추가
			HashMap<String, Object> temp = new HashMap<>();
			temp.put("x1", e.getX());
			temp.put("y1", e.getY());
			temp.put("x2", e.getX());
			temp.put("y2", e.getY());
			temp.put("color", Color.RED);
			list.add(temp);
			numLabel.setText("Num Of Rects : " + list.size());
			repaint();
		}else{ // 이미 그려진 원이 있다면
			
			int ex = e.getX();
			int ey = e.getY();
			
			for(Integer index : innerIndexList){
				
				HashMap<String, Object> temp = list.get(index);
				
				temp.put("x3", ex);
				temp.put("y3", ey);
				
				temp.put("color", Color.yellow);
				
				list.set(index, temp);
				
			}
			
			repaint();
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int index : innerIndexList){
			list.get(index).put("color", Color.red);
		}
		innerIndexList.clear();
		repaint();
		
	}
	
}