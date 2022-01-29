package com.backend.frontend.login;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.function.Function;

public class LoginPage {

    private WebDriver drajver;

    @FindBy(id = "user_name")
    private WebElement korisnickoIme;

    @FindBy(id = "password")
    private WebElement lozinka;

    @FindBy(id = "login_button")
    private WebElement dugme;

    public LoginPage(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public void prijava(String korIme, String loz) {
        this.proveraStrane();

        this.korisnickoIme.sendKeys(korIme);
        this.lozinka.sendKeys(loz);
        this.dugme.click();
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//input[@id='user_name']"));
            }
        });
        return true;
    }
}
