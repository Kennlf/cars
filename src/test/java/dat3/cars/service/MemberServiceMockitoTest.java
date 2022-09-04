package dat3.cars.service;

import dat3.cars.dto.MemberRequest;
import dat3.cars.dto.MemberResponse;
import dat3.cars.entity.Member;
import dat3.cars.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class MemberServiceMockitoTest {

    @Mock
    MemberRepository memberRepository;

    @Autowired
    MemberService memberService;

    @BeforeEach
    public void setup() {
        memberService = new MemberService(memberRepository);
    }

    @Test
    void getMembers() {
        Mockito.when(memberRepository.findAll()).thenReturn(List.of(
                new Member("m1", "pw", "m1@a.dk", "aa", "aaa", "aaaa", "aaaa", "1234"),
                new Member("m2", "pw", "mm@a.dk", "bb", "bbb", "bbbb", "bbbb", "1234")
        ));
        List<MemberResponse> members = memberService.getMembers();
        assertEquals(2, members.size());
    }


    @Test
    void findMemberByUsername() {
    }

    @Test
    void addMember() throws Exception {
        Member m = new Member("m1", "pw", "m1@a.dk", "aa", "aaa", "aaaa", "aaaa", "1234");
        //If you wan't to do this for Car you have to manually set the id. REMEMBER there is NO real database
        Mockito.when(memberRepository.save(any(Member.class))).thenReturn(m);
        MemberRequest request = new MemberRequest(m);
        MemberResponse found = memberService.addMember(request);
        assertEquals("m1", found.getUsername());
    }


    @Test
    void editMember() {
    }

    @Test
    void setRankingForUser() {
    }

    @Test
    void deleteByUsername() {
    }
}