package ru.nsu.plodushcheva;

public class SubjectInfo {
    private String subjectName;
    private final ExamType examType;
    private final int examGrade;

    public enum ExamType {Exam, DifCredit, Credit}
    private final int finalSemesterNumber;


    public SubjectInfo(String subjectName, ExamType examType, int examGrade, int finalSemesterNumber) {
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

    public int getFinalSemesterNumber(){
        return finalSemesterNumber;
    }

}
