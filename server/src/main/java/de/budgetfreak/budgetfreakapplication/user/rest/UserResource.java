package de.budgetfreak.budgetfreakapplication.user.rest;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class UserResource extends ResourceSupport {

    private final String name;
    //TODO RUKL private final List<String> budgets;

    @JsonCreator
    public UserResource(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
