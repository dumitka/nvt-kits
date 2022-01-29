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

public class MealsOfCategory {

    private WebDriver drajver;

    @FindBy(xpath = "//h1/a")
    private WebElement naslov;

    @FindBy(id = "add_button")
    private WebElement dodajDugme;

    @FindBy(id = "return_button")
    private WebElement profilDugme;

    @FindBy(id = "polje2")
    private WebElement polje2;

    public MealsOfCategory(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='add_button']"));
            }
        });
        return true;
    }

    public void odabir(String izabranaOpcija) {
        this.proveraStrane();

        if (izabranaOpcija.equals(ChefConstants.DODAJ_JELO)) this.dodajDugme.click();
        else this.profilDugme.click();
    }

    public boolean proveraBroja(int broj) {
        this.proveraStrane();
        int brRedova = this.drajver.findElements(By.xpath("//tr")).size();
        return broj == (brRedova - 1);
    }

    public boolean proveraNaslova(String naslov) {
        this.proveraStrane();
        return this.naslov.getText().equals(naslov);
    }

    public void klikNaDrugoJelo() {
        this.proveraStrane();
        this.polje2.click();
    }

    public boolean proveraOpisa(String opis) {
        this.proveraStrane();
        int brojElem = this.drajver.findElements(By.xpath("//i[text() = '" + opis + "']")).size();
        return 1 == brojElem;
    }
}
