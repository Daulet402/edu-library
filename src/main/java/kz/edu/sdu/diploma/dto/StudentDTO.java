package kz.edu.sdu.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentDTO {
    private Long id;
    private Short course;
    private String advisor;
    private String lastName;
    private String username;
    private String firstName;
    private String studentId;
    private String middleName;
    private String passwordHash;
    private GrantType grantType;
    private SpecialityDTO speciality;
}
