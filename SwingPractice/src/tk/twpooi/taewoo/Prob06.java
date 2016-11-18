package tk.twpooi.taewoo;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Prob06 {

	public static void main(String[] args) {
		
		JFrame f = new JFrame();

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Prob05");
		f.setSize(800, 500);
		
		Prob06Panel panel = new Prob06Panel();
		f.add(panel);
		
		f.setVisible(true);

	}

}

class Prob06Panel extends JPanel implements ActionListener{
	
	private JTextField textField;
	private JTextArea textArea;
	
	Prob06Panel(){
		
		this.setLayout(new BorderLayout());
		
		textField = new JTextField(20);
		textField.addActionListener(this);
		this.add(textField, BorderLayout.NORTH);
		
		textArea = new JTextArea();
		this.add(textArea, BorderLayout.CENTER);
		
		JLabel info = new JLabel("Type At the Above TextField");
		this.add(info, BorderLayout.SOUTH);
		
		
	}
	
	public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == textField)
        {
            String tmpStr = textField.getText();
            textArea.append(tmpStr+"\n");
            textField.setText("");
            textField.requestFocus();
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }
	
}