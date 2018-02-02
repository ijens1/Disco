package me.isaacdjl.disco.application

import android.app.Activity
import android.app.Application
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import me.isaacdjl.disco.di.AppModule
import me.isaacdjl.disco.di.DaggerAppComponent
import me.isaacdjl.disco.di.IntroDataModelModule
import javax.inject.Inject

/**
 * Definition of our application that can supply context to non-activity classes such as
 * the IntroDatamodel
 *
 * @author
 */

class DiscoApplication : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .application(this)
                .introDataModelModule(IntroDataModelModule(this))
                .appModule(AppModule(this))
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}
