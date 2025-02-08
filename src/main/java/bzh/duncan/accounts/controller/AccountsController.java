package bzh.duncan.accounts.controller;

import bzh.duncan.accounts.constants.AccountsConstants;
import bzh.duncan.accounts.dto.AccountsDto;
import bzh.duncan.accounts.dto.CustomerDto;
import bzh.duncan.accounts.dto.ErrorResponseDto;
import bzh.duncan.accounts.dto.ResponseDto;
import bzh.duncan.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Accounts",
        description = "APIs for managing customer accounts"
)
@RestController
@RequestMapping(value = "/api", produces = "application/json")
@AllArgsConstructor
@Validated
public class AccountsController {

    private final IAccountsService accountsService;

    @Operation(
            summary = "Create a new account",
            description = "Create a new account for a customer"
    )
    @ApiResponses
            (value = {
                    @ApiResponse(responseCode = "201",
                            description = "Account created successfully"
                    ),
                    @ApiResponse(responseCode = "500",
                            description = "Internal server error",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorResponseDto.class)
                            )
                    )
            })
    @PostMapping("/createAccount")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        accountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
            @RequestParam String mobileNumber) {

        CustomerDto customerDto = accountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = accountsService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number should be 10 digits")
            @RequestParam String mobileNumber
    ) {
        boolean isDeleted = accountsService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.STATUS_200, AccountsConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.STATUS_417, AccountsConstants.MESSAGE_417_DELETE));
        }
    }

}
