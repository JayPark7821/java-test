package me.jaypark.javatest.study;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import me.jaypark.javatest.domain.Member;
import me.jaypark.javatest.domain.Study;
import me.jaypark.javatest.member.MemberService;

@ExtendWith(MockitoExtension.class)
class StudyServiceTest {

	@Mock
	MemberService memberService;

	@Mock
	StudyRepository studyRepository;

	
	@Test
	void  createStudyService() throws Exception {
		MemberService memberService = new MemberService() {
			@Override
			public Optional<Member> findById(Long memberId) {
				return Optional.empty();
			}

			@Override
			public void validate(Long memberId) {

			}

			@Override
			public void notify(Study newstudy) {

			}

			@Override
			public void notify(Member member) {

			}
		};

		StudyRepository studyRepository = new StudyRepository(){

			@Override
			public List<Study> findAll() {
				return null;
			}

			@Override
			public List<Study> findAll(Sort sort) {
				return null;
			}

			@Override
			public Page<Study> findAll(Pageable pageable) {
				return null;
			}

			@Override
			public List<Study> findAllById(Iterable<Long> longs) {
				return null;
			}

			@Override
			public long count() {
				return 0;
			}

			@Override
			public void deleteById(Long aLong) {

			}

			@Override
			public void delete(Study entity) {

			}

			@Override
			public void deleteAllById(Iterable<? extends Long> longs) {

			}

			@Override
			public void deleteAll(Iterable<? extends Study> entities) {

			}

			@Override
			public void deleteAll() {

			}

			@Override
			public <S extends Study> S save(S entity) {
				return null;
			}

			@Override
			public <S extends Study> List<S> saveAll(Iterable<S> entities) {
				return null;
			}

			@Override
			public Optional<Study> findById(Long aLong) {
				return Optional.empty();
			}

			@Override
			public boolean existsById(Long aLong) {
				return false;
			}

			@Override
			public void flush() {

			}

			@Override
			public <S extends Study> S saveAndFlush(S entity) {
				return null;
			}

			@Override
			public <S extends Study> List<S> saveAllAndFlush(Iterable<S> entities) {
				return null;
			}

			@Override
			public void deleteAllInBatch(Iterable<Study> entities) {

			}

			@Override
			public void deleteAllByIdInBatch(Iterable<Long> longs) {

			}

			@Override
			public void deleteAllInBatch() {

			}

			@Override
			public Study getOne(Long aLong) {
				return null;
			}

			@Override
			public Study getById(Long aLong) {
				return null;
			}

			@Override
			public Study getReferenceById(Long aLong) {
				return null;
			}

			@Override
			public <S extends Study> Optional<S> findOne(Example<S> example) {
				return Optional.empty();
			}

			@Override
			public <S extends Study> List<S> findAll(Example<S> example) {
				return null;
			}

			@Override
			public <S extends Study> List<S> findAll(Example<S> example, Sort sort) {
				return null;
			}

			@Override
			public <S extends Study> Page<S> findAll(Example<S> example, Pageable pageable) {
				return null;
			}

			@Override
			public <S extends Study> long count(Example<S> example) {
				return 0;
			}

			@Override
			public <S extends Study> boolean exists(Example<S> example) {
				return false;
			}

			@Override
			public <S extends Study, R> R findBy(Example<S> example,
				Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
				return null;
			}
		};

		StudyService studyService = new StudyService(memberService,studyRepository);
		assertNotNull(studyService);
	}

	@Test
	void createStudyService1() throws Exception {
		MemberService memberService = mock(MemberService.class);
		StudyRepository studyRepository = mock(StudyRepository.class);

		StudyService studyService = new StudyService(memberService, studyRepository);
		assertNotNull(studyService);
	}


	@Test
	void createNewStudy(@Mock MemberService memberService,
		 					  @Mock StudyRepository studyRepository) throws Exception {

		StudyService studyService = new StudyService(memberService, studyRepository);

		Member member = new Member();
		member.setId(1L);
		member.setEmail("test@mail.com");

		when(memberService.findById(any())).thenReturn(Optional.of(member));
		Study java = new Study(10, "java");
		studyService.createNewStudy(1L, java);

		assertEquals("test@mail.com", memberService.findById(1L).get().getEmail());
		assertEquals("test@mail.com", memberService.findById(2L).get().getEmail());

		// when(memberService.findById(1L)).thenThrow(new RuntimeException());
		doThrow(new IllegalArgumentException()).when(memberService).validate(1L);

		assertThrows(IllegalArgumentException.class, () -> {
			memberService.validate(1L);

		});

		assertNotNull(studyService);

	}


	@Test
	void createNewStudy2(@Mock MemberService memberService,
		@Mock StudyRepository studyRepository) throws Exception {

		StudyService studyService = new StudyService(memberService, studyRepository);

		Member member = new Member();
		member.setId(1L);
		member.setEmail("test@mail.com");

		when(memberService.findById(any()))
			.thenReturn(Optional.of(member))
			.thenThrow(new RuntimeException())
			.thenReturn(Optional.empty());

		Optional<Member> byId = memberService.findById(1L);
		assertEquals("test@mail.com", byId.get().getEmail());

		assertThrows(RuntimeException.class, ()->{
			memberService.findById(2L);
		});

		assertEquals(Optional.empty(),  memberService.findById(3L));

	}

	@Test
	void createNewStudy3() throws Exception {

		StudyService studyService = new StudyService(memberService, studyRepository);

		Member member = new Member();
		member.setId(1L);
		member.setEmail("test@mail.com");
		Study study = new Study(10, "테스트");

		when(memberService.findById(1L)).thenReturn(Optional.of(member));
		when(studyRepository.save(study)).thenReturn(study);

		studyService.createNewStudy(1L, study);
		assertEquals(member.getId(), study.getOwnerId());

		verify(memberService, times(1)).notify(study);
		verify(memberService, times(1)).notify(member);
		verify(memberService, never()).validate(any());

		InOrder inOrder = inOrder(memberService);
		inOrder.verify(memberService).notify(study);

		inOrder.verify(memberService).notify(member);
	}
}