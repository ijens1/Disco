package me.isaacdjl.disco.ui.intro

import android.arch.lifecycle.ViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.pchmn.materialchips.model.ChipInterface
import io.reactivex.disposables.CompositeDisposable
import me.isaacdjl.disco.data.repository.Repository
import java.util.*

/**
 * Handles manipulation of data for the intro activity ui controller
 *
 * @author Isaac Jensen-Large
 */
class IntroViewModel(val repository: Repository): ViewModel() {

    // Stored repository data
    lateinit private var restaurantTypes: Array<String>

    // Formatted repository dta
    lateinit private var foodPreferenceChips: ArrayList<FoodPreferenceChip>

    // Stored user data
    lateinit private var userFoodPreferences: ArrayList<ChipInterface>

    lateinit private var userLocationPreference: LatLng

    lateinit private var userCameraPosition: CameraPosition

    lateinit private var currentDateSelected: Calendar

    lateinit private var userEatDates: ArrayList<Calendar>

    // RX stuff
    private var compositeDisposable = CompositeDisposable()


    fun retrieveAllFoodPreferenceChips(): ArrayList<FoodPreferenceChip>{
        if (::foodPreferenceChips.isInitialized) {
            return foodPreferenceChips
        }
        if (::restaurantTypes.isInitialized == false){
            restaurantTypes = repository.retrieveRestaurantTypes()
        }
        foodPreferenceChips = ArrayList<FoodPreferenceChip>()

        for (i in restaurantTypes.indices) {
            foodPreferenceChips.add(FoodPreferenceChip(i, restaurantTypes[i]))
        }

        return foodPreferenceChips
    }


    // USER FOOD PREFERENCES DATA
    fun retrieveUserFoodPreferences(): ArrayList<ChipInterface>{
        if (!userHasFoodPreferences()) {
            return ArrayList<ChipInterface>()
        }
        return userFoodPreferences
    }

    fun addUserFoodPreference(chip: ChipInterface) {
        if(!userHasFoodPreferences()) {
            userFoodPreferences = ArrayList<ChipInterface>()
        }
        userFoodPreferences.add(chip)
    }

    /**
     * This is a pretty inefficient method, but since the list of chips is small, it doesn't matter
     */
    fun removeUserFoodPreference(chip: ChipInterface) {
        userFoodPreferences.remove(chip)
    }

    fun userHasFoodPreferences(): Boolean = (::userFoodPreferences.isInitialized && userFoodPreferences.size > 0)


    // USER LOCATION PREFERENCE DATA

    /**
     * Only use this method after having checked that userHaslocationPreference returns true
     */
    fun retrieveUserlocationPreference(): LatLng {
        if (!userHasLocationPreference()) {
            return LatLng(0.toDouble(), 0.toDouble())
        }
        return userLocationPreference
    }

    fun changeUserLocationPreference(latLng: LatLng) {
        userLocationPreference = latLng
    }

    fun userHasLocationPreference(): Boolean = (::userLocationPreference.isInitialized)

    fun retrieveUserCameraPosition(): CameraPosition {
        if (!userHasCameraPosition()) {
            return CameraPosition.Builder().target(LatLng(45.427738, -75.692607)).zoom(5.toFloat()).build()
        }
        return userCameraPosition
    }
     fun changeUserCameraPosition(cameraPosition: CameraPosition) {
         userCameraPosition = cameraPosition
     }

    fun userHasCameraPosition(): Boolean = (::userCameraPosition.isInitialized)

    // USER DATETIME PREFERENCE DATA

    /**
     * Technically there could be some issues here where the proper date isn't set before the user
     * sets the time they would like to have the current eat at, but since this method is called
     * before the dialog is created, that should never be an issue
     */
    fun setCurrentDateSelected(date: Calendar) {
        currentDateSelected = date
    }

    fun retrieveUserEatdates(): ArrayList<Calendar> {
        return userEatDates
    }

    fun addUserEatDate(hourOfDay: Int, minute: Int) {
        val newDate = Calendar.getInstance()
        newDate.set(currentDateSelected.get(Calendar.YEAR) - 1900, currentDateSelected.get(Calendar.MONTH), currentDateSelected.get(Calendar.DAY_OF_MONTH), hourOfDay, minute)
        if (!::userEatDates.isInitialized) {
            userEatDates = ArrayList<Calendar>()
        }
        userEatDates.add(newDate)
    }

    fun userHasEatDates(): Boolean = (::userEatDates.isInitialized && userEatDates.size > 0)

    fun handleCalendarModification(selected: Boolean): DateTimePrefSlideFragment.CalendarModificationResult {
        if (!selected) return DateTimePrefSlideFragment.CalendarModificationResult.MODIFY_OR_ADD_NEW_EAT
        else return DateTimePrefSlideFragment.CalendarModificationResult.ADD_NEW_EAT
    }
}