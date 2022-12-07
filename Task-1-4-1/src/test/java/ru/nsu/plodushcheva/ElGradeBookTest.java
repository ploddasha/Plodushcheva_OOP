package ru.nsu.plodushcheva;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ElGradeBookTest {

    @Test
    public void badStudentTest(){
        ElGradeBook gradeBook = new ElGradeBook("Plodushcheva",210656,0,2);

        gradeBook.addGrades(1,3,"Algebra");
        gradeBook.addGrades(1,4,"Discrete math");
        gradeBook.addGrades(1,4,"Declarative programming");
        gradeBook.addGrades(1,4,"History");
        gradeBook.addGrades(1,5,"Basics of speech");
        gradeBook.addGrades(1,3,"Imperative programming");

        gradeBook.addGrades(2,3,"Algebra");
        gradeBook.addGrades(2,3,"Discrete math");
        gradeBook.addGrades(2,4,"Declarative programming");
        gradeBook.addGrades(2,3,"Imperative programming");
        gradeBook.addGrades(2,5,"English");
        gradeBook.addGrades(2,4,"Digital platforms");

        int gpa = gradeBook.getGpa();
        Assertions.assertEquals(3.0, gpa);

        boolean a = gradeBook.redDiploma();
        Assertions.assertEquals(false,a);

        boolean b = gradeBook.scholarship();
        Assertions.assertEquals(false,b);

    }

    @Test
    public void coolStudentTest(){
        ElGradeBook gradeBook = new ElGradeBook("Groopmate",210699,5,3);

        gradeBook.addGrades(1,5,"Algebra");
        gradeBook.addGrades(1,5,"Discrete math");
        gradeBook.addGrades(1,5,"Declarative programming");
        gradeBook.addGrades(1,5,"History");
        gradeBook.addGrades(1,5,"Basics of speech");
        gradeBook.addGrades(1,5,"Imperative programming");

        gradeBook.addGrades(2,5,"Algebra");
        gradeBook.addGrades(2,5,"Discrete math");
        gradeBook.addGrades(2,5,"Declarative programming");
        gradeBook.addGrades(2,5,"Imperative programming");
        gradeBook.addGrades(2,5,"English");
        gradeBook.addGrades(2,5,"Digital platforms");

        int gpa = gradeBook.getGpa();
        Assertions.assertEquals(5.0, gpa);

        boolean a = gradeBook.redDiploma();
        Assertions.assertEquals(true,a);

        boolean b = gradeBook.scholarship();
        Assertions.assertEquals(true,b);

    }
    @Test
    public void differentTest(){
        ElGradeBook gradeBook = new ElGradeBook("Groopmate",210699,5,3);

        gradeBook.addGrades2(1,5, Subject.ExamType.Exam,2, "Algebra");
        gradeBook.addGrades2(1,5, Subject.ExamType.Exam,2, "Discrete math");
        gradeBook.addGrades2(1,5, Subject.ExamType.DifCredit,2,"Declarative programming");
        gradeBook.addGrades2(1,5, Subject.ExamType.DifCredit,1,"History");
        gradeBook.addGrades2(1,5, Subject.ExamType.DifCredit,1, "Basics of speech");
        gradeBook.addGrades2(1,5, Subject.ExamType.DifCredit,2,"Imperative programming");

        gradeBook.addGrades2(2,5, Subject.ExamType.Exam,2,"Algebra");
        gradeBook.addGrades2(2,5, Subject.ExamType.Exam,2,"Discrete math");
        gradeBook.addGrades2(2,5, Subject.ExamType.DifCredit,2,"Declarative programming");
        gradeBook.addGrades2(2,5, Subject.ExamType.Exam,2,"Imperative programming");
        gradeBook.addGrades2(2,5, Subject.ExamType.DifCredit, 2 ,"English");
        gradeBook.addGrades2(2,5, Subject.ExamType.DifCredit,2,"Digital platforms");

        double gpa = gradeBook.gpa2();
        Assertions.assertEquals(5.00, gpa,0.01);

        boolean a = gradeBook.redDiploma();
        Assertions.assertEquals(true,a);

        boolean b = gradeBook.scholarship();
        //Assertions.assertEquals(true,b);


    }
}