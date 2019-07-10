package testmeapp.utility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Drivers {
		static WebDriver driver;
		public static WebDriver getDriver(String browser)
		{
			if (browser.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver","C:\\Users\\Training_c2a.04.30\\Desktop\\Drivers\\chromedriver.exe");
				driver = new ChromeDriver();
//				driver.get("https://www.google.com");
			}
			else 
			System.out.println("invalid browser");
			return driver;
		}
		

}
