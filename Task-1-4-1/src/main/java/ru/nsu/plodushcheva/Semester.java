package ru.nsu.plodushcheva;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static ru.nsu.plodushcheva.Subject.ExamType.Exam;

public class Semester {
    Map<String, Integer> semester = new HashMap<>();
    Map<String, Subject> semester2 = new HashMap<>();


    public void addGrade(String subject, int grade) {
        this.semester.put(subject, grade);
    }

    public void addGrade2(String subject, int grade) {
        Subject a = new Subject("Math",Exam, );
        this.semester2.put(subject, );
    }
    public Collection<Integer> getGrades() {
        return semester.values();
    }
    /*private ArrayList<Subject> subjects;

    public void Semester(){
        subjects = new ArrayList<>();
    }

    Semester(ArrayList<Subject> subjects) {
        this.subjects = subjects;
    }

    public ArrayList <Subject> getSubjects() {
        return subjects;
    }
    public void addSubject(Subject subject) {
        subjects.add(subject);
    } */

}
