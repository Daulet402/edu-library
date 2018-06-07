package kz.edu.sdu.diploma.tasks;

import kz.edu.sdu.diploma.dto.StudentDTO;
import kz.edu.sdu.diploma.service.EmailService;
import kz.edu.sdu.diploma.service.dao.StudentDao;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@PropertySource("classpath:app.mail.properties")
public class SendMailTaskExecutor {

    @Value("${app.mail.username}")
    public String from;

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private EmailService emailService;

    private static final String TITLE = "Библиотечные книги";
    private static final String TEXT = "Уважаемые студенты! Напоминаем о необходимости вернуть в библиотеку книги, которые Вы брали в течений тукущего и предыдущих семестров";

    @Scheduled(cron = "0 04 14 25 5,12 ?")
    //@Scheduled(fixedDelay = 10_000)
    public void sendEmailToStudents() {
        List<StudentDTO> allStudentsWithEmail = studentDao.getAllStudentsWithEmail();
        if (CollectionUtils.isNotEmpty(allStudentsWithEmail)) {
            //emailService.sendMail("140103081@stu.sdu.edu.kz", from, TITLE, TEXT);
            for (StudentDTO student : allStudentsWithEmail) {
                emailService.sendMail(student.getUsername(), from, TITLE, TEXT);
                System.out.println("mail sent to " + student.getUsername());
            }
        }
    }
}
