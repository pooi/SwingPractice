package tk.twpooi.taewoo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class prob10 {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();
		f.setSize(1000, 800);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Prob10Panel panel = new Prob10Panel();
		f.add(panel);
		
		f.setVisible(true);

	}

}

class Prob10Panel extends JPanel implements MouseMotionListener, MouseWheelListener{
	
	BufferedImage img;
	
	int x, y;
	int d = 50;
	
	Prob10Panel(){
		
		try{
			img = ImageIO.read(new File("lena.jpg"));
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		this.addMouseMotionListener(this);
		this.addMouseWheelListener(this);
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		g.drawImage(img, 0, 0, null);
		
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(3));
		g2d.setColor(Color.RED);
		g2d.drawRect(x-d, y-d, d*2, d*2);
		
		g2d.drawImage(img, img.getWidth(), 0, img.getWidth()+300, 300, x-d, y-d, x+d, y+d, null);
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		x = e.getX();
		y = e.getY();
		
		if(x<d){
			x = d;
		}
		if(x>img.getWidth()-d){
			x = img.getWidth()-d;
		}
		if(y<d){
			y = d;
		}
		if(y>img.getHeight()-d){
			y = img.getHeight()-d;
		}
		
		repaint();
		
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		// TODO Auto-generated method stub
		if(e.getWheelRotation() < 0){
			d-=1;
			if(d < 1){
				d = 1;
			}
		}else{
			d+=1;
			if(d>img.getWidth()/2){
				d = img.getWidth()/2;
			}
			
		}
		
		x = e.getX();
		y = e.getY();
		
		if(x<d){
			x = d;
		}
		if(x>img.getWidth()-d){
			x = img.getWidth()-d;
		}
		if(y<d){
			y = d;
		}
		if(y>img.getHeight()-d){
			y = img.getHeight()-d;
		}
		
		repaint();
	}
	
}