package crawler;

public class Food {
	//主要的细节包含 图片 名称 距离 平均消费 评分 到这去
	private String img;
	private String name;
	private String Distance;
	private String avg;
	private String score;
	private String lat;
	private String lon;
	public Food() {}
	public Food(String img,String name,String Distance,String avg,String score,String lat,String lon) {
		this.img = img;
		this.avg = avg;
		this.Distance = Distance;
		this.lat = lat;
		this.lon = lon;
		this.score = score;
		this.name = name;
	}
	
	public void setAvg(String avg) {
		this.avg = avg;
	}
	public void setDistance(String distance) {
		this.Distance = distance;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public void setLon(String lon) {
		this.lon = lon;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setScore(String score) {
		this.score = score;
	}
	
	public String getAvg() {
		return avg;
	}
	public String getDistance() {
		return Distance;
	}
	public String getImg() {
		return img;
	}
	public String getLat() {
		return lat;
	}
	public String getLon() {
		return lon;
	}
	public String getName() {
		return name;
	}
	public String getScore() {
		return score;
	}

}
