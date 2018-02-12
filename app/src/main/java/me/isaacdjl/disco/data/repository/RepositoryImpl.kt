package me.isaacdjl.disco.data.repository

import android.content.Context
import me.isaacdjl.disco.R
import me.isaacdjl.disco.data.database.daos.UserPrefsDao
import javax.inject.Inject

/**
 * The model for the app
 *
 * @author Isaac Jensen-Large
 */
class RepositoryImpl(val app: Context): Repository {
    @Inject
    lateinit var userPrefsDao: UserPrefsDao

    override fun retrieveRestaurantTypes(): Array<String> {
        return app.resources.getStringArray(R.array.restaurant_types)
    }

    override fun retrieveUserPrefsDao(): UserPrefsDao = userPrefsDao
}