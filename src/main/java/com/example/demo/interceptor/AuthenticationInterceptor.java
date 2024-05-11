package com.example.demo.interceptor;


import com.example.demo.utils.TokenUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    private final TokenUtil tokenUtil;

    @Autowired
    public AuthenticationInterceptor(TokenUtil tokenUtil) {
        this.tokenUtil = tokenUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("拦截请求");

        // 从请求中获取 token
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            String jwtToken = token.substring(7);
            System.out.println("token:"+jwtToken);
            // Token 验证通过，允许请求继续执行
            if(tokenUtil.validateToken(jwtToken)){
                System.out.println("通过");
                return true;
            }

        }
        // 身份验证失败，返回未授权状态码
        System.out.println("请求不通过");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }



}