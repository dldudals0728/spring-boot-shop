package kr.ac.gachon.shop.controller;

import kr.ac.gachon.shop.component.Weapon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserBeanController {
    // @Autowired를 통해 객체화.
    @Autowired // 필드 주입 방법
    @Qualifier("superShotGun")
    private Weapon swp;

    private final Weapon bwp; // 생성자 주입시 final 선언

    @Autowired // 생성자 주입 방법
    // UserBeanController의 생성자에 누군가가 객체를 던져 주고, 호출해 준다. ==> spring container가 해줌! @Autowired를 사용함으로써 던져주는게 가능
    // *** spring container가 가지고 있는 객체가 뭐다? bean! ***
    public UserBeanController(Weapon bwp) {
        // @Qualifier("basicShotGun")가 Weapon 타입의 객체의 @Primary 선언으로 생략 가능
        this.bwp = bwp;
    }
    /*
    // @Primary가 없었다면, 아래와 같이 @Qualifier를 이용해서 건네줄 수 있다!
    @Autowired // 생성자 주입 방법
    public UserBeanController(@Qualifier("basicShotGun") Weapon bwp) {
        this.bwp = bwp;
    }
    */

    @GetMapping("/b")
    public String main(Model m){
        m.addAttribute("name","홍길동");
        // bwp라는 객체의 getModel()함수를 호출했다. 그럼 누군가는 bwp를 객체화 했다는 뜻.
        m.addAttribute("weapon", bwp.getModel());
        return "user_bean";
    }

    @GetMapping("/s")
    // @ResponseBody를 타입 옆에 붙여되 되네?
    // ResponseEntity<Object> vs. ResponseEntity<Model>v ??
    public @ResponseBody ResponseEntity<Model> get(Model m) {
        m.addAttribute("name","홍길동");
        m.addAttribute("weapon", swp.getModel());
        return new ResponseEntity<Model>(m, HttpStatus.OK);
    }
}
