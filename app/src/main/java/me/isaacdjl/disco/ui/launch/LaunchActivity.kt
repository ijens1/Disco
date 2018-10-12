package me.isaacdjl.disco.ui.launch

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import me.isaacdjl.disco.ui.intro.MainIntroActivity
import me.isaacdjl.disco.ui.main.FeedActivity

/**
 * Handles the launch of the app. If the user has already seen the intro, send them to the FeedActivity
 * (main activity). If not, send them to the IntroActivity
 *
 * @author Isaac Jensen-Large
 */

class LaunchActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPreferences: SharedPreferences = this.getSharedPreferences("user_history", MODE_PRIVATE)
        if (sharedPreferences.getBoolean("first_open", true)) {
            val openIntroIntent = Intent(this, MainIntroActivity::class.java)
            startActivity(openIntroIntent)
        } else {
            val openMainIntent = Intent(this, FeedActivity::class.java)
            startActivity(openMainIntent)
        }

        finish()
    }
}