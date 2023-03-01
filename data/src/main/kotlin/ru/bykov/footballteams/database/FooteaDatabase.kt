package ru.bykov.footballteams.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.bykov.footballteams.database.model.TeamEntity

@Database(
    entities = [TeamEntity::class],
    version = FooteaDatabase.LATEST_VERSION,
    exportSchema = true
)
abstract class FooteaDatabase : RoomDatabase() {

    abstract fun teamsDao(): TeamsDao

    companion object {
        @Volatile
        private var instance: FooteaDatabase? = null

        fun getInstance(context: Context): FooteaDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): FooteaDatabase {
            return Room.databaseBuilder(
                context,
                FooteaDatabase::class.java,
                "footea.db"
            ).build()
        }

        const val LATEST_VERSION = 1
    }
}