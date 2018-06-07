package kz.edu.sdu.diploma.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EntityNotFoundException extends EbaException {

    private Code code;

    private enum Code {
        STUDENT_NOT_FOUND
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    private EntityNotFoundException(Code code, String message) {
        super(message);
        this.code = code;
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }

    public static EntityNotFoundException ofStudentNotFound(String message) {
        return new EntityNotFoundException(Code.STUDENT_NOT_FOUND, message);
    }
}

