package com.canopy.framework.datadriven.MarketPlaceSuite;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.canopy.framework.datadriven.TestBase;
import com.canopy.framework.datadriven.util.Constants;
import com.canopy.framework.datadriven.util.ErrorUtil;
import com.canopy.framework.datadriven.util.TestDataProvider;

public class LoginTest extends TestBase {
	@BeforeTest
	public void initLogs(){
		initLogs(this.getClass());
	}

	@Test(dataProviderClass=TestDataProvider.class,dataProvider="MarketplaceDataProvider")
	public void loginTest(Hashtable<String,String> table) throws IOException{
		APPLICATION_LOG.debug("Executing Test Case Test1");
		validateRunmodes("loginTest", Constants.MARKETPLACE_SUITE, table.get("Runmode"));
		doLogin(table.get(Constants.BROWSER_COL), table.get(Constants.USERNAME_COL), table.get(Constants.PASSWORD_COL));
		//Validation for Login
		boolean logoutButton= isElementPresent("logoutButton_xpath");
		if(!(((table.get(Constants.EXPECTEDRESULT_COL).equals("SUCCESS"))&& logoutButton)))
			Assert.fail("Not able to logged in with correct credential");
		else if(table.get(Constants.EXPECTEDRESULT_COL).equals("FAILURE")){
			if(logoutButton){
				Assert.fail("Logged in with wrong credential");

			}
		}
	/*		
		try{ // Report but not stop the program
		Assert.assertEquals("A", "B");
		}catch (Throwable t){
			ErrorUtil.addVerificationFailure(t);
		}
		
	    Assert.assertEquals("A", "B"); // Stop the Program

		
	*/	
		
		}
    @AfterMethod
    public void close(){
    	quit();
    }

	
}
