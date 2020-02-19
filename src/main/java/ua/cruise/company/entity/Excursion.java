package ua.cruise.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor

@Entity
@Table(name="excursion")
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nameEn;
    @Column(nullable = false)
    private String nameUkr;

    @Column(length = 65536, nullable = false)
    private String descriptionEn;
    @Column(length = 65536, nullable = false)
    private String descriptionUkr;

    @Column(nullable = false)
    private Long approximateDurationHr;

    @Column(nullable = false)
    private BigDecimal priceUSD;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "seaport_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Seaport seaport;

    public Excursion() {
        seaport = new Seaport();
    }
}
