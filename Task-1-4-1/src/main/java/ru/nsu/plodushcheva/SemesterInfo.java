package ru.nsu.plodushcheva;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * semester information
 * list of pairs
 * course name - information about the grade for the course.
 */
public class SemesterInfo {
    Map<String, GradeInfo> semester = new HashMap<>();

    /**
     * for examination and differential credit
     * adding a semester grade for the course.
     *
     * @param subjectName name of the subject
     * @param grade a grade in the subject indicated
     * @param gradeType type of final assessment
     * @throws Exception mismatch between the assessment and the type of assessment
     */
    public void addGrade(String subjectName, int grade, GradeInfo.ExamType gradeType)
            throws Exception {
        GradeInfo grades = new GradeInfo(grade, gradeType);
        this.semester.put(subjectName, grades);
    }

    /**
     * for credit
     * adding a semester grade for the course.
     *
     * @param subjectName name of the subject
     * @param credit pass or fail
     * @param gradeType type of final assessment
     * @throws Exception mismatch between the assessment and the type of assessment
     */
    public void addGrade(String subjectName, boolean credit, GradeInfo.ExamType gradeType)
            throws Exception {
        GradeInfo grades = new GradeInfo(credit, gradeType);
        this.semester.put(subjectName, grades);
    }

    /**
     * getter type of evaluation.
     *
     * @param subjectName course name
     * @return the type of assessment for the subject indicated
     */
    public GradeInfo.ExamType getGradeType(String subjectName) {
        return semester.get(subjectName).getGradeType();
    }

    /**
     * obtaining a grade by subject title.
     *
     * @param subjectName course name
     * @return grade for the subject indicated
     */
    public int getGrade(String subjectName) {
        return semester.get(subjectName).getGrade();
    }

    /**
     * getter of credit by subject name.
     *
     * @param subjectName course name
     * @return pass or fail
     */
    public Boolean getCredit(String subjectName) {
        return semester.get(subjectName).getCredit();
    }

    /**
     * a list of grades for the semester.
     *
     * @return all grades for the semester
     */
    public List<Integer> getValuesGrades() {
        List<Integer> values = new LinkedList<>();
        for (GradeInfo name : semester.values()) {
            if (name.getGradeType() != GradeInfo.ExamType.Credit) {
                values.add(name.getGrade());
            }
        }
        return values;
    }

    /**
     * a list of credits for the semester.
     *
     * @return all credits for the semester.
     */
    public List<Boolean> getValuesCredits() {
        List<Boolean> credits = new LinkedList<>();
        for (GradeInfo name : semester.values()) {
            if (name.getGradeType() == GradeInfo.ExamType.Credit) {
                credits.add(name.getCredit());
            }
        }
        return credits;
    }

    /**
     * many non-repeating subject names.
     *
     * @return all subjects (titles) for the course
     */
    public Set<String> getNames() {
        return semester.keySet();
    }

}
