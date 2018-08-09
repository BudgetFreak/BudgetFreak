package de.budgetfreak.budgeting.domain;

import javax.persistence.*;

@Entity(name = "BF_CATEGORY")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "matercategory_id")
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
