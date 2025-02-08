package bzh.duncan.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold successful response information"
)
public class ResponseDto {

    // Ne pas mettre d'exemple, car ce ne sera pas toujours le même
    @Schema(description = "Status code of the response")
    private String statusCode;

    @Schema(description = "Message of the response")
    private String message;

}
