package de.budgetfreak.budgeting.web;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Controller for accessing accounts.
 */
@RestController
@RequestMapping("/users/{userId}/budget")
@Validated
public class BudgetController {

    /**
     * Get one account. WORK IN PROGRESS. THIS IS CURRENTLY JUST FOR DEFINING THE API.
     */
    @GetMapping
    public Resource<BudgetResource> get(
            @PathVariable("userId") long userId,
            @Valid @NotNull @RequestParam(value = "year") Long year,
            @Valid @NotNull @RequestParam("month") Long month
    ) {
        // create BudgetResource
        BudgetResource budgetResource = new BudgetResource(year, month, new BigDecimal("21.05"), new BigDecimal("1740.00"));
        budgetResource.add(linkTo(methodOn(BudgetController.class).get(userId, year, month)).withSelfRel());
        budgetResource.add(linkTo(methodOn(BudgetController.class).get(userId, year, month + 1)).withRel("next"));
        budgetResource.add(linkTo(methodOn(BudgetController.class).get(userId, year, month - 1)).withRel("previous"));

        // add MasterCategories
        MasterCategoryResource dailySpendings = new MasterCategoryResource("Daily Spendings");
        MasterCategoryResource savings = new MasterCategoryResource("Savings");
        List<MasterCategoryResource> masterCategoryResources = asList(dailySpendings, savings);
        budgetResource.embedResource("masterCategories", masterCategoryResources);

        // add categories to dailySpendings
        dailySpendings.embedResource("categories", Arrays.asList(
                createCategory("Groceries", userId, year, month),
                createCategory("Sports", userId, year, month),
                createCategory("Pocket money", userId, year, month)
        ));

        // add categories to savings
        savings.embedResource("categories", Arrays.asList(
                createCategory("Emergencies", userId, year, month),
                createCategory("New car", userId, year, month)
        ));


        return new Resource<>(budgetResource);
    }

    private CategoryResource createCategory(String name, @PathVariable("userId") long userId, @Valid @NotNull @RequestParam(value = "year") Long year, @Valid @NotNull @RequestParam("month") Long month) {
        CategoryResource categoryResource = new CategoryResource(name, 1337.42, 200, 72.15, 150, 195.10, 132.57);
        categoryResource.add(new Link("/users/" + userId + "/budget/category/" + 42 + "?year=" + year + "&month=" + month));
        return categoryResource;
    }
}
