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
