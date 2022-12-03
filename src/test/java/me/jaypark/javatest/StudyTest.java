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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnJre;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.JRE;
import org.junit.jupiter.api.condition.OS;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

	@Test
	@DisplayName("스터디 만들기 \uD83D\uDE31")
	void create() throws Exception{
		Study study = new Study( 10);
		assertAll(
			() -> assertNotNull(study),
			() -> assertEquals(StudyStatus.DRAFT, study.getStatus(), ()->"스터디를 처음 만들면 상태값이 DRAFT여야 한다."),
			() -> assertTrue(study.getLimit() > 0 , "스터디 최대 참석 가능인원은 0명 이상")
		);
		System.out.println("create");
	}


	@Test
	void create_new_study() throws Exception{

		IllegalArgumentException exception =
			assertThrows(IllegalArgumentException.class, () -> new Study(-19));
		assertEquals("limit은 0보다 커야 한다.", exception.getMessage());

		System.out.println("create1");
	}

	@Test
	void create_new_study1() throws Exception{

		assertTimeout(Duration.ofMillis(100), () -> {
			new Study(10);
			Thread.sleep(300);
		});
	}

	@Test
	@DisabledOnOs(OS.WINDOWS)
	void create_new_study2() throws Exception{

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