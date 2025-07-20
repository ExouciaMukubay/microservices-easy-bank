package com.easybank.accounts.controller;

import com.easybank.accounts.dto.AccountsDto;
import com.easybank.accounts.dto.ErrorResponseDto;
import com.easybank.accounts.service.AccountsServiceI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "CRUD REST APIs for Accounts in Easy Bank",
        description = "CRUD REST APIs in Easy Bank to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path = "/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    private final AccountsServiceI accountsServiceI;

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    }
    )
    /**
     * Retrieve account details based on the customerAccountNumber
     *
     * @param mobileNumber the mobile number of the account
     * @return the account details
     */
    @GetMapping("/fetch/")
    public ResponseEntity<AccountsDto> fetchAccountsDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]{10})", message = "Account " +
                                                                    "number must be 10 digits")
                                                            String accountNumber) {
        var accountsDto = accountsServiceI.fetchAccountDetails(accountNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountsDto);
    }
}
