package me.isaacdjl.disco.ui.intro

import android.arch.lifecycle.MutableLiveData
import android.content.Context
import me.isaacdjl.disco.R

class IntroDataModelImpl(val appContext: Context): IntroDataModelInterface {

    override fun retrieveRestaurantTypes(): MutableLiveData<Array<String>> {
        val restaurantTypes = MutableLiveData<Array<String>>()
        restaurantTypes.value = appContext.resources.getStringArray(R.array.restaurant_types)
        return restaurantTypes
    }
}