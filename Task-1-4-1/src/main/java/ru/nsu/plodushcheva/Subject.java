package ru.nsu.plodushcheva;

public class Subject {
    private String subjectName;
    private ExamType examType;
    private int examGrade;

    public enum ExamType {Exam, DifCredit, Credit}
    private int finalSemesterNumber;


    public Subject (String subjectName, ExamType examType, int examGrade, int finalSemesterNumber) {
        this.subjectName = subjectName;
        this.examType = examType;
        this.examGrade = examGrade;
        this.finalSemesterNumber = finalSemesterNumber;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public ExamType getExamType() {
        return examType;
    }

    public int getExamGrade() {
        return examGrade;
    }

}
