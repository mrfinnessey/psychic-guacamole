import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;



public class GUI {
	// TODO a class to model a graphing calculator
	//M minimum positive value of the double used to increase by.
	public final static double step = Math.pow(10, -2);
	//M Tracks where points have been filled in the array.
	private int xIndexTracker;
	private int yIndexTracker;
	//M Store the x and y values respectively.
	//M We're trying this. IDK if it works. YOLO.
	private double [] xValues = new double[2001];
	private double [] yValues = new double [2001];
	private boolean pointsReady = false;
	//M Weird and messed up constructor. It works for now, if we can clean it up later, we might want to.
	
	private void fillXValues() {
		//M Currently the compiler isn't entering this for loop for some reason.
		for(int i = (int) (-1 * Math.pow(10,3)); i <= (int) Math.pow(10, 3); i++) {
			xValues[xIndexTracker++] = (double) (i * step);
		}
		System.out.println("XValues filled.");
	}
	
	private void setXIndexTracker(int x) {
		xIndexTracker = x;
	}
	public GUI() {
		//M Creating the various swing components.
		JFrame frame = new JFrame();
		JTextField equation = new JTextField("Enter an equation.");
		JButton calculate = new JButton("Calculate");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(calculate, BorderLayout.CENTER);
		frame.getContentPane().add(equation, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
		//M Creating the MathEvaluator with a default equation.
		MathEvaluator m = new MathEvaluator("x^2");		
		//M Adding the variable to MathEvaluator with a placeholder value.
	   	m.addVariable("x", ((2.0)));
	   	//M Adding the ActionListener for the button.
	   	calculate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Beginning to fill YValues.");
				//M resetting yIndexTracker.
				yIndexTracker = 0;
				//M Setting the MathEvaluator to the entered equation.
				m.setExpression(equation.getText());
				System.out.println("Equation: " + m.expression);
				//M This isn't working out either.
				for(int i = 0; i <= (xValues.length - 1); i++) {
					m.addVariable("x", xValues[i]);
					System.out.println("x: " + m.getVariable("x"));
					System.out.println("Value: " + m.getValue());
					yValues[yIndexTracker++] = m.getValue();
				}
				pointsReady = true;
				System.out.println("YValues filled.");
				yIndexTracker = 0;
			}
	   	});
	   	
	}
	private double[] getXValues() {
		return xValues;
	}
	
	private double[] getYValues() {
		return yValues;
	}
	private boolean getPointsReady() {
		return pointsReady;
	}
	private void setPointsReady(boolean status) {
		pointsReady = status;
	}
	public static void main(String [] args) {
		GUI gui = new GUI();
		gui.setXIndexTracker(0);
		gui.fillXValues();
		Graph graph = new Graph();
		while(true) {
			if(gui.getPointsReady() == true) {
				System.out.println("Drawing Graph!");
				graph.draw(gui.getXValues(), gui.getYValues());
				//M Preventing the method from repeatedly executing.
				System.out.println("Finished drawing!");
				gui.setPointsReady(false);
			}
			else {
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}