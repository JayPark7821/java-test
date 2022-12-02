## JUnit
### 1. JUnit 소개
* 자바 개발자가 가장 많이 사용하는 테스팅 프레임워크.
* 단위 테스트를 작성하는 자바 개발자 93% JUnit을 사용함.
* 자바 8 이상을 필요로 함.
* 대체재: TestNG, Spock,

![image](https://user-images.githubusercontent.com/60100532/204076182-f68a1b86-5a64-4f6d-8eef-458b0bbea804.png)

Platform : 테스트를 실행해주는 런처 제공. TestEngine API제공
Jupiter : TestEngine API 구현체로 JUnit5를 제공
Vintage : JUnit 4와 3을 지원하는 TestEngine 구현체

### 2. JUnit 5: 시작하기
* 스프링 부트 프로젝트 만들기
  * 2.2+ 버전의 스프링 부트 프로젝트를 만든다면 기보능로 JUnit5 의존성이 추가 됨.
* 기본 어노테이션
  * @Test
  * @BeforeAll / @AfterAll 
  * @BeforeEach / @AfterEach
  * @Disabled

* @BeforeAll 
  * 모든 테스트를 실행하기 전에 딱 한번만 실행
  * 반드시 static void로 메소드를 작성해야함. private 불가.
* @AfterAll 
  * 모든 테스트를 실행한 후에 딱 한번만 실행 
  * 반드시 static void로 메소드를 작성해야함. private 불가.
* @BeforeEach
  * 각각의 테스트를 실행하기 이전에 매번 실행됨
* @AfterEach
  * 각각의 테스트를 실행한 이후에 매번 실행됨
  
### 2. JUnit 5: 테스트 이름 표기하기
* @DisplayNameGeneration
  * Method와 Class 레퍼런스를 사용해서 테스트 이름을 표기하는 방법 설정
  * 기본 구현체로 ReplaceUnderscores 제공
* @DisplayName
  * 어떤 테스트인지 테스트 이름을 보다 쉽게 표현할 수 있는 방법을 제공하는 애노테이션.
  * @DisplayNameGeneration 보다 우선 순위가 높다.