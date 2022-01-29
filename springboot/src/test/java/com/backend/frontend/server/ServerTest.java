package com.backend.frontend.server;

import com.backend.frontend.constants.ServerConstants;
import com.backend.frontend.login.LoginPage;
import com.backend.frontend.server.pages.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertTrue;

public class ServerTest {

    private WebDriver drajver;
    private LoginPage loginStrana;
    private AddDrink dodajPice;
    private AllDrinks svaPica;
    private ChooseDrinks odabirPica;
    private DrinkCardPage prikazKartePica;
    private DrinkPage prikazPica;
    private DrinkView fiksanPrikaPica;
    private ServerFirstPage prvaStrana;
    private ServerProfile profilSefaSale;

    @Before
    public void postavljanjeSvega() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/com/backend/frontend/chromedriverDumit.exe");
        this.drajver = new ChromeDriver();

        this.dodajPice = new AddDrink(this.drajver);
        this.fiksanPrikaPica = new DrinkView(this.drajver);
        this.loginStrana = new LoginPage(this.drajver);
        this.odabirPica = new ChooseDrinks(this.drajver);
        this.prikazPica = new DrinkPage(this.drajver);
        this.profilSefaSale = new ServerProfile(this.drajver);
        this.prvaStrana = new ServerFirstPage(this.drajver);
        this.svaPica = new AllDrinks(this.drajver);
        this.prikazKartePica = new DrinkCardPage(this.drajver);

        this.drajver.navigate().to("http://localhost:4200/login");
    }

    @Test
    public void testiranje() throws InterruptedException {
        // loginPage
        assertTrue(this.loginStrana.proveraStrane());
        this.loginStrana.prijava("laki", "laki");

        // serverProfile
        assertTrue(this.profilSefaSale.proveraStrane());
        this.profilSefaSale.odabir(true);

        // serverFirstPage
        assertTrue(this.prvaStrana.proveraStrane());
        this.prvaStrana.odabir(ServerConstants.DODAJ_PICE);

        // addDrink
        assertTrue(this.dodajPice.proveraStrane());
        this.dodajPice.popunjavanjePodataka();

        // serverFirstPage
        assertTrue(this.prvaStrana.proveraStrane());
        this.prvaStrana.odabir(ServerConstants.PREGLED_KP);

        // drinkCardPage
        assertTrue(this.prikazKartePica.proveraStrane());
        assertTrue(this.prikazKartePica.proveraBroja(6));           // provera broj artikala 6
        this.prikazKartePica.vratiNaProfil();

        // serverFirstPage
        assertTrue(this.prvaStrana.proveraStrane());
        this.prvaStrana.odabir(ServerConstants.PREGLED_PICA);

        // allDrink
        assertTrue(this.svaPica.proveraStrane());
        assertTrue(this.svaPica.proveraBroja(10));
        this.svaPica.proveraZajecarac();                // proveri da li je dodalo kako valja Zajecarca
        this.svaPica.pretraga();
        Thread.sleep(10000);
        assertTrue(this.svaPica.proveraBroja(6));
        assertTrue(this.svaPica.proveraJabuka());       // pretraga J treba da nadje Jabuku
        assertTrue(this.svaPica.proveraJelen());        // pretraga J treba da nadje Jelen
        this.svaPica.izbrisiPice();                     // brisem Jabuku
        Thread.sleep(10000);
        assertTrue(this.svaPica.proveraBroja(5));
        this.svaPica.vratiNaProfil();                       // provera i da bi pretraga bila cista

        // serverFirstPage
        assertTrue(this.prvaStrana.proveraStrane());
        this.prvaStrana.odabir(ServerConstants.PREGLED_KP);

        // drinkCardPage
        assertTrue(this.prikazKartePica.proveraStrane());
        assertTrue(this.prikazKartePica.proveraJelen());
        assertTrue(this.prikazKartePica.proveraVino());
        assertTrue(this.prikazKartePica.proveraBroja(5));           // provera broj artikala 5, izbrisana Jabuka
        this.prikazKartePica.vratiNaProfil();

        // serverFirstPage
        assertTrue(this.prvaStrana.proveraStrane());
        this.prvaStrana.odabir(ServerConstants.PREGLED_PICA);

        // allDrink
        assertTrue(this.svaPica.proveraStrane());
        this.svaPica.filer();
        Thread.sleep(10000);
        assertTrue(this.svaPica.proveraBroja(4));
        assertTrue(this.svaPica.proveraVino());         // filter alkoholna pica nadje Vino
        assertTrue(this.svaPica.proveraJelen());        // filter alkoholna pica nadje Jelen
        this.svaPica.pretragaFilter();
        Thread.sleep(10000);
        assertTrue(this.svaPica.proveraJelen());        // alkoholna pica sa slovom j nadje Jelen
        this.svaPica.dvoklikNaSliku();

        // drinkPage
        assertTrue(this.prikazPica.proveraStrane());
        this.prikazPica.klikNaIzbrisi();
        this.prikazPica.klikNaIzmeni();

        // addDink
        assertTrue(this.dodajPice.proveraStrane());
        this.dodajPice.vratiNaProfil();

        // serverFirstPage
        assertTrue(this.prvaStrana.proveraStrane());
        this.prvaStrana.odabir(ServerConstants.DODAJ_KP);

        // chooseDrinks
        assertTrue(this.odabirPica.proveraStrane());
        this.odabirPica.dvoklikNaSliku();

        // drinkView
        assertTrue(this.fiksanPrikaPica.proveraStrane());
        assertTrue(this.fiksanPrikaPica.proveraNaziva());       // da li je prikazalo stranu sa Jelen
        this.fiksanPrikaPica.vratiNaProfil();

        // serverFirstPage
        assertTrue(this.prvaStrana.proveraStrane());
        this.prvaStrana.odabir(ServerConstants.DODAJ_KP);

        // chooseDrinks
        assertTrue(this.odabirPica.proveraStrane());
        this.odabirPica.dodajPiceUKP();
        Thread.sleep(10000);
        this.odabirPica.prelazakNaKP();

        // drinkCard
        assertTrue(this.prikazKartePica.proveraStrane());
        assertTrue(this.prikazKartePica.proveraJelen());
        assertTrue(this.prikazKartePica.proveraBroja(1));           // trenutno u kreiranoj kp 1 elem
        this.prikazKartePica.sacuvaj();

        // serverFirstPage
        assertTrue(this.prvaStrana.proveraStrane());
        this.prvaStrana.odabir(ServerConstants.PREGLED_KP);

        // drinkCardPage
        assertTrue(this.prikazKartePica.proveraStrane());
        assertTrue(this.prikazKartePica.proveraJelen());
        assertTrue(this.prikazKartePica.proveraBroja(1));           // provera nakon cuvanja
        this.prikazKartePica.dodajJosPica();

        // chooseDrinks
        assertTrue(this.odabirPica.proveraStrane());
        this.odabirPica.vracanjeNazad();

        //serverFirstPage
        assertTrue(this.prvaStrana.proveraStrane());
        this.prvaStrana.odabir(ServerConstants.PREGLED_PICA);

        // allDrink
        assertTrue(this.svaPica.proveraStrane());
        assertTrue(this.svaPica.proveraBroja(9));
        this.svaPica.vratiNaProfil();

        //serverFirstPage
        assertTrue(this.prvaStrana.proveraStrane());
        this.prvaStrana.odabir(ServerConstants.ODJAVA);

        // loginPage
        assertTrue(this.loginStrana.proveraStrane());
    }

    @After
    public void gasenjeSvega() throws Exception {
        this.drajver.quit();
    }
}
