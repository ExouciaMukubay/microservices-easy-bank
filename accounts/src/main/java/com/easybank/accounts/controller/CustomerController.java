package com.easybank.accounts.controller;


import com.easybank.accounts.dto.CustomerDto;
import com.easybank.accounts.dto.ErrorResponseDto;
import com.easybank.accounts.dto.ResponseDto;
import com.easybank.accounts.service.CustomerServiceI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static com.easybank.accounts.constants.ResponseStatus.*;

/**
 * CRUD REST APIs for Accounts in EasyBank to manage account details
 */
@Tag(
        name = "CRUD REST APIs for customer in Easy Bank",
        description = "CRUD REST APIs in Easy Bank to CREATE, UPDATE, FETCH AND DELETE account details"
)
@RestController
@RequestMapping(path = "/api/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CustomerController {

    private final CustomerServiceI customerServiceI;

    @Operation(
            summary = "Create Account REST API",
            description = "REST API to create new Customer & new Account. Available account types: Savings, Checking," +
                    " " +
                    "Current, Loan"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
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
     * Create a new account and return the result of the operation.
     *
     * @param customerDto customer details
     * @return a response with the status of the operation
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        customerServiceI.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CREATED.getCode(), CREATED.getMessage()));
    }

    @Operation(
            summary = "Fetch Customer Account Details REST API",
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
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchCustomerDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                            Long customerAccountNumber) {
        var customerDto = customerServiceI.fetchCustomerDetails(customerAccountNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }

    @GetMapping("/all")

    public ResponseEntity<Page<CustomerDto>> getAllCustomers(@RequestParam(required = false) String customerType,
                                                             @PageableDefault(size = 10) Pageable pageable) {
        var customers = customerServiceI.getAllCustomers(customerType, pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customers);
    }


    @Operation(
            summary = "Update Customer Account Details REST API",
            description = "REST API to update Customer &  Account details based on a account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCustomerAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = customerServiceI.updateCustomerAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(SUCCESS.getCode(), SUCCESS.getMessage()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(UPDATE_FAILED.getCode(), UPDATE_FAILED.getMessage()));
        }
    }


    @Operation(
            summary = "Delete Account & Customer Details REST API",
            description = "REST API to delete Customer &  Account details based on a mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
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
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
                                                            Long customerAccountNumber) {
        boolean isDeleted = customerServiceI.deleteAccount(customerAccountNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(SUCCESS.getCode(), SUCCESS.getMessage()));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(DELETE_FAILED.getCode(), DELETE_FAILED.getMessage()));
        }
    }
}

