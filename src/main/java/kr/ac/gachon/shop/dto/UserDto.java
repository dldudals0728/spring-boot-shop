package kr.ac.gachon.shop.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// lombok의 anotation을 이용하여 모든 필드의 getter / setter / toString 모두 자동으로 생성 가능! (ln 17 ~ 39)
@Getter
@Setter
@ToString
public class UserDto {
//    private으로, getter / setter 필요!
    private String name;
    private String phone;

    /*
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
    */
}
