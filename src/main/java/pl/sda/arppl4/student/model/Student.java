package pl.sda.arppl4.student.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private Set<Ocena> oceny;

    public Student(String imie, String nazwisko, String indexNumber, LocalDate dataUrodzenia) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.indexNumber = indexNumber;
        this.dataUrodzenia = dataUrodzenia;
    }
}
