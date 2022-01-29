package com.backend.frontend.chef;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.backend.frontend.chef.pages.ChefProfile;
import com.backend.frontend.chef.pages.CurrentMenuCategories;
import com.backend.frontend.chef.pages.CurrentMenuMealProfile;
import com.backend.frontend.chef.pages.CurrentMenuMeals;
import com.backend.frontend.constants.ChefConstants;
import com.backend.frontend.cook.pages.CookProfilePage;
import com.backend.frontend.cook.pages.OrdersPage;
import com.backend.frontend.cook.pages.TakenOrdersPage;
import com.backend.frontend.login.LoginPage;

public class CurrentMenuTest {
	private WebDriver browser;
	
	//pages
	private LoginPage loginPage;
	private ChefProfile chefProfile;
	private CurrentMenuCategories currentMenuCategories;
	private CurrentMenuMeals currentMenuMeals;
	private CurrentMenuMealProfile currentMenuMealProfile;
		
	@Before
	public void setSelenium() {
		System.setProperty("webdriver.chrome.driver", "src/test/java/com/backend/frontend/chromedriverTaca.exe");
        this.browser = new ChromeDriver();

        this.loginPage = new LoginPage(this.browser);
        this.chefProfile = new ChefProfile(this.browser);
        this.currentMenuCategories = new CurrentMenuCategories(this.browser);
        this.currentMenuMeals = new CurrentMenuMeals(this.browser);
        this.currentMenuMealProfile = new CurrentMenuMealProfile(this.browser);
        
        browser.manage().window().maximize();
        
        //navigiram
        this.browser.navigate().to("http://localhost:4200/login");
	}
	
	
	@Test
	public void currentMenuTest() throws InterruptedException {
		 // loginPage
        assertTrue(this.loginPage.proveraStrane());
        this.loginPage.prijava("mara", "mara");
        
        
        // chefProfile
        assertTrue(this.chefProfile.proveraStrane());
        this.chefProfile.odabir(ChefConstants.TREN_JELOVNIK);
        
        //on categories page
        assertTrue(this.currentMenuCategories.ImOnCurrentMenuCategoriesPage());
        
        //click os main meal for example
        this.currentMenuCategories.choseOption(ChefConstants.MAIN_M);
        
        //on currentMenuMeals page
        assertTrue(this.currentMenuMeals.ImOnCurrentMenuMealsPage());
        
        //check if table is empty --> not
        assertTrue(this.currentMenuMeals.checkTableNotEmpty());
        
        //click row
        currentMenuMeals.clickRow1();
        //going to current menuMeal profile
        assertTrue(this.currentMenuMealProfile.ImOnCurrentMenuMealProfile());
        
        //check meal name
        String name = this.currentMenuMealProfile.checkNameOfMeal();
        assertEquals(ChefConstants.NAME_OF_MEAL, name);
        
     
        this.currentMenuMealProfile.clickChangeButton();
        
        //check meal price
        this.currentMenuMealProfile.putPrice(1200);
          
        this.currentMenuMealProfile.clickChangeOption();
        
        /*
        String price = this.currentMenuMealProfile.checkPriceOfMeal();
        assertEquals(1200+ " RSD", price);
        
        this.currentMenuMealProfile.clickDeleteButton();
        this.currentMenuMealProfile.clickyesOption();
        assertTrue(this.currentMenuMeals.ImOnCurrentMenuMealsPage());
        
        this.currentMenuMeals.clickBackButton();
        assertTrue(this.chefProfile.proveraStrane());
        
        this.chefProfile.clickLogOut();
        assertTrue(this.loginPage.proveraStrane());
        */
        
	}
		
}
