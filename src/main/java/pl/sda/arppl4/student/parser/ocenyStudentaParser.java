package pl.sda.arppl4.student.parser;

import pl.sda.arppl4.student.dao.GenericDao;
import pl.sda.arppl4.student.model.Ocena;
import pl.sda.arppl4.student.model.Student;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ocenyStudentaParser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final Scanner scanner;
    private final GenericDao<Student> daoStudent;
    private final GenericDao<Ocena> daoOcena;

    public ocenyStudentaParser(Scanner scanner, GenericDao<Student> daoStudent, GenericDao<Ocena> daoOcena) {
        this.scanner = scanner;
        this.daoStudent = daoStudent;
        this.daoOcena = daoOcena;
    }

    public void obslugaKomend() {
        String komenda = null;
        do {
            System.out.println("Wybierz działanie: DodajStudenta/UsunStudenta/EdytujStudenta/ListaStudentow/DodajOcene/UsunOcene/PoprawOcene/ObliczSrednia lub Quit");
            komenda = scanner.next();
            if (komenda.equalsIgnoreCase("DodajStudenta")) {
                obsluzDodajStudenta();
            } else if (komenda.equalsIgnoreCase("UsunStudenta")) {
                obsluzUsunStudenta();
            } else if (komenda.equalsIgnoreCase("ListaStudentow")) {
                obsluzListaStudentow();
            } else if (komenda.equalsIgnoreCase("EdytujStudenta")) {
                obsluzEdytujStudenta();
            }


        } while (!komenda.equalsIgnoreCase("Quit"));
    }

    private void obsluzEdytujStudenta() {
        System.out.println("Podaj id Studenta do edycji");
        Long podaneId = scanner.nextLong();
        Optional<Student> szukanyStudent = daoStudent.znajdzPoId(podaneId, Student.class);

        if (szukanyStudent.isPresent()) {
            Student student = szukanyStudent.get();

            String parametrDoZmiany = null;
            do {
            System.out.println("Wskaż jakie dane studenta chcesz zmodyfikować: imie/nazwisko");
            parametrDoZmiany = scanner.next();

            if (parametrDoZmiany.equalsIgnoreCase("imie")) {
                System.out.println("Podaj imie studenta po zmianie");
                String noweImie = scanner.next();
                student.setImie(noweImie);
            } else if (parametrDoZmiany.equalsIgnoreCase("nazwisko")) {
                    System.out.println("Podaj nazwsoko studenta po zmianie");
                    String noweNazwisko = scanner.next();
                    student.setNazwisko(noweNazwisko);
            } else {
                    System.out.println("Wskazano nie istniejącą opcje");
                }
            } while (parametrDoZmiany==null);
            daoStudent.aktualizuj(student);
        } else
        System.out.println("Wskazany student nie istnieje");
    }


    private void obsluzListaStudentow() {
        List<Student> listaStudentow = daoStudent.list(Student.class);
        for (Student student : listaStudentow) {
            System.out.println(student);
        }
    }

    private void obsluzUsunStudenta() {
        System.out.println("Podaj id Studenta do usunięcia");
        Long podaneId = scanner.nextLong();
        Optional<Student> szukanyStudent = daoStudent.znajdzPoId(podaneId, Student.class);

        if (szukanyStudent.isPresent()) {
            Student student = szukanyStudent.get();
            daoStudent.usun(student);
            System.out.println("Wskazany student został usunięty");
        } else {
            System.out.println("Wskazany student nie został znaleziony");
        }
    }

    private void obsluzDodajStudenta() {
        System.out.println("Podaj imie studenta");
        String imie = scanner.next();

        System.out.println("Podaj nazwisko studenta");
        String nazwisko = scanner.next();

        System.out.println("Wprowadź nr indeksu");
        String indeks = scanner.next();

        LocalDate dataUrodzenia = zaladujDateUrodzenia();
        
        Student student = new Student (imie,nazwisko,indeks,dataUrodzenia);
        daoStudent.dodaj(student);
    }

    private LocalDate zaladujDateUrodzenia() {
        LocalDate dataUrodzenia = null;
        do {
            try {
                System.out.println("Podaj datę urodzenia studenta w formacie: yyyy-MM-dd");
                String dataUrodzeniaString = scanner.next();

                dataUrodzenia = LocalDate.parse(dataUrodzeniaString, FORMATTER);

            } catch (DateTimeException dt) {
                dataUrodzenia = null;
                System.err.println("Wskazana data urodzenia jest nieprawidłowa. Wprowadź datę w formacie: yyyy-MM-dd");
            }
        } while (dataUrodzenia == null);
        return dataUrodzenia;
    }
}

