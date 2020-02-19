package ua.cruise.company.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="ship", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private String routeNameEn;
    @Column(nullable = false)
    private String routeNameUkr;

    @Column(nullable = false)
    private int oneTripDurationDays;

    @ManyToMany
    @JoinTable(name = "ship_route_seaport",
            joinColumns = @JoinColumn(name = "ship_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "seaport_id", referencedColumnName = "id"))
    private Set<Seaport> visitingPorts = new HashSet<>();


    @ManyToMany
    @JoinTable(name = "ship_extras",
            joinColumns = @JoinColumn(name = "ship_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "extra_id", referencedColumnName = "id"))
    private Set<Extra> extras = new HashSet<>();
}
