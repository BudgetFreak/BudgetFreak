package de.budgetfreak.budgetfreakapplication.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {

    private String name;
    private String currency;

//   TODO @JsonCreator
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
