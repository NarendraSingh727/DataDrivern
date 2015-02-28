package com.canopy.framework.datadriven.MarketPlaceSuite;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.canopy.framework.datadriven.TestBase;
import com.canopy.framework.datadriven.util.Constants;
import com.canopy.framework.datadriven.util.TestDataProvider;

public class Test2 extends TestBase{
	
	@BeforeTest
	public void initLogs(){
		initLogs(this.getClass());
	}
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="MarketplaceDataProvider")
	public void test2(Hashtable<String,String> table) throws IOException{
		APPLICATION_LOG.debug("Executing Test Case Test2");

		//System.out.println("Test 2");
		validateRunmodes("Test2", Constants.MARKETPLACE_SUITE, table.get(Constants.RUNMODE_COL));
		
		doDefaultLogin(table.get(Constants.BROWSER_COL));
		
		}
	@AfterMethod
    public void close(){
    	quit();
    }

}
