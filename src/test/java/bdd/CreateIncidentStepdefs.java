package bdd;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.kolorobot.icm.account.Account;
import com.github.kolorobot.icm.account.User;
import com.github.kolorobot.icm.incident.Address;
import com.github.kolorobot.icm.incident.Incident;
import com.github.kolorobot.icm.incident.IncidentRepository;
import com.github.kolorobot.icm.incident.IncidentService;

import cucumber.api.java8.En;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;

import static org.mockito.Mockito.*;

public class CreateIncidentStepdefs implements En {

	@InjectMocks
	private IncidentService objectUnderTest = new IncidentService();

	@Mock
	private IncidentRepository incidentRepositoryMock;

	private User user;
	private Incident incident;

	@cucumber.api.java.Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Given("^Użytkownik w roli (.*)$")
	public void uzytkownik_w_roli(String rola) {
		Account account = createAccount(Account.ROLE_USER);
		user = new User(account);
	}

	@Given("^Dane incydentu: Typ incydentu: (.*),  Opis incydentu: (.*), Ulica, numer domu/mieszkania: (.*), Kod, Miasto: (.*)$")
	public void dane_incydentu(String typ, String opis, String adres,
			String miasto) {
		incident = new Incident();
		incident.setIncidentType(typ);
		incident.setDescription(opis);
		Address address = new Address();
		address.setAddressLine(adres);
		address.setCityLine(miasto);
		incident.setAddress(address);
	}

	@When("^Utwórz incydent")
	public void utworz_incydent() {
		objectUnderTest.createIncident(user, incident.getIncidentType(),
				incident.getDescription(), incident.getAddress()
						.getAddressLine(), incident.getAddress().getCityLine());
	}
	
	@Then("^Incydent powinien zapisać się w bazie$")
	public void incydent_powinien_sie_zapisac() {
		verify(incidentRepositoryMock, times(1)).save(anyObject());
	}

	private Account createAccount(String role) {
		Account account = new Account("name", "email", "password", role);
		account.setId(1L);
		return account;
	}
}
