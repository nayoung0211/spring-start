package hello.hello_spring.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository  memoryMemberRepository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        memoryMemberRepository.clearStore();
    }

    @Test
    void join(){
        //given
        Member member = new Member();
        member.setName("hello");
        //when
        Long join = memberService.join(member);

        //then
        Member findMember = memberService.findOne(join).get();
        assertThat(findMember.getName()).isEqualTo("hello");
    }

    @Test
    public void 중복회원예외(){
        //given
        Member member = new Member();
        member.setName("hello");

        Member member2 = new Member();
        member2.setName("hello");

        //when
        memberService.join(member);
        IllegalStateException e = assertThrows(IllegalStateException.class,
            () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }

    @Test
    void findOne(){
        Member member = new Member();
        memberService.join(member);

    }

    @Test
    void findMembers(){
    }

}