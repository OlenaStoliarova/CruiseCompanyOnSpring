package ua.cruise.company.dto;

import java.math.BigDecimal;

public class ExcursionDTO {
    private Long id;
    private String name;
    private String description;
    private Long approximateDurationHr;
    private BigDecimal price;
    private SeaportDTO seaport;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getApproximateDurationHr() {
        return approximateDurationHr;
    }

    public void setApproximateDurationHr(Long approximateDurationHr) {
        this.approximateDurationHr = approximateDurationHr;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public SeaportDTO getSeaport() {
        return seaport;
    }

    public void setSeaport(SeaportDTO seaport) {
        this.seaport = seaport;
    }

    @Override
    public String toString() {
        return "ExcursionDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", approximateDurationHr=" + approximateDurationHr +
                ", price=" + price +
                ", seaport=" + seaport +
                '}';
    }
}
