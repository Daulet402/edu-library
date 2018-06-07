package kz.edu.sdu.diploma.implementation;

import kz.edu.sdu.diploma.configuration.WebAuthenticationProvider;
import kz.edu.sdu.diploma.dto.AdministratorDTO;
import kz.edu.sdu.diploma.dto.Role;
import kz.edu.sdu.diploma.dto.StudentDTO;
import kz.edu.sdu.diploma.dto.exception.EbaException;
import kz.edu.sdu.diploma.dto.exception.EntityNotFoundException;
import kz.edu.sdu.diploma.helper.AuthenticationHelper;
import kz.edu.sdu.diploma.service.AuthorizationService;
import kz.edu.sdu.diploma.service.dao.StudentDao;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationHelper authenticationHelper;

    @Override
    public void login(String username, String password) throws EbaException {
        StudentDTO studentDTO = studentDao.findStudentByUsername(username);

        if (Objects.isNull(studentDTO) || !StringUtils.equals(studentDTO.getPasswordHash(), password))
            throw EntityNotFoundException.ofStudentNotFound("Student not found");

    }

    @Override
    public boolean isAdministratorSignInSuccessful(String username, String password) {
        WebAuthenticationProvider webAuthenticationProvider = getWebAuthenticationProviderInternal();
        if (webAuthenticationProvider == null) {
            System.err.println("No right provider found");
            return false;
        }
        webAuthenticationProvider.setUserDetailsService(webAuthenticationProvider.getAdminUserDetailsService());
        return signInInternal(username, password, Role.ROLE_ADMIN.name());
    }

    @Override
    public boolean isAdministratorAuthenticated() {
        return authenticationHelper.isUserAuthenticatedAndHasRole(Role.ROLE_ADMIN.name());
    }

    @Override
    public void adminLogout() throws EbaException {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Override
    public boolean isStudentAuthenticated() {
        return authenticationHelper.isUserAuthenticatedAndHasRole(Role.ROLE_STUDENT.name());
    }

    @Override
    public boolean isStudentSignInSuccessful(String username, String password) {
        WebAuthenticationProvider webAuthenticationProvider = getWebAuthenticationProviderInternal();
        if (webAuthenticationProvider == null) {
            System.err.println("No right provider found");
            return false;
        }
        webAuthenticationProvider.setUserDetailsService(webAuthenticationProvider.getStudentUserDetailsService());
        return signInInternal(username, password, Role.ROLE_STUDENT.name());
    }

    @Override
    public void studentLogout() throws EbaException {
        SecurityContextHolder.getContext().setAuthentication(null);
    }

    @Override
    public StudentDTO getStudentFromAuthorization() {
        return authenticationHelper.getStudentFromAuthorization();
    }

    @Override
    public AdministratorDTO getAdminFromAuthorization() {
        return authenticationHelper.getAdminFromAuthorization();
    }

    private boolean signInInternal(String username, String password, String role) {
        boolean signInResult = false;
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password); // TODO: 05/12/2018 hash of password
        try {
            Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            if (authenticationHelper.hasUserRole(userDetails, role)) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                signInResult = true;
            }
        } catch (BadCredentialsException e) {
            e.printStackTrace();
        }
        return signInResult;
    }

    private WebAuthenticationProvider getWebAuthenticationProviderInternal() {
        List<AuthenticationProvider> providers = ((ProviderManager) authenticationManager).getProviders();
        if (CollectionUtils.isNotEmpty(providers))
            if (providers.get(0) instanceof WebAuthenticationProvider)
                return (WebAuthenticationProvider) providers.get(0);

        return null;
    }
}
