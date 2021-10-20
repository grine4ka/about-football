package ru.bykov.footballteams.models

import com.google.gson.annotations.SerializedName
import java.util.*

enum class Gender {
    @SerializedName("male")
    MALE,
    @SerializedName("female")
    FEMALE;

    override fun toString(): String {
        return super.toString()
            .lowercase(Locale.getDefault())
            .replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
    }
}