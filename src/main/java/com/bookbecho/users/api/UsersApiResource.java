package com.bookbecho.users.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@Component
@Scope("singleton")
@Tag(name = "Users", description = "An API capability to support administration of application users.")
public class UsersApiResource {

    @Autowired
    public UsersApiResource(){}


    @PostMapping("/hello")
    @Operation(summary = "Create a User", description = "Adds new application user.\n" + "\n"
            + "Note: Password information is not required (or processed). Password details at present are auto-generated and then sent to the email account given (which is why it can take a few seconds to complete).\n"
            + "\n" + "Mandat" +
            "ory Fields: \n" + "username, firstname, lastname, email, officeId, roles, sendPasswordToEmail\n" + "\n"
            + "Optional Fields: \n" + "staffId,passwordNeverExpires,isSelfServiceUser,clients")
    @RequestBody(required = true, content = @Content(schema = @Schema(implementation = UsersApiResourceSwagger.PostUsersRequest.class)))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK") })
    public String create(@Parameter(hidden = true) final String apiRequestBodyAsJson) {
        System.out.println(apiRequestBodyAsJson);
        return apiRequestBodyAsJson;
    }

    @GetMapping("/rip")
    public String get() {
        System.out.println("fork");
        return "Rip";
    }
}
