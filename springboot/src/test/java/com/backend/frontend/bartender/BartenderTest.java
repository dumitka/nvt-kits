package com.backend.frontend.bartender;

import com.backend.frontend.bartender.pages.BartenderProfile;
import com.backend.frontend.bartender.pages.NovePorudzbine;
import com.backend.frontend.bartender.pages.PreuzetePorudzbine;
import com.backend.frontend.login.LoginPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BartenderTest {
    private WebDriver drajver;
    private LoginPage loginStrana;
    private BartenderProfile profile;
    private NovePorudzbine nove;
    private PreuzetePorudzbine preuzete;

    @Before
    public void postavljanjeSvega() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/com/backend/frontend/chromedriverDumit.exe");
        this.drajver = new ChromeDriver();

        this.loginStrana = new LoginPage(this.drajver);
        this.profile = new BartenderProfile(this.drajver);
        this.nove = new NovePorudzbine(this.drajver);
        this.preuzete = new PreuzetePorudzbine(this.drajver);

        this.drajver.navigate().to("http://localhost:4200/login");
    }

    @Test
    public void bartender() throws InterruptedException {

        assertTrue(this.loginStrana.proveraStrane());
        this.loginStrana.prijava("lola", "lola");

        assertTrue(this.profile.proveraStrane());
        this.profile.klik();

        assertTrue(this.nove.proveraStrane());
        this.nove.klikPreuzmi();
        this.nove.klikPreuzete();

        assertTrue(this.preuzete.proveraStrane());
        this.preuzete.klikZavrsi();
        this.preuzete.klikNazad();

        assertTrue(this.nove.proveraStrane());
        this.nove.klikNazad();

        assertTrue(this.profile.proveraStrane());
        this.profile.odjava();

        assertTrue(this.loginStrana.proveraStrane());
    }
}
