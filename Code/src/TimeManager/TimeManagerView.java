package TimeManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class TimeManagerView implements Observer {
	
	public JFrame mainFrame = new JFrame("Time Manager");
	private JPanel titelPanel = new JPanel(); 	//The panel which shows the title of the current tab
	private JPanel mainPanel = new JPanel();
	private JPanel addPanel = new JPanel();		//The panel where one can add a new task
	
	private JTabbedPane tabPanel; //The tabPanel
	
	private JButton addButton =  new JButton("Add");	//The button to add a new task
	private JButton logoutButton = new JButton("Log Out");	//The  button to log out
	
	TimeManagerView(){	
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		
		//Content titlePanel 
		titelPanel.setBackground(Color.red);
		titelPanel.setLayout(new GridLayout(1, 5));
		titelPanel.add(new JLabel("Title"));
		titelPanel.add(logoutButton);

		//Content tab
		tabPanel = makeTabPanel();
		mainPanel.add(tabPanel);

		//mainPanel.setBackground(Color.blue);
		
		addPanel = MakeAddPanel(addPanel);
		
		mainFrame.getContentPane().add(BorderLayout.NORTH, titelPanel);
		mainFrame.getContentPane().add(BorderLayout.CENTER, tabPanel);
		mainFrame.getContentPane().add(BorderLayout.SOUTH, addPanel);
		
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//mainFrame.setSize( JFrame.MAXIMIZED_VERT, JFrame.MAXIMIZED_HORIZ);
		
	}

	private JTabbedPane makeTabPanel() {
		JTabbedPane tab = new JTabbedPane(JTabbedPane.LEFT);
		tab.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		return tab;
	}
	
	public void loadCategories(List<TaskCategory> taskCategories) {
		// remove all existing tabs, then load everything
		tabPanel.removeAll();
		
		for(TaskCategory category : taskCategories) {
			JPanel taskPanel = new JPanel();
			taskPanel.setLayout(new BoxLayout(taskPanel, BoxLayout.PAGE_AXIS));
			tabPanel.addTab(category.categoryName, category.icon, taskPanel, category.categoryDescription);
		}
	
	}
	
	public void loadTasks(List<TaskItem> taskItems) {
		// remove all existing tasks, then load everything
		removeAllTasks();

		for(TaskItem item : taskItems) {
			//TODO: Add priority, date, etc as arguments
			int tabIndex = tabPanel.indexOfTab(item.taskCategory);
			JPanel taskPanel = (JPanel) tabPanel.getComponent(tabIndex);
			JComponent task = new JButton(item.taskDescripton + " priority : " + item.taskPriority);
			taskPanel.add(task);
		}
	}
	
	public void removeAllTasks() {
		// remove all existing task from all tabs
		int numberOfTabs = tabPanel.getTabCount();
		for(int i=0;i<numberOfTabs;i++) {
			JPanel panelToClear = (JPanel) tabPanel.getComponent(i);
			panelToClear.removeAll();
		}
	}
	
	@Override
	public void update(Observable o, Object arg) {
		List list = (List) arg;
		if(list.get(0) instanceof TaskCategory) {
			loadCategories(list);
		}
		else {
			loadTasks(list);
		}
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
