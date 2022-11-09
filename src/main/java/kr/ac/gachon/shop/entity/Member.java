package kr.ac.gachon.shop.entity;

import kr.ac.gachon.shop.constant.Role;
import kr.ac.gachon.shop.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "member")
@Getter
// Entity에서 Setter는 없어야된다. set을 하면 DB field에 직접 update를 쳐버린다!!
// 따라서 모든 인자값을 받는 생성자를 만들거나, builder 타입으로 제공해아 함!!
@Setter
@ToString
// 원칙상 MemberEntity가 맞지만, 개발하다 보면 뒤에 prefix로 Entity 붙힌거 때문에 에러가 나는 상황이 발생할 수 있어서 안붙힘.
public class Member {

    @Id
    @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    // Dto를 받아 Entity로 변경해주는 메서드 생성
    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword());
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }
}
