package com.qa.opencart.logging;

import java.io.File;

import org.apache.log4j.FileAppender;
import org.apache.log4j.spi.LoggingEvent;

public class CustomFileAppender extends FileAppender {

//	@Override
//	public void append(LoggingEvent event) {
//		String driverFactoryClassName = event.getMDC("DriverFactory").toString();
//		String testClassName = event.getMDC("testClassName").toString();
//		String logsFolderPath = "logs";
//		File logsFolder = new File(logsFolderPath);
//
//		// Create the logs folder if it doesn't exist
//		if (!logsFolder.exists()) {
//			logsFolder.mkdirs();
//		}
//
//		setFile(logsFolderPath + "/" + testClassName + ".log");
//		setFile(logsFolderPath + "/" + driverFactoryClassName + ".log");
//
//		activateOptions();
//		super.append(event);

//	}
	
	@Override
	public void append(LoggingEvent event) {
	    Object driverFactoryObj = event.getMDC("DriverFactory");
	    Object testClassNameObj = event.getMDC("testClassName");

	    // Fallback to default names if MDC keys are missing
	    String driverFactoryClassName = (driverFactoryObj != null) ? driverFactoryObj.toString() : "defaultDriverFactory";
	    String testClassName = (testClassNameObj != null) ? testClassNameObj.toString() : "defaultTestClass";

	    String logsFolderPath = "logs";
	    File logsFolder = new File(logsFolderPath);
	    if (!logsFolder.exists()) {
	        logsFolder.mkdirs();
	    }

	    // Write to separate log files (you likely want to choose one, not overwrite twice)
	    setFile(logsFolderPath + "/" + testClassName + ".log");
	    activateOptions(); // activate before appending
	    super.append(event);

	    // If you need to log to both files, you must use two separate appenders or log twice
	    // Not recommended to call setFile() multiple times in a single append()
	}
}