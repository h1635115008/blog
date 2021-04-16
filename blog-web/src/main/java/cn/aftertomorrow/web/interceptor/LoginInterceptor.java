package cn.aftertomorrow.web.interceptor;

import cn.aftertomorrow.common.request.dto.user.UserDTO;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 后台登录拦截器
 *
 * @author huangming
 * @date 2019/09/26
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String url = httpServletRequest.getRequestURI();
        if (url.indexOf("/login") >= 0) {
            return true;
        }
        HttpSession session = httpServletRequest.getSession();
        UserDTO user = (UserDTO) session.getAttribute("user");
        if (user != null) {
            return true;
        }
        httpServletRequest.getRequestDispatcher("/WEB-INF/page/login.jsp").forward(httpServletRequest, httpServletResponse);
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
