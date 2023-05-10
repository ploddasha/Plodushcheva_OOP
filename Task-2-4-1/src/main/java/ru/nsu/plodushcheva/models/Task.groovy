package ru.nsu.plodushcheva.models

class Task {
    String name
    Integer points
    Integer id


    Task(String name) {
        this.name = name
    }

    void points(Integer points) {
        this.points = points
    }


}
