package ixigoAssignment;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class IxigoHomePage {
	WebDriver driver;

	@FindBy(how = How.XPATH, using = "//div[text()='From']/following-sibling::input[@placeholder='Enter city or airport']")
	WebElement fromCity;

	@FindBy(how = How.XPATH, using = "//div[text()='To']/following-sibling::input[@placeholder='Enter city or airport']")
	WebElement toCity;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Depart']")
	WebElement departureDate;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Return']")
	WebElement returnDate;

	@FindBy(how = How.XPATH, using = "//*[text()='Search']")
	WebElement btnSearch;

	@FindBy(how = How.XPATH, using = "//div[text()='Travellers | Class']/following-sibling::input")
	WebElement txtTraveller;

	public IxigoHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setFromCity(String strFromCity) {

		fromCity.click();
		fromCity.sendKeys(strFromCity);
		fromCity.sendKeys(Keys.ENTER);
	}

	public void setToCity(String strToCity) {
		toCity.click();
		driver.findElement(By.xpath("//div[@class='city' and text()='" + strToCity + "']")).click();

		fromCity.sendKeys(Keys.ENTER);
	}

	public void setDepartureDate(String strDate) {
		departureDate.click();
		driver.findElement(By.xpath("(//td[@data-date='" + strDate + "'])[1]")).click();
	}

	public void setReturnDate(String strDate) {
		returnDate.click();
		driver.findElement(By.xpath("(//td[@data-date='" + strDate + "'])[1]")).click();
	}

	public void setTravaller(String strValue) throws InterruptedException {
		txtTraveller.click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(
				"(//div[@class='u-box passanger-class-list flex-container']//span[@data-val='" + strValue + "'])[1]"))
				.click();
	}

	public IxigoResultPage clickSearchButton() {
		btnSearch.click();
		return PageFactory.initElements(driver, IxigoResultPage.class);
	}

	public String getTitle() {
		return driver.getTitle();
	}

}

