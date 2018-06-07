package kz.edu.sdu.diploma.dto.mapper;

import kz.edu.sdu.diploma.dto.FacultyDTO;
import kz.edu.sdu.diploma.dto.GrantType;
import kz.edu.sdu.diploma.dto.SpecialityDTO;
import kz.edu.sdu.diploma.dto.StudentDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;

public class StudentDTOMapper implements RowMapper {

    @Override
    public StudentDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        SpecialityDTO specialityDTO = new SpecialityDTO(
                rs.getLong("speciality_id"),
                rs.getString("speciality_name"),
                new FacultyDTO(rs.getLong("faculty_id"), rs.getString("faculty_name"), Collections.emptyList()));

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(rs.getLong("id"));
        studentDTO.setSpeciality(specialityDTO);
        studentDTO.setCourse(rs.getShort("course"));
        studentDTO.setAdvisor(rs.getString("advisor"));
        studentDTO.setUsername(rs.getString("username"));
        studentDTO.setLastName(rs.getString("last_name"));
        studentDTO.setFirstName(rs.getString("first_name"));
        studentDTO.setStudentId(rs.getString("student_id"));
        studentDTO.setMiddleName(rs.getString("middle_name"));
        studentDTO.setPasswordHash(rs.getString("password_hash"));
        studentDTO.setGrantType(GrantType.valueOf(rs.getString("grant_type")));
        return studentDTO;
    }
}
