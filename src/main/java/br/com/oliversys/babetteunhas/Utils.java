package br.com.oliversys.babetteunhas;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class Utils {

	private static final String CONFIG_URI = "babetteconfig.properties";
	
	public Utils() {}
		
	public static Logger getLogger(Class c){
		return Logger.getLogger(c);
	}
	
	public static Properties getPropertiesFromWAR(){
		Properties p = new Properties();
		InputStream in = Utils.class.getClassLoader().getResourceAsStream(CONFIG_URI);
		if (in != null){
			try {
				p.load(in);
				return p;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return p;
	}
	
	public static Properties getPropertiesFromSO(){
		Properties p = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(System.getProperty("user.home") + "/babette.properties");
			if (in != null)
				p.load(in);				
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return p;
	}

	public static Properties getDESENVPropertiesFromSO(){
		Properties p = new Properties();
		InputStream in = null;
		try {
			in = new FileInputStream(System.getProperty("user.home") + "/babette-desenv.properties");
			if (in != null)
				p.load(in);				
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return p;
	}
	
}
