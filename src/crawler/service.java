package crawler;

public class service {
	
	private String picString;
	private String nameString;
	
	public service(String pic,String name) {
		this.picString=pic;
		this.nameString=name;
	}
	public String getpic() {
		return picString;
	}
	public String getname() {
		return nameString;
	}
}
