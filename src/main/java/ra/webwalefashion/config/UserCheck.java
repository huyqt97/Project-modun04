package ra.webwalefashion.config;

import org.springframework.web.servlet.HandlerInterceptor;
import ra.webwalefashion.DTO.response.UserLoginRes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserCheck implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        UserLoginRes user = (UserLoginRes) session.getAttribute("UserLoginSession");
        if (user != null) {
            return true;
        }
        return false;
    }
}
