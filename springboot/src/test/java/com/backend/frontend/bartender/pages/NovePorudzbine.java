package com.backend.frontend.bartender.pages;

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

public class NovePorudzbine {

    private WebDriver drajver;

    @FindBy(id="take_button")
    private WebElement preuzmiBtn;

    @FindBy(id="taken_button")
    private WebElement preuzete;

    @FindBy(id="back_button")
    private WebElement nazad;

    public NovePorudzbine(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='taken_button']"));
            }
        });
        return true;
    }

    public void klikPreuzete() {
        this.preuzete.click();
    }

    public void klikPreuzmi() {
        this.preuzmiBtn.click();
    }

    public void klikNazad() {
        this.nazad.click();
    }
}
