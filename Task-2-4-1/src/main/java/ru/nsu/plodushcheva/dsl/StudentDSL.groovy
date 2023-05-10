package ru.nsu.plodushcheva.dsl

import ru.nsu.plodushcheva.models.Student
import static groovy.lang.Closure.DELEGATE_ONLY


class StudentDSL {

    ArrayList<Student> st = new ArrayList<>()

    void student(@DelegatesTo(value = Student, strategy = DELEGATE_ONLY) final Closure closure) {

        Student student = new Student(GroupsDSL.groups.last().groupId)

        st << student

        closure.delegate = student
        closure.resolveStrategy = DELEGATE_ONLY
        closure.call()
    }
}