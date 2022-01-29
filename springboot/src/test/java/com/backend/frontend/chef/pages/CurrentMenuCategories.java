package com.backend.frontend.chef.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.backend.frontend.constants.ChefConstants;
import com.backend.frontend.cook.pages.Utilities;

public class CurrentMenuCategories {
	private WebDriver driver;
	
	@FindBy(id = "cold_appetizer_button")
	private WebElement CAButton;
	
	@FindBy(id = "hot_appetizer_button")
    private WebElement HAButton;

    @FindBy(id = "main_meal_button")
    private WebElement MMButton;

    @FindBy(id = "dessert_button")
    private WebElement DButton;

    @FindBy(id = "salate_button")
    private WebElement SButton;

    @FindBy(id = "appendices_button")
    private WebElement AButton;
	
	
	
	public CurrentMenuCategories(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	
	public boolean ImOnCurrentMenuCategoriesPage() {
		Utilities.clickableWait(driver, this.CAButton, 20);
		return true;
	}
	
	
	public void choseOption(String option) {
        if (option.equals(ChefConstants.COLD_A)) this.CAButton.click();
        else if (option.equals(ChefConstants.HOT_A)) this.HAButton.click();
        else if (option.equals(ChefConstants.MAIN_M)) this.MMButton.click();
        else if (option.equals(ChefConstants.DESSERT)) this.DButton.click();
        else if (option.equals(ChefConstants.SALAD)) this.SButton.click();
        else this.AButton.click();
    }
	
}
