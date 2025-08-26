package hello.hello_spring.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import hello.hello_spring.domain.Member;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save(){
        Member member = new Member();
        member.setName("string");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        assertThat(result).isEqualTo(member);
    }
    @Test
    public void findById(){
        Member member = new Member();
        member.setName("string");
        repository.save(member);
        Optional<Member> result = repository.findById(member.getId());

        assertThat(result.isPresent()).isTrue();
    }
    @Test
    public void findByName(){
        Member member = new Member();
        member.setName("spring1");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member spring1 = repository.findByName("spring1").get();
        assertThat(spring1).isEqualTo(member);


    }
    @Test
    public void findAll(){
        Member member = new Member();
        member.setName("spring1");
        repository.save(member);
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);

    }


}