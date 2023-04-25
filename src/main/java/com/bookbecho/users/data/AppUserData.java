package com.bookbecho.users.data;

public final class AppUserData {
    private final Long id;
    private final String username;
    private final String officeName;
    private final String firstname;
    private final String lastname;
    private final String email;


    private AppUserData(Long id, String username, String officeName, String firstname, String lastname, String email) {
        this.id = id;
        this.username = username;
        this.officeName = officeName;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
    }



}
