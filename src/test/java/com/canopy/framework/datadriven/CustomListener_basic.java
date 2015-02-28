package com.canopy.framework.datadriven;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class CustomListener_basic extends TestListenerAdapter implements IInvokedMethodListener,ISuiteListener{
	
	public void onTestFailure(ITestResult tr){
		
		TestBase.APPLICATION_LOG.debug("Fail - "+tr.getName());

	}
	
	public void onTestSkipped(ITestResult tr) {
		TestBase.APPLICATION_LOG.debug("Skipped - " +tr.getName());

	}
	
	public void onTestSuccess(ITestResult tr){
		TestBase.APPLICATION_LOG.debug("Success - " +tr.getName());

	}
	
	public void afterInvocation(IInvokedMethod method, ITestResult result) {
		
		
	}
 
	public void beforeInvocation(IInvokedMethod arg0, ITestResult test) {
	}

	@Override
	public void onFinish(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ISuite arg0) {
		// TODO Auto-generated method stub
		
	}

	

}