package me.isaacdjl.disco.data.database.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

/**
 * Entity responsible for storing the user preferences retrieved from the intro
 *
 * @author Isaac Jensen-Large
 */

@Entity(tableName = "userPrefs")
data class UserPrefs(
        @ColumnInfo(name = "foodPrefs") var foodPrefs: ArrayList<String>,
        @ColumnInfo(name = "location") var location: LatLng,
        @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long
)
