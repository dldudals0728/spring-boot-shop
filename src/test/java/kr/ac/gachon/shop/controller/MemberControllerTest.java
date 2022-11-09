package kr.ac.gachon.shop.controller;

import kr.ac.gachon.shop.dto.MemberFormDto;
import kr.ac.gachon.shop.entity.Member;
import kr.ac.gachon.shop.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional  // query 문제 시 롤백 해준다! (해도 되고, 안해도 됨. but, 실 서비스에서는 빼는게 good!) 즉, 테스트가 끝나면 DB가 rollback 됨.
@TestPropertySource(locations="classpath:application-test.properties")
class MemberControllerTest {
    @Autowired
    private MemberService memberService;

    // MockMvc: test 시에는 html, 즉 form이 없음. 이 떄 Mvc를 대체할 수 있는 것이 MockMvc
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member createMember(String email, String password){
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail(email);
        memberFormDto.setName("홍길동");
        memberFormDto.setAddress("서울시 마포구 합정동");
        memberFormDto.setPassword(password);
        Member member = Member.createMember(memberFormDto, passwordEncoder);
        return memberService.saveMember(member);
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    public void loginSuccessTest() throws Exception{
        String email = "test@email.com";
        String password = "1234";
        this.createMember(email, password);
        // 마치 form을 submit 한 것처럼 해준다!
        mockMvc.perform(formLogin().userParameter("email")
                        .loginProcessingUrl("/members/login")
//                        .user(email).password(password))
                        .user(email).password("123"))
                .andExpect(SecurityMockMvcResultMatchers.authenticated());
    }

    @Test
    @DisplayName("로그인 실패 테스트")
    public void loginFailTest() throws Exception{
        String email = "test@email.com";
        String password = "1234";
        this.createMember(email, password);
        mockMvc.perform(formLogin().userParameter("email")
                        .loginProcessingUrl("/members/login")
                        .user(email).password("12345"))
                .andExpect(SecurityMockMvcResultMatchers.unauthenticated());

    }
}