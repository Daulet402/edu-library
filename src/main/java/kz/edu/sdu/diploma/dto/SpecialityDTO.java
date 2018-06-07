package kz.edu.sdu.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecialityDTO {
    private Long id;
    private String name;
    private FacultyDTO faculty;
}
