package tk.twpooi.taewoo;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Prob08 {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob08");
		f.setSize(800, 500);
		
		Prob08Panel panel = new Prob08Panel(f);
		f.add(panel);
		
		f.setVisible(true);

	}

}

class Prob08Panel extends JPanel{
	
	private JFrame parent;
	
	private int time;
	
	private JLabel label = new JLabel("a");
	
	private JButton toggleBtn;
	
	Prob08Panel(JFrame parent){
		
		this.parent = parent;
		
		this.setLayout(new BorderLayout());
		
		JPanel btnPanel = new JPanel();
		
		JButton hour = new JButton("Hour");
		hour.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				time += 3600;
				label.setText(getTimeText());
				repaint();
			}
			
		});
		btnPanel.add(hour);
		
		JButton min = new JButton("Min");
		min.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				time += 60;
				label.setText(getTimeText());
				repaint();
			}
			
		});
		btnPanel.add(min);
		
		JButton sec = new JButton("Sec");
		sec.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				time += 1;
				label.setText(getTimeText());
				repaint();
			}
			
		});
		btnPanel.add(sec);
		
		toggleBtn = new JButton("Digital Clock");
		toggleBtn.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(toggleBtn.getText().equals("Digital Clock")){
					toggleBtn.setText("Round Clock");
					label.setVisible(false);
				}else{
					toggleBtn.setText("Digital Clock");
					label.setVisible(true);
				}
				
				repaint();
				
			}
			
		});
		btnPanel.add(toggleBtn);
		
		
		Font font = new Font(this.getFont().getName(), this.getFont().getStyle(), 150);
		label.setFont(font);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.add(btnPanel, BorderLayout.NORTH);
		this.add(label, BorderLayout.CENTER);
		
		new Thread(){
			public void run(){
				
				while(true){

					label.setText(getTimeText());

					repaint();
					try{
						Thread.sleep(1000);
						time += 1;
					}catch(Exception e){}
					
				}
				
			}
		}.start();
		
	}
	
	private int calculateHorizontalOffset(int angle, int radius) {
        return (int) Math.round(Math.cos(Math.toRadians(angle)) * radius);
    }

    private int calculateVerticalOffset(int angle, int radius) {
        return (int) Math.round(Math.sin(Math.toRadians(angle)) * radius);
    }
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		if(this.toggleBtn.getText().equals("Round Clock")){
			
			int width = parent.getWidth();
			int height = parent.getHeight();
			int r = Math.min(width, height)/2 - 50;
			
			int cx = width/2;
			int cy = height/2;
			
			Graphics2D g2d = (Graphics2D)g;
			g2d.setStroke(new BasicStroke(2));

			g2d.setColor(Color.white);
			g2d.fillOval(cx-r, cy-r, r*2, r*2);
			g2d.setColor(Color.black);
			g2d.drawOval(cx-r, cy-r, r*2, r*2);
			
			g2d.setColor(Color.black);
			for(int i=0; i<=60; i++){
				
				int offsetX, offsetY;
				
				offsetX = calculateHorizontalOffset(i*6, r);
		        offsetY = calculateVerticalOffset(i*6, r);

				g2d.drawLine(cx, cy, cx + offsetX, cy + offsetY);
				
			}
			
			r -= 10;
			
			g2d.setColor(Color.white);
			g2d.fillOval(cx-r, cy-r, r*2, r*2);
			
			r+= 10;

			g2d.setColor(Color.black);
			for(int i=0; i<=60; i+=5){
				
				int offsetX, offsetY;
				
				offsetX = calculateHorizontalOffset(i*6, r);
		        offsetY = calculateVerticalOffset(i*6, r);

				g2d.drawLine(cx, cy, cx + offsetX, cy + offsetY);
				
			}
			
			r -= 20;
			
			g2d.setColor(Color.white);
			g2d.fillOval(cx-r, cy-r, r*2, r*2);
			
			
			// Second
			g2d.setStroke(new BasicStroke(2));
			g2d.setColor(Color.blue);
			
			int offsetX, offsetY;
			
			offsetX = calculateHorizontalOffset((getSec()-15)*6, r);
	        offsetY = calculateVerticalOffset((getSec()-15)*6, r);

			g2d.drawLine(cx, cy, cx + offsetX, cy + offsetY);
			

			// Minute
			r-=20;
			g2d.setStroke(new BasicStroke(4));
			g2d.setColor(Color.green);
			
			offsetX = calculateHorizontalOffset((getMin()-15)*6, r);
	        offsetY = calculateVerticalOffset((getMin()-15)*6, r);

			g2d.drawLine(cx, cy, cx + offsetX, cy + offsetY);


			// Hour
			r-=40;
			g2d.setStroke(new BasicStroke(6));
			g2d.setColor(Color.red);
			
			offsetX = calculateHorizontalOffset((getHour()*60/12-15)*6, r);
	        offsetY = calculateVerticalOffset((getHour()*60/12-15)*6, r);

			g2d.drawLine(cx, cy, cx + offsetX, cy + offsetY);
			
		}else{
			
		}
		
	}
	
	public int getHour(){
		return time/3600;
	}
	
	public int getMin(){
		return (time-getHour()*3600)/60;
	}
	
	public int getSec(){
		return time%60;
	}
	
	public String getTimeText(){
		
		String text = "";
		
		if(time >= 86400){
			time -= 86400;
		}
		
		int hour = time/3600;
		int min = (time-hour*3600)/60;
		int sec = time%60;

		String hourT;
		if(hour <10){
			hourT = "0" + hour;
		}else{
			hourT = Integer.toString(hour);
		}
		String minT;
		if(min <10){
			minT = "0" + min;
		}else{
			minT = Integer.toString(min);
		}
		String secT;
		if(sec <10){
			secT = "0" + sec;
		}else{
			secT = Integer.toString(sec);
		}

		text = hourT + ":" + minT + ":" + secT;
		
		return text;
		
	}
	
}