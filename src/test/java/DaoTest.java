import kz.edu.sdu.diploma.app.BackEndApp;
import kz.edu.sdu.diploma.dto.BookRequestOutputDTO;
import kz.edu.sdu.diploma.dto.StudentDTO;
import kz.edu.sdu.diploma.service.AuthorizationService;
import kz.edu.sdu.diploma.service.EmailService;
import kz.edu.sdu.diploma.service.dao.AdministratorDao;
import kz.edu.sdu.diploma.service.dao.BookDao;
import kz.edu.sdu.diploma.service.dao.BookRequestDao;
import kz.edu.sdu.diploma.service.dao.StudentDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = BackEndApp.class)
@PropertySource("classpath:app.datasource.properties")
public class DaoTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private BookDao bookDao;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private BookRequestDao bookRequestDao;

    @Autowired
    private AdministratorDao administratorDao;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private EmailService emailService;

    @Test
    public void test() throws Exception {
        List<BookRequestOutputDTO> fullBookRequests = bookRequestDao.findFullBookRequests();
        Assert.assertNotNull(fullBookRequests);
    }

    @Test
    public void testFindStudentByUsername() {
        StudentDTO student = studentDao.findStudentByUsername("140103082");
        Assert.assertNotNull(student);
    }

    @Test
    public void testIsStudentSignInSuccessful() {
        Assert.assertTrue(authorizationService.isStudentSignInSuccessful("140103082", "asd"));
    }

    @Test
    public void testGetAllStudentsWithEmail() {
        List<StudentDTO> students = studentDao.getAllStudentsWithEmail();
        Assert.assertNotNull(students);
    }
}
