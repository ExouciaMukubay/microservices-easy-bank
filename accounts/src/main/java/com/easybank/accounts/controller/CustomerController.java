package com.easybank.accounts.controller;


import com.easybank.accounts.dto.CustomerDto;
import com.easybank.accounts.dto.ResponseDto;
import com.easybank.accounts.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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
        name = "CRUD REST APIs for customer in Easy Bank"
)
@RestController
@RequestMapping(path = "/api/customer", produces = {MediaType.APPLICATION_JSON_VALUE})
@AllArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @Operation(
            summary = "Create Account Details"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto) {
        customerService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(CREATED.getCode(), CREATED.getMessage()));
    }

    @Operation(
            summary = "Fetch Customer Account Details"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchCustomerDetails(@RequestParam Long customerAccountNumber) {
        var customerDto = customerService.fetchCustomerDetails(customerAccountNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDto);
    }

    @Operation(
            summary = "Fetch all Customer Account Details REST API"
    )
    @GetMapping("/all")
    public ResponseEntity<Page<CustomerDto>> getAllCustomers(@RequestParam(required = false) String customerType,
                                                             @PageableDefault(size = 10, sort = "surname") Pageable pageable) {
        var customers = customerService.getAllCustomers(customerType, pageable);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customers);
    }


    @Operation(
            summary = "Update Customer Account Details REST API",
            description = "REST API to update Customer Account details not the account details"
    )

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateCustomerAccountDetails(@Valid @RequestBody CustomerDto customerDto) {
        boolean isUpdated = customerService.updateCustomerAccount(customerDto);
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
            summary = "Delete Account & Customer Details REST API"
    )
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccountDetails(@RequestParam Long customerAccountNumber) {
        boolean isDeleted = customerService.deleteAccount(customerAccountNumber);
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

