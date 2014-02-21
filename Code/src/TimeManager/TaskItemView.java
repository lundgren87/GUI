
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
	
	private final String iconLocation = "bin/images/";
	private final String iconExtension = ".gif";
	private final int MAXIMUM_PRIORITY = 3;
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	public TaskItemView() {
		initComponents();
		// TODO: pass argument to constructor? and set variables accordingly
	}	

	public void initComponents() {

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
		ImageIcon priorityIcon = new ImageIcon("bin/images/exclamation2.png");
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
		ImageIcon editIcon = new ImageIcon("bin/images/edit1.png");
		editButton = new JButton("", editIcon);
		gbc.gridx =  5;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		this.add(editButton, gbc);
	}
	
	public void setDescription(String desc) {
		taskDescripton = desc;
		taskLabel.setText(taskDescripton);
		taskLabel.setToolTipText(taskDescripton);
	}
	
	public void setPriority(int prio) {
		if(prio > MAXIMUM_PRIORITY) prio = MAXIMUM_PRIORITY;
		taskPriority = prio;
		
		// Show priority labels according to new priority
		for(int i = 0 ; i < MAXIMUM_PRIORITY ; i++) {
			if(i < taskPriority) priorityLabels[i].setVisible(true);
			else priorityLabels[i].setVisible(false);
		}
	}
	
	public void setCategory(String categoryName) {
		taskCategoryName = categoryName;
		ImageIcon categoryIcon = new ImageIcon(iconLocation + taskCategoryName + "_s" + iconExtension);
		categoryLabel.setIcon(categoryIcon);
	}
	
	public void setDueDate(String date) {
		dueDate = date;
		dueDateLabel.setText(dueDate);
	}
	
	public void setProgress(int progress) {
		taskProgress = progress;
		progressbarre.setValue(taskProgress);
	}
}
