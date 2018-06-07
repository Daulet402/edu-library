package kz.edu.sdu.diploma.implementation;

import kz.edu.sdu.diploma.dto.Role;
import kz.edu.sdu.diploma.dto.StudentDTO;
import kz.edu.sdu.diploma.dto.UserDetailsDTO;
import kz.edu.sdu.diploma.service.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

@Service("studentUserDetailsService")
public class StudentUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StudentDTO studentDTO = studentDao.findStudentByUsername(username);
        if (Objects.isNull(studentDTO))
            throw new UsernameNotFoundException("Not found");

        UserDetailsDTO userDetailsDTO = new UserDetailsDTO();
        userDetailsDTO.setStudent(studentDTO);
        userDetailsDTO.setUsername(studentDTO.getUsername());
        userDetailsDTO.setPassword(studentDTO.getPasswordHash());
        userDetailsDTO.setAuthorities(Arrays.asList((GrantedAuthority) () -> Role.ROLE_STUDENT.name()));
        return userDetailsDTO;
    }
}
