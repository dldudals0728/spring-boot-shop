package kr.ac.gachon.shop.service;

import kr.ac.gachon.shop.entity.Member;
import kr.ac.gachon.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional // 개발할 때만 붙히고, 실제 서비스에서는 붙히면 안됨. 에러가 생기면 DB를 알아서 롤백해준다!!
@RequiredArgsConstructor //bean 주입방법 생성자 final member, @NonNull member 생성자 생성함. 아니면 field에서 @Autowired(final 불가), 또는 생성자 만들어서 bean 주입
public class MemberService{

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
}
