package wifiInforms;

import java.util.ArrayList;

public class PublicWifiListResult {
	
	 private Integer list_total_count;  // 전체 정보 개수
	 private WifiResult RESULT;  // 실행 성공여부 결과
	 private ArrayList<PublicWifiItem> row = null; // wifi 정보
	
	
	public WifiResult getRESULT() {
		return RESULT;
	}
	public void setRESULT(WifiResult rESULT) {
		RESULT = rESULT;
	}
	public Integer getList_total_count() {
		return list_total_count;
	}
	public void setList_total_count(Integer list_total_count) {
		this.list_total_count = list_total_count;
	}

	public ArrayList<PublicWifiItem> getRow() {
		return row;
	}
	public void setRow(ArrayList<PublicWifiItem> row) {
		this.row = row;
	}

}
