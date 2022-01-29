package com.backend.frontend.chef;

import com.backend.frontend.chef.pages.*;
import com.backend.frontend.constants.ChefConstants;
import com.backend.frontend.login.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ChefTest {

    private WebDriver drajver;
    private LoginPage loginStrana;
    private ChefProfile profilKuvara;
    private MealCategories kategorijeJela;
    private MealsOfCategory jelaKategorije;
    private MealProfile jeloPrikaz;
    private ChangeMeal jeloIzmena;
    private NewMeal novoJelo;

    @Before
    public void postavljanjeSvega() {
        System.setProperty("webdriver.chrome.driver", "src/test/java/com/backend/frontend/chromedriverDumit.exe");
        this.drajver = new ChromeDriver();

        this.loginStrana = new LoginPage(this.drajver);
        this.profilKuvara = new ChefProfile(this.drajver);
        this.kategorijeJela = new MealCategories(this.drajver);
        this.jelaKategorije = new MealsOfCategory(this.drajver);
        this.jeloPrikaz = new MealProfile(this.drajver);
        this.jeloIzmena = new ChangeMeal(this.drajver);
        this.novoJelo = new NewMeal(this.drajver);

        this.drajver.navigate().to("http://localhost:4200/login");
    }

    @Test
    public void testiranje() throws InterruptedException {
        // loginPage
        assertTrue(this.loginStrana.proveraStrane());
        this.loginStrana.prijava("mara", "mara");

        // chefProfile
        assertTrue(this.profilKuvara.proveraStrane());
        this.profilKuvara.odabir(ChefConstants.JELA);

        // mealCategories
        assertTrue(this.kategorijeJela.proveraStrane());
        this.kategorijeJela.odabir(ChefConstants.TOPLA_PREDJELA);

        // mealsOfCategory
        assertTrue(this.jelaKategorije.proveraStrane());
        assertTrue(this.jelaKategorije.proveraNaslova(ChefConstants.TOPLA_PREDJELA));
        assertTrue(this.jelaKategorije.proveraBroja(6));            // pocetno stanje
        this.jelaKategorije.klikNaDrugoJelo();

        // mealProfile
        assertTrue(this.jeloPrikaz.proveraStrane());
        assertTrue(this.jeloPrikaz.proveraImena(ChefConstants.JELO_ZA_BRISANJE));
        this.jeloPrikaz.odabir(ChefConstants.IZBRISI_JELO);

        // mealsOfCategory
        assertTrue(this.jelaKategorije.proveraStrane());
        assertTrue(this.jelaKategorije.proveraNaslova(ChefConstants.TOPLA_PREDJELA));
        assertTrue(this.jelaKategorije.proveraOpisa(ChefConstants.JELO_ORIGINAL));      // provera opisa
        assertTrue(this.jelaKategorije.proveraBroja(5));            // izbrisano je jelo
        this.jelaKategorije.klikNaDrugoJelo();

        // mealProfile
        assertTrue(this.jeloPrikaz.proveraStrane());
        assertTrue(this.jeloPrikaz.proveraImena(ChefConstants.JELO_ZA_IZMENU));
        this.jeloPrikaz.odabir(ChefConstants.IZMENI_JELO);

        // changeMeal
        assertTrue(this.jeloIzmena.proveraStrane());
        this.jeloIzmena.odustani();

        // mealProfile
        assertTrue(this.jeloPrikaz.proveraStrane());
        assertTrue(this.jeloPrikaz.proveraImena(ChefConstants.JELO_ZA_IZMENU));
        this.jeloPrikaz.odabir(ChefConstants.IZMENI_JELO);

        // changeMeal
        assertTrue(this.jeloIzmena.proveraStrane());
        this.jeloIzmena.izmeni();

        // mealsOfCategory
        assertTrue(this.jelaKategorije.proveraStrane());
        assertTrue(this.jelaKategorije.proveraNaslova(ChefConstants.TOPLA_PREDJELA));
        assertTrue(this.jelaKategorije.proveraBroja(5));            // stanje je isto
        assertTrue(this.jelaKategorije.proveraOpisa(ChefConstants.JELO_IZMENJENO));
        assertFalse(this.jelaKategorije.proveraOpisa(ChefConstants.JELO_ORIGINAL));
        this.jelaKategorije.odabir(ChefConstants.DODAJ_JELO);

        // newMeal
        assertTrue(this.novoJelo.proveraStrane());
        this.novoJelo.vratiNazad();

        // mealsOfCategory
        assertTrue(this.jelaKategorije.proveraStrane());
        assertTrue(this.jelaKategorije.proveraNaslova(ChefConstants.TOPLA_PREDJELA));
        assertTrue(this.jelaKategorije.proveraBroja(5));            // stanje je isto
        this.jelaKategorije.odabir(ChefConstants.DODAJ_JELO);

        // newMeal
        assertTrue(this.novoJelo.proveraStrane());
        this.novoJelo.popunjavanjePodataka();

        // mealsOfCategory
        assertTrue(this.jelaKategorije.proveraStrane());
        assertTrue(this.jelaKategorije.proveraNaslova(ChefConstants.HLADNA_PREDJELA));
        assertTrue(this.jelaKategorije.proveraBroja(7));            // nakon dodavanja
        assertTrue(this.jelaKategorije.proveraOpisa(ChefConstants.JELO_TEK_DODATO));
        this.jelaKategorije.odabir(ChefConstants.NAZAD);

        // mealCategories
        assertTrue(this.kategorijeJela.proveraStrane());
        this.kategorijeJela.odabir(ChefConstants.NAZAD);

        // chefProfile
        assertTrue(this.profilKuvara.proveraStrane());
        this.profilKuvara.odabir(ChefConstants.NAZAD);

        // loginPage
        assertTrue(this.loginStrana.proveraStrane());
    }

    @After
    public void gasenjeSvega() throws Exception {
        this.drajver.quit();
    }
}
