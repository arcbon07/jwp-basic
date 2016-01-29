#### 1. Tomcat 서버를 시작할 때 웹 애플리케이션이 초기화하는 과정을 설명하라.
* Servlet 컨테이너의 초기화 과정
	1) init
		core.mvc.DispatcherServlet 에서 오버라이딩한 init메서드가 실행된다. 
		init에서 RequestMapping클래스를 사용하여 각 url들을 알맞은 controller에 매핍한다.
	2) db initialization
		next.support.context 클래스에서 오버라이딩한 contextInitialized 메서드가 실행된다.
		"jwp.sql" 데이터 베이스에 연결된다.
	

#### 2. Tomcat 서버를 시작한 후 http://localhost:8080으로 접근시 호출 순서 및 흐름을 설명하라.
* Servlet 컨테이너의 흐름
	1) init 초기화
	2) service
		core.mvc.DispatcherServlet 에서 오버라이딩한 service 메서드가 실행된다. 
		service 메서드에서 request의 url에 mapping된 controller를 선택한다.
		controller의 excute 메서드는 MapandView 객체를 반환한다.
		MapandView 객체의 render 메서드를 실행한다.
		--->http://localhost:8080으로 접근시
		http://localhost:8080의 접근은 index.jsp에서 
		 /qna/list.next로  redirect되어  /qna/list.next의 요청이 다시 온다.
		"/qna/list.next"는  ListController에 매핑되엇다.
		따라서 service 메서드에서  ListController가 controller가 된다.
		다음 ListConller의 excute 메서드가 실행된다.
		이 메서드는 jsp정보와 갱신된 질문목록의 정보가 담긴 ModelandView 객체를 반환한다.
		반환된 View 객체의 render 메서드를 실행한다.
		여기서 jsp정보에 담긴 attribute를 설정하고 "/qna/list.jsp"를 응답으로 보낸다.