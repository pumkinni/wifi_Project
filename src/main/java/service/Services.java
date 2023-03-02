package service;

import com.google.gson.Gson;

import wifiInforms.PublicWifiList;
import wifiInforms.PublicWifiListResult;
import wifiInforms.WifiKilometer;
import wifiInforms.WifiResult;
import wifiInforms.HistoryItem;
import wifiInforms.PublicWifiItem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.List;

public class Services {
	 

	public static List<HistoryItem> historyList(){
    	List<HistoryItem> itemList = new ArrayList();
    	
        String url = "jdbc:mariadb://localhost:3306/wifiProject";
        String dbUserId = "testuser1";
        String dbPassword = "0000";


        //1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2. 커넥션 개채 생성
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            //3. 스테이트먼트 객체 생성
            String sql = " select ID , H_LAT , H_LNT , SEARCH_DATE "
            		+ " from LOCATION_HISTORY ; ";

            preparedStatement = connection.prepareStatement(sql);

            //4. 쿼리실행
            rs = preparedStatement.executeQuery();

            //5. 결과 수행
            while (rs.next()) {

                int id = rs.getInt("ID");
                float lat = rs.getFloat("H_LAT");
                float lnt = rs.getFloat("H_LNT");
                String search_date = rs.getString("SEARCH_DATE");
                
                HistoryItem item = new HistoryItem();
                item.setId(id);
                item.setLat(lat);
                item.setLnt(lnt);
                item.setDate(search_date);
                
                itemList.add(item); 
               }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6. 객체 연결 해제(close) - 무조건 실행 되어야하므로 마지막에 실행
            try {
                if (rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return itemList;
    }
	
	
	public PriorityQueue<WifiKilometer> wifiList(String nowLat, String nowLnt){
		
		PriorityQueue<WifiKilometer> itemList = new PriorityQueue<>();
    	
        String url = "jdbc:mariadb://localhost:3306/wifiProject";
        String dbUserId = "testuser1";
        String dbPassword = "0000";


        //1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2. 커넥션 개채 생성
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            //3. 스테이트먼트 객체 생성
            String sql = " SELECT MANAGE_NAME , GU , WIFI_NAME , STREET_ADR , DATAIL_ADR , INSTALL_LOC, "
            		+ " INSTALL_TYPE , INSTALL_AGENCY , SERVICE_TYPE , NET_TYPE , INSTALL_YEAR, "
            		+ " IN_OUT , CONNECT_INV , LAT , LNT , WORK_DATE "
            		+ " from WIFI ";

            preparedStatement = connection.prepareStatement(sql);

            //4. 쿼리실행
            rs = preparedStatement.executeQuery();

            //5. 결과 수행
            while (rs.next()) {

                String manageNo = rs.getString("manage_name");
                String gu = rs.getString("GU");
                String wifiName = rs.getString("WIFI_NAME");
                String streetAdr = rs.getString("STREET_ADR");
                String detailAdr = rs.getString("DATAIL_ADR");
                String installLoc= rs.getString("INSTALL_LOC");
                String installType = rs.getString("INSTALL_TYPE");
                String installAgency = rs.getString("INSTALL_AGENCY");
                String serviceType = rs.getString("SERVICE_TYPE");
                String netType = rs.getString("NET_TYPE");
                String installYear = rs.getString("INSTALL_YEAR");
                String inOrOut = rs.getString("IN_OUT");
                String connectInv = rs.getString("CONNECT_INV");
                String lat = rs.getString("LAT");
                String lnt = rs.getString("LNT");
                String workDate = rs.getString("WORK_DATE");
                
                
                PublicWifiItem item = new PublicWifiItem();
                item.setX_SWIFI_MGR_NO(manageNo);
                item.setX_SWIFI_WRDOFC(gu);
                item.setX_SWIFI_MAIN_NM(wifiName);
                item.setX_SWIFI_ADRES1(streetAdr); 
                item.setX_SWIFI_ADRES2(detailAdr);
                item.setX_SWIFI_INSTL_FLOOR(installLoc);
                item.setX_SWIFI_INSTL_TY(installType);
                item.setX_SWIFI_INSTL_MBY(installAgency);
                item.setX_SWIFI_SVC_SE(serviceType);
                item.setX_SWIFI_CMCWR(netType);
                item.setX_SWIFI_CNSTC_YEAR(installYear);
                item.setX_SWIFI_INOUT_DOOR(inOrOut);
                item.setX_SWIFI_REMARS3(connectInv);
                item.setLAT(lat);
                item.setLNT(lnt);
                item.setWORK_DTTM(workDate);
                
                WifiKilometer wifiKilometer = new WifiKilometer();
                wifiKilometer.setItem(item); 
                double kilo = Math.round(distanceInKilometer(Double.parseDouble(lat), Double.parseDouble(lnt), 
                		Double.parseDouble(nowLat), Double.parseDouble(nowLnt)) * 1000) / 1000.0;
                
                wifiKilometer.setKilo(kilo);
                
                itemList.offer(wifiKilometer);
                
                if (itemList.size() > 20) {
                	itemList.poll();
                }
                
               }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6. 객체 연결 해제(close) - 무조건 실행 되어야하므로 마지막에 실행
            try {
                if (rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return itemList;
    }
    
	
	// 하버파인 -> 위경도 거리 구하기
	public double distanceInKilometer(double x1, double y1, double x2, double y2) {
	    double distance;
	    double radius = 6371; // 지구 반지름(km)
	    double toRadian = Math.PI / 180;

	    double deltaLatitude = Math.abs(x1 - x2) * toRadian;
	    double deltaLongitude = Math.abs(y1 - y2) * toRadian;

	    double sinDeltaLat = Math.sin(deltaLatitude / 2);
	    double sinDeltaLng = Math.sin(deltaLongitude / 2);
	    double squareRoot = Math.sqrt(
	        sinDeltaLat * sinDeltaLat +
	        Math.cos(x1 * toRadian) * Math.cos(x2 * toRadian) * sinDeltaLng * sinDeltaLng);

	    distance = 2 * radius * Math.asin(squareRoot);

	    return distance;
	}

	// 기능 추가하기
	// 중복되는 것은 기본키가 관리번호로 설정되어있어서 제거된다.
	//(중복되었는데 모든 내용이 같으면 넘어가고 update 된 내용이 있으면 수정)
	public int insertAll() {
		// 페이지 변수 추가해서 1000개씩 가져오기
		Integer totalCnt = 0;
		int start = 0;
		int end = start + 999;
		
		while (totalCnt != null && start <= totalCnt) {
			totalCnt = getInform(start, end);
			
			start += 1000;
			end += 1000;
			if (end > totalCnt) {
				end = totalCnt;
			}
		}
		System.out.print("done");
		
		return totalCnt;
	}
		
	public int getInform(int startNum, int endNum) {
		Integer cnt = 0 ; // 총 조회개수
        WifiResult result = null; // 실행결과 (정상/실패)
        ArrayList<PublicWifiItem> items = null; // wifi 정보들
		
		try {
			OpenApi api = new OpenApi();
			String response = api.requestInform(startNum,endNum);
			
			if (response != null) {
				System.out.println("reponse : " + response);
			       
		        Gson gson = new Gson();
		        PublicWifiList wifiList = gson.fromJson(response, PublicWifiList.class);
	
		        PublicWifiListResult wifiResult = null;
		        
		        if(wifiList != null){
		        	wifiResult = wifiList.getTbPublicWifiInfo();
		            cnt = wifiResult.getList_total_count();
		            result = wifiResult.getRESULT();
		            items = wifiResult.getRow(); 
		        }
		        
				System.out.println(cnt);
				System.out.println(result.getCODE() + result.getMESSAGE());
				
				
				
				for (PublicWifiItem item : items) {
					dbInsert(item);
					
//					item.showItems();
				}
				
			}
		
		} catch (Exception e){
			
			System.out.println("search error : " + e.getMessage());
        }
		
		return cnt;	
	}
	
	public boolean dbInsert(PublicWifiItem item){
		boolean success = false;

        String url = "jdbc:mariadb://localhost:3306/wifiProject";
        String dbUserId = "testuser1";
        String dbPassword = "0000";

        //1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2. 커넥션 개채 생성
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            //3. 스테이트먼트 객체 생성
            String sql = " insert into WIFI "
            		+ " (MANAGE_NAME , GU , WIFI_NAME , STREET_ADR , DATAIL_ADR , INSTALL_LOC, "
            		+ " INSTALL_TYPE , INSTALL_AGENCY , SERVICE_TYPE , NET_TYPE , INSTALL_YEAR, "
            		+ " IN_OUT , CONNECT_INV , LAT , LNT , WORK_DATE ) "
            		+ " values "
            		+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, item.getX_SWIFI_MGR_NO());
            preparedStatement.setString(2, item.getX_SWIFI_WRDOFC());
            preparedStatement.setString(3, item.getX_SWIFI_MAIN_NM());
            preparedStatement.setString(4, item.getX_SWIFI_ADRES1()); 
            preparedStatement.setString(5, item.getX_SWIFI_ADRES2());
            preparedStatement.setString(6, item.getX_SWIFI_INSTL_FLOOR());
            preparedStatement.setString(7, item.getX_SWIFI_INSTL_TY());
            preparedStatement.setString(8, item.getX_SWIFI_INSTL_MBY());
            preparedStatement.setString(9, item.getX_SWIFI_SVC_SE());
            preparedStatement.setString(10, item.getX_SWIFI_CMCWR());
            preparedStatement.setString(11, item.getX_SWIFI_CNSTC_YEAR());
            preparedStatement.setString(12, item.getX_SWIFI_INOUT_DOOR());
            preparedStatement.setString(13, item.getX_SWIFI_REMARS3());
            preparedStatement.setString(14, item.getLAT());
            preparedStatement.setString(15, item.getLNT());
            preparedStatement.setString(16, item.getWORK_DTTM());

            //4. 쿼리실행
            int effected = preparedStatement.executeUpdate();

            // 5. 결과 확인
            if (effected > 0){
            	success = true;

            } else {
                System.out.println("저장 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            //6. 객체 연결 해제(close) - 무조건 실행 되어야하므로 마지막에 실행
            try {
                if (rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        return success;
    }
	
	public void insertHistory(String Lat, String Lnt){

        String url = "jdbc:mariadb://localhost:3306/wifiProject";
        String dbUserId = "testuser1";
        String dbPassword = "0000";

        //1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2. 커넥션 개채 생성
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            //3. 스테이트먼트 객체 생성
            String sql = " insert into LOCATION_HISTORY "
            		+ " (H_LAT, H_LNT, SEARCH_DATE) "
            		+ " values "
            		+ " (?, ?, now()) ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, Lat);
            preparedStatement.setString(2, Lnt);
            
            //4. 쿼리실행
            int effected = preparedStatement.executeUpdate();

            // 5. 결과 확인
            if (effected > 0){
                System.out.println("저장 성공");
            } else {
                System.out.println("저장 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            //6. 객체 연결 해제(close) - 무조건 실행 되어야하므로 마지막에 실행
            try {
                if (rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }
	
	public void deleteHistory (String id){

        String url = "jdbc:mariadb://localhost:3306/wifiProject";
        String dbUserId = "testuser1";
        String dbPassword = "0000";

        //1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //2. 커넥션 개채 생성
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            //3. 스테이트먼트 객체 생성
            String sql = " Delete from LOCATION_HISTORY "
            		+ " where ID = ?; ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, Integer.parseInt(id));

            //4. 쿼리실행
            int effected = preparedStatement.executeUpdate();

            // 5. 결과 확인
            if (effected > 0){
            	System.out.println("삭제 성공");

            } else {
                System.out.println("삭제 실패");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            //6. 객체 연결 해제(close) - 무조건 실행 되어야하므로 마지막에 실행
            try {
                if (rs != null && !rs.isClosed()){
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()){
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                if (connection != null && !connection.isClosed()){
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
    }

}
