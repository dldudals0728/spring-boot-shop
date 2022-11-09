package kr.ac.gachon.shop.service;

import kr.ac.gachon.shop.entity.Member;
import kr.ac.gachon.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional // 개발할 때만 붙히고, 실제 서비스에서는 붙히면 안됨. 에러가 생기면 DB를 알아서 롤백해준다!!
@RequiredArgsConstructor //bean 주입방법 생성자 final member, @NonNull member 생성자 생성함. 아니면 field에서 @Autowired(final 불가), 또는 생성자 만들어서 bean 주입
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        // validateDuplicateMember(member);
        this.validateDuplicateMember(member);
        // .save() method: JpaRepository가 가지고있음
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            // 여기서 다시 로그인 화면으로 돌아가게 해준다거나 하는 로직 생성
            throw new IllegalStateException("이미 가입된 회원입니다."); // 예외 처리
        }
    }


    // 이렇게 하면 어느곳에 있던지 여기 와서 해당 로직을 수행하게 할 수 있다.
    // 로그인 로직
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        
        Member member = memberRepository.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        // builer()방법을 사용할 경우, 무제한으로 늘어날 수 있는 생성자를 구현할 필요가 없고, 명시적으로 어떤 값을 사용하는지 쉽게 볼 수 있음!
        // 로그인 시 이용할 정보를 여기다가 세팅하면 됨!
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

}
