package DataProviders;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "longPairDataProvider")
    public Object[][] longPairDataProvider() {
        return new Object[][] {
                { 10L, 5L },
                { 20L, 0L },    // will test division-by-zero
                { -10L, 3L }
        };
    }

    // DataProvider for methods that take two double values
    @DataProvider(name = "doublePairDataProvider")
    public Object[][] doublePairDataProvider() {
        return new Object[][] {
                { 2.5, 1.5 },
                { 5.0, 2.0 },
                { -3.3, 4.0 }
        };
    }

    // DataProvider for methods that take a single long value
    @DataProvider(name = "longDataProvider")
    public Object[][] longDataProvider() {
        return new Object[][] {
                { 10L },
                { 0L },
                { -5L }
        };
    }

    // DataProvider for methods that take a single double value
    @DataProvider(name = "doubleDataProvider")
    public Object[][] doubleDataProvider() {
        return new Object[][] {
                { 2.5 },
                { 0.0 },
                { -3.3 }
        };
    }
}
