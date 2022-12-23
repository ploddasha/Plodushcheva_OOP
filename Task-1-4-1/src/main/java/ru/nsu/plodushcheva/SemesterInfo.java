package ru.nsu.plodushcheva;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * инофрмация о семестре
 * список пар
 * название предмета - информация об оценке за этот предмет.
 */
public class SemesterInfo {
    Map<String, GradeInfo> semester = new HashMap<>();

    /**
     * для экзамена и дифференцированного зачета
     * добавление оценки за семестр по предмету.
     *
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
     * для зачета
     * добавление оценки за семестр по предмету.
     *
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
     * геттер типа оценивания.
     *
     * @param subjectName имя предмета
     * @return тип оценивания по указанному предмету
     */
    public GradeInfo.ExamType getGradeType(String subjectName) {
        return semester.get(subjectName).getGradeType();
    }

    /**
     * получение оценки по навзанию предмета.
     *
     * @param subjectName имя предмета
     * @return оценка за указанный предмет
     */
    public int getGrade(String subjectName) {
        return semester.get(subjectName).getGrade();
    }

    /**
     * геттер зачета по названию предмета.
     *
     * @param subjectName имя предмета
     * @return зачет или незачет
     */
    public Boolean getCredit(String subjectName) {
        return semester.get(subjectName).getCredit();
    }

    /**
     * список оценок за семестр.
     *
     * @return все оценки за семестр
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
     * список зачетов за семестр.
     *
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
     * множество неповторяющихся назвнаий предметов.
     *
     * @return все предметы (названия) за семестр
     */
    public Set<String> getNames() {
        return semester.keySet();
    }

}
