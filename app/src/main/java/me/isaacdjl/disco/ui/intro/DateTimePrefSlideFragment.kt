package me.isaacdjl.disco.ui.intro

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.heinrichreimersoftware.materialintro.app.SlideFragment
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_datetime_preferences.*
import me.isaacdjl.disco.R
import me.isaacdjl.disco.ViewModelFactory
import java.util.*
import javax.inject.Inject

/**
 * Slide Fragment which retrieves data about the dates and times at which the user would like to go
 * out for eats
 *
 * @author Isaac Jensen-Large
 */
class DateTimePrefSlideFragment: SlideFragment() {

    enum class CalendarModificationResult {
        ADD_NEW_EAT, MODIFY_OR_ADD_NEW_EAT
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var introViewModel: IntroViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    /**
     * Possible source of NPE here. See the whart's explanation though
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        introViewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(IntroViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_datetime_preferences, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dateTimeCalendarView.selectionMode = MaterialCalendarView.SELECTION_MODE_MULTIPLE

        dateTimeCalendarView.setOnDateChangedListener { _ , date: CalendarDay, selected: Boolean ->
            val calendarModificationResult: CalendarModificationResult = introViewModel.handleCalendarModification(selected)
            val newDate = Calendar.getInstance()
            newDate.set(date.year, date.month, date.day)
            introViewModel.setCurrentDateSelected(newDate)
            if (calendarModificationResult == CalendarModificationResult.ADD_NEW_EAT) {
                val ft = fragmentManager?.beginTransaction()
                val newFragment = TimePickerFragment()
                newFragment.show(ft, "timerPickerDialog")
            } else {
                // Start a new dialog that allows the user to add new eat or modify an existing one
            }
        }
    }
}