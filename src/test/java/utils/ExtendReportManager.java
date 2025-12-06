package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtendReportManager {
//    attribute giup tao file HTML report
    private static ExtentReports extend;

//    attribute tuong tac truc tiep voi test va render
//    infor test case thong qua extent
    private static ExtentTest test;

//    ham khoi tao
    public static ExtentReports getInstance() {
        if (extend == null) {
//            tao ten file voi format timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = System.getProperty("user.dir") + "/" + ConfigReader.getReportPath() + "/Report_" + timestamp + ".html";

//            tao ExtentSparkReporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);

//            cau hinh report: title, report name, theme, ngay tao report,...
            sparkReporter.config().setDocumentTitle("Orange HRM test");
            sparkReporter.config().setReportName("Selenium test execution");
            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

            extend = new ExtentReports();
            extend.attachReporter(sparkReporter);

        }
        return extend;
    }

//    ham tao test case moi
    public static ExtentTest createTest(String testName, String description) {
        test = getInstance().createTest(testName, description);
        return test;
    }

    public static ExtentTest getTest() {
        return test;
    }

//    ham flush de ghi info test case ra file
    public static void flushReport() {
        if(extend != null) {
            extend.flush();
        }
    }
}
