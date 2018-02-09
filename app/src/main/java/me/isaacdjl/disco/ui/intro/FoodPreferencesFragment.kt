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
import javax.inject.Inject

/**
 * Fragment for first intro slide which retrieves data about user food preferences
 *
 * @author Isaac Jensen-Large
 */

class FoodPreferencesFragment : SlideFragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var foodPreferenceChips: ArrayList<FoodPreferenceChip>

    lateinit var introViewModel: IntroViewModel

    lateinit var userFoodPreferences: ArrayList<ChipInterface>

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context);
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // Retrieve the viewModel
        introViewModel = ViewModelProviders.of(this, viewModelFactory).get(IntroViewModel::class.java)

        foodPreferenceChips = introViewModel.retrieveFoodPreferenceChips()

        userFoodPreferences = introViewModel.retrieveUserFoodPreferences()

        return inflater.inflate(R.layout.fragment_food_preferences, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         // Synthetic property
        foodPreferencesChipsInput.filterableList= foodPreferenceChips

        // Initialize the list of user preferences if they have already selected some
        for (userFoodPreference in userFoodPreferences) {
            foodPreferencesChipsInput.addChip(userFoodPreference)
        }

        // Make sure to keep track of changes in viewModel
        foodPreferencesChipsInput.addChipsListener(object : ChipsInput.ChipsListener{
            override fun onChipAdded(p0: ChipInterface?, p1: Int) {
                if (p0 != null)
                    introViewModel.addUserFoodPreference(p0)
            }

            override fun onChipRemoved(p0: ChipInterface?, p1: Int) {
                if (p0 != null)
                    introViewModel.removeUserFoodPreference(p0)
            }

            override fun onTextChanged(p0: CharSequence?) {}
        })

    }
}