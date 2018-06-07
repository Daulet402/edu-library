package kz.edu.sdu.diploma.dto.mapper;

import kz.edu.sdu.diploma.dto.BookDTO;
import kz.edu.sdu.diploma.utils.DateTimeUtils;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDTOMapper implements RowMapper {

    @Override
    public BookDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(rs.getLong("id_book"));
        bookDTO.setSortStr(rs.getString("sortstr"));
        bookDTO.setAuthor(rs.getString("desc_1"));
        bookDTO.setBkGuid(rs.getString("bk_guid"));
        bookDTO.setIssueYear(rs.getInt("desc_31"));
        bookDTO.setLabelId(rs.getLong("id_label"));
        bookDTO.setBookName(rs.getString("desc_21"));
        bookDTO.setCatalogId(rs.getLong("id_catalog"));
        bookDTO.setNumberOfInstances(rs.getInt("copycnt"));
        bookDTO.setLibrarianId(rs.getLong("id_librarian"));
        bookDTO.setPublicationPlace(rs.getString("desc_29"));
        bookDTO.setLastLibrarianId(rs.getLong("id_librarian2"));
        bookDTO.setRecDate(DateTimeUtils.fromTimestamp(rs.getTimestamp("recdate")));
        bookDTO.setLastModifiedDate(DateTimeUtils.fromTimestamp(rs.getTimestamp("lastdate")));
        bookDTO.setFirstInputDate(DateTimeUtils.fromTimestamp(rs.getTimestamp("firstdate")));

        return bookDTO;
    }
}
