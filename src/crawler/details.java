package crawler;

import java.util.ArrayList;

public class details {
	//开放时间，优待政策，服务设施，景点特色，景点介绍，交通信息、
	private String time ;
	private ArrayList<String> policy= new ArrayList<String>();
	private ArrayList<service> service= new ArrayList<service>();
	private ArrayList<String> mark =new ArrayList<String>();
	private String details;
	private ArrayList<String> traffic =new ArrayList<String>();
	
	public void set_mark(String str) {
		this.mark.add(str);
	}
	public void set_traffic(String str) {
		this.traffic.add(str);
	}
	public void set_time(String str) {
		this.time=str;
	}
	public void set_policy(String str) {
		this.policy.add(str);
	}
	public void set_details(String str) {
		this.details=str;
	}
	public void set_service(service str) {
		this.service.add(str);
	}
	
	
	
	
	
	public String gettime() {
		return time;
	}
	public ArrayList<String> getpolicy() {
		return policy;
	}
	public ArrayList<service> getservice() {
		return service;
	}
	public ArrayList<String> getmark() {
		return mark;
	}
	public String getdetails() {
		return details;
	}
	public ArrayList<String> gettraffic() {
		return traffic;
	}
}
