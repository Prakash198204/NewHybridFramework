package listener;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import helper.Utility;

public class ExtentManager {
	
	static ExtentReports report;
	
	public static ExtentReports getInstance() {
		
		if(report==null) {
			report=createInstance();
			return report;
		}
		else {
			return report;
		}
	}
	
	public static ExtentReports createInstance() {
		
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/AutomationReport_"+Utility.getCurrentDateTime()+".html");
		sparkReporter.config().setReportName("Automation Report");
		sparkReporter.config().setDocumentTitle("Sprint 1 Report");
		sparkReporter.config().setTheme(Theme.DARK);
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		return extent;
				
	}
}
