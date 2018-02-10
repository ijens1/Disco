package me.isaacdjl.disco.ui.intro

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.WindowManager
import android.widget.NumberPicker
import com.heinrichreimersoftware.materialintro.app.IntroActivity
import com.heinrichreimersoftware.materialintro.app.OnNavigationBlockedListener
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import me.isaacdjl.disco.R
import javax.inject.Inject

/**
 * The intro activity that runs the intro slides to retrieve preferences from the user
 *
 * @author Isaac Jensen-Large
 */

class MainIntroActivity : IntroActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidFragmentInjector : DispatchingAndroidInjector<Fragment>

    private lateinit var introViewModel: IntroViewModel;

    override fun onCreate(savedInstanceState: Bundle?){
        AndroidInjection.inject(this);
        setFullscreen(true)
        super.onCreate(savedInstanceState)

        // User has entered the intro, make sure they don't see it again
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("user_history", MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean("first_open", true)
        editor.apply()

        addSlide( FragmentSlide.Builder()
                .background(R.color.colorFoodPrefSlideBackground)
                .backgroundDark(R.color.colorFoodPrefSlideBackgroundDark)
                .fragment(FoodPreferencesSlideFragment())
                .build())
        addSlide( SimpleSlide.Builder()
                .background(R.color.colorFoodPrefSlideBackground)
                .backgroundDark(R.color.colorFoodPrefSlideBackgroundDark)
                .build())


        addOnNavigationBlockedListener(object: OnNavigationBlockedListener {
            override fun onNavigationBlocked(position: Int, direction: Int) {
                if (position == 0 && direction == OnNavigationBlockedListener.DIRECTION_FORWARD) {
                    val builder: AlertDialog.Builder = AlertDialog.Builder(applicationContext, android.R.style.Theme_Material_Dialog_Alert)
                    builder.setTitle("Food Preferences Empty")
                            .setMessage("You can't use the app without putting in some eats.")
                            .setNeutralButton(R.string.OK, object : DialogInterface.OnClickListener {
                                override fun onClick(dialog: DialogInterface?, which: Int) {}
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show()
                }
            }
        })

        // Make sure keyboard doesn't automatically come up
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidFragmentInjector
}