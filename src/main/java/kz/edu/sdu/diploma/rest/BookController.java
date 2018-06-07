package kz.edu.sdu.diploma.rest;

import kz.edu.sdu.diploma.dto.BookDTO;
import kz.edu.sdu.diploma.dto.BookRequestDTO;
import kz.edu.sdu.diploma.dto.StudentDTO;
import kz.edu.sdu.diploma.service.AuthorizationService;
import kz.edu.sdu.diploma.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorizationService authorizationService;

    @RequestMapping(value = "/sort/{sortStr}", method = RequestMethod.GET)
    public List<BookDTO> getBooksBySortStr(@PathVariable("sortStr") String sortStr) {
        return bookService.getBooksBySortStr(sortStr);
    }

    @RequestMapping(value = "/sort", method = RequestMethod.GET)
    public BookRequestDTO getBooksBySortStr2() {
        return new BookRequestDTO();
    }

    @RequestMapping(value = "/request/new", method = RequestMethod.POST)
    public void addBookRequest(@RequestBody BookRequestDTO bookRequestDTO) {
        if (Objects.nonNull(bookRequestDTO)) {
            StudentDTO student = authorizationService.getStudentFromAuthorization();
            if (Objects.nonNull(student))
                bookRequestDTO.setStudentId(student.getId());
        }
        bookService.addBookRequest(bookRequestDTO);
    }
}
