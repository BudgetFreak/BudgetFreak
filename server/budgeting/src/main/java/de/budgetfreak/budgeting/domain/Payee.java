package de.budgetfreak.budgeting.domain;

import de.budgetfreak.usermanagement.domain.User;

import javax.persistence.*;

/**
 * A payee can be linked to {@link Transaction}-entities.
 */
@Entity(name = "BF_PAYEE")
public class Payee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

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

    public User getUser() {
        return user;
    }

    public Payee setUser(User user) {
        this.user = user;
        return this;
    }
}
