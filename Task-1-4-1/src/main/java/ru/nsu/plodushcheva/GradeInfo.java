package ru.nsu.plodushcheva;

/**
 * информация об оценке
 * тип оценивания Экзамен, Дифференцированный зачет, Зачет
 * оценка для Экзамена или Дифференцированного зачета
 * зачет/незачет для Зачета
 */
public class GradeInfo {
    private Integer grade;
    private boolean credit;
    private final ExamType gradeType;
    public enum ExamType {
        Exam,
        DifCredit,
        Credit
    }

    /**
     * @param grade оценка для Экзамена и Дифф.зачета
     * @param gradeType тип оценивания
     * @throws Exception если тип оценивания Зачет
     */
    public GradeInfo (Integer grade, ExamType gradeType) throws Exception {
        if (gradeType == ExamType.Exam || gradeType == ExamType.DifCredit){
            this.grade = grade;
            this.gradeType = gradeType;
        } else throw new Exception("Exam type and the grade are not the same");
    }

    /**
     * @param credit результат
     * @param gradeType тип оценивания Зачет
     * @throws Exception если тип оценивания Экзамен или Дифф.зачет
     */
    public GradeInfo (Boolean credit, ExamType gradeType) throws Exception {
        if (gradeType == ExamType.Credit) {
            this.credit = credit;
            this.gradeType = gradeType;
        } else throw new Exception("Exam type and the grade are not the same");

    }

    /**
     * @return оценка за экзамен/дифф.зачет
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * @return результат зачета
     */
    public Boolean getCredit() {
        return credit;
    }

    /**
     * @return тип оценивания
     */
    public ExamType getGradeType() {
        return gradeType;
    }
}
