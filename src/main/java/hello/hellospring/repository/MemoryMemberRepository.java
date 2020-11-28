package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        //대상이 null인 경우를 커버.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
        /*store의 각 값들values()에 stream()씌우고,
          store의 member의 getName이 입력한 name과 같을 때에 filter
          그러한 원소를 찾는다findAny()
         */
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
        //store의 모든 원소들 values()을 ArrayList로 반환한다.
    }

    //테스트에서, 메서드 테스트 하나 끝날때마다 store초기화
    public void clearStore(){
        store.clear();
    }
}
