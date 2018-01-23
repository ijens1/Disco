package me.isaacdjl.disco.ui.intro

import android.content.Context
import me.isaacdjl.disco.application.DiscoApplication
import javax.inject.Inject

/**
 * The model/datamodel for the intro activity.
 * Provides the restaurant types and insertions into the database
 *
 * @author: Isaac Jensen-Large
 */
class IntroDataModel() {

    @Inject
    lateinit var appContext: Context

    val discoApplication: DiscoApplication = DiscoApplication()

    init {
        DiscoApplication.discoComponent.inject(this)
    }

}