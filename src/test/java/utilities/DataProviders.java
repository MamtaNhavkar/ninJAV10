package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders 
{
//DataProvider 1
	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException
	{
		//taking xl file from testData
		
		//String path=".\\testData\\Book1.xlsx";  //Valid2+invalid2 data 
		//String path=".\\testData\\Book2.xlsx";  //Valid0+invalid4 data 
		String path=".\\testData\\Ninja_LoginData.xlsx"; //Valid4+invalid0 data 
		
		//creating an object for XLUtility
		ExcelUtility xlutil= new ExcelUtility(path);
		  
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols= xlutil.getCellCount("Sheet1", 1);
		
		//created for two dimension array which can store
		String loginData[][]= new String[totalrows][totalcols];
		
		for(int i=1;i<=totalrows;i++)   //1  //read the data from xl storing in two dimensional array
		{
			for(int j=0;j<totalcols;j++) //0  //i is row j is col
			{
				loginData[i-1][j]=xlutil.getCellData("Sheet1", i, j);   //1,0
			}
		}
		
		//returning two dimensional array
		return loginData;
	}
	
	//DataProvider 2
}
