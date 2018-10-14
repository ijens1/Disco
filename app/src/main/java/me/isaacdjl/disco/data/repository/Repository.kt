package me.isaacdjl.disco.data.repository

import me.isaacdjl.disco.data.database.daos.UserPrefsDao

/**
 * The interface for the model of the app
 *
 * @author Isaac Jensen-Large
 */

interface Repository {

    fun retrieveFoodTypes(): Array<String>

    fun retrieveUserPrefsDao(): UserPrefsDao
}