package views;  
import models.DataExtractor; 
import models.Instance;  

public class Main {  	
	public static void main(String[] args) { 
		// TODO Auto-generated method stub 		
		DataExtractor De = new DataExtractor("/home/hads/Documents/JavaWS/Sat/src/views/data_set/sets/uuf75-01.cnf");
		Instance I=De.getData();
		System.out.println(I.list);
		
	}
}