package me.isaacdjl.disco.ui.intro

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heinrichreimersoftware.materialintro.app.SlideFragment
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_datetime_preferences.*
import me.isaacdjl.disco.R
import me.isaacdjl.disco.ViewModelFactory
import javax.inject.Inject

/**
 * Slide Fragment which retrieves data about the dates and times at which the user would like to go
 * out for eats
 *
 * @author Isaac Jensen-Large
 */
class DateTimePrefSlideFragment: SlideFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var introViewModel: IntroViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        // Retrieve the viewModel
        introViewModel = ViewModelProviders.of(this, viewModelFactory).get(IntroViewModel::class.java)

        return inflater.inflate(R.layout.fragment_datetime_preferences, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dateTimeCalendarView.selectionMode = MaterialCalendarView.SELECTION_MODE_MULTIPLE
    }
}