package me.jaypark.javatest.member;

import me.jaypark.javatest.domain.Member;
import me.jaypark.javatest.domain.Study;
import java.util.Optional;

public interface MemberService {

    Optional<Member> findById(Long memberId);

    void validate(Long memberId);

    void notify(Study newstudy);

    void notify(Member member);
}
