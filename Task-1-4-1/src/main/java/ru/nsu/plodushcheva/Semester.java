package ru.nsu.plodushcheva;

import java.util.ArrayList;

public class Semester {
    private ArrayList<Subject> subjects;

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
    }
}
