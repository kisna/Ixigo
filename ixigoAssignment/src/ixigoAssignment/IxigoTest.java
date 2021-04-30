package ixigoAssignment;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class IxigoTest {

	WebDriver driver;
	IxigoHomePage ixigoHomePage;

	@BeforeTest
	public void setUp() {
		String driverLocation = "C:/Users/mudit/eclipse-workspace/ixigoAssignment/lib/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", driverLocation);
		
		driver = new ChromeDriver();
		driver.get("https://www.ixigo.com/");
		driver.manage().window().maximize();
	}

	@Test
	public void testFlightBooking() throws InterruptedException {
		ixigoHomePage = new IxigoHomePage(driver);
		// Verifying page title is ixigo
		Assert.assertTrue(ixigoHomePage.getTitle().contains("ixigo"));

		// fill flight booking details
		ixigoHomePage.setFromCity("DEL - New Delhi");

		ixigoHomePage.setToCity("BLR - Bengaluru, India");
		Thread.sleep(2000);
		ixigoHomePage.setDepartureDate("30042021");
		ixigoHomePage.setReturnDate("27062021");
		ixigoHomePage.setTravaller("2");

		IxigoResultPage ixigoResultPage = ixigoHomePage.clickSearchButton();
		
		// implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// verifying the result page
		Assert.assertTrue(ixigoResultPage.isStopsHeaderDisplayed());

		
		// select non stop in stops filter
		ixigoResultPage.setNonStop();
		
		
		// get all airline details (Airline number,departure time, fare)
		ixigoResultPage.printFlightDetails(ixigoResultPage.getFlightName(), ixigoResultPage.getFlightdepartureTime(),
				ixigoResultPage.getFlightPrice());

	}

	 @AfterTest
	 public void quitDriver() {
	 driver.quit();
	 }

}

