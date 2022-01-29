package com.backend.frontend.cook.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class TakenOrdersPage {
private WebDriver driver;
	
	@FindBy(id = "end_button1")
	private WebElement endButton1;
	
	@FindBy(id = "back_button")
	private WebElement backButton;
	
	
	@FindBy(xpath = "//table")
	private WebElement table;
	
	@FindBy(xpath = "//table//tbody")
	private WebElement bodyOfTable;
	
	
	@FindBy(xpath = "//table//tbody//tr")
	private List<WebElement> productOfTable;
	
	
	public TakenOrdersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	
	
	public void clickEndButton1() {
		Utilities.clickableWait(driver, this.endButton1, 20).click();
	}
	
	public void clickBackButton() {
		Utilities.clickableWait(driver, this.backButton, 10).click();
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
	
	
	public boolean ImOnTakenOrdersPage() {
		Utilities.clickableWait(driver, this.table, 20);
		return true;
	}
	
}
