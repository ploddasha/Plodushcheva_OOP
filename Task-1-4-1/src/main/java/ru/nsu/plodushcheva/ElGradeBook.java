package ru.nsu.plodushcheva;

import java.util.ArrayList;

public class ElGradeBook {
    private String studentName;
    private int bookID;
    private int idGlobal;
    private int currentSemester;
    private ArrayList<Integer> idsGlobal = new ArrayList<>();

    private int qualifyingWork;
    private int diplomaGrade;
    private Semester[] semesters = new Semester[8];

    public ElGradeBook(String studentName, int bookID, int qualifyingWork, int currentSemester){
        if (!idsGlobal.contains(bookID)){
            idsGlobal.add(bookID);
            this.bookID = bookID;
            this.studentName = studentName;
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

    public int gpa(){
        ArrayList<Integer> grades = getAllGrades();
        double gpa = 0;
        for (int grade : grades) {
            gpa += grade;
        }
        gpa /= grades.size();
        return (int) gpa;
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

