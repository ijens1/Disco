package me.isaacdjl.disco.ui.intro

import android.arch.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng
import me.isaacdjl.disco.data.repository.Repository
import java.util.*

/**
 * Handles manipulation of data for the intro activity ui controller
 *
 * @author Isaac Jensen-Large
 */
class IntroViewModel(val repository: Repository): ViewModel() {

    private var userFoodPref: ArrayList<String>? = null

    private var userLocationPref: LatLng? = null

    private var currDateSelected: Calendar? = null

    private var userEatTimes: HashMap<Calendar, ArrayList<Calendar>>? = null

    fun retrieveAllFoodTypes(): ArrayList<String>{
        return repository.retrieveRestaurantTypes().toCollection(ArrayList())
    }
    // USER FOOD PREFERENCES DATA
    fun retrieveUserFoodPreferences(): ArrayList<String>?{
        return userFoodPref
    }

    fun addUserFoodPreference(pref: String) {
        if (userFoodPref == null) {
            userFoodPref = ArrayList()
        }
        userFoodPref?.add(pref)
    }

    fun removeUserFoodPreference(pref: String) {
        userFoodPref?.remove(pref)
        if (userFoodPref?.size == 0) userFoodPref = null
    }

    // USER LOCATION PREFERENCE DATA

    fun retrieveUserLocationPreference(): LatLng? {
        return userLocationPref
    }

    fun changeUserLocationPreference(latLng: LatLng) {
        userLocationPref = latLng
    }

    // USER DATETIME PREFERENCE DATA

    fun setCurrentDateSelected(date: Calendar) {
        currDateSelected = date
    }

    fun retrieveUserEatDates(): MutableCollection<ArrayList<Calendar>>? {
        return userEatTimes?.values
    }

    fun addUserEatTime(hourOfDay: Int, minute: Int) {
        currDateSelected?.let { currDateSelected ->
            val newEatTime = Calendar.getInstance()
            newEatTime.set(currDateSelected.get(Calendar.YEAR) - 1900, currDateSelected.get(Calendar.MONTH), currDateSelected.get(Calendar.DAY_OF_MONTH), hourOfDay, minute)
            if (userEatTimes == null) {
                userEatTimes = HashMap()
            }
           userEatTimes?.let { userEatTimes ->
                if (userEatTimes.containsKey(currDateSelected)) {
                    userEatTimes.get(currDateSelected)?.add(newEatTime)
                }
                else {
                    val newEatList = ArrayList<Calendar>()
                    newEatList.add(newEatTime)
                    userEatTimes.put(currDateSelected, newEatList)
                }
            }

        }
    }
}