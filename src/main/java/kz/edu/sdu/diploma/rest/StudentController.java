package kz.edu.sdu.diploma.rest;

import kz.edu.sdu.diploma.commons.Constants;
import kz.edu.sdu.diploma.dto.SpecialityDTO;
import kz.edu.sdu.diploma.dto.StudentDTO;
import kz.edu.sdu.diploma.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping(Constants.STUDENT_MAIN_URI)
public class StudentController {

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        StudentDTO student = authorizationService.getStudentFromAuthorization();
        if (Objects.nonNull(student)) {
            SpecialityDTO speciality = student.getSpeciality();
            modelAndView.addObject("fullName", String.format("%s %s", student.getFirstName(), student.getLastName()));
            modelAndView.addObject("studentId", student.getStudentId());
            modelAndView.addObject("email", student.getUsername());
            modelAndView.addObject("advisor", student.getAdvisor());
            modelAndView.addObject("course", student.getCourse());
            modelAndView.addObject("grantType", student.getGrantType().getName());
            modelAndView.addObject("department", Objects.nonNull(speciality) ? speciality.getName() : "default");
            modelAndView.addObject("faculty", Objects.nonNull(speciality) && Objects.nonNull(speciality.getFaculty()) ? speciality.getFaculty().getName() : "default");
        }
        modelAndView.setViewName(Constants.STUDENT_HOME_PAGE);
        return modelAndView;
    }

    @RequestMapping("/about")
    public String about() {
        return Constants.STUDENT_ABOUT_PAGE;
    }

    @RequestMapping("/ask")
    public String ask() {
        return Constants.STUDENT_ASK_PAGE;
    }

    @RequestMapping("/library")
    public String library() {
        return Constants.STUDENT_LIBRARY_PAGE;
    }

    @RequestMapping("/world")
    public String world() {
        return Constants.STUDENT_WORLD_PAGE;
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String studentLogout() throws Exception {
        authorizationService.studentLogout();
        return "redirect:".concat(Constants.STUDENT_LOGIN_URI);
    }
}
