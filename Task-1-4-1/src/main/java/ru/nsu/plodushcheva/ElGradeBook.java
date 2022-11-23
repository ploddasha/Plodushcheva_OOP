package ru.nsu.plodushcheva;

import java.util.ArrayList;

public class ElGradeBook {
    private String studentName;
    private int bookID;
    private int idGlobal;
    private ArrayList<Integer> idsGlobal;

    private int qualifyingWork;
    private int diplomaGrade;
    private Semester[] semesters = new Semester[8];

    public void ElGradeBook(String studentName,int bookID,int qualifyingWork){
        this.studentName = studentName;
        if (!idsGlobal.contains(bookID)){
            idsGlobal.add(bookID);
            this.bookID = bookID;
        }
        this.qualifyingWork = qualifyingWork;
    }

    public String getStudentName() {
        return studentName;
    }

    public int gpa(String studentName){
        return bookID;
    }

    public boolean redDiploma(String studentName){
        boolean y = true;
        return y;
    }

    public boolean scholarship(String studentName){
        boolean y = true;
        if ()
        return y;
    }
}

