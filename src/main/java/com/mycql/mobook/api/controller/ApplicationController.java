package com.mycql.mobook.api.controller;

import com.mycql.mobook.api.model.request.MobilePhoneBookingSearchRequest;
import com.mycql.mobook.api.model.request.MobilePhoneCheckinRequest;
import com.mycql.mobook.api.model.request.MobilePhoneCheckoutRequest;
import com.mycql.mobook.api.model.request.MobilePhoneSearchRequest;
import com.mycql.mobook.api.model.response.MobilePhone;
import com.mycql.mobook.api.model.response.MobilePhoneBookingEntry;
import com.mycql.mobook.api.model.response.MobilePhoneCheckinResponse;
import com.mycql.mobook.api.model.response.MobilePhoneCheckoutResponse;
import com.mycql.mobook.exception.ClientNotFoundException;
import com.mycql.mobook.exception.NoAvailableModelFoundException;
import com.mycql.mobook.exception.PhoneExistsException;
import com.mycql.mobook.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@OpenAPIDefinition(
    info = @Info(
        title = "MoBook: An API for borrowing mobile phones from the inventory",
        version = "0.0.1",
        contact = @Contact(name = "Chris Luna", email = "codeinmydna@gmail.com")
    )
)
@RequestMapping("/api")
public interface ApplicationController {

    @Operation(summary = "Check for a mobile phone details from the inventory", tags = {"Mobile Phone Details"})
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Mobile Phone details given the id if found",
                content = {
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MobilePhone.class)
                )
        }),
        @ApiResponse(
            responseCode = "404",
            description = "No phone in the inventory with the given id",
            content = {
                @Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)
                )
            }
        ),
    })
    @GetMapping(
        value = "/mobile-phone/{id}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE}
    )
    MobilePhone checkMobilePhone(@Parameter(description = "The id of the mobile phone to view")
                                 @PathVariable Long id) throws ResourceNotFoundException;

    @Operation(summary = "Search for mobile phones in the inventory with the provided attributes", tags = {"Mobile Phone Details"})
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Mobile Phone details matching the search criteris",
            content = {
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(
                        schema = @Schema(implementation = MobilePhone.class)
                    )
                )
            }
        ),
    })
    @PostMapping(
        value = "/mobile-phone/search",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<MobilePhone> searchMobilePhones(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "The filter criteria for the mobile phones to search for",
            useParameterTypeSchema = true
        )
        @RequestBody MobilePhoneSearchRequest searchRequest);

    @Operation(summary = "Checks out / borrows a mobile phone of the given model", tags = {"Booking Operation"})
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Checkout details",
            content = {
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MobilePhoneCheckoutResponse.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "No customer/client with the given id found for checking out",
            content = {
                @Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "422",
            description = "The request cannot be processed because there is no more available phones of the model for checkout",
            content = {
                @Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)
                )
            }
        ),
    })
    @PostMapping(
        value = "/mobile-phone/checkout",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE}
    )
    MobilePhoneCheckoutResponse checkoutMobilePhone(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                description = "The client and phone model for checkout",
                useParameterTypeSchema = true
        )
        @RequestBody MobilePhoneCheckoutRequest checkoutRequest) throws NoAvailableModelFoundException, ClientNotFoundException;

    @Operation(summary = "Checks in / returns a mobile phone of the given model", tags = {"Booking Operation"})
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Checkin details",
            content = {
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MobilePhoneCheckinResponse.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "No customer/client with the given id found for checking out",
            content = {
                @Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "409",
            description = "The provided phone id is already the inventory",
            content = {
                @Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)
                )
            }
        ),
    })
    @PostMapping(
        value = "/mobile-phone/checkin",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE}
    )
    MobilePhoneCheckinResponse checkinMobilePhone(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            description = "The client and phone id for checkin",
            useParameterTypeSchema = true
        )
        @RequestBody MobilePhoneCheckinRequest checkinRequest) throws PhoneExistsException, ClientNotFoundException;

    @Operation(summary = "View the booking details event (checkout / checkin)",  tags = {"Booking Details"})
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "The booking details requested",
            content = {
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = MobilePhoneBookingEntry.class)
                )
            }
        ),
        @ApiResponse(
            responseCode = "404",
            description = "No booking event with the given id",
            content = {
                @Content(
                    mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE,
                    schema = @Schema(implementation = ProblemDetail.class)
                )
            }
        ),
    })
    @GetMapping(
        value = "/booking/{id}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_PROBLEM_JSON_VALUE}
    )
    MobilePhoneBookingEntry checkBooking(@Parameter(description = "The checkout booking reference id")
                                         @PathVariable Long id) throws ResourceNotFoundException;

    @Operation(summary = "Search for booking events with the provided attributes",  tags = {"Booking Details"})
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Booking event details matching the search criteris",
            content = {
                @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(
                        schema = @Schema(implementation = MobilePhoneBookingEntry.class)
                    )
                )
            }
        ),
    })
    @PostMapping(
        value = "/booking/search",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    List<MobilePhoneBookingEntry> searchBookings(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
                required = true,
                description = "The filter criteria for the booking event to search for",
                useParameterTypeSchema = true
        )
        @RequestBody MobilePhoneBookingSearchRequest request);

}
