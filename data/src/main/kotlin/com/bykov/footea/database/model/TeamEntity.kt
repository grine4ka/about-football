package com.bykov.footea.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bykov.footea.models.FootballTeam
import com.bykov.footea.models.FootballTeamDetails

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

fun TeamEntity.toTeam(): FootballTeam = FootballTeam(
    id,
    name,
    logo
)

fun TeamEntity.toTeamDetails(): FootballTeamDetails = FootballTeamDetails(
    id,
    name,
    country,
    "$venueName with capacity $venueCapacity",
    logo
)
