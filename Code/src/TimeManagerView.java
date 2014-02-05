import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class TimeManagerView {
	
	private JFrame mainFrame = new JFrame("Time Manager");
	private JPanel titelPanel = new JPanel(); 	//The panel which shows the titel of the current tab
	private JPanel mainPanel = new JPanel();	//The panel where the tasks are shown
	private JPanel tabPanel = new JPanel();		//The panel where different sortings are shown (as tabs)
	private JPanel addPanel = new JPanel();		//The panel where one can add a new task
	
	private JButton addButton =  new JButton("Add");	//The button to add a new task
	private JButton logoutButton = new JButton("Log Out");	//The  button to log out
	
	TimeManagerView(){	
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		
		//temporary try out of titel pannel

		titelPanel.setLayout(new GridLayout(1, 2));
		titelPanel.add(new JLabel("Titel"));
		titelPanel.add(logoutButton);
		
		mainFrame.getContentPane().add(BorderLayout.NORTH, titelPanel);
		mainFrame.getContentPane().add(BorderLayout.WEST, tabPanel);
		mainFrame.getContentPane().add(BorderLayout.SOUTH, addButton);
		mainFrame.getContentPane().add(BorderLayout.CENTER, mainPanel);
		
		mainFrame.setSize(700, 700);
		mainFrame.setVisible(true);
	}

}
