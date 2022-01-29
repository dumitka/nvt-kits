package com.backend.frontend.cook.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CookProfilePage {
	
	private WebDriver driver;
	
	@FindBy(id = "orders")
	private WebElement ordersButton;
	
	@FindBy(id = "logout")
	private WebElement logOutButton;
	
	
	
	public CookProfilePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public void clickOrdersButton() {
		Utilities.clickableWait(driver, this.ordersButton, 20).click();
	}
	
	public void clickLogoutButton() {
		Utilities.clickableWait(driver, this.logOutButton, 10).click();
	}
	
	
	public boolean ImOnCookProfilePage() {
		Utilities.clickableWait(driver, this.ordersButton, 20);
		return true;
	}
}
