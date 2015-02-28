package com.canopy.framework.datadriven.util;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.canopy.framework.datadriven.TestBase;

public class TestDataProvider {
	
	@DataProvider(name="MarketplaceDataProvider")
	public static Object [][] getDataSuiteA(Method m) throws IOException{
		TestBase.init();
		Xls_Reader xls1 = new Xls_Reader(TestBase.prop.getProperty("xlsFileLocation")+Constants.MARKETPLACE_SUITE+".xlsx");
        return Utility.getData(m.getName(), xls1);		
	}
	
	@DataProvider(name="suiteBDataProvider")
	public static Object [][] getDataSuiteB(Method m) throws IOException{
		TestBase.init();
		Xls_Reader xls1 = new Xls_Reader(TestBase.prop.getProperty("xlsFileLocation")+Constants.SECOND_SUITE+".xlsx");
        return Utility.getData(m.getName(), xls1);		
	}

}
