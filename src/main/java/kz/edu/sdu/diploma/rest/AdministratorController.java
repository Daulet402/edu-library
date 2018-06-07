package kz.edu.sdu.diploma.rest;

import kz.edu.sdu.diploma.commons.Constants;
import kz.edu.sdu.diploma.dto.AdministratorDTO;
import kz.edu.sdu.diploma.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping(Constants.ADMIN_MAIN_URI)
public class AdministratorController {

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(value = {Constants.ADMIN_HOME_URI}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(Constants.ADMIN_HOME_PAGE);
        AdministratorDTO administratorDTO = authorizationService.getAdminFromAuthorization();
        if (Objects.nonNull(administratorDTO)) {
            modelAndView.addObject("degree", administratorDTO.getDegreeNameEn());
            modelAndView.addObject("position", administratorDTO.getPositionNameEn());
            modelAndView.addObject("phone", administratorDTO.getPhone());
            modelAndView.addObject("email", administratorDTO.getEmail());
            modelAndView.addObject("fullName", String.format(
                    "%s %s %s",
                    administratorDTO.getLastName(),
                    administratorDTO.getFirstName(),
                    administratorDTO.getMiddleName()));
        }
        return modelAndView;
    }

    @RequestMapping(value = {"/about"}, method = RequestMethod.GET)
    public String about() {
        return Constants.ADMIN_ABOUT_PAGE;
    }

    @RequestMapping(value = {"/notifications"}, method = RequestMethod.GET)
    public String notifications() {
        return Constants.ADMIN_NOTIFICATIONS_PAGE;
    }

    @RequestMapping(value = {"/library"}, method = RequestMethod.GET)
    public String library() {
        return Constants.ADMIN_LIBRARY_PAGE;
    }

    @RequestMapping(value = {"/world"}, method = RequestMethod.GET)
    public String world() {
        return Constants.ADMIN_WORLD_PAGE;
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String administratorLogout() throws Exception {
        authorizationService.adminLogout();
        return "redirect:".concat(Constants.ADMIN_LOGIN_URI);
    }
}
