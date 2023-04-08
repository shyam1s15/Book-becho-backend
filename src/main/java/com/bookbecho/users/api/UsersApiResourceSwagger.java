package com.bookbecho.users.api;


import io.swagger.v3.oas.annotations.media.Schema;

final class UsersApiResourceSwagger {
    private UsersApiResourceSwagger() {}

    @Schema(description = "PostUsersRequest")
    public static final class PostUsersRequest {
        private PostUsersRequest() {}

        @Schema(example = "newuser")
        public String username;
        @Schema(example = "password")
        public String password;
        @Schema(example = "repeatPassword")
        public String repeatPassword;
        @Schema(example = "Test")
        public String firstname;
        @Schema(example = "User")
        public String lastname;
        @Schema(example = "whatever@mifos.org")
        public String email;
    }

}
