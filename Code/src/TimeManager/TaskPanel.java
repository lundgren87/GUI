package TimeManager;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.Locale.Category;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that holds the 3 Task Panels together in each categiory.
 * @author Aries
 *
 */
public class TaskPanel extends JPanel{
	
	public TaskCategory taskCategory;
    JPanel panelNorth;
    JPanel panelWest;
    JPanel panelEast;
    GridBagConstraints c;
    ActionListener viewListener;
    
    /**
     * Creates a new Task Panel that does not belong to any category yet
     */
    public TaskPanel(ActionListener listener) {
    	viewListener = listener;
    	init();
    }
    
    /**
     * Creates a new Task Panel that belongs to a category
     * @param cat Category that the Task Panel belongs to
     */
	public TaskPanel(ActionListener listener, TaskCategory cat) {
		taskCategory = cat;
		viewListener = listener;
		init();
	}
    
	/**
	 * Initialize all inner components
	 */
	private void init() {
		//this.setPreferredSize(new Dimension(800,500));
		
	    // TaskPanel arranges its panels in GridBagLayout
	    c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		this.setLayout(new GridBagLayout());
		
		// initialization of the 3 subpanels inside TaskPanel (panelNorth, panelEast, panelWest)
	    panelNorth = new JPanel();
	    panelNorth.setLayout(new BoxLayout(panelNorth, BoxLayout.Y_AXIS));
	    //panelNorth.setBorder(BorderFactory.createLineBorder(Color.black));
	   
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		this.add(panelNorth, c);
		
	    panelWest = new JPanel();
	    panelWest.setLayout(new BoxLayout(panelWest, BoxLayout.Y_AXIS));
	    //panelWest.setBorder(BorderFactory.createLineBorder(Color.black));
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		this.add(panelWest, c);
	    
	    panelEast = new JPanel();
	    panelEast.setLayout(new BoxLayout(panelEast, BoxLayout.Y_AXIS));
	    //panelEast.setBorder(BorderFactory.createLineBorder(Color.black));
		c.gridwidth = 1;
		c.weightx = 0.5;
		c.gridx = 1;
		c.gridy = 1;
		this.add(panelEast, c);
	}
	
	/**
	 * Removes all tasks inside this TaskPanel contains
	 */
	void removeAllTasks() {
		panelNorth.removeAll();
		panelEast.removeAll();
		panelWest.removeAll();
	}
	
	/**
	 * Add a new TaskItem to the TaskPanel.
	 * @param item TaskItem to be added
	 */
	void addTask(TaskItem item) {
		TaskItemView customComponent = new TaskItemView();
		customComponent.setDescription(item.taskDescripton);
		customComponent.setCategory(item.taskCategory);
		customComponent.setPriority(item.taskPriority);
		customComponent.setProgress(32);
		customComponent.setDueDate("Tomorrow");
		//JLabel taskDescription = new JLabel(item.taskDescripton + ". Priority : " + item.taskPriority);
		//customComponent.add(taskDescription);
		
		// A TaskItem will be displayed in one of the 3 sub panels, depending on its priority
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
