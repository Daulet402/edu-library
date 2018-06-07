package kz.edu.sdu.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO {
    private Long id;
    private Long labelId;
    private String bkGuid; //bk_guid
    private String author; //desc_1
    private String sortStr;
    private Long catalogId;
    private String bookName; //desc_21
    private Long librarianId;
    private Integer issueYear; //desc_31
    private Long lastLibrarianId; // id_librarian2
    private LocalDateTime recDate;
    private String publicationPlace; //desc_29
    private Integer numberOfInstances; //copynnt
    private LocalDateTime firstInputDate; //firstdate
    private LocalDateTime lastModifiedDate; //lastdate
}
