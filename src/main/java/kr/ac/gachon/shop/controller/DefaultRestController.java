package kr.ac.gachon.shop.controller;

import kr.ac.gachon.shop.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/rest")
public class DefaultRestController {
    @GetMapping("/json")
    public ResponseEntity<Object> html(Model m) {
        m.addAttribute("model", "모델값 json");
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @GetMapping("/dto")
    public UserDto html(UserDto userDto) {
        return userDto;
    }

    @GetMapping("/map")
    public HashMap<String, String> html() {
        HashMap<String, String> map = new HashMap<>() {{
            put("이름", "이영민");
            put("나이", "24");
            put("국적", "한국");
        }};
        return map;
    }

    // 이런식으로 하면, 받을 값을 지정해놓지 않아도 됨!
    @GetMapping(value = "/param")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + ":" + map.getValue() + "\n");
        });

//        for (String key: param.keySet()) {
//            String value = param.get(key);
//            sb.append(key + ":" + value + "\n");
//            System.out.println("[key]:" + key + ", [value]:" + value);
//        }

        return sb.toString();
    }
}
