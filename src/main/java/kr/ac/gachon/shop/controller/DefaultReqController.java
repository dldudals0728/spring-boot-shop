package kr.ac.gachon.shop.controller;

import kr.ac.gachon.shop.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// 복붙으로 package를 import하지 못했을 때는, alt + enter !
// @Controller 설정 시, 해당 url 주소로 접속 가능!
@Controller
// @RequestMapping("/req") [Get, Put, Delete, Post]Mapping 있음!
// 프로그래머에 의해서 808이후의 주소를 "/req"로 결정할 수 있다! ==> home 주소를 결정할 수 있는것 같다. ==> 정보 은닉!
// 기존에는 디렉토리 형식을 사용했었는데, 그러면 해킹에 취약(해당 디렉토리에 이 파일이 있구나 -> 이것만 바꾸면?)
@RequestMapping("/req")
public class DefaultReqController {

    // http://localhost:8080/req/html
    @RequestMapping(value = "/html", method = RequestMethod.GET)    // 이것도 길다 => @GetMapping("/html")
    public String html() {
        // get/index 로 라우팅!
        return "get/index";
    }

    // http://localhost:8080/req/html/안녕하세요
    // @PathVariable: 주소줄의 값을 받아오는 방식
    // Model m: Spring Container가 넘겨주는 모델! 여기 m에다가 값을 셋팅
    @RequestMapping(value="/html/{msg}", method = RequestMethod.GET)
    public String html(@PathVariable String msg, Model m) {
        // key와 value로 셋팅
        // View로 전달할 때는 뭐로 하겠니 ===> Model!! *** 그림 중요!!! ***
        // Model 객체가 Controller 안에 있네
        m.addAttribute("msg", msg);
        return "get/index";
    }

    // http://localhost:8080/req/txt?msg=안녕하세요
    @RequestMapping(value="/txt", method = RequestMethod.GET)
    // @ResponseBody: @RestController는 @RequestMapping의 get방식으로 받고, @ResponseBody하는 것을 합쳐놓은 것이다.
    // @ResponseBody는 단순 출력!(View 아님)
    @ResponseBody
    // @RequestParam: ?뒤에 순수하게 get방식으로 들어오는 애들!
    public String html(@RequestParam(value = "msg", required = false) String msg) {
        return msg;
    }

    // http://localhost:8080/req/json
    // json 받기!! --> 종프 사용
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Object> html(Model m) {
        // java의 map(또는 hashMap) 배열로 넘어간다. 두 개 이상 넣을려면 addAttribute를 넣는다!
        m.addAttribute("model", "모델값 json");
//        m.addAttribute("test", "test json");

        // list 형태로 return!!
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    // http://localhost:8080/req/dto?name=홍길동&phone=01011111111
    // 접속 하려면 http://localhost:8080/post.html => http://localhost:8080/req/dto
    // 처음 error: method-RequestMethod.POST로 되어 있는데 post dir 존재 x.
    // static은 주소 그대로 사용하기 때문에 static에 post.html 생성 시 가능! (static/post.html => 8080/post.html)
    // 얘도 json type
    @RequestMapping(value = "/dto",  method = RequestMethod.POST)
    // 객체가 가지고 있는 멤버들을 json으로 변환하여 출력해줌.
    @ResponseBody
    public UserDto html(UserDto userDto) {
        // 객체 그대로 return!
        return userDto;
    }

    // 접속 하려면 http://localhost:8080/post.html => http://localhost:8080/req/dto-model
    @RequestMapping(value = "/dto-model", method = RequestMethod.POST)
    public String html(UserDto userDto, Model m) {
        m.addAttribute("dto", userDto);
        return "post/index";
    }
}
