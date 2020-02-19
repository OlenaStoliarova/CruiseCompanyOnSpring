package ua.cruise.company.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name="extras", uniqueConstraints={@UniqueConstraint(columnNames={"nameEn"})})
public class Extra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String nameEn;
    @Column(nullable = false)
    private String nameUkr;

    public Extra(String nameEn, String nameUkr) {
        this.nameEn = nameEn;
        this.nameUkr = nameUkr;
    }
}
