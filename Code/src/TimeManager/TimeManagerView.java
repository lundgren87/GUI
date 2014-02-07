package TimeManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;


/**
 * The view of the timemanager. The main frame and all panels are made here. 
 * @author Kim, Pontus, Aries, Sercan
 *
 */
public class TimeManagerView implements Observer {
	
	public JFrame mainFrame = new JFrame("Time Manager");	//The main frame. 
	private JPanel titelPanel = new JPanel(); 	//The panel which shows the title of the current tab
	private JPanel addPanel = new JPanel();		//The panel where one can add a new task
	
	private JTabbedPane tabPanel; 		//The tabPanel
	GridBagConstraints gridBagConstraint;
	
	private JButton addButton =  new JButton("Add");	//The button to add a new task
	private JButton logoutButton = new JButton("Log Out");	//The  button to log out
	
	
	public TimeManagerView(){	
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		
		//Content titlePanel 
		titelPanel.setBackground(Color.red);		//this is a temporary backgroundcolor
		titelPanel.setLayout(new GridLayout(1, 5));
		titelPanel.add(new JLabel("Title"));
		titelPanel.add(logoutButton);				//The logout button should not be that big (or here at all)

		tabPanel = makeTabPanel();
		
		//Set gridBagContraint
		gridBagConstraint = new GridBagConstraints();
		gridBagConstraint.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraint.weightx = 1;
		gridBagConstraint.gridwidth = GridBagConstraints.REMAINDER;
		//gridBagConstraint.anchor = GridBagConstraints.PAGE_START;
		
		addPanel = MakeAddPanel(addPanel);
		
		//Layout of the titelpanel, tabpannel and addpanel. 
		mainFrame.getContentPane().add(BorderLayout.NORTH, titelPanel);
		mainFrame.getContentPane().add(BorderLayout.CENTER, tabPanel);
		mainFrame.getContentPane().add(BorderLayout.SOUTH, addPanel);
		
		mainFrame.setPreferredSize(new Dimension(1024, 768));
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//mainFrame.setSize( JFrame.MAXIMIZED_VERT, JFrame.MAXIMIZED_HORIZ);
		
	}

	/**
	 * makes a tabnedpane
	 * @return tabpane
	 */
	private JTabbedPane makeTabPanel() {
		JTabbedPane tab = new JTabbedPane(JTabbedPane.LEFT);
		tab.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
		return tab;
	}
	
	/**
	 * loads categories
	 * @param taskCategories list of categories
	 */
	public void loadCategories(List<TaskCategory> taskCategories) {
		// remove all existing tabs, then load everything
		tabPanel.removeAll();
		
		for(TaskCategory category : taskCategories) {
			JPanel taskPanel = new JPanel();
			taskPanel.setLayout(new GridBagLayout());
			tabPanel.addTab(category.categoryName, category.icon, taskPanel, category.categoryDescription);
		}
	
	}
	
	/**
	 * load tasks
	 * @param taskItems list of taskItems
	 */
	public void loadTasks(List<TaskItem> taskItems) {
		// remove all existing tasks, then load everything
		removeAllTasks();

		for(TaskItem item : taskItems) {
			//TODO: Add priority, date, etc as arguments
			int tabIndex = tabPanel.indexOfTab(item.taskCategory);
			JComponent taskPanel = (JPanel) tabPanel.getComponent(tabIndex);
			
			JComponent task = new JButton(item.taskDescripton + " priority : " + item.taskPriority);
			taskPanel.add(task, gridBagConstraint);
		}
	}
	
	/**
	 * remove all existing task from all tabs
	 */
	public void removeAllTasks() {
		int numberOfTabs = tabPanel.getTabCount();
		for(int i=0;i<numberOfTabs;i++) {
			JComponent panelToClear = (JPanel) tabPanel.getComponent(i);
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

	/**
	 * fills the addpanel with a button, textarea and combobox
	 * @param addPanel2, the JPannel addPanel
	 * @return addPanel2
	 */
	private JPanel MakeAddPanel(JPanel addPanel2) {
		JTextField nameActivity = new JTextField("Name activity"); 	//texfield to insert the name of the activity
		JComboBox dropdownCategory = new JComboBox();				//dropdown menu
		
		nameActivity.setSize(200, 100);
		
		addPanel2.setLayout(new GridLayout(2, 2));
		
		addPanel2.add(nameActivity);
		addPanel2.add(BorderLayout.CENTER, dropdownCategory);
		addPanel2.add(BorderLayout.SOUTH, addButton);
		return addPanel;
	}

	

}
