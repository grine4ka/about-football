package ru.bykov.footballteams.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Defines a team
 */
@Entity(tableName = "teams")
data class TeamEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    @ColumnInfo(defaultValue = "")
    val logo: String,
    val country: String,
    @ColumnInfo(name = "venue_name")
    val venueName: String,
    @ColumnInfo(name = "venue_capacity")
    val venueCapacity: String
)