import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;


public class TimeManagerView {
	
	private JFrame frame = new JFrame("Time Manager");
	private JButton addButton =  new JButton("Add");
	
	TimeManagerView(){	

		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.getContentPane().add(BorderLayout.SOUTH, addButton);
		frame.setSize(700, 700);
		frame.setVisible(true);
	}

}
