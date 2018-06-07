package kz.edu.sdu.diploma.implementation.dao;

import kz.edu.sdu.diploma.dto.BookRequestDTO;
import kz.edu.sdu.diploma.dto.BookRequestNotificationDTO;
import kz.edu.sdu.diploma.dto.BookRequestOutputDTO;
import kz.edu.sdu.diploma.dto.mapper.BookRequestDTOMapper;
import kz.edu.sdu.diploma.dto.mapper.BookRequestOutputDTOMapper;
import kz.edu.sdu.diploma.service.dao.BookRequestDao;
import kz.edu.sdu.diploma.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class BookRequestDaoImpl implements BookRequestDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_NEW_BOOK_REQUEST_QUERY = "INSERT INTO book_requests(" +
            "book_request_id, author, name, publication_date, student_id) " +
            "VALUES (?, ?, ?, ?, ?);";

    private static final String FIND_ALL_BOOK_REQUESTS_QUERY = "SELECT * " +
            "FROM book_requests";

    private static final String INSERT_NEW_BOOK_REQUEST_NOTIFICATION_QUERY = "INSERT INTO public.book_request_notifications(" +
            "created_on, updated_on, is_read, read_by, book_request_id) " +
            "VALUES (CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, ?, ?, ?)";

    private static final String UPDATE_BOOK_REQUEST_NOTIFICATION_BY_BOOK_REQUEST_ID_QUERY = "UPDATE public.book_request_notifications " +
            "SET updated_on=CURRENT_TIMESTAMP, is_read=?, read_by=?" +
            " WHERE book_request_id=?";

    private static final String FIND_FULL_BOOK_REQUESTS_QUERY = "SELECT br.book_request_id, br.author, br.name, br.publication_date, bn.id as notification_id, bn.updated_on, bn.created_on, bn.is_read, bn.read_by, s.*" +
            "FROM public.book_requests br " +
            "LEFT JOIN book_request_notifications bn " +
            "USING(book_request_id) " +
            "LEFT JOIN students s " +
            "ON s.id = br.student_id " +
            "WHERE bn.id is not null " +
            "AND s.id is not null " +
            "ORDER BY created_on desc";

    @Override
    public void addBookRequest(BookRequestDTO bookRequestDTO) {
        checkEntityForNull(bookRequestDTO);
        jdbcTemplate.update(INSERT_NEW_BOOK_REQUEST_QUERY, new Object[]{
                bookRequestDTO.getId(),
                bookRequestDTO.getAuthor(),
                bookRequestDTO.getName(),
                DateTimeUtils.toTimestamp(bookRequestDTO.getPublicationDate()),
                bookRequestDTO.getStudentId()
        });
    }

    @Override
    public List<BookRequestDTO> findAllBookRequests() {
        return jdbcTemplate.query(
                FIND_ALL_BOOK_REQUESTS_QUERY,
                new BookRequestDTOMapper()
        );
    }

    @Override
    public void addBookRequestNotification(BookRequestNotificationDTO bookRequestNotificationDTO) {
        checkEntityForNull(bookRequestNotificationDTO);
        jdbcTemplate.update(INSERT_NEW_BOOK_REQUEST_NOTIFICATION_QUERY, new Object[]{
                bookRequestNotificationDTO.getIsRead(),
                bookRequestNotificationDTO.getReadBy(),
                bookRequestNotificationDTO.getBookRequestId()
        });
    }

    @Override
    public void updateBookRequestNotification(BookRequestNotificationDTO bookRequestNotificationDTO) {
        checkEntityForNull(bookRequestNotificationDTO);
        jdbcTemplate.update(UPDATE_BOOK_REQUEST_NOTIFICATION_BY_BOOK_REQUEST_ID_QUERY, new Object[]{
                bookRequestNotificationDTO.getIsRead(),
                bookRequestNotificationDTO.getReadBy(),
                bookRequestNotificationDTO.getBookRequestId()
        });
    }

    @Override
    public Long getNextValueOfBookRequestIdSequence() {
        return jdbcTemplate.queryForObject(" SELECT NEXTVAL('book_request_id_seq ') from book_request_id_seq", Long.TYPE);
    }

    @Override
    public List<BookRequestOutputDTO> findFullBookRequests() {
        return jdbcTemplate.query(
                FIND_FULL_BOOK_REQUESTS_QUERY,
                new BookRequestOutputDTOMapper()
        );
    }

    private void checkEntityForNull(Object entity) {
        if (Objects.isNull(entity))
            throw new IllegalArgumentException("Entity is mandatory");
    }
}
