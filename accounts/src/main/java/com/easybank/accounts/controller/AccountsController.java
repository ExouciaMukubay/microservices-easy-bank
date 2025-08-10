package com.easybank.accounts.controller;

import com.easybank.accounts.dto.AccountsDto;
import com.easybank.accounts.dto.ResponseDto;
import com.easybank.accounts.service.AccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.easybank.accounts.constants.ResponseStatus.DELETE_FAILED;
import static com.easybank.accounts.constants.ResponseStatus.SUCCESS;

@Tag(
        name = "CRUD REST APIs for accounts in Easy Bank"
)
@RestController
@RequestMapping(path = "/api/accounts", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class AccountsController {

    private final AccountsService accountsService;

    @Operation(
            summary = "Create Account Details REST API",
            description = "REST API to cretae Account details"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody AccountsDto accountsDto) {
        accountsService.createAccount(accountsDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(SUCCESS.getCode(), SUCCESS.getMessage()));
    }

    @Operation(
            summary = "Fetch Account Details REST API",
            description = "REST API to fetch Customer &  Account details based on a mobile number"
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
                                                            Long accountNumber) {
        var accountsDto = accountsService.fetchAccountDetails(accountNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(accountsDto);
    }

    @Operation(
            summary = "Delete Account Details REST API",
            description = "REST API to delete Account details "
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam Long accountNumber) {
        boolean isDeleted = accountsService.deleteAccount(accountNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(SUCCESS.getCode(), SUCCESS.getMessage()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDto(DELETE_FAILED.getCode(), DELETE_FAILED.getMessage()));
        }
    }

}
