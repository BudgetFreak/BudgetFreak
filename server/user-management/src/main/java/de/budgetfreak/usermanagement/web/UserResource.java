package de.budgetfreak.usermanagement.web;

import org.springframework.hateoas.ResourceSupport;

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
