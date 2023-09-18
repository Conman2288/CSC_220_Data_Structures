/*
Name: 		Connor Heard
Date: 		9/16/2023
Course: 	CSC 220 - Data Structures
*/


public class ComplexityAnalyzer extends AbstractComplexityAnalyzer {
	/**
	 * Returns a String containing the names of the functions that could have
	 * possibly generated the output given the input
	 * @param 		input number
	 * @param 		output number
	 * @return 		names of possible functions
	 */


	// This function calculates the log base 2 of x
	static double log2(double x) {
		return (double) (Math.log(x) / Math.log(2));
	}

	// The Complexity analyzer function checks the input and output arguments and determines if
	// Their relationship fall into various mathematical functions
	public String analyzeComplexity(double input, double output) {
		
		String solution = "";

		// Checks for constant function
		if (1 == output) {
			solution += "Constant, ";
		}

		// Checks for logarithmic function base 2
		if (log2(input) == output){
			solution += "Logarithmic, ";
		}

		// Checks if the function is linear
		if (input == output) {
			solution += "Linear, ";
		}

		// Checks if the function is quasi-linear
		if (input * log2(input) == output) {
			solution += "Quasi-Linear, ";
		}

		// Checks if the function is a quadratic in nature
		if (Math.pow(input, 2) == output) {
			solution += "Quadratic, ";
		}

		// Checks if the function is cubic
		if (Math.pow(input, 3) == output) {
			solution += "Cubic, ";
		}

		// Checks if the function can be modeled exponentially
		if (Math.pow(2, input) == output) {
			solution += "Exponential, ";
		}

		// returns the final string of functions with the last comma and space removed
		return solution.substring(0, solution.length()-2);
	}
}
