package kz.edu.sdu.diploma.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService adminUserDetailsService;

    @Autowired
    private UserDetailsService studentUserDetailsService;

    @Autowired
    private AuthenticationProvider authenticationProvider;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .anyRequest().permitAll();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        WebAuthenticationProvider authenticationProvider = new WebAuthenticationProvider();
        authenticationProvider.setUserDetailsService(studentUserDetailsService);
        authenticationProvider.setAdminUserDetailsService(adminUserDetailsService);
        authenticationProvider.setStudentUserDetailsService(studentUserDetailsService);
        return authenticationProvider;
    }
}
