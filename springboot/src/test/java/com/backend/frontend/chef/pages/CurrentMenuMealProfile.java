package com.backend.frontend.chef.pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.backend.frontend.cook.pages.Utilities;
import com.google.common.base.Function;

public class CurrentMenuMealProfile {

private WebDriver driver;
	
	@FindBy(id = "change_button")
    private WebElement changeButton;

    @FindBy(id = "delete_button")
    private WebElement deleteButton;

    @FindBy(id = "return_button")
    private WebElement returnButton;
    
    @FindBy(xpath = "//mat-card//mat-card-content//div//div//b")
	private WebElement name;
    
    
    @FindBy(id = "priceee")
    private WebElement priceee;
	
    @FindBy(xpath = "//input[@id='price_input']")
   	private WebElement priceDialog;
    
    @FindBy(id = "change_option")
    private WebElement changeOption;
    
    @FindBy(id = "yes_option")
    private WebElement yesOption;
    

    
    
    
	public CurrentMenuMealProfile(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	
	public String checkNameOfMeal() {
		WebElement bElem = Utilities.visibilityWait(driver, this.name, 5);
		return bElem.getText();
	}
	
	
	public String checkPriceOfMeal() {
		WebElement bElem = Utilities.visibilityWait(driver, this.priceee, 5);
		return bElem.getText();
	}
	
	
	public void clickChangeButton() {
		Utilities.clickableWait(driver, this.changeButton, 10).click();
	}
	
	
	public void clickDeleteButton() {
		Utilities.clickableWait(driver, this.deleteButton, 10).click();
	}
	
	
	public void clickyesOption() {
		Utilities.clickableWait(driver, this.yesOption, 10).click();
	}
	
	public void clickReturnButton() {
		Utilities.clickableWait(driver, this.returnButton, 10).click();
	}

	
	public void clickChangeOption() {
		Utilities.clickableWait(driver, this.changeOption, 20).click();
	}
	
	
	
	public void putPrice(int price) {
		
		Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.driver).withTimeout(Duration.ofSeconds(40))
	            .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

	    cekanje.until(new Function<WebDriver, WebElement>() {
	        @Override
	        public WebElement apply(WebDriver webDriver) {
	            return driver.findElement(By.xpath("//input[@id='price_input']"));
	        }
	    });
		
	    this.priceDialog.clear();
		this.priceDialog.sendKeys(price+"");
	}
	
	
	public boolean ImOnCurrentMenuMealProfile() {
		Utilities.clickableWait(driver, this.changeButton, 20);
		return true;
	}
	
}
