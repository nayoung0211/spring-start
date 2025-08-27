package hello.hello_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import hello.hello_spring.domain.Member;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>,MemberRepository {

    @Override
    Optional<Member> findByName(String name);

}
