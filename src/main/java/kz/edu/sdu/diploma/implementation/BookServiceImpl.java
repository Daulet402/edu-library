package kz.edu.sdu.diploma.implementation;

import kz.edu.sdu.diploma.dto.BookDTO;
import kz.edu.sdu.diploma.dto.BookRequestDTO;
import kz.edu.sdu.diploma.dto.BookRequestNotificationDTO;
import kz.edu.sdu.diploma.dto.BookRequestOutputDTO;
import kz.edu.sdu.diploma.service.BookService;
import kz.edu.sdu.diploma.service.dao.BookDao;
import kz.edu.sdu.diploma.service.dao.BookRequestDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Autowired
    private BookRequestDao bookRequestDao;

    @Override
    public List<BookDTO> getBooksBySortStr(String sortStr) {
        return bookDao.findBooksBySortStrOrPublicationYear(sortStr);
    }

    @Override
    @Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
    public void addBookRequest(BookRequestDTO bookRequestDTO) {
        Long bookRequestId = bookRequestDao.getNextValueOfBookRequestIdSequence();
        bookRequestDTO.setId(bookRequestId);

        BookRequestNotificationDTO notificationDTO = new BookRequestNotificationDTO();
        notificationDTO.setIsRead(Boolean.FALSE);
        notificationDTO.setBookRequestId(bookRequestId);

        bookRequestDao.addBookRequest(bookRequestDTO);
        bookRequestDao.addBookRequestNotification(notificationDTO);
    }

    @Override
    public List<BookRequestOutputDTO> getAllBookRequestOutputDTOs() {
        return bookRequestDao.findFullBookRequests();
    }
}
