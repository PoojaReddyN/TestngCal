import DataProviders.DataProviderClass;
import com.epam.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class CalculatorTesting {

    private Calculator calculator;
;

    @BeforeSuite
    public void beforeSuite(){
        System.out.println("Suite is starting");
    }

    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        System.out.println("BeforeClass: Initializing Calculator instance.");
        calculator = new Calculator();
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before Method: " + Thread.currentThread().getName());
    }

    @Test(dataProvider = "longPairDataProvider",dataProviderClass = DataProviderClass.class, groups = {"positive"})
    public void positiveLongArithmeticTest(long a, long b) {
        long expectedSum = a + b;
        long expectedSub = a - b;
        Assert.assertEquals(calculator.sum(a, b), expectedSum, "Positive: Correct long sum");
        Assert.assertEquals(calculator.sub(a, b), expectedSub, "Positive: Correct long subtraction");
    }


    @Test(dataProvider = "doublePairDataProvider", dataProviderClass = DataProviderClass.class, groups = {"positive"})
    public void positiveDoubleArithmeticTest(double a, double b) {
        double expectedSum = a + b;
        double expectedPow = Math.pow(a, Math.floor(b));
        Assert.assertEquals(calculator.sum(a, b), expectedSum, 0.0001, "Positive: Correct double sum");
        Assert.assertEquals(calculator.pow(a, b), expectedPow, 0.0001, "Positive: Correct double pow");
    }


    @Test(dataProvider = "longDataProvider", dataProviderClass = DataProviderClass.class, groups = {"positive"})
    public void positiveLongSingleTest(long value) {
        boolean expected = (value > 0);
        Assert.assertEquals(calculator.isPositive(value), expected, "Positive: isPositive check for " + value);
    }


    @Test(dataProvider = "doubleDataProvider", dataProviderClass = DataProviderClass.class, groups = {"positive"})
    public void positiveDoubleSingleTest(double value) {
        double expectedSqrt = Math.sqrt(Math.abs(value));
        Assert.assertEquals(calculator.sqrt(value), expectedSqrt, 0.0001, "Positive: sqrt check for " + value);
    }


    @Test(groups = {"positive", "trignometry"})
    public void positiveTrigonometryTest() {
        double angle = Math.PI / 2;

        Timeout.sleep(1);
        Assert.assertEquals(calculator.sin(angle), Math.sin(angle), 0.0001, "Positive: Correct sine for PI/2");

        Assert.assertEquals(calculator.cos(angle), Math.sin(angle), 0.0001, "Positive: cos returns sine for PI/2");
    }

    @Test(groups = {"trignometry"})
    public void trigonometryTgTest() {
        double angle = Math.PI / 4;
        double actualTg = calculator.tg(angle);

        double expectedTg = 1.0;
        Assert.assertEquals(actualTg, expectedTg, 0.0001,
                "Testcase failed: Expected tangent (tg) for angle PI/4 to be [" + expectedTg +
                        "] but found [" + actualTg + "]");
    }


    @Test(dataProvider = "longPairDataProvider", dataProviderClass = DataProviderClass.class)
    public void failedLongArithmeticTest(long a, long b) {
        long wrongExpectedSum = a + b + 1; // Incorrect value
        Assert.assertEquals(calculator.sum(a, b), wrongExpectedSum, "Failed: Intentional wrong long sum");
    }


    @Test(dataProvider = "doublePairDataProvider", dataProviderClass = DataProviderClass.class)
    public void failedDoubleArithmeticTest(double a, double b) {
        double wrongExpectedPow = Math.pow(a, Math.floor(b)) + 1.0; // Incorrect value
        Assert.assertEquals(calculator.pow(a, b), wrongExpectedPow, 0.0001, "Failed: Intentional wrong double pow");
    }


    @Test(dataProvider = "longDataProvider", dataProviderClass = DataProviderClass.class)
    public void failedLongSingleTest(long value) {
        // Force failure: if value > 0, we expect false (which is wrong).
        Assert.assertFalse(calculator.isPositive(value), "Failed: Intentional wrong isPositive for " + value);
    }


    @Test(dataProvider = "doubleDataProvider", dataProviderClass = DataProviderClass.class)
    public void failedDoubleSingleTest(double value) {
        double wrongExpectedSqrt = Math.sqrt(Math.abs(value)) + 1.0; // Incorrect value
        Assert.assertEquals(calculator.sqrt(value), wrongExpectedSqrt, 0.0001, "Failed: Intentional wrong sqrt for " + value);
    }


    @Test(dependsOnGroups = {"positive"})
    public void failedExceptionTest() {
        try {
            calculator.div(100, 0);
            Assert.fail("Failed: Division by zero should throw exception");
        } catch (NumberFormatException e) {

            Assert.fail("Failed: Caught exception as expected, but marking test as failure intentionally");
        }
    }


    @AfterMethod
    public void afterMethod() {
        System.out.println("After Method: " + Thread.currentThread().getName());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        System.out.println("AfterClass: Cleaning up Calculator instance.");
        calculator = null;
    }

}