## Mockito
### 1. Mockito 소개
* Mock : 진짜 객체와 비슷하게 동작하지만 프로그래머가 직접 그 객체의 행동을 관리하는 객체.
* Mockito: Mock 객체를 쉽게 만들고 관리하고 검증할 수 있는 방법을 제공한다.
* 테스트를 작성하는 자바 개발자 50%+ 사용하는 Mock 프레임워크

### 2. Mockito 시작하기
* 스프링 부트 2.2+ 프로젝트 생성시 spring-boot-starter-test에서 자동으로 Mockito를 추가해 줌.
* 다음 3개만 알면 Mock을 활용한 테스트를 쉽게 작성할 수 있다.
  * Mock을 만드는 방법
  * Mock이 어떻게 동작해야 하는지 관리하는 방법
  * Mock의 행동을 검증하는 방법

### 3. Mock 객체 만들기
* Mockito.mock() 메소드로 만드는 방법
```java
MemberService memberService = mock(MemberService.class);
StudyRepository studyRepository = mock(StudyRepository.class);
```

* @Mock 어노테이션으로 만드는 방법
  * JUnit 5 extension으로 MockitoExtension을 사용해야 한다.
  * 필드
  * 메소드 매개변수
  
```java
@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

  @Mock
  MemberService memberService;

  @Mock
  StudyRepository studyRepository;
}
```  
```java
@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

  @Test
  void createStudyService(@Mock MemberService memberService,
          @Mock StudyRepository studyRepository) {
    StudyService studyService = new StudyService(memberService, studyRepository);
    assertNotNull(studyService);
  }
}
```

### 4. Mock 객체 Stubbing
* 모든 Mock 객체의 행동
  * Null을 리턴한다.(Optional 타입은 Optional.empty)
  * Primitive 타입은 기본 Primitive 값.
  * 콜렉션은 비어있는 콜렉션.
  * Void 메소드는 예외를 던지지 않고 아무런 일도 발생하지 않는다.
* Mock 객체를 조작해서
  * 특정한 매개변수를 받은 경우 특정한 값을 리턴하거나 예외를 던지도록 만들 수 있다.
  * Void 메소드 특정 매개변수를 받거나 호출된 경우 예외를 발생 시킬 수 있다.
  * 메소드가 동일한 매개변수로 여러번 호출될 때 각기 다르게 행동하도록 조작할 수도 있다.