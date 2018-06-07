package kz.edu.sdu.diploma.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookRequestNotificationDTO {
    private Long id;
    private Long readBy;
    private Boolean isRead;
    private Long bookRequestId;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
