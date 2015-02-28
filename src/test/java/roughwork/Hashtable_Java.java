package roughwork;

import java.util.Hashtable;

public class Hashtable_Java {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

     Hashtable<String , String> table=new Hashtable<String,String> ();
     table.put("Place", "Korea");
     table.put("Continent","Asia");
     System.out.println(table.get("Place"));
		
		
	}

}
