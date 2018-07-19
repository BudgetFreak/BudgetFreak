package de.budgetfreak.utils.web;

import org.springframework.hateoas.Link;

import java.util.Collection;

/**
 * Factory to determine all {@link Link}s for a given entity.
 *
 * @param <E> The type of the entity.
 */
public abstract class ResourceLinks<E> {
    private Class<E> targetType;

    public ResourceLinks(Class<E> targetType) {
        this.targetType = targetType;
    }

    public abstract Collection<Link> generateLinks(E entity);

    public boolean accepts(Class<?> targetClass) {
        return targetClass == targetType;
    }
}
