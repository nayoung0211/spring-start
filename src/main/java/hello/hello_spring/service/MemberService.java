package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Transactional
public class MemberService {
    private final MemberRepository memberRepository;

    public  MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member){
        //회원가입시 시간 측정
        long start = System.currentTimeMillis();
        try{
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        }finally {
            long end = System.currentTimeMillis();
            long timeMs = end - start;
            System.out.println(timeMs);
        }
    }

    //중복 회원 검증
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()).ifPresent(m -> {
           throw new IllegalStateException("이미 존재하는 회원입니다.");
       });
    }
    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        long start = System.currentTimeMillis();
        try{
            return memberRepository.findAll();
        }finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println(timeMs);
        }
    }

    public Optional<Member> findOne(Long id){
        return memberRepository.findById(id);
    }
}
