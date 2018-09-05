package de.budgetfreak.utils.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springframework.hateoas.ResourceSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

/**
 * Extend {@link ResourceSupport} for embedding other resources.
 */
public abstract class ResourceEmbeddableSupport extends ResourceSupport {

    private final Map<String, List<? extends ResourceSupport>> embedded = new HashMap<>();

    @JsonInclude(NON_EMPTY)
    @JsonProperty("_embedded")
    public Map<String, List<? extends ResourceSupport>> getEmbeddedResources() {
        return embedded;
    }

    public void embedResource(String relationship, List<? extends ResourceSupport> resources) {
        embedded.put(relationship, resources);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ResourceEmbeddableSupport)) return false;

        ResourceEmbeddableSupport that = (ResourceEmbeddableSupport) o;

        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(embedded, that.embedded)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .appendSuper(super.hashCode())
                .append(embedded)
                .toHashCode();
    }
}