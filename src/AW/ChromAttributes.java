package AW;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ChromAttributes {
	static ChromeDriverService service;
	static DesiredCapabilities capabilities;
	static ChromeOptions options;
	static WebDriver driver;
	public ChromeDriverService getService() {
		return service;
	}
	public void setService(ChromeDriverService service) {
		this.service = service;
	}
	public DesiredCapabilities getCapabilities() {
		return capabilities;
	}
	public void setCapabilities(DesiredCapabilities capabilities) {
		this.capabilities = capabilities;
	}
	public ChromeOptions getOptions() {
		return options;
	}
	public void setOptions(ChromeOptions options) {
		this.options = options;
	}
	public WebDriver getDriver() {
		return driver;
	}
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}
}
