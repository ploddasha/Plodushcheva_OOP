package ru.nsu.plodushcheva.models

class Group {
    Student studentsDSL
    String groupId

    Group(String groupId) {
        this.groupId = groupId
        studentsDSL = new Student()
    }
}
