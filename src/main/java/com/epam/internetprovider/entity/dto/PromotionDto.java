package com.epam.internetprovider.entity.dto;

import com.epam.internetprovider.entity.Entity;

import java.util.Date;
import java.util.Objects;

/**
 * The {@code PromotionDto} presents combination promotion and tariff plan objects
 *
 * @author Roman Alexandrov
 * @version 1.0
 */

public class PromotionDto implements Entity {
    private final Long id;
    private String promotionName;
    private Date startDate;
    private Date endDate;
    private String description;
    private String tariffName;
    private Long tariffId;
    private Integer newPrice;
    private Boolean status;

    public PromotionDto(Long id, String promotionName, Date startDate, Date endDate, String description, String tariffName, Long tariffId, Integer newPrice, Boolean status){
        this.id = id;
        this.promotionName = promotionName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.tariffName = tariffName;
        this.tariffId = tariffId;
        this.newPrice = newPrice;
        this.status = status;
    }

    @Override
    public Long getId(){
        return id;
    }

    public String getPromotionName(){
        return promotionName;
    }

    public void setPromotionName(String promotionName){
        this.promotionName = promotionName;
    }

    public Date getStartDate(){
        return startDate;
    }

    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    public Date getEndDate(){
        return endDate;
    }

    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getTariffName(){
        return tariffName;
    }

    public void setTariffName(String tariffName){
        this.tariffName = tariffName;
    }

    public Long getTariffId(){
        return tariffId;
    }

    public void setTariffId(Long tariffId){
        this.tariffId = tariffId;
    }

    public Integer getNewPrice(){
        return newPrice;
    }

    public void setNewPrice(Integer newPrice){
        this.newPrice = newPrice;
    }

    public Boolean getStatus(){
        return status;
    }

    public void setStatus(Boolean status){
        this.status = status;
    }

    @Override
    public boolean equals(Object o){
        if (this == o)
            return true;
        if (!(o instanceof PromotionDto))
            return false;

        PromotionDto that = (PromotionDto) o;

        if (!Objects.equals(id, that.id))
            return false;
        if (!Objects.equals(promotionName, that.promotionName))
            return false;
        if (!Objects.equals(startDate, that.startDate))
            return false;
        if (!Objects.equals(endDate, that.endDate))
            return false;
        if (!Objects.equals(description, that.description))
            return false;
        if (!Objects.equals(tariffName, that.tariffName))
            return false;
        if (!Objects.equals(tariffId, that.tariffId))
            return false;
        if (!Objects.equals(newPrice, that.newPrice))
            return false;
        return Objects.equals(status, that.status);
    }

    @Override
    public int hashCode(){
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (promotionName != null ? promotionName.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (tariffName != null ? tariffName.hashCode() : 0);
        result = 31 * result + (tariffId != null ? tariffId.hashCode() : 0);
        result = 31 * result + (newPrice != null ? newPrice.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString(){
        return "PromotionDto{" +
                "id=" + id +
                ", promotionName='" + promotionName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", description='" + description + '\'' +
                ", tariffName='" + tariffName + '\'' +
                ", tariffId=" + tariffId +
                ", newPrice=" + newPrice +
                ", status=" + status +
                '}';
    }
}
