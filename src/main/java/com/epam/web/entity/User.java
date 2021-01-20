package com.epam.web.entity;

import java.util.Objects;

/**
 * The {@code User} presents user object
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class User implements Entity {
    private final Long id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;
    private Double totalAmount;
    private Boolean statusBlock;
    private Double traffic;
    private Long tariffId;
    private Long promotionId;
    private Integer discount;

    public User(Long id, String login, String password, String firstName, String lastName, Role role, Double totalAmount, Boolean statusBlock, Double traffic, Long tariffId, Long promotionId, Integer discount) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.totalAmount = totalAmount;
        this.statusBlock = statusBlock;
        this.traffic = traffic;
        this.tariffId = tariffId;
        this.promotionId = promotionId;
        this.discount = discount;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Boolean getStatusBlock() {
        return statusBlock;
    }

    public void setStatusBlock(Boolean statusBlock) {
        this.statusBlock = statusBlock;
    }

    public Double getTraffic() {
        return traffic;
    }

    public void setTraffic(Double traffic) {
        this.traffic = traffic;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }

    public Long getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(Long promotionId) {
        this.promotionId = promotionId;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(id, user.id)) return false;
        if (!Objects.equals(login, user.login)) return false;
        if (!Objects.equals(password, user.password)) return false;
        if (!Objects.equals(firstName, user.firstName)) return false;
        if (!Objects.equals(lastName, user.lastName)) return false;
        if (role != user.role) return false;
        if (!Objects.equals(totalAmount, user.totalAmount)) return false;
        if (!Objects.equals(statusBlock, user.statusBlock)) return false;
        if (!Objects.equals(traffic, user.traffic)) return false;
        if (!Objects.equals(tariffId, user.tariffId)) return false;
        if (!Objects.equals(promotionId, user.promotionId)) return false;
        return Objects.equals(discount, user.discount);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        result = 31 * result + (totalAmount != null ? totalAmount.hashCode() : 0);
        result = 31 * result + (statusBlock != null ? statusBlock.hashCode() : 0);
        result = 31 * result + (traffic != null ? traffic.hashCode() : 0);
        result = 31 * result + (tariffId != null ? tariffId.hashCode() : 0);
        result = 31 * result + (promotionId != null ? promotionId.hashCode() : 0);
        result = 31 * result + (discount != null ? discount.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", role=" + role +
                ", totalAmount=" + totalAmount +
                ", statusBlock=" + statusBlock +
                ", traffic=" + traffic +
                ", tariffId=" + tariffId +
                ", promotionId=" + promotionId +
                ", discount=" + discount +
                '}';
    }
}