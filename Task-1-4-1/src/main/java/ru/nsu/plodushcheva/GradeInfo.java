package ru.nsu.plodushcheva;

/**
 * информация об оценке
 * тип оценивания Экзамен, Дифференцированный зачет, Зачет
 * оценка для Экзамена или Дифференцированного зачета
 * зачет/незачет для Зачета.
 */
public class GradeInfo {
    private int grade;
    private boolean credit;
    private final ExamType gradeType;

    /**
     * перечисление типов итогового оценивания по предмету.
     */
    public enum ExamType {
        Exam,
        DifCredit,
        Credit
    }

    /**
     * конструктор оценки.
     *
     * @param grade оценка для Экзамена и Дифф.зачета
     * @param gradeType тип оценивания
     * @throws Exception если тип оценивания Зачет
     */
    public GradeInfo(int grade, ExamType gradeType) throws Exception {
        if (gradeType == ExamType.Exam || gradeType == ExamType.DifCredit) {
            this.grade = grade;
            this.gradeType = gradeType;
        } else {
            throw new Exception("Exam type and the grade are not the same");
        }
    }

    /**
     * конструктор зачета.
     *
     * @param credit результат
     * @param gradeType тип оценивания Зачет
     * @throws Exception если тип оценивания Экзамен или Дифф.зачет
     */
    public GradeInfo(boolean credit, ExamType gradeType) throws Exception {
        if (gradeType == ExamType.Credit) {
            this.credit = credit;
            this.gradeType = gradeType;
        } else {
            throw new Exception("Exam type and the grade are not the same");
        }

    }

    /**
     * геттер оценки.
     *
     * @return оценка за экзамен/дифф.зачет
     */
    public int getGrade() {
        return grade;
    }

    /**
     * геттер зачета.
     *
     * @return результат зачета
     */
    public boolean getCredit() {
        return credit;
    }

    /**
     * геттер типа оценивания.
     *
     * @return тип оценивания
     */
    public ExamType getGradeType() {
        return gradeType;
    }
}
