package kz.edu.sdu.diploma.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kz.edu.sdu.diploma.configuration.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRequestOutputDTO {
    private Long readBy;
    private String name;
    private String author;
    private Boolean isRead;
    private String studentId;
    private Long bookRequestId;
    private Long notificationId;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdOn;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedOn;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime publicationDate;
}
