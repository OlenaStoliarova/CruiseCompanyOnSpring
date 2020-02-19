package ua.cruise.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor

@Entity
@Table(name="cruise", uniqueConstraints={@UniqueConstraint(columnNames={"startingDate", "ship_id"})})
public class Cruise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private LocalDate startingDate;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ship_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ship ship;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    @Min(value=0)
    private int vacancies;

    public Cruise() {
        ship = new Ship();
    }
}
