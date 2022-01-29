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

public class MealCategories {

    private WebDriver drajver;

    @FindBy(id = "cold_appetizer_button")
    private WebElement hladnaPredjelaDugme;

    @FindBy(id = "hot_appetizer_button")
    private WebElement toplaPredjelaDugme;

    @FindBy(id = "main_meal_button")
    private WebElement glavnaJelaDugme;

    @FindBy(id = "dessert_button")
    private WebElement dezertiDugme;

    @FindBy(id = "salate_button")
    private WebElement salateDugme;

    @FindBy(id = "appendices_button")
    private WebElement dodaciDugme;

    @FindBy(id = "profile_button")
    private WebElement profilDugme;

    public MealCategories(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='cold_appetizer_button']"));
            }
        });
        return true;
    }

    public void odabir(String izabranaOpcija) {
        this.proveraStrane();

        if (izabranaOpcija.equals(ChefConstants.TOPLA_PREDJELA)) this.toplaPredjelaDugme.click();
        else if (izabranaOpcija.equals(ChefConstants.HLADNA_PREDJELA)) this.hladnaPredjelaDugme.click();
        else if (izabranaOpcija.equals(ChefConstants.GLAVNA_JELA)) this.glavnaJelaDugme.click();
        else if (izabranaOpcija.equals(ChefConstants.DEZERTI)) this.dezertiDugme.click();
        else if (izabranaOpcija.equals(ChefConstants.SALATE)) this.salateDugme.click();
        else if (izabranaOpcija.equals(ChefConstants.DODACI)) this.dodaciDugme.click();
        else this.profilDugme.click();
    }
}
