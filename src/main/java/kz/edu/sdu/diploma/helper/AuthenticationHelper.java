package kz.edu.sdu.diploma.helper;

import kz.edu.sdu.diploma.dto.AdministratorDTO;
import kz.edu.sdu.diploma.dto.StudentDTO;
import kz.edu.sdu.diploma.dto.UserDetailsDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthenticationHelper {

    public boolean isUserAuthenticatedAndHasRole(String role) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(authentication)
                && !StringUtils.equals("anonymousUser", String.valueOf(authentication.getPrincipal()))
                && hasUserRole((UserDetails) authentication.getPrincipal(), role);
    }

    public boolean hasUserRole(UserDetails userDetails, String role) {
        if (StringUtils.isEmpty(role))
            throw new IllegalArgumentException("Role is mandatory");

        if (userDetails == null || CollectionUtils.isEmpty(userDetails.getAuthorities()))
            return false;

        return userDetails
                .getAuthorities()
                .stream()
                .anyMatch(authority -> StringUtils.equals(authority.getAuthority(), role));
    }

    public StudentDTO getStudentFromAuthorization() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(authentication) && Objects.nonNull(authentication.getPrincipal())
                ? ((UserDetailsDTO) authentication.getPrincipal()).getStudent() : null;
    }

    public AdministratorDTO getAdminFromAuthorization() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return Objects.nonNull(authentication) && Objects.nonNull(authentication.getPrincipal())
                ? ((UserDetailsDTO) authentication.getPrincipal()).getAdmin() : null;
    }
}
