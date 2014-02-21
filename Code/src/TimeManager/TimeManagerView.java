package TimeManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale.Category;

import javax.swing.BorderFactory;
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
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;


/**
 * The view of the timemanager. The main frame and all panels are made here. 
 * @author Kim, Pontus, Aries, Sercan
 *
 */
public class TimeManagerView {
	
	public JFrame mainFrame = new JFrame(config.LanguageRepository.getString("TIME_MANAGER"));	//The main frame. 
	private JPanel titelPanel = new JPanel(); 	//The panel which shows the title of the current tab
	private JPanel addPanel = new JPanel();		//The panel where one can add a new task
	private JPanel centerPanel;
	private JPanel tabPanel; 		// Left part of the centerPanel
	private TaskPanel taskPanel;	// Right part of the rightPanel
	private List<TaskPanel> taskPanels;			// One TaskPanel for each category
	
	private JButton addButton =  new JButton(config.LanguageRepository.getString("ADD"));		//The button to add a new task
	private Calendar startDate = Calendar.getInstance();	//Selects today's date.
	
	private static String currentLanguage;
	
	TimeManagerView(){	
		
		currentLanguage = config.LanguageRepository.getCurrentLanguage();
		mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		mainFrame.pack();
		mainFrame.addComponentListener(new ComponentAdapter(){
			@Override
			public void componentHidden(ComponentEvent e) {
				close();
				((JFrame)(e.getComponent())).dispose();
			}
		});
		
		addMenuBar(mainFrame); 
		
		//Content titlePanel 
		titelPanel.setBackground(Color.LIGHT_GRAY);		//this is a temporary backgroundcolor
		titelPanel.setLayout(new GridLayout(2, 5));

		
		
		makeCenterPanel();
		JScrollPane centerScrollPanel = new JScrollPane();
		centerScrollPanel.getViewport().add(centerPanel);
		MakeAddPanel(addPanel);
		
		//Layout of the titelpanel, tabpannel and addpanel. 
		mainFrame.getContentPane().add(BorderLayout.NORTH, titelPanel);
		mainFrame.getContentPane().add(BorderLayout.CENTER, centerScrollPanel);
		mainFrame.getContentPane().add(BorderLayout.SOUTH, addPanel);
		
		int width = config.Config.loadInt("WindowWidth", 1024);
		int height = config.Config.loadInt("WindowHeight", 600);
		
		mainFrame.setPreferredSize(new Dimension(width, height));
		//mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setSize(width,height);
		
	}
	
	private int closeOperation(){
		config.Config.saveInt("WindowHeight", mainFrame.getHeight());
		config.Config.saveInt("WindowWidth", mainFrame.getWidth());
		
		return JFrame.EXIT_ON_CLOSE;
	}
	
	private void close() {
		closeOperation();
		//TimeManagerModel.closeOperation();
	}
	
	private static void addMenuBar(final JFrame mainFrame) {
		// Make a menu bar
		JMenuBar menuBar = new JMenuBar();
		
		// Make menus
		JMenu fileMenu = new JMenu(config.LanguageRepository.getString("FILE"));
		JMenu editMenu = new JMenu (config.LanguageRepository.getString("EDIT"));
		JMenu helpMenu = new JMenu (config.LanguageRepository.getString("HELP"));
		
		// Add menus to the menuBar
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
		
		// Make subMenus
		JMenuItem newAction = new JMenuItem(config.LanguageRepository.getString("SAVE"));
		JMenuItem loadAction = new JMenuItem(config.LanguageRepository.getString("LOAD"));
		JMenuItem exitAction = new JMenuItem(config.LanguageRepository.getString("EXIT"));
		JMenu languageAction = new JMenu(config.LanguageRepository.getString("LANGUAGE"));
		JMenuItem helpAction = new JMenuItem(config.LanguageRepository.getString("HELP"));
		
		ButtonGroup rbgroup = new ButtonGroup();
		final JRadioButtonMenuItem swedish = new JRadioButtonMenuItem(config.LanguageRepository.getString("SWEDISH"));
		final JRadioButtonMenuItem english = new JRadioButtonMenuItem(config.LanguageRepository.getString("ENGLISH"));
		if(currentLanguage.equals("English"))
			english.setSelected(true);
		else
			swedish.setSelected(true);
		
		rbgroup.add(swedish);
		
		swedish.addActionListener((new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent s) {
				if(swedish.isSelected()){
					config.LanguageRepository.setCurrentLanguage("Swedish");
					System.out.println("You choose Swedish");
				}
				mainFrame.revalidate();
				mainFrame.repaint();
			}
		}));
		
		english.addActionListener((new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(english.isSelected()){
					config.LanguageRepository.setCurrentLanguage("English");
					System.out.println("you choose English");
				}	
				mainFrame.revalidate();
				mainFrame.repaint();			
			}
		}));
		
		
		rbgroup.add(english);
		
		languageAction.add(english);
		languageAction.add(swedish);

		
		// Add action to newAction
		newAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent h){
				System.out.println("You have clicked new action");
				}
			}
		);
		
		//Add actions to the menus
		fileMenu.add(newAction);
		fileMenu.add(loadAction);
		fileMenu.add(exitAction);
		editMenu.add(languageAction);
		
		helpMenu.add(helpAction);
		
		mainFrame.setJMenuBar(menuBar);	
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
		switchTab("all_categories");
	}
	JLabel CategoryAsTitle = new JLabel (" ",JLabel.CENTER);
	public void switchTab(String selectedCategory) {
		
		CategoryAsTitle.setText(selectedCategory);
		titelPanel.add(CategoryAsTitle);
		
		try {
			centerPanel.remove(taskPanel);
			
		}
		catch (Exception e) {
			//e.printStackTrace();
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
			//System.out.println("Switching to " + taskPanel.taskCategory.categoryName);
		}
		catch(Exception e) {
			//e.printStackTrace();
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
				if(tp.taskCategory.categoryName.equalsIgnoreCase(item.taskCategory)) {
					tp.addTask(item); 
				}
				if(tp.taskCategory.categoryName.equalsIgnoreCase("all_categories")) {
					tp.addTask(item);
				}
			}
		}
	}

	private void MakeAddPanel(JPanel addPanel) {
		this.addPanel = addPanel;
		addPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Make and add NameActivity
		final JTextArea nameActivity = new JTextArea(3,1); 		//TextArea to insert the name of the activity
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = addPanel.getWidth()-1;
		nameActivity.setBorder(BorderFactory.createTitledBorder
	            (config.LanguageRepository.getString("DESC_ACTIV")));
		addPanel.add(nameActivity,c);
		
		//Make a datepanel
		JPanel datePanel = new JPanel();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 0;
		c.gridx = 3;
		c.gridy = 2;
		c.gridwidth = addPanel.getWidth()-4;	
		datePanel.setBorder(BorderFactory.createTitledBorder(config.LanguageRepository.getString("DUEDATE")));
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
		String[] categoryStrings= {"Home", "School", "Work"};			//This should request the list of the category's. 
		final JComboBox dropdownCategory = new JComboBox(categoryStrings);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 0.45;
		dropdownCategory.setBorder(BorderFactory.createTitledBorder(config.LanguageRepository.getString("CATEGORY")));
		addPanel.add(dropdownCategory,c);
		
		//Make and add priorityPanel.
		JPanel priorityPanel = new JPanel();
			final JRadioButton highPriority = new JRadioButton(config.LanguageRepository.getString("HIGH"));
			final JRadioButton mediumPriority = new JRadioButton(config.LanguageRepository.getString("MEDIUM"));
			final JRadioButton lowPriority = new JRadioButton(config.LanguageRepository.getString("LOW"));
				
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
		priorityPanel.setBorder(BorderFactory.createTitledBorder(config.LanguageRepository.getString("PRIORITY")));
		addPanel.add(priorityPanel,c);
		
		//add the addButton 
		Font font = new Font(null, Font.BOLD, 20);				//makes the font of the activity big and bold
		addButton.setFont(font);
		c.fill = GridBagConstraints.BOTH;
		c.ipady = 50;
		c.ipadx = 100;
		c.gridx = 6;
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
	        String[] monthStrings= {config.LanguageRepository.getString("JAN"),
	        		config.LanguageRepository.getString("FEB"),
	        		config.LanguageRepository.getString("MAR"),
	        		config.LanguageRepository.getString("APR"),
	        		config.LanguageRepository.getString("MAY"),
	        		config.LanguageRepository.getString("JUN"),
	        		config.LanguageRepository.getString("JUL"),
	        		config.LanguageRepository.getString("AUG"),
	        		config.LanguageRepository.getString("SEP"),
	        		config.LanguageRepository.getString("OCT"),
	        		config.LanguageRepository.getString("NOV"),
	        		config.LanguageRepository.getString("DEC")};	//This should request the list of the category's. 
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

