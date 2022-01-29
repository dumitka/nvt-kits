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

public class PreuzetePorudzbine {
    private WebDriver drajver;

    @FindBy(id = "end_button")
    private WebElement zavrsi;

    @FindBy(id = "back_button")
    private WebElement nazad;

    public PreuzetePorudzbine(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='end_button']"));
            }
        });
        return true;
    }

    public void klikZavrsi() {
        this.zavrsi.click();
    }

    public void klikNazad() {
        this.nazad.click();
    }
}
