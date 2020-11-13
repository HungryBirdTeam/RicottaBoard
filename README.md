# Team Hungry Bird 

 Front : 정진권
 
 Back : 이정훈, 최문경
 
 Full Stack : 김강현, 정용우

리코타보드 서비스 페이지 는 이쪽으로 -> [Ricotta Board](http://k3a204.p.ssafy.io/)

![image](https://user-images.githubusercontent.com/23401317/99085450-8acb3100-260b-11eb-8184-7b4b542b7cda.png)



# Introduce Ricotta Board 

![ricotta-board-git-lab-img 002](https://user-images.githubusercontent.com/23401317/99073206-d3c5ba00-25f8-11eb-8e9a-5181a6a9764d.jpeg)
![ricotta-board-git-lab-img 008](https://user-images.githubusercontent.com/23401317/99073220-da543180-25f8-11eb-80a2-1468a4731584.jpeg)
![ricotta-board-git-lab-img 009](https://user-images.githubusercontent.com/23401317/99073377-20a99080-25f9-11eb-9e5e-b385f18ca382.jpeg)
![ricotta-board-git-lab-img 014](https://user-images.githubusercontent.com/23401317/99073275-f7890000-25f8-11eb-8536-ed862b72a64a.jpeg)

```mermaid
gantt
    dateFormat  MM-DD
    title       리코타 보드
    %% excludes    weekends
    %% (`excludes` accepts specific dates in YYYY-MM-DD format, days of the week ("sunday") or "weekends", but not the word "weekdays".)

    section 프로젝트 준비
    주제 선정				  :done, 10-11, 5d
    기능 목록 상세 도출			:done, 10-14, 4d
    화면 기획				  :done, 10-15, 3d
    개발 환경 구성             :done, 10-16, 3d
	
	section Frontend
    칸반보드 모듈		  	:done, kanban, 10-19, 3d
    캘린더 모듈	      	 :done, calendar, after kanban, 2d
    마크다운 모듈 	 	 :crit, active, 10-22,5d
    공지사항 팝업		 	 :crit, active, front1, after calendar, 5d
    포커스 모듈 표시   	  :front2, after front1, 5d
    
    section Board Server
    Redis 구조 최적화 	:done, 10-19, 3d
    MongoDB 구축 및 백업구현     	 :done, 10-21, 5d
    공지사항 API 구현     	 :crit, active, 10-28, 2d
    히스토리 기능 구현		:after front2, 5d
    
    section Auth server
    User CRUD 구현 		   :done, 10-19, 4d
    SMTP 메일 인증 구현       :crit, active, smtp, 10-23, 5d
    JWT 인증작업             :after smtp, 5d

    section RTC Server
    사전 공부 및 예제 실습               :done, rtc1, 10-19, 7d
    RTC 통신 구조 설계				 :crit, active, rtc2, after rtc1, 2d
    RTC 다대다 통신 구현				 :after rtc2, 2d

    section Chat Server
    QA 및 버그 찾기               :11-02, 3d
    
    %%section 기타
    %%UCC제작 :11-23, 3d
    %%발표준비 :11-26, 2d
```