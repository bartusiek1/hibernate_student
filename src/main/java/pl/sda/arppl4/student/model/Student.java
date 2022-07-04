package pl.sda.arppl4.student.model;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    private String imie;
    private String nazwisko;
    private String indexNumber;
    private LocalDate dataUrodzenia;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    @EqualsAndHashCode.Exclude
    private Set<Ocena> oceny;

    public Student(String imie, String nazwisko, String indexNumber, LocalDate dataUrodzenia) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.indexNumber = indexNumber;
        this.dataUrodzenia = dataUrodzenia;
    }
}

