package GropID.ArtID;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EfileSubmissionMail {
	static WebDriver driver = null;
	static JavascriptExecutor js;
	static WebDriverWait wbwait;
	public static void main(String[] args) {


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
		
		driver.findElement(By
				.xpath("//*[text()='Customer Portal E-File Submission']"))
				.click();
		 WebElement legalStatement = driver.findElement(By.xpath("//table//p//a[contains(., 'Legal Statement')]"));
			WebElement PrivacyPolicy = driver.findElement(By.xpath("//table//p//a[contains(., 'Privacy Policy')]"));
	}

}
