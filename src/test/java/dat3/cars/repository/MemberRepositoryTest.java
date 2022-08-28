package dat3.cars.repository;

import dat3.cars.entity.Member;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    private static String mem1;
    private static String mem2;

    @BeforeAll
    public static void setUpData(@Autowired MemberRepository memberRepository){
        Member m1 = new Member("User1", "test", "a@a.dk", "Anders", "And", "Det lille hus", "Andeby", 1111, true, 1);
        Member m2 = new Member("User2", "testtest", "b@b.dk", "Nemo", "fisk", "En annemone", "Havet", 1522, true, 1);

        memberRepository.save(m1);
        memberRepository.save(m2);

        mem1 = m1.getUsername();
        mem2 = m2.getUsername();
    }

    @Test
    public void testFindByEmail(){
        Member found = memberRepository.findMemberByEmail("a@a.dk");
        assertEquals(mem1, found.getUsername());

    }


    @Test
    void findMemberByZip() {
        List<Member> members = memberRepository.findMemberByZip(1111);
        assertEquals(1, members.size());
    }

    @Test
    void findMemberByZipBetween() {
        List<Member> members = memberRepository.findMemberByZipBetween(0, 1200);
        assertEquals(1, members.size());

        members = memberRepository.findMemberByZipBetween(1200, 1523);
        assertEquals(1, members.size());

        members = memberRepository.findMemberByZipBetween(0, 1550);
        assertEquals(2, members.size());
    }
}