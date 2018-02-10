package me.isaacdjl.disco.ui.main

import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import me.isaacdjl.disco.R
import me.isaacdjl.disco.ui.intro.MainIntroActivity

/**
 * The main activity for the app. It shows a feed of the user's upcoming eats and the information
 * about them including date, time, and location from which they are scheduled to leave.
 *
 * This activity is also responsible for directing the user to the intro to the app
 *
 * @author Isaac Jensen-Large
 */
class FeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
    }
}
