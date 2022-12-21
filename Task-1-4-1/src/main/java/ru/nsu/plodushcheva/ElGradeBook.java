package ru.nsu.plodushcheva;

import java.util.ArrayList;
import java.util.List;

import static ru.nsu.plodushcheva.GradeInfo.ExamType.*;

public class ElGradeBook {
    private String studentName;
    private final int bookID;
    private int qualifyingWork;
    private final SemesterInfo[] semesters = new SemesterInfo[8];
    private int currentSemester;
    private static final ArrayList<Integer> idsGlobal = new ArrayList<>();


    public ElGradeBook(String studentName, int bookID, int qualifyingWork, int currentSemester)
            throws IllegalAccessException {
        if (!idsGlobal.contains(bookID)){
            idsGlobal.add(bookID);
            this.studentName = studentName;
            this.bookID = bookID;
            this.qualifyingWork = qualifyingWork;
            this.currentSemester = currentSemester;
            for (int i = 0; i < 8; i++) {
                semesters[i] = new SemesterInfo();
            }

            //gpa = 0;
            //numberForGpa = 0;
            //sumForGpa = 0;
            //countOfThree = 0;
        } else {
            throw new IllegalAccessException("This book already exist");
        }
    }

    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName){ this.studentName = studentName; }
    public int getCurrentSemester(){
        return currentSemester;
    }
    public void setCurrentSemester(int currentSemester) {this.currentSemester = currentSemester; }
    public void setQualifyingWork(int qualifyingWork) {this.qualifyingWork = qualifyingWork; }
    public int getQualifyingWork(){
        return qualifyingWork;
    }

    public int getBookID() {
        return bookID;
    }

    public void addGrades(int semester, int grade,
                          GradeInfo.ExamType type, int fsn, String subject) {

        int temp = 2;

        if (type == Exam) {
            temp = 1;
        }
        if (type == Credit){
            temp = 3;
        }
        Integer[] grades = new Integer[] {grade, fsn, temp};
        semesters[semester - 1].addGrade(subject,grades);

    }

    public ArrayList<Integer> getSemesterGrades(int semester) {

        return new ArrayList<>(semesters[semester].getValues());
    }

    public ArrayList<Integer> getAllGrades() {
        ArrayList<Integer> grades = new ArrayList<>();
        for (int i = 0; i < currentSemester; i++) {
            grades.addAll(semesters[i].getValues());
        }
        return grades;
    }

    public List<Integer> getAllFinalGrades() {
        List<Integer> grades = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        for (int i = currentSemester - 1; i >= 0; i--) {
            List<String> names = semesters[i].getNames();
            for (String name : names) {
                if (!temp.contains(name)) {
                    Integer grade = semesters[i].getGrade(name);
                    grades.add(grade);
                    temp.add(name);
                }
            }
        }
        return grades;
    }

    public double gpa(){
        List<Integer> allGrades = getAllGrades();
        int sumForGpa = 0;
        for (Integer allGrade : allGrades) {
            sumForGpa += allGrade;
        }
        return (double) sumForGpa/allGrades.size();
    }


    public boolean redDiploma(){
        int countOfFive = 0;
        int countOfThree = 0;
        List<Integer> finalGrades =  getAllFinalGrades();
        for (Integer finalGrade : finalGrades) {
            if (finalGrade == 5) {
                countOfFive++;
            }
            if (finalGrade == 3) {
                countOfThree++;
            }
        }

        return (double) countOfFive/finalGrades.size() >= 0.75 &&
                (qualifyingWork == 5) && countOfThree == 0;
    }

    public boolean scholarship(){
        List<Integer> grades = new ArrayList<>(semesters[currentSemester - 2].getValues());

        for (Integer grade : grades) {
            if (grade < 4) {
                return false;
            }
        }
        return true;
    }
    public boolean upperScholarship(){
        List<Integer> grades = semesters[currentSemester-1].getValues();

        int countOfGood = 0;
        for (Integer grade : grades) {
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

