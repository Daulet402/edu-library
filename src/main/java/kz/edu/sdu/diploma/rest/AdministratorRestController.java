package kz.edu.sdu.diploma.rest;

import kz.edu.sdu.diploma.commons.Constants;
import kz.edu.sdu.diploma.dto.BookRequestOutputDTO;
import kz.edu.sdu.diploma.dto.exception.AccessDeniedException;
import kz.edu.sdu.diploma.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Constants.ADMIN_MAIN_URI)
public class AdministratorRestController {

    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/books/request/all", method = RequestMethod.GET)
    public List<BookRequestOutputDTO> getAllBookRequest() throws AccessDeniedException {
        return bookService.getAllBookRequestOutputDTOs();
    }
}
