package me.isaacdjl.disco.ui.intro

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heinrichreimersoftware.materialintro.app.SlideFragment
import com.pchmn.materialchips.ChipsInput
import com.pchmn.materialchips.model.ChipInterface
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_food_preferences.*
import me.isaacdjl.disco.R
import me.isaacdjl.disco.ViewModelFactory
import me.isaacdjl.disco.nonNull
import me.isaacdjl.disco.observe
import javax.inject.Inject

/**
 * Slide Fragment which retrieves data about user food preferences
 *
 * @author Isaac Jensen-Large
 */

class FoodPrefSlideFragment : SlideFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var introViewModel: IntroViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        introViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(IntroViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_food_preferences, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        introViewModel.getAllFoodTypesLiveData().nonNull().observe(this) { foodTypes ->
             // Initialize the list of possible food types the user can select
            val allFoodChips = ArrayList<FoodPreferenceChip>()
            for ((i, foodType) in foodTypes.withIndex()) {
                allFoodChips.add(FoodPreferenceChip(i, foodType))
            }
            foodPreferencesChipsInput.filterableList = allFoodChips
        }

        introViewModel.getAllFoodTypes()

        // Initialize the list of user preferences if they have already selected some
        introViewModel.retrieveUserFoodPreferences()?.let {
            for ((i, userFoodPreference) in it.withIndex()){
                foodPreferencesChipsInput.addChip(FoodPreferenceChip(i, userFoodPreference))
            }
        }

        // Make sure to keep track of changes in viewModel
        foodPreferencesChipsInput.addChipsListener(object : ChipsInput.ChipsListener{
            override fun onChipAdded(chipAdded: ChipInterface?, p1: Int) {
                if (chipAdded != null) {
                    introViewModel.addUserFoodPreference(chipAdded.label)
                }
            }

            override fun onChipRemoved(chipRemoved: ChipInterface?, p1: Int) {
                if (chipRemoved != null) {
                    introViewModel.removeUserFoodPreference(chipRemoved.label)
                }
            }

            override fun onTextChanged(p0: CharSequence?) {}
        })
    }

    /**
     * For some reason the library calls the conGoForward function before it's actually required for
     * use. So, it happens that it gets called before Dagger has had the chance to inject into the
     * fragment. Forces check on initialization of the viewModel.
     */
    override fun canGoForward(): Boolean = (::introViewModel.isInitialized && introViewModel.retrieveUserFoodPreferences() != null)
}