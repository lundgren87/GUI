import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;


public class TimeManagerView {
	
	private JFrame mainFrame = new JFrame("Time Manager");
	private JPanel titelPanel = new JPanel(); 	//The panel which shows the titel of the current tab
	private JPanel addPanel = new JPanel();		//The panel where one can add a new task
	
	private JTabbedPane categotoryTab = new JTabbedPane(JTabbedPane.LEFT); //The tabPanel
	
	private JButton addButton =  new JButton("Add");	//The button to add a new task
	private JButton logoutButton = new JButton("Log Out");	//The  button to log out
	
	TimeManagerView(){	
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		
		//Content titlePanel 
		titelPanel.setBackground(Color.red);
		titelPanel.setLayout(new GridLayout(1, 2));
		titelPanel.add(new JLabel("Titel"));
		titelPanel.add(logoutButton);
		
		//Content addPanel
		addPanel.setBackground(Color.yellow);
		addPanel.add(addButton);
		
		//Content tab
		//mainPanel.setBackground(Color.blue);
		categotoryTab.addTab("Work", null, new JPanel(), "Show work related tasks");
		categotoryTab.addTab("School", null, new JPanel(), "Show school related tasks");
		categotoryTab.addTab("House", null, new JPanel(), "Show house related tasks");
		categotoryTab.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		
		//Content addPanel
		JTextArea nameActivity = new JTextArea("Name activity"); 
		String input = nameActivity.getText();
		//addPanel.add(new )
		
		mainFrame.getContentPane().add(BorderLayout.NORTH, titelPanel);
		mainFrame.getContentPane().add(BorderLayout.CENTER, categotoryTab);
		mainFrame.getContentPane().add(BorderLayout.SOUTH, addPanel);
		
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//mainFrame.setSize( JFrame.MAXIMIZED_VERT, JFrame.MAXIMIZED_HORIZ);
		mainFrame.setVisible(true);
	}

}
