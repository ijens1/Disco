package me.isaacdjl.disco

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import me.isaacdjl.disco.data.repository.Repository
import me.isaacdjl.disco.ui.intro.IntroViewModel
import me.isaacdjl.disco.ui.intro.MainIntroActivity

/**
 * Handles creation of viewmodels for various ui controllers
 *
 * @author Isaac Jensen-Large
 */

class ViewModelFactory(val repository: Repository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(IntroViewModel::class.java) ->  IntroViewModel(repository) as T
            else -> throw IllegalArgumentException("Unknown ViewModel Class")
        }
    }
}