package me.isaacdjl.disco.ui.intro

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.WindowManager
import com.heinrichreimersoftware.materialintro.app.IntroActivity
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
    lateinit var dispatchingAndroidFragmentInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var introViewModel: IntroViewModel;

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this);
        setFullscreen(true)
        super.onCreate(savedInstanceState)

        // Gotta add this basically useless slide at the beginning.
        // Currently there's a bug that doesn't allow the blocking of navigation on the first slide
        addSlide(SimpleSlide.Builder()
                .title("Welcome to Disco!")
                .description("We're going to take you through some steps here to set up the app. Each is necessary so you can't skip any of them.")
                .background(R.color.slidesBackground)
                .backgroundDark(R.color.slidesBackgroundDark)
                .build())
        addSlide(FragmentSlide.Builder()
                .background(R.color.slidesBackground)
                .backgroundDark(R.color.slidesBackgroundDark)
                .fragment(FoodPrefSlideFragment())
                .build())
        addSlide(SimpleSlide.Builder()
                .title(getString(R.string.locationPermissionSlideTitle))
                .description(getString(R.string.locationPermissionSlideDescription))
                .image(R.drawable.ic_location_on_black_64dp)
                .permission(android.Manifest.permission.ACCESS_FINE_LOCATION)
                .background(R.color.slidesBackground)
                .backgroundDark(R.color.slidesBackgroundDark)
                .build())
        addSlide(FragmentSlide.Builder()
                .background(R.color.slidesBackground)
                .backgroundDark(R.color.slidesBackgroundDark)
                .fragment(LocationPrefSlideFragment())
                .build())

        // Make sure keyboard doesn't automatically come up
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidFragmentInjector
}
