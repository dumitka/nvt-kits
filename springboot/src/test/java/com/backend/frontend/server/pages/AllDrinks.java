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

public class AllDrinks {

    private WebDriver drajver;

    @FindBy(id = "kategorija")
    private WebElement kategorija;

    @FindBy(id = "alkoholna")
    private WebElement kategorijaAlkoholno;

    @FindBy(id = "sve")
    private WebElement kategorijaSve;

    @FindBy(id = "pretraga")
    private WebElement pretraga;

    @FindBy(id = "searchIkona")
    private WebElement pretragaIkona;

    @FindBy(id = "slikaPica6")
    private WebElement jelenSlika;

    @FindBy(id = "digmePica3")
    private WebElement jabukaIzbrisiDugme;

    @FindBy(id = "dugmeIzbrisi")
    private WebElement izbrisiDugme;

    @FindBy(xpath = "//h1/a")
    private WebElement linkProfil;

    public AllDrinks(WebDriver d) {
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

    public boolean proveraJelen() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//img[@id='slikaPica6']"));
            }
        });
        return true;
    }

    public boolean proveraZajecarac() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//img[@id='slikaPica10']"));
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
                return drajver.findElement(By.xpath("//img[@id='slikaPica5']"));
            }
        });
        return true;
    }

    public boolean proveraJabuka() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//img[@id='slikaPica3']"));
            }
        });
        return true;
    }

    // pronadji Jabuku
    public void pretraga() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//input[@id='pretraga']"));
            }
        });

        // da budem sigurna da su sve kategorije selektovane
        this.kategorija.click();
        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//mat-option[@id='sve']"));
            }
        });
        this.kategorijaSve.click();

        this.pretraga.clear();
        this.pretraga.sendKeys(ServerConstants.JELEN);
        this.pretragaIkona.click();
        int brSlika = this.drajver.findElements(By.xpath("//img")).size();
    }

    // pronadji Jelen
    public void filer() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//input[@id='pretraga']"));
            }
        });

        this.kategorija.click();
        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//mat-option[@id='sve']"));
            }
        });
        this.kategorijaAlkoholno.click();
    }

    // pronadji Jelen
    public void pretragaFilter() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//input[@id='pretraga']"));
            }
        });

        this.pretraga.clear();
        this.pretraga.sendKeys(ServerConstants.JELEN);
        this.kategorija.click();
        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//mat-option[@id='sve']"));
            }
        });
        this.kategorijaAlkoholno.click();
    }

    public void dvoklikNaSliku() {
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        // da budem sigurna da ima slika piva
        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//img[@id='slikaPica6']"));
            }
        });

        Actions actions = new Actions(this.drajver);
        actions.moveToElement(this.jelenSlika).doubleClick().perform();
    }

    public void izbrisiPice() {
        this.proveraStrane();
        this.jabukaIzbrisiDugme.click();

        // da li se pojavio prozor
        Wait<WebDriver> cekanje = new FluentWait<WebDriver>(this.drajver).withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);

        cekanje.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return drajver.findElement(By.xpath("//button[@id='dugmeOdustani']"));
            }
        });

        this.izbrisiDugme.click();
    }

    public boolean proveraBroja(int broj) {
        int brSlika = this.drajver.findElements(By.xpath("//img")).size();
        return broj == brSlika;
    }

    public void vratiNaProfil() {
        this.proveraStrane();
        this.linkProfil.click();
    }
}
