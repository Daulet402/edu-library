package kz.edu.sdu.diploma.service;

import kz.edu.sdu.diploma.dto.AdministratorDTO;
import kz.edu.sdu.diploma.dto.StudentDTO;
import kz.edu.sdu.diploma.dto.exception.EbaException;

public interface AuthorizationService {

    void login(String username, String password) throws EbaException;

    boolean isAdministratorSignInSuccessful(String username, String password);

    boolean isAdministratorAuthenticated();

    void adminLogout() throws EbaException;

    boolean isStudentAuthenticated();

    boolean isStudentSignInSuccessful(String username, String password);

    void studentLogout() throws EbaException;

    StudentDTO getStudentFromAuthorization();

    AdministratorDTO getAdminFromAuthorization();
}
