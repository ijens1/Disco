package me.isaacdjl.disco.di

import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import me.isaacdjl.disco.application.DiscoApplication

/**
 * Responsible for injection of dispatchingAndroidInjectors and also building the component graph
 *
 * @author Isaac Jensen-Large
 */

@Component(modules = [AndroidSupportInjectionModule::class,
        DiscoAppModule::class,
        BuildersModule::class])
interface DiscoAppComponent {
    @Component.Builder
    interface Builder {
        fun discoAppModule(discoAppModule: DiscoAppModule): Builder
        @BindsInstance
        fun application(application: DiscoApplication): Builder
        fun build(): DiscoAppComponent
    }
    fun inject(application: DiscoApplication)
}