package kz.edu.sdu.diploma.implementation.dao;

import kz.edu.sdu.diploma.dto.BookDTO;
import kz.edu.sdu.diploma.dto.mapper.BookDTOMapper;
import kz.edu.sdu.diploma.service.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDaoImpl implements BookDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String FIND_BY_SORT_STR_OR_PUBLICATION_YEAR_QUERY = "select * from books.books where sortstr like ? or to_char(desc_31, '9999') like ?";

    @Override
    public List<BookDTO> findBooksBySortStrOrPublicationYear(String sortStr) {
        return jdbcTemplate.query(
                FIND_BY_SORT_STR_OR_PUBLICATION_YEAR_QUERY,
                new Object[]{getLikeConditionInternal(sortStr), getLikeConditionInternal(sortStr)},
                new BookDTOMapper()
        );
    }

    private String getLikeConditionInternal(String value) {
        return "%".concat(value).concat("%");
    }
}
