package testmeapp.tests;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import testmeapp.utility.Drivers;

public class OnlineShoppingTest {
	WebDriver driver;
	ExtentReports extent;
	ExtentTest Logger;
	@BeforeTest
	public void startReportBeforetest()
	{
	driver = Drivers.getDriver("chrome");
	driver.get("http://10.232.237.143:443/TestMeApp");
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/group5Reports.html", true);
		extent.addSystemInfo("Host Name", "TestMe");
		extent.addSystemInfo("Environment", "Selenium Testing");
		extent.addSystemInfo("User Name", "group5");
	}
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
	String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
	TakesScreenshot ts = (TakesScreenshot) driver;
	File source = ts.getScreenshotAs(OutputType.FILE);
	String destination = System.getProperty("user.dir") + "/FailedScreenshots/" + screenshotName + dateName
			+ ".png";
	File finalDestination = new File(destination);
	FileUtils.copyFile(source, finalDestination);
	return destination;
	}


	
  @Test (priority=3, enabled=true)
  public void testCart() 
  {
	  
	  Logger = extent.startTest("passTest");
	  Actions act=new Actions(driver);
	  WebDriverWait wait1= new WebDriverWait(driver, 50);
	  wait1.until(ExpectedConditions.presenceOfElementLocated(By.linkText("All Categories")));
	  act.moveToElement(driver.findElement(By.linkText("All Categories"))).build().perform();
	  WebElement Electronics= driver.findElement(By.linkText("Electronics"));
	 act.moveToElement(Electronics).click().build().perform();
//	  Electronics.click();
	 WebDriverWait wait= new WebDriverWait(driver, 50);
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Head Phone")));
	  WebElement HeadPhone=driver.findElement(By.linkText("Head Phone"));
	  act.moveToElement(HeadPhone).click().build().perform();
     driver.findElement(By.linkText("Add to cart")).click();
     driver.findElement(By.xpath("//*[@id='header']/div[1]/div/div/div[2]/div/a[2]")).click();
     Assert.assertEquals(driver.getTitle(), "View Cart");
     driver.findElement(By.xpath("//*[@id=\"cart\"]/tfoot/tr[2]/td[5]/a")).click();
     Assert.assertEquals(driver.getTitle(), "Cart Checkout");
     driver.findElement(By.xpath("//input[@type='submit' and @value='Proceed to Pay']")).click();
     Assert.assertEquals(driver.getTitle(), "Redirecting to Payment Gateway");
     
     Logger.log(LogStatus.PASS, "Test Case Passed is passTest");

	 }
  @Test(priority=2,enabled=true)
  public void testLogin()
  {
	  Logger = extent.startTest("passTest");
	  //driver.findElement(By.linkText("SignIn")).click();
	  //Assert.assertEquals(driver.getTitle(), "Login");
	  WebDriverWait wait= new WebDriverWait(driver, 50);
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("userName")));
	  driver.findElement(By.id("userName")).sendKeys("Aravi12356");
	  driver.findElement(By.id("password")).sendKeys("abcd1234");
	  driver.findElement(By.name("Login")).click();
	  WebDriverWait wait1= new WebDriverWait(driver, 50);
	  wait1.until(ExpectedConditions.titleContains("Home"));
	  Assert.assertEquals(driver.getTitle(),"Home");
	  System.out.println("successful login");  
	  Logger.log(LogStatus.PASS, "Test Case Passed is passTest");
  }
  @Test(priority=4, enabled=true)
  public void testPaymentPass()
  {
	  Logger = extent.startTest("passTest");
	  WebDriverWait wait= new WebDriverWait(driver, 50);
	  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\'swit\']/div[1]/div/label/i")));
	  driver.findElement(By.xpath("//*[@id=\'swit\']/div[1]/div/label/i")).click();
	  driver.findElement(By.xpath("//*[@id=\"btn\"]")).click();
	  Assert.assertEquals(driver.getTitle(), "Payment Gateway");
	  driver.findElement(By.name("username")).sendKeys("123456");
	  driver.findElement(By.name("password")).sendKeys("Pass@456");
	  driver.findElement(By.xpath("//input[@type='submit' and @value='LOGIN'] ")).click();
	  driver.findElement(By.name("transpwd")).sendKeys("Trans@456");
	  driver.findElement(By.xpath("//input[@type='submit' and @value='PayNow'] ")).click();
	  Assert.assertEquals(driver.getTitle(), "Order Details");	
	  Logger.log(LogStatus.PASS, "Test Case Passed is passTest");
  }
  @Test(priority=5, enabled=true)
  public void testPaymentFail()
  {
	  Logger = extent.startTest("failTest");
	  driver = Drivers.getDriver("chrome");
		driver.get("http://10.232.237.143:443/PaymentGateway/getOrderDetails.htm");
		driver.findElement(By.xpath("//*[@id=\'swit\']/div[1]/div/label/i")).click();
		driver.findElement(By.xpath("//*[@id=\"btn\"]")).click();
		  Assert.assertEquals(driver.getTitle(), "Payment Gateway");
		  driver.findElement(By.name("username")).sendKeys("123456");
		  driver.findElement(By.name("password")).sendKeys("Pass@456");
		  driver.findElement(By.xpath("//input[@type='submit' and @value='LOGIN'] ")).click();
		  //driver.findElement(By.name("transpwd")).sendKeys("Trans@456");
		  driver.findElement(By.xpath("//input[@type='submit' and @value='PayNow'] ")).click();
		  Assert.assertEquals(driver.getTitle(), "Order Details");	
		  Logger.log(LogStatus.PASS, "Test Case (failTest) Status is passed");
  }
  @Test(priority=1, enabled=false)
  public void testRegistation()
  {
	  Logger = extent.startTest("passTest");
	  driver.findElement(By.linkText("SignIn")).click();
	  Assert.assertEquals(driver.getTitle(), "Login");                //check login page
	  driver.findElement(By.linkText("New User?Register Here")).click();
	  Assert.assertEquals(driver.getTitle(), "Sign Up");  //check Sign Up page
	  
	  driver.findElement(By.id("userName")).sendKeys("Aravi12356");
	  driver.findElement(By.id("firstName")).click();
	  Assert.assertEquals(driver.findElement(By.xpath("//span[@class='label label-default']")).getText(),"Available");
	  driver.findElement(By.id("firstName")).sendKeys("a");
	  driver.findElement(By.id("lastName")).sendKeys("b");
	  driver.findElement(By.id("password")).sendKeys("abcd1234");
	  driver.findElement(By.id("pass_confirmation")).sendKeys("abcd1234");
	  driver.findElement(By.xpath("//input[@type='radio' and @value='Male']")).click();
	  driver.findElement(By.id("emailAddress")).sendKeys("group5@gmail.com");
	  driver.findElement(By.id("mobileNumber")).sendKeys("9876543219");
	  driver.findElement(By.id("dob")).sendKeys("07/05/2019");
	  driver.findElement(By.id("address")).sendKeys("coimbatore");
	  Select sel= new Select(driver.findElement(By.id("securityQuestion")));
	  sel.selectByValue("411010");
	  driver.findElement(By.id("answer")).sendKeys("coimbatore");
	  driver.findElement(By.xpath("//input[@type='submit' and @value='Register']")).click();
	  Assert.assertEquals(driver.getTitle(), "Login");
	  System.out.println("user registered successfully");
	  Logger.log(LogStatus.PASS, "Test Case Passed is passTest");
	  
  }
  @AfterMethod
  public void getResultAfterMethod(ITestResult result) throws Exception
  {
	  if (result.getStatus() == ITestResult.FAILURE) {
			Logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getName());
			Logger.log(LogStatus.FAIL, "Test Case Failed is " + result.getThrowable());
			String screenshotPath = OnlineShoppingTest.getScreenshot(driver, result.getName());
			Logger.log(LogStatus.FAIL, Logger.addScreenCapture(screenshotPath));
		} else if (result.getStatus() == ITestResult.SKIP) {
			Logger.log(LogStatus.SKIP, "Test Case Skipped is " + result.getName());
		}
		extent.endTest(Logger);
		extent.flush();

  }
  @AfterTest
  public void endReportAftertest()
  
  {
	  driver.quit();
	  extent.close();
	  
  }
}
