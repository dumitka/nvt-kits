package com.backend.frontend.chef.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.backend.frontend.cook.pages.Utilities;

public class CurrentMenuMeals {
	
	private WebDriver driver;
	
	@FindBy(id = "back_button")
    private WebElement backButton;

    @FindBy(id = "add_button")
    private WebElement addButton;

    @FindBy(id = "row1")
    private WebElement row1;

    @FindBy(xpath = "//table//tbody")
	private WebElement bodyOfTable;
    
    @FindBy(xpath = "//table")
	private WebElement table;
	
    
    
	public CurrentMenuMeals(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	
	public boolean ImOnCurrentMenuMealsPage() {
		Utilities.clickableWait(driver, this.table, 20);
		return true;
	}
	
	
	
	public boolean checkTableNotEmpty() {
		WebElement body = Utilities.visibilityWait(driver, this.bodyOfTable, 10);
		if(body == null) {
			return false;
		}
		return true;
	}
	
	
	
	public void clickAddButton() {
		Utilities.clickableWait(driver, this.addButton, 10).click();
	}
	
	public void clickBackButton() {
		Utilities.clickableWait(driver, this.backButton, 10).click();
	}
	
	public void clickRow1() {
		Utilities.clickableWait(driver, this.row1, 10).click();
	}
	
}
