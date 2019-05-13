# Hanulproject
teamA의 안드로이드 팀프로젝트
~~~
#각 파트별 컨트롤러 번호 

1 = notice
2 = complain
3 = community
4 = setting
5 = user
6 = status
~~~
~~~
레이아웃에 있는 id는

community > cm()---
complain > cp()---
list > l()---
notice > n()---
setting > s()---
user > u()---
status > st()---

형식으로 지정함

()안에는 기능을 넣음
list > (아무것도 안넣음)
detail > d
modify > m

---  부분엔 VO 의 항목들과 같음

자바파일에서 '_'있는파일(Menu_main)은 레이아웃과 관련된 파일
없으면 기능과 관련된 파일

각 메뉴에 대하여 select, insert, update 기능은 따로 빼서 합함

select 할때 생성자가 여러가지를 만들 수 없어서
초기화 하는 메서드를 따로 만들어 놓았음. (꼭 해줄것!)

파싱하는 부분만 모아서 task > task > ReadMessage 클래스로 모아놓음
메서드는 (DB이름)ReadMessage(JsonReader reader)임

vo는 vo끼리 패키지에 있음
adapter도 adapter끼리 패키지로 묶어놓음
~~~
