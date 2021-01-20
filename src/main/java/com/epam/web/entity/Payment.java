package com.epam.web.entity;

import java.util.Date;
import java.util.Objects;

/**
 * The {@code Payment} presents payment object
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class Payment implements Entity {
    private final Long id;
    private Double amount;
    private Date paymentDate;
    private Long userId;

    public Payment(Long id, Double amount, Date paymentDate, Long userId) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.userId = userId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Payment payment = (Payment) o;

        if (!Objects.equals(id, payment.id)) return false;
        if (!Objects.equals(amount, payment.amount)) return false;
        if (!Objects.equals(paymentDate, payment.paymentDate)) return false;
        return Objects.equals(userId, payment.userId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (paymentDate != null ? paymentDate.hashCode() : 0);
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", userId=" + userId +
                '}';
    }
}
