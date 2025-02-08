package bzh.duncan.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name = "ErrorResponse",
        description = "Schema to hold error response information"
)
public class ErrorResponseDto {

    @Schema(description = "API path where the error occurred", example = "/api/v1/customers")
    private String apiPath;

    @Schema(description = "HTTP status code of the error", example = "418")
    private HttpStatus errorCode;

    @Schema(description = "Error message", example = "I'm a teapot")
    private String errorMessage;

    @Schema(description = "Time when the error occurred", example = "2021-09-01T12:00:00")
    private LocalDateTime errorTime;
}
