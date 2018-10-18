package me.isaacdjl.disco.IntroTests

import com.google.android.gms.maps.model.LatLng
import me.isaacdjl.disco.ui.intro.DateTimePrefSlideFragment
import me.isaacdjl.disco.ui.intro.FoodPrefSlideFragment
import me.isaacdjl.disco.ui.intro.IntroViewModel
import me.isaacdjl.disco.ui.intro.LocationPrefSlideFragment
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class IntroSlideFragmentsTest {

    @Mock
    lateinit var introViewModel: IntroViewModel

    @InjectMocks
    var foodPrefSlideFragment = FoodPrefSlideFragment()

    @InjectMocks
    var locationPrefSlideFragment = LocationPrefSlideFragment()

    @InjectMocks
    var dateTimePrefSlideFragment = DateTimePrefSlideFragment()

    @Test
    fun cantGoForwardWhenPrefsEmpty() {
        `when`(introViewModel.retrieveUserFoodPreferences()).thenReturn(null)
        `when`(introViewModel.retrieveUserLocationPreference()).thenReturn(null)
        `when`(introViewModel.retrieveUserEatDates()).thenReturn(null)

        val foodCanGoForward = foodPrefSlideFragment.canGoForward()
        val locationCanGoForward = locationPrefSlideFragment.canGoForward()
        val dateTimeCanGoForward = dateTimePrefSlideFragment.canGoForward()

        assertThat(foodCanGoForward, `is`(false))
        assertThat(locationCanGoForward, `is`(false))
        assertThat(dateTimeCanGoForward, `is`(false))
    }

    @Test
    fun canGoForwardWhenPrefsNotEmpty() {
        `when`(introViewModel.retrieveUserFoodPreferences()).thenReturn(ArrayList())
        `when`(introViewModel.retrieveUserLocationPreference()).thenReturn(LatLng(0.0, 0.0))
        `when`(introViewModel.retrieveUserEatDates()).thenReturn(ArrayList())

        val foodCanGoForward = foodPrefSlideFragment.canGoForward()
        val locationCanGoForward = locationPrefSlideFragment.canGoForward()
        val dateTimeCanGoForward = dateTimePrefSlideFragment.canGoForward()

        assertThat(foodCanGoForward, `is`(true))
        assertThat(locationCanGoForward, `is`(true))
        assertThat(dateTimeCanGoForward, `is`(true))
    }
}