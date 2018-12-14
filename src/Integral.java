public class Integral {
	public String trapezoidalIntegral(double lowerLimit, double upperLimit, double [] xValues, double [] yValues) {
		//M Variable to store the value of the integral.
		double integralValue = 0.0;
		//M Variable to store the step value.
		final double STEP_VALUE = 0.0001;
		//M Variables to store the beginning and ending indices where the limits of integration occur.
		int beginIndex = -1;
		int endIndex = -1;
		int temp = -1;
		boolean inverted = false;
		//M Finding the beginning and ending indices.
		for(int i = 0; i < xValues.length; i++) {
			if(xValues[i] == lowerLimit) {
				beginIndex = i;
				break;
			}
		}
		for(int j = 0; j < xValues.length; j++) {
			if(xValues[j] == upperLimit) {
				endIndex = j;
				break;
			}
		}
		//M Handling for when the limits of integration are inverted.
		if(beginIndex > endIndex) {
			inverted = true;
			temp = beginIndex;
			beginIndex = endIndex;
			endIndex = temp;
		}
		for(int k = beginIndex; k <= endIndex; k++) {
			if(Double.isNaN(yValues[k]) || Double.isInfinite(yValues[k])) {
				return "Not possible";
			}
		}
		for(int l = beginIndex; l < endIndex; l++) {
			//this is the only line of code I changed
			integralValue += (STEP_VALUE * (yValues[l]+yValues[l+1]) / 2);
		}
		//K Converting integralValue into a string
		String s = String.valueOf(integralValue);
		//K Rounding down to 3 decimal places
		for (int j = 0; j < s.length(); j++) {
			if ((s.substring(j, j + 1)).compareTo(".") == 0 && (s.length() - j) > 5 && s.substring(s.length() - 3,s.length()-1).compareTo("E-") != 0) {
				s = s.substring(0,j+5);
			}else if(s.substring(s.length() - 3,s.length()-1).compareTo("E-") == 0) {
				s = "0.0";
			}
		}
		
		if(inverted == false) {
		return s;
		}
		else {
			//M If the limits of integration are inverted, then the opposite of the uninverted value is returned.
			s = "-" + s;
			return s;
		}
	}
}