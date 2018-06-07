package kz.edu.sdu.diploma.implementation.dao;

import kz.edu.sdu.diploma.dto.AdministratorDTO;
import kz.edu.sdu.diploma.dto.mapper.AdministratorDTOMapper;
import kz.edu.sdu.diploma.service.dao.AdministratorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class AdministratorDaoImpl implements AdministratorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FIND_ADMINISTRATOR_BY_USERNAME_QUERY = "select " +
            "a.*, d.name_ru as degree_name_ru, " +
            "d.name_en as degree_name_en, p.name_ru as position_name_ru, p.name_en as position_name_en " +
            "from public.administrators a " +
            "left join degrees d using(degree_id) " +
            "left join positions p using (position_id) " +
            "where email = ?";


    @Override
    public AdministratorDTO findAdministratorByUsername(String username) {
        try {
            return (AdministratorDTO) jdbcTemplate.queryForObject(
                    FIND_ADMINISTRATOR_BY_USERNAME_QUERY,
                    new Object[]{username},
                    new AdministratorDTOMapper());
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
