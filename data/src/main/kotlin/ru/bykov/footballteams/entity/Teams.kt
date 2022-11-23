package ru.bykov.footballteams.entity

import com.google.gson.annotations.SerializedName
import ru.bykov.footballteams.models.FootballTeam
import ru.bykov.footballteams.models.FootballTeamDetails
import java.util.*

class FootballTeamEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

fun FootballTeamEntity.toDomain(): FootballTeam = FootballTeam(
    id,
    name
)

class FootballTeamDetailsEntity(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("gender")
    val gender: Gender,
    @SerializedName("national")
    val national: Boolean,
    @SerializedName("description")
    val description: String,
    @SerializedName("badge_url")
    val badgeUrl: String
)

fun FootballTeamDetailsEntity.toDomain(baseUrl: String): FootballTeamDetails = FootballTeamDetails(
    id,
    name,
    gender.toString(),
    national,
    description,
    baseUrl + badgeUrl
)

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