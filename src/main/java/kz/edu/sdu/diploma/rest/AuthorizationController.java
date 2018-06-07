package kz.edu.sdu.diploma.rest;

import kz.edu.sdu.diploma.dto.exception.EbaException;
import kz.edu.sdu.diploma.service.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorizationController {

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestParam("username") String username, @RequestParam String password) throws EbaException { // TODO: 04/30/2018 maybe send password hash ?
        authorizationService.login(username, password);
    }
}
