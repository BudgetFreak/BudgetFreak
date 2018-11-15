package de.budgetfreak.budgeting.domain;

import de.budgetfreak.usermanagement.domain.User;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import javax.persistence.*;

/**
 * A category contains monthly spendings and has relating {@link Transaction}-entities which contain the
 * spending for each category. Categories are combined in a {@link MasterCategory}.
 */
@Entity
@Table(name = "BF_CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MASTERCATEGORY_ID")
    private MasterCategory masterCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    @Column(name = "TYPE")
    private CategoryType type = CategoryType.USER_DEFINED;

    public Category() {
    }

    public Category(String name, User user, CategoryType type) {
        this.name = name;
        this.user = user;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public Category setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Category setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }

    public MasterCategory getMasterCategory() {
        return masterCategory;
    }

    public Category setMasterCategory(MasterCategory masterCategory) {
        this.masterCategory = masterCategory;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Category setUser(User user) {
        this.user = user;
        return this;
    }

    public CategoryType getType() {
        return type;
    }

    public Category setType(CategoryType type) {
        this.type = type;
        return this;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
