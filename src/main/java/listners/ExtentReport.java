package listners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import base.BasePage;

public class ExtentReport extends BasePage implements ITestListener {

	private static final String OUTPUT_FOLDER = "./HTML Report/";
	private static final String FILE_NAME = "TestExecutionReport.html";

	private static ExtentReports extent = init();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();

	private static ExtentReports init() {

		Path path = Paths.get(OUTPUT_FOLDER);
		// if directory exists?
		if (!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				// fail to create directory
				e.printStackTrace();
			}
		}
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_FOLDER + FILE_NAME);
		htmlReporter.config().setDocumentTitle("Automation Test Results");
		htmlReporter.config().setReportName("Cibi Karthik Report");
		htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
		htmlReporter.config().setTheme(Theme.STANDARD);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setReportUsesManualConfiguration(true);

		return extent;
	}

	public synchronized void onStart(ITestContext context) {
		log.info("Test Suite Started!");
	}

	public synchronized void onFinish(ITestContext context) {
		log.info(("Test Suite is Ending!"));
		extent.flush();
		test.remove();
	}

	public synchronized void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		String qualifiedName = result.getMethod().getQualifiedName();
		int last = qualifiedName.lastIndexOf(".");
		int mid = qualifiedName.substring(0, last).lastIndexOf(".");
		String className = qualifiedName.substring(mid + 1, last);
		log.info(methodName + " started!");
		ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
				result.getMethod().getDescription());
		extentTest.assignCategory(result.getTestContext().getSuite().getName());
		extentTest.assignCategory(className);
		test.set(extentTest);
		test.get().getModel().setStartTime(getTime(result.getStartMillis()));
	}

	public synchronized void onTestSuccess(ITestResult result) {
		log.info((result.getMethod().getMethodName() + " --- Passed!"));
		test.get().pass("Test passed");
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailure(ITestResult result) {
		log.error((result.getMethod().getMethodName() + " --- Failed!"));
		try {
			test.get().fail(result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		} catch (IOException e) {
			log.error("Exception thrown while updating test fail status " + Arrays.toString(e.getStackTrace()));
		}
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestSkipped(ITestResult result) {
		log.warn((result.getMethod().getMethodName() + " --- Skipped!"));
		try {
			test.get().skip(result.getThrowable(),
					MediaEntityBuilder.createScreenCaptureFromPath(getScreenshot()).build());
		} catch (IOException e) {
			log.error("Exception thrown while updating test skip status " + Arrays.toString(e.getStackTrace()));
		}
		test.get().getModel().setEndTime(getTime(result.getEndMillis()));
	}

	public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		log.warn(("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName()));
	}

	private Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}

}
