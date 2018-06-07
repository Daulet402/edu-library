package kz.edu.sdu.diploma.implementation.dao;

import kz.edu.sdu.diploma.dto.StudentDTO;
import kz.edu.sdu.diploma.dto.mapper.StudentDTOMapper;
import kz.edu.sdu.diploma.service.dao.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FIND_STUDENT_BY_USERNAME_QUERY = "select " +
            "s.*,sp.name as speciality_name, f.faculty_id, f.name as faculty_name " +
            "from public.students s, specialities sp,faculties f " +
            "where sp.speciality_id=s.speciality_id " +
            "and f.faculty_id=sp.faculty_id " +
            "and student_id = ?";


    private static final String GET_ALL_STUDENTS_WITH_EMAIL_QUERY = "select " +
            "s.*,sp.name as speciality_name, f.faculty_id, f.name as faculty_name " +
            "from public.students s, specialities sp,faculties f " +
            "where sp.speciality_id=s.speciality_id " +
            "and f.faculty_id=sp.faculty_id " +
            "and username != ''";

    @Override
    public StudentDTO findStudentByUsername(String username) {
        try {
            return (StudentDTO) jdbcTemplate.queryForObject(
                    FIND_STUDENT_BY_USERNAME_QUERY,
                    new Object[]{username},
                    new StudentDTOMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<StudentDTO> getAllStudentsWithEmail() {
        return jdbcTemplate.query(
                GET_ALL_STUDENTS_WITH_EMAIL_QUERY,
                new StudentDTOMapper()
        );
    }
}
