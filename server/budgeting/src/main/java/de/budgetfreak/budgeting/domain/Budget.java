package de.budgetfreak.budgeting.domain;

import de.budgetfreak.usermanagement.domain.User;

import javax.persistence.*;

@Entity(name = "BF_BUDGET")
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User user;

    public Long getId() {
        return id;
    }

    public Budget setId(Long id) {
        this.id = id;
        return this;
    }

    public int getYear() {
        return year;
    }

    public Budget setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public Budget setMonth(int month) {
        this.month = month;
        return this;
    }

    public User getUser() {
        return user;
    }

    public Budget setUser(User user) {
        this.user = user;
        return this;
    }
}
