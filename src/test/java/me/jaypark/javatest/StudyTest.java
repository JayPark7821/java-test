package me.jaypark.javatest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.aggregator.AggregateWith;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import me.jaypark.javatest.domain.Study;
import me.jaypark.javatest.domain.StudyStatus;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// @ExtendWith(FindSlowTestExtension.class)
class StudyTest {

	@RegisterExtension
	static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

	@Order(2)
	@Test
	@DisplayName("스터디 만들기 \uD83D\uDE31")
	void create() throws Exception {
		Study study = new Study(10);
		Thread.sleep(1005L);

		assertAll(
			() -> assertNotNull(study),
			() -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT여야 한다."),
			() -> assertTrue(study.getLimitCount() > 0, "스터디 최대 참석 가능인원은 0명 이상")
		);
		System.out.println("create");
	}

	@Order(1)
	@Test
	void create_new_study() throws Exception {

		IllegalArgumentException exception =
			assertThrows(IllegalArgumentException.class, () -> new Study(-19));
		assertEquals("limit은 0보다 커야 한다.", exception.getMessage());

		System.out.println("create1");
	}

	@Order(3)
	@Test
	void create_new_study1() throws Exception {

		assertTimeout(Duration.ofMillis(100), () -> {
			new Study(10);
			Thread.sleep(300);
		});
	}

	@Order(4)
	@Test
	@DisabledOnOs(OS.WINDOWS)
	void create_new_study2() throws Exception {

		assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
			new Study(10);
			Thread.sleep(300);
		});

		System.out.println("create1");
	}

	@Test
	@EnabledOnOs({OS.MAC, OS.LINUX})
	@DisabledOnJre(JRE.JAVA_8)
	@EnabledIfEnvironmentVariable(named = "TEST_NEW", matches = "LOCAL")
	void env_variable_test() {
		String test_env = System.getenv("TEST_ENV");
		System.out.println("test_env = " + test_env);
		assumeTrue("LOCAL".equalsIgnoreCase(test_env));

		assumingThat("LOCAL".equalsIgnoreCase(test_env), () -> {
			System.out.println("test_env = " + test_env);
			Study study = new Study(10);
			assertNotNull(study);
		});
		assumingThat("DEV".equalsIgnoreCase(test_env), () -> {
			System.out.println("test_env = " + test_env);
			Study study = new Study(10);
			assertNotNull(study);
		});
	}

	// @Test
	// @Tag("fast")
	@FastTest
	void tag2() throws Exception {
		Study study = new Study(10);
		assertNotNull(study);
	}

	@Test
	@Tag("slow")
	void tag1() throws Exception {
		Study study = new Study(10);
		assertNotNull(study);
	}

	@DisplayName("스터디 만들기")
	@RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
	void repeatTest(RepetitionInfo info) throws Exception {
		System.out.println("repeat Test");
		System.out.println("info.getCurrentRepetition() = " + info.getCurrentRepetition());
		System.out.println("info.getTotalRepetitions() = " + info.getTotalRepetitions());
	}

	@DisplayName("스터디 만들기")
	@ParameterizedTest(name = "{index}, {displayName} message = {0}")
	@ValueSource(strings = {"날씨가", "많이", "추워지고", "있네요"})
	void parameterizedTest(String message) {
		System.out.println(message);
	}

	@DisplayName("스터디 만들기")
	@EmptySource
	@NullSource
	@ParameterizedTest(name = "{index}, {displayName} message = {0}")
	@ValueSource(ints = {10, 20, 30})
	void parameterizedTest2(@ConvertWith(StudyConverter.class) Study study) {
		System.out.println(study.getLimitCount());
	}

	static class StudyConverter extends SimpleArgumentConverter {
		@Override
		protected Object convert(Object source, Class<?> targetType) throws ArgumentConversionException {
			assertEquals(Study.class, targetType, "Can only convert to Study");
			return new Study(Integer.parseInt(source.toString()));
		}
	}

	@DisplayName("스터디 만들기")
	@EmptySource
	@NullSource
	@ParameterizedTest(name = "{index}, {displayName} message = {0}")
	@CsvSource({"10, '자바 스터디'", "20, 스프링"})
	void parameterizedTest3(Integer limit, String name) {
		Study study = new Study(limit, name);
		System.out.println("study = " + study);
	}

	@DisplayName("스터디 만들기")
	@EmptySource
	@NullSource
	@ParameterizedTest(name = "{index}, {displayName} message = {0}")
	@CsvSource({"10, '자바 스터디'", "20, 스프링"})
	void parameterizedTest4(ArgumentsAccessor argumentsAccessor) {

		Study study = new Study(argumentsAccessor.getInteger(0), argumentsAccessor.getString(1));
		System.out.println("study = " + study);
	}

	@DisplayName("스터디 만들기")
	@EmptySource
	@NullSource
	@ParameterizedTest(name = "{index}, {displayName} message = {0}")
	@CsvSource({"10, '자바 스터디'", "20, 스프링"})
	void parameterizedTest5(@AggregateWith(StudyAggregator.class) Study study) {
		System.out.println("study = " + study);
	}

	static class StudyAggregator implements ArgumentsAggregator {
		@Override
		public Object aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws
			ArgumentsAggregationException {
			return new Study(accessor.getInteger(0), accessor.getString(1));
		}
	}

	// 모든 테스트를 실행하기 전에 딱 한번만 실행
	// 반드시 static void로 메소드를 작성해야함. private 불가.
	@BeforeAll
	static void beforeAll() {
		System.out.println("beforeAll");
	}

	// 모든 테스트를 실행한 후에 딱 한번만 실행
	// 반드시 static void로 메소드를 작성해야함. private 불가.
	@AfterAll
	static void afterAll() {
		System.out.println("afterAll");
	}

	// 각각의 테스트를 실행하기 이전에 매번 실행됨
	@BeforeEach
	void beforeEach() {
		System.out.println("beforeEach");
	}

	// 각각의 테스트를 실행한 이후에 매번 실행됨
	@AfterEach
	void afterEach() {
		System.out.println("afterEach");
	}

}