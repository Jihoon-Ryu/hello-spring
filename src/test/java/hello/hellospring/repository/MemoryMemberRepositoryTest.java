package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.internal.TypeComparators;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //테스트 끝날 때마다 store 초기화
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test //마치 main함수 실행하는 것과 비슷하다
    public void save(){
        Member member = new Member();
        member.setName("JH");

        repository.save(member);
        //Optional이 걸린 것은 get()으로 꺼낼 수 있다.
        Member result = repository.findById(member.getId()).get();

        //result와 member가 같은가?
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("YJH");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Vika");
        repository.save(member2);

        Member result = repository.findByName("YJH").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("YJH");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Vika");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }

}
