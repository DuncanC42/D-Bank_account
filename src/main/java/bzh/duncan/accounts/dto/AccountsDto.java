package bzh.duncan.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(
        name = "Accounts",
        description = "Schema to hold account information"
)
public class AccountsDto {

    @Schema(description = "Account number of bank account", example = "1234567890")
    @NotEmpty(message = "Account number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number should be a 10 digit number")
    private Long accountNumber;

    @Schema(description = "Account type of bank account", example = "Savings")
    @NotEmpty(message = "Customer id cannot be null or empty")
    private String accountType;

    @Schema(description = "Branch address of bank account", example = "123, Main Street, New York")
    @NotEmpty(message = "Branch address cannot be null or empty")
    private String branchAddress;
}
