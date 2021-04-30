package ixigoAssignment;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IxigoResultPage {
	WebDriver driver;

	@FindBy(how = How.XPATH, using = "(//div[@class='stops']//span[@class='ixi-icon-tick check-icon'])[1]")
	WebElement cbxNonStop;

	@FindBy(how = How.XPATH, using = "//div[@class='price-group']//div[contains(@class,'c-price')]/following::span[2]")
	List<WebElement> lstPrice;

	@FindBy(how = How.XPATH, using = "//div[text()='Stops']")
	WebElement txtStops;

	public IxigoResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void setNonStop() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(txtStops));
		cbxNonStop.click();
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public boolean isStopsHeaderDisplayed() {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOf(txtStops));
		return txtStops.isDisplayed();
	}

	public ArrayList<String> getFlightdepartureTime() {
		ArrayList<String> lstdepartureTime = new ArrayList<>();
		for (int nIndex = 0; nIndex < lstPrice.size() - 3; nIndex++) {
			int nPrice = Integer.parseInt(lstPrice.get(nIndex).getText());

			List<WebElement> lstDepartureTime = driver.findElements(By.xpath(
					"//div[@class='price-group']//div[contains(@class,'c-price')]/following::span['2']/preceding::div[@class='time-group']/div[1]"));
			for (WebElement we : lstDepartureTime) {
				lstdepartureTime.add(we.getText());

			}

		}
		return lstdepartureTime;
	}

	public ArrayList<String> getFlightPrice() {
		ArrayList<String> lstFlightPrice = new ArrayList<>();
		for (int nIndex = 0; nIndex < lstPrice.size() - 3; nIndex++) {
			int nPrice = Integer.parseInt(lstPrice.get(nIndex).getText());

			lstFlightPrice.add(lstPrice.get(nIndex).getText());
		}

		return lstFlightPrice;
	}

	public ArrayList<String> getFlightName() {
		ArrayList<String> lstFlightName = new ArrayList<>();
		for (int nIndex = 0; nIndex < lstPrice.size() - 3; nIndex++) {
			int nPrice = Integer.parseInt(lstPrice.get(nIndex).getText());

			List<WebElement> lstflightName = driver.findElements(By.xpath(
					"//div[@class='price-group']//div[contains(@class,'c-price')]/following::span['2']/preceding::div[@class='airline-text']/div[1]"));
			for (WebElement we : lstflightName) {
				lstFlightName.add(we.getText());

			}
		}

		return lstFlightName;

	}

	public void printFlightDetails(ArrayList<String> lstFlightName, ArrayList<String> lstDepartureTime,
			ArrayList<String> lstFlightPrice) {
		ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String>>(3);
		aList.add(lstFlightName);
		aList.add(lstDepartureTime);
		aList.add(lstFlightPrice);

		for (int nIndex = 0; nIndex < aList.size(); nIndex++) {
			for (int j = 0; j < aList.get(nIndex).size(); j++) {

				System.out.print(aList.get(nIndex).get(j) + " ");
			}
			System.out.println();
		}
	}
}
