package crawler;

import java.io.IOException;
import java.util.ArrayList;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class get_scenic {
	ArrayList<list_scenic> list =new ArrayList<list_scenic>();
	ArrayList<details> list_detailsArrayList= new ArrayList<details>();
	public ArrayList<list_scenic> get(int count) throws  IOException {
		request rqRequest= new request();
		String urlString="https://m.ctrip.com/restapi/soa2/13342/json/getSightRecreationList?_fxpcqlniredt=09031050410110759615&__gw_appid=99999999&__gw_ver=1.0&__gw_from=214062&__gw_platform=H5";
		String pramgeString1="{\"Index\":%d,\"Count\":20,\"SortType\":0,\"DistrictId\":158,\"TagIds\":[],\"zoneIds\":[],\"CategoryId\":0,\"displayType\":0,\"lat\":0,\"lon\":0,\"showTodayUse\":false,\"showCircuml\":false,\"aroundDistance\":0,\"themeId\":0,\"level2ThemeId\":0,\"productType\":[],\"head\":{\"cid\":\"09031050410110759615\",\"ctok\":\"\",\"cver\":\"1.0\",\"lang\":\"01\",\"sid\":\"55551825\",\"syscode\":\"09\",\"auth\":null,\"extension\":[{\"name\":\"protocal\",\"value\":\"https\"}]},\"contentType\":\"json\"}";
		//控制加载更多
		String pramgeString =String.format(pramgeString1, count);
		org.json.JSONObject jsonObject=new org.json.JSONObject(pramgeString);
		String ssString=request.sendPost(urlString, pramgeString);
        //先将这条数据解析为JSONObject
        JSONObject outJson = JSONObject.parseObject(ssString);
        JSONObject outJson1=JSONObject.parseObject(outJson.getString("result"));
        //获得景点列表
        String outString=outJson1.getString("sightRecreationList");
        JSONArray list = new JSONArray().parseArray(outString);
        //获取景点信息
        for (int i = 0; i <20; i++) {
			JSONObject js = (JSONObject)list.get(i);
			String name =js.getString("name");
			String img  =js.getString("coverImageUrl");
			String mark =js.getString("tagNameList");
			String score  =js.getString("commentScore");
			String id =js.getString("id");
			String distance  =js.getString("distanceStr");
			String latString = js.getString("coordInfo");//获取经纬度
			JSONObject lat_lon = JSONObject.parseObject(latString);
			list_scenic scenic =new list_scenic(name, img, score, mark, distance, id);
			scenic.setLat(lat_lon.getString("gDLat"));
			scenic.setLat(lat_lon.getString("gDLon"));
			this.list.add(scenic);
		}
        return this.list;
	}
	/*
	 * 获取景点详情
	 */
	public details get_details(String id) throws IOException{
		details placeDetails =new details();
		request rqRequest= new request();
		String url="https://sec-m.ctrip.com/restapi/soa2/12530/json/scenicSpotDescription?_fxpcqlniredt=09031076411465212022";
		String pro = String.format("{\"viewid\":%s,\"retype\":1,\"searchtype\":1,\"pageid\":238013,\"ver\":\"8.2.2\",\"head\":{\"cid\":\"09031076411465212022\",\"ctok\":\"\",\"cver\":\"1.0\",\"lang\":\"01\",\"sid\":\"8888\",\"syscode\":\"09\",\"auth\":null,\"extension\":[{\"name\":\"protocal\",\"value\":\"https\"}]},\"contentType\":\"json\"}", id);
		org.json.JSONObject jsonObject=new org.json.JSONObject(pro);
		String ssString=request.sendPost(url,pro);
		JSONObject outJson = JSONObject.parseObject(ssString);
		JSONObject outJson1=JSONObject.parseObject(outJson.getString("data"));
		JSONArray ouArray = new JSONArray().parseArray(outJson1.getString("simpledesc"));
		for (int i = 0; i < ouArray.size(); i++) {
			JSONObject js =(JSONObject)ouArray.get(i);
			//景点特色
			if (js.getString("tcode").equals("8")) {
				JSONArray list_8=new JSONArray().parseArray(js.getString("desclist"));
				for (int j = 0; j < list_8.size(); j++) {
					JSONObject js_8=(JSONObject)list_8.get(j);
					placeDetails.set_mark(js_8.get("text").toString().trim());
				}
				continue;
			}
			//交通信息
			if (js.getString("tcode").equals("18")) {
				JSONArray list_18=new JSONArray().parseArray(js.getString("desclist"));
				for (int j = 0; j < list_18.size(); j++) {
					JSONObject js_18=(JSONObject)list_18.get(j);
					placeDetails.set_traffic(js_18.get("text").toString());
				}
				continue;
			}
			//开放时间
			if(js.getString("tcode").equals("78")) {
				
				JSONArray list_78=new JSONArray().parseArray(js.getString("desclist"));
				for (int j = 0; j < list_78.size(); j++) {
					JSONObject js_78=(JSONObject)list_78.get(j);
					placeDetails.set_time(js_78.get("text").toString());
				}
				continue;
			}
			//优待政策
			if(js.getString("tcode").equals("16")) {
				
				JSONArray list_16=new JSONArray().parseArray(js.getString("desclist"));
				for (int j = 0; j < list_16.size(); j++) {
					JSONObject js_16=(JSONObject)list_16.get(j);
					placeDetails.set_policy(js_16.get("text").toString());
				}
				continue;
			}
			//景点介绍
			if(js.getString("tcode").equals("99")) {
				JSONArray list_99=new JSONArray().parseArray(js.getString("desclist"));
				if(list_99.size()==1) {
					JSONObject js_99=(JSONObject)list_99.get(0);
					placeDetails.set_details(js_99.get("text").toString());
				}
				continue;
			}
			//建议游玩时间
			if(js.getString("tcode").equals("80")) {
				JSONArray list_80=new JSONArray().parseArray(js.getString("desclist"));
				for (int j = 0; j < list_80.size(); j++) {
					JSONObject js_78=(JSONObject)list_80.get(j);
					placeDetails.set_playtime(js_78.get("text").toString());
				}
				continue;
			}
		}
		JSONObject service = JSONObject.parseObject(outJson1.getString("servicedesc"));
		if (service!=null) {
			JSONArray servicesArray = new JSONArray().parseArray(service.getString("complexservice"));
			for (int j = 0; j < servicesArray.size(); j++) {
				JSONObject JB = (JSONObject)servicesArray.get(j);
				String nameString  = JB.get("name").toString();
				String picString = JB.get("iconurl").toString();
				service service_ = new service(picString, nameString);
				placeDetails.set_service(service_);
			}
		}
		
		return placeDetails;
	}
	public List<Food> get_food(String lat,String lon)throws IOException {
		ArrayList<Food> list = new ArrayList<Food>();
		String url = "https://m.ctrip.com/restapi/soa2/10332/json/GetHomePageRestaruantListV706?_fxpcqlniredt=09031127410714579370&__gw_appid=99999999&__gw_ver=1.0&__gw_from=0&__gw_platform=H5";
		String pro = String.format("{\"ViewDestId\":158,\"Lon\":%s,\"Lat\":%s,\"CurrentDestId\":0,\"SubVersion\":1,\"PageIndex\":1,\"PageSize\":20,\"DistanceFilter\":0,\"PriceFilter\":[],\"ParentPoiId\":0,\"CuisineFilter\":[],\"SceneFilter\":[],\"SellFilter\":[],\"FoodFilters\":[],\"ZoneId\":0,\"RegionId\":0,\"MetroId\":0,\"IsBusinessOpen\":false,\"PositionLat\":0,\"PositionLon\":0,\"MeiShiLinTypes\":[],\"Platform\":2,\"OrderType\":0,\"IsOnlyList\":false,\"IsMeiShiLin\":true,\"head\":{\"cid\":\"09031127410714579370\",\"ctok\":\"\",\"cver\":\"1.0\",\"lang\":\"01\",\"sid\":\"55551791\",\"syscode\":\"09\",\"auth\":null,\"extension\":[{\"name\":\"protocal\",\"value\":\"https\"}]},\"contentType\":\"json\"}", lon,lat);
		org.json.JSONObject jsonObject=new org.json.JSONObject(pro);
		String ssString=request.sendPost(url, pro);
		JSONObject outJson = JSONObject.parseObject(ssString);
		JSONArray ouArray = new JSONArray().parseArray(outJson.getString("Restaurants"));
		
		for (int i = 0; i < ouArray.size(); i++) {
			JSONObject js =(JSONObject)ouArray.get(i);
			String Name = js.getString("Name");//名称
			org.json.JSONObject jsonObject1=new org.json.JSONObject(js.getString("BCoord"));//经纬度
			String dis = js.getString("DistanceDesc");
			String avg = js.getString("AveragePrice");
			String img = js.getString("ImageUrl");
			String score = js.getString("CommentScore"); 
			String lat1 = Double.toString(jsonObject1.getDouble("Lat"));
			String lon1 = Double.toString(jsonObject1.getDouble("Lng"));
			Food food = new Food(img, Name, dis, avg, score, lat1, lon1);
			list.add(food);
		}
		return list;
	}

}
