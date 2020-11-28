package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    //속성
    private final MemberRepository memberRepository;

    //생성자
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //기능 메서드
    public Long join(Member member) {
        //같은 이름 중복X
        validateDuplicatedMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicatedMember(Member member){
        //같은 이름 중복X
        memberRepository.findByName(member.getName())
                .ifPresent(m
                        -> {throw new IllegalStateException("이미 존재하는 회원입니다");
                });
    }

      //전체 회원 조회
    public List<Member> findMembers(){
       return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
       return memberRepository.findById(memberId);
    }
}
