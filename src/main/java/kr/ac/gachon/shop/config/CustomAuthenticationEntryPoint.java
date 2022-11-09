package kr.ac.gachon.shop.config;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    // login exception (로그인 실패) 발생 시 routing을 결정해 준다! 꼭 Overriding 처리를 해줘야 함.
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

        // 아래와 같이 로그인 실패 시 어떤 위치로 redirect 할 수도 있음.
        //LOGGER.info("가입되지 않은 사용자 접근");
        //response.sendRedirect("/signin");
    }

}