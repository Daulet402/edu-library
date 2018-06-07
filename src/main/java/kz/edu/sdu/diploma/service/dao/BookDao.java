package kz.edu.sdu.diploma.service.dao;

import kz.edu.sdu.diploma.dto.BookDTO;

import java.util.List;

public interface BookDao {

    List<BookDTO> findBooksBySortStrOrPublicationYear(String sortStr);
}
