package ua.cruise.company.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CruiseDTO {
    private Long id;
    private LocalDate startingDate;
    private LocalDate finishingDate;
    private ShipDTO ship;
    private BigDecimal price;
    private int vacancies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public LocalDate getFinishingDate() {
        return finishingDate;
    }

    public void setFinishingDate(LocalDate finishingDate) {
        this.finishingDate = finishingDate;
    }

    public ShipDTO getShip() {
        return ship;
    }

    public void setShip(ShipDTO ship) {
        this.ship = ship;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getVacancies() {
        return vacancies;
    }

    public void setVacancies(int vacancies) {
        this.vacancies = vacancies;
    }

    @Override
    public String toString() {
        return "CruiseDTO{" +
                "id=" + id +
                ", startingDate=" + startingDate +
                ", finishingDate=" + finishingDate +
                ", ship=" + ship +
                ", price=" + price +
                ", vacancies=" + vacancies +
                '}';
    }
}
