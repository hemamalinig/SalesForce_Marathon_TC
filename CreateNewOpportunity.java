package marathon2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateNewOpportunity {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		/* Test Steps:
			1. Login to https://login.salesforce.com
			2. Click on toggle menu button from the left corner
			3. Click view All and click Sales from App Launcher
			4. Click on Opportunity tab 
			5. Click on New button
			6. Enter Opportunity name as 'Salesforce Automation by *Your Name*,Get the text and Store it 
			7. Choose close date as Today
			8. Select 'Stage' as Need Analysis
			9. click Save and VerifyOppurtunity Name*/
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
		driver.findElement(By.xpath("//button[text()='View All']")).click();
		driver.findElement(By.xpath("//p[text()='Sales']")).click();
		Thread.sleep(3000);
		
		WebElement element = driver.findElement(By.xpath("//span[text()='Opportunities']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", element);	
		driver.findElement(By.xpath("//div[@title='New']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//label[text()='Opportunity Name']/following::input")).sendKeys("Salesforce Automation by *Hemamalini*");
		Thread.sleep(2000);
	    driver.findElement(By.xpath("(//span[text()='Private']/following::input)[2]")).click();	
	    
	    driver.findElement(By.xpath("//input[@name='CloseDate']")).sendKeys("8/4/2022");
	    Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='--None--']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[@title='Needs Analysis']")).click();
//		try {
//			ele.click();
//		} catch (ElementClickInterceptedException e) {
//			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//			wait.until(ExpectedConditions.stalenessOf(ele));
//			ele.click();
//		}
		driver.findElement(By.xpath("//button[text()='Save']")).click();
		
		String text = driver.findElement(By.xpath("//span[@class='toastMessage slds-text-heading--small forceActionsText']/a")).getAttribute("title");
		System.out.println(text);
		if(text.contains("Hemamalini")){
			System.out.println("success");
		} else {
			System.out.println("Failure");
			}		

	}

}
