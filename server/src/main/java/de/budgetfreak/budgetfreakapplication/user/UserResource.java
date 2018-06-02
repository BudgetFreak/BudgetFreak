package de.budgetfreak.budgetfreakapplication.user;

import org.springframework.hateoas.ResourceSupport;

import java.nio.file.Files;

public class UserResource extends ResourceSupport {

    private String name;
    private String currency;

    public UserResource() {
    }

    public UserResource(String name, String currency) {
        this.name = name;
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public String getCurrency() {
        return currency;
    }
}