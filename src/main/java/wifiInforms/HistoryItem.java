package wifiInforms;

public class HistoryItem {
	private Integer id;
	private float lat;
	private float lnt;
	private String date;
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLnt() {
		return lnt;
	}
	public void setLnt(float lnt) {
		this.lnt = lnt;
	}
	
	

}
