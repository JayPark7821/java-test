package me.jaypark.javatest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest {

	@Test
	@DisplayName("스터디 만들기 \uD83D\uDE31")
	void create() throws Exception{
		Study study = new Study();
		assertNotNull(study);
		System.out.println("create");
	}


	@Test
	void create_new_study() throws Exception{
		Study study = new Study();
		assertNotNull(study);
		System.out.println("create1");
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