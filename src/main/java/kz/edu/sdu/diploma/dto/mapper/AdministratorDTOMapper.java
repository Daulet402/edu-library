package kz.edu.sdu.diploma.dto.mapper;

import kz.edu.sdu.diploma.dto.AdministratorDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorDTOMapper implements RowMapper {

    @Override
    public AdministratorDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        AdministratorDTO administratorDTO = new AdministratorDTO();
        administratorDTO.setId(rs.getLong("id"));
        administratorDTO.setEmail(rs.getString("email"));
        administratorDTO.setPhone(rs.getString("phone"));
        administratorDTO.setLastName(rs.getString("last_name"));
        administratorDTO.setFirstName(rs.getString("first_name"));
        administratorDTO.setMiddleName(rs.getString("middle_name"));
        administratorDTO.setPasswordHash(rs.getString("password_hash"));
        administratorDTO.setDegreeNameRu(rs.getString("degree_name_ru"));
        administratorDTO.setDegreeNameEn(rs.getString("degree_name_en"));
        administratorDTO.setPositionNameRu(rs.getString("position_name_ru"));
        administratorDTO.setPositionNameEn(rs.getString("position_name_en"));
        return administratorDTO;
    }
}
