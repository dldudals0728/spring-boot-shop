package kr.ac.gachon.shop.repository;

import kr.ac.gachon.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Repository: class(table)과 연결되어 CRUD를 작업. <다루는 class(table), class의 @Id(primary key)값의 데이터 타입>
public interface ItemRepository extends JpaRepository<Item, Long> {
    // 함수 명만 설계 규칙(findBy[field name])에 따라 설계하면, 구현은 spring container가 다 한다!
    List<Item> findByItemNm(String itemNm); // find+(Entity명)+By+(변수명)으로 메소드 생성. Entity명 제거할 수 있음
    // 원래 함수명 설계 규칙은 find[Entity]By[Field] 이다. Entity명은 생략 가능하다.
    // List<Item> findItemByItemNm(String itemNm);

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // 자료형에 int / Integer 둘 다 되네?
    List<Item> findByPriceLessThan(Integer price);

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
}