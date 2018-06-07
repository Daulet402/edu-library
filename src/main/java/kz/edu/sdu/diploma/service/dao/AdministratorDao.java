package kz.edu.sdu.diploma.service.dao;

import kz.edu.sdu.diploma.dto.AdministratorDTO;

public interface AdministratorDao {

    AdministratorDTO findAdministratorByUsername(String username);
}
