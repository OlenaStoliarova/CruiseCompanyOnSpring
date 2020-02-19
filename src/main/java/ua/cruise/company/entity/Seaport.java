package ua.cruise.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table (name="seaport", uniqueConstraints={@UniqueConstraint(columnNames={"nameEn"})})
public class Seaport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nameEn;

    @Column(nullable = false)
    private String nameUkr;

    @Column(nullable = false)
    private String countryEn;

    @Column(nullable = false)
    private String countryUkr;

    /*
    @OneToMany(mappedBy="seaport")
    private Set<Excursion> excursions;*/
}
