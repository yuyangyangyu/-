package crawler;


import java.io.IOException;
import java.util.ArrayList;


public class crawler {
	static ArrayList<list_scenic> list =new ArrayList<list_scenic>();
	static details details;
	public static void main(String[] args){
		get_scenic lScenic = new get_scenic();
		try {
			details = lScenic.get_details("50223");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
