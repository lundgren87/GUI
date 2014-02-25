
package TimeManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.PriorityBlockingQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

/**
 * A JPanel view class to display a Task Item. It also serves as the custom component required by the project.
 * @author Aries, Sercan
 *
 */
public class TaskItemView extends JPanel
{
	String taskCategoryName;
	public String taskDescripton;
	public int taskPriority;
	public String dueDate;
	public int taskProgress;
	
	public JLabel taskLabel, dueDateLabel, categoryLabel;
	public JLabel[] priorityLabels;
	public ImageIcon priorityIcon, editIcon;
	public JCheckBox isDoneCheckBox;
	public JProgressBar progressbarre;
	public JButton editButton;
	
	private final String iconLocation = config.Config.loadProperty("Images", "assets/images/");
	private final String iconExtension = ".gif";
	private final int MAXIMUM_PRIORITY = 3;
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	/**
	 * Creates a new Task Item View component to display a Task View
	 */
	public TaskItemView() {
		// TODO: Consider to make a constructor with a TaskItem argument
		// that sets variables accordingly
		initComponents();
	}	
	
	/**
	 * Initialize all inner components. Executed once in initialization.
	 */
	private void initComponents() {

		this.setLayout(new GridBagLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.blue));
		
		// Category
		categoryLabel = new JLabel();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		this.add(categoryLabel, gbc);
		
		// Priority
		priorityLabels = new JLabel[MAXIMUM_PRIORITY];
		ImageIcon priorityIcon = new ImageIcon(iconLocation + "exclamation2.png");
		for(int i=0;i<MAXIMUM_PRIORITY;i++) {
			JLabel prio = new JLabel(priorityIcon);
			gbc.gridx = i + 1;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			this.add(prio, gbc);
			priorityLabels[i] = prio;
		}
		
		// Due date
		dueDateLabel = new JLabel();
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		this.add(dueDateLabel, gbc);
		
		isDoneCheckBox = new JCheckBox();
		gbc.gridx = 5;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		this.add(isDoneCheckBox, gbc);
		
		taskLabel = new JLabel();
		//task_label.setBorder(BorderFactory.createLineBorder(Color.gray));
		//taskLabel.setPreferredSize(new Dimension(this.getWidth(), 25)); 
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 6;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(taskLabel, gbc);
		
		progressbarre = new JProgressBar();
		progressbarre.setStringPainted(true);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(progressbarre, gbc);
		
		// TODO: No hardcode
		ImageIcon editIcon = new ImageIcon(iconLocation + "edit1.png");
		editButton = new JButton("", editIcon);
		gbc.gridx =  5;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		this.add(editButton, gbc);
	}
	
	/**
	 * Setter for displayed Task Description
	 * @param desc
	 */
	public void setDescription(String desc) {
		taskDescripton = desc;
		taskLabel.setText(taskDescripton);
		taskLabel.setToolTipText(taskDescripton);
	}
	
	/**
	 * Setter for displayed priority
	 * @param desc
	 */
	public void setPriority(int prio) {
		// Validate that priority is within acceptable range
		if(prio > MAXIMUM_PRIORITY) prio = MAXIMUM_PRIORITY;
		else if(prio < 1) prio = 1;
		taskPriority = prio;
		
		// Show priority labels according to new priority
		for(int i = 0 ; i < MAXIMUM_PRIORITY ; i++) {
			if(i < taskPriority) priorityLabels[i].setVisible(true);
			else priorityLabels[i].setVisible(false);
		}
	}
	
	/**
	 * Setter for displayed category
	 * @param categoryName The new category name. Must match its icon name.
	 */
	public void setCategory(String categoryName) {
		taskCategoryName = categoryName;
		ImageIcon categoryIcon = new ImageIcon(iconLocation + taskCategoryName + "_s" + iconExtension);
		categoryLabel.setIcon(categoryIcon);
	}
	
	/**
	 * Setter for the due date
	 * @param date The new due date
	 */
	public void setDueDate(String date) {
		dueDate = date;
		dueDateLabel.setText(dueDate);
	}
	
	/**
	 * Setter for the progress
	 * @param progress The new progress
	 */
	public void setProgress(int progress) {
		// Validate that priority is within acceptable range
		if(progress > 100) progress = 100;
		else if(progress < 0) progress = 0;
		taskProgress = progress;
		progressbarre.setValue(taskProgress);
	}
}
