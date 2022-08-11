package marathon2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EditOpportunity {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/* Test Steps:
			1. Login to https://login.salesforce.com
			2. Click on toggle menu button from the left corner
			3. Click view All and click Sales from App Launcher
			4. Click on Opportunity tab 
			5. Search the Opportunity 'Salesforce Automation by *Your Name*'
			6. Click on the Dropdown icon and Select Edit
			7. Choose close date as Tomorrow date
			8. Select 'Stage' as Perception Analysis
			9. Select Deliver Status as In Progress
			10. Enter Description as SalesForce
			11. Click on Save and Verify Stage as Perception Analysis*/
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);			
		driver.get("https://login.salesforce.com/");		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.id("username")).sendKeys("ramkumar.ramaiah@testleaf.com");
		driver.findElement(By.id("password")).sendKeys("Password$123");
		driver.findElement(By.id("Login")).click();
		driver.findElement(By.className("slds-icon-waffle")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		Thread.sleep(3000);
		
		WebElement element = driver.findElement(By.xpath("//span[text()='Opportunities']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);	
		Thread.sleep(5000);
		WebElement findElement = driver.findElement(By.xpath("//input[@placeholder='Search this list...']"));
		findElement.sendKeys("Salesforce Automation by *Hemamalini*",Keys.ENTER);
		findElement.sendKeys(Keys.ENTER);
		Thread.sleep(5000);
		driver.findElement(By.xpath("(//div[@class='forceVirtualActionMarker forceVirtualAction'])[1]")).click();
		driver.findElement(By.xpath("//a[@title='Edit']")).click();
		Thread.sleep(2000);
	    driver.findElement(By.xpath("(//span[text()='Private']/following::input)[2]")).click();	    
	    driver.findElement(By.xpath("//input[@name='CloseDate']")).clear();
	    driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys("8/5/2022");	
	    Thread.sleep(3000);
	    driver.findElement(By.xpath("//label[text()='Stage']/following::span")).click();

	    
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@title='Perception Analysis']")).click();
			 
		WebElement delivery = driver.findElement(By.xpath("//span[text()='Last Modified By']"));		
		 Actions a = new Actions(driver);
		 a.scrollToElement(delivery).perform();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='Delivery/Installation Status']/following::button")).click();
			driver.findElement(By.xpath("//span[text()='In progress']")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("//label[text()='Description']/following::textarea")).sendKeys("SalesForce");
			driver.findElement(By.xpath("//button[text()='Save']")).click();
			Thread.sleep(5000);
			String text = driver.findElement(By.xpath("//span[text()='Perception Analysis']")).getText();
			System.out.println(text);
	}

}
