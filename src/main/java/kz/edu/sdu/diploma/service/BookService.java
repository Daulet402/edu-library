package kz.edu.sdu.diploma.service;

import kz.edu.sdu.diploma.dto.BookDTO;
import kz.edu.sdu.diploma.dto.BookRequestDTO;
import kz.edu.sdu.diploma.dto.BookRequestOutputDTO;

import java.util.List;

public interface BookService {

    List<BookDTO> getBooksBySortStr(String sortStr);

    void addBookRequest(BookRequestDTO bookRequestDTO);

    List<BookRequestOutputDTO> getAllBookRequestOutputDTOs();
}
