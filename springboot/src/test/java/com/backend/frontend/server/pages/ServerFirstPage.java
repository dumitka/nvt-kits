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

public class ServerFirstPage {

    private WebDriver drajver;

    @FindBy(id = "dodajPice")
    private WebElement dugmeDodajPice;

    @FindBy(id = "pregledPica")
    private WebElement dugmePregledPica;

    @FindBy(id = "kreirajKP")
    private WebElement dugmeKreirajKP;

    @FindBy(id = "pregledKP")
    private WebElement dugmePregledKP;

    @FindBy(id = "odjava")
    private WebElement dugmeOdjava;

    public ServerFirstPage(WebDriver d) {
        this.drajver = d;
        PageFactory.initElements(this.drajver, this);
    }

    public boolean proveraStrane() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='dodajPice']"));
            }
        });
        return true;
    }

    public void odabir(String odabranoDugme) {
        this.proveraStrane();

        if (odabranoDugme.equals(ServerConstants.DODAJ_PICE)) this.dugmeDodajPice.click();
        else if (odabranoDugme.equals(ServerConstants.PREGLED_PICA)) this.dugmePregledPica.click();
        else if (odabranoDugme.equals(ServerConstants.DODAJ_KP)) this.dugmeKreirajKP.click();
        else if (odabranoDugme.equals(ServerConstants.PREGLED_KP)) this.dugmePregledKP.click();
        else this.dugmeOdjava.click();
    }
}
