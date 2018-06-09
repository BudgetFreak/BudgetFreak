package de.budgetfreak.usermanagement.web;

import org.springframework.hateoas.Link;

public abstract class ResourceLinks<T> {
    private Class<T> targetType;

    public ResourceLinks(Class<T> targetType) {
        this.targetType = targetType;
    }

    public abstract Iterable<Link> generateLinks(T entity);

    public boolean accepts(Class<T> targetClass) {
        return targetClass == targetType;
    }
}
