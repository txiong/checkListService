package thorxiong.demo.checklist.exception;

import org.springframework.http.HttpStatus;

public class ApplicationError {
    public final int status;
    public final String errorMessage;

    public ApplicationError(ApplicationException e, HttpStatus status) {
        this.status = status.value();
        this.errorMessage = e.getLocalizedMessage();
    }

    public ApplicationError(HttpStatus status) {
        this.status = status.value();
        this.errorMessage = ApplicationException.ERROR_BAD_REQUEST;
    }
}
