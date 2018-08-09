package de.budgetfreak.budgeting.domain;

import de.budgetfreak.accounting.domain.Account;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity(name = "BF_TRANSACTION")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "booking_date")
    private Date bookingDate;

    @Column(name = "description")
    private String description;

    @Column(name = "cleared")
    private boolean cleared;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "payee_id")
    private Payee payee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public Long getId() {
        return id;
    }

    public Transaction setId(Long id) {
        this.id = id;
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Transaction setAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public Transaction setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Transaction setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isCleared() {
        return cleared;
    }

    public Transaction setCleared(boolean cleared) {
        this.cleared = cleared;
        return this;
    }

    public Payee getPayee() {
        return payee;
    }

    public Transaction setPayee(Payee payee) {
        this.payee = payee;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public Transaction setCategory(Category category) {
        this.category = category;
        return this;
    }

    public Account getAccount() {
        return account;
    }

    public Transaction setAccount(Account account) {
        this.account = account;
        return this;
    }
}
