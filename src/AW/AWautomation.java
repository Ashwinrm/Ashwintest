package AW;

import static org.testng.Assert.assertEquals;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.jsoup.Connection.Method;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
//import org.openqa.selenium.Point;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AWautomation {

	ExtentReports extent;
	ExtentTest aw;

	ChromAttributes chromeAttributes = new ChromAttributes();

	@BeforeSuite
	public void beforetest() {

		String destFile = null;
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH-mm-ss");
		destFile = "/Users/ashwin/Desktop/Selenium/Testleaf/Selenium/Reports/AwReports/";
		String destDir = dateFormat.format(new Date()) + ".html";
		extent = new ExtentReports(destFile + " " + destDir, true);

		extent.loadConfig(new File("/Users/ashwin/Desktop/Selenium/Testleaf/Selenium/extent-config.xml"));
	}

	@Test(priority = 1)
	public void setChromeAttributes() throws IOException {
		ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(
						new File("/Users/ashwin/Desktop/Selenium/Testleaf/Selenium/Drivers/chromedriver 2.35"))
				.usingAnyFreePort().build();
		service.start();
		chromeAttributes.setService(service);

		// Setting DesiredCapabilities for chrome
		DesiredCapabilities capabilities = new DesiredCapabilities();
		ChromeOptions options = new ChromeOptions();
		chromeAttributes.setCapabilities(capabilities);
		chromeAttributes.setOptions(options);

		// The address string should be in the form of "hostname/ip:port". 9222
		// is the port that you specified in the
		// --remote-debugging-port Chromium
		String remoteDebuggingAddress = "localhost:9222";
		options.setExperimentalOption("debuggerAddress", remoteDebuggingAddress);
		capabilities.setCapability(ChromeOptions.CAPABILITY, options);

		// Create a WebDriver instance using URL provided by the
		// ChromeDriverService and capabilities with
		System.out.println("HEY THIS IS FOR SERVICE URL" + service.getUrl());
		WebDriver driver = new RemoteWebDriver(service.getUrl(), capabilities);
		chromeAttributes.setDriver(driver);
	}

	// Starting of Application

	@Test(priority = 0)
	public void StartAW() {

		try {
			aw = extent.startTest("AW Final Automation Report");
			aw.assignAuthor("Ashwin RM");
			ChromeDriverService service = new ChromeDriverService.Builder()
					.usingDriverExecutable(new File("/Applications/AnywhereWorks.app/Contents/MacOS/AnywhereWorks"))
					.usingAnyFreePort().build();
			service.start();
			System.out.println("App URL " + service.getUrl());
		} catch (Exception ex) {
		}

		aw.log(LogStatus.PASS, "Application Launched Successfully");
		}

	public void testmethod() {
		System.out.println("Executing testmethod:");
	}

	@Test(priority = 2)
	public void ChromeCommunication() throws IOException, JSONException, InterruptedException {

		/* This will start the chrome
		 ChromeDriverService service = new ChromeDriverService.Builder()
		 .usingDriverExecutable(new
		 File("/Users/ashwin/Desktop/Selenium/Testleaf/Selenium/Drivers/chromedriver2"))
		 .usingAnyFreePort()
		 .build();
		 service.start();
		 chromeAttributes.setService(service);
		 // Setting DesiredCapabilities for chrome
		 DesiredCapabilities capabilities = new DesiredCapabilities();
		 ChromeOptions options = new ChromeOptions();
		 chromeAttributes.setCapabilities(capabilities);
		 chromeAttributes.setOptions(options);
		
		 // The address string should be in the form of "hostname/ip:port".
		 9222 is the port that you specified in the
		 // --remote-debugging-port Chromium
		 String remoteDebuggingAddress = "localhost:9222";
		 options.setExperimentalOption("debuggerAddress",
		 remoteDebuggingAddress);
		 capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		
		 // Create a WebDriver instance using URL provided by the
		 ChromeDriverService and capabilities with
		 System.out.println("HEY THIS IS FOR SERVICE URL"+service.getUrl());
		 WebDriver driver = new RemoteWebDriver(service.getUrl(),
		 capabilities);
		 chromeAttributes.setDriver(driver);*/
		java.util.Set<java.lang.String> windowHandles = chromeAttributes.getDriver().getWindowHandles();
		System.out.println(windowHandles);

		// Focusing Chat window and clicking the element to go to the main
		// container

		AWTest wc = new AWTest();
		String chat = wc.WindowId("Anywhere Works");
		// pass your window ID if you have multiple window
		chromeAttributes.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		chromeAttributes.getDriver().switchTo().window("CDwindow-" + chat);
		chromeAttributes.getDriver().findElement(By.id("sidebar-footer")).click();
		chromeAttributes.getDriver().findElement(By.xpath("//*[@id='statusPopup']/div[1]/header[1]/div")).click();
		chromeAttributes.getDriver().findElement(By.className("show-work")).click();

		// Switching to the main container

		String chat2 = wc.WindowId("AnywhereWorks");
		chromeAttributes.getDriver().switchTo().window("CDwindow-" + chat2);
		// Thread.sleep(8000);

		chromeAttributes.getDriver().findElement(By.id("preferences")).click();
		aw.log(LogStatus.PASS, "Got into the main container successfully");
	}

	@Test(priority = 3)
	public void fetch() throws Exception {

		// Fetching an account
		chromeAttributes.getDriver().findElement(By.xpath("//*[@id='preferences']/ul/li[3]")).click();
		chromeAttributes.getDriver().findElement(By.name("fetchVal")).sendKeys("8772998833" + Keys.ENTER);
		
		chromeAttributes.getDriver().findElement(By.xpath("//*[@id='answer_phrase_popup']/div/a/div")).click();
		Thread.sleep(9000);
		AWTest wc = new AWTest();
		String SB = wc.WindowId("SB");
		chromeAttributes.getDriver().switchTo().window("CDwindow-" + SB);
		chromeAttributes.getDriver().findElement(By.xpath("//*[@id='step_e83a223c-8479-4995-c582-8a34adc0fd71']/input"))
				.sendKeys("Ashwin");
		assertEquals("However the office does not close at sharp noon.", chromeAttributes.getDriver()
				.findElement(By.xpath("//*[@id='step_fff2769b-4652-4458-ab75-a1b7830f7e06']")).getText());
		aw.log(LogStatus.PASS, "Account loaded successfully");
		AWTest gngbacktoclose = new AWTest();
		String mc = gngbacktoclose.WindowId("AnywhereWorks");
		// System.out.println("HEY THIS IS FOR WINDOW 2" + mc);
		chromeAttributes.getDriver().switchTo().window("CDwindow-" + mc);
		Thread.sleep(8000);
		// closing of tab
		Actions action = new Actions(chromeAttributes.getDriver());
		action.keyDown(Keys.COMMAND);
		action.sendKeys("w");
		action.keyUp(Keys.COMMAND);
		action.perform();
		System.out.println("Fetched tab closed");

		// Getting logs from API window
		// AWTest apiwin = new AWTest();
		// String api = apiwin.WindowId("API-WINDOW");
		// // System.out.println("HEY THIS IS FOR WINDOW 2" + mc);
		// // String title = chromeAttributes.getDriver().getTitle();
		//
		// Thread.sleep(8000);
		// // System.out.println(title + "this is the title");
		// // LogEntries logEntries =
		// chromeAttributes.getDriver().manage().logs().get("browser");
		// // for (LogEntry entry : logEntries) {
		// // System.out.println(entry.getMessage());
		// // }
		// // LogEntries logEntries =
		// chromeAttributes.getDriver().switchTo().window("CDwindow-" +
		// api).manage().logs().get(LogType.BROWSER);
		// // for (LogEntry entry : logEntries) {
		// // System.out.println(new Date(entry.getTimestamp()) + " " +
		// entry.getLevel() + " " + entry.getMessage());
		// // //do something useful with the data
		// // }
		
		LogEntries logEntries = chromeAttributes.getDriver().manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
            //do something useful with the data
        }

	}

	@Test(priority = 4,enabled=false )
	public void Refetch() throws Exception {

		chromeAttributes.getDriver().findElement(By.id("preferences")).click();
		chromeAttributes.getDriver().findElement(By.xpath("//*[@id='preferences']/ul/li[4]")).click();
		chromeAttributes.getDriver().findElement(By.xpath("//*[@id='answer_phrase_popup']/div/a/div")).click();

		Thread.sleep(9000);
		AWTest gngbacktoSB = new AWTest();
		String SB = gngbacktoSB.WindowId("SB");
		chromeAttributes.getDriver().switchTo().window("CDwindow-" + SB);

		chromeAttributes.getDriver().findElement(By.xpath("//*[@id='step_e83a223c-8479-4995-c582-8a34adc0fd71']/input"))
				.sendKeys("Ashwin refetch");
		;
		assertEquals("However the office does not close at sharp noon.", chromeAttributes.getDriver()
				.findElement(By.xpath("//*[@id='step_fff2769b-4652-4458-ab75-a1b7830f7e06']")).getText());
		aw.log(LogStatus.PASS, "Account loaded successfully");

		AWTest gngbacktoclose = new AWTest();
		String mc = gngbacktoclose.WindowId("AnywhereWorks");
		// System.out.println("HEY THIS IS FOR WINDOW 2" + mc);
		chromeAttributes.getDriver().switchTo().window("CDwindow-" + mc);
		Thread.sleep(8000);
		Actions action = new Actions(chromeAttributes.getDriver());
		action.keyDown(Keys.COMMAND);
		action.sendKeys("w");
		action.keyUp(Keys.COMMAND);
		action.perform();
		System.out.println("Re-fetched tab closed");

	}

	@Test(priority = 5,enabled=false)
	public void Backupform() throws Exception {

		chromeAttributes.getDriver().findElement(By.id("preferences")).click();
		chromeAttributes.getDriver().findElement(By.xpath("//*[@id='preferences']/ul/li[6]")).click();
		Thread.sleep(9000);
		AWTest gngbacktoSB = new AWTest();
		String bf = gngbacktoSB.WindowId("Backup Message Form");
		chromeAttributes.getDriver().switchTo().window("CDwindow-" + bf);
		assertEquals("Email address *",
				chromeAttributes.getDriver().findElement(By.xpath("//*[@id='i1']")).getText());
		aw.log(LogStatus.PASS, "Backup form loaded successfully");
		AWTest gngbacktoclose = new AWTest();
		String mc = gngbacktoclose.WindowId("AnywhereWorks");
		// System.out.println("HEY THIS IS FOR WINDOW 2" + mc);
		chromeAttributes.getDriver().switchTo().window("CDwindow-" + mc);
		Thread.sleep(8000);
		Actions action = new Actions(chromeAttributes.getDriver());
		action.keyDown(Keys.COMMAND);
		action.sendKeys("w");
		action.keyUp(Keys.COMMAND);
		action.perform();
		System.out.println("Backup form closed");

	}

	 @Test(priority = 6,enabled=false)
	 public void Feedback(){
	 chromeAttributes.getDriver().findElement(By.id("preferences")).click();
	 chromeAttributes.getDriver().findElement(By.xpath("//*[@id='preferences']/ul/li[7]")).click();
	 String actualplaceholder =
	 chromeAttributes.getDriver().findElement(By.xpath("//*[@id='agentFb']/div/div/div[2]/div[1]/textarea")).getAttribute("placeholder");
	 String expectedplaceholder = "Please give us your feedback! Thanks";
	 assertEquals(actualplaceholder, expectedplaceholder);
	 chromeAttributes.getDriver().findElement(By.xpath("//*[@id='agentFb']/div/div/div[2]/div[1]/textarea")).sendKeys("This is test feedback from selenium");
	 chromeAttributes.getDriver().findElement(By.id("fileUpload")).sendKeys("/Users/ashwin/Desktop/Screen Shot 123.png");
	 chromeAttributes.getDriver().findElement(By.id("sendFdb")).click();
	 }

	@Test(priority = 7,enabled=false)
	public void Reload() throws Exception {
		//
		chromeAttributes.getDriver().findElement(By.id("preferences")).click();
		chromeAttributes.getDriver().findElement(By.id("reloadV2")).click();
		Thread.sleep(8000);

		AWTest gngtolily = new AWTest();
		String mc = gngtolily.WindowId("fullcreative - AWS Apps Authentication");
		chromeAttributes.getDriver().switchTo().window("CDwindow-" + mc);
		assertEquals("fullcreative - AWS Apps Authentication", chromeAttributes.getDriver().getTitle());
		aw.log(LogStatus.PASS, "Reloaded Phone System Successfully");

	}

	 @Test(priority = 8,enabled=false)
	 public void ClearCache() throws Exception {
	
		 AWTest preference = new AWTest();
			String mc = preference.WindowId("AnywhereWorks");
			chromeAttributes.getDriver().switchTo().window("CDwindow-" + mc);
	 chromeAttributes.getDriver().findElement(By.id("preferences")).click();
	 chromeAttributes.getDriver().findElement(By.className("clear_cache")).click();
	 Thread.sleep(8000);
	 AWTest loginpage = new AWTest();
	 String login = loginpage.WindowId("Login");
	 System.out.println("LOGGGGGGGGGGGGGGGIN"+login);
	 chromeAttributes.getDriver().switchTo().window("CDwindow-" + login);
	 Thread.sleep(5000);
	 String ab = chromeAttributes.getDriver().getTitle();
	 System.out.println(ab + " " + "bc");
	 assertEquals("Login", chromeAttributes.getDriver().getTitle());
	 aw.log(LogStatus.PASS, "Cache Cleared Successfully");
	 Thread.sleep(8000);
	 }

	@Test(priority = 9,enabled=false)
	public void Login() throws Exception {

		// Sign in with google code

		/*
		 * WebElement gbutton =
		 * chromeAttributes.getDriver().findElement(By.id("signin-with-google"))
		 * ; Robot click = new Robot(); org.openqa.selenium.Point Point =
		 * gbutton.getLocation(); System.out.println("print "+Point.getX());
		 * System.out.println("print "+Point.getY()); click.mouseMove(950, 365);
		 * click.mousePress(InputEvent.BUTTON1_MASK);
		 * click.mouseRelease(InputEvent.BUTTON1_MASK);
		 * click.mousePress(InputEvent.BUTTON1_MASK);
		 * click.mouseRelease(InputEvent.BUTTON1_MASK);
		 * chromeAttributes.getDriver().findElement(By.id("identifierId")).
		 * sendKeys("ashwin.mohan@anywhere.co");
		 * chromeAttributes.getDriver().findElement(By.className("CwaK9")).click
		 * (); chromeAttributes.getDriver().findElement(By.name("password")).
		 * sendKeys("....");
		 * chromeAttributes.getDriver().findElement(By.className("CwaK9")).click
		 * ();
		 */

		chromeAttributes.getDriver().findElement(By.id("user-email")).sendKeys("ashwin.mohan@anywhere.co");
		chromeAttributes.getDriver().findElement(By.id("user-password")).sendKeys("billy12");
		chromeAttributes.getDriver().findElement(By.id("process-login")).click();
	}

	// @Test(priority = 10)
	// public void spellcheck() throws Exception {
	// AWTest wc = new AWTest();
	// String mc = wc.WindowId("AnywhereWorks");
	// chromeAttributes.getDriver().switchTo().window("CDwindow-" + mc);
	// Thread.sleep(8000);
	// chromeAttributes.getDriver().findElement(By.id("preferences")).click();
	// aw.log(LogStatus.PASS, "Got into the main container successfully");
	// }

	@Test(priority = 12)
	public void closeaw() throws IOException {
		// chromeAttributes.getDriver().close();
		chromeAttributes.getDriver().quit();

		extent.endTest(aw);

		// aw.log(LogStatus.PASS, "Application closed successfully");
		//
	}

	@AfterSuite
	public void aftersuite() {
		extent.flush();
		extent.close();
	}

	//

	// String mainWindowsHandle = driver.getWindowHandle();
	// Set<String> handles = driver.getWindowHandles(); // Gets all the
	// available windows
	// for(String handle : handles)
	// {
	// driver.switchTo().window(handle);
	// try{
	// driver.findElement(By.id("preferences_Icon")).click();
	// } catch(Exception ex){
	// continue;
	// }
	// }
	 @Test(priority = 11)
	 public static void main(String args[]) throws Exception {
	
	 AWautomation automationObject = new AWautomation();
	 //
	 System.out.println("methods"+automationObject.getClass().getMethods());
	 // automationObject.getClass().getMethod(name, parameterTypes);
	 automationObject.StartAW();
	 automationObject.setChromeAttributes();
	 automationObject.ChromeCommunication();
	 // automationObject.fetch();
	 // automationObject.Refetch();
	 automationObject.closeaw();
	
	 }

}