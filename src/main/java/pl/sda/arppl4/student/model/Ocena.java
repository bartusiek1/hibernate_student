package pl.sda.arppl4.student.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor

public class Ocena {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private LocalDateTime wystawienieOceny;
    private LocalDateTime poprawkaOceny;
    private Double ocena;

    @Enumerated(EnumType.STRING)
    private Przedmiot przedmiot;

    @ManyToOne
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Student student;

    public Ocena(LocalDateTime wystawienieOceny, Double ocena, Przedmiot przedmiot, Student student) {
        this.wystawienieOceny = wystawienieOceny;
        this.ocena = ocena;
        this.przedmiot = przedmiot;
        this.student = student;
    }
}
