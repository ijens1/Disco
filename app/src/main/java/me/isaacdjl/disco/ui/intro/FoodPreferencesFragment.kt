package me.isaacdjl.disco.ui.intro

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import com.pchmn.materialchips.ChipsInput
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.fragment_food_preferences.*
import me.isaacdjl.disco.R
import me.isaacdjl.disco.ViewModelFactory
import javax.inject.Inject

class FoodPreferencesFragment : Fragment(){

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @BindView(R.id.input_chips_food_preferences)
    lateinit var foodPreferencesChipsInput: ChipsInput;

    lateinit var introViewModel: IntroViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        AndroidInjection.inject(this)
        return inflater.inflate(R.layout.fragment_food_preferences, container, false)

        // Retrieve the viewModel
        introViewModel = ViewModelProviders.of(this, viewModelFactory).get(IntroViewModel::class.java)

    }
}