package com.backend.frontend.server.pages;

import com.backend.frontend.constants.ServerConstants;
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

public class ChooseDrinks {

    private WebDriver drajver;

    @FindBy(id = "dugmeKP")
    private WebElement dugmeKP;

    @FindBy(id = "dugmePica6")
    private WebElement jelenDugmeDodaj;

    @FindBy(id = "dugmeSacuvaj")
    private WebElement sacuvajDugme;

    @FindBy(id = "slikaPica6")
    private WebElement jelenslika;

    @FindBy(id = "cena")
    private WebElement cena;

    @FindBy(xpath = "//h1/a")
    private WebElement linkProfil;

    public ChooseDrinks(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//input[@id='pretraga']"));
            }
        });
        return true;
    }

    public void prelazakNaKP() {
        this.proveraStrane();
        this.dugmeKP.click();
    }

    public void dvoklikNaSliku() {
        this.proveraStrane();

        Actions actions = new Actions(this.drajver);
        actions.moveToElement(this.jelenslika).doubleClick().perform();
    }

    public void vracanjeNazad() {
        this.proveraStrane();
        this.linkProfil.click();
    }

    public void dodajPiceUKP() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//input[@id='pretraga']"));
            }
        });

        this.jelenDugmeDodaj.click();

        // da li se pojavio prozor
        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='dugmeSacuvaj']"));
            }
        });

        this.cena.sendKeys(ServerConstants.JELEN_CENA + "");
        this.sacuvajDugme.click();
    }
}
