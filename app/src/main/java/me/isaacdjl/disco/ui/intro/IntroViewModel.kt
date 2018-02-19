package me.isaacdjl.disco.ui.intro

import android.arch.lifecycle.ViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.pchmn.materialchips.model.ChipInterface
import io.reactivex.disposables.CompositeDisposable
import me.isaacdjl.disco.data.repository.Repository

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

}