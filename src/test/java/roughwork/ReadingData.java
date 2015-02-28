package roughwork;

import com.canopy.framework.datadriven.util.Constants;
import com.canopy.framework.datadriven.util.Xls_Reader;

public class ReadingData {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Xls_Reader xls = new Xls_Reader("E:\\Canopy_MarketPlace_Data\\SuiteA.xlsx");
        String testName="Test3";
        int rows = xls.getRowCount(Constants.DATA_SHEET);
        System.out.println("Total Rows - "+rows);
        
        //row Number of testCase
        int testCaseRowNum=1;
        for(testCaseRowNum=1;testCaseRowNum<=rows;testCaseRowNum++){
        String testNameXls=	xls.getCellData(Constants.DATA_SHEET, 0, testCaseRowNum);
        if(testNameXls.equalsIgnoreCase(testName))
        	break ;
       }
        System.out.println("Test Starts from row Number - "+testCaseRowNum);

        int dataStartRowNum=testCaseRowNum+2;
        int ColStartRowNum=testCaseRowNum+1;
        
        //rows of Data
        
        int testRows=1;
        while(!xls.getCellData(Constants.DATA_SHEET, 0, dataStartRowNum+testRows).equals("")){
        	testRows++ ;
        	
        }
        System.out.println("Total Rows of Data are - " +testRows);
               
        int testCols=0 ;
        while(!xls.getCellData(Constants.DATA_SHEET,testCols ,ColStartRowNum).equals("")){
    	   testCols++ ;
       }

       System.out.println("Total Cols - " + testCols);
        
       for(int rNum=dataStartRowNum;rNum <(dataStartRowNum+testRows);rNum++){
    	   for(int cNum=0;cNum<testCols;cNum++){
    		   System.out.println(xls.getCellData(Constants.DATA_SHEET, cNum, rNum));
    	   }
    	   
       }
       
       
       
	}

	   
	
}
