## JMeter
### 1. JMeter 소개
* 성능 측정 및 부하(load) 테스트 기능을 제공하는 오픈 소스 자바 어플리케이션
* 다양한 형태의 어플리케이션 테스트 지원
  * 웹 - HTTP,HTTPS
  * SOAP / REST 웹 서비스
  * FTP
  * 데이터베이스(JDBC 사용)
  * Mail(SMTP, POP3, IMAP)

* CLI 지원
  * CI 또는 CD 툴과 연동할 때 편리함.
  * UI사용하는 것보다 메모리 등 시스템 리소스를 적게 사용

* 주요 개념
  * Thread Group: 한 쓰레드 당 유저 한명
  * Sampler: 어떤 유저가 해야 하는 액션 
  * Listener: 응답을 받았을 할 일 (리포팅, 검증, 그래프 그리기 등)
  * Configuration: Sampler 또는 Listener가 사용할 설정 값 (쿠키, JDBC 커넥션 등)
  * Assertion: 응답이 성공적인지 확인하는 방법 (응답 코드, 본문 내용 등)

### 2. JMeter 사용하기
* Thread Group 만들기
  * Number of Threads: 쓰레드 개수
  * Ramp-up period: 쓰레드 개수를 만드는데 소요할 시간
  * Loop Count: infinite 체크 하면 위에서 정한 쓰레드 개수로 계속 요청 보내기. 값을 입력하면 해당 쓰레드 개수 X 루프 개수 만큼 요청 보냄.

* Sampler 만들기
  * 여러 종류의 샘플러가 있지만 그 중에 우리가 사용할 샘플러는 HTTP Request 샘플러.
  * HTTP Sampler
    * 요청을 보낼 호스트, 포트, URI, 요청 본문 등을 설정
  * 여러 샘플러를 순차적으로 등록하는 것도 가능하다.

* Listener 만들기
  * View Results Tree
  * View Resulrts in Table
  * Summary Report
  * Aggregate Report
  * Response Time Graph
  * Graph Results

* Assertion 만들기
  * 응답 코드 확인
  * 응답 본문 확인

* CLI 사용하기
  * jmeter -n -t 설정 파일 -l 리포트 파일
