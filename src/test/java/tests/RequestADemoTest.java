package tests;

import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import base.BaseTest;
import utilities.Constants;

public class RequestADemoTest extends BaseTest {

	@Test(priority = 0)
	public void navToRequestDemo() {
		String title = requestDemo.navigateToRequestDemo();
		System.out.println("Log In page title is: " + title);
		Assert.assertTrue(title.contains(Constants.REQUEST_A_DEMO), "Title Mismatch");
	}

	@Test(priority = 1)
	@Parameters({ "requestDemo", "month", "date", "time" })
	public void selectRequestDemoOption(@Optional String demoOption, @Optional String month, @Optional String date,
			@Optional String time) throws InterruptedException {
		String selectedOption = requestDemo.getRequestDemo(demoOption);
		Assert.assertEquals(selectedOption, Constants.REQUESTDEMO_OPTION,
				"Expected Request A Demo option is not Available/Selected: ");
		String selectedMonth = requestDemo.selectMonth(month);
		Assert.assertTrue(selectedMonth.contains(month));
		String selectedDate = requestDemo.selectDate(date);
		Assert.assertTrue(selectedDate.contains(date));
		String selectedTime = requestDemo.selectTime(time);
		Assert.assertTrue(selectedTime.contains(time));
	}

	@Test(priority = 2)
	public void enterDetailsForRequestDemo() {
		Boolean flag = requestDemo.enterDetails("Cibi", "cibitbe", "Demo Check");
		Assert.assertTrue(flag, "Schedule Demo button is not available");
	}

}
