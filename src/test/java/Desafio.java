import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Desafio {
	private static ChromeDriverService service;
	private static WebDriver driver;

	@BeforeClass
	public static void createAndStartService() throws IOException {
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("driver/chromedriver.exe"))
				.usingAnyFreePort()
				.build();
		service.start();
	}

	@AfterClass
	public static void createAndStopService() {
		if (driver != null) {
			driver.quit();
		}
		service.stop();
	}

	@Before
	public void createDriver() {
		driver = new RemoteWebDriver(service.getUrl(),
				DesiredCapabilities.chrome());
	}

	@After
	public void quitDriver() {

	}

	@Test
	public void desafioUm() {
		
		driver.get("https://www.grocerycrud.com/demo/bootstrap_theme");

		CustomerPage page = new CustomerPage(driver);
		page.selectVersion("Bootstrap V4 Theme");
		page.clickAddCustomer();

		AddPage add = new AddPage(driver);
		add.fillName("Teste Sicredi");
		add.fillLastName("Teste");
		add.fillContactFirstName("Caroline");
		add.fillPhone("51 9999-9999");
		add.fillAddressLine1("Av Assis Brasil, 3970");
		add.fillAddressLine2("Torre D");
		add.fillCity("Porto Alegre");
		add.fillState("RS");
		add.fillPostalCode("91000-000");
		add.fillCountry("Brasil");
		add.selectFromEmployeer("Fixter");
		add.fillCreditLimit("200");
		add.clickSave();

		String msg = "Your data has been successfully stored into the database. Edit Customer or Go back to list";

		assertEquals("Verificando mensagem de sucesso", msg, add.searchMessageSuccess());
	}

	@Test
	public void desafioDois() throws InterruptedException {
		desafioUm();

		AddPage add = new AddPage(driver);
		add.clickGoBackToList();
		
		CustomerPage page = new CustomerPage(driver);
		page.fillSearchLupa("Teste Sicredi");
		page.clickCheckbox();
		page.clickDelete();
		
		PopUpPage popup = new PopUpPage(driver);
		String msg1 = "Are you sure that you want to delete this 1 item?";
		assertEquals("Verificando mensagem de confirmacao delete", msg1, popup.searchMessageDelete());
		popup.clickDelete();
		
		String msg2 = "Your data has been successfully deleted from the database.";
		assertEquals("Verificando mensagem de deletado com sucesso", msg2, page.searchMessageSuccessDelete());
	}
}
