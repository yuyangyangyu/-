package crawler;


import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class crawler {
	static ArrayList<list_scenic> list =new ArrayList<list_scenic>();
	public static void main(String[] args){
		get_scenic scenic =new get_scenic();
		try {
			details details = scenic.get_details("43811");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(elements.text());
	}
}
