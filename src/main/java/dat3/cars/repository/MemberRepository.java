package dat3.cars.repository;

import dat3.cars.entity.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, String>{

    Member findMemberByEmail(String email);
    List<Member> findMemberByZip(int zip);
    List<Member> findMemberByZipBetween(int start, int end);

}
