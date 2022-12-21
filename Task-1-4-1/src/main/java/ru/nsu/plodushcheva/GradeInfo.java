package ru.nsu.plodushcheva;

public class GradeInfo {
    // первое значение оценка
    // второе значение тип экзамена
    // 1 Exam  2 DiffCredit  3 Credit
    // третье значение номер семестра с последней оценкой

    public Integer[] grades;

    public enum ExamType {Exam, DifCredit, Credit}

    public GradeInfo (Integer[] grades) {
        this.grades = grades;
    }

    public void setGrade(Integer grade){
        grades[0] = grade;
    }
    public void setType(Integer type){
        grades[1] = type;
    }
    public void setSemesterNumber(Integer semesterNumber){
        grades[2] = semesterNumber;
    }
    public Integer[] getGrades(){
        return grades;
    }
    public Integer getGrade(){
        return grades[0];
    }
}
