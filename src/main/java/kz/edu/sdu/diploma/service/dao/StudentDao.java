package kz.edu.sdu.diploma.service.dao;

import kz.edu.sdu.diploma.dto.StudentDTO;

import java.util.List;

public interface StudentDao {

    StudentDTO findStudentByUsername(String username);

    List<StudentDTO> getAllStudentsWithEmail();
}
