package bzh.duncan.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Schema(
        name = "Customer",
        description = "Schema to hold customer and account information"
)
public class CustomerDto {

    @Schema(description = "Name of the customer", example = "John Doe")
    @NotEmpty(message = "Name cannot be null or empty")
    @Size(min = 5, max = 30, message = "The length of the customer name must be between 5 and 30 character")
    private String name;

    @Schema(description = "Email address of the customer", example = "john.doe@email.com")
    @NotEmpty(message = "Email cannot be null or empty")
    @Email(message = "Emal address should be a valid value")
    private String email;

    @Schema(description = "Mobile number of the customer", example = "9876543210")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be a 10 digit number")
    private String mobileNumber;

    @Schema(description = "Account details of the customer")
    private AccountsDto accountsDto;
}