package TimeManager;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerDateModel;


	public class TaskItemViewEdit extends JDialog
	{
		
		TaskItem originalTaskItem, editedTaskItem;
		ActionListener viewListener;
		static JPanel panel = new JPanel();
		public JTextArea task_label_edit;
		public JSlider progress_slider;
		
		//public JComboBox category_edit;
		private JRadioButton highPriority;
		private JRadioButton mediumPriority;
		private JRadioButton lowPriority;
		
		//public ImageIcon categImg, prioImg;
		public JCheckBox done;
		//public JProgressBar progressbarre;
		public JButton doneEditingButton;
		private Calendar startDate = Calendar.getInstance();	//Selects today's date. 
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		public TaskItemViewEdit(ActionListener listener, TaskItem item) {
			viewListener = listener;
			originalTaskItem = item;
			editedTaskItem = new TaskItem(originalTaskItem);
			initComponents();
			populateWithExistingTask();
			setSize(600,200);
			setVisible(true);
		}	

		public void initComponents() {
			//super(null,"title",)
			panel.setLayout(new GridBagLayout());
			//panel.setSize(400, 200);
			panel.setBorder(BorderFactory.createLineBorder(Color.gray));
			
			//Make and add drop-down menu to choose category		
			String[] categoryStrings= {"Home", "School", "Work"};	 
			final JComboBox dropdownCategory = new JComboBox(categoryStrings);
			gbc.fill = GridBagConstraints.BOTH;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 1;

			dropdownCategory.setBorder(BorderFactory.createTitledBorder("Category"));
			panel.add(dropdownCategory,gbc);

			//ImageIcon prioImg = new ImageIcon("bin/images/exclamation2.png");
			highPriority = new JRadioButton("!!!");
			mediumPriority = new JRadioButton("!!");
			lowPriority = new JRadioButton("!");
			
			//Group the buttons
			final ButtonGroup priority_edit = new ButtonGroup();
			priority_edit.add(highPriority);
			priority_edit.add(mediumPriority);
			priority_edit.add(lowPriority);
		
			//Default is medium
			mediumPriority.setSelected(true);
			gbc.gridx = 1;
			gbc.gridy = 0;
			panel.add(lowPriority, gbc);
			gbc.gridx = 2;
			gbc.gridy = 0;
			panel.add(mediumPriority, gbc);
			gbc.gridx = 3;
			gbc.gridy = 0;
			panel.add(highPriority, gbc);

			
			/*duedate_label = new JTextField("Due: Tomorrow");
			gbc.gridx = 4;
			gbc.gridy = 0;
			panel.add(duedate_label, gbc);*/
			//Make a datepanel
			JPanel datePanel = new JPanel();
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 4;
			gbc.gridy = 0;
			datePanel.setBorder(BorderFactory.createTitledBorder("Starting Date"));
			panel.add(datePanel,gbc);

			datePanel.setLayout(new GridLayout(0,4));
					//Make an date chooser, add this to the datePanel
					JComboBox startYear, startMonth, startDay;
			       	startYear = new JComboBox();
			        buildYearsList(startYear);
			        startYear.setSelectedIndex(5);
			        startMonth = new JComboBox();
			        buildMonthsList(startMonth);
			        startMonth.setSelectedIndex(startDate.get(Calendar.MONTH));
			        startDay = new JComboBox();
			        buildDaysList(startDate, startDay, startMonth);
			        startDay.setSelectedItem(Integer.toString(startDate.get(Calendar.DATE)));
			        //startYear.addItemListener(this);
			        //startMonth.addItemListener(this);
			        //startDay.addItemListener(this);
			        //datePanel.add(startDateLabel);
			        datePanel.add(startDay);
			        datePanel.add(startMonth);
			        datePanel.add(startYear);
				
					//make an time spinner, add this to the datePanel
					JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );
					JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
					timeSpinner.setEditor(timeEditor);
					timeSpinner.setValue(new Date()); 
					datePanel.add(timeSpinner);

			
			
			/*done = new JCheckBox();
			gbc.gridx =  panel.getWidth()-1;;
			gbc.gridy = 0;
			panel.add(done, gbc);*/
			
			task_label_edit = new JTextArea(3,1);
			
			//Tasks.task_label.getText()
			//task_label.setBorder(BorderFactory.createLineBorder(Color.gray));
			//task_label_edit.setPreferredSize(new Dimension(panel.getWidth(), 75)); 
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = panel.getWidth()-1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			panel.add(task_label_edit, gbc);

			progress_slider = new JSlider();
			progress_slider.setMinimum(0);
			progress_slider.setMaximum(100);
			//Tasks.task_label.getText()
			//task_label.setBorder(BorderFactory.createLineBorder(Color.gray));
			//task_label_edit.setPreferredSize(new Dimension(panel.getWidth(), 75)); 
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = 6;
			panel.add(progress_slider, gbc);
			
			/*progressbarre = new JProgressBar();
			progressbarre.setValue(72);
			progressbarre.setStringPainted(true);
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = panel.getWidth()-2;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			panel.add(progressbarre, gbc);*/
			
			doneEditingButton = new JButton("Save");
			gbc.gridx =  panel.getWidth()-1;;
			gbc.gridy = 2;
			//gbc.gridwidth = 1;
			doneEditingButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					editedTaskItem.taskDescripton = task_label_edit.getText();
					originalTaskItem = editedTaskItem;
				}
		        });
			panel.add(doneEditingButton, gbc);

			add(panel);
		}
		
		public void populateWithExistingTask() {
			task_label_edit.setText(editedTaskItem.taskDescripton);
			switch(editedTaskItem.taskPriority) {
			case 1:
				lowPriority.setSelected(true);
				break;
			case 2:
				mediumPriority.setSelected(true);
				break;
			case 3:
				highPriority.setSelected(true);
				break;
			}
			progress_slider.setValue(editedTaskItem.taskProgress);
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
	 
	 public TaskItem getEditedTask() {
		 return editedTaskItem;
	 }
}

