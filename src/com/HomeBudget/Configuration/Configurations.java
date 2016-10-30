package com.HomeBudget.Configuration;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public   class Configurations {


	public    void initialize() {
		try {
			Properties prop = new Properties();
			String propFileName = "properties.properties";
 
			InputStream	inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
 
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
 
 
			// get the property value and print it out
			String user = prop.getProperty("backEndUrl");
			System.out.println(user);
	   
	}catch(Exception e)
		{
		System.out.println(e.toString());
		}
	}

}
