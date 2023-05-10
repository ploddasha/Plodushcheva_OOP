package ru.nsu.plodushcheva.models

class Student {
    String groupId
    String id
    String username
    String repoUrl
    ArrayList<String> tasks = []

    Student(String groupId) {
        this.groupId = groupId
    }

    void id(String id) {
        this.id = id
    }

    void username(String username) {
        this.username = username
    }

    void repoUrl(String url) {
        this.repoUrl = url
    }

    void tasks(String... tasks) {
        this.tasks.addAll(tasks)
    }
}
