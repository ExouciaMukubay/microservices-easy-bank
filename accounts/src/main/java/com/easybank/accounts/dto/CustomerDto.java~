package com.easybank.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@Schema(
        name = "Customer",
        description = "Schema to hold Customer and Account information"
)
public class CustomerDto {

    @Schema(description = "Customer Account Number", example = "101")
    private Long customerAccountNumber;

    @Schema(
            description = "Surname of the customer", example = "Max"
    )
    @NotEmpty(message = "Name can not be a null or empty")
    private String surname;

    @Schema(
            description = "Lastname of the customer", example = "Mustermann"
    )
    @NotEmpty(message = "Name can not be a null or empty")
    private String lastname;

    @Schema(
            description = "Email address of the customer", example = "max@mustermann.com"
    )
    @NotEmpty(message = "Email address can not be a null or empty")
    @Email(message = "Email address should be a valid value")
    private String email;

    @Schema(
            description = "Mobile Number of the customer", example = "4912345678"
    )
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @Schema(
            description = "Type of the customer", example = "Individual or Corporate"
    )
    @NotEmpty(message = "customer type can not be a null or empty")
    private String customerType;

    @Schema(
            description = "Address of the customer", example = "Heidelbergerstraße 1, 10117 Belrin"
    )
    @NotEmpty(message = "Address can not be a null or empty")
    private String address;

    @Schema(
            description = "Account(s) details of the Customer"
    )
    private List<AccountsDto> accountsDto;
}