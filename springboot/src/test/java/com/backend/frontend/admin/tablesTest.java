package com.backend.frontend.admin;

import com.backend.frontend.admin.pages.AdminProfile;
import com.backend.frontend.admin.pages.Tables;
import com.backend.frontend.login.LoginPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class tablesTest {
    private WebDriver drajver;
    private LoginPage loginStrana;
    private AdminProfile adminProfile;
    private Tables tablesStrana;

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/com/backend/frontend/chromedriverDumit.exe");
        this.drajver = new ChromeDriver();

        this.loginStrana = new LoginPage(this.drajver);
        this.adminProfile = new AdminProfile(this.drajver);
        this.tablesStrana = new Tables(this.drajver);

        this.drajver.navigate().to("http://localhost:4200/login");
    }

    @Test
    public void tables() throws InterruptedException {

        assertTrue(this.loginStrana.proveraStrane());
        this.loginStrana.prijava("pera", "pera");

        assertTrue(this.adminProfile.proveraStrane());
        this.adminProfile.klik();

        assertTrue(this.tablesStrana.proveraStrane());
        this.tablesStrana.shouldClickAndHoldAndReleaseOnElement();
        this.tablesStrana.klik();

        assertTrue(this.adminProfile.proveraStrane());
        this.adminProfile.odjava();

        assertTrue(this.loginStrana.proveraStrane());
    }
}
