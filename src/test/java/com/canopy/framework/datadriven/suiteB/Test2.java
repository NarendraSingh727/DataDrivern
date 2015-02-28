package com.canopy.framework.datadriven.suiteB;

import java.io.IOException;
import java.util.Hashtable;

import org.testng.annotations.Test;

import com.canopy.framework.datadriven.TestBase;
import com.canopy.framework.datadriven.util.Constants;
import com.canopy.framework.datadriven.util.TestDataProvider;

public class Test2 extends TestBase{
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="suiteBDataProvider")
	public void test2(Hashtable<String,String> table) throws IOException{
			//System.out.println("Test 2");
		validateRunmodes("Test2", Constants.SECOND_SUITE, table.get(Constants.RUNMODE_COL));
		}


}
