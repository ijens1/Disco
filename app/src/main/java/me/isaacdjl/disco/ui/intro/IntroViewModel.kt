package me.isaacdjl.disco.ui.intro

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import javax.inject.Inject

class IntroViewModel @Inject constructor(var dataModel: IntroDataModelInterface): ViewModel() {
    private var restaurantTypes: MutableLiveData<Array<String>>? = null

    fun retrieveRestaurantTypes(): MutableLiveData<Array<String>> {
        if (restaurantTypes == null) {
            restaurantTypes = dataModel.retrieveRestaurantTypes()
        }
        return restaurantTypes!!
    }

}