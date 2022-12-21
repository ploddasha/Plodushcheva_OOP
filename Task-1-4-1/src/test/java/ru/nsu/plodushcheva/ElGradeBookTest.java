package ru.nsu.plodushcheva;

import static ru.nsu.plodushcheva.GradeInfo.ExamType.Credit;
import static ru.nsu.plodushcheva.GradeInfo.ExamType.DifCredit;
import static ru.nsu.plodushcheva.GradeInfo.ExamType.Exam;

import org.junit.jupiter.api.Assertions;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class ElGradeBookTest {
    @Test
    public void coolStudentTest() throws IllegalAccessException {
        ElGradeBook gradeBook = new ElGradeBook("CoolGroupMate", 210699, 5, 3);

        gradeBook.addGrades(1, 5, Exam, 2, "Algebra");
        gradeBook.addGrades(1, 5, Exam, 2, "Discrete math");
        gradeBook.addGrades(1, 5, GradeInfo.ExamType.DifCredit, 2, "Declarative programming");
        gradeBook.addGrades(1, 5, GradeInfo.ExamType.DifCredit, 1, "History");
        gradeBook.addGrades(1, 5, GradeInfo.ExamType.DifCredit, 1, "Basics of speech");
        gradeBook.addGrades(1, 5, GradeInfo.ExamType.DifCredit, 2, "Imperative programming");

        gradeBook.addGrades(2, 5, Exam, 2, "Algebra");
        gradeBook.addGrades(2, 5, Exam, 2, "Discrete math");
        gradeBook.addGrades(2, 5, GradeInfo.ExamType.DifCredit, 2, "Declarative programming");
        gradeBook.addGrades(2, 5, Exam, 2, "Imperative programming");
        gradeBook.addGrades(2, 5, GradeInfo.ExamType.DifCredit, 2, "English");
        gradeBook.addGrades(2, 5, GradeInfo.ExamType.DifCredit, 2, "Digital platforms");

        double gpa = gradeBook.gpa();
        Assertions.assertEquals(5, gpa);

        boolean a = gradeBook.redDiploma();
        Assertions.assertTrue(a);

        boolean b = gradeBook.scholarship();
        Assertions.assertTrue(b);
    }

    @Test
    public void okayStudentTest() throws IllegalAccessException {
        ElGradeBook gradeBook = new ElGradeBook("NotAsCoolGroupMate", 210698, 5, 3);

        gradeBook.addGrades(1, 3, Exam, 2, "Algebra");
        gradeBook.addGrades(1, 3, Exam, 2, "Discrete math");
        gradeBook.addGrades(1, 4, GradeInfo.ExamType.DifCredit, 2, "Declarative programming");
        gradeBook.addGrades(1, 4, GradeInfo.ExamType.DifCredit, 1, "History");
        gradeBook.addGrades(1, 4, GradeInfo.ExamType.DifCredit, 1, "Basics of speech");
        gradeBook.addGrades(1, 3, GradeInfo.ExamType.DifCredit, 2, "Imperative programming");

        gradeBook.addGrades(2, 4, Exam, 2, "Algebra");
        gradeBook.addGrades(2, 4, Exam, 2, "Discrete math");
        gradeBook.addGrades(2, 4, GradeInfo.ExamType.DifCredit, 2, "Declarative programming");
        gradeBook.addGrades(2, 4, Exam, 2, "Imperative programming");
        gradeBook.addGrades(2, 5, GradeInfo.ExamType.DifCredit, 2, "English");
        gradeBook.addGrades(2, 4, GradeInfo.ExamType.DifCredit, 2, "Digital platforms");

        double gpa = gradeBook.gpa();
        Assertions.assertEquals(3.83, gpa, 0.01);

        boolean a = gradeBook.redDiploma();
        Assertions.assertFalse(a);

        boolean b = gradeBook.scholarship();
        Assertions.assertTrue(b);
    }

    @Test
    public void badStudentTest() throws IllegalAccessException {
        ElGradeBook gradeBook = new ElGradeBook("NotCoolGroupMate", 210697, 3, 3);

        gradeBook.addGrades(1, 3, Exam, 2, "Algebra");
        gradeBook.addGrades(1, 3, Exam, 2, "Discrete math");
        gradeBook.addGrades(1, 3, GradeInfo.ExamType.DifCredit, 2, "Declarative programming");
        gradeBook.addGrades(1, 4, GradeInfo.ExamType.DifCredit, 1, "History");
        gradeBook.addGrades(1, 4, GradeInfo.ExamType.DifCredit, 1, "Basics of speech");
        gradeBook.addGrades(1, 3, GradeInfo.ExamType.DifCredit, 2, "Imperative programming");

        gradeBook.addGrades(2, 3, Exam, 2, "Algebra");
        gradeBook.addGrades(2, 4, Exam, 2, "Discrete math");
        gradeBook.addGrades(2, 3, GradeInfo.ExamType.DifCredit, 2, "Declarative programming");
        gradeBook.addGrades(2, 4, Exam, 2, "Imperative programming");
        gradeBook.addGrades(2, 3, GradeInfo.ExamType.DifCredit, 2, "English");
        gradeBook.addGrades(2, 4, GradeInfo.ExamType.DifCredit, 2, "Digital platforms");

        double gpa = gradeBook.gpa();
        Assertions.assertEquals(3.42, gpa, 0.01);

        boolean a = gradeBook.redDiploma();
        Assertions.assertFalse(a);

        boolean b = gradeBook.scholarship();
        Assertions.assertFalse(b);
    }

    @Test
    public void studentBookTest() throws IllegalAccessException, IOException {

        File file = new File("./src/test/resources/Book1.txt");
        Reader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        String[] array = line.split(" ");
        ElGradeBook gradeBook = new ElGradeBook(array[0], Integer.parseInt(array[1]), 
                Integer.parseInt(array[2]), Integer.parseInt(array[3]));

        line = reader.readLine();
        while (line != null) {
            array = line.split(" ");
            GradeInfo.ExamType temp = null;
            if (Objects.equals(array[2], "Exam")) {
                temp = Exam;
            }
            if (Objects.equals(array[2], "DifCredit")) {
                temp = DifCredit;
            }
            if (Objects.equals(array[2], "Credit")) {
                temp = Credit;
            }
            gradeBook.addGrades(Integer.parseInt(array[0]), Integer.parseInt(array[1]), 
                    temp, Integer.parseInt(array[3]), array[4]);
            line = reader.readLine();
        }

        double gpa = gradeBook.gpa();
        Assertions.assertEquals(3.42, gpa, 0.01);

        boolean a = gradeBook.redDiploma();
        Assertions.assertFalse(a);

        boolean b = gradeBook.scholarship();
        Assertions.assertFalse(b);
    }
}