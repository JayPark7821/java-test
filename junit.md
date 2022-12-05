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
  
### 3. JUnit 5: 테스트 이름 표기하기
* @DisplayNameGeneration
  * Method와 Class 레퍼런스를 사용해서 테스트 이름을 표기하는 방법 설정
  * 기본 구현체로 ReplaceUnderscores 제공
* @DisplayName
  * 어떤 테스트인지 테스트 이름을 보다 쉽게 표현할 수 있는 방법을 제공하는 애노테이션.
  * @DisplayNameGeneration 보다 우선 순위가 높다.

### 4. JUnit 5: Assertions
* org.junit.jupiter.api.Assertions.*
  * 실제 값이 기대한 값과 같은지 확인 -> assertEquals(expected,actual)
  * 값이 null이 아닌지 확인 -> assertNotNull(actual)
  * 다음 조건이 참(true)인지 확인 -> assertTrue(boolean)
  * 모든 확인 구문 확인 -> assertAll(excutables)
  * 예외 발생 확인 -> assertThrows(expectedType, executable)
  * 특정 시간 안에 실행이 완료되는지 확인(assertTimeout(duration, executable))

### 5. JUnit 5: 조건에 따라 테스트 실행하기
* 특정한 조건을 만족하는 경우에 테스트를 실행하는 방법
* org.junit.jupiter.api.Assumptions.*
  * assumeTrue(조건)
  * assumingThat(조건, 테스트)
* @Enabled_____와 @Disabled_____
  * OnOS
  * OnJre
  * IfSystemProperty
  * IfEnvrionmentVariable
  * If

### 6. JUnit 5: 태깅과 필터링
* 테스트 그룹을 만들고 원하는 테스트 그룹만 테스트를 실행할 수 있는 기능.
* @Tag
  * 테스트 메소드에 태그를 추가할 수 있다.
  * 하나의 테스트 메소드에 여러 태그를 사용할 수 있다.
* 인텔리j에서 특정 태그로 테스트 필터링 하는 방법

![image](https://user-images.githubusercontent.com/60100532/205474275-847f7c05-2307-4c23-9491-87f2a2eab41f.png)


### 7. JUnit 5: 커스텀 태그
* 어노테이션을 좋바하여 커스텀 태그를 만들 수 있다.
```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Tag("fast")
@Test
public @interface FastTest {
}

```


### 8. JUnit 5: 테스트 반복하기 1
* @RepeatedTest
  * 반복 횟수와 반복 테스트 이름을 설정할 수 있따.
    * {displayName}
    * {currentRepetition}
    * {totalRepetitions}
  * RepetitionInfo 타입의 인자를 받을 수 있다.
* @ParameterizedTest
  * 테스트에 여러 다른 매개변수를 대입해가며 반복 실행한다.
    * {displayName}
    * {index}
    * {arguments}
    * {0},{1}....


### 9. JUnit 5: 테스트 반복하기 2
* 인자 값들의 소스
  * @ValueSource
  * @NullSource, @EmptySource, @NullAndEmptySource
  * @EnumSource
  * @MethodSource
  * @MethodSource
  * @CsvSource
  * @CvsFileSource
  * @ArgumentSource
* 인자 값 타입 변환
  * 암묵적인 타입 변환
  * 명시적인 타입 변환
    * SimpleArgumentConverter 상속받은 구현체 제공
    * @ConvertWith
* 인자 값 조합
  * ArgumentsAccessor
  * 커스텀 Accessor
    * ArgumentsAggregator 인터페이스 구현
    * @AggregateWith

### 10. JUnit 5: 테스트 인스턴스
* JUnit은 테스트 메소드 마다 테스트 인스턴스를 새로 만든다.
  * 이것이 기본 적략.
  * 테스트 메소드를 독립적으로 실행하여 예상치 못한 부작용을 방지하기 위함이다.
  * 이 전략을 JUnit5에서 변경할 수 있다.
* @TestInstance(Lifecycle.PER_CLASS)
  * 테스트 클래스당 인스턴스를 하나만 만들어 사용한다.
  * 경우에 따라, 테스트 간에 공유하는 모든 상태를 @BeforeEach 또는 @AfterEach에서 초기화 할 필요가 있다.
  * @BeforeAll과 @AfterAll을 인스턴스 메소드 또는 인터페이스에 정의한 default 메소드로 정의할 수도 있다.
  * 