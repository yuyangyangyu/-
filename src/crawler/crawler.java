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
		get_scenic lScenic= new get_scenic();
		try {
			lScenic.get(1);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
