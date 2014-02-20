package TimeManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


/**
 * The view of the timemanager. The main frame and all panels are made here. 
 * @author Kim, Pontus, Aries, Sercan
 *
 */
public class TimeManagerView implements Observer {
	
	public JFrame mainFrame = new JFrame("Time Manager");	//The main frame. 
	private JPanel titelPanel = new JPanel(); 	//The panel which shows the title of the current tab
	private JPanel addPanel = new JPanel();		//The panel where one can add a new task
	private JPanel centerPanel;
	private JPanel tabPanel; 		// Left part of the centerPanel
	private TaskPanel taskPanel;	// Right part of the rightPanel
	private List<TaskPanel> taskPanels;			// One TaskPanel for each category
	
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
		
		makeCenterPanel();
		
		addPanel = MakeAddPanel(addPanel);
		
		//Layout of the titelpanel, tabpannel and addpanel. 
		mainFrame.getContentPane().add(BorderLayout.NORTH, titelPanel);
		mainFrame.getContentPane().add(BorderLayout.CENTER, centerPanel);
		mainFrame.getContentPane().add(BorderLayout.SOUTH, addPanel);
		
		mainFrame.setPreferredSize(new Dimension(1024, 768));
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//mainFrame.setSize( JFrame.MAXIMIZED_VERT, JFrame.MAXIMIZED_HORIZ);
		
	}

	/**
	 * makes a tabnedpane
	 * @return tabpane
	 */
	private void makeCenterPanel() {
		// Create empty tabPanel
		tabPanel = new JPanel();
		tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.Y_AXIS));
		tabPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		tabPanel.setPreferredSize(new Dimension(88,500));
		
		// Create empty taskPanel and its list
		taskPanels = new ArrayList<TaskPanel>();
		taskPanel = new TaskPanel();
		
		centerPanel = new JPanel();
		centerPanel.add(BorderLayout.WEST, tabPanel);
		centerPanel.add(BorderLayout.EAST, taskPanel);
	}
	
	/**
	 * loads categories
	 * @param taskCategories list of categories
	 */
	public void loadCategories(List<TaskCategory> taskCategories) {
		// remove all existing tabs, then load everything
		tabPanel.removeAll();
		for(TaskCategory category : taskCategories) {
			// Generate icons for each category and add it to the tab panel (left side)
			JButton btn = new JButton(category.icon);
			btn.setName(category.categoryName);
			btn.setToolTipText(category.categoryDescription);
			btn.setAlignmentX(Component.CENTER_ALIGNMENT);
			// make the button look like icon
			btn.setContentAreaFilled(false);
			btn.setFocusPainted(false);
			//btn.setMargin(new Insets(0, 0, 0, 0));
			//btn.setOpaque(false);
			//btn.setBorderPainted(false);
			//btn.setBackground(null);
			
			//TODO: Move actionlistener code to somewhere else
			btn.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {
			    	JComponent b = (JComponent) e.getSource();
			    	switchTab(b.getName());
			    }
			});   
			tabPanel.add(btn);
		
			// Generate panel to contain tasks for each category (right side)
			TaskPanel tp = new TaskPanel(category);
			taskPanels.add(tp);
		}
		// when categories changed, switch back to all category
		// TODO: switch to the new category or last active category instead
		switchTab("house");
	}
	
	public void switchTab(String selectedCategory) {
		try {
			centerPanel.remove(taskPanel);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		// TODO: use hashmap?
		for(TaskPanel tp : taskPanels) {
			if(tp.taskCategory.categoryName.equalsIgnoreCase(selectedCategory)) {
				taskPanel = tp;
			}
		}
		centerPanel.add(BorderLayout.EAST, taskPanel);
		centerPanel.validate();
		centerPanel.repaint();
		
		try {
			System.out.println("Switching to " + taskPanel.taskCategory.categoryName);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * load tasks
	 * @param taskItems list of taskItems
	 */
	public void loadTasks(List<TaskItem> taskItems) {
		// remove all existing tasks, then load everything
		for(TaskPanel tp : taskPanels) {
			tp.removeAllTasks();
		}
		
		for(TaskItem item : taskItems) {
			// TODO: use hashmap?
			for(TaskPanel tp : taskPanels) {
				if(tp.taskCategory.categoryName.equalsIgnoreCase(item.taskCategory) || 
					tp.taskCategory.categoryName.equalsIgnoreCase("all_categories")
				) {
					tp.addTask(item);
				}
			}
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
