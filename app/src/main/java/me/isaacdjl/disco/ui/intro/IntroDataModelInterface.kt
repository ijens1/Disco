package me.isaacdjl.disco.ui.intro

import android.arch.lifecycle.MutableLiveData

/**
 * Created by x1c on 24/01/18.
 */
interface IntroDataModelInterface {

    fun retrieveRestaurantTypes(): MutableLiveData<Array<String>>
}