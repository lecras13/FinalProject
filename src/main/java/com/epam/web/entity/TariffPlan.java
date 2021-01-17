package com.epam.web.entity;

import java.util.Objects;

public class TariffPlan implements Entity {
    private final Long id;
    private String tariffName;
    private Integer price;
    private String description;

    public TariffPlan(Long id, String tariffName, Integer price, String description) {
        this.id = id;
        this.tariffName = tariffName;
        this.price = price;
        this.description = description;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TariffPlan that = (TariffPlan) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(tariffName, that.tariffName)) return false;
        if (!Objects.equals(price, that.price)) return false;
        return Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (tariffName != null ? tariffName.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TariffPlan{" +
                "id=" + id +
                ", tariffName='" + tariffName + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
