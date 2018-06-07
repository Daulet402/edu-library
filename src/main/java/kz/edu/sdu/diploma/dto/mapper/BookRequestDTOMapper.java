package kz.edu.sdu.diploma.dto.mapper;

import kz.edu.sdu.diploma.dto.BookRequestDTO;
import kz.edu.sdu.diploma.utils.DateTimeUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRequestDTOMapper implements RowMapper {

    @Override
    public BookRequestDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookRequestDTO bookRequestDTO = new BookRequestDTO();
        bookRequestDTO.setId(rs.getLong("book_request_id"));
        bookRequestDTO.setAuthor(rs.getString("author"));
        bookRequestDTO.setName(rs.getString("name"));
        bookRequestDTO.setPublicationDate(DateTimeUtils.fromTimestamp(rs.getTimestamp("publication_date")));
        bookRequestDTO.setStudentId(rs.getLong("student_id"));
        return bookRequestDTO;
    }
}
