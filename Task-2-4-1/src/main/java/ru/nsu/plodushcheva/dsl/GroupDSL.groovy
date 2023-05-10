package ru.nsu.plodushcheva.dsl

import ru.nsu.plodushcheva.models.Group
import static groovy.lang.Closure.DELEGATE_ONLY


class GroupDSL {
    static ArrayList<Group> groups = new ArrayList<>()

    static void group(String groupId, @DelegatesTo(value = StudentDSL, strategy = DELEGATE_ONLY) final Closure closure) {

        Group group = new Group(groupId)

        groups << group

        closure.delegate = group.getStudentsDSL()
        closure.resolveStrategy = DELEGATE_ONLY

        closure.call()
    }
}
