package com.bookbecho.users.api;

import com.bookbecho.infrastructure.core.serialization.DefaultToApiJsonSerializer;
import com.bookbecho.users.data.AppUserData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/users")
@Component
@Scope("singleton")
@Tag(name = "Users", description = "An API capability to support administration of application users.")
public class UsersApiResource {

    @Autowired
    public UsersApiResource(final DefaultToApiJsonSerializer<AppUserData> toApiJsonSerializer){
        this.toApiJsonSerializer = toApiJsonSerializer;
    }

    private final DefaultToApiJsonSerializer<AppUserData> toApiJsonSerializer;


    @GET
    @Path("/hello")
    @Operation(summary = "Create a User", description = "Adds new application user.\n" + "\n"
            + "Note: Password information is not required (or processed). Password details at present are auto-generated and then sent to the email account given (which is why it can take a few seconds to complete).\n"
            + "\n" + "Mandat" +
            "ory Fields: \n" + "username, firstname, lastname, email, officeId, roles, sendPasswordToEmail\n" + "\n"
            + "Optional Fields: \n" + "staffId,passwordNeverExpires,isSelfServiceUser,clients")
    @RequestBody(required = true, content = @Content(schema = @Schema(implementation = UsersApiResourceSwagger.PostUsersRequest.class)))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK") })
//    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.TEXT_PLAIN })
    public String create(@Parameter(hidden = true) final String apiRequestBodyAsJson) {

//        return this.toApiJsonSerializer.serialize(result);

        return apiRequestBodyAsJson;

    }

    @GET
    @Path("/rip")
    @Produces({ MediaType.APPLICATION_JSON })
    public String get() {
        return "Rip";
    }
}
