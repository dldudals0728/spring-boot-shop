package kr.ac.gachon.shop.component;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

// @Component: 내가 만든 class를 bean으로 지정하고 싶을 때 사용!
@Component // 사용자 정의 빈 등록
@Qualifier("basicShotGun") // Bean의 구체적인 선별 방법
@Primary // 같은 Bean 객체 주입시 우선 순위 지정
public class ShotGun implements Weapon {
    private String model = "Basic ShotGun";

    @Override
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public void fire() {
        System.out.println(model+" fire!!");
    }
}
