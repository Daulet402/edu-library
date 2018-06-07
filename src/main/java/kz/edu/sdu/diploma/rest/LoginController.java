package kz.edu.sdu.diploma.rest;

import kz.edu.sdu.diploma.commons.Constants;
import kz.edu.sdu.diploma.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("")
public class LoginController {

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(value = {Constants.ADMIN_LOGIN_URI}, method = RequestMethod.GET)
    public String administratorLogin() throws Exception {
        return authorizationService.isAdministratorAuthenticated() ? Constants.ADMIN_HOME_PAGE : Constants.ADMIN_LOGIN_PAGE;
    }

    @RequestMapping(value = {"/login/administrator"}, method = RequestMethod.POST)
    public ModelAndView administratorLoginProcess(@RequestParam("username") String username, @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        if (authorizationService.isAdministratorAuthenticated() || authorizationService.isAdministratorSignInSuccessful(username, password)) {
            modelAndView.setViewName("redirect:".concat(Constants.ADMIN_MAIN_URI).concat(Constants.ADMIN_HOME_URI));
            return modelAndView;
        }
        modelAndView.setViewName(Constants.ADMIN_LOGIN_PAGE);
        modelAndView.addObject("error", "Bad credentials");
        return modelAndView;
    }

    @RequestMapping(value = {Constants.STUDENT_LOGIN_URI}, method = RequestMethod.GET)
    public String studentLogin() throws Exception {
        return authorizationService.isStudentAuthenticated() ? Constants.STUDENT_HOME_PAGE : Constants.STUDENT_LOGIN_PAGE;
    }

    @RequestMapping(value = {"/login/student"}, method = RequestMethod.POST)
    public ModelAndView studentLoginProcess(@RequestParam("username") String username, @RequestParam("password") String password) {
        ModelAndView modelAndView = new ModelAndView();
        if (authorizationService.isStudentAuthenticated() || authorizationService.isStudentSignInSuccessful(username, password)) {
            modelAndView.setViewName("redirect:".concat(Constants.STUDENT_MAIN_URI).concat(Constants.STUDENT_HOME_URI));
            return modelAndView;
        }
        modelAndView.setViewName(Constants.STUDENT_LOGIN_PAGE);
        modelAndView.addObject("error", "Bad credentials");
        return modelAndView;
    }
}
