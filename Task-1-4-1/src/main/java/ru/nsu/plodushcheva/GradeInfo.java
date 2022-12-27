package ru.nsu.plodushcheva;

/**
 * assessment information
 * type of assessment Exam, Differential Credit, Pass
 * grade for Exam or Differential credit
 * pass/fail for Credit.
 */
public class GradeInfo {
    private int grade;
    private boolean credit;
    private final ExamType gradeType;

    /**
     * listing the types of summative assessment in the subject.
     */
    public enum ExamType {
        Exam,
        DifCredit,
        Credit
    }

    /**
     * assessment constructor.
     *
     * @param grade Assessment for Examination and Differential Credit
     * @param gradeType type of assessment
     * @throws Exception if the type of assessment is Credit
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
     * credit constructor.
     *
     * @param credit result
     * @param gradeType type of assessment Credit
     * @throws Exception if the type of assessment is Examination or Differentiated Credit
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
     * getter assessment.
     *
     * @return Examination/diff. score
     */
    public int getGrade() {
        return grade;
    }

    /**
     * the getter of the credit.
     *
     * @return result
     */
    public boolean getCredit() {
        return credit;
    }

    /**
     * getter type of evaluation.
     *
     * @return type of evaluation.
     */
    public ExamType getGradeType() {
        return gradeType;
    }
}
