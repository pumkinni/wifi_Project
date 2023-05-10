# Wifi Service(mini project)


## 공공 와이파이 정보 OPEN API를 통해 근처 와이파이 20개를 보여주는 시스템

project execution period : 2023.02.28 ~ 2023.03.02
*****

### 데이터 모델링 

<img width="664" alt="wifi-erd" src="https://user-images.githubusercontent.com/102509636/222399033-570963d5-6d07-4c2b-b09e-a3ccf88088a6.png">



### 홈화면

<img width="800" alt="스크린샷 2023-03-02 18 53 59" src="https://user-images.githubusercontent.com/102509636/222394808-305b2b85-bf6d-4e28-bbc5-1c30f4d5b8d2.png">

*****
### 기능 구현

1. 내 위치(위도, 경도) 가져오기

<img width="600" alt="스크린샷 2023-03-02 19 07 26" src="https://user-images.githubusercontent.com/102509636/222397765-5a6760be-e5b6-41cc-a4aa-3a972a24ad1e.png">

2. 근처 와이파이 정보 20개 보기
<img width="600" alt="스크린샷 2023-03-02 19 06 38" src="https://user-images.githubusercontent.com/102509636/222397891-6df431f9-e6d4-4329-9a64-2049ae929d7f.png">

3. 오픈 API를 통해 wifi 정보 가져오기

<img width="600" alt="스크린샷 2023-03-02 19 06 47" src="https://user-images.githubusercontent.com/102509636/222398038-04f5f8d8-925b-4219-977d-978d3a0321b1.png">

4. 근처 wifi 정보 보기 시 위도, 경도, 조회일자 정보 히스토리에 저장
<img width="600" alt="스크린샷 2023-03-02 19 06 53" src="https://user-images.githubusercontent.com/102509636/222398271-c9db2828-3fd4-4969-9628-29f2f95b571f.png">

5. 위치 히스토리 정보 삭제
<img width="600" alt="스크린샷 2023-03-02 19 06 58" src="https://user-images.githubusercontent.com/102509636/222398365-86c4cacf-bceb-4f3e-905d-75d9a9a9ccf9.png">

*****
### 파일
[Java]()

- [Java/Service](https://github.com/pumkinni/mini_project1/tree/main/src/main/java/service) : 서비스를 위한 자바 파일

  - [OpenApi.java](https://github.com/pumkinni/mini_project1/blob/main/src/main/java/service/OpenApi.java) : 시작 페이지, 마지막 페이지를 입력하여 open api에서 json 정보 받아오기

  - [Services.java](https://github.com/pumkinni/mini_project1/blob/main/src/main/java/service/Services.java) : 데이터 출력, 입력, 삭제를 위한 모든 메소드 정보

- [Java/wifiInforms](https://github.com/pumkinni/mini_project1/tree/main/src/main/java/wifiInforms) : 와이파이 정보를 주고 받기 위한 클래스들


[webapp](https://github.com/pumkinni/mini_project1/tree/main/src/main/webapp)

- [dataProcess.jsp](https://github.com/pumkinni/mini_project1/blob/main/src/main/webapp/dataProcess.jsp) : 근처 와이파이 정보 보기 클릭 시 작동 (히스토리에 정보저장, 근처 와이파이 정보들 출력을 위한 아이디 전달)

- [history.jsp](https://github.com/pumkinni/mini_project1/blob/main/src/main/webapp/history.jsp) : 히스토리 정보 출력 페이지

- [index.jsp](https://github.com/pumkinni/mini_project1/blob/main/src/main/webapp/index.jsp) : 홈화면

- [load-wifi.jsp](https://github.com/pumkinni/mini_project1/blob/main/src/main/webapp/load-wifi.jsp) : 와이파이 정보를 가져오는 페이지


