package pl.sda.arppl4.student.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
