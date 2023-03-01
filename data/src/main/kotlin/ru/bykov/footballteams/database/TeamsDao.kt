package ru.bykov.footballteams.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Maybe
import io.reactivex.Single
import ru.bykov.footballteams.database.model.TeamEntity

@Dao
interface TeamsDao {

    @Query("SELECT * FROM teams")
    fun getAll(): Single<List<TeamEntity>>

    @Query("SELECT * FROM teams WHERE id = :teamId")
    fun getById(teamId: Int): Maybe<TeamEntity>

    /**
     * Inserts [teams] into the db if they don't exist, and ignores those that do
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertOrIgnoreTeams(teams: List<TeamEntity>)
}