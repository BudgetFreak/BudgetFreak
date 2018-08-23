package de.budgetfreak.demousergeneration.web;

import org.springframework.hateoas.ResourceSupport;

/**
 * Ressource f�r den DemoUserName
 */
public class DemoUserNameResource extends ResourceSupport {

    private String userName;

    public DemoUserNameResource() {
    }

    public DemoUserNameResource(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}
