package kz.edu.sdu.diploma.service.dao;

import kz.edu.sdu.diploma.dto.BookRequestDTO;
import kz.edu.sdu.diploma.dto.BookRequestNotificationDTO;
import kz.edu.sdu.diploma.dto.BookRequestOutputDTO;

import java.util.List;

public interface BookRequestDao {

    void addBookRequest(BookRequestDTO bookRequestDTO);

    List<BookRequestDTO> findAllBookRequests();

    void addBookRequestNotification(BookRequestNotificationDTO bookRequestNotificationDTO);

    void updateBookRequestNotification(BookRequestNotificationDTO bookRequestNotificationDTO);

    Long getNextValueOfBookRequestIdSequence();

    List<BookRequestOutputDTO> findFullBookRequests();
}
