
package TimeManager;

import java.awt.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.JProgressBar;

public class Tasks extends JPanel
{
	
	//static JPanel panel = new JPanel();
	public static JLabel task_label;
	public JLabel duedate_label;
	public JLabel category,prio1, prio2, prio3;
	public ImageIcon categImg, prioImg, editIcon;
	public JCheckBox done;
	public JProgressBar progressbarre;
	public JButton editbutton;
	
	
	
	GridBagConstraints gbc = new GridBagConstraints();
	
	public Tasks() {

		this.setLayout(new GridBagLayout());
		//this.setSize(500, 500);
		this.setBorder(BorderFactory.createLineBorder(Color.blue));

		category = new JLabel(categImg);
		ImageIcon categImg = new ImageIcon("bin/images/house_resized.gif");
		category.setIcon(categImg);
		gbc.gridx = 0;
		gbc.gridy = 0;
		this.add(category, gbc);
		
		prio1 = new JLabel(prioImg);
		ImageIcon prioImg = new ImageIcon("bin/images/exclamation2.png");
		prio1.setIcon(prioImg);
		gbc.gridx = 1;
		gbc.gridy = 0;
		this.add(prio1, gbc);
		
		prio2 = new JLabel(prioImg);
		prio2.setIcon(prioImg);
		gbc.gridx = 2;
		gbc.gridy = 0;
		this.add(prio2, gbc);
		
		prio3 = new JLabel(prioImg);
		prio3.setIcon(prioImg);
		gbc.gridx = 3;
		gbc.gridy = 0;
		this.add(prio3, gbc);
		
		duedate_label = new JLabel("Due: Tomorrow");
		gbc.gridx = 4;
		gbc.gridy = 0;
		this.add(duedate_label, gbc);
		
		done = new JCheckBox();
		gbc.gridx =  this.getWidth()-1;;
		gbc.gridy = 0;
		this.add(done, gbc);
		
		task_label = new JLabel("I have to goto the toilet! Let me not forget it because it is very important and if I don't do it it will be too bad for me.");
		task_label.setToolTipText(task_label.getText());
		//task_label.setBorder(BorderFactory.createLineBorder(Color.gray));
		task_label.setPreferredSize(new Dimension(this.getWidth(), 25)); 
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = this.getWidth()-1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(task_label, gbc);

		
		progressbarre = new JProgressBar();
		progressbarre.setValue(32);
		progressbarre.setStringPainted(true);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = this.getWidth()-1;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		this.add(progressbarre, gbc);
		
		ImageIcon editIcon = new ImageIcon("bin/images/edit1.png");
		editbutton = new JButton("", editIcon);
		gbc.gridx =  this.getWidth()-1;;
		gbc.gridy = 2;
		//gbc.gridwidth = 1;
		this.add(editbutton, gbc);
	}	

/*
public static void main (String[] args){

		Tasks t = new Tasks();
		JFrame frame = new JFrame("Task Component");
		frame.pack();
		frame.setVisible(true);
		frame.setSize(1024,726);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(this);
		
	}
*/
}
