
package me.isaacdjl.disco.application

import android.app.Activity
import android.app.Application
import android.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector
import me.isaacdjl.disco.di.DaggerDiscoAppComponent
import javax.inject.Inject

/**
 * Definition of our application that can supply context to non-activity classes such as
 * the IntroDatamodel
 *
 * @author
 */

class DiscoApplication : Application(), HasActivityInjector, HasFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidActivityInjector: DispatchingAndroidInjector<Activity>

    @Inject
    lateinit var dispatchingAndroidFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate() {
        super.onCreate()

        DaggerDiscoAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidActivityInjector
    override fun fragmentInjector(): AndroidInjector<Fragment> = dispatchingAndroidFragmentInjector
}