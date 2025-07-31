package utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	private int count = 0;
	private static final int maxTry = 2; // Retry twice

	public boolean retry(ITestResult result) {
	    if (count < maxTry) {
	        System.out.println("Retrying " + result.getName() + " again — Attempt " + (count + 1));
	        count++;
	        return true;
	    }
	    return false;
	}
/*	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		return false;
	}
	*/
}
	 