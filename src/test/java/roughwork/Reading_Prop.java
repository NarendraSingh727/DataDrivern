package roughwork;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Reading_Prop {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
    System.out.println(System.getProperty("user.dir"));
    String path=System.getProperty("user.dir")+"\\src\\test\\resources\\project_properties" ;
    Properties prop = new Properties();
    try {
		FileInputStream fs = new FileInputStream(path);
		prop.load(fs);
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		System.out.println(prop.getProperty("fileLocation"));
		
		
	}

}
