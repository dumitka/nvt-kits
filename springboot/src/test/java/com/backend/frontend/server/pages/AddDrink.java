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

public class AddDrink {

    private WebDriver drajver;

    @FindBy(id = "ime")
    private WebElement nazivPica;

    @FindBy(id = "opis")
    private WebElement opisPica;

    @FindBy(id = "kategorija")
    private WebElement kategorijaPica;

    @FindBy(id = "alkoholno")
    private WebElement kategorijAlkoholno;

    @FindBy(id = "kolicina")
    private WebElement kolicina;

    @FindBy(id = "jedinica")
    private WebElement mernaJedinica;

    @FindBy(id = "l")
    private WebElement litar;

    @FindBy(id = "slika")
    private WebElement slikaPica;

    @FindBy(id = "pivo")
    private WebElement pivoSlika;

    @FindBy(id = "dugmeSacuvaj")
    private WebElement dugmeSacuvaj;

    @FindBy(xpath = "//h1/a")
    private WebElement linkProfil;

    public AddDrink(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='dugmeSacuvaj']"));
            }
        });
        return true;
    }

    public void popunjavanjePodataka() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='dugmeSacuvaj']"));
            }
        });
        this.nazivPica.sendKeys("Zajecarac");
        this.opisPica.sendKeys("Najbolje pivo ikad");
        this.kategorijaPica.click();
        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//mat-option[@id='alkoholno']"));
            }
        });
        this.kategorijAlkoholno.click();
        this.kolicina.sendKeys("0.5");
        this.mernaJedinica.click();
        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//mat-option[@id='l']"));
            }
        });
        this.litar.click();
        this.slikaPica.click();
        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//mat-option[@id='pivo']"));
            }
        });
        this.pivoSlika.click();
        this.dugmeSacuvaj.click();
    }

    public void vratiNaProfil() {
        this.proveraStrane();
        this.linkProfil.click();
    }
}
