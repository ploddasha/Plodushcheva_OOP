package ru.nsu.plodushcheva;

import java.util.ArrayList;
import java.util.Collection;

import static ru.nsu.plodushcheva.Subject.ExamType.DifCredit;
import static ru.nsu.plodushcheva.Subject.ExamType.Exam;

public class ElGradeBook {
    private String studentName;
    private int bookID;
    private int currentSemester;
    private static ArrayList<Integer> idsGlobal = new ArrayList<>();
    private double gpa;
    private int sumForGpa;
    private int numberForGpa;
    private int countOfThree;
    private int countOfFive;

    private int qualifyingWork;
    private Semester[] semesters = new Semester[8];

    public ElGradeBook(String studentName, int bookID, int qualifyingWork, int currentSemester){
        if (!idsGlobal.contains(bookID)){
            idsGlobal.add(bookID);
            gpa = 0;
            numberForGpa = 0;
            sumForGpa = 0;
            countOfThree = 0;
            this.studentName = studentName;
            this.bookID = bookID;
            this.qualifyingWork = qualifyingWork;
            this.currentSemester = currentSemester;
            for (int i = 0; i < 8; i++) {
                semesters[i] = new Semester();
            }
        }
    }

    public String getStudentName() {
        return studentName;
    }
    public int getCurrentSemester(){
        return currentSemester;
    }
    public int getQualifyingWork(){
        return qualifyingWork;
    }

    public void addGrades(int semester, int grade, String subject) {
        semesters[semester].addGrade(subject, grade);
    }
    public void addGrades2(int semester, int grade, Subject.ExamType type, int fsn, String subject) {
        Subject a = new Subject(subject, type, grade, fsn);
        semesters[semester - 1].addGrade2(subject, a);

        if ((type == Exam || type == DifCredit) && semester == fsn){
            numberForGpa++;
            sumForGpa += grade;
            if (grade == 3){
                countOfThree++;
            }
            if (grade == 5){

            }
        }


    }

    public ArrayList<Integer> getSemGrades(int semester) {
        ArrayList<Integer> integers = new ArrayList<>(semesters[semester].getGrades());
        return integers;
    }

    public ArrayList<Integer> getAllGrades() {
        ArrayList<Integer> grades = new ArrayList<>();
        for (int i = 0; i < currentSemester; i++) {
            grades.addAll(semesters[i].getGrades());
        }
        return grades;
    }

    public ArrayList<Integer> getAllFinalGrades() {
        ArrayList<Integer> grades = new ArrayList<>();
        for (int i = 0; i < currentSemester; i++) {
            Collection<Subject> n = semesters[i].getGrades2();
            grades.addAll(semesters[i].getGrades());
        }
        return grades;
    }

    public int getGpa(){
        ArrayList<Integer> grades = getAllGrades();
        double gpa = 0;
        for (int grade : grades) {
            gpa += grade;
        }
        gpa /= grades.size();
        return (int) gpa;
    }
    public double gpa2(){
        gpa = sumForGpa/numberForGpa;
        return gpa;
    }


    //bullish
    public boolean redDiploma(){
        ArrayList<Integer> grades = getAllGrades();
        int countOfFive = 0;
        for (int grade : grades) {
            if (5 == grade) {
                countOfFive++;
            }
        }
        return (double) countOfFive/grades.size() >= 0.75 && (qualifyingWork == 5);
    }

    public boolean redDiploma2(){
        //boolean a = true;

        return (double) countOfFive/numberForGpa >= 0.75 && (qualifyingWork == 5) && countOfThree == 0;
    }

    public boolean scholarship(){
        ArrayList<Integer> grades = new ArrayList<>(semesters[currentSemester].getGrades());

        int countOfGood = 0;
        for (int grade : grades) {
            if (grade < 4) {
                return false;
            }
            if (grade == 4) {
                countOfGood++;
            }

        }
        return countOfGood < 1;
    }
}

