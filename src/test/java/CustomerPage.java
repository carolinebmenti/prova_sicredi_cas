import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CustomerPage {
	protected WebDriver driver;
	protected WebDriverWait wait;

	public CustomerPage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, 20);
	}

	public void selectVersion(String versao) {
		WebElement selectVersion = driver.findElement(By.id("switch-version-select"));
		Select combobox = new Select(selectVersion);
		combobox.selectByVisibleText(versao);
	}

	public void clickAddCustomer(){
		driver.findElement(By.cssSelector("#gcrud-search-form > div.header-tools > div.floatL.t5 > a")).click();
	}
	
	public void clickLupa(){
		driver.findElement(By.cssSelector("a.btn.btn-primary.search-button.t5")).click();
	}
	
	public void fillSearchLupa(String name){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/form/div[2]/table/thead/tr[2]/td[3]/input")));
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/form/div[2]/table/thead/tr[2]/td[3]/input")).sendKeys(name);
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/form/div[2]/table/thead/tr[2]/td[3]/input")).sendKeys(Keys.ENTER);
	}
	
	public void clickCheckbox() throws InterruptedException {
		Thread.sleep(3000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/form/div[2]/table/tbody/tr/td[1]/input")));
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/form/div[2]/table/tbody/tr/td[1]/input")).click();
	}
	
	public void clickDelete(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/form/div[2]/table/thead/tr[2]/td[2]/div[1]/a")));
		driver.findElement(By.xpath("/html/body/div[2]/div[2]/div[1]/div[2]/form/div[2]/table/thead/tr[2]/td[2]/div[1]/a")).click();
	}

	public String searchMessageSuccessDelete(){
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body > div.alert.alert-success.growl-animated.animated.bounceInDown")));
		return driver.findElement(By.cssSelector("body > div.alert.alert-success.growl-animated.animated.bounceInDown > span:nth-child(4) > p")).getText();
	}
}
