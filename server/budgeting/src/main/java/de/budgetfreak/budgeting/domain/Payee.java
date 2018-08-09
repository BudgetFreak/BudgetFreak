package de.budgetfreak.budgeting.domain;

import javax.persistence.*;

@Entity(name = "BF_PAYEE")
public class Payee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public Payee setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Payee setName(String name) {
        this.name = name;
        return this;
    }
}
