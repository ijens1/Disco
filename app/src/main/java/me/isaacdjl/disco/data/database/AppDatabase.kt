package me.isaacdjl.disco.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import me.isaacdjl.disco.data.database.daos.UserPrefsDao
import me.isaacdjl.disco.data.database.entities.UserPrefs

/**
 * The database for the app
 *
 * @author Isaac Jensen-Large
 */

@Database(entities = [UserPrefs::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userPrefsDao(): UserPrefsDao
}
