package com.backend.frontend.server.pages;

import com.backend.frontend.constants.ServerConstants;
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

public class DrinkView {

    private WebDriver drajver;

    @FindBy(id = "naziv")
    private WebElement naziv;

    @FindBy(xpath = "//h1/a")
    private WebElement linkProfil;

    public DrinkView(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//span[@id='naziv']"));
            }
        });
        return true;
    }

    public boolean proveraNaziva() {
        this.proveraStrane();
        return this.naziv.getText().equals(ServerConstants.JELEN_NAZIV);
    }

    public void vratiNaProfil() {
        this.proveraStrane();
        this.linkProfil.click();
    }
}
