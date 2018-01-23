package me.isaacdjl.disco.ui.intro

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import com.pchmn.materialchips.ChipsInput
import kotlinx.android.synthetic.main.fragment_food_preferences.*
import me.isaacdjl.disco.R

class FoodPreferencesFragment : Fragment(){

    @BindView(R.id.input_chips_food_preferences)
    lateinit var foodPreferencesChipsInput: ChipsInput;

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_food_preferences, container, false)


    }
}