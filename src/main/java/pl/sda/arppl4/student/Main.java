package pl.sda.arppl4.student;

import pl.sda.arppl4.student.dao.GenericDao;
import pl.sda.arppl4.student.model.Ocena;
import pl.sda.arppl4.student.model.Student;
import pl.sda.arppl4.student.parser.ocenyStudentaParser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        GenericDao<Student> studentGenericDao = new GenericDao();
        GenericDao<Ocena> ocenaGenericDao = new GenericDao<>();

        ocenyStudentaParser parser = new ocenyStudentaParser(scanner,studentGenericDao, ocenaGenericDao);
        parser.obslugaKomend();
    }
}
