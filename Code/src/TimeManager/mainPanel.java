package TimeManager;

import java.awt.*;

import javax.swing.*;

class mainPanel extends 	JFrame
{
	private		JTabbedPane tabbedPane;
	//pages for different categories
	private		JPanel		panel1;
	private		JPanel		panel2;
	private		JPanel		panel3;
	
	//tasks
	public 		JLabel		task1 = new JLabel ("Sketch your design for user interface programming");
	public		JLabel 		task2 = new JLabel( "Read Chapter 3 in Agile Methods Book" );	
	public		JLabel 		task3 = new JLabel( "Help your moving friend to cary the furniture" );
	public		JLabel 		task4 = new JLabel( "Attend your friends birthday party" );
	
	Font rank1 = new Font("Courier", Font.BOLD,20);
	Font rank2 = new Font("Courier", Font.BOLD,15);	
	Font rank3 = new Font("Courier", Font.BOLD,12);
	

	
	public mainPanel()
	{
		// NOTE: to reduce the amount of code in this example, it uses
		// panels with a NULL layout.  This is NOT suitable for
		// production code since it may not display correctly for
		// a look-and-feel.
		
		setTitle( "All Categories" );
		setSize( 1024, 726);
		setBackground( Color.gray);

		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create the tab pages
		createCategory1();
		createCategory2();
		createCategory3();
	
		/*ImageIcon image1 = new ImageIcon(
	            this.getClass().getResource("/images/school.gif"));
	    ImageIcon image2 = new ImageIcon(
	            this.getClass().getResource("/images/work.gif"));
	    ImageIcon image3 = new ImageIcon(
	            this.getClass().getResource("/images/school.gif"));
	    ImageIcon image4 = new ImageIcon(
	            this.getClass().getResource("/images/add_new.gif"));
	    */
		// Create a tabbed pane
		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane.addTab( "All Categories", panel1 );
		tabbedPane.addTab( "Work", panel2 );
		tabbedPane.addTab( "School", panel3 );
		getContentPane().add(tabbedPane);
		//topPanel.add( tabbedPane, BorderLayout.CENTER );
		

		
		task1.setFont(rank1);
		task2.setFont(rank3);
		task3.setFont(rank2);
		task4.setFont(rank3);
		
	}

	public void createCategory1()
	{
		panel1 = new JPanel();
		panel1.setLayout( new GridLayout( 3, 2 ) );
		
		//setTitle( "All Categories" );
		
		panel1.add(task1);
		panel1.add(task2);
		panel1.add(task3); 			
		panel1.add(task4);
	}

	public void createCategory2()
	{
		panel2 = new JPanel();
		panel2.setLayout( new GridLayout( 3, 2 ) );
		//setTitle( "Work" );
		
		panel2.add(task1);
		panel2.add(task2);
		panel2.add(task3);

	}

	public void createCategory3()
	{
		panel3 = new JPanel();
		panel3.setLayout( new GridLayout( 3, 2 ) );
		//setTitle( "School" );
		
		panel3.add(task3);
		panel3.add(task4);
	}

    // Main method to get things started
	public static void main( String args[] )
	{
		// Create an instance of the test application
		mainPanel mainFrame	= new mainPanel();
		mainFrame.setVisible( true );
	}
}