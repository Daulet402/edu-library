package kz.edu.sdu.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FacultyDTO {
    private Long id;
    private String name;
    private List<SpecialityDTO> specialityList;
}
