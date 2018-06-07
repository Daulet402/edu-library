package kz.edu.sdu.diploma.configuration.interceptors;

import kz.edu.sdu.diploma.commons.Constants;
import kz.edu.sdu.diploma.service.AuthorizationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AuthorizationService authorizationService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();

        if (StringUtils.startsWith(requestURI, Constants.ADMIN_MAIN_URI) && !authorizationService.isAdministratorAuthenticated()) {
            response.sendRedirect(Constants.ADMIN_LOGIN_URI);
            return false;
        }

        if (StringUtils.startsWith(requestURI, Constants.STUDENT_MAIN_URI) && !authorizationService.isStudentAuthenticated()) {
            response.sendRedirect(Constants.STUDENT_LOGIN_URI);
            return false;
        }

        return true;
    }
}
