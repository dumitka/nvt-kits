package com.backend.frontend.server.pages;

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

public class ServerProfile {

    private WebDriver drajver;

    @FindBy(id = "opcijaSS")
    private WebElement dugmeSefSale;

    @FindBy(id = "opcijaK")
    private WebElement dugmeKonobar;

    public ServerProfile(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='opcijaSS']"));
            }
        });
        return true;
    }

    public void odabir(boolean sefSaleIzabran) {
        this.proveraStrane();

        if (sefSaleIzabran) this.dugmeSefSale.click();
        else this.dugmeKonobar.click();
    }
}
