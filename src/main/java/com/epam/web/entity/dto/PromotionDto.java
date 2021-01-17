package com.epam.web.entity.dto;

import com.epam.web.entity.Entity;
import com.epam.web.entity.TariffPlan;

import java.util.Date;
import java.util.Objects;

public class PromotionDto implements Entity {
    private final Long id;
    private String promotionName;
    private Date startDate;
    private Date endDate;
    private String description;
    private TariffPlan tariffPlan;
    private Integer newPrice;

    public PromotionDto(Long id, String promotionName, Date startDate, Date endDate, String description, TariffPlan tariffPlan, Integer newPrice) {
        this.id = id;
        this.promotionName = promotionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.tariffPlan = tariffPlan;
        this.newPrice = newPrice;
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TariffPlan getTariffPlan() {
        return tariffPlan;
    }

    public void setTariffPlan(TariffPlan tariffPlan) {
        this.tariffPlan = tariffPlan;
    }

    public Integer getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(Integer newPrice) {
        this.newPrice = newPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PromotionDto that = (PromotionDto) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(promotionName, that.promotionName))
            return false;
        if (!Objects.equals(startDate, that.startDate)) return false;
        if (!Objects.equals(endDate, that.endDate)) return false;
        if (!Objects.equals(description, that.description)) return false;
        if (!Objects.equals(tariffPlan, that.tariffPlan)) return false;
        return Objects.equals(newPrice, that.newPrice);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (promotionName != null ? promotionName.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (tariffPlan != null ? tariffPlan.hashCode() : 0);
        result = 31 * result + (newPrice != null ? newPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PromotionDto{" +
                "id=" + id +
                ", promotionName='" + promotionName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", tariffPlan=" + tariffPlan +
                ", newPrice=" + newPrice +
                '}';
    }
}
