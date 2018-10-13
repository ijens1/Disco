package me.isaacdjl.disco.ui.intro

import android.app.Dialog
import android.app.TimePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.text.format.DateFormat
import android.view.ContextThemeWrapper
import android.widget.TimePicker
import dagger.android.support.AndroidSupportInjection
import me.isaacdjl.disco.R
import me.isaacdjl.disco.ViewModelFactory
import java.util.*
import javax.inject.Inject

/**
 * Allows the user to pick the times they would like to go out to eat
 *
 * @author Isaac Jensen-Large
 */
class TimePickerFragment: DialogFragment(), TimePickerDialog.OnTimeSetListener {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: IntroViewModel

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    /**
     * Possible source of NPE here. See the whart's explanation on reddit.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(requireActivity(), viewModelFactory).get(IntroViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        return TimePickerDialog( ContextThemeWrapper(activity, R.style.MyTimePickerDialog), this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        viewModel.addUserEatTime(hourOfDay, minute)
    }
}