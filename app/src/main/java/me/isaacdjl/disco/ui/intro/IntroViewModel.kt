package me.isaacdjl.disco.ui.intro

import android.arch.lifecycle.ViewModel
import com.pchmn.materialchips.model.Chip
import com.pchmn.materialchips.model.ChipInterface
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

    fun retrieveUserFoodPreferences(): ArrayList<ChipInterface>{
        if (!(::userFoodPreferences.isInitialized)) {
            return ArrayList<ChipInterface>()
        }
        return userFoodPreferences
    }

    fun addUserFoodPreference(chip: ChipInterface) {
        if(!(::userFoodPreferences.isInitialized)) {
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

}