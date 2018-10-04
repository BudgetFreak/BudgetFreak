package de.budgetfreak.budgeting.domain;

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
}
