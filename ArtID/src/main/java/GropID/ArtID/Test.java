package GropID.ArtID;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test {
	static WebDriver driver = null;
	static JavascriptExecutor js;
	static WebDriverWait wbwait;
	static WebElement loginlink, downloadLink, username, password ;
	

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		js = (JavascriptExecutor) driver;

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://mail.yahoo.com/");

		driver.findElement(By.xpath("//a//span[text()='Sign in']")).click();
		driver.findElement(By.xpath("//input[@id='login-username']")).sendKeys("emeatest40@gmail.com");
		driver.findElement(By.xpath("//input[@id='login-signin']")).click();
		driver.findElement(By.xpath("//input[@id='login-passwd']")).sendKeys("Kimberly2020");
		driver.findElement(By.xpath("//*[@id='login-signin']")).click();
		// by subject Line
		// driver.findElement(By.xpath("//span[@data-test-id='message-subject' and
		// text()='Customer Portal E-File Submission']"));
		driver.findElement(By
				.xpath("//span[@data-test-id='message-subject' and text()='Customer Portal E-File Validation Error']"))
				.click();
		
		// driver.findElement(By.xpath("//span[@data-test-id='message-subject' and
		// text()='Customer Portal Test E-File Validation Error']")).click();
		// subject Line

		// WebElement subjectLine =
		// driver.findElement(By.xpath("//span[@data-test-id='message-group-subject-text'
		// and text()='Customer Portal Test E-File Validation Error']"));
		WebElement lblEfileID = driver.findElement(By.xpath("(//*[contains(@class, 'info-table-two')]//td[1])[1]"));
		WebElement lblEfileName = driver.findElement(By.xpath("(//*[contains(@class, 'info-table-two')]//td[1])[2]"));
		WebElement lblSubmitted = driver.findElement(By.xpath("(//*[contains(@class, 'info-table-two')]//td[1])[3]"));
		WebElement lblUserId = driver.findElement(By.xpath("(//*[contains(@class, 'info-table-two')]//td[1])[4]"));
		WebElement lblUserEmail = driver.findElement(By.xpath("(//*[contains(@class, 'info-table-two')]//td[1])[5]"));
		WebElement lblCustomerName = driver
				.findElement(By.xpath("(//*[contains(@class, 'info-table-two')]//td[1])[6]"));

		WebElement txtEfileID = driver.findElement(By.xpath("(//*[contains(@class, 'info-table-two')]//td[2])[1]"));
		WebElement txtEfileName = driver.findElement(By.xpath("(//*[contains(@class, 'info-table-two')]//td[2])[2]"));
		WebElement txtSubmitted = driver.findElement(By.xpath("(//*[contains(@class, 'info-table-two')]//td[2])[3]"));
		WebElement txtUserId = driver.findElement(By.xpath("(//*[contains(@class, 'info-table-two')]//td[2])[4]"));
		WebElement txtUserEmail = driver.findElement(By.xpath("(//*[contains(@class, 'info-table-two')]//td[2])[5]"));
		WebElement txtCustomerName = driver
				.findElement(By.xpath("(//*[contains(@class, 'info-table-two')]//td[2])[6]"));

		 
		
		 WebElement legalStatement = driver.findElement(By.xpath("//table//p//a[contains(., 'Legal Statement')]"));
			WebElement PrivacyPolicy = driver.findElement(By.xpath("//table//p//a[contains(., 'Privacy Policy')]"));
		
		WebDriverWait wbwait = new WebDriverWait(driver, 10);
		Actions act = new Actions(driver);
		act.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();

		WebElement Errorslbl = driver.findElement(By.xpath("//table[contains(@class, 'content-table')]//h2"));

		String errortxt = Errorslbl.getText();
		System.out.println(errortxt);
		String numberOnly = errortxt.replaceAll("[^0-9]", "");
		System.out.println(numberOnly);
		int countoferrors = Integer.parseInt(numberOnly);
		System.out.println(countoferrors);

		if (countoferrors < 50)
			lessThan50Errors();
		else
			moreThan50Errors();

	}

	public static void lessThan50Errors() throws InterruptedException {
		String currentHandle = driver.getWindowHandle();
		WebElement loginlink = driver.findElement(By.xpath("//table//span//a[contains(.,'Login')]"));
		 WebElement downloadLink = driver.findElement(By.xpath("//table//span//a[contains(.,'Download')]"));
		js.executeScript("arguments[0].click();", loginlink);
		Set<String> handles = driver.getWindowHandles();

		for (String handle : handles) {

			if (!handle.equals(currentHandle)) {
				driver.switchTo().window(handle);
			}
		}
		// driver.switchTo().window(currentHandle);

		Thread.sleep(15000);
		System.out.println(driver.getCurrentUrl());
		WebElement username = driver.findElement(By.xpath("//*[@id='idp-discovery-username']"));
		

		js.executeScript("document.getElementById('idp-discovery-username').value='emeatest40@gmail.com'");
		username.sendKeys(Keys.ENTER);
		System.out.println("username");
		Thread.sleep(2000);

		WebElement password = driver.findElement(By.xpath("//*[@id='okta-signin-password']"));
		js.executeScript("arguments[0].click();", password);
		js.executeScript("document.getElementById('okta-signin-password').value='Abc1234'");
		password.sendKeys(Keys.ENTER);

		WebElement signin = driver.findElement(By.xpath("//input[@id='okta-signin-submit']"));
		js.executeScript("arguments[0].click();", signin);

		Thread.sleep(18000);
		driver.switchTo().window(currentHandle);

		js.executeScript("arguments[0].click();", downloadLink);

	}

	public static void moreThan50Errors() {
System.out.println("more than 50 errors");
	}

}
