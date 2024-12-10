package springboot.example.supplyAi.interceptor;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import springboot.example.supplyAi.domain.User;
import springboot.example.supplyAi.service.UserService;
import springboot.example.supplyAi.utils.JWTUtil;
import springboot.example.supplyAi.utils.ThreadLocalUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            Claims claims = JWTUtil.parseToken(token);
            String customerId = claims.getSubject();
            User user = userService.findUserBycustomerId(customerId);
            ThreadLocalUtil.set(user);
            return true;
        } catch (JwtException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT");
            return false;
        }

    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        ThreadLocalUtil.remove();
    }
}
