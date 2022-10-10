package pl.mlisowski.projectmanagement.common.web;

import com.auth0.jwt.exceptions.TokenExpiredException;
import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class CommonControllerAdvice {

    @ExceptionHandler({TokenExpiredException.class})
    public ResponseEntity<ErrorResponse> handleUnathorized(Exception e) {
        return error(HttpStatus.UNAUTHORIZED, e.getMessage());
    }

    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFound(Exception e) {
        return error(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<ErrorResponse> handleDefault(Exception e) {
        return error(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private ResponseEntity<ErrorResponse> error(HttpStatus status, String message) {
        return ResponseEntity.status(status)
                .body(ErrorResponse.of(status.value(), message));
    }

    @Value
    @Builder
    private static class ErrorResponse {
        int code;
        String errorMessage;

        public static ErrorResponse of(int code, String errorMessage) {
            return new ErrorResponse(code, errorMessage);
        }
    }

}
