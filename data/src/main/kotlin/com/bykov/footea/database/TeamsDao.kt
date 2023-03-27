package com.bykov.footea.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bykov.footea.database.model.TeamEntity
import io.reactivex.Maybe
import io.reactivex.Single

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