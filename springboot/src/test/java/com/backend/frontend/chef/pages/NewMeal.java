package com.backend.frontend.chef.pages;

import com.backend.frontend.constants.ChefConstants;
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

public class NewMeal {

    private WebDriver drajver;

    @FindBy(id = "add_new_meal_button")
    private WebElement dodajDugme;

    @FindBy(id = "return_button")
    private WebElement nazadDugme;

    @FindBy(id = "name")
    private WebElement imeJela;

    @FindBy(id = "description")
    private WebElement opisJela;

    @FindBy(id = "type")
    private WebElement tipJela;

    @FindBy(id = "time")
    private WebElement vremePripremeJela;

    @FindBy(id = "heavy")
    private WebElement tezinaPripremeJela;

    @FindBy(id = "amountNumber")
    private WebElement kolicnaJela;

    @FindBy(id = "amountUnit")
    private WebElement mernaJedinica;

    @FindBy(id = "g")
    private WebElement gMernaJedinica;

    @FindBy(id = "easy")
    private WebElement lakoTezinaJela;

    @FindBy(id = "cold")
    private WebElement hladnaTipJela;

    public NewMeal(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='add_new_meal_button']"));
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
                return drajver.findElement(By.xpath("//button[@id='add_new_meal_button']"));
            }
        });
        this.imeJela.sendKeys(ChefConstants.NOVO_JELO);
        this.opisJela.sendKeys(ChefConstants.JELO_TEK_DODATO);
        this.tipJela.click();
        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//mat-option[@id='cold']"));
            }
        });
        this.hladnaTipJela.click();
        this.vremePripremeJela.sendKeys("2");
        this.tezinaPripremeJela.click();
        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//mat-option[@id='easy']"));
            }
        });
        this.lakoTezinaJela.click();
        this.kolicnaJela.sendKeys("250");
        this.mernaJedinica.click();
        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//mat-option[@id='kg']"));
            }
        });
        this.gMernaJedinica.click();
        this.dodajDugme.click();
    }

    public void vratiNazad() {
        this.proveraStrane();
        this.nazadDugme.click();
    }
}
