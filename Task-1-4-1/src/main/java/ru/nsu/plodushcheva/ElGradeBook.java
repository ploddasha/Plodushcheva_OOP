package ru.nsu.plodushcheva;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * The student's electronic grade book,
 * which stores the student's name, the result of his/her qualification work,
 * current semester number,
 * An array with semesters.
 * Each semester is a HashMap with pairs.
 * course name - information about the grades of the course.
 */
public class ElGradeBook {
    private String studentName;
    private int qualifyingWork;
    private final SemesterInfo[] semesters = new SemesterInfo[16];
    private int currentSemester;


    /**
     * the constructor of the record book.
     *
     * @param studentName student name
     * @param qualifyingWork assessment for qualifying work
     * @param currentSemester current semester's number
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
     * getter of the student's name.
     *
     * @return student name
     */
    public String getStudentName() {
        return studentName;
    }

    /**
     * setter of the student's name.
     *
     * @param studentName student name
     */
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    /**
     * the getter of the number of the current semester.
     *
     * @return current semester's number
     */
    public int getCurrentSemester() {
        return currentSemester;
    }

    /**
     * setter of the current semester's numbers.
     *
     * @param currentSemester current semester's number
     */
    public void setCurrentSemester(int currentSemester) {
        this.currentSemester = currentSemester;
    }

    /**
     * the setter of the qualification work.
     *
     * @param qualifyingWork assessment for qualifying work
     */
    public void setQualifyingWork(int qualifyingWork) {
        this.qualifyingWork = qualifyingWork;
    }

    /**
     * getter of the qualifying work.
     *
     * @return assessment for qualifying work
     */
    public int getQualifyingWork() {
        return qualifyingWork;
    }

    /**
     * for the exam and the dissertation.
     * Adding a grade for a semester in a subject.
     *
     * @param semester semester number with grades to be recorded
     * @param grade grade for course
     * @param type type of assessment - diffCredit or Exam
     * @param subject course name
     * @throws Exception if the type of assessment is Credit
     */
    public void addGrades(int semester, int grade,
                          GradeInfo.ExamType type, String subject) throws Exception {
        semesters[semester - 1].addGrade(subject, grade, type);
    }

    /**
     * for credit
     * Adding a grade for a semester in the course.
     *
     * @param semester semester number with grades to be recorded
     * @param credit credit or non-credit for course
     * @param type type of assessment - credit
     * @param subject course name
     * @throws Exception if the type of assessment is not Credit
     */
    public void addGrades(int semester, boolean credit,
                          GradeInfo.ExamType type, String subject) throws Exception {
        semesters[semester - 1].addGrade(subject, credit, type);
    }

    /**
     * a list of all the grades for the semester.
     *
     * @param semester semester number
     * @return grades for a given semester
     */
    public List<Integer> getSemesterGrades(int semester) {
        return new ArrayList<>(semesters[semester - 1].getValuesGrades());
    }

    /**
     * a list of all grades for the entire period of study.
     *
     * @return grades for the whole period of study
     */
    public List<Integer> getAllGrades() {
        List<Integer> grades = new ArrayList<>();
        for (int i = 0; i < currentSemester - 1; i++) {
            grades.addAll(semesters[i].getValuesGrades());
        }
        return grades;
    }

    /**
     * a list of all credits for the entire period of study.
     *
     * @return credits for the entire period of study
     */
    public List<Boolean> getAllCredits() {
        List<Boolean> credits = new LinkedList<>();
        for (int i = 0; i < currentSemester - 1; i++) {
            credits.addAll(semesters[i].getValuesCredits());
        }
        return credits;
    }

    /**
     * a list of all final grades,
     * Final grades are the most recent grades in the course.
     *
     * @return final grades (for the last semester of the course).
     */
    public List<Integer> getAllFinalGrades() {
        List<Integer> grades = new ArrayList<>();
        Set<String> temp = new HashSet<>();

        for (int i = currentSemester - 1; i >= 0; i--) {
            Set<String> names = semesters[i].getNames();
            for (String name : names) {
                if (!temp.contains(name)
                        && semesters[i].getGradeType(name) != GradeInfo.ExamType.Credit) {
                    grades.add(semesters[i].getGrade(name));
                    temp.add(name);
                }
            }
        }
        return grades;
    }

    /**
     * grade point average
     * all subjects are taken into account
     * credits do not count.
     *
     * @return average score
     */
    public double gpa() {

        int sumForGpa = getAllGrades().stream().reduce(0, Integer::sum);
        return (double) sumForGpa / getAllGrades().size();
    }

    /**
     * conditions for a red diploma:
     * more fives than 75%
     * no fives
     * no failures
     * a qualification paper with a good grade.
     *
     * @return Is it possible to get a red diploma in the current semester
     */
    public boolean redDiploma() {
        int countOfFive = (int) getAllFinalGrades().stream().filter(x -> x == 5).count();
        int countOfThree = (int) getAllFinalGrades().stream().filter(x -> x == 3).count();

        if (getAllCredits().contains(false)) {
            return false;
        }

        return (double) countOfFive / getAllFinalGrades().size() >= 0.75
                && (qualifyingWork == 5) && countOfThree == 0;
    }

    /**
     * you need to have no "3" or "2" levels to get a scholarship.
     *
     * @return Is it possible to receive a scholarship in the current semester
     */
    public boolean scholarship() {
        List<Integer> grades = new ArrayList<>(semesters[currentSemester - 2].getValuesGrades());

        return getAllCredits().stream().allMatch(x -> x)
                && grades.stream().noneMatch(x -> x < 4);

    }

    /**
     * no more than one 4 grade is required for an advanced scholarship.
     *
     * @return Is it possible to receive a scholarship increase in the current semester
     */
    public boolean upperScholarship() {
        List<Integer> grades = semesters[currentSemester - 2].getValuesGrades();

        return getAllCredits().stream().allMatch(x -> x)
                && grades.stream().noneMatch(x -> x < 4)
                && grades.stream().filter(x -> x == 4).count() <= 1;
    }

}