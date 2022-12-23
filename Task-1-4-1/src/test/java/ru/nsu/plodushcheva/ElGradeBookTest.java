package ru.nsu.plodushcheva;

import static ru.nsu.plodushcheva.GradeInfo.ExamType.Credit;
import static ru.nsu.plodushcheva.GradeInfo.ExamType.DifCredit;
import static ru.nsu.plodushcheva.GradeInfo.ExamType.Exam;

import org.junit.jupiter.api.Assertions;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class ElGradeBookTest {

    @Test
    public void studentTest() {
        ElGradeBook gradeBook = new ElGradeBook("CoolGroupMate", 5, 3);

        Assertions.assertEquals("CoolGroupMate", gradeBook.getStudentName());
        Assertions.assertEquals(5, gradeBook.getQualifyingWork());
        Assertions.assertEquals(3, gradeBook.getCurrentSemester());

        gradeBook.setStudentName("AnotherName");
        gradeBook.setQualifyingWork(4);
        gradeBook.setCurrentSemester(4);

        Assertions.assertEquals("AnotherName", gradeBook.getStudentName());
        Assertions.assertEquals(4, gradeBook.getQualifyingWork());
        Assertions.assertEquals(4, gradeBook.getCurrentSemester());
    }

        @Test
    public void coolStudentTest() throws Exception {
        ElGradeBook gradeBook = new ElGradeBook("CoolGroupMate", 5, 3);

        gradeBook.addGrades(1, 5, Exam, "Algebra");
        gradeBook.addGrades(1, 5, Exam, "Discrete math");
        gradeBook.addGrades(1, 5, GradeInfo.ExamType.DifCredit, "Declarative programming");
        gradeBook.addGrades(1, 5, GradeInfo.ExamType.DifCredit, "History");
        gradeBook.addGrades(1, 5, GradeInfo.ExamType.DifCredit, "Basics of speech");
        gradeBook.addGrades(1, 5, GradeInfo.ExamType.DifCredit, "Imperative programming");
        gradeBook.addGrades(1, true, GradeInfo.ExamType.Credit, "Physical education");

        gradeBook.addGrades(2, 5, Exam, "Algebra");
        gradeBook.addGrades(2, 5, Exam, "Discrete math");
        gradeBook.addGrades(2, 5, GradeInfo.ExamType.DifCredit, "Declarative programming");
        gradeBook.addGrades(2, 5, Exam, "Imperative programming");
        gradeBook.addGrades(2, 5, GradeInfo.ExamType.DifCredit, "English");
        gradeBook.addGrades(2, 5, GradeInfo.ExamType.DifCredit, "Digital platforms");
        gradeBook.addGrades(2, true, GradeInfo.ExamType.Credit, "Physical education");

        double gpa = gradeBook.gpa();
        Assertions.assertEquals(5, gpa);

        boolean a = gradeBook.redDiploma();
        Assertions.assertTrue(a);

        boolean b = gradeBook.scholarship();
        Assertions.assertTrue(b);
    }

    @Test
    public void okayStudentTest() throws Exception {
        ElGradeBook gradeBook = new ElGradeBook("NotAsCoolGroupMate",  5, 3);

        gradeBook.addGrades(1, 3, GradeInfo.ExamType.Exam, "Algebra");
        gradeBook.addGrades(1, 3, GradeInfo.ExamType.Exam, "Discrete math");
        gradeBook.addGrades(1, 4, GradeInfo.ExamType.DifCredit, "Declarative programming");
        gradeBook.addGrades(1, 4, GradeInfo.ExamType.DifCredit, "History");
        gradeBook.addGrades(1, 4, GradeInfo.ExamType.DifCredit, "Basics of speech");
        gradeBook.addGrades(1, 3, GradeInfo.ExamType.DifCredit, "Imperative programming");
        gradeBook.addGrades(1, true, GradeInfo.ExamType.Credit, "Physical education");

        gradeBook.addGrades(2, 4, GradeInfo.ExamType.Exam, "Algebra");
        gradeBook.addGrades(2, 4, GradeInfo.ExamType.Exam, "Discrete math");
        gradeBook.addGrades(2, 4, GradeInfo.ExamType.DifCredit, "Declarative programming");
        gradeBook.addGrades(2, 4, GradeInfo.ExamType.Exam, "Imperative programming");
        gradeBook.addGrades(2, 5, GradeInfo.ExamType.DifCredit, "English");
        gradeBook.addGrades(2, 4, GradeInfo.ExamType.DifCredit, "Digital platforms");
        gradeBook.addGrades(2, true, GradeInfo.ExamType.Credit, "Physical education");

        double gpa = gradeBook.gpa();
        Assertions.assertEquals(3.83, gpa, 0.01);

        boolean a = gradeBook.redDiploma();
        Assertions.assertFalse(a);

        boolean b = gradeBook.scholarship();
        Assertions.assertTrue(b);

        boolean d = gradeBook.upperScholarship();
        Assertions.assertFalse(d);


        gradeBook.addGrades(1, false, GradeInfo.ExamType.Credit, "Physical education");
        boolean c = gradeBook.scholarship();
        Assertions.assertFalse(c);

    }

    @Test
    public void badStudentTest() throws Exception {
        ElGradeBook gradeBook = new ElGradeBook("NotCoolGroupMate",  3, 3);

        gradeBook.addGrades(1, 3, Exam, "Algebra");
        gradeBook.addGrades(1, 3, Exam, "Discrete math");
        gradeBook.addGrades(1, 3, GradeInfo.ExamType.DifCredit, "Declarative programming");
        gradeBook.addGrades(1, 4, GradeInfo.ExamType.DifCredit, "History");
        gradeBook.addGrades(1, 4, GradeInfo.ExamType.DifCredit, "Basics of speech");
        gradeBook.addGrades(1, 3, GradeInfo.ExamType.DifCredit, "Imperative programming");
        gradeBook.addGrades(1, true, GradeInfo.ExamType.Credit, "Physical education");

        gradeBook.addGrades(2, 3, Exam, "Algebra");
        gradeBook.addGrades(2, 4, Exam, "Discrete math");
        gradeBook.addGrades(2, 3, GradeInfo.ExamType.DifCredit, "Declarative programming");
        gradeBook.addGrades(2, 4, Exam, "Imperative programming");
        gradeBook.addGrades(2, 3, GradeInfo.ExamType.DifCredit, "English");
        gradeBook.addGrades(2, 4, GradeInfo.ExamType.DifCredit, "Digital platforms");
        gradeBook.addGrades(2, true, GradeInfo.ExamType.Credit, "Physical education");

        List<Integer> semesterGrades = gradeBook.getSemesterGrades(2);
        List<Integer> expected = new ArrayList<>();
        expected.add(3);
        expected.add(4);
        expected.add(3);
        expected.add(4);
        expected.add(3);
        expected.add(4);
        Assertions.assertEquals(expected, semesterGrades);

        double gpa = gradeBook.gpa();
        Assertions.assertEquals(3.42, gpa, 0.01);

        boolean a = gradeBook.redDiploma();
        Assertions.assertFalse(a);

        boolean b = gradeBook.scholarship();
        Assertions.assertFalse(b);
    }

    @Test
    public void studentBookTest() throws Exception {

        File file = new File("./src/test/resources/Book1.txt");
        Reader fr = new FileReader(file);
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        String[] array = line.split(" ");
        ElGradeBook gradeBook = new ElGradeBook(array[0], Integer.parseInt(array[1]), 
                Integer.parseInt(array[2]));

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
                    temp, array[3]);
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