package me.isaacdjl.disco.data.database.daos

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import io.reactivex.Flowable
import me.isaacdjl.disco.data.database.entities.UserPrefs

/**
 * Data Access Object for the users preferences retrieved from intro
 *
 * @author Isaac Jensen-Large
 */

@Dao interface UserPrefsDao {

    @Query("select * from userPrefs")
    fun getAllUserPrefs(): Flowable<ArrayList<UserPrefs>>

    @Query("select * from userPrefs where id = :id")
    fun findUserPrefsById(id: Long): UserPrefs

    @Insert(onConflict = REPLACE)
    fun insertUserPrefs(userPrefs: UserPrefs)

    @Update(onConflict = REPLACE)
    fun updateUserPrefs(userPrefs: UserPrefs)

    @Delete
    fun deleteUserPrefs(userPrefs: UserPrefs)
}
