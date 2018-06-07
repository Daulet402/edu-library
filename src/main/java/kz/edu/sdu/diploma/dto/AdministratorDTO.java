package kz.edu.sdu.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdministratorDTO {
    private Long id;
    private String email;
    private String phone;
    private String lastName;
    private String firstName;
    private String middleName;
    private String degreeNameRu;
    private String degreeNameEn;
    private String positionNameRu;
    private String positionNameEn;
    private String passwordHash;
}
