package wifiInforms;

public class WifiKilometer implements  Comparable<WifiKilometer>{
	Double kilo;
	PublicWifiItem item;
	
	
	public Double getKilo() {
		return kilo;
	}
	public void setKilo(Double killo) {
		this.kilo = killo;
	}
	public PublicWifiItem getItem() {
		return item;
	}
	public void setItem(PublicWifiItem item) {
		this.item = item;
	}
	
	
	@Override
	public int compareTo(WifiKilometer o) {
		return o.kilo >= this.kilo ? 1 : -1;
	}

}


