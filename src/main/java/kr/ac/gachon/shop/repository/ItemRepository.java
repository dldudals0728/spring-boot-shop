package kr.ac.gachon.shop.repository;

import kr.ac.gachon.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Repository: class(table)과 연결되어 CRUD를 작업. <다루는 class(table), class의 @Id(primary key)값의 데이터 타입>
public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {
    // 함수 명만 설계 규칙(findBy[field name])에 따라 설계하면, 구현은 spring container가 다 한다!
    List<Item> findByItemNm(String itemNm); // find+(Entity명)+By+(변수명)으로 메소드 생성. Entity명 제거할 수 있음
    // 원래 함수명 설계 규칙은 find[Entity]By[Field] 이다. Entity명은 생략 가능하다.
    // List<Item> findItemByItemNm(String itemNm);

    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    // 자료형에 int / Integer 둘 다 되네? ==> Integer로 하는게 굳!
    // Integer: 기본 자료형 int의 wrapper class. 이렇게 하면 Integer의 내부 함수를 이용할 수 있기 때문에? 좋다!
    // ex) null이란 값이 들어왔을 때, int로 받으면 null을 받을 수 없다. (예외 발생) Integer로 하면 null값을 가질 수 있고, 알아서 처리해준다!
    // 가능 하면 다 wrapper class로 넣어라.
    List<Item> findByPriceLessThan(Integer price);

    List<Item> findByPriceLessThanOrderByPriceDesc(Integer price);
    // test: 쿼리문 순서를 바꿔봤는데, 에러 반환!
    // List<Item> findByPriceOrderByPriceDescLessThan(Integer price);

    List<Item> findByPriceLessThanEqual(Integer price);

    // 이상 / 이하
    List<Item> findByPriceIsBetween(Integer priceMin, Integer priceMax);

    // JPQL => 테이블 이름X, Item 이라는 Class! : Back 의 DB 가 바뀌었을 때(mariadb -> oracle), 변경할 필요 X
    // Item i == Item 이라는 클래스의 instance i
    // select i == 모든 "객체" select!
    // *** 모든 검색은 table 과 column 을 참조하지 않고, class 와 instance 를 참조한다. ***
    // native query랑 다르다!!!
    // @Query() 어노테이션에 들어가는 변수는 :[variable name]으로, 앞에 콜론(:)을 붙여서 전달한다.
    // 변수 명은 @Param("[variable name]")으로 정의된다.
    @Query("select i from Item i  where i.itemDetail like " +
    "%:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail") String itemDetail);

    // mariadb Query(nativeQuery = true) 속성으로 가능
    @Query(value = "select * from item i where i.item_detail like" +
    "%:itemDetail% order by i.price desc", nativeQuery = true)
    List<Item> findByItemDetailByNative(@Param("itemDetail") String itemDetail);
}