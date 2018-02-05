package me.isaacdjl.disco.data.repository

/**
 * The interface for the model of the app
 *
 * @author Isaac Jensen-Large
 */

interface Repository {

    fun retrieveRestaurantTypes(): Array<String>
}