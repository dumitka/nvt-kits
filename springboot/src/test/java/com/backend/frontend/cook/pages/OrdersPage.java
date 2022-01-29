package com.backend.frontend.cook.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {
	
	private WebDriver driver;
	
	@FindBy(id = "taken_button")
	private WebElement takenOrdersButton;
	
	
	@FindBy(id = "back_button")
	private WebElement backButton;
	
	
	@FindBy(id = "take_button2")
	private WebElement orderOption2;
	
	@FindBy(id = "take_button3")
	private WebElement orderOption3;
	
	@FindBy(id = "take_button1")
	private WebElement orderOption1;
	
	
	
	@FindBy(xpath = "//table//tbody")
	private WebElement bodyOfTable;
	
	
	@FindBy(xpath = "//table//tbody//tr")
	private List<WebElement> productOfTable;
	
	
	
	
	
	public OrdersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	
	
	
	
	public void clickTakenOrdersButton() {
		Utilities.clickableWait(driver, this.takenOrdersButton, 10).click();
	}
	
	public void clickBackButton() {
		Utilities.clickableWait(driver, this.backButton, 10).click();
	}
	
	

	public void clickorderOption2() {
		Utilities.clickableWait(driver, this.orderOption2, 10).click();
	}
	
	public void clickorderOption3() {
		Utilities.clickableWait(driver, this.orderOption3, 10).click();
	}
	
	public void clickorderOption1() {
		Utilities.clickableWait(driver, this.orderOption1, 10).click();
	}
	
	
	
	
	public boolean checkTableNotEmpty() {
		WebElement body = Utilities.visibilityWait(driver, this.bodyOfTable, 10);
		if(body == null) {
			return false;
		}
		return true;
	}
	
	
	
	public boolean numberOfOrders(int expectedNum) {
		if(this.productOfTable.size() == expectedNum) {
			return true;
		}
		return false;
	}
	
	
	public boolean ImOnOrdersPage() {
		Utilities.clickableWait(driver, this.takenOrdersButton, 20);
		return true;
	}
	
	
	
	
}
