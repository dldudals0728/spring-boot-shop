package kr.ac.gachon.shop.controller;

import kr.ac.gachon.shop.dto.ItemDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/thymeleaf")
public class ThymeleafExController {
    @GetMapping(value = "/ex01")
    public String thymeleafExample01(Model m) {
        m.addAttribute("data", "타임리프 예제 입니다.");
        return "thymeleafEx/thymeleafEx01";
    }

    @GetMapping(value = "/ex02")
    public String thymeleafExample02(Model m) {
        ItemDto itemDto = new ItemDto();

        itemDto.setItemNm("테스트 상품1");
        itemDto.setItemDetail("상품 상세 설명");
        itemDto.setPrice(10000);
        itemDto.setRegTime(LocalDateTime.now());

        m.addAttribute("itemDto", itemDto);
        return "thymeleafEx/thymeleafEx02";
    }

    @GetMapping(value = "/ex03")
    public String thymeleafExample03(Model m) {
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (int i=1; i<=10; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemNm("테스트 상품" +i);
            itemDto.setItemDetail("상품 상세 설명" + i);
            itemDto.setPrice(10000 * i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtoList.add(itemDto);
        }

        m.addAttribute("itemDtoList", itemDtoList);
        return "thymeleafEx/thymeleafEx03";
    }

    @GetMapping(value = "/ex04")
    public String thymeleafExample04(Model m) {
        List<ItemDto> itemDtoList = new ArrayList<>();

        for (int i=1; i<=10; i++) {
            ItemDto itemDto = new ItemDto();
            itemDto.setItemNm("테스트 상품" +i);
            itemDto.setItemDetail("상품 상세 설명" + i);
            itemDto.setPrice(10000 * i);
            itemDto.setRegTime(LocalDateTime.now());

            itemDtoList.add(itemDto);
        }

        m.addAttribute("itemDtoList", itemDtoList);
        return "thymeleafEx/thymeleafEx04";
    }

    @GetMapping(value = "/ex05")
    public String thymeleafEx05() {
        return "thymeleafEx/thymeleafEx05";
    }

    @GetMapping(value = "/ex06")
    // PostMapping이 아님 => url?param1=test1&param2=test2 로 입력해서 들어갈 수 있음!
    // @RequestParam으로 파라미터를 전달해도 됨! => 그럼 RequestParam은 왜쓰는거지.
    // RequestController - msg 부분도 RequestParam 안써도 되데 ==> 질문하기!
//    public String thymeleafEx06(@RequestParam(value = "param1", required = false) String param1, @RequestParam(value="param2", required = false) String param2, Model m) {
    public String thymeleafEx06(String param1, String param2, Model m) {
        m.addAttribute("param1", param1);
        m.addAttribute("param2", param2);
        return "thymeleafEx/thymeleafEx06";
    }

    @GetMapping(value = "/ex07")
    public String thymeleafEx07() {
        return "thymeleafEx/thymeleafEx07";
    }
}
