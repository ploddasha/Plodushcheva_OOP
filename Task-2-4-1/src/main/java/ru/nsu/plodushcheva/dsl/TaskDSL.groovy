package ru.nsu.plodushcheva.dsl

import static groovy.lang.Closure.DELEGATE_ONLY


class TaskDSL {

    static ArrayList<TaskDSL> tasks = new ArrayList<>()

    static void task(String name, @DelegatesTo(value = TaskDSL, strategy = DELEGATE_ONLY) final Closure closure) {

        TaskDSL task = new TaskDSL(name)

        tasks << task

        closure.delegate = task
        closure.resolveStrategy = DELEGATE_ONLY

        closure.call()
    }
}
