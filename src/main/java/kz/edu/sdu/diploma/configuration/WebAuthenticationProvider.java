package kz.edu.sdu.diploma.configuration;

import lombok.Data;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

@Data
public class WebAuthenticationProvider extends DaoAuthenticationProvider {
    private UserDetailsService adminUserDetailsService;
    private UserDetailsService studentUserDetailsService;
}
