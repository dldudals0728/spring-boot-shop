# spring boot shopping mall project
언급되지 않은 모든 출처는 [ㅎULL.KR](https://hull.kr/) 입니다. 감사합니다 교수님!
## Directory
```
├─main
│  ├─java
│  │  └─kr
│  │      └─ac
│  │          └─gachon
│  │                 └─shop
│  │                      ├─ShopApplication.java
│  │                      │
│  │                      ├─controller
│  │                      │      ├─DefaultGetController.java
│  │                      │      ├─DefaultPostController.java
│  │                      │      └─DefaultReqController.java
│  │                      │
│  │                      └─dto
│  │                          └─UserDto.java
│  │
│  └─resources
│      ├─application.properties
│      │
│      ├─static
│      │    └─post.html
│      │
│      └─templates
│          ├─get
│          │   └─index.html
│          │
│          └─post
│              └─index.html
│
└─test
    └─java
        └─kr
            └─ac
                └─gachon
                    └─shop
                         └─ShopApplicationTests.java
```
## 개발 환경 세팅
### Software
<hr>

code editor: IntelliJ<br>
server: wampserver64 3.2.3<br>
> wampserver64 3.2.3 setting<br>
> 
> mariadb: 10.3.23<br>
> MySQL: 8.0.21<br>
> Browser: Chrome

추가적으로 mariadb 한글 사용을 위한 utf8인코딩을 적용해준다.<br>
IN Mariadb - my.ini
> lc-messages=en_US <br>
> 
> [mysqldump] <br>
> quick <br>
> ax_allowed_packet = 16M <br>
> default-character-set = utf8 <br>
> 
> [mysql] <br>
> no-auto-rehash <br>
> ; Remove the next comment character if you are not familiar with SQL <br>
> ;safe-updates <br>
> default-character-set=utf8 <br>
> 
> [myisamchk] <br>
> key_buffer_size = 20M <br>
> sort_buffer_size = 20M <br>
> read_buffer = 2M <br>
> write_buffer = 2M <br>
>
> [mysqlhotcopy] <br>
> interactive-timeout <br>
>
> [mysqld] <br>
> port=3307 <br>
> init_connect="SET collation_connection = utf8_general_ci" <br>
> init_connect="SET NAMES utf8" <br>
> character-set-server = utf8 <br>
> collation-server = utf8_general_ci <br>
>
> [client] <br>
> default-character-set = utf8 <br>

### spring initializr
<hr>

spring initializr를 통해 spring boot를 사용하기 위한 셋업을 진행한다.<br>
![spring initializr-dark](https://user-images.githubusercontent.com/70733949/190141562-a4661503-3030-4685-9303-174e4c2b65ec.png)

### software issue
<hr>

1. maven error
spring initializr를 이용해 프로젝트를 생성했을 때, pom.xml 파일에서 오류가 발생할 수 있다.
```xml
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <version>2.7.3</version>
    ...
```
그럴 땐 위와 같이 maven의 version을 명시해 주면 된다.

### Intellij 단축키
<hr>

모듈 쉽게 import하기: <kbd>alt</kbd> + <kbd>enter</kbd><br>
(import가 안되어 글자가 빨갛게 된 모듈에 커서를 두고 사용!)<br>
<br>

자동 들여쓰기: 전체 드래그 후 <kbd>ctrl</kbd> + <kbd>alt</kbd> + <kbd>l</kbd><br>
<br>

들여쓰기 <kbd><--></kbd> space 변경: <kbd>shift</kbd> * 2 후에 tab sapce 검색 후 To Tabs(Spaces)선택<br>
<br>

한글 주석 오류: File > Settings > Editor > File Encodings > Transparent native-to-ascii conversion 체크<br>
<br>

## Study!
<hr>

commit(주차)별 내용

commit1: 3주차
```
@Controller
@RequestMapping("/req")
@RequestMapping(value="/txt", method = RequestMethod.GET)
@ResponseBody
RequestMethod.GET
@PathVariable
Model
modelObj.addAttribute(key, value)
@RequestParam
@GetMapping("/html")
@PostMapping("/html")
    > GetMapping, PostMapping은 이미 방법이 정해져 있기 때문에 매개변수가 하나이다.
    (@RequestMapping은 value, method로 두개이다.)
```
* static 하위의 html파일은 "직접적으로 접근"이 가능하다.(static/index.html <kbd>-></kbd>[port]/index.html)
* Dto의 필드명과 html에서 submit하는 태그의 name속성의 값이 서로 같아야 한다 ⚠
<hr>

5주차
### application-test.properties

데이터베이스를 사용할 때, 테스트 용으로 사용할 수 있다.<br>
repository에서 className 위에서 <kbd>alt</kbd> + <kbd>Enter</kbd> 후 Create Test를 선택하면 된다.
<br>
> 테스트는 JUnit5로!!<br>
> test class name은 알아서 정해준다.

ItemRepositoryTest에서 사용한 application-test.properties이다.
```properties
spring.datasource.driver-class-name=org.mariadb.jdbc.Driver
spring.datasource.url=jdbc:mariadb://localhost:3307/test?characterEncoding=UTF-8&serverTimezone=UTC
#테스트 계정 새로 생성해서 부여
spring.datasource.username=test
spring.datasource.password=123456

# DB 모드
spring.jpa.hibernate.ddl-auto=create

#MariaDB 방언(dialect) 설정
spring.jpa.database-platform=org.hibernate.dialect.MariaDB103Dialect
```
RepositoryTest에서
```JAVA
@TestPropertySource(locations="classpath:application-test.properties")
```
해당 어노테이션을 추가해 test properties를 우선 적용시킬 수 있다.

<hr>

6주차
### repository: Wrapper Class
repository interface에서 매개변수로 받을 값의 자료형을 입력할 때 wrapper class를 입력하는게 좋다고 한다.

```java
List<Item> findByPriceLessThan(int price);
List<Item> findByPriceLessThan(Integer price);
```
위 아래 둘 다 정상적으로 코드가 동작하지만, Wrapper Class를 사용할 경우, null값이 들어왔을 때와 같이 예외적인 상황에 내부적으로 부드러운 조취가 가능하다.<br>
그리고 현업에서 대부분 이렇게 쓰인다고 하니 그냥 wrapper class 쓰자.


### querydsl
#### setup
pom.xml에 해당 dependency와 plugin을 추가한다.
> dependency는 dependencies 안에, plugin은 plugins 안에 넣으면 된다!


```xml
    <!--querydsl 의존성 추가-->
   <dependency>
      <groupId>com.querydsl</groupId>
      <artifactId>querydsl-jpa</artifactId>
      <version>5.0.0</version>
   </dependency>
   <dependency>
      <groupId>com.querydsl</groupId>
      <artifactId>querydsl-apt</artifactId>
      <version>5.0.0</version>
   </dependency>
   <!--querydsl 의존성 추가-->
```
```xml
    <!--querydsl 플러그인 설정-->
   <plugin>
      <groupId>com.mysema.maven</groupId>
      <artifactId>apt-maven-plugin</artifactId>
      <version>1.1.3</version>
      <executions>
         <execution>
            <goals>
               <goal>process</goal>
            </goals>
            <configuration>
               <outputDirectory>target/generated-sources/java</outputDirectory>
               <processor>com.querydsl.apt.jpa.JPAAnnotationProcessor</processor>
            </configuration>
         </execution>
      </executions>
   </plugin>
   <!--querydsl 플러그인 설정-->
```
그러면 빨간 글씨가 엄청 많이 보일텐데, 당황하지 말고 우측 상단에 Maven 마크로 새로고침 버튼이 있는데 그걸 누르면 된다.

그 후에 Intellij 옆에 Maven을 누르면 Maven 창이 나오는데,
>[prj name] - Lifecycle - compile

을 선택하여 컴파일 해준다. 그러면 target 폴더가 생기고,
> target - generated-sources - java - prj.entity - QItem

이 생긴다! 파일이 생긴걸 확인했다면
> Flie > Project Structure(ctrl+alt+shift+s) > Modules > target > generated-sources 선택

위와 같이 설정해준다. 그러면 준비 끝 !

![project-setting-1](https://user-images.githubusercontent.com/70733949/194039866-103cbf40-5d0f-4cd2-9e69-78121feed9e5.png)

이거를

![project-setting-2](https://user-images.githubusercontent.com/70733949/194040022-2660a950-30bc-4977-b6df-d59ed8e3b2c0.png)
이렇게 바꿔주면 된다.

### BooleanBuilder & Pageable * HARD *
게시판을 포면 페이지별로 몇 개의 게시물들을 보여주는데, 이걸 쉽게 구현하는 것이 Spring 에서 제공하는 Pageable 이다.

> BooleanBuilder에 대한 주석을 보려면 6주차 ItemRepositoryTest.java 확인!

```java
Pageable pageable = PageRequest.of(0, 5);
```
위와 같은 경우는 페이지 0번부터 5개의 record를 가져온다!
> indexing 처럼 0부터 시작한다. 0부터 !!

## thymeleaf(Front-end)
<hr>

[thymeleaf 공식 홈페이지](http://www.thymeleaf.org)

```html
<html xmlns:th="http://www.thymeleaf.org">
```
html 태그에 다음과 같이 입력하여 thymeleaf 문법을 사용할 수 있다.

### 기본개념
> https://hull.kr/springboot/66?sca=%EC%9D%B4%EB%A1%A0 <hr>
> View에 값을 전달할 때는 Model에 담아서 전달하게 된다!!

### thymeleaf 사용 문법
``` thymeleafexpressions
* th:text="${data}"
단순 값을 모델에 전달했을 때

* th:text="${dto.dtoAttr}"
모델에 Dto를 전달하고, 해당 Dto에 접근할 때

* th:each="dto, status=${dtoList}"
List<Dto> 형식을 모델로 받았을 때.
dtoList의 각각이 dto로 들어가고, status를 이용하여 index, even 등에 접근할 수 있다.
각각의 dto에 접근하려면 dto.dtoAttr로 접근할 수 있다.

* th:if="${조건}"
* th:unless="${조건}"
thymeleaf의 조건문. thymeleaf에서 else = unless이고 unless에 들어가는 조건은 if에 들어가는 조건과 같아야한다.

* th:switch="${조건}"
*     th:case="true(false)"
switch-case 문은 case문이 안으로 들어가도록 구현한다.

* th:href="@{url 또는 controller mapping의 value}"
thymeleaf의 링크는 텍스트와 달리 @{}로 접근한다.
프로젝트 내부의 View를 보여주려면 View의 경로가 아닌 Controller의 Mapping value로 지정해준다!

* th:href="@{/thymeleaf/ex06(param1 = 'param data1', param2 = 'param data2')
위와 같이 파라미터를 전달할 수도 있다. 컨트롤러 함수에서 해당 파라미터를 매개변수로 받아 사용한다.

* th:replace="fragments/header::content이름"
layout을 이용하여 View를 만든 후 다른 View에서 사용할 수 있다.
replace의 값은 resource/templates 내의 파일 경로 및 layout:fragment="content이름"의 값을 받는다.
```

### spring-boot-starter-security

```xml
<!--security 의존성 추가-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
<!--security 의존성 추가-->
```
pom.xml에 의존성을 추가하는 것 만으로도 권한 설정을 끝낼 수 있다.<br>
원래는 세션이나 쿠키를 이용하여 권한을 제어하지만, spring-boot-starter-security를 이용하면 모든 페이지에 접근 제한을 걸 수 있다.

먼저 모든 권한을 해제해 주고, 필요한 곳에 권한 접근을 허용하면 된다.

기본 아이디는 user, 비밀번호는
<img>
다음과 같이 spring 터미널에서 주어진다.<br>
로그아웃을 진행하고 싶다면
```
http://localhost:8080/logout
```
해당 url로 접속하면 로그아웃 페이지가 나온다.

### Test 파일 새성 방법!
Repository, 또는 Service를 테스트 하려면 ClassNameTest라는 파일이 test 폴더 내에 있어야 한다.<br>
이를 쉽게 하려면 해당 Repository, 또는 Serveice의 className에 커서를 올린 후<br>
<kbd>alt</kbd> + <kbd>Enter</kbd>를 하면 'Create Test'가 있다. Junit5로 자동으로 되어있기 때문에 바로 생성하여 Test를 진행할 수 있다.
> Test 파일은<br>
> @SpringBootTest<br>
> @TestPropertySource(locations="classpath:application-test.properties")
> annotation을 이용해 설정해주고 test를 진행한다!


⊙ JUnit의 assert 메소드
> 같지 않다면 exception

assertEquals(A, B) : 객체 A와 B가 같은 값을 가지는지 확인한다.
assertEquals(A, B, C) : 객체 A와 B가 같은 값을 가지는지 확인한다. C는 오차범위.
assertArrayEquals(A, B) : 배열 A와 B가 일치하는지 확인한다.
assertSame(A, B) : 객체 A와 B가 같은 객체인지 확인한다.
assertTrue(A) : 조건 A가 참인지 확인한다.
assertNull(A) : 객체A가 Null인지 확인한다.
assertNotNull(A) : 객체 A가 Null이 아닌지 확인한다.

# Tips
<hr>

### WEB을 header, footer, nav등으로 나눌 때의 장점
검색엔진은 물론, 재사용이 가능하다.<br>
[헐](hull.kr)을 보면 header에는 로고, 로그인 메뉴, 각각의 콘텐츠 메뉴를 놓고, footer에는 Copyright에 대해 작성해 놓았다.<br>
모든 페이지에 같은 header와 footer가 사용되니까, 만들어놓고 재사용하면 개꿀이다.
> <i>그럼 react같은 single page application은 사용 효율이 조금 떨어지나...?</i>
<hr>

### bootstrap
[bootstrap](https://getbootstrap.com/)<br>
bootstrap에 대해 몰랐다. 근데 good js, css를 제공하더라. bootstrap CDN을 이용해서 빠른 개발을 진행할 수 있을 것 같다!


### 배포 파일 ~~~.min.file: .min의 의미
```html
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
```
bootstrap의 css를 받는데 bootstrap.min.css 파일을 불러온다.<br>
여기서 .min은, "css 파일에서, 엔터(줄바꿈)을 모두 제거한 css파일"이라는 의미이다.<br>
실제로 해당 링크를 웹페이지에서 접속하면 줄바꿈이 모두 제거된 css파일을 볼 수 있다. .min을 제거하고 확인하면 보기 좋은 css파일을 볼 수 있다.
> .min이 없는 파일은 보고 공부하라고 있는 것!

### spring security token
spring security를 사용할 때 꼭 추가해줘야 하는 input이 있다.
```html
<form>
    ...
    <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}">
</form>
```
input type=hidden에  token 값을 넣어주는 것이다. 중요하다!!! 꼭 넣어줘야 한다!!

## 긴급 추가
상단의 spring security token handling 코드를 넣으니 아래와 같은 오류가 생겨나면서 html 소스가 뿌려지지 않았다.
```
spring net::ERR_INCOMPLETE_CHUNKED_ENCODING 200
```
상단의 코드를 주석처리하니까 화면은 나오는데, 회원가입 시 exception 403 error가 발생했다. 주석처리를 안하면 view가 안뜨고,<br>
주석처리를 하니까 view는 나오는데 회원가입이 안되는 문제가 있다.

이런 미친 다른곳에 있던 똑같은 코드를 그대로 복사해서 문제가 되는 부분에 붙여넣기 했더니 된다 ㅋㅋ 진짜 미치겠네


### session & cookie
session: 한번 로그인 되면 해당 페이지에서 다른 곳으로 이동할 때 로그인 정보를 가지고 다니는 것
cookie: 페이지를 벗어나도 로그인 정보를 가지고 있는 것

# ERROR report
<hr>

### (1/2) Item Entity, Repository, Test 실습 Querydsl

> package org.junit.jupiter.api does not exist
>
pom.xml을 수정하고 Maven에서 컴파일을 진행했더니 잘 되던 JUnit5 테스트가 먹통이 되었다.<br><br>
구글링을 통해 얻은 방법은 pom.xml에 org.junit.jupiter dependency를 추가하면 된다는 정보를 얻어서 해결했다.<br>
그런데 교수님이 일종의 버그이고, 정상 실행이 되었다면 해당 의존성을 다시 지워도 정상 실행 된다고 하셨는데
진짜로 됐다...<br><br>
원인은 잘 모르겠다 ^^ 원인 찾아내겠다고 붙잡고 있으면 하루종일 걸린다고 마무리 지으라 하셨다 ㅎ
