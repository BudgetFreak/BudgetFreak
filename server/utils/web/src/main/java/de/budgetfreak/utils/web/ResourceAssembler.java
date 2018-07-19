package de.budgetfreak.utils.web;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Wrapper for a {@link org.springframework.hateoas.ResourceAssembler}. Finds all {@link Link}s of a given entity via
 * {@link ResourceLinks}.
 *
 * @param <E> Entity-class
 * @param <R> Resource-class
 */
public abstract class ResourceAssembler<E, R extends ResourceSupport> extends ResourceAssemblerSupport<E, R> {

    private List<ResourceLinks<E>> resourceLinkFactories;

    public ResourceAssembler(Class<?> controllerClass, Class<R> resourceType, List<ResourceLinks<E>> resourceLinkFactories) {
        super(controllerClass, resourceType);
        this.resourceLinkFactories = resourceLinkFactories;
    }

    /**
     * Converts the given entity to a {@link ResourceSupport} and adds all {@link Link}s via the available {@link ResourceLinks}.
     *
     * @param entity The entity to convert.
     * @return The {@link ResourceSupport} representation.
     * @see org.springframework.hateoas.ResourceAssembler#toResource(Object)
     */
    @Override
    public R toResource(E entity) {
        R resource = createResource(entity);

        Collection<Link> resourceLinks = findResourceLinks(entity);
        resource.add(resourceLinks);

        return resource;
    }

    protected abstract R createResource(E entity);

    /**
     * Searches the Spring context for all {@link ResourceLinks} which can handle the class of the given entity.
     *
     * @param entity Entity to create the {link Link}s for.
     * @return A list of all links for this entity.
     */
    private Collection<Link> findResourceLinks(E entity) {
        List<Link> links = new ArrayList<>();
        resourceLinkFactories.stream().filter(link -> link.accepts(entity.getClass())).forEach(link -> links.addAll(link.generateLinks(entity)));
        return links;
    }
}
