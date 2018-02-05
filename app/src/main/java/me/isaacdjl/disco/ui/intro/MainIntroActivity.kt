package me.isaacdjl.disco.ui.intro

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import dagger.android.AndroidInjection
import me.isaacdjl.disco.ViewModelFactory
import me.isaacdjl.disco.application.DiscoApplication
import javax.inject.Inject

/**
 * The intro activity that runs the intro slides to retrieve preferences from the user
 *
 * @author: Isaac Jensen-Large
 */
class MainIntroActivity : IntroActivity(){
    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var restaurantTypes: Array<String>

    private lateinit var introViewModel: IntroViewModel;

    override fun onCreate(savedInstanceState: Bundle?){
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState)

        // User has entered the intro, make sure they don't see it again
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("user_history", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("first_open", true)
        editor.apply()

        introViewModel = viewModelFactory.create(IntroViewModel::class.java)

        restaurantTypes = introViewModel.retrieveRestaurantTypes()
    }
}