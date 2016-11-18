package tk.twpooi.taewoo;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class Prob07 {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob07");
		f.setSize(800, 500);
		
		Prob07Panel panel = new Prob07Panel();
		f.add(panel);
		
		f.setVisible(true);

	}

}

class Prob07Panel extends JPanel{
	
	private int time;
	
	private JLabel label = new JLabel("a");
	
	Prob07Panel(){
		
		this.setLayout(new BorderLayout());
		
		JPanel btnPanel = new JPanel();
		
		JButton hour = new JButton("Hour");
		hour.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				time += 3600;
				label.setText(getTimeText());
			}
			
		});
		btnPanel.add(hour);
		
		JButton min = new JButton("Min");
		min.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				time += 60;
				label.setText(getTimeText());
			}
			
		});
		btnPanel.add(min);
		
		JButton sec = new JButton("Sec");
		sec.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				time += 1;
				label.setText(getTimeText());
			}
			
		});
		btnPanel.add(sec);
		
		Font font = new Font(this.getFont().getName(), this.getFont().getStyle(), 150);
		label.setFont(font);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		
		this.add(btnPanel, BorderLayout.NORTH);
		this.add(label, BorderLayout.CENTER);
		
		new Thread(){
			public void run(){
				
				while(true){

					label.setText(getTimeText());

					try{
						Thread.sleep(1000);
						time += 1;
					}catch(Exception e){}
					
				}
				
			}
		}.start();
		
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