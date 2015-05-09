package bdd.selenium;

import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by cyprian on 09.05.15.
 */
public class CreateIncidentSeleniumStepdefs {

    private String appAddress;
    private String lang;
    private WebDriver webDriver;
    private String checkPath;
    private String user;
    private String password;

    public CreateIncidentSeleniumStepdefs() {
        this.webDriver = new SharedDriver();
    }

    @Given("^Dane formularza$")
    public void Dane_formularza(DataTable arg1) throws Throwable {
        Map<String, String> values = arg1.asMap(String.class, String.class);
        for (Map.Entry<String, String> val : values.entrySet()) {
            webDriver.findElement(By.id(val.getKey())).clear();
            webDriver.findElement(By.id(val.getKey())).sendKeys(val.getValue());
        }
    }

    @Given("^Aplikacja pod adresem (.+)/$")
    public void Aplikacja_pod_adresem_http_localhost_(String arg1) throws Throwable {
        this.appAddress = arg1;
        webDriver.get(this.appAddress);
    }

    @Given("^Aktualny język to (.+)$")
    public void Aktualny_język_to_pl(String arg1) throws Throwable {
        this.lang = arg1;
    }

    @Given("^Użytkownik zalogowany (.+) z hasłem (.+)$")
    public void Użytkownik_zalogowany_z_haslem(String arg1, String arg2) throws Throwable {
        this.user = arg1;
        this.password = arg2;
        webDriver.navigate().to(this.appAddress+"/signin");
        webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        webDriver.findElement(By.id("inputEmail")).clear();
        webDriver.findElement(By.id("inputEmail")).sendKeys(this.user);
        webDriver.findElement(By.id("inputPassword")).clear();
        webDriver.findElement(By.id("inputPassword")).sendKeys(this.password);
        webDriver.findElement(By.id("signin")).click();
    }

    @Given("^Sprawdzana strona pod ścieżką (.+)$")
    public void Sprawdzana_strona_pod_ścieżką_incident_html(String arg1) throws Throwable {
        this.checkPath = arg1;
        webDriver.navigate().to(this.appAddress + this.checkPath);
    }

    @When("^Kliknięty przycisk (.+)$")
    public void Kliknięty_przycisk_save(String arg1) throws Throwable {
        webDriver.findElement(By.id(arg1)).click();
    }

    @Then("^Pojawi się komunikat poprawnego zapisania$")
    public void Pojawi_się_komunikat_poprawnego_zapisania() throws Throwable {
        // Express the Regexp above with the code you wish you had
        throw new PendingException();
    }

    @Then("^Pojawi się alert (.+)$")
    public void Pojawi_sie_alert(String arg1) throws Throwable {
        assertNotNull(webDriver.findElement(By.id("alert")));
        assertEquals(arg1, webDriver.findElement(By.id("alert")).getText());
    }

    @Then("^Błędne pole (.+)$")
    public void Bledne_pole(String arg1) throws Throwable {
        assertNotNull(webDriver.findElement(By.id(arg1)));
        assertEquals("form-group has-error", webDriver.findElement(By.id(arg1)).findElement(By.xpath("../..")).getAttribute("class"));
    }
}
