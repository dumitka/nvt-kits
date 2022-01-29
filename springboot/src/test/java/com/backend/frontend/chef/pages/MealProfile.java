package com.backend.frontend.chef.pages;

import com.backend.frontend.constants.ChefConstants;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class MealProfile {

    private WebDriver drajver;

    @FindBy(id = "delete_button")
    private WebElement izbrisiDUgme;

    @FindBy(id = "change_button")
    private WebElement izmeniDugme;

    @FindBy(id = "return_button")
    private WebElement profilDugme;

    @FindBy(id = "yes_option")
    private WebElement daDugme;

    @FindBy(id = "no_option")
    private WebElement neDugme;

    public MealProfile(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='change_button']"));
            }
        });
        return true;
    }

    public void odabir(String izabranaOpcija) {
        this.proveraStrane();

        if (izabranaOpcija.equals(ChefConstants.IZMENI_JELO)) this.izmeniDugme.click();
        else if (izabranaOpcija.equals(ChefConstants.IZBRISI_JELO)) this.izbrisiJelo();
        else this.profilDugme.click();
    }

    private void izbrisiJelo() {
        this.izbrisiDUgme.click();
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='yes_option']"));
            }
        });

        this.neDugme.click();

        WebDriverWait wait = new WebDriverWait(this.drajver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='delete_button']")));
        this.izbrisiDUgme.click();
        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='yes_option']"));
            }
        });
        this.daDugme.click();
    }

    public boolean proveraImena(String imeJela) {
        this.proveraStrane();
        int brojElem = this.drajver.findElements(By.xpath("//b[text() ='" + imeJela +"']")).size();
        return brojElem == 1;
    }

}
