
package me.isaacdjl.disco.application

import android.app.Activity
import android.app.Application
import android.support.v4.app.Fragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasFragmentInjector
import dagger.android.support.HasSupportFragmentInjector
import me.isaacdjl.disco.di.DaggerDiscoAppComponent
import javax.inject.Inject

/**
 * Definition of our application that can supply context to non-activity classes such as
 * the IntroDatamodel
 *
 * @author
 */

class DiscoApplication : Application(), HasActivityInjector{

    @Inject
    lateinit var dispatchingAndroidActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerDiscoAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidActivityInjector

}