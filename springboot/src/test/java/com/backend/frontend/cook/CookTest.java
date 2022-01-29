package com.backend.frontend.cook;

import com.backend.frontend.constants.ServerConstants;
import com.backend.frontend.cook.pages.CookProfilePage;
import com.backend.frontend.cook.pages.OrdersPage;
import com.backend.frontend.cook.pages.TakenOrdersPage;
import com.backend.frontend.login.LoginPage;
import com.backend.frontend.server.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static com.backend.frontend.constants.CookConstants.NUMBER_OF_ORDERS;
import static com.backend.frontend.constants.CookConstants.NUMBER_OF_TAKEN_ORDRS;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CookTest {

	private WebDriver browser;
	
	//pages
	private LoginPage loginPage;
	private CookProfilePage cookProfilePage;
	private OrdersPage ordersPage;
	private TakenOrdersPage takenOrdersPage;
	
	
	@Before
	public void setSelenium() {
		System.setProperty("webdriver.chrome.driver", "src/test/java/com/backend/frontend/chromedriverTaca.exe");
        this.browser = new ChromeDriver();

        this.loginPage = new LoginPage(this.browser);
        this.cookProfilePage = new CookProfilePage(this.browser);
        this.ordersPage = new OrdersPage(this.browser);
        this.takenOrdersPage = new TakenOrdersPage(this.browser);
        
        browser.manage().window().maximize();
        
        //navigiram
        this.browser.navigate().to("http://localhost:4200/login");
	}
	
	
	 @Test
	 public void cookTest() throws InterruptedException {
        //loginPage
        assertTrue(this.loginPage.proveraStrane());
        
        //on cook profile page
        this.loginPage.prijava("djuro", "djuro");
        assertTrue(this.cookProfilePage.ImOnCookProfilePage());
        
        //on orders page
        this.cookProfilePage.clickOrdersButton();
        assertTrue(this.ordersPage.ImOnOrdersPage());
        
        //table not empty
        boolean notEmpty = this.ordersPage.checkTableNotEmpty();
        assertTrue(notEmpty);
        
        //table has 5 elements
        boolean numOfOrders = this.ordersPage.numberOfOrders(NUMBER_OF_ORDERS);
        assertTrue(numOfOrders);
        
        
        //click on order 2
        this.ordersPage.clickorderOption2();
        
        
        //go on taken orders
        this.ordersPage.clickTakenOrdersButton();
        assertTrue(this.takenOrdersPage.ImOnTakenOrdersPage());
        
        
        //table not empty
        boolean notEmptyTakenOrdersTable = this.takenOrdersPage.checkTableNotEmpty();
        assertTrue(notEmptyTakenOrdersTable);
        
        
        //table has 1 elements
        boolean numOfTakenOrders = this.takenOrdersPage.numberOfOrders(NUMBER_OF_TAKEN_ORDRS);
        assertTrue(numOfTakenOrders);
        
        //click on end button
        this.takenOrdersPage.clickEndButton1();   
             
        //go back on orders table
        this.takenOrdersPage.clickBackButton();
        assertTrue(this.ordersPage.ImOnOrdersPage());
        
        this.ordersPage.clickBackButton();
        assertTrue(this.cookProfilePage.ImOnCookProfilePage());
       
        
        //test logout
        this.cookProfilePage.clickLogoutButton();
        assertEquals("http://localhost:4200/login", browser.getCurrentUrl());
       
        
	 }
	 
	 
	 @After
		public void closeSelenium() {
			//Shutdown the browser
			//browser.quit();
		}
}
