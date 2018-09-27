import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
//M Currently issues with graphing complex polynomial functions, likely an issue with syntax for MathEvaluator.
//M Added these things to hopefully get some drawings gong.
public class Graph {
	//M Adding a new JPanel on which to hold the things.
	JPanel graphWindow = new JPanel();
	private String[] colors = {"#FF0000", "#FFA500", "#008000", "#00FFFF", "#000080",
			"#FF00FF", "#800080", "#C0C0C0"};
	private int colorTracker = 0;
	public Graph() {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(graphWindow, BorderLayout.CENTER);
		frame.pack();
		frame.setSize(500, 500);
		graphWindow.setSize(500, 500);
		frame.setLocation(500, 0);
		frame.setVisible(true);
	}
	public void clear() {
		//M A method to clear the graph.
		g.clearRect(0, 0, 500, 500);
		colorTracker = 0;
	}
	 public void draw(double [] xValues, double [] yValues) {
		 //TODO A method to draw the graph.
	        //Get the Graphics2D object of a JPanel, to draw on
	        g = (Graphics2D) graphWindow.getGraphics();
	        //Draw some axes
	        g.drawLine(250, 500, 250, 0); // The Y axis
	        g.drawLine(0, 250, 500, 250);  //The X axis
        	//M Reusing the colors as necessary.
	        if(colorTracker > 7) {
        		colorTracker = 0;
        	}
        	//M Changing the colors of lines.
        	g.setColor(Color.decode(colors[colorTracker++]));
	        for (int i=1; i < xValues.length; i++) {
	            drawPoint(xValues[i], yValues[i]);  //Your data gets read here
	        }
	 }

	        private void drawPoint(double x, double y) {
	        	//TODO A method to draw the various lines that make up the graph.
	        	//M Implementing behavior to scale with the axes, more complexity can be added with more time.
	            g.draw(new Line2D.Double(((25 * x) + 250), ((25 * -y) + 250), ((25 * x) + 250), ((25 * -y) + 250))); //Use negative Y to simulate a standard Cartesian behaivor
	        }
	Graphics2D g;
}