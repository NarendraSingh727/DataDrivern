package com.canopy.framework.datadriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.SkipException;

import com.canopy.framework.datadriven.util.Constants;
import com.canopy.framework.datadriven.util.Utility;
import com.canopy.framework.datadriven.util.Xls_Reader;

public class TestBase {
    public static Properties prop ;
	public static Logger APPLICATION_LOG=null; //Logger.getLogger("devpinoyLogger");
	public WebDriver driver;
	
	public void initLogs(Class<?> class1){

		FileAppender appender = new FileAppender();
		// configure the appender here, with file location, etc
		appender.setFile(System.getProperty("user.dir")+"//target//reports//"+CustomListener.resultFolderName+"//"+class1.getName()+".log");
		appender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		appender.setAppend(false);
		appender.activateOptions();

		APPLICATION_LOG = Logger.getLogger(class1);
		APPLICATION_LOG.setLevel(Level.DEBUG);
		APPLICATION_LOG.addAppender(appender);
	}
	
	
	
	public static void init() throws IOException{
		if(prop == null){
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\project_properties" ;
	     prop = new Properties();
	    try {
			FileInputStream fs = new FileInputStream(path);
			prop.load(fs);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			System.out.println(prop.getProperty("xlsFileLocation"));
			
		}		
		
	}
    
	public  void validateRunmodes (String testName,String suiteName,String dataRunmode) throws IOException{
	init() ;
	APPLICATION_LOG.debug("Validating runmode of test case " +testName + " in suite "+suiteName);
	boolean suiteRunmode =	Utility.isSuiteRunnable(suiteName, new Xls_Reader(prop.getProperty("xlsFileLocation")+"Suite.xlsx"));
	boolean testRunmode =	Utility.isTestCaseRunnable(testName, new Xls_Reader(prop.getProperty("xlsFileLocation")+suiteName+".xlsx"));
	boolean dataSetRunmode=false;
	if(dataRunmode.equals(Constants.RUNMODE_YES))
		dataSetRunmode=true;
	
	if(!(suiteRunmode && testRunmode && dataSetRunmode)){
		APPLICATION_LOG.debug("Skipping the test "+testName+" inside the suite "+ suiteName);
		throw new SkipException("Skipping the test "+testName+" inside the suite "+ suiteName);
	}
	
	
}

	
	/****************************Generic Functions *******************************/
	
	public void openBrowser(String browserName){
		/*
		if(browserName.equals("Mozilla")){
			FirefoxProfile fp = new FirefoxProfile();
			fp.setAcceptUntrustedCertificates(true);
			fp.setAssumeUntrustedCertificateIssuer(false);
			driver= new FirefoxDriver(fp);
		}
		else if(browserName.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver", prop.getProperty("chromedriverexe"));
			driver= new ChromeDriver();
		}
		*/
		try{
		DesiredCapabilities cap = new DesiredCapabilities();
		if(browserName.equals("Mozilla")){
			cap.setBrowserName("firefox");
		}
		else if(browserName.equals("Chrome")){
			cap.setBrowserName("chrome");
		}
		cap.setPlatform(Platform.ANY);
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		catch(Exception e){
			Assert.fail("Not able to Open Browser "+e.getMessage());
		}
		
		//driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public void navigate(String URLKey){
		driver.get(URLKey);
	}
	
	public void click(String identifier){
		try{
		if(identifier.endsWith("_xpath"))
			driver.findElement(By.xpath(prop.getProperty(identifier))).click();
		
		else if(identifier.endsWith("_id"))
			driver.findElement(By.id(prop.getProperty(identifier))).click();
		else if(identifier.endsWith("_name"))
			driver.findElement(By.name(prop.getProperty(identifier))).click();
		}
		catch (NoSuchElementException e){
			Assert.fail("No Element is Found - " +identifier);
		}
	}
	
	public void input(String identifier,String data){
		
		try{
		if(identifier.endsWith("_xpath"))
			driver.findElement(By.xpath(prop.getProperty(identifier))).sendKeys(data);
		
		else if(identifier.endsWith("_id"))
			driver.findElement(By.id(prop.getProperty(identifier))).sendKeys(data);
		else if(identifier.endsWith("_name"))
			driver.findElement(By.name(prop.getProperty(identifier))).sendKeys(data);
		}
		catch (NoSuchElementException e){
			Assert.fail("No Element is Found - " +identifier);
		}
		
	}
	
	public boolean verifyTitle(String expectedTitleKey){
		String expectedTitle=prop.getProperty(expectedTitleKey);
		String actualTitle=driver.getTitle();
		
		if(expectedTitle.equals(actualTitle))
			return true;
		else
			return false;
		
	}
	
	public boolean isElementPresent(String identifier){

		int size=0;
		if(identifier.endsWith("_xpath"))
			size=driver.findElements(By.xpath(prop.getProperty(identifier))).size();
		
		else if(identifier.endsWith("_id"))
			size=driver.findElements(By.id(prop.getProperty(identifier))).size();
		else if(identifier.endsWith("_name"))
			size=driver.findElements(By.name(prop.getProperty(identifier))).size();
		else
			 size=driver.findElements(By.xpath(identifier)).size();
		
		if(size >0)
			return true;
		else
			return false;
	}
	
	public String getText(String identifier){
		String text="";
		if(identifier.endsWith("_xpath"))
			text=driver.findElement(By.xpath(prop.getProperty(identifier))).getText();
		
		else if(identifier.endsWith("_id"))
			text=driver.findElement(By.id(prop.getProperty(identifier))).getText();
		else if(identifier.endsWith("_name"))
			text=driver.findElement(By.name(prop.getProperty(identifier))).getText();
		
		return text;
		
		
	}
	
	
	
	public void quit(){
		if(driver!=null){
			driver.quit();
			driver=null;
		}
	}
	
	
	/*******************************Application Specific Functions****************************************/
	
	public void doLogin(String browser,String username,String password){
		openBrowser(browser);
		navigate(prop.getProperty("testSiteURL"));
		Assert.assertTrue(isElementPresent("login_xpath"), "Element is not Found - login_xpath");
		click("login_xpath");
		APPLICATION_LOG.debug("Login into CEP Application");
		Assert.assertTrue(verifyTitle("loginPage"), "Title Do not Match. Got Title as "+ driver.getTitle());
		input("loginUsername_xpath", username);
		input("loginPassword_xpath", password);
		click("loginButton_xpath");
		
		
	}
	
	public void doDefaultLogin(String browser){
		
		doLogin(browser, prop.getProperty("defaultUsername"), prop.getProperty("defaultPassword"));
		
	}
	
}