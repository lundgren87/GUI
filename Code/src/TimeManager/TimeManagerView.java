package TimeManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
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
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;


public class TimeManagerView implements Observer {
	
	public JFrame mainFrame = new JFrame("Time Manager");
	private JPanel titelPanel = new JPanel(); 	//The panel which shows the title of the current tab
	private JPanel mainPanel = new JPanel();
	private JPanel addPanel = new JPanel();		//The panel where one can add a new task
	
	private JTabbedPane tabPanel; //The tabPanel
	GridBagConstraints gridBagConstraint;
	
	private JButton addButton =  new JButton("Add");		//The button to add a new task
	private JButton logoutButton = new JButton("Log Out");	//The  button to log out
	
	private Calendar startDate = Calendar.getInstance();	//Selects today's date. 
	
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
		MakeAddPanel(addPanel);
		
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
		
		//Add actions to the menus
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

	private void MakeAddPanel(JPanel addPanel) {
		this.addPanel = addPanel;
		addPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Make and add NameActivity
		final JTextField nameActivity = new JTextField(); 		//texfield to insert the name of the activity
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		nameActivity.setBorder(BorderFactory.createTitledBorder
	            ("Description of activity"));
		addPanel.add(nameActivity,c);
		
		//Make a datepanel
		JPanel datePanel = new JPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;	
		datePanel.setBorder(BorderFactory.createTitledBorder("Due Date"));
		addPanel.add(datePanel,c);

		datePanel.setLayout(new GridLayout(0,4));
				//Make an date chooser, add this to the datePanel
				final JComboBox startYear;
				final JComboBox startMonth;
				final JComboBox startDay;
		       	startYear = new JComboBox();
		        buildYearsList(startYear);
		        startYear.setSelectedIndex(5);
		        startMonth = new JComboBox();
		        buildMonthsList(startMonth);
		        startMonth.setSelectedIndex(startDate.get(Calendar.MONTH));
		        startDay = new JComboBox();
		        buildDaysList(startDate, startDay, startMonth);
		        startDay.setSelectedItem(Integer.toString(startDate.get(Calendar.DATE)));
		        datePanel.add(startDay);
		        datePanel.add(startMonth);
		        datePanel.add(startYear);
			
				//make an time spinner, add this to the datePanel
				final JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
				final JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
				timeSpinner.setEditor(timeEditor);
				timeSpinner.setValue(new Date()); 
				datePanel.add(timeSpinner);
		
		//Make and add dropdown menu to choose category		
		String[] categoryStrings= {"Home", "School", "Work", "All"};			//This should request the list of the category's. 
		final JComboBox dropdownCategory = new JComboBox(categoryStrings);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 0.45;
		dropdownCategory.setBorder(BorderFactory.createTitledBorder("Category"));
		addPanel.add(dropdownCategory,c);
		
		//Make and add priorityPanel.
		JPanel priorityPanel = new JPanel();
			final JRadioButton highPriority = new JRadioButton("High");
			final JRadioButton mediumPriority = new JRadioButton("Medium");
			final JRadioButton lowPriority = new JRadioButton("Low");
				
			final ButtonGroup bg = new ButtonGroup();			//Group the buttons
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
		c.weightx = 0.45;
		priorityPanel.setBorder(BorderFactory.createTitledBorder("Priority"));
		addPanel.add(priorityPanel,c);
		
		//add the addButton 
		Font font = new Font(null, Font.BOLD, 20);				//makes the font of the activity big and bold
		addButton.setFont(font);
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 50;
		c.ipadx = 100;
		c.gridx = 3;
		c.gridy = 0;
		c.gridheight = 3;
		c.weightx = 0.1;
		addPanel.add(addButton,c);
		
		//Get the name of the activity (should also get the date, category, and priority)
		addButton.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		       String message;
		       String priority;
		       String date;
		       String time;
		    	 if(nameActivity.getText().length()>4){
		    		if(highPriority.isSelected()){
		        			priority = "***";
		        			}
		    		else if(lowPriority.isSelected()){
		    			 priority = "*";
		    			 }
		        	else
		        			priority = "**";
		    		
		    		date = startDay.getSelectedItem().toString() + " " +  
		    				startMonth.getSelectedItem().toString() + " " +
		    				startYear.getSelectedItem().toString();
		    		
		    		time = timeEditor.getFormat().format(timeSpinner.getValue());
		    		
		    		 
		    		 message = String.format(
		    			"Activity name = " + nameActivity.getText() + 
		        		"\nCategory = " + dropdownCategory.getSelectedItem().toString() +
		        		"\nPriority = " + priority +
		        		"\nDate = " + date + 
		        		"\nTime = "+ time + '\n'
		    			);   	 
		    	 }
		    	 else 
		    		 message = "Give a valid name to the task. \nIt should include at least 5 characters"; //one should actually give a popup	  
		    	 
		    	 System.out.println(message);}
		    	 
		      
		    });
	}
	
	/**
	 * Provides a list of the upcomming 5 years, set on the current year
	 * @param yearsList combobox which is gonna be filled
	 */
	private void buildYearsList(JComboBox yearsList) {
        int currentYear = startDate.get(Calendar.YEAR);

        for (int yearCount = currentYear - 5; yearCount <= currentYear + 5; yearCount++)
            yearsList.addItem(Integer.toString(yearCount));
    }
	
	/**
	 * Provides a list of the all months
	 * @param monthsList combobox which is gonna be filled
	 */
	 private void buildMonthsList(JComboBox monthsList) {
	        monthsList.removeAllItems();
	        String[] monthStrings= {"January", "February", "March", "April", "May", "June" , "July", "August", "September", "October", "November","December"};			//This should request the list of the category's. 
	        for (int monthCount = 0; monthCount < 12; monthCount++)
	            monthsList.addItem(monthStrings[monthCount]);
	    }
	 
	 /**
	  * Provides a list of all days, starting on the current date
	  * @param dateIn today's date
	  * @param daysList combobox which is gonna be filled, starting on the current day 
	  * @param monthsList combobos which is gonna be set on the current month. 
	  */
	 private void buildDaysList(Calendar dateIn, JComboBox daysList, JComboBox monthsList) {
	        daysList.removeAllItems();
	        dateIn.set(Calendar.MONTH, monthsList.getSelectedIndex());
	        int lastDay = startDate.getActualMaximum(Calendar.DAY_OF_MONTH);

	        for (int dayCount = 1; dayCount <= lastDay; dayCount++)
	            daysList.addItem(Integer.toString(dayCount));
	    }
}

