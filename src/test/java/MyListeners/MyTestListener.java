package MyListeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyTestListener implements ITestListener {

    @Override
    public void onFinish(ITestContext contextFinish) {
        System.out.println("onFinish method finished");
    }

    @Override
    public void onStart(ITestContext contextStart) {
        System.out.println("onStart method started");
    }
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test started "+result);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Test success "+result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Test failed "+result);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Test skipped "+result);
    }
}