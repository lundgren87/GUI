package TimeManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;


public class TimeManagerView implements Observer {
	
	public JFrame mainFrame = new JFrame("Time Manager");
	private JPanel titelPanel = new JPanel(); 	//The panel which shows the title of the current tab
	private JPanel mainPanel = new JPanel();
	private JPanel addPanel = new JPanel();		//The panel where one can add a new task
	
	private JTabbedPane tabPanel; //The tabPanel
	GridBagConstraints gridBagConstraint;
	
	private JButton addButton =  new JButton("Add");	//The button to add a new task
	private JButton logoutButton = new JButton("Log Out");	//The  button to log out
	
	TimeManagerView(){	
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.pack();
		addMenuBar(mainFrame); 
		
		//Content titlePanel 
		titelPanel.setBackground(Color.red);
		titelPanel.setLayout(new GridLayout(1, 5));
		titelPanel.add(new JLabel("Title"));
		titelPanel.add(logoutButton);

		//Content tab
		tabPanel = makeTabPanel();
		mainPanel.add(tabPanel);
		
		gridBagConstraint = new GridBagConstraints();
		gridBagConstraint.fill = GridBagConstraints.HORIZONTAL;
		gridBagConstraint.weightx = 1;
		gridBagConstraint.gridwidth = GridBagConstraints.REMAINDER;
		//gridBagConstraint.anchor = GridBagConstraints.PAGE_START;

		//mainPanel.setBackground(Color.blue);
		
		addPanel = MakeAddPanel(addPanel);
		
		mainFrame.getContentPane().add(BorderLayout.NORTH, titelPanel);
		mainFrame.getContentPane().add(BorderLayout.CENTER, tabPanel);
		mainFrame.getContentPane().add(BorderLayout.SOUTH, addPanel);
		
		mainFrame.setPreferredSize(new Dimension(1024, 768));
		mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//mainFrame.setSize( JFrame.MAXIMIZED_VERT, JFrame.MAXIMIZED_HORIZ);
		
	}
	
	private static void addMenuBar(JFrame mainFrame) {
		// Make a menu bar
		JMenuBar menuBar = new JMenuBar();
		// Make menus
		JMenu fileMenu = new JMenu("File");
		JMenu editMenu = new JMenu ("Edit");
		JMenu helpMenu = new JMenu ("Help");
		// Add menus to the menuBar
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		// Make subMenus
		JMenuItem newAction = new JMenuItem("New");
		JMenuItem exitAction = new JMenuItem("Exit");
		JMenuItem cutAction = new JMenuItem("Cut");
		JMenuItem copyAction = new JMenuItem("Copy");
		JMenuItem pasteAction = new JMenuItem("Paste");
		JMenuItem helpAction = new JMenuItem("Help");
		// Add action to newAction
		newAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent h){
				System.out.println("You have clicked new action");
				}
			}
		);
		
		// Add actions to the menus
		fileMenu.add(newAction);
		fileMenu.add(exitAction);
		editMenu.add(cutAction);
		editMenu.add(copyAction);
		editMenu.add(pasteAction);
		helpMenu.add(helpAction);
		
		mainFrame.setJMenuBar(menuBar);	
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
			taskPanel.setLayout(new GridBagLayout());
			tabPanel.addTab(category.categoryName, category.icon, taskPanel, category.categoryDescription);
		}
	
	}
	
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
	
	public void removeAllTasks() {
		// remove all existing task from all tabs
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

	private JPanel MakeAddPanel(JPanel addPanel2) {
		addPanel2.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Make and add NameActivity
		final JTextField nameActivity = new HintTextField("Name activity"); 		//texfield to insert the name of the activity
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		addPanel2.add(nameActivity,c);
		
		//Make and add starting date. This is now a temporary button
		JButton button2 = new JButton("date 1");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;		
		c.weightx = 0.5;
		addPanel2.add(button2,c);
		
		//make and add ending date. This is now a temporary button
		JButton button3 = new JButton ("Date 2");
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		c.weightx = 0.5;
		addPanel2.add(button3,c);
		
		//Make and add dropdown menu to choose category		
		String[] categoryStrings= {"Home", "School", "Work", "All"};			//This should request the list of the category's. 
		final JComboBox dropdownCategory = new JComboBox(categoryStrings);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.0;
		addPanel2.add(dropdownCategory,c);
		
		//Make and add priorityPanel.
		JPanel priorityPanel = new JPanel();
		JRadioButton highPriority = new JRadioButton("High");
		JRadioButton mediumPriority = new JRadioButton("Medium");
		JRadioButton lowPriority = new JRadioButton("Low");
			
		ButtonGroup bg = new ButtonGroup();			//Group the buttons
		bg.add(highPriority);
		bg.add(mediumPriority);
		bg.add(lowPriority);
		
		mediumPriority.setSelected(true);			//Default is medium
		
		priorityPanel.add(highPriority);
		priorityPanel.add(mediumPriority);
		priorityPanel.add(lowPriority);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 2;
		addPanel2.add(priorityPanel,c);
		
		//add the addButton 
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipady = 50;
		c.ipadx = 100;
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 3;
		addPanel2.add(addButton,c);
		
		//Get the name of the activity (should also get the date, category, and priority)
		addButton.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		        String message = String.format("textFieldA='%s'",
		        		nameActivity.getText());
		        System.out.println(message);
		      }
		    });
		return addPanel;
	}

	class HintTextField extends JTextField implements FocusListener {

	  private final String hint;
	  private boolean showingHint;

	  public HintTextField(final String hint) {
	    super(hint);
	    this.hint = hint;
	    this.showingHint = true;
	    super.addFocusListener(this);
	  }

	  @Override
	  public void focusGained(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText("");
	      showingHint = false;
	    }
	  }
	  @Override
	  public void focusLost(FocusEvent e) {
	    if(this.getText().isEmpty()) {
	      super.setText(hint);
	      showingHint = true;
	    }
	  }

	  @Override
	  public String getText() {
	    return showingHint ? "" : super.getText();
	  }

	}
}

