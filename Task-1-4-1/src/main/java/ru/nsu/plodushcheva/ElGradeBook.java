package ru.nsu.plodushcheva;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Электронная зачетная книжка студента,
 * в которой хранятся имя студента, результат его квалификационной работы,
 * номер текущего семестра,
 * массив с семестрами.
 * Каждый семестр представляет из себя HashMap с парами
 * название предмета - инофрмация об оценке по этому предмету.
 */
public class ElGradeBook {
    private String studentName;
    private int qualifyingWork;
    private final SemesterInfo[] semesters = new SemesterInfo[16];
    private int currentSemester;


    /**
     * конструктор зачетной книжки.
     *
     * @param studentName имя студента
     * @param qualifyingWork оценка за квалификационную работу
     * @param currentSemester номер текущего семестра
     */
    public ElGradeBook(String studentName, int qualifyingWork, int currentSemester) {
            this.studentName = studentName;
            this.qualifyingWork = qualifyingWork;
            this.currentSemester = currentSemester;
            for (int i = 0; i < 8; i++) {
                semesters[i] = new SemesterInfo();
            }
    }

    /**
     * @return имя студента
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * @param studentName имя студента
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * @return номер текущего семестра
     */
    public int getCurrentSemester() {
        return currentSemester;
    }

    /**
     * @param currentSemester номер текущего семетра
     */
    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    /**
     * @param qualifyingWork оценка за квалификационную работу
     */
    public void setQualifyingWork(int qualifyingWork) {
        this.qualifyingWork = qualifyingWork;
    }

    /**
     * @return оценка за квалификационную работу
     */
    public int getQualifyingWork() {
        return qualifyingWork;
    }

    /**
     * @param semester номер семестра с записываемыми оценками
     * @param grade оценка за предмет
     * @param type тип оценивания
     * @param subject название предмета
     * @throws Exception если тип оценивания не соотвествует оценке
     */
    public void addGrades(int semester, int grade,
                          GradeInfo.ExamType type, String subject) throws Exception {
        semesters[semester - 1].addGrade(subject, grade, type);
    }

    /**
     * @param semester номер семестра с записываемыми оценками
     * @param credit зачет незачет за предмет
     * @param type тип оценивания - зачет
     * @param subject название предмета
     * @throws Exception елси тип оценивания не Credit
     */
    public void addGrades(int semester, boolean credit,
                          GradeInfo.ExamType type, String subject) throws Exception {
        semesters[semester - 1].addGrade(subject, credit, type);
    }

    /**
     * @param semester номер семетра
     * @return оценки за укзаный семестр
     */
    public List<Integer> getSemesterGrades(int semester) {
        return new ArrayList<>(semesters[semester - 1].getValuesGrades());
    }

    /**
     * @return оценки за весь период обучения
     */
    public List<Integer> getAllGrades() {
        List<Integer> grades = new ArrayList<>();
        for (int i = 0; i < currentSemester - 1; i++) {
            grades.addAll(semesters[i].getValuesGrades());
        }
        return grades;
    }

    /**
     * @return зачеты за весь период обучения
     */
    public List<Boolean> getAllCredits() {
        List<Boolean> credits = new LinkedList<>();
        for (int i = 0; i < currentSemester - 1; i++) {
            credits.addAll(semesters[i].getValuesCredits());
        }
        return credits;
    }

    /**
     * @return финальные оценки (за последние семестры по предмету)
     */
    public List<Integer> getAllFinalGrades() {
        List<Integer> grades = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        for (int i = currentSemester - 1; i >= 0; i--) {
            Set<String> names = semesters[i].getNames();
            for (String name : names) {
                if (!temp.contains(name) &&
                        semesters[i].getGradeType(name)!= GradeInfo.ExamType.Credit) {
                    grades.add(semesters[i].getGrade(name));
                    temp.add(name);
                }
            }
        }
        return grades;
    }

    /**
     * @return средний балл
     */
    public double gpa() {

        Integer sumForGpa = getAllGrades().stream().reduce(0, Integer::sum);
        return (double) sumForGpa/getAllGrades().size();
    }

    /**
     * @return возможно ли получение красного диплома в текущем семестре
     */
    public boolean redDiploma() {
        int countOfFive = (int) getAllFinalGrades().stream().filter(x -> x == 5).count();
        int countOfThree = (int) getAllFinalGrades().stream().filter(x -> x == 3).count();

        if ( getAllCredits().contains(false)) {
            return false;
        }

        return (double) countOfFive/getAllFinalGrades().size() >= 0.75 &&
                (qualifyingWork == 5) && countOfThree == 0;
    }

    /**
     * @return возмонжо ли получение стипендии в текущем семестре
     */
    public boolean scholarship() {
        List<Integer> grades = new ArrayList<>(semesters[currentSemester - 2].getValuesGrades());

        return getAllCredits().stream().allMatch(x -> x) &&
                grades.stream().noneMatch(x -> x < 4);

    }

    /**
     * @return возмонжо ли получение повышенной стипендии в текущем семестре
     */
    public boolean upperScholarship() {
        List<Integer> grades = semesters[currentSemester - 2].getValuesGrades();

        return getAllCredits().stream().allMatch(x -> x) &&
                grades.stream().noneMatch(x -> x < 4) &&
                grades.stream().filter(x -> x == 4).count() <= 1;
    }

}

