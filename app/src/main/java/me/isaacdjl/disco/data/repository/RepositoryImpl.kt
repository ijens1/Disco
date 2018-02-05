package me.isaacdjl.disco.data.repository

import android.content.Context
import me.isaacdjl.disco.R

/**
 * The model for the app
 *
 * @author Isaac Jensen-Large
 */
class RepositoryImpl(val app: Context): Repository {

    override fun retrieveRestaurantTypes(): Array<String> {
        return app.resources.getStringArray(R.array.restaurant_types)
    }
}