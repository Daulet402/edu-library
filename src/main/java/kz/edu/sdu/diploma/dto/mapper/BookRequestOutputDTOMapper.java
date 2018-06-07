package kz.edu.sdu.diploma.dto.mapper;

import kz.edu.sdu.diploma.dto.BookRequestOutputDTO;
import kz.edu.sdu.diploma.utils.DateTimeUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRequestOutputDTOMapper implements RowMapper {

    @Override
    public BookRequestOutputDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookRequestOutputDTO bookRequestOutputDTO = new BookRequestOutputDTO();
        bookRequestOutputDTO.setName(rs.getString("name"));
        bookRequestOutputDTO.setReadBy(rs.getLong("read_by"));
        bookRequestOutputDTO.setAuthor(rs.getString("author"));
        bookRequestOutputDTO.setIsRead(rs.getBoolean("is_read"));
        bookRequestOutputDTO.setStudentId(rs.getString("student_id"));
        bookRequestOutputDTO.setBookRequestId(rs.getLong("book_request_id"));
        bookRequestOutputDTO.setNotificationId(rs.getLong("notification_id"));
        bookRequestOutputDTO.setCreatedOn(DateTimeUtils.fromTimestamp(rs.getTimestamp("created_on")));
        bookRequestOutputDTO.setUpdatedOn(DateTimeUtils.fromTimestamp(rs.getTimestamp("updated_on")));
        bookRequestOutputDTO.setPublicationDate(DateTimeUtils.fromTimestamp(rs.getTimestamp("publication_date")));
        return bookRequestOutputDTO;
    }
}
