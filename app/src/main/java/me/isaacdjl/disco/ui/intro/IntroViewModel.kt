package me.isaacdjl.disco.ui.intro

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import me.isaacdjl.disco.data.repository.Repository
import javax.inject.Inject

/**
 * Handles manipulation of data for the intro activity ui controller
 *
 * @author Isaac Jensen-Large
 */
class IntroViewModel(val repository: Repository): ViewModel() {
    private var restaurantTypes: Array<String>? = null

    fun retrieveRestaurantTypes(): Array<String>{
        if (null == restaurantTypes){
            restaurantTypes = repository.retrieveRestaurantTypes()
        }
        return restaurantTypes!!
    }
}