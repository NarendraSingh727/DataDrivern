package com.canopy.framework.datadriven.suiteB;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.canopy.framework.datadriven.TestBase;
import com.canopy.framework.datadriven.util.Constants;
import com.canopy.framework.datadriven.util.TestDataProvider;
import com.canopy.framework.datadriven.util.Utility;
import com.canopy.framework.datadriven.util.Xls_Reader;

public class Test1 extends TestBase {

	@Test(dataProviderClass=TestDataProvider.class,dataProvider="suiteBDataProvider")
	public void test1(Hashtable<String,String> table) throws IOException{
		/*	
			Xls_Reader xls = new Xls_Reader("E:\\Canopy_MarketPlace_Data\\Suite.xlsx");
			System.out.println(Utility.isSuiteRunnable("SuiteA", xls));
			System.out.println(Utility.isSuiteRunnable("SuiteB", xls));
			System.out.println(Utility.isSuiteRunnable("SuiteC", xls));
			Xls_Reader xls1 = new Xls_Reader("E:\\Canopy_MarketPlace_Data\\SuiteA.xlsx");

			System.out.println(Utility.isTestCaseRunnable("Test1", xls1));
			*/
		validateRunmodes("Test1", Constants.SECOND_SUITE, table.get("Runmode"));
		
		}

	}
