package ru.nsu.plodushcheva;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SemesterInfo {
    Map<String, GradeInfo> semester = new HashMap<>();
    private final List<Integer> vals = new ArrayList<>();
    private final List<String> names = new ArrayList<>();

    public void addGrade(String subjectName, Integer[] grades){
        GradeInfo gradesI = new GradeInfo(grades);
        this.semester.put(subjectName, gradesI);
        vals.add(grades[0]);
        names.add(subjectName);

    }
    // получаем оценку по имени предмета
    public int getGrade(String subjectName){
        GradeInfo grades = semester.get(subjectName);
        return grades.getGrade();
    }

    public List<Integer> getValues(){
        return vals;
    }
    public List<String> getNames(){
        return names;
    }

}
