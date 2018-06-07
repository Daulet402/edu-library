package kz.edu.sdu.diploma.implementation;

import kz.edu.sdu.diploma.dto.AdministratorDTO;
import kz.edu.sdu.diploma.dto.Role;
import kz.edu.sdu.diploma.dto.UserDetailsDTO;
import kz.edu.sdu.diploma.service.dao.AdministratorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service("adminUserDetailsService")
public class AdminUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AdministratorDao administratorDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AdministratorDTO administratorDTO = administratorDao.findAdministratorByUsername(username);
        if (Objects.isNull(administratorDTO))
            throw new UsernameNotFoundException("Not found");

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setAdmin(administratorDTO);
        userDetailsDTO.setUsername(administratorDTO.getEmail());
        userDetailsDTO.setPassword(administratorDTO.getPasswordHash());
        userDetailsDTO.setAuthorities(Arrays.asList((GrantedAuthority) () -> Role.ROLE_ADMIN.name()));
        return userDetailsDTO;
    }
}
