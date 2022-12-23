package ru.nsu.plodushcheva;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;


/**
 * инофрмация о семестре
 * список пар
 * название предмета - информация об оценке за этот предмет.
 */
public class SemesterInfo {
    Map<String, GradeInfo> semester = new HashMap<>();

    /**
     * @param subjectName имя предмета
     * @param grade оценка по указанному предмету
     * @param gradeType тип итогового оценивая
     * @throws Exception при несоответвии оценки и типа оценивания
     */
    public void addGrade(String subjectName, Integer grade, GradeInfo.ExamType gradeType)
            throws Exception {
        GradeInfo grades = new GradeInfo(grade, gradeType);
        this.semester.put(subjectName, grades);
    }

    /**
     * @param subjectName имя предмета
     * @param credit зачет незачет
     * @param gradeType тип итогового оценивая
     * @throws Exception при несоответвии оценки и типа оценивания
     */
    public void addGrade(String subjectName, Boolean credit, GradeInfo.ExamType gradeType)
            throws Exception {
        GradeInfo grades = new GradeInfo(credit, gradeType);
        this.semester.put(subjectName, grades);
    }

    /**
     * @param subjectName имя предмета
     * @return тип оценивания по указанному предмету
     */
    public GradeInfo.ExamType getGradeType (String subjectName) {
        return semester.get(subjectName).getGradeType();
    }

    /**
     * @param subjectName имя предмета
     * @return оценка за указанный предмет
     */
    public int getGrade(String subjectName) {
        return semester.get(subjectName).getGrade();
    }

    /**
     * @param subjectName имя предмета
     * @return зачет или незачет
     */
    public Boolean getCredit(String subjectName) {
        return semester.get(subjectName).getCredit();
    }

    /**
     * @return все оценки за семестр
     */
    public List<Integer> getValuesGrades() {
        List<Integer> values = new LinkedList<>();
        for (GradeInfo name : semester.values()){
            if (name.getGradeType() != GradeInfo.ExamType.Credit){
                values.add(name.getGrade());
            }
        }
        return values;
    }

    /**
     * @return все зачеты за семестр
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
     * @return все предметы (названия) за семестр
     */
    public Set<String> getNames() {
        return semester.keySet();
    }

}
