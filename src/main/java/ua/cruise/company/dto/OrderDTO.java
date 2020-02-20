package ua.cruise.company.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OrderDTO {
    private Long id;
    private LocalDate creationDate;
    private UserDTO user;
    private CruiseDTO cruise;
    private int quantity;
    private BigDecimal totalPrice;
    private String status;
    private String addedExcursions;
    private String freeExtras;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public CruiseDTO getCruise() {
        return cruise;
    }

    public void setCruise(CruiseDTO cruise) {
        this.cruise = cruise;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddedExcursions() {
        return addedExcursions;
    }

    public void setAddedExcursions(String addedExcursions) {
        this.addedExcursions = addedExcursions;
    }

    public String getFreeExtras() {
        return freeExtras;
    }

    public void setFreeExtras(String freeExtras) {
        this.freeExtras = freeExtras;
    }

    @Override
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", creationDate=" + creationDate +
                ", user=" + user +
                ", cruise=" + cruise +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                ", status='" + status + '\'' +
                ", addedExcursions='" + addedExcursions + '\'' +
                ", freeExtras='" + freeExtras + '\'' +
                '}';
    }
}
