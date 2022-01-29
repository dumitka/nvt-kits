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

public class DrinkCardPage {

    private WebDriver drajver;

    @FindBy(id = "dodajPica")
    private WebElement dugmeeDodaj;

    @FindBy(id = "sacuvaj")
    private WebElement dugmeSacuvaj;

    @FindBy(xpath = "//h1/a")
    private WebElement linkProfil;

    public DrinkCardPage(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//table[@id='tabela']"));
            }
        });
        return true;
    }

    public boolean proveraJelen() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//td[@id='naziv6']"));
            }
        });
        return true;
    }

    public boolean proveraVino() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
            .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//td[@id='naziv5']"));
            }
        });
        return true;
    }

    public void vratiNaProfil() {
        this.proveraStrane();
        this.linkProfil.click();
    }

    public void dodajJosPica() {
        this.proveraStrane();
        this.dugmeeDodaj.click();
    }

    public void sacuvaj() {
        this.proveraStrane();
        this.dugmeSacuvaj.click();
    }

    public boolean proveraBroja(int broj) {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//td[@id='naziv6']"));
            }
        });
        int brRedova = this.drajver.findElements(By.xpath("//tr")).size();
        return broj == (brRedova - 1);
    }
}
