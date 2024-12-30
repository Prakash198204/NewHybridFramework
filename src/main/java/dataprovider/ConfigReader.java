package dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	public static String getValue(String key) {
		
		Properties pro = new Properties();
		try 
		{
			pro.load(new FileInputStream(new File(System.getProperty("user.dir")+"/configuration/config.properties")));
		} 
		catch (IOException e) 
		{
			System.out.println("LOG : INFO - File not found" + e.getMessage());
		}
		
		String value = pro.getProperty(key);
		
		return value;
	}

}
