# spring boot shopping mall project
언급되지 않은 모든 출처는 [ㅎULL.KR](https://hull.kr/) 입니다. 감사합니다 교수님!
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
>
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
.img

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

