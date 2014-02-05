package TimeManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class TimeManagerView {
	
	private JFrame mainFrame = new JFrame("Time Manager");
	private JPanel titelPanel = new JPanel(); 	//The panel which shows the title of the current tab
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
		
		categotoryTab = MakeMainPanel(categotoryTab);
		//Content tab
		//mainPanel.setBackground(Color.blue);
		;
		

		addPanel = MakeAddPanel(addPanel);

		
		mainFrame.getContentPane().add(BorderLayout.NORTH, titelPanel);
		mainFrame.getContentPane().add(BorderLayout.CENTER, categotoryTab);
		mainFrame.getContentPane().add(BorderLayout.SOUTH, addPanel);
		
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//mainFrame.setSize( JFrame.MAXIMIZED_VERT, JFrame.MAXIMIZED_HORIZ);
		mainFrame.setVisible(true);
	}

	private JTabbedPane MakeMainPanel(JTabbedPane categotoryTab2) {
		categotoryTab2.addTab("Work",  null, new JPanel(), "Show work related tasks");
		categotoryTab2.addTab("School", null, new JPanel(), "Show school related tasks");
		categotoryTab2.addTab("House", null, new JPanel(), "Show house related tasks");
		categotoryTab2.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		return categotoryTab2;
	}

	private JPanel MakeAddPanel(JPanel addPanel2) {
		//addPanel2.setPreferredSize(addPanel.getWidth(), 200);
		JTextField nameActivity = new JTextField("Name activity"); 	//texfield to insert the name of the activity
		JComboBox dropdownCategory = new JComboBox();				//dropdown menu
		
		//textbox
		//nameActivity.setLineWrap(false);
		//nameActivity.setSize(addPanel2.getSize().width-30, 10);
		//nameActivity.setSize(200, 10);
		nameActivity.setSize(200, 100);
		
		//Dropdownmenu
		//dropdownCategory.addItem();
		
		addPanel2.setLayout(new GridLayout(2, 2));
		
		addPanel2.add(nameActivity);
		addPanel2.add(BorderLayout.CENTER, dropdownCategory);
		addPanel2.add(BorderLayout.SOUTH, addButton);
		return addPanel;
	}

}
