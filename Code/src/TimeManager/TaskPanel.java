package TimeManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Locale.Category;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TaskPanel extends JPanel{
	
	public TaskCategory taskCategory;
    JPanel panelNorth;
    JPanel panelWest;
    JPanel panelEast;
    GridBagConstraints c;
    
    public TaskPanel() {
    	init();
    }
    
	public TaskPanel(TaskCategory cat) {
		taskCategory = cat;
		init();
	}
    
	private void init() {
		this.setPreferredSize(new Dimension(800,500));
		
	    // TaskPanel arranges its panels in GridBagLayout
	    c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		this.setLayout(new GridBagLayout());
		
		// initialization of the 3 panels inside TaskPanel
	    panelNorth = new JPanel();
	    panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.Y_AXIS));
	    panelNorth.setBorder(BorderFactory.createLineBorder(Color.black));
		c.gridwidth = 3;
		c.gridx = 0;
		c.gridy = 0;
		this.add(panelNorth, c);
		
	    panelWest = new JPanel();
	    panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
	    panelWest.setBorder(BorderFactory.createLineBorder(Color.black));
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		this.add(panelWest, c);
	    
	    panelEast = new JPanel();
	    panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.Y_AXIS));
	    panelEast.setBorder(BorderFactory.createLineBorder(Color.black));
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		this.add(panelEast, c);
	}
	
	void removeAllTasks() {
		panelNorth.removeAll();
		panelEast.removeAll();
		panelWest.removeAll();
	}
	
	void addTask(TaskItem item) {
		JLabel taskDescription = new JLabel(item.taskDescripton + ". Priority : " + item.taskPriority);
		JPanel customComponent = new Tasks();
		customComponent.setBorder(BorderFactory.createLineBorder(Color.green));
		customComponent.add(taskDescription);
		switch(item.taskPriority) {
			case 1:
				panelNorth.add(customComponent);
				//System.out.println(item.taskDescripton+ " is added to panel North in category " + taskCategory.categoryName);
				break;
			case 2:
				panelWest.add(customComponent);
				//System.out.println(item.taskDescripton + " is added to panel West in category " + taskCategory.categoryName);
				break;
			case 3:
				panelEast.add(customComponent);
				//System.out.println(item.taskDescripton + " is added to panel East in category " + taskCategory.categoryName);
				break;
			default:
				
		}
	}
}
