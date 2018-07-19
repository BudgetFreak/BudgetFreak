package de.budgetfreak.budgeting.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.*;

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
}