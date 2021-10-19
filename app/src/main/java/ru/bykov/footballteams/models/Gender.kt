package ru.bykov.footballteams.models

import java.util.*

enum class Gender {
    MALE, FEMALE;

    override fun toString(): String {
        return super.toString()
            .lowercase(Locale.getDefault())
            .replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
    }
}