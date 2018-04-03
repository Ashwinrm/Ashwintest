package AW;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Testing {
  WebDriver driver;
  ChromeDriverService service;
	public void getService() throws IOException{
		ChromeDriverService service2 = new ChromeDriverService.Builder()
				.usingDriverExecutable(
						new File("/Users/ashwin/Desktop/Selenium/Testleaf/Selenium/Drivers/chromedriver 5"))
				.usingAnyFreePort().build();
		         service2.start();
	
	
//		driver = new RemoteWebDriver(service.getUrl(),
//				DesiredCapabilities.chrome());
//		driver.get("https://hub.anywhereworks.com");
//		//driver.get("https://access.anywhereworks.com/o/serviceLogin?oauth=b5800f1897e4e9496b33&project=anywhereworks&continue=https%3A%2F%2Faccess.anywhereworks.com%2Fo%2Foauth2%2Fauth%3Fresponse_type%3Dcode%26client_id%3D7a0ad-6a8c594c8b47031c088ce3194a6f87fe%26redirect_uri%3Dhttps%253A%252F%252Fhub.anywhereworks.com%252FauthCallback%26scope%3Dcontacts-api.full_access%2Bawapis.fullaccess%2Bstorageapi.obj.readwrite%26state%3Dhttps%3A%2F%2Fhub.anywhereworks.com%26access_type%3Doffline%26approval_prompt%3Dforce%26social_prompt%3Dforce%26project%3Danywhereworks%26fl%3D1&social_prompt=force");
//        driver.findElement(By.id("signin-with-google")).click();
     
        
        DesiredCapabilities capabilities = new DesiredCapabilities();
     

     ChromeOptions options = new ChromeOptions();
     String remoteDebuggingAddress = "localhost:9222";
     options.setExperimentalOption( "debuggerAddress" , remoteDebuggingAddress );
     capabilities.setCapability( ChromeOptions.CAPABILITY , options );
     driver = new RemoteWebDriver( service.getUrl() , capabilities );
        
	}
	
	public void createService() throws IOException{
		ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("/Applications/Google Chrome.app/Contents/MacOS/Google Chrome"))
                 .usingAnyFreePort().build();
		service.start();
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Testing tt= new Testing();
		
		tt.createService();
		tt.getService();
		

	}

}
