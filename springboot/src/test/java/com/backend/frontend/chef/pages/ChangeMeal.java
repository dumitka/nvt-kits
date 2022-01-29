package com.backend.frontend.chef.pages;

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

public class ChangeMeal {

    private WebDriver drajver;

    @FindBy(id = "change_meal_button")
    private WebElement izmeniDugme;

    @FindBy(id = "return_button")
    private WebElement nazadDugme;

    @FindBy(id = "opis")
    private WebElement labelaOpis;

    public ChangeMeal(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='change_meal_button']"));
            }
        });
        return true;
    }

    public void odustani() {
        this.proveraStrane();
        this.nazadDugme.click();
    }

    public void izmeni() {
        this.proveraStrane();
        this.labelaOpis.sendKeys(", nana");
        this.izmeniDugme.click();
    }
}
