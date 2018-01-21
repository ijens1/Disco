package me.isaacdjl.disco.ui.intro

import android.os.Bundle
import android.os.PersistableBundle
import butterknife.BindView
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.pchmn.materialchips.ChipsInput
import kotlinx.android.synthetic.main.fragment_food_preferences.view.*
import me.isaacdjl.disco.R

/**
 * The intro activity that runs the intro slides to retrieve preferences from the user
 *
 * @author: Isaac Jensen-Large
 */
class MainIntroActivity : IntroActivity(){

    @BindView(R.id.input_chips_food_preferences)
    lateinit var foodPreferencesChipsInput: ChipsInput;

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }
}