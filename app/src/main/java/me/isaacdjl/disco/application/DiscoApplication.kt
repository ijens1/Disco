package me.isaacdjl.disco.application

import android.app.Application
import me.isaacdjl.disco.dagger.AppComponent
import me.isaacdjl.disco.dagger.AppModule
import me.isaacdjl.disco.dagger.DaggerAppComponent

/**
 * Definition of our application that can supply context to non-activity classes such as
 * the IntroDatamodel
 *
 * @author
 */

class DiscoApplication : Application() {

    lateinit var discoComponent: AppComponent

    private fun initDagger(app: DiscoApplication): AppComponent =
            DaggerAppComponent.builder()
                    .appModule(AppModule(app))
                    .build()

    override fun onCreate() {
        super.onCreate()
        discoComponent = initDagger(this)
    }
}
