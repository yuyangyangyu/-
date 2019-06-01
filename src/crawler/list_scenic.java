package crawler;
/**
 * 景点的综合介绍 图片/名字/评分/特点/距离/id
 * @author yo
 *
 */
public class list_scenic {
	private String nameString;//名字
	private String imgString;//图片
	private String scoreString;//评分
	private String markString;//特点
	private String distanceString;//距离
	private String idString;//id
	public list_scenic() {
		
	}
	public list_scenic(String name,String img , String score,String mark,String distance,String id) {
		this.nameString=name;
		this.imgString=img;
		this.scoreString=score;
		this.markString=mark;
		this.distanceString=distance;
		this.idString=id;
	}
	/**
	 * 可分别单独设置list属性
	 * @param name
	 */
	public void setname(String name) {
		this.nameString=name;
	}
	public void setimg(String img) {
		this.imgString=img;
	}
	public void setscore(String score) {
		this.scoreString=score;
	}
	public void setmark(String mark) {
		this.nameString=mark;
	}
	public void setdistance(String distance) {
		this.distanceString=distance;
	}
	public void setid(String id) {
		this.idString=id;
	}
	/**
	 * 获取list属性
	 */
	public String getname() {
		return nameString;
	}
	public String getimg() {
		return imgString;
	}
	public String getscore() {
		return scoreString;
	}
	public String getmark() {
		return markString;
	}
	public String getdistance() {
		return distanceString;
	}
	public String getid() {
		return idString;
	}
}
