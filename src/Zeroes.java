
public class Zeroes {
//TODO: create a class to find zeros and other values using IVT
	/* K: Current idea is to use a for loop and increase i by 0.001 and use IVT to chekc if there's a 0
	 * K: Problem: if a function touches the x axis but doesn't cross
	 * K: No solution yet
	 */
	
	public static void main(String[] args) {
		double[] zeros = zeroFinder("3x^3 - 5x^2 + 4x- 3");
		if (zeros.length == 0) {
			System.out.println("no zeros");
		}
		for (int i = 0; i < zeros.length; i++) {
			System.out.println(zeros[i]);
		}
	}
	
	public static int splitter(String s) {
		// M Splitting the polynomial expression at every term
		// M Let's hope we won't have to simplify anything. We should check in on this.
		// M Also we need to find a way to store the + or - as split doesn't retain it.
		String[] terms = s.split("\\+|\\-");
		int splitIndex = 0;
		for(int i = 0; i < terms.length; i++) {
			terms[i].trim();
			for(int j = 0; j < terms[i].length(); j++) {
				if (terms[i].charAt(j) == 'x'){
					splitIndex = j;
				}
			}
		}
		return splitIndex;
	}
	
	public static double plugIn(String s, double xval) {
		double rV = 0;
		//K This next bit of code turns any coefficient into a multiplication function so we can plug it into MathEvaluator
		//K For example, 128x turns into 128*x and 64x^2 turns into 64*x^2
		//K This current code is very janky and inefficient. I'm gonna try to find a better solution later
		for (int i = 0; i < s.length() - 1; i++) {
			if ((s.substring(i,i+2)).compareTo("0x") == 0){
				s = s.substring(0,i) + "0*x" + s.substring(i+2);
			}else if ((s.substring(i,i+2)).compareTo("1x") == 0){
				s = s.substring(0,i) + "1*x" + s.substring(i+2);
			}else if ((s.substring(i,i+2)).compareTo("2x") == 0){
				s = s.substring(0,i) + "2*x" + s.substring(i+2);
			}else if ((s.substring(i,i+2)).compareTo("3x") == 0){
				s = s.substring(0,i) + "3*x" + s.substring(i+2);
			}else if ((s.substring(i,i+2)).compareTo("4x") == 0){
				s = s.substring(0,i) + "4*x" + s.substring(i+2);
			}else if ((s.substring(i,i+2)).compareTo("5x") == 0){
				s = s.substring(0,i) + "5*x" + s.substring(i+2);
			}else if ((s.substring(i,i+2)).compareTo("6x") == 0){
				s = s.substring(0,i) + "6*x" + s.substring(i+2);
			}else if ((s.substring(i,i+2)).compareTo("7x") == 0){
				s = s.substring(0,i) + "7*x" + s.substring(i+2);
			}else if ((s.substring(i,i+2)).compareTo("8x") == 0){
				s = s.substring(0,i) + "8*x" + s.substring(i+2);
			}else if ((s.substring(i,i+2)).compareTo("9x") == 0){
				s = s.substring(0,i) + "9*x" + s.substring(i+2);
			}
		}
		MathEvaluator ME = new MathEvaluator(s);
		ME.addVariable("x", xval);
		rV = ME.getValue();
		return rV;
	}
	
	public static double[] zeroFinder(String s) {
		int zeros = 0;
		for (int i = -20; i < 20; i++) {
			double firstValue = plugIn(s, i);
			double secondValue = plugIn(s, i+1);
			if (firstValue < 0 && (secondValue) > 0) {
				zeros++;
			}else if (firstValue > 0 && (secondValue + 1) < 0) {
				zeros++;
			}else if (firstValue == 0) {
				zeros++;
			}
		}
		double[] rV = new double[zeros];
		zeros = 0;
		for (int i = -20; i <= 20; i++) {
			double firstValue = plugIn(s, i);
			double secondValue = plugIn(s, i+0.01);
			if (firstValue < 0 && (secondValue) > 0) {
				rV[zeros] = i;
				zeros++;
			}else if (firstValue > 0 && (secondValue + 1) < 0) {
				rV[zeros] = i;
				zeros++;
			}else if (firstValue == 0) {
				rV[zeros] = i;
				zeros++;
			}
		}
		return rV;
	}
}