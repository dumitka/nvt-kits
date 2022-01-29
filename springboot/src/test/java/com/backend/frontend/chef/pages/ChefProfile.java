package com.backend.frontend.chef.pages;

import com.backend.frontend.constants.ChefConstants;
import com.backend.frontend.cook.pages.Utilities;

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

public class ChefProfile {

    private WebDriver drajver;

    @FindBy(id = "orders")
    private WebElement dugmePorudzbina;

    @FindBy(id = "meals")
    private WebElement dugmeJela;

    @FindBy(id = "current_menu")
    private WebElement dugmeTrenMeni;

    @FindBy(id = "new_menu")
    private WebElement dugmeNoviMeni;

    @FindBy(id = "logout")
    private WebElement dugmeOdjava;

    public ChefProfile(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='orders']"));
            }
        });
        return true;
    }

    public void odabir(String izabranaOpcija) {
        this.proveraStrane();

        if (izabranaOpcija.equals(ChefConstants.JELA)) this.dugmeJela.click();
        else if (izabranaOpcija.equals(ChefConstants.PORUDZBINE)) this.dugmePorudzbina.click();
        else if (izabranaOpcija.equals(ChefConstants.JELOVNIK)) this.dugmeNoviMeni.click();
        else if (izabranaOpcija.equals(ChefConstants.NAZAD)) this.dugmeOdjava.click();
        else this.dugmeTrenMeni.click();
    }
    
    
    public void clickLogOut() {
		Utilities.clickableWait(drajver, this.dugmeOdjava, 10).click();
	}
}
