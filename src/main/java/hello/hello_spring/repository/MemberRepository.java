package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;


public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findByName(String name);
    Optional<Member> findById(Long id);
    List<Member> findAll();

}
