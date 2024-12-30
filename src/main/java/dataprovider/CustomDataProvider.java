package dataprovider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider {
	
	@DataProvider(name="login")
	public static Object[][] getUserDetails() {
		
		System.out.println("LOG:INFO - Generating test data for the test");
		Object [][] arr =ExcelReder.getDataFromExcel("login_details");
		System.out.println("LOG:INFO - Testdata Generated");
		return arr;
	}

}
