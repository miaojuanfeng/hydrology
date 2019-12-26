package gov.gz.hydrology.interceptor;

import gov.gz.hydrology.constant.CommonConst;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
//        System.out.println("请求路径："+request.getRequestURI());
//        HttpSession session = request.getSession();
//        if( session.getAttribute(CommonConst.SESSION_KEY_USER) == null ){
//            response.sendRedirect("/XAJ/cms/user/login");
//            return false;
//        }
//        String[] segUrl = request.getRequestURI().split("/");
//        String urlClass = segUrl[3];
//        if(
//            "station".equals(urlClass) ||
//            "forecast".equals(urlClass) ||
//            "user".equals(urlClass) ||
//            "step".equals(urlClass)
//        ){
//            session.setAttribute(CommonConst.SESSION_URL_CLASS, urlClass);
//        }

        return true;
    }
}
