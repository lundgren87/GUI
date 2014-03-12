package TimeManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import java.util.Observable;
import javax.swing.Timer;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;


/**
 * The view of the Time Manager. The main frame and all panels are made here. 
 * @author Kim, Pontus, Aries, Sercan, Sven
 *
 */
public class TimeManagerView extends Observable implements ActionListener {
	
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
	private static String currentTheme;
	public boolean isShuttingDown = false;
	JLabel CategoryAsTitle = new JLabel (" ",JLabel.CENTER);
	
	JRadioButtonMenuItem swedish;
	JRadioButtonMenuItem english;
	JRadioButtonMenuItem oceanTheme;
	JRadioButtonMenuItem metalTheme;
	JRadioButtonMenuItem redTheme;
	JRadioButtonMenuItem blueTheme;
	JTextArea nameActivity;
	JRadioButton highPriority;
	JRadioButton mediumPriority;
	JRadioButton lowPriority;
	JComboBox startYear;
	JComboBox startMonth;
	JComboBox startDay;
	JSpinner.DateEditor timeEditor;
	JSpinner timeSpinner;
	JComboBox dropdownCategory;
	private JLabel timeLabel;
	
	/**
	 * Creates a new Time Manager View
	 */
	TimeManagerView(){	
		
		// load language
		currentLanguage = config.LanguageRepository.getCurrentLanguage();
		
		// Load theme with Ocean as default if property isn't stored
		currentTheme = config.Config.loadProperty("theme", "Ocean");
		
		// On close: hide the view, notify observers to save variables etc, then close
		mainFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		//Initialize look and feel and update the mainFrame
		lookAndFeel.initLookAndFeel();
		SwingUtilities.updateComponentTreeUI(mainFrame);
		mainFrame.pack();
		
		mainFrame.addComponentListener(new ComponentAdapter(){

			@Override
			public void componentHidden(ComponentEvent e) {
				isShuttingDown = true;
				setChanged();
				notifyObservers();
				
				((JFrame)(e.getComponent())).dispose();
			}
		});
		
		/* This section creates the inner components of the view
		 * Which mainly consists of
		 *  - Menu Bar with different menu functionalities
		 *  - Title Panel that shows that is currently active
		 *  - Tab Panel containing category icons to select
		 *  - Task Panel shows all the tasks in the selected category
		 *  - Add Panel to add a new task
		*/
		
		addMenuBar(mainFrame); 
		
		//Content titlePanel 
		//titelPanel.setBackground(Color.LIGHT_GRAY);		//this is a temporary backgroundcolor
		titelPanel.setLayout(new GridLayout(2, 5));
		titelPanel.add(new JLabel(config.LanguageRepository.getString("TITEL")));
		titelPanel.add(new JLabel (""));
		titelPanel.add(new JLabel (""));
		titelPanel.add(new JLabel (""));
		timeLabel = new JLabel ("Time");
		titelPanel.add(timeLabel);                  // Adds date and time to titelPanel
		titelPanel.add(new JLabel (""));
		titelPanel.add(new JLabel (""));
		titelPanel.add(new JLabel (""));
		//titelPanel.add(new JLabel (""));
		
		// Generates the current weekday, date and time
	    new Timer(1000, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	    		DateFormat dateFormat = new SimpleDateFormat("EEEE dd/MM/yyyy HH:mm:ss");
	        	Calendar now = Calendar.getInstance();
	            timeLabel.setText(dateFormat.format(now.getTime()));
	        }
	    }).start();
	    
		makeCenterPanel();
		JScrollPane centerScrollPanel = new JScrollPane();
		centerScrollPanel.getVerticalScrollBar().setUnitIncrement(16);
		centerScrollPanel.getViewport().add(centerPanel);
		MakeAddPanel(addPanel);
		
		//Layout of the titelpanel, tabpannel and addpanel. 
		mainFrame.getContentPane().add(BorderLayout.NORTH, titelPanel);
		mainFrame.getContentPane().add(BorderLayout.WEST, tabPanel );
		mainFrame.getContentPane().add(BorderLayout.CENTER, centerScrollPanel);
		mainFrame.getContentPane().add(BorderLayout.SOUTH, addPanel);
		
		int width = config.Config.loadInt("WindowWidth", 1024);
		int height = config.Config.loadInt("WindowHeight", 600);
		
		mainFrame.setPreferredSize(new Dimension(width, height));
		//mainFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainFrame.setSize(width,height);
		
	}
	
	/**
	 * Overriden close method, which will saves view-related config variables
	 * before it closes
	 * @return Returns JFrame.EXIT_ON_CLOSE after saving the config variables
	 */
	public int closeOperation(){
		config.Config.saveInt("WindowHeight", mainFrame.getHeight());
		config.Config.saveInt("WindowWidth", mainFrame.getWidth());
		
		return JFrame.EXIT_ON_CLOSE;
	}
	
	/**
	 * Add a menu bar on the JFrame
	 * @param mainFrame JFrame to add the menu bar to
	 */
	private void addMenuBar(final JFrame mainFrame) {
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
		JMenu themeAction = new JMenu(config.LanguageRepository.getString("THEME"));
		JMenuItem helpAction = new JMenuItem(config.LanguageRepository.getString("HELP"));
		
		ButtonGroup rbgroup = new ButtonGroup();
		swedish = new JRadioButtonMenuItem(config.LanguageRepository.getString("SWEDISH"));
		english = new JRadioButtonMenuItem(config.LanguageRepository.getString("ENGLISH"));
		if(currentLanguage.equals("English"))
			english.setSelected(true);
		else
			swedish.setSelected(true);
		
		rbgroup.add(swedish);
		swedish.addActionListener(this);
		english.addActionListener(this);
		
		rbgroup.add(english);
		
		languageAction.add(english);
		languageAction.add(swedish);
		
		
		
		/* 
		 * SET UP THEME MENU
		 * */
		oceanTheme = new JRadioButtonMenuItem(config.LanguageRepository.getString("OCEAN"));
		metalTheme = new JRadioButtonMenuItem(config.LanguageRepository.getString("METAL"));
		redTheme = new JRadioButtonMenuItem(config.LanguageRepository.getString("RED"));
		blueTheme = new JRadioButtonMenuItem(config.LanguageRepository.getString("BLUE"));
		
		// Add a button group, only one selectable
		ButtonGroup themeButtonGroup = new ButtonGroup();
		themeButtonGroup.add(oceanTheme);
		themeButtonGroup.add(metalTheme);
		themeButtonGroup.add(redTheme);
		themeButtonGroup.add(blueTheme);
		
		// Decide which is selected on startup
		if(currentTheme.equals("Ocean"))
			oceanTheme.setSelected(true);
		else if(currentTheme.equals("Metal"))
			metalTheme.setSelected(true);
		else if(currentTheme.equals("Red"))
			redTheme.setSelected(true);
		else if(currentTheme.equals("Blue"))
			blueTheme.setSelected(true);
		
		// Add action listeners to all themes
		oceanTheme.addActionListener(this);
		metalTheme.addActionListener(this);
		redTheme.addActionListener(this);
		blueTheme.addActionListener(this);
		
		themeAction.add(oceanTheme);
		themeAction.add(metalTheme);
		themeAction.add(redTheme);
		themeAction.add(blueTheme);
		
		

		
		//Add actions to the menus
		fileMenu.add(newAction);
		fileMenu.add(loadAction);
		fileMenu.add(exitAction);
		editMenu.add(languageAction);
		editMenu.add(themeAction);
		
		helpMenu.add(helpAction);
		
		mainFrame.setJMenuBar(menuBar);	
		}

	/**
	 * Center panels contains the Tab Panel with the clickable category icons
	 *  and the Task Panel that shows the Task Items
	 * @return tabpane
	 */
	private void makeCenterPanel() {
		// Create empty tabPanel
		tabPanel = new JPanel();
		tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.Y_AXIS));
		//tabPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		tabPanel.setPreferredSize(new Dimension(88,500));
		
		// Create empty taskPanel and its list
		taskPanels = new ArrayList<TaskPanel>();
		taskPanel = new TaskPanel();
		
		centerPanel = new JPanel();
		//centerPanel.setBackground(Color.green);
		centerPanel.add(taskPanel);
	}
	
	/**
	 * Remove existing categories and load all categories in the List.
	 * @param taskCategories List of categories to load
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

			btn.setActionCommand("CategoryButtons");
			btn.addActionListener(this);
			tabPanel.add(btn);
		
			// Generate panel to contain tasks for each category (right side)
			TaskPanel tp = new TaskPanel(category);
			taskPanels.add(tp);
		}
		// switch to last active category
		switchTab(config.Config.loadProperty("currentCategory", "all_categories"));
	}
	
	/**
	 * Displays tab from the category.
	 * @param selectedCategory Category that will be the active tab
	 */
	public void switchTab(String selectedCategory) {
		// Record the newly actived tab
		config.Config.saveProperty("currentCategory",selectedCategory);
		
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
	 * Remove existing tasks and load all tasks in the List.
	 * @param taskItems List of items to load
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
		taskPanel.validate();
		taskPanel.repaint();
	}

	private void MakeAddPanel(JPanel addPanel) {
		this.addPanel = addPanel;
		addPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		//Make and add NameActivity
		nameActivity = new JTextArea(3,1); 		//TextArea to insert the name of the activity
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
		timeSpinner = new JSpinner( new SpinnerDateModel() );
		timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date()); 
		datePanel.add(timeSpinner);
		
		//Make and add dropdown menu to choose category		
		String[] categoryStrings= {"Home", "School", "Work"};			//This should request the list of the category's. 
		dropdownCategory = new JComboBox(categoryStrings);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 1;
		c.weightx = 0.45;
		dropdownCategory.setBorder(BorderFactory.createTitledBorder(config.LanguageRepository.getString("CATEGORY")));
		addPanel.add(dropdownCategory,c);
		
		//Make and add priorityPanel.
		JPanel priorityPanel = new JPanel();
		highPriority = new JRadioButton(config.LanguageRepository.getString("HIGH"));
		mediumPriority = new JRadioButton(config.LanguageRepository.getString("MEDIUM"));
		lowPriority = new JRadioButton(config.LanguageRepository.getString("LOW"));
			
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
		addButton.setActionCommand("AddButton");
		addButton.addActionListener(this);

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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//If a radio button is pressed
		if(e.getSource() instanceof JRadioButtonMenuItem) {
			
			//If the radio button is a language button
			if(e.getSource() == swedish) {
				if(swedish.isSelected()) {
					config.LanguageRepository.setCurrentLanguage("Swedish");
					JOptionPane.showMessageDialog(mainFrame, config.LanguageRepository.getString("LANGUAGERESTART"));
				}
				mainFrame.revalidate();
				mainFrame.repaint();
			}
			else if(e.getSource() == english) {
				if(english.isSelected()){
					config.LanguageRepository.setCurrentLanguage("English");
					JOptionPane.showMessageDialog(mainFrame, config.LanguageRepository.getString("LANGUAGERESTART"));
				}	
				mainFrame.revalidate();
				mainFrame.repaint();	
			}
			
			//If the radio button is a theme button, store theme property and display restart required popup
			if(e.getSource() == oceanTheme) {
				if(oceanTheme.isSelected()) {
					config.Config.saveProperty("theme", "Ocean");
					JOptionPane.showMessageDialog(mainFrame, config.LanguageRepository.getString("THEMERESTART"));
				}
			}
			else if(e.getSource() == metalTheme) {
				if(metalTheme.isSelected()) {
					config.Config.saveProperty("theme", "Metal");
					JOptionPane.showMessageDialog(mainFrame, config.LanguageRepository.getString("THEMERESTART"));
				}
			}
			else if(e.getSource() == redTheme) {
				if(redTheme.isSelected()) {
					config.Config.saveProperty("theme", "Red");
					JOptionPane.showMessageDialog(mainFrame, config.LanguageRepository.getString("THEMERESTART"));
				}
			}
			else if(e.getSource() == blueTheme) {
				if(blueTheme.isSelected()) {
					config.Config.saveProperty("theme", "Blue");
					JOptionPane.showMessageDialog(mainFrame, config.LanguageRepository.getString("THEMERESTART"));
				}
			}
			
		}

		else if(e.getSource() instanceof JButton) {
			JButton btn = (JButton) e.getSource();
			if(btn.getActionCommand().equalsIgnoreCase("CategoryButtons")) {
				switchTab(btn.getName());
			}
			else if(btn.getActionCommand().equalsIgnoreCase("AddButton")) {
				String description, category, date, time;
				int priority, progress;
				
				description = nameActivity.getText();

				category = dropdownCategory.getSelectedItem().toString();
				
				date = startDay.getSelectedItem().toString() + " " +  
						startMonth.getSelectedItem().toString() + " " +
						startYear.getSelectedItem().toString();
		    		
				time = timeEditor.getFormat().format(timeSpinner.getValue());
				
				if(highPriority.isSelected()) priority = 1;
				else if(lowPriority.isSelected()) priority = 2;
				else priority = 2;
		    		
				progress = 0;
 	 
				TaskItem taskItem = new TaskItem(description, category, date + " " + time, progress, priority);
				setChanged();
				notifyObservers(taskItem);
			}
		       
		}
	}
}

