package com.epam.internetprovider.entity;

import java.util.Date;
import java.util.Objects;

/**
 * The {@code Promotion} presents promotion object
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class Promotion implements Entity {
    private final Long id;
    private String promotionName;
    private Date startDate;
    private Date endDate;
    private String description;
    private Long tariffPlanId;
    private Integer newPrice;
    private Boolean status;

    public Promotion(Long id, String promotionName, Date startDate, Date endDate, String description, Long tariffPlanId, Integer newPrice) {
        this.id = id;
        this.promotionName = promotionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.tariffPlanId = tariffPlanId;
        this.newPrice = newPrice;
    }

    public Promotion(Long id, String promotionName, Date startDate, Date endDate, String description, Long tariffPlanId, Integer newPrice, Boolean status) {
        this.id = id;
        this.promotionName = promotionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.tariffPlanId = tariffPlanId;
        this.newPrice = newPrice;
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
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

    public Long getTariffPlanId() {
        return tariffPlanId;
    }

    public void setTariffPlanId(Long tariffPlanId) {
        this.tariffPlanId = tariffPlanId;
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

        Promotion promotion = (Promotion) o;

        if (!Objects.equals(id, promotion.id)) return false;
        if (!Objects.equals(promotionName, promotion.promotionName))
            return false;
        if (!Objects.equals(startDate, promotion.startDate)) return false;
        if (!Objects.equals(endDate, promotion.endDate)) return false;
        if (!Objects.equals(description, promotion.description))
            return false;
        if (!Objects.equals(tariffPlanId, promotion.tariffPlanId))
            return false;
        return Objects.equals(newPrice, promotion.newPrice);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (promotionName != null ? promotionName.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (tariffPlanId != null ? tariffPlanId.hashCode() : 0);
        result = 31 * result + (newPrice != null ? newPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Promotion{" +
                "id=" + id +
                ", promotionName='" + promotionName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", tariffPlanId=" + tariffPlanId +
                ", newPrice=" + newPrice +
                '}';
    }
}
