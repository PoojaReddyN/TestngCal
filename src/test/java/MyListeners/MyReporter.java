package MyListeners;

import org.testng.IReporter;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map;
import java.io.*;

public class MyReporter implements IReporter {

    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory){

        System.out.println("reporter executed!!!!!");
        for (XmlSuite suite : xmlSuites) {
            System.out.println("Suite Name: " + suite.getName());
            System.out.println("Parallel Execution Mode: " + suite.getParallel());
        }

        for (ISuite suite : suites) {
            System.out.println("Suite Name: " + suite.getName());
            Map<String, ISuiteResult> results = suite.getResults();
            for (String testName : results.keySet()) {
                System.out.println("Test Name: " + testName);
            }
        }

        System.out.println("Reports will be saved in: " + outputDirectory);
        File reportFile = new File(outputDirectory + "/custom-report.html");


        List<XmlSuite> list = xmlSuites.stream().toList();
        System.out.println(list);
        List<ISuite> list1 = suites.stream().toList();
        System.out.println(list1);
        System.out.println(outputDirectory);

    }


}