package com.backend.frontend.admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.function.Function;

public class Tables {
    private WebDriver drajver;

    @FindBy(name = "10")
    private WebElement table;

    @FindBy(id = "big-btn")
    private WebElement button;

    public Tables(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public void shouldClickAndHoldAndReleaseOnElement() {

        WebElement sto = drajver.findElement(By.name("10"));
        WebElement restoran = drajver.findElement(By.id("restaurant"));
        Actions actions = new Actions(drajver);

        //Move tile3 to the position of tile2
        actions.clickAndHold(sto)
                .release(restoran) //ili pomeriti za offset
                .perform();
    }

    public void klik() {
        this.button.click();
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='big-btn']"));
            }
        });
        return true;
    }
}
